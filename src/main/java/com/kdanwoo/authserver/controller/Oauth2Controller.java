package com.kdanwoo.authserver.controller;

import com.google.gson.Gson;
import com.kdanwoo.authserver.config.CommonConfiguration;
import com.kdanwoo.authserver.model.OAuthToken;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * id/password 기반 Oauth2 인증을 담당하는 서버
 * 다음 endpont가 자동 생성 된다.
 * /oauth/authorize
 * /oauth/token
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/oauth2")
public class Oauth2Controller {

    private final Gson gson;
    private final RestTemplate restTemplate;
    private final CommonConfiguration commonConfiguration;

//    @GetMapping(value = "/callback")
//    public OAuthToken callbackSocial(@RequestParam String code) {
//
//        String credentials = commonConfiguration.getClientId() + ":" + commonConfiguration.getClientSecret();
//        String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        headers.add("Authorization", "Basic " + encodedCredentials);
//
//
//        // 인증 서버로부터 Access Token 을 발급 받기 위해 필요한 파라미터 생성
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("code", code);
//        params.add("grant_type", "authorization_code"); // 권한 코드 승인 방식을 사용
//        params.add("redirect_uri", "http://localhost:8082/oauth2/callback");
//        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
//
//        // Access Token 발급 요청
//        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8082/oauth/token", request, String.class);
//        if (response.getStatusCode() == HttpStatus.OK) {
//            return gson.fromJson(response.getBody(), OAuthToken.class);
//        }
//        return null;
//    }

    @GetMapping(value = "/token/refresh")
    public OAuthToken refreshToken(@RequestParam String refreshToken) {

        String credentials = commonConfiguration.getClientId() + ":" + commonConfiguration.getClientSecret();
        String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Authorization", "Basic " + encodedCredentials);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("refresh_token", refreshToken);
        params.add("grant_type", "refresh_token");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8082/oauth/token", request, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return gson.fromJson(response.getBody(), OAuthToken.class);
        }
        return null;
    }


}