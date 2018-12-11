package pl.edu.wat.wcy.invoice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.wcy.invoice.dto.UserLoginDTO;
import pl.edu.wat.wcy.invoice.dto.UserSignUpDTO;
import pl.edu.wat.wcy.invoice.response.JwtAuthenticationResponse;
import pl.edu.wat.wcy.invoice.response.SingleResponse;
import pl.edu.wat.wcy.invoice.service.AuthService;

import javax.validation.Valid;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    final AuthService authService;

    @PostMapping("/signin")
    public JwtAuthenticationResponse authenticateUser(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        return authService.authenticateUser(userLoginDTO);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserSignUpDTO userSignUpDTO) {
        return authService.registerUser(userSignUpDTO);
    }
}