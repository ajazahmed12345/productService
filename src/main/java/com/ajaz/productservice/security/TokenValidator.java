package com.ajaz.productservice.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class TokenValidator {


    private RestTemplateBuilder restTemplateBuilder;

    private String authServiceValidateUrl = "http://localhost:8081/auth/validate";

    public TokenValidator(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }
    public JwsTokenObj validateToken(Long userId, String token){
        RestTemplate restTemplate = restTemplateBuilder.build();
//        ResponseEntity<JwsTokenObj> response = restTemplate.postForEntity(authServiceValidateUrl, JwsTokenObj.class, userId, token);

        ValidateRequestDto validateRequestDto = new ValidateRequestDto(
                userId,
                token
        );


        ResponseEntity<JwsTokenObj> response = restTemplate.postForEntity(authServiceValidateUrl, validateRequestDto, JwsTokenObj.class);

        System.out.println(response.getBody().getUserId());



        return response.getBody();
    }
}
