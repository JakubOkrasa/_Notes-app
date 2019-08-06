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

//        http.authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll();

        http
                .authorizeRequests()
                .antMatchers("/login**") // tu mogą być params jak success, error itp, dlatego **
                .permitAll() //dostępny dla wszystkich
                .and()
                .formLogin() //wł. form. logowania
                .loginPage("/login") //ustawienie strony logowania
                .loginProcessingUrl("/signin") //włącza obsługę formularza, dotyczy atrybutu w <form>
                .usernameParameter("username") //pod jakim atrybutem jest username np. w thymeleaf
                .passwordParameter("password")

//                .defaultSuccessUrl() //(3) 0:23:00
                .successHandler((req, res, auth) -> {
                    System.out.println("==================SUCCESS HANDLER ====================");
                    for (GrantedAuthority authority : auth.getAuthorities()) { //servlet dostarcza:
                        //req - request; res - response z servletu; auth
                        // auth.getAuthorities()
                        // - lista możliwych typów userów
                        System.out.println(authority.getAuthority());
                    }
                    System.out.println(auth.getName());
                    res.sendRedirect("/"); //res."co ma się stać jeśli success" tu: przejście na srone główną
                })

//                .failureUrl() //poniższa linia jest nadrzędna (3) 0:29:00
                .failureHandler((req, res, exp) -> {//exp - exception
                    System.out.println("==================FAILURE HANDLER ====================");
                    String errorMessage;
                    if(exp.getClass().isAssignableFrom(BadCredentialsException.class)) {
                        errorMessage = "Invalid username or password";
                    }
                    else {
                        errorMessage="unknown error: "+exp.getMessage();
                    }
                    req.getSession().setAttribute("message", errorMessage);
                    res.sendRedirect("/"); //added by me
                })
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")  //to nie jest strona www tylko mapowanie endpointu
                //nastąpi usunięcie cookies i wylogowanie, ale nie przejście na stronę
                .logoutSuccessHandler((req, res, auth) -> {
//                    req.getSession().setAttribute("message", "You are logged out");
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
                .passwordEncoder(passwordEncoder); //odhashowanie hasła
    }





}
