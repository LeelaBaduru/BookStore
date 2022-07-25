package com.learningspring.bookStore.security.oauth;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private static Logger logger = LoggerFactory.getLogger(OAuth2LoginSuccessHandler.class);


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        logger.info("Inside Success Handler");
        CustomOAuth2User loggedInUser = (CustomOAuth2User) authentication.getPrincipal();


        //   logger.info("oAuth2User:" + oAuth2User.getAttributes());

        logger.info("loggedInUser Email:" + loggedInUser.getEmail());
        logger.info("loggedInUser name:" + loggedInUser.getName());
        logger.info("authentication.getName():" + authentication.getName());

        super.onAuthenticationSuccess(request, response, authentication);
    }
}