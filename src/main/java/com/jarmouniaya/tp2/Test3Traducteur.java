package com.jarmouniaya.tp2;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.google.genai.GoogleGenAiChatModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;

import java.util.Map;

public class Test3Traducteur {
    public static void main(String[] args) {
        ChatModel model = GoogleGenAiChatModel.builder()
                .apiKey(System.getenv("GEMINI_KEY"))
                .modelName("gemini-3.5-flash-lite")   // le même nom de modèle que dans les autres tests
                .temperature(0.7)
                .build();

        // --- Version 1 : un seul paramètre (le texte à traduire) ---
        PromptTemplate template1 = PromptTemplate.from(
                "Traduis le texte suivant en anglais : {{texte}}");
        Prompt prompt1 = template1.apply(Map.of("texte", "Bonjour, comment allez-vous ?"));
        System.out.println("Prompt 1 : " + prompt1.text());
        System.out.println("Réponse 1 : " + model.chat(prompt1.text()));

        // --- Version 2 : deux paramètres (la langue et le texte) ---
        PromptTemplate template2 = PromptTemplate.from(
                "Traduis le texte suivant en {{langue}} : {{texte}}");
        Prompt prompt2 = template2.apply(Map.of(
                "langue", "espagnol",
                "texte", "Bonjour, comment allez-vous ?"));
        System.out.println("Prompt 2 : " + prompt2.text());
        System.out.println("Réponse 2 : " + model.chat(prompt2.text()));
    }
}