package com.ajaz.productservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ExceptionDto {
    private HttpStatus statusCode;
    private String message;

    public ExceptionDto(HttpStatus statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }


}
