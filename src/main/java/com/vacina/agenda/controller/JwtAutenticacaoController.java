package com.vacina.agenda.controller;

import com.vacina.agenda.configs.JwtTokenUtil;
import com.vacina.agenda.configs.UsuarioConfig;
import com.vacina.agenda.model.JwtResponse;
import com.vacina.agenda.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class JwtAutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UsuarioConfig usuarioConfig;

    @PostMapping
    public ResponseEntity<?> criarTokenAutenticacao(@RequestBody Usuario usuario) throws Exception {
        autenticar(usuario.getUsername(), usuario.getPassword());
        UserDetails userDetails = usuarioConfig
                .loadUserByUsername(usuario.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void autenticar(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
