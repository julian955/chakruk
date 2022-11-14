package com.semillero.crakruk.auth.controller;



import com.semillero.crakruk.auth.dto.*;
import com.semillero.crakruk.auth.service.CustomUserDetailsService;
import com.semillero.crakruk.auth.service.IUserService;
import com.semillero.crakruk.auth.service.JwtUtils;
import com.semillero.crakruk.model.Role;
import com.semillero.crakruk.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class UserAuthController {

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private RoleRepository roleRepository;


    @GetMapping("/me")
    public ResponseEntity<UserProfileDto> getProfile(HttpServletRequest request) {

        UserProfileDto dto = userService.getUserProfile(request);
        return ResponseEntity.ok().body(dto);
    }



    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto) {
        try {
            UserBasicDto user = userService.signup(userDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authRequest) throws Exception {

        return ResponseEntity.ok(userService.generateToken(authRequest));
    }

     @PostMapping("/test")
    public ResponseEntity<Void> test(@RequestHeader(value = "token") String token) throws Exception {

         System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
         System.out.println(token);
         System.out.println(userService.getUserIdFromToken(token));
         System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        return ResponseEntity.ok().build();
    }
}
