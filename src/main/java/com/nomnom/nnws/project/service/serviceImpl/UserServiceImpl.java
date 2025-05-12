package com.nomnom.nnws.project.service.serviceImpl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.nomnom.nnws.project.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

public String getCurrentUserKeycloakId (String authHeader) {
    if (authHeader != null && authHeader.startsWith("Bearer ")) {
        String token = authHeader.substring(7);
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getSubject(); // Das ist die Keycloak-ID (sub)
    }
    return null;
}
}
