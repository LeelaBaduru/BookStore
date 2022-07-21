package com.learningspring.bookStore.config;

import com.learningspring.bookStore.security.oauth.CustomOAuth2UserService;
import com.learningspring.bookStore.security.oauth.OAuth2LoginSuccessHandler;
import com.learningspring.bookStore.security.oauth.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
@EnableWebSecurity

public class WebSecurityConfig {

    private static Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
    private static final String[] WHITE_LIST_URLS = {"/", "/error", "/webjars/**", "/authors/**"
    };


    @Autowired
    private CustomOAuth2UserService oauthUserService;

    @Autowired
    private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;


  /*  @Bean
    public WebClient rest(ClientRegistrationRepository clients, OAuth2AuthorizedClientRepository authz) {
        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 =
                new ServletOAuth2AuthorizedClientExchangeFilterFunction(clients, authz);
        return WebClient.builder()
                .filter(oauth2).build();
    }
    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2UserService(WebClient rest) {
        logger.info("Inside oauth2UserService");
        DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
        return request -> {
            OAuth2User user = delegate.loadUser(request);
            logger.info("user:" + user);
            logger.info("RegistrationId:" + request.getClientRegistration().getRegistrationId());
            if (!"github".equals(request.getClientRegistration().getRegistrationId())) {
                return user;
            }

            OAuth2AuthorizedClient client = new OAuth2AuthorizedClient
                    (request.getClientRegistration(), user.getName(), request.getAccessToken());
            String url = user.getAttribute("organizations_url");
            logger.info("url" + url);
            List<Map<String, Object>> orgs = rest
                    .get().uri(url)
                    .attributes(oauth2AuthorizedClient(client))
                    .retrieve()
                    .bodyToMono(List.class)
                    .block();
            logger.info("orgs" + orgs);

            if (orgs.stream().anyMatch(org -> "spring-projects".equals(org.get("login")))) {

                logger.info("Inside condition equals" + orgs);
                return user;
            }
            logger.info("Inside else condition");
            throw new OAuth2AuthenticationException(new OAuth2Error("invalid_token", "Not in Spring Team", ""));

        };
    }  */

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

     //   SimpleUrlAuthenticationFailureHandler handler = new SimpleUrlAuthenticationFailureHandler();

        http
                .cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(WHITE_LIST_URLS).permitAll()
               // .antMatchers("/admin/admin.html").hasRole("ADMIN")
                 .antMatchers("/hello").hasAuthority("USER")
                .and()
                .exceptionHandling(e -> e.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .logout(l -> l.logoutSuccessUrl("/").permitAll())
                /*    .oauth2Login(o -> o
                     .failureHandler((request, response, exception) -> {
                         request.getSession().setAttribute("error.message", exception.getMessage());
                         handler.onAuthenticationFailure(request, response, exception);
                         logger.info("exception.getMessage() " + exception.getMessage());
                         logger.info("handler exception: " + exception);

                     }));  */

                .oauth2Login()
                .loginPage("/login")
                .userInfoEndpoint()
              //  .userService(oauthUserService)
                .and()
                .successHandler(oAuth2LoginSuccessHandler);
        return http.build();

    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/h2-console/**");
    }
}