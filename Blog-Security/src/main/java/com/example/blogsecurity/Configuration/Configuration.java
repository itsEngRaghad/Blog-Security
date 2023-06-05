package com.example.blogsecurity.Configuration;

import com.example.blogsecurity.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@org.springframework.context.annotation.Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class Configuration {

    //initialize
    private final MyUserDetailsService myUserDetailsService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());//tp hash password
        return daoAuthenticationProvider;
    }


    //security filter chain-to authorize
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(authenticationProvider())//authorization
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST,"/api/v1/auth/register").permitAll()//determine path or ** for all auth//register of method of post type
                .requestMatchers("/api/v1/auth/admin").hasAuthority("ADMIN") //admin only one has authority
                .requestMatchers("/api/v1/auth/user").hasAuthority("USER")  //user only one has authority
                .requestMatchers("/api/v1/auth/login").hasAuthority("USER") //user can log login
                .anyRequest().authenticated()//any thing else , other request as long as he is signed in or ask 4 password
                .and()
                .logout().logoutUrl("/api/v1/auth/logout")
                .deleteCookies("JSESSIONID") //delete cookies is logout, we give the cookies name to delete
                .invalidateHttpSession(true) //when delete session , invalid session
                .and()
                .httpBasic();
        return http.build(); //build all chain
    }


}
