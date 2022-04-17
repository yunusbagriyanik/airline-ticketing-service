package com.airlinesticketingbackend.auth.controller;

import com.airlinesticketingbackend.dto.auth.AuthenticationRequest;
import com.airlinesticketingbackend.dto.common.GenericResult;
import com.airlinesticketingbackend.dto.user.CreateUserRequest;
import com.airlinesticketingbackend.dto.user.User;
import com.airlinesticketingbackend.auth.service.AuthenticationService;
import com.airlinesticketingbackend.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @Autowired
    public AuthController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<GenericResult<User>> authenticate(@RequestBody AuthenticationRequest request) throws AuthenticationException {
        GenericResult<User> response = this.authenticationService.authenticate(request);
        if (response != null && response.getProcessResult() != null) {

            if (HttpStatus.OK.value() == response.getProcessResult().getStatusCode()) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public ResponseEntity<GenericResult<User>> registerUser(@RequestBody CreateUserRequest request) {
        final GenericResult<User> response = this.userService.userRegister(request);
        return ResponseEntity.ok(response);
    }
}
