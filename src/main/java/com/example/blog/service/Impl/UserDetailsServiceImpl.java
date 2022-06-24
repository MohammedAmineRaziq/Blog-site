package com.example.blog.service.Impl;

import com.example.blog.model.UserDetailsImpl;
import com.example.blog.repository.AdminRepository;
import com.example.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        if(userRepository.findByEmail(email)!=null)
        {
            return new UserDetailsImpl(userRepository.findByEmail(email));
        }
        else
        {
            if (adminRepository.findByEmail(email)!=null)
            {
                return new UserDetailsImpl(adminRepository.findByEmail(email));
            }
        }
        return null;
    }
}
