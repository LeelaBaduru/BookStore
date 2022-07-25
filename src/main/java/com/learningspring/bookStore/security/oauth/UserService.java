package com.learningspring.bookStore.security.oauth;

import com.learningspring.bookStore.entity.AuthenticationProvider;
import com.learningspring.bookStore.entity.Customer;
import com.learningspring.bookStore.entity.Role;
import com.learningspring.bookStore.repository.CustomerRepository;
import com.learningspring.bookStore.repository.RoleRepository;
import org.apache.catalina.connector.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserService {

    private static Logger logger = LoggerFactory.getLogger(CustomOAuth2UserService.class);
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    RoleRepository roleRepository;

     public Customer processOAuthPostLogin(String username) {

        Customer existUser = customerRepository.getUserByUsername(username);

        if (existUser == null) {
            logger.info("Inside new user creation");
            Customer newUser = new Customer();
            newUser.setUsername(username);
            newUser.setEnabled(true);

            //   if (request.getRequestURI().contains("github")) {
            newUser.setProvider(AuthenticationProvider.GITHUB);
            //    } else newUser.setProvider(AuthenticationProvider.GOOGLE);

            Role roleUser = roleRepository.findByName("ROLE_ADMIN");
            newUser.addRole(roleUser);

            logger.info("Password of the customer:" + newUser.getPassword());
            logger.info("Username of tne customer:" + newUser.getUsername());
            logger.info("roles of tne customer:" + newUser.getRoles());

            customerRepository.save(newUser);
            return newUser;

        } else return existUser;

    }
}
