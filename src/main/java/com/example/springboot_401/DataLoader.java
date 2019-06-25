package com.example.springboot_401;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Component;


import java.util.Arrays;


// Add user/role data into the database before the application runs

@Component

public class DataLoader implements CommandLineRunner {

    // Instantiate both the user and role repository to invoke constructor methods

    @Autowired

    UserRepository userRepository;


    @Autowired

    RoleRepository roleRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired

    private PasswordEncoder passwordEncoder;



    /*

        Run method will be executed after the application context is

        loaded and right before the Spring Application run method is

        completed.

     */

    @Override

    public void run(String... strings) throws Exception {

        System.out.println("Loading data...");

        roleRepository.save(new Role("ADMIN"));


        User user = new User("myemail@gmail.com", "password", "Cesar", "Mejia", true, "crsmejia");

        user.setRoles(Arrays.asList(roleRepository.save(new Role("AUTHOR"))));

        user.setProfileImg("https://res.cloudinary.com/dn5oij7hb/image/upload/v1559607790/krcmuorysynfcdsqtmnq.jpg");

        userRepository.save(user);


        Message message = new Message();
        message.setAuthorId(user.getId());
        message.setImageUrl("https://res.cloudinary.com/dn5oij7hb/image/upload/v1559609170/avzjnx4kz146xoqzmlvu.jpg");
        message.setTitle("Welcome!");
        message.setContent("Welcome to the Bullhorn application, I hope you enjoy your stay!");
        message.setPostedBy(user.getUsername());
        message.setPostedDate("06/03/2019");

        messageRepository.save(message);

    }

}
