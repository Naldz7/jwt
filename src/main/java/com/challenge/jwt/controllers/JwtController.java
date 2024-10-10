package com.challenge.jwt.controllers;

import com.challenge.jwt.services.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {

    @Autowired
    private JwtService jwtService;

    @Operation(summary = "Validar JWT",
            description = "Endpoint que valida um token JWT.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Boolean.class)) }),
            @ApiResponse(responseCode = "400", description = "Erro no processamento da requisição",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.",
                    content = @Content)
    })
    @GetMapping("/jwt/validate")
    public ResponseEntity<Boolean> validateJwt(@RequestHeader("jwt") String jwt) {
        try {
            if (jwt != null) {
                Boolean isValid = this.jwtService.validateJwtPayload(jwt);
                return isValid ? ResponseEntity.ok(isValid) : ResponseEntity.badRequest().body(isValid);
            }
            return ResponseEntity.badRequest().body(false);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(false);
        }
    }
}