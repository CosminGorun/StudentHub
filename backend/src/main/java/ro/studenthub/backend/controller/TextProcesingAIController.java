package ro.studenthub.backend.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/AI")
public class TextProcesingAIController {
    @Value(value = "${api_key_openrouter}")
    private String apiKey;
    @Value(value = "${model_openrouter}")
    private String model;
    @Value(value = "${URL_openrouter}")
    private String url;

    private final String systemPrompt = """ 
            "The products in the list must be in English."
            "The first word of the user's message indicates the language of the message. "
            "You must return a list of ingredients in English from the user's prompt, in the following format: {"
            "validList: (true or false, indicating whether the prompt describes/contains a list of ingredients),"
            "errorText: (if the list is not valid, otherwise null),"
            "ingredients: [list of ingredients with name, quantity, and unit; if quantity is not specified, set quantity to 0 and unit to null]}
        """;
    @GetMapping("/getList")
    public ResponseEntity<String> getTheListOfIngredients(@RequestParam("prompt") String userMessage) throws IOException, InterruptedException {
        JSONObject payload = new JSONObject();
        payload.put("model", model);
        payload.put("temperature", 0);

        payload.put("messages", new org.json.JSONArray()
                .put(new JSONObject().put("role", "system").put("content", systemPrompt))
                .put(new JSONObject().put("role", "user").put("content", userMessage))
        );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://openrouter.ai/api/v1/chat/completions"))
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(payload.toString()))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


        String body = response.body();
        System.out.println(body);

        JSONObject responseJson = new JSONObject(body);
        try {
            String content = responseJson
                    .getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content");

            String content2 = content.replace("```json", "")
                    .replace("```", "")
                    .trim();
            System.out.println(ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .body(content2));
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .body(content2);
        }catch (Exception e){
            String resp="{\n" +
                    "  \"validList\": false,\n" +
                    "  \"errorText\": \"The used API don't work right now!\" ,\n" +
                    "  \"ingredients\": []\n" +
                    "}";
            System.out.println(ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .body(resp));
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .body(resp);
        }
    }
}
