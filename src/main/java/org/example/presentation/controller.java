package org.example.presentation;

import okhttp3.OkHttpClient;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.tomcat.util.http.parser.MediaType;
import org.example.service.Utils;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Optional;


@RestController
public class controller {


  @GetMapping("/hello")
  public String helloWorld() {
    return "Hello World App Orchestrator";
  }

  @GetMapping("/authentifierNotaire")
  public StringBuilder authentifierNotaire(@RequestParam String username, @RequestParam String mdp) throws IOException {
    String parametres = "8080/authentifierNotaire?username=" + username + "&mdp=" + mdp;
    return Utils.callApi(parametres, "GET");
  }

  @GetMapping("/afficherNotaires")
  public StringBuilder afficherNotaires() throws IOException {
    String parametres = "8080/afficherNotaires";
    return Utils.callApi(parametres, "GET");
  }

  @GetMapping("/rechercheNotaire")
  public StringBuilder rechercheNotaire(@RequestParam String recherche) throws IOException {
    String parametres = "8080/rechercheNotaire?recherche=" + recherche;
    return Utils.callApi(parametres, "GET");
  }

  @GetMapping("/afficheracte")
  public StringBuilder afficheracte(@RequestParam int id) throws Exception {
    String parametres = "8181/afficheracte?id=" + id;
    return Utils.callApi(parametres, "GET");
  }

  @PostMapping("/archiveracte")
  public String archiveActe(@RequestBody String body) throws Exception {

    // Appel de l'API
    String response = Utils.postMessage("http://localhost:8181/archiveracte", body);

    return response;

  }
}
