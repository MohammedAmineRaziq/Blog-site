package com.example.blog;

import com.example.blog.model.Admin;
import com.example.blog.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BlogApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

    @Autowired
    AdminRepository adminRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if (this.adminRepository.findByEmail("admin@admin.com")==null)
        {
            Admin admin=new Admin();
            admin.setEmail("admin@admin.com");
            admin.setPassword(bCryptPasswordEncoder.encode("12345"));
            this.adminRepository.save(admin);
        }
    }

    @Bean(name = "passwordEncoder")
    public static  BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
