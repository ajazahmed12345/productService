package com.ajaz.productservice.security;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class JwsTokenObj {
    private Long userId;
    private String email;
    private List<Role> roles = new ArrayList<>();
    private Date createdAt;
    private Date expiryAt;

}
