package com.java_email;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.mail.Session;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MailClientTest {

    public static final String FROM_EMAIL = "fromEmail";
    public static final String PASSWORD = "password";
    public static final String TO_EMAIL = "toEmail";

    @Mock
    private MailHelper mailHelper;

    private MailClient mailClient;

    @Before
    public void setUp() {
        mailClient = new MailClient(mailHelper, FROM_EMAIL, PASSWORD);
    }

    @Test
    public void sendEmail_callsMailHelperToSendEmailWithSession() {
        mailClient.sendEmail(TO_EMAIL);

        ArgumentCaptor<Session> argument = ArgumentCaptor.forClass(Session.class);
        verify(mailHelper).sendMail(eq(TO_EMAIL), argument.capture());
        Session actualSession = argument.getValue();
        assertThat(actualSession, is(notNullValue()));
    }

}