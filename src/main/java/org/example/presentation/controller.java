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


@RestController
public class controller {


  @GetMapping("/hello")
  public String helloWorld() {
    return "Hello World App Orchestrator";
  }

  /*
  @GetMapping("/rechercheNotaire")
  public String rechercheNotaire(@RequestParam String nom) {
      return Notaire.rechercherNotaire(Utils.getNotaires(), nom);
  }

  @GetMapping("/afficherNotaires")
  public String afficherNotaires() {
      return Notaire.afficherNotaires(Utils.getNotaires());
  }
*/
  @GetMapping("/authentifierNotaire")
  public StringBuilder authentifierNotaire(@RequestParam String username, @RequestParam String mdp) throws IOException {
    String parametres = "8181/authentifierNotaire?username=" + username + "&mdp=" + mdp;
    return Utils.callApi(parametres, "GET");
  }

  @GetMapping("/afficheracte")
  public StringBuilder afficheracte(@RequestParam int id) throws Exception {
    String parametres = "8080/afficheracte?id=" + id;
    return Utils.callApi(parametres, "GET");
  }

  @PostMapping("/archiveracte")
  public StringBuilder archiveActe(@RequestBody String anneeNaissance, String signature, int minuteNum, int minutaireNum, String designation) throws Exception {

    System.out.println("minute num " + minuteNum);
    JSONObject requestBody = new JSONObject();
    requestBody.put("anneeNaissance", anneeNaissance);
    requestBody.put("signature", signature);
    requestBody.put("minuteNum", 0);
    requestBody.put("minutaireNum", 0);
    requestBody.put("designation", designation);



    // Conversion du corps en chaîne JSON
    String jsonBody = requestBody.toString();

    // Appel de l'API
    StringBuilder response = Utils.sendPostRequest("http://localhost:8080/archiveracte", jsonBody);

    return response;

  }
}
