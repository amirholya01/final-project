package com.amir.finalproject.security.service;

import com.amir.finalproject.model.User;
import com.amir.finalproject.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    @Transactional
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("The user was not found -- username or email is not valid "+ username)
        );
        return UserPrinciple.build(user);
   }
}
