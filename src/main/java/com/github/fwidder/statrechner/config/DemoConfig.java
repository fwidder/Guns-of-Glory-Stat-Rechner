package com.github.fwidder.statrechner.config;

import com.github.fwidder.statrechner.dao.AuthorityRepository;
import com.github.fwidder.statrechner.dao.UserRepository;
import com.github.fwidder.statrechner.model.Authority;
import com.github.fwidder.statrechner.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DemoConfig implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${app.demo}")
    String demo;

    @Value("${app.demo.user}")
    String username;

    @Value("${app.demo.pass}")
    String pass;

    private static final Logger LOG =
            LoggerFactory.getLogger(DemoConfig.class);

    @Override
    public void run(String... args) throws Exception {
        if (demo == null || demo.isEmpty() || !demo.equalsIgnoreCase("true")) {
            LOG.info("Demo Mode is disabled!");
            return;
        }

        if (username == null || username.isBlank() || pass == null || pass.isBlank()) {
            LOG.warn("Demo Mode disabled! No Username or Password supplied!");
            LOG.warn("Please provide them in you properties as 'app.demo.user' and 'app.demo.pass'.");
            return;
        }

        LOG.info("Demo Mode is enabled!");

        Iterable<Authority> authorities = authorityRepository.findAll();
        Set<Authority> authoritySet = new HashSet<>();
        authorities.forEach(authoritySet::add);

        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(pass))
                .authorities(authoritySet)
                .build();

        userRepository.save(user);

        Iterable<User> users = userRepository.findAll();
        LOG.info("Users:");
        users.forEach(u -> {
            LOG.info("\t{} -> {}", u.getId(), u.getUsername());
        });
    }
}