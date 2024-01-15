package com.example.demo.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.AuthRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) {
        

        //  URL for authentication
        String apiUrl = "https://qa.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp";

        // Creating a RestTemplate for making HTTP requests
        RestTemplate restTemplate = new RestTemplate();

        // Creating the request body with login credentials login_id and password
        String requestBody = "{" +
                "\"login_id\":\"" + authRequest.getLogin_id() + "\"," +
                "\"password\":\"" + authRequest.getPassword() + "\"" +
                "}";

        // Making the HTTP POST request to the authentication API
        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, requestBody, String.class);

        // Log the response body and status code.Created for checking error as it was throwing some.
        System.out.println("Response Body: " + response.getBody());
        System.out.println("Status Code: " + response.getStatusCodeValue());

        // Extracting the access token from the response body
        String responseBody = response.getBody();
        String accessToken = extractAccessToken(responseBody);

        // Returning the token in the response
        return ResponseEntity.ok(accessToken);
    }

    // helpe method to extract the access token from the JSON response
    private String extractAccessToken(String responseBody) {
        try {
            // Parse the JSON response and extract the access token
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);
            return jsonNode.get("access_token").asText();
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception if it have any
            return null;
        }
    }

}