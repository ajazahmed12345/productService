package com.ajaz.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestTitlesDto {
    private List<String> uuids;
}
