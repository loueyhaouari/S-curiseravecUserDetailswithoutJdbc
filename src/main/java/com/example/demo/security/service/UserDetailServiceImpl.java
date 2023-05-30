//package com.example.demo.security.service;
//
//import com.example.demo.security.entities.AppUser;
//import lombok.AllArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class UserDetailServiceImpl implements UserDetailsService {
// private AccountService accountService;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//       AppUser appUser= accountService.loadUserByUsername(username);
//       if(appUser==null) throw new UsernameNotFoundException(String.format("User %s not found",username));
//       Use
//       return null;
//    }
//}
