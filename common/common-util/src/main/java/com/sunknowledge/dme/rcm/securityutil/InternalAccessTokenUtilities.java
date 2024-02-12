package com.sunknowledge.dme.rcm.securityutil;

import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import java.util.Properties;

public interface InternalAccessTokenUtilities {
    /*static String getAccessTokenByParam(String url, String grant_type, String client_id,
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
    }*/

    static String getAccessToken() {
        String accessToken = "{\"access_token\": \"NOT_AVAILABLE\"}";
        try {
            CommonUtilities commonUtilitiesObj = new CommonUtilities();
            Properties propData = commonUtilitiesObj.readPropertiesFile("/security-property-files/InternalAccessTokenParameters.properties");

            String url = propData.getProperty("url");
            String grant_type = propData.getProperty("grant_type");
            String client_id = propData.getProperty("client_id");
            String client_secret = propData.getProperty("client_secret");
            String scope = propData.getProperty("scope");
            String realms = propData.getProperty("realms");

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
            if (response.getStatusCode() == HttpStatus.OK) {
                accessToken = (String) response.getBody();
            }
        } catch (HttpClientErrorException e) {
            // Client-side errors (4xx)
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "API/Page Not Found");
            } else if (e.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error: Error in API Service");
            } else {
                throw new RuntimeException(e);
            }
        } catch (HttpServerErrorException e) {
            // Server-side errors (5xx)
            if (e.getCause() instanceof ConnectTimeoutException || e.getStatusCode().equals(HttpStatus.REQUEST_TIMEOUT)) {
                throw new ResponseStatusException(HttpStatus.REQUEST_TIMEOUT, "Connection/Request Timeout Exception");
            } else if (e.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error: Error in API Service");
            } else if (e.getStatusCode().equals(HttpStatus.SERVICE_UNAVAILABLE)) {
                throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "API Service Unavailable");
            } else {
                throw new RuntimeException(e);
            }

        } catch (ResourceAccessException e) {
            // Timeouts, connection refused, etc.
            throw new RuntimeException(e);
        } catch (Exception e) {
            // Any other exception
            throw new RuntimeException(e);
        }
        return accessToken;
    }

    static String getAccessTokenForKeycloak() {
        String accessToken = "{\"access_token\": \"NOT_AVAILABLE\"}";
        try {
            CommonUtilities commonUtilitiesObj = new CommonUtilities();
            Properties propData = commonUtilitiesObj.readPropertiesFile("/security-property-files/InternalAccessTokenParameters.properties");

            String url = propData.getProperty("url");
            String grant_type = propData.getProperty("grant_type");
            String client_id = propData.getProperty("client_id_keycloak");
            String client_secret = propData.getProperty("client_secret_keycloak");
            String scope = propData.getProperty("scope");
            String realms = propData.getProperty("realms");

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
            if (response.getStatusCode() == HttpStatus.OK) {
                accessToken = (String) response.getBody();
            }
        } catch (HttpClientErrorException e) {
            // Client-side errors (4xx)
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "API/Page Not Found");
            } else if (e.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error: Error in API Service");
            } else {
                throw new RuntimeException(e);
            }
        } catch (HttpServerErrorException e) {
            // Server-side errors (5xx)
            if (e.getCause() instanceof ConnectTimeoutException || e.getStatusCode().equals(HttpStatus.REQUEST_TIMEOUT)) {
                throw new ResponseStatusException(HttpStatus.REQUEST_TIMEOUT, "Connection/Request Timeout Exception");
            } else if (e.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error: Error in API Service");
            } else if (e.getStatusCode().equals(HttpStatus.SERVICE_UNAVAILABLE)) {
                throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "API Service Unavailable");
            } else {
                throw new RuntimeException(e);
            }

        } catch (ResourceAccessException e) {
            // Timeouts, connection refused, etc.
            throw new RuntimeException(e);
        } catch (Exception e) {
            // Any other exception
            throw new RuntimeException(e);
        }
        return accessToken;
    }
}
