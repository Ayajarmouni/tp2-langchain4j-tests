package com.jarmouniaya.tp2;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.google.genai.GoogleGenAiChatModel;

public class Test5SansRag {
    public static void main(String[] args) {
        ChatModel model = GoogleGenAiChatModel.builder()
                .apiKey(System.getenv("GEMINI_KEY"))
                .modelName("gemini-3.5-flash-lite")   // le même nom de modèle que dans les autres tests
                .temperature(0.7)
                .build();

        String question = "Comment s'appelle le chat de Pierre ?";
        System.out.println("Question : " + question);
        System.out.println("Réponse  : " + model.chat(question));
    }
}