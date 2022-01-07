package com.example.cryptousersjwt.Controller;

import com.example.cryptousersjwt.Entity.Cryptos;
import com.example.cryptousersjwt.Entity.User;
import com.example.cryptousersjwt.Repository.CryptoRepository;
import com.example.cryptousersjwt.Repository.RoleRepository;
import com.example.cryptousersjwt.Repository.UserRepository;
import com.example.cryptousersjwt.Requests.LoginRequest;
import com.example.cryptousersjwt.Requests.SignUpRequest;
import com.example.cryptousersjwt.Responses.JWTResponse;
import com.example.cryptousersjwt.Responses.LogOutResponse;
import com.example.cryptousersjwt.Responses.signUpResponse;
import com.example.cryptousersjwt.Roles.ERoles;
import com.example.cryptousersjwt.Roles.Roles;
import com.example.cryptousersjwt.SecurityConfig.AuthToken;
import com.example.cryptousersjwt.SecurityConfig.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(
        origins = {"http://localhost:3001","http://localhost:3000","http://localhost:3002"},
        allowCredentials = "true",
        maxAge = 3600,
        allowedHeaders = "*",
        /*originPatterns = "http://localhost:8080",*/
        methods= {RequestMethod.GET,RequestMethod.POST,
                RequestMethod.DELETE, RequestMethod.PUT,
                RequestMethod.PATCH, RequestMethod.OPTIONS,
                RequestMethod.HEAD, RequestMethod.TRACE}
    )
public class UserController {
    @Autowired
    User user;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    CryptoRepository cryptoRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTUtils jwtUtils;


    @CrossOrigin
    @PostMapping("/signIn")
    public ResponseEntity<?> authenticateUser(HttpServletResponse response, @RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateToken(authentication);
        User user = (User) authentication.getPrincipal();
        /*List<String> roles = user.getAuthorities().stream()
                .map(role -> role.getAuthority())
                .collect(Collectors.toList());*/
        /*Cookie tokenCookie = new Cookie("accessToken",jwt);
        tokenCookie.setHttpOnly(true);
        tokenCookie.setSecure(true);
        tokenCookie.setPath("/");
        tokenCookie.setDomain("/");
        tokenCookie.setMaxAge(8460000);*/
        ResponseCookie resCookie = ResponseCookie.from("Set-Cookie", jwt)
                .httpOnly(true)
                .sameSite("None")
                .secure(true)
                .path("/")
                .maxAge(Math.toIntExact(84600))
                .build();
        /*response.addHeader("Set-Cookie", resCookie.toString());*/
        /*tokenCookie.setSecure(true);*/
        /*ResponseCookie responseCookie = ResponseCookie.from("Bearer",jwt).httpOnly(true)*//*.sameSite("None").secure(true)*//*.path("/").*//*domain(".app.localhost").*//*maxAge(8460000).build();*/
        /*response.addHeader("Set-Cookie", tokenCookie);*/
        JWTResponse responseBody = new JWTResponse(user.getId(),user.getUsername(),user.getEmail());
        responseBody.setMessage("logged in successfully");
        responseBody.setIsSuccess(true);
        return  ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.SET_COOKIE,resCookie.toString()).body(responseBody);
    }

    @CrossOrigin
    @PostMapping("/signUp")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {

        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));
        Set<String> strRoles = signUpRequest.getRole();
        Set<Roles> roles = new HashSet<>();

        if (strRoles == null) {
            Roles userRole = roleRepository.findByName(ERoles.ROLE_USER);
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "user":
                        Roles userRole = roleRepository.findByName(ERoles.ROLE_USER);
                        roles.add(userRole);
                    default:
                        System.out.println("can't find role");
                }

            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new signUpResponse(true,"User registered successfully!"));
    }

    @CrossOrigin
    @PostMapping("/addToList")
    public ResponseEntity<?> addToList(/* @RequestBody Cryptos crypto*//*@CookieValue(value="Bearer") String cookie, */@RequestHeader Map<String,String> headers,@RequestHeader(name="Set-Cookie") String token)
            /*(@RequestHeader(name="Set-Cookie") String token)*/ {
            /*String bearer = req.getHeaders().toString();
            System.out.println(bearer);*/
            headers.forEach((key,value) ->{
                System.out.println("Header Name: "+key+" Header Value: "+value);
            });
            System.out.println("Cookie Val"+token);


                /*System.out.println(token);*/
            /*(@RequestHeader Map<String,String> headers){
        headers.forEach((key,value) ->{
            System.out.println("Header Name: "+key+" Header Value: "+value);
        });*/

            /*String name = jwtUtils.getUsernameFromToken(cookie);
            System.out.println(name);
            crypto.setUsername(name);
            cryptoRepository.save(crypto);*/
            return ResponseEntity.ok().body("List updated successfully");

    }

    /*@PostMapping("/addToList")
    public ResponseEntity<?> checkCookie(@CookieValue(value="Bearer") String cookie,@RequestBody com.fasterxml.jackson.databind.JsonNode cryptos) {
        if(jwtUtils.validateJWTToken(cookie)){
            System.out.println(cryptos);
            *//*cryptoRepository.saveAll(cryptos);*//*
            return ResponseEntity.ok().body("Added successfully");
        }
        return ResponseEntity.badRequest().body("Could not add");
        *//*Cannot deserialize value of type `java.util.ArrayList<com.example.cryptousersjwt.Entity.Cryptos>` from Object value (token `JsonToken.START_OBJECT`)<EOL>*//*
    }*/

    @CrossOrigin
    @GetMapping("/logOut")
    public ResponseEntity<?> logOut(){
        ResponseCookie responseCookie = ResponseCookie.from("Bearer",null).build();
        LogOutResponse logOutResponse = new LogOutResponse(true,"Logged Out Successfully");
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,responseCookie.toString()).body(logOutResponse);
    }
    }
