package com.pezesha.controller;

import com.pezesha.model.User;
import com.pezesha.payload.ApiResponse;
import com.pezesha.payload.JwtResponse;
import com.pezesha.payload.LoginReq;
import com.pezesha.security.JwtTokenProvider;
import com.pezesha.service.UserService;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * @author : HAron Korir
 * {@code @mailto} : koryrh@gmail.com
 * {@code @created} : 4/4/23, Tuesday
 **/
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider tokenProvider;
    @Autowired
    private UserService userService;

    /**
     * Allow user to login
     * @param loginReq
     * @return ApiResponse
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(
            @NonNull
            @RequestBody LoginReq loginReq) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginReq.getUsername().trim(),
                            loginReq.getPassword().trim()
                    ));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            User user = userService.findByUsername(loginReq.getUsername().trim());

            String jwt = tokenProvider.generateToken(authentication);

            LOGGER.info(">>> Successfully user login. (By ==> "+user.getUsername()+") ");
            return ResponseEntity.ok(
                    new ApiResponse(
                            HttpStatus.OK.value(),
                            "Login Successfully",
                            new JwtResponse(user.getUsername(), jwt)
                    )
            );

        }catch ( BadCredentialsException e ) {
            LOGGER.error(">>> Unable to login. (By ==> "+loginReq.getUsername()+")", e.getMessage());
            throw new BadCredentialsException("Wrong Username or Password", e);
        }

    }
}
