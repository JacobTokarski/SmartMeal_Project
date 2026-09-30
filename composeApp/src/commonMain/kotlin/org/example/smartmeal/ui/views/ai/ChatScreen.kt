package org.example.smartmeal.ui.views.ai

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.example.smartmeal.model.chat.ChatMessage
import org.example.smartmeal.model.chat.ChatMessage.Role
import org.example.smartmeal.ui.components.chat.CustomChipSuggestions
import org.example.smartmeal.ui.components.chat.CustomMessageList
import org.example.smartmeal.ui.views.ai.parts.ChatBackground
import org.example.smartmeal.ui.views.ai.parts.ChatHeader
import org.example.smartmeal.ui.views.ai.parts.ChatInputBar
import org.koin.compose.viewmodel.koinViewModel
import kotlin.random.Random
import kotlin.time.Duration.Companion.milliseconds

class ChatScreen: Screen {
    @Composable
    override fun Content() {

        val viewModel = koinViewModel<ChatViewModel>()

        ChatContent(
            viewModel = viewModel
        )
    }
}

@Composable
fun ChatContent(
    viewModel: ChatViewModel
) {
    var inputBarValue by remember { mutableStateOf("") }
    var isBotTyping by remember { mutableStateOf(false) }
    var placeholderChange by remember { mutableStateOf("Jakimi składnikami dysponujesz?")}
    val scope = rememberCoroutineScope()

    val messages = remember {
        mutableStateListOf(
            ChatMessage(
                id = "Witaj",
                role = Role.ASSISTANT,
                content = "Cześć! Jestem Twoim kulinarnym asystentem. Podaj składniki z lodówki, a zaproponuję gotowe danie.",
                timestamp = 0L
            )
        )
    }

    fun sendMessage(text: String) {
        if (text.isBlank()) return

        messages.add(
            ChatMessage(
                id = "sg-${Random.nextInt()}",
                role = Role.USER,
                content = text,
                timestamp = 0L
            )
        )

        scope.launch {
            isBotTyping = true
            delay(1200.milliseconds)
            isBotTyping = false
            messages.add(
                ChatMessage(
                    id = "sg-${Random.nextInt()}",
                    role = Role.ASSISTANT,
                    content = "Proponuję pełnoziarniste penne z grillowanym kurczakiem i szpinakiem w sosie jogurtowym.",
                    timestamp = 0L
                )
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ChatBackground()


        Scaffold(
            topBar = {
                ChatHeader()
            },
            containerColor = Color.Transparent
        ) { paddingValues ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = paddingValues.calculateTopPadding())
            ) {

                CustomMessageList(
                    messages = messages,
                    isBotTyping = isBotTyping,
                    modifier = Modifier
                        .weight(1f)
                )

                CustomChipSuggestions(
                    onSuggestionClick = { suggestion ->
                        placeholderChange = suggestion
                    },
                    modifier = Modifier.padding(top = 15.dp, bottom = 8.dp),
                )

                ChatInputBar(
                    value = inputBarValue,
                    onValueChange = { inputBarValue = it},
                    onSend = {
                        sendMessage(inputBarValue)
                        inputBarValue = ""
                    },
                    placeholder = placeholderChange
                )
            }
        }
    }
}