package com.jtm.notesapp.commons.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private CustomUserService customUserService;
    private PasswordEncoder passwordEncoder;

    public SecurityConfig(CustomUserService customUserService, PasswordEncoder passwordEncoder) {
        this.customUserService = customUserService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable(); //0:15:00 (3)

        http
                .authorizeRequests()
                .antMatchers("/login**")
                .permitAll() //dostępny dla wszystkich
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/signin")
                .usernameParameter("username")
                .passwordParameter("password")

//                .defaultSuccessUrl() //(3) 0:23:00
                .successHandler((req, res, auth) -> {
                    System.out.println("==================SUCCESS HANDLER ====================");
                    for (GrantedAuthority authority : auth.getAuthorities()) {
                        System.out.println(authority.getAuthority());
                    }
                    System.out.println(auth.getName());
                    res.sendRedirect("/");
                })

                .failureHandler((req, res, exp) -> {//exp - exception
                    System.out.println("==================FAILURE HANDLER ====================");
                    res.sendRedirect("/login"); //todo ?error=true OR req.getSession().setAttribute("message", "You are logged out.");
                })
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")  //maps endpoint in Thymeleaf to logging out built-in Spring
                .logoutSuccessHandler((req, res, auth) -> {
                    res.sendRedirect("/login");
                })
                .permitAll()
                .and()
                .csrf().disable();


                http.headers().frameOptions().disable(); //for h2 database
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService((customUserService))
                .passwordEncoder(passwordEncoder);
    }





}
