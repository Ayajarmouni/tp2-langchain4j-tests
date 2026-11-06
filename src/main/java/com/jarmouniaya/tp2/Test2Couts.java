package com.jarmouniaya.tp2;

import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.google.genai.GoogleGenAiChatModel;
import dev.langchain4j.model.output.TokenUsage;

public class Test2Couts {
    public static void main(String[] args) {
        ChatModel model = GoogleGenAiChatModel.builder()
                .apiKey(System.getenv("GEMINI_KEY"))
                .modelName("gemini-3.5-flash-lite")   // le même nom de modèle que dans Test1
                .temperature(0.7)
                .build();

        // Même question que dans Test1
        ChatResponse reponse = model.chat(UserMessage.from("Quelle est la capitale du Maroc ?"));

        System.out.println("Réponse : " + reponse.aiMessage().text());

        TokenUsage usage = reponse.tokenUsage();
        System.out.println("Tokens en entrée  : " + usage.inputTokenCount());
        System.out.println("Tokens en sortie  : " + usage.outputTokenCount());
        System.out.println("Tokens au total   : " + usage.totalTokenCount());
    }
}