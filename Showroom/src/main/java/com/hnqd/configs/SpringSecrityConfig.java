/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hnqd.configs;

import com.cloudinary.Cloudinary;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

/**
 *
 * @author DELL
 */
@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.hnqd.controllers",
    "com.hnqd.repositories",
    "com.hnqd.services",
    "com.hnqd.configs",})
@PropertySource("classpath:application.properties")
public class SpringSecrityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    @Autowired
    private Environment env;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    
    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("quocduy6114@gmail.com");
        mailSender.setPassword("sdtp ahlt xkzk ptyh");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.starttls.enable", "true");

        return mailSender;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/showrooms/update/**", "/api/v1/showrooms/create/**", 
                        "/api/v1/vehicles/create/**", "/api/v1/vehicles/delete/**", 
                        "/api/v1/vehicles/update/**", "/api/v1/vehicles/update/images/**")
                .hasAnyAuthority("ADMIN")
                .antMatchers("/api/v1/bookings/create", "/api/v1/bookings/customer", 
                        "/api/v1/bookings/customer/**")
                .hasAnyAuthority("CUSTOMER")
                .antMatchers("/api/v1/bookings/update/**", "/api/v1/bookings/staff")
                .hasAnyAuthority("STAFF")
                .antMatchers("/api/v1/auth/register", "/api/v1/auth/login", "/api/v1/users/**", 
                        "/api/v1/vehicles/**", "/cloudinary/upload", "/api/v1/showrooms/**").permitAll()
                .anyRequest().authenticated();
//            .authorizeRequests()
//            .antMatchers("/login", "Showroom/api/v1/auth/**").permitAll()
//            .anyRequest().authenticated();
//            .and()
//            .formLogin().loginPage("/login")
//            .usernameParameter("username")
//            .passwordParameter("password")
//            .defaultSuccessUrl("/").failureUrl("/login?error");
//        
//        http.logout().logoutSuccessUrl("/login");

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public Cloudinary getCloudinary() {
        Map config = new HashMap();
        config.put("cloud_name", env.getProperty("cloudinary.cloud-name"));
        config.put("api_key", env.getProperty("cloudinary.api-key"));
        config.put("api_secret", env.getProperty("cloudinary.api-secret"));
        config.put("secure", true);

        return new Cloudinary(config);
    }

}
