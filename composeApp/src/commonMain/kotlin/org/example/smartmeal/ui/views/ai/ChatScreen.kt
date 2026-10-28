package org.example.smartmeal.ui.views.ai

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import org.example.smartmeal.model.chat.ChatPrompts
import org.example.smartmeal.ui.components.chat.CustomChipSuggestions
import org.example.smartmeal.ui.components.chat.CustomMessageList
import org.example.smartmeal.ui.views.ai.parts.ChatBackground
import org.example.smartmeal.ui.views.ai.parts.ChatHeader
import org.example.smartmeal.ui.views.ai.parts.ChatInputBar
import org.koin.compose.viewmodel.koinViewModel

class ChatScreen : Screen {
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

    val state by viewModel.uiState.collectAsState()

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
                    messages = state.messages,
                    isBotTyping = state.isBotTyping,
                    modifier = Modifier
                        .weight(1f)
                )

                if (state.showSuggestions) {

                    CustomChipSuggestions(
                        suggestions = ChatPrompts.suggestions,
                        onSuggestionClick = viewModel::onSuggestionsClick,
                        modifier = Modifier.padding(top = 15.dp, bottom = 8.dp),
                    )
                }

                ChatInputBar(
                    value = state.input,
                    onValueChange = viewModel::onInputChange,
                    onSend = viewModel::onSendClick
                )
            }
        }
    }
}