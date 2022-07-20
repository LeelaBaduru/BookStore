package com.learningspring.bookStore.security.oauth;

import com.learningspring.bookStore.entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


// This class loads the current logged-in user details and returns user object
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private static Logger logger = LoggerFactory.getLogger(CustomOAuth2UserService.class);
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        logger.info("Inside CustomOAuth2UserService class");
        OAuth2User user =  super.loadUser(userRequest);
        return new CustomOAuth2User(user);

    }
}