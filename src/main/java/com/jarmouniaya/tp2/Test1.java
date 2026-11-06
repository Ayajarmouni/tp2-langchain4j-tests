package com.jarmouniaya.tp2;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.google.genai.GoogleGenAiChatModel;

public class Test1 {
    public static void main(String[] args) {
        ChatModel model = GoogleGenAiChatModel.builder()
                .apiKey(System.getenv("GEMINI_KEY"))
                .modelName("gemini-3.5-flash-lite")
                .temperature(0.7)
                .build();

        System.out.println(model.chat("Bonjour, je m'appelle Aya Jarmouni."));
        System.out.println(model.chat("Comment je m'appelle ?"));
    }
}