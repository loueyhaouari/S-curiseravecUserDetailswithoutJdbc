//package com.example.demo.security;
//
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//import javax.sql.DataSource;
//
//@Configuration //pour montrer que cette classe est une classe de configuration
//@EnableWebSecurity //pour activer le security web
//@EnableMethodSecurity(prePostEnabled = true)
//@AllArgsConstructor
//public class SecurityConfig {
//
//
//    private PasswordEncoder passwordEncoder;
//    //@Bean
//    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){
//        JdbcUserDetailsManager jdbcUserDetailsManager=new JdbcUserDetailsManager(dataSource);
//        //j'utilise la meme base de données dans application.properties
//        return jdbcUserDetailsManager;
//    }
//    //@Bean
//    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
//          String pwd=passwordEncoder.encode("12345");
//        //mot de passe hachéé avec bcrypt
//        System.out.println(pwd);
//
//        InMemoryUserDetailsManager inMemoryUserDetailsManager=new InMemoryUserDetailsManager(
//                //User est une classe de spring security
//                //{noop} noPassword Encoder
////                User.withUsername("user1").password("{noop}1234").roles("USER").build(),
////                User.withUsername("admin").password("{noop}1234").roles("USER,ADMIN").build(),
////                User.withUsername("user2").password("{noop}1234").roles("USER").build()
//                User.withUsername("user1").password(pwd).roles("USER").build(),
//                User.withUsername("admin").password(passwordEncoder.encode("3333")).roles("ADMIN","USER").build(),
//                User.withUsername("user2").password(passwordEncoder.encode("4444")).roles("USER").build()
//
//        );
//   return inMemoryUserDetailsManager;
//    }
//
//    //créer security filter cha in
//    //Spring security est basée sur une chaine de filter
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        //httpSecurity est un builder
//        //toutes les requetes nécessitte une authentifcation
//        httpSecurity.csrf().disable();
//        httpSecurity.formLogin();
//        //elle permet que l'utilisateur qui a un role admin le seul qui peut éffectuer la requete /delete
//        httpSecurity.authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN");
//  httpSecurity.authorizeHttpRequests().requestMatchers("/user/**").hasRole("USER");
//       httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
//       httpSecurity.exceptionHandling().accessDeniedPage("/notAuthorized");
////       httpSecurity.userDetailsService(userDetailServiceImpl);
//        return       httpSecurity.build();
//    }
//}
