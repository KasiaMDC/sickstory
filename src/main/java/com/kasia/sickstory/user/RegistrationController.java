package com.kasia.sickstory.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

    @Controller
    @RequestMapping("/register")
    public class RegistrationController {

        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;

        @Autowired
        public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
            this.userRepository = userRepository;
            this.passwordEncoder = passwordEncoder;
        }

        @GetMapping
        public String prepareRegistrationPage() {
            return "/WEB-INF/views/registration-form.jsp";
        }

        @PostMapping
        public String processRegistrationPage(String username,
                                              String password,
                                              String firstName,
                                              String lastName) {
            User user = new User();
            String encodedPassword = passwordEncoder.encode(password);
            user.setPassword(encodedPassword);
            user.setUsername(username);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setActive(true);

            userRepository.save(user);
            return "redirect:/index.html";
        }
    }

