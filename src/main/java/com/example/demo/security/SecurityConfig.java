package com.example.demo.security;

import com.example.demo.security.service.UserDetailServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;


@EnableWebSecurity
@Configuration
@EnableMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
//Pour éviter la suppression avec l'url protéger les méthodes
public class SecurityConfig {

    private PasswordEncoder passwordEncoder;
    private UserDetailServiceImpl userDetailServiceImpl;
    //Datasource de mon application c'est la base de donnéee qui est configuré dans application.properties
//@Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){
    JdbcUserDetailsManager jdbcUserDetailsManager=new JdbcUserDetailsManager(dataSource);
    return jdbcUserDetailsManager;
}
    //@Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        return new InMemoryUserDetailsManager(
//                User.withUsername("user1").password("{noop}1234").roles("USER").build(),
//                User.withUsername("user2").password("{noop}1234").roles("USER").build(),
//                User.withUsername("admin").password("{noop}1234").roles("USER","ADMIN").build()
                User.withUsername("user1").password(passwordEncoder.encode("1234")).roles("USER").build(),
                User.withUsername("user2").password(passwordEncoder.encode("1234")).roles("USER").build(),
                User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("USER","ADMIN").build()
        );
    }
@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    //Toutes les requetes nécéssite une authentification
//   httpSecurity.formLogin();
//    httpSecurity.rememberMe();
    httpSecurity.formLogin().loginPage("/login").permitAll();
    httpSecurity.authorizeHttpRequests().requestMatchers("/webjars/**").permitAll();
//    httpSecurity.authorizeHttpRequests().requestMatchers("/user/**").hasRole("USER");
//    httpSecurity.authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN");
        httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
        httpSecurity.exceptionHandling().accessDeniedPage("/notAuthorized");
        httpSecurity.userDetailsService(userDetailServiceImpl);
    return httpSecurity.build();
    }

}