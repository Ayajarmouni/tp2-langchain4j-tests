# Utilisation de l'Intelligence Artificielle (IA)

Ce fichier documente quelques exemples d'utilisation de l'assistant IA lors du développement et du test des fonctionnalités de LangChain4j.

---

### Exemple 1 : Création et configuration d'un outil personnalisé (`@Tool`)

* **Prompt / Demande :**
  > "Comment créer un outil météo personnalisé avec LangChain4j en utilisant l'API Open-Meteo pour qu'un agent puisse vérifier s'il va pleuvoir ?"

* **Réponse / Résultat obtenu :**
  > L'IA a fourni la structure de la classe `MeteoTool.java` utilisant les annotations `@Tool` et `@P` de LangChain4j, ainsi que l'utilisation de `HttpClient` et `Jackson` pour parser la réponse JSON de l'API Open-Meteo.

---

### Exemple 2 : Résolution d'erreur de configuration RAG

* **Prompt / Demande :**
  > "J'ai une erreur lors de l'intégration du lecteur de document PDF pour le RAG dans LangChain4j, comment charger correctement un fichier PDF local ?"

* **Réponse / Résultat obtenu :**
  > L'IA a conseillé d'utiliser le `FileSystemDocumentLoader` pour charger le fichier `cours_ml.pdf` depuis la racine du projet, puis d'utiliser `PdfDocumentParser` pour extraire et segmenter le texte.