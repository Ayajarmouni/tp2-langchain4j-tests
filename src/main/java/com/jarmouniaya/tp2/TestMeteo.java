package com.jarmouniaya.tp2;

public class TestMeteo {
    public static void main(String[] args) {
        MeteoTool meteo = new MeteoTool();
        // Paris : séjour de 3 jours qui commence aujourd'hui
        System.out.println("Pluie prévue à Paris (aujourd'hui, 3 jours) : "
                + meteo.isPluiePrevue(48.85, 2.35, 0, 3));
        // Séjour de 3 jours qui commence demain
        System.out.println("Pluie prévue à Paris (demain, 3 jours) : "
                + meteo.isPluiePrevue(48.85, 2.35, 1, 3));
    }
}