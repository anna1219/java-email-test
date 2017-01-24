package com.java_email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Application {

    @Autowired
    private MailClient mailClient;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    private void sendMail() {
        mailClient.sendEmail("any toEmail here");
    }
}
