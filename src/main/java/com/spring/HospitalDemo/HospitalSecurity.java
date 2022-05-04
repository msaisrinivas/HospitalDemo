package com.spring.HospitalDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class HospitalSecurity extends WebSecurityConfigurerAdapter {


    @Autowired
    private DataSource securityDataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //users form JDBCBycryptPasswordEncoder
        auth.jdbcAuthentication().dataSource(securityDataSource).passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery(
                        "select username,password, enabled from employee where username=?")
                .authoritiesByUsernameQuery(
                        "select username, authority from authorities where username=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").hasAnyRole("DOCTOR","ADMIN")
                .antMatchers("/patients/viewdet").hasAnyRole("ADMIN","DOCTOR")
                .antMatchers("/patients/**").hasRole("DOCTOR")
                .antMatchers("/rooms/**").hasRole("ADMIN")
                .and()
                .formLogin().loginPage("/myLoginPage")
                .loginProcessingUrl("/authTheUser").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access");
    }
}
