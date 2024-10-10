package com.challenge.jwt.services;

public interface JwtService {

    public Boolean validateJwtPayload(String jwtPayloadToken);
}
