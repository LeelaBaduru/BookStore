package com.learningspring.bookStore.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

@RestController
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

/*    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        logger.info("name from login controller:" + principal.getAttribute("name"));
        return Collections.singletonMap("name", principal.getAttribute("name"));
    } */

    @GetMapping("/user")
    public Map<String, Object> user(Authentication principal) {
        logger.info("name from login controller:" +  principal.getName());
        return Collections.singletonMap("name", principal.getName());
    }


    @GetMapping("/error")
    public String error(HttpServletRequest request) {
        String message = (String) request.getSession().getAttribute("error.message");
        request.getSession().removeAttribute("error.message");
        return message;
    }

    @GetMapping("/admin")
    public String admin() {
        return "Hello Admin, Welcome!";
    }

}