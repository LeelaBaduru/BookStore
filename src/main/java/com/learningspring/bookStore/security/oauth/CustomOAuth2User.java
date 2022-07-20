package com.learningspring.bookStore.security.oauth;

import com.learningspring.bookStore.entity.Customer;
import com.learningspring.bookStore.entity.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.*;

public class CustomOAuth2User implements OAuth2User{

    private static Logger logger = LoggerFactory.getLogger(CustomOAuth2User.class);


    private OAuth2User oauth2User;

    public CustomOAuth2User(OAuth2User oauth2User) {
        logger.info("Inside CustomOAuth2User class");
        this.oauth2User = oauth2User;
    }

    @Override
    public Map<String, Object> getAttributes() {

        return oauth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return oauth2User.getAuthorities();

    }

    @Override
    public String getName() {

        return oauth2User.getAttribute("name");
    }


    public String getEmail() {

        return oauth2User.<String>getAttribute("email");
    }

}
