package com.example.zerodigital.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;



    private  BCryptPasswordEncoder bCryptPasswordEncoder;




    public User register(User user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public List<User> getUsers()
    {
        return userRepository.findAll();
    }
    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword()) // already encrypted
                .build();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
