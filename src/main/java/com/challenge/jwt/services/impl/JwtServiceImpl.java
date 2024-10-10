package com.challenge.jwt.services.impl;

import com.challenge.jwt.services.JwtService;
import com.challenge.jwt.utils.Base64Decoder;
import com.challenge.jwt.utils.JwtValidator;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl implements JwtService {

    public Boolean validateJwtPayload(String jwtPayloadToken) {
        String decodedPayloadToken = new Base64Decoder().decodePayload(jwtPayloadToken);

        return JwtValidator.validateToken(decodedPayloadToken);
    }
}
