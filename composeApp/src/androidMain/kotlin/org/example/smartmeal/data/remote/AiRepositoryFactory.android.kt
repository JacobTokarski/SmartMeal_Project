package org.example.smartmeal.data.remote

import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend
import com.google.firebase.ai.type.content
import io.github.aakira.napier.Napier
import io.ktor.utils.io.CancellationException
import org.example.smartmeal.model.chat.ChatMessage
import org.example.smartmeal.model.chat.ChatPrompts
import kotlin.time.Duration.Companion.milliseconds

private const val MODEL_NAME = "gemini-3.8-flash"

actual fun createAiRepository(): AiRepository = GeminiAiRepository()

private class GeminiAiRepository: AiRepository {


    private val model by lazy {
        Firebase.ai(backend = GenerativeBackend.googleAI()).generativeModel(
            modelName = MODEL_NAME,
            systemInstruction = content { text(ChatPrompts.SYSTEM)},
        )
    }
        override suspend fun ask(history: List<ChatMessage>, prompt: String): AiResult {
            val contents = history
                .dropWhile { it.role == ChatMessage.Role.ASSISTANT }
                .takeLast(2)
                .map { msg ->
                    val roleStr = if (msg.role == ChatMessage.Role.USER) "user" else "model"
                    content(role = roleStr) { text(msg.content.trim()) }
                }

            val maxRetries = 2
            var currentDelay = 2000L

            for (attempt in 0..maxRetries) {
                try {
                    val reply = model.startChat(history = contents).sendMessage(prompt).text
                    if (!reply.isNullOrBlank()) {
                        return AiResult.Success(reply.trim())
                    }
                } catch (e: CancellationException) {
                    throw e
                } catch (e: Exception) {
                    val isOverloaded = e.message?.contains("DEADLINE", ignoreCase = true) == true ||
                            e.message?.contains("OVERLOADED", ignoreCase = true) == true

                    if (isOverloaded && attempt < maxRetries) {
                        kotlinx.coroutines.delay(currentDelay.milliseconds)
                        currentDelay *= 2
                        continue
                    }

                    Napier.e("Błąd zapytania do AI (próba $attempt)", e)
                    val message = if (isOverloaded) {
                        "Serwery przetwarzają zapytanie zbyt długo. Spróbuj zadać krótsze pytanie."
                    } else {
                        "Nie mogę teraz odpowiedzieć - sprawdź połączenie z internetem."
                    }
                    return AiResult.Failure(message)
                }
            }

            return AiResult.Failure("Nie udało się uzyskać odpowiedzi, spróbuj ponownie.")
        }
    }
