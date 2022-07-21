package com.learningspring.bookStore.security.oauth;

import com.learningspring.bookStore.entity.Customer;
import com.learningspring.bookStore.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
     Customer customer = customerRepository.getUserByUsername(username);

        logger.info("Inside UserDetailsServiceImpl class");

       if (customer == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new MyUserDetails(customer);
    }
}