package com.jarmouniaya.tp2;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.google.genai.GoogleGenAiChatModel;
import dev.langchain4j.service.AiServices;

import java.util.Scanner;

public class Test7Outils {

    public static void main(String[] args) {
        ChatModel model = GoogleGenAiChatModel.builder()
                .apiKey(System.getenv("GEMINI_KEY"))
                .modelName("gemini-3.5-flash-lite")   // le même nom de modèle que dans les autres tests
                .temperature(0.7)
                .build();

        AssistantMeteo assistant = AiServices.builder(AssistantMeteo.class)
                .chatModel(model)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(20)) // les outils génèrent des messages
                .tools(new MeteoTool())                                   // ajout de l'outil
                .build();

        conversationAvec(assistant);
    }

    private static void conversationAvec(AssistantMeteo assistant) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("==================================================");
                System.out.println("Posez votre question : ");
                String question = scanner.nextLine();
                if (question.isBlank()) {
                    continue;
                }
                System.out.println("==================================================");
                if ("fin".equalsIgnoreCase(question)) {
                    break;
                }
                System.out.println("Assistant : " + assistant.chat(question));
                System.out.println("==================================================");
            }
        }
    }
}