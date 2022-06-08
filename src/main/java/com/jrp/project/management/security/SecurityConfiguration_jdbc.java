package com.jrp.project.management.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration_jdbc extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource datasource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //Define the Authentication mechanism
        auth.jdbcAuthentication()
                .withUser("myuser")
                .password("pass")
                .roles("USER")
                .and()
                .withUser("managerUser")
                .password("pass3")
                .roles("ADMIN");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers("/projects/new").hasRole("ADMIN") //1st rule
                .antMatchers("/employees/new").hasRole("ADMIN")
                .antMatchers("/h2_console/**").permitAll()
                .antMatchers("/").authenticated().and().formLogin();
        http.csrf().disable();
        http.headers().frameOptions().disable();

    }
}
