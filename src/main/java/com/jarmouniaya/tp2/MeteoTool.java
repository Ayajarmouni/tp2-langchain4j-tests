package com.jarmouniaya.tp2;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MeteoTool {

    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // Probabilité de pluie (en %) à partir de laquelle on considère qu'il faut un parapluie
    private final double seuilPluie = 30;

    @Tool("Indique s'il faut prévoir un parapluie pour un séjour. Donne true si de la pluie est prévue "
            + "pendant le séjour, à partir de la latitude et de la longitude du lieu.")
    public boolean isPluiePrevue(
            @P("Latitude du lieu") double latitude,
            @P("Longitude du lieu") double longitude,
            @P("Nombre de jours avant le départ (0 si le séjour commence aujourd'hui)") int joursAvantDepart,
            @P("Durée du séjour en jours") int dureeSejour) {
        try {
            int nbJours = joursAvantDepart + dureeSejour;
            String url = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude
                    + "&longitude=" + longitude
                    + "&daily=precipitation_probability_max&forecast_days=" + nbJours;

            JsonNode probas = lire(url).path("daily").path("precipitation_probability_max");

            for (int i = joursAvantDepart; i < nbJours && i < probas.size(); i++) {
                if (probas.get(i).asDouble() >= seuilPluie) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException("Impossible d'obtenir la météo : " + e.getMessage(), e);
        }
    }

    private JsonNode lire(String url) throws Exception {
        HttpRequest requete = HttpRequest.newBuilder(URI.create(url)).GET().build();
        HttpResponse<String> reponse = CLIENT.send(requete, HttpResponse.BodyHandlers.ofString());
        return MAPPER.readTree(reponse.body());
    }
}