package com.learningspring.bookStore.security.oauth;

import com.learningspring.bookStore.entity.AuthenticationProvider;
import com.learningspring.bookStore.entity.Customer;
import com.learningspring.bookStore.entity.Role;
import com.learningspring.bookStore.repository.CustomerRepository;
import com.learningspring.bookStore.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    PasswordEncoder passwordEncoder;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RoleRepository roleRepository;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {


        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();



        String email = oAuth2User.getEmail();
        String name = oAuth2User.getName();
        String authorities = String.valueOf(oAuth2User.getAuthorities());
        String username;

        logger.info("Customer's email: " + email);
        logger.info("Customer's name: " + name);
        logger.info(request.getRequestURI());
        logger.info(String.valueOf(request.getRequestURL()));
        logger.info("Authority:" + authorities);

        if (email != null) {
            username = email;
        } else username = name;

        //    userService.processOAuthPostLogin(username);


        Customer existUser = customerRepository.getUserByUsername(username);

        if (existUser == null) {
            logger.info("Inside new user creation");
            Customer newUser = new Customer();
            newUser.setUsername(username);
            newUser.setEnabled(true);

            if (request.getRequestURI().contains("github")) {
                newUser.setProvider(AuthenticationProvider.GITHUB);
            } else newUser.setProvider(AuthenticationProvider.GOOGLE);

            Role roleUser = roleRepository.findByName("ROLE_ADMIN");
            newUser.addRole(roleUser);

            logger.info("Authorities of the Customer:" + newUser.getAuthorities());
            logger.info("Password of the customer:" + newUser.getPassword());
            logger.info("Username of tne customer:" + newUser.getUsername());

            customerRepository.save(newUser);
        }

   //     logger.info("Authorities of the exist user in database:" + existUser.getAuthorities());
        super.onAuthenticationSuccess(request, response, authentication);
    }
}