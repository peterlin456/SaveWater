package com.lifebelowwater.savewater.config;

import com.lifebelowwater.savewater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserService userService;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers("api/v1/**").permitAll()
                .antMatchers("/new").hasAnyAuthority("ADMIN")
                .anyRequest().authenticated()
                .and().httpBasic();


        //example:
//        http.authorizeRequests()
//                .antMatchers("/").hasAnyAuthority("USER", "CREATOR", "EDITOR", "ADMIN")
//                .antMatchers("/new").hasAnyAuthority("ADMIN", "CREATOR")
//                .antMatchers("/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
//                .antMatchers("/delete/**").hasAuthority("ADMIN")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().permitAll()
//                .and()
//                .logout().permitAll()
//                .and()
//                .exceptionHandling().accessDeniedPage("/403")
//        ;
    }
}
