package com.jarmouniaya.tp2;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.bgesmallenv15.BgeSmallEnV15EmbeddingModel;
import dev.langchain4j.model.google.genai.GoogleGenAiEmbeddingModel;
import dev.langchain4j.store.embedding.CosineSimilarity;

import java.time.Duration;

public class Test4Embeddings {

    public static void main(String[] args) {
        // Modèle intégré (local, gratuit, entraîné surtout sur de l'anglais)
        EmbeddingModel modeleIntegre = new BgeSmallEnV15EmbeddingModel();

        // Modèle externe de Google
        EmbeddingModel modeleGoogle = GoogleGenAiEmbeddingModel.builder()
                .apiKey(System.getenv("GEMINI_KEY"))
                .modelName("gemini-embedding-001")
                .taskType(GoogleGenAiEmbeddingModel.TaskTypeEnum.SEMANTIC_SIMILARITY)
                .timeout(Duration.ofSeconds(10))              // l'énoncé suggère de commencer à 100 ms
                .build();

        String[][] couples = {
                {"Le chat dort sur le canapé.", "Un chaton fait la sieste sur le sofa."},   // phrases similaires
                {"Il fait très chaud aujourd'hui.", "Il fait très froid aujourd'hui."},      // phrases opposées
                {"J'adore la pizza.", "La pizza est mon plat préféré."},                     // similaires
                {"Le train part à 8 heures.", "Les éléphants vivent en Afrique."},           // sans rapport
                {"Je programme en Java.", "Je développe des applications avec Java."},       // similaires
                {"La Terre tourne autour du Soleil.", "Ma voiture est en panne."}            // sans rapport
        };

        System.out.println("Intégré      | Google       | Couple");
        for (String[] couple : couples) {
            // Modèle intégré
            Embedding e1 = modeleIntegre.embed(couple[0]).content();
            Embedding e2 = modeleIntegre.embed(couple[1]).content();
            double simIntegre = CosineSimilarity.between(e1, e2);

            // Modèle Google
            Embedding g1 = modeleGoogle.embed(couple[0]).content();
            Embedding g2 = modeleGoogle.embed(couple[1]).content();
            double simGoogle = CosineSimilarity.between(g1, g2);

            System.out.printf("%.8f  |  %.8f  |  %s  <->  %s%n", simIntegre, simGoogle, couple[0], couple[1]);
        }
    }
}