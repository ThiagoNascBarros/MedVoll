package org.med.voll.application.controller;

import org.med.voll.application.communication.request.user.RequestUserLogin;
import org.med.voll.application.communication.response.ResponseUserLoginToken;
import org.med.voll.domain.user.User;
import org.med.voll.infra.security.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import lombok.AllArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity Login(@RequestBody @Valid RequestUserLogin request) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(request.login(), request.password());
        var authentication = manager.authenticate(authenticationToken);

        if (!authentication.isAuthenticated()) {
            return ResponseEntity.badRequest().body("Usuário ou senha incorretos");
        }

        var tokenJWT = tokenService.generateToken((User) Objects.requireNonNull(authentication.getPrincipal()));

        return ResponseEntity.ok(new ResponseUserLoginToken(tokenJWT));
    }

}
