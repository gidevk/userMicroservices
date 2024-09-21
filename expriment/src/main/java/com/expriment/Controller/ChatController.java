package com.expriment.Controller;

import com.expriment.entity.vo.ChatGptRequest;
import com.expriment.utils.audit.LoggerClass;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import sun.rmi.runtime.Log;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Value("${chatgpt.api.endpoint}")
    private String chatGptApiEndpoint;

    @Value("${chatgpt.api.key}")
    private String chatGptApiKey;

//    private final RestTemplate restTemplate;
//
//    public ChatController(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }

    @PostMapping(value="/generate", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> generateResponse(@RequestBody ChatGptRequest requestBody) {
        LoggerClass.appLayerLogger.info("Inside chatGpt.");
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(chatGptApiKey);

        // Construct the request body as a JSON object
//        String requestBody = "{\"prompt\": \"" + requestBody + "\", \"max_tokens\": 100}";

        requestBody.setModel("gpt-3.5-turbo");
        HttpEntity<?> httpEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                chatGptApiEndpoint,
                HttpMethod.POST,
                httpEntity,
                String.class
        );

        LoggerClass.appLayerLogger.info("Response String is {}",responseEntity.getBody());
        return ResponseEntity.ok(responseEntity.getBody());
    }
}
