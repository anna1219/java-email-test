package com.java_email;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.subethamail.wiser.Wiser;

import static com.java_email.WiserAssertions.assertReceivedMessage;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
public class SendMailIntegrationTest {

    public static final String TO_EMAIL = "toemail@host.com";

    private Wiser wiser;

    @Autowired
    private MailClient mailClient;

    @Before
    public void setUp() throws Exception {
        //need to use a port >1024 so avoid port binding permission error
        wiser = new Wiser(9000);
        wiser.start();
    }

    @After
    public void tearDown() throws Exception {
        wiser.stop();
    }

    @Test
    public void send() throws Exception {
        mailClient.sendEmail(TO_EMAIL);

        assertReceivedMessage(wiser)
                .from("javaEmailTestAcc@gmail.com")
                .to(TO_EMAIL)
                .withSubject("Subject")
                .withContent("Hellow from Java Email Test");
    }
}