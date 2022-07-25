package com.learningspring.bookStore.security.oauth;

import com.learningspring.bookStore.entity.Customer;
import com.learningspring.bookStore.entity.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.*;


public class CustomOAuth2User implements OAuth2User {

    private static Logger logger = LoggerFactory.getLogger(CustomOAuth2User.class);

    private OAuth2User oauth2User;
    private Customer customer;


 /*   public CustomOAuth2User(OAuth2User oauth2User) {
        logger.info("Inside CustomOAuth2User class");
        this.oauth2User = oauth2User;
    }
  */

    public CustomOAuth2User(Customer customer) {
        logger.info("Inside CustomOAuth2User class");
        this.customer = customer;
    }


    @Override
    public Map<String, Object> getAttributes() {

        return (Map<String, Object>) oauth2User.getAttributes().get(customer.getUsername());

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        logger.info("Inside Custom OAuth2User authorities");
        // return oauth2User.getAuthorities();
        // return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));

        List<Role> roles = customer.getRoles();
        logger.info("roles:" + roles);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getName() {

        // return oauth2User.getAttribute("name");
        logger.info("Inside getName");
        return customer.getUsername();

    }

    public String getEmail() {

     //    return oauth2User.<String>getAttribute("email");

       return customer.getUsername();
    }

}