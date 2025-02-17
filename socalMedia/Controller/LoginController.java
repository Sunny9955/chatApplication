package com.socalMedia.socalMedia.Controller;

import com.socalMedia.socalMedia.Model.JwtRequest;
import com.socalMedia.socalMedia.Model.JwtResponse;
import com.socalMedia.socalMedia.Model.User;
import com.socalMedia.socalMedia.Security.JwtHelper;
import com.socalMedia.socalMedia.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService login_service;
    @Autowired
    private UserDetailsService customUserDetailsServiceLogin;
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private AuthenticationManager authenticationManager;
//@CrossOrigin(origins = ("https://sunny9955.github.io") )
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("/login") // Ensure this is POST
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        this.doAuthenticate(request.getEmail().trim(), request.getPassword().trim());
        UserDetails userDetails = customUserDetailsServiceLogin.loadUserByUsername(request.getEmail().trim());
        String token = this.jwtHelper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .userName(userDetails.getUsername())
                .userEmail(((User) userDetails).getUserEmail())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            logger.info("Trying to authenticate user: " + email + " with password: " + password);
            UserDetails userDetails = customUserDetailsServiceLogin.loadUserByUsername(email);
            if (!password.equals(userDetails.getPassword())) {
                throw new BadCredentialsException("Invalid Username or Password!!");
            }
        } catch (BadCredentialsException e) {
            logger.error("Bad credentials for user: " + email);
            throw new BadCredentialsException("Invalid Username or Password!!");
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credentials Invalid!!");
    }
}
