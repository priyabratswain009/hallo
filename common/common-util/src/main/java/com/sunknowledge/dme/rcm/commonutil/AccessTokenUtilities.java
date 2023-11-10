package com.sunknowledge.dme.rcm.commonutil;

import com.sunknowledge.dme.rcm.dto.TokenResponse;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.StandardCharsets;

public class AccessTokenUtilities {
    public static String getAccessToken(String url, String grant_type, String client_id,
                                        String client_secret, String scope, String realms) {
        // --- Creating RestTemplate -----
        RestTemplate restTemplate = new RestTemplateBuilder().build();

        // --- Build the URL for the Keycloak server with required parameters ---
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        String requestUrl = builder.buildAndExpand(realms).toUriString();

        //--- Create an HttpEntity object with the necessary headers ---
        MultiValueMap map = new LinkedMultiValueMap<>();
        map.add("grant_type", grant_type);
        map.add("client_id", client_id);
        map.add("client_secret", client_secret);
        map.add("scope", scope);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(client_id, client_secret); // base64 encoded client id and secret
        HttpEntity httpEntity = new HttpEntity<>(map, headers);

        //--- Send a POST request to the Keycloak server ---
        ResponseEntity response = restTemplate.exchange(requestUrl, HttpMethod.POST, httpEntity, String.class);


        //--- Retrieve the access token from the response body ---
        String accessToken = (String) response.getBody();
        return accessToken;
    }

    public static String getOtherwaytoFindAccessToken() {
        // Create a RestTemplate to describe the request
        RestTemplate restTemplate = new RestTemplate();
        // Specify the http headers that we want to attach to the request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", new AccessTokenUtilities().createAuthHeaderString(ApplicationConstants.username, ApplicationConstants.password));

        // Create a map of the key/value pairs that we want to supply in the body of the request
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("response_type", ApplicationConstants.responseType);
        map.add("client_id", ApplicationConstants.clientId);
        map.add("username", ApplicationConstants.username);
        map.add("password", ApplicationConstants.password);
        map.add("scope", ApplicationConstants.scope);
        map.add("grant_type", ApplicationConstants.grantType);

        // Create an HttpEntity object, wrapping the body and headers of the request
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        // Execute the request, as a POSt, and expecting a TokenResponse object in return
        ResponseEntity<TokenResponse> response =
            restTemplate.exchange(ApplicationConstants.url,
                HttpMethod.POST,
                entity,
                TokenResponse.class);

        TokenResponse token = response.getBody();
        System.out.println("==================>TOKEN====================>"+token.getAccessToken());
        return token.getAccessToken();
    }

    // Just a helper metod to create the basic auth header
    private String createAuthHeaderString(String username, String password) {
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.US_ASCII));
        String authHeader = "Basic " + new String(encodedAuth);
        return authHeader;
    }
}
