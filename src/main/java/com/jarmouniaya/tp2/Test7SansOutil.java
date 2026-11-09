package com.jarmouniaya.tp2;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.google.genai.GoogleGenAiChatModel;

public class Test7SansOutil {
    public static void main(String[] args) {
        ChatModel model = GoogleGenAiChatModel.builder()
                .apiKey(System.getenv("GEMINI_KEY"))
                .modelName("gemini-3.5-flash-lite")   // le même nom de modèle que dans les autres tests
                .temperature(0.7)
                .build();

        String question = "Je dois partir dans 2 jours à Paris ; j'y resterai 3 jours. "
                + "Est-ce que je dois mettre un parapluie dans mes valises ?";
        System.out.println("Réponse : " + model.chat(question));
    }
}