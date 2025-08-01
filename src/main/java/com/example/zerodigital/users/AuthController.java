package com.example.zerodigital.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shared.CustomizedResponse;

import java.util.Collections;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService  userService;
    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping(value = "/auth", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity login(@RequestBody User user) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
            );

            UserDetails userDetails = userService.loadUserByUsername(user.getEmail());
            String jwt = jwtUtil.generateToken(userDetails);

            var response = new CustomizedResponse("Login successful", Collections.singletonList(jwt));
            return new ResponseEntity(response,HttpStatus.OK);
        } catch (BadCredentialsException ex) {
            return new ResponseEntity<>(
                    new CustomizedResponse("Invalid email/password", null),
                    HttpStatus.UNAUTHORIZED
            );
        }
    }

}
