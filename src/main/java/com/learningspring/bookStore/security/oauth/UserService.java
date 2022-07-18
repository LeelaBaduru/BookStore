package com.learningspring.bookStore.security.oauth;

import com.learningspring.bookStore.entity.AuthenticationProvider;
import com.learningspring.bookStore.entity.Customer;
import com.learningspring.bookStore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Provider;

// class to store the user details into database.
@Service
public class UserService {

    @Autowired
    CustomerRepository customerRepository;

    public void processOAuthPostLogin(String username) {
        Customer existUser = customerRepository.getUserByUsername(username);

        if (existUser == null) {
            Customer newUser = new Customer();
            newUser.setUsername(username);
            newUser.setProvider(AuthenticationProvider.GOOGLE);
            newUser.setEnabled(true);
            customerRepository.save(newUser);
        }

    }
}