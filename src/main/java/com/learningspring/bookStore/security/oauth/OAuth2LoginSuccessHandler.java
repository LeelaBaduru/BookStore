package com.learningspring.bookStore.security.oauth;

import com.learningspring.bookStore.controller.LoginController;
import com.learningspring.bookStore.entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private static Logger logger = LoggerFactory.getLogger(OAuth2LoginSuccessHandler.class);

    @Autowired
    private  UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();

        String email = oAuth2User.getEmail();
        logger.info("Customer's email: " + email);


        String name = oAuth2User.getName();
        logger.info("Customer's name: " + name);

        String username;

        if ( email != null ) {

           username = email;

        } else username = name;

        userService.processOAuthPostLogin(username);


        super.onAuthenticationSuccess(request, response, authentication);

    }
}
