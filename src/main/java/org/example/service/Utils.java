package org.example.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Utils {

    public static StringBuilder callApi(String params, String requestMethod) throws IOException {
        // URL de l'API à appeler
        String apiUrl = "http://localhost:" + params;

        // Création d'une connexion HTTP à l'API
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(requestMethod);

        if(requestMethod == "GET"){
            // Lecture de la réponse de l'API
            Scanner scanner = new Scanner(conn.getInputStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }
            scanner.close();

            return response;
        }
        return null;
    }

    // Méthode pour envoyer une requête POST avec un corps
    public static StringBuilder sendPostRequest(String apiUrl, String jsonBody) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Configuration de la connexion
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        // Écriture du corps de la requête
        DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
        outputStream.writeBytes(jsonBody);
        outputStream.flush();
        outputStream.close();

        // Récupération de la réponse
        int responseCode = connection.getResponseCode();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        // Affichage de la réponse
        System.out.println("Code de réponse : " + responseCode);
        System.out.println("Réponse : " + response.toString());

        return response;
    }
}
