package org.wildhamsters.loginservice;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * @author Mariusz Bal
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@SuppressFBWarnings(value = "THROWS_METHOD_THROWS_CLAUSE_BASIC_EXCEPTION", justification = "Can't fix that for now")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    final private UserDetailsService userDetailsService;
    final private PasswordEncoder passwordEncoder;

    public WebSecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable()
                .formLogin()
                .defaultSuccessUrl("/welcome")
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/register**").permitAll()
                .antMatchers("/menu").permitAll()
                .anyRequest().authenticated().and()
                .logout()
                .logoutSuccessHandler(new LoginserviceSuccessLogoutHandler())
                .logoutSuccessUrl("/login?logout");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }
}
