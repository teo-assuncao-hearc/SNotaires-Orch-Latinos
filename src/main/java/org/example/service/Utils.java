package org.example.service;

import ch.qos.logback.core.net.server.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.hop.pipeline.transforms.httppost.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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


    // Méthode pour envoyer une requête POST avec un body
    public static String postMessage(String apiUrl, String jsonBody) throws URISyntaxException, IOException, InterruptedException {
        var client = HttpClient.newHttpClient();
        var uri = new URI(apiUrl);

        var request = HttpRequest.newBuilder(uri).
        POST(HttpRequest.BodyPublishers.ofString(jsonBody)).header("Content-type", "application/json").
        build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        //assertEquals(201, response.statusCode());


        return response.body();
    }
}
