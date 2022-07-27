package com.learningspring.bookStore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.security.Provider;
import java.util.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    private String password = "pass";
    //   private String role;
    private boolean enabled;

    private String Email;

    @Enumerated(EnumType.STRING)
    private AuthenticationProvider provider;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<>();

    public Long getId() {

        return id;
    }

    public AuthenticationProvider getProvider() {

        return provider;
    }

    public void setProvider(AuthenticationProvider provider) {

        this.provider = provider;
    }

    public List<Role> getRoles() {
        System.out.println("inside get role");
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

}