package com.disanyuzhou.email;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 * Created by make on 7/17/16.
 */
public class Test {
    public static void main(String[] args) throws EmailException {

        SimpleEmail email = new SimpleEmail();
        email.setHostName("smtp.sina.com");
        email.setSmtpPort(25);
        email.setAuthenticator(new DefaultAuthenticator("oozie@sina.com", "makeoozie"));
        email.setSSLOnConnect(false);
        email.setFrom("oozie@sina.com");
        email.setSubject("test");
        email.setMsg("Hello world!");
        email.addTo("oozie@sina.com");
//        email.setAuthentication("oozie@sina.com", "makeoozie");

        email.send();
    }
}
