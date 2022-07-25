package com.learningspring.bookStore.security.oauth;

import com.learningspring.bookStore.entity.AuthenticationProvider;
import com.learningspring.bookStore.entity.Customer;
import com.learningspring.bookStore.entity.Role;
import com.learningspring.bookStore.repository.CustomerRepository;
import com.learningspring.bookStore.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserService userService;

    private static Logger logger = LoggerFactory.getLogger(CustomOAuth2UserService.class);

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        logger.info("Inside CustomOAuth2UserService class");

        OAuth2User user = super.loadUser(userRequest);
        String username;

        logger.info("User name:" + user.getAttribute("name"));
        logger.info("User email:" + user.getAttribute("email"));

        if (user.getAttribute("email") != null) {
            username = user.getAttribute("email");
        } else username = user.getAttribute("name");

        //    Customer customer = userService.processOAuthPostLogin(username);
        Customer existUser = customerRepository.getUserByUsername(username);

        if (existUser == null) {
            logger.info("Inside new user creation");
            Customer newUser = new Customer();
            newUser.setUsername(username);
            newUser.setEnabled(true);

            if (user.getAttribute("email") == null) {
                //   if (request.getRequestURI().contains("github")) {
                newUser.setProvider(AuthenticationProvider.GITHUB);
            } else newUser.setProvider(AuthenticationProvider.GOOGLE);

            Role roleUser = roleRepository.findByName("ROLE_USER");
            newUser.addRole(roleUser);

            logger.info("Password of the customer:" + newUser.getPassword());
            logger.info("Username of tne customer:" + newUser.getUsername());
            logger.info("Roles assigned to the customer:" + newUser.getRoles());

            customerRepository.save(newUser);
            existUser = newUser;

        }

        logger.info("Customer:" + existUser);
        logger.info("user:" + user.toString());

        // return new CustomOAuth2User(user);

        return new CustomOAuth2User(existUser);

    }
}