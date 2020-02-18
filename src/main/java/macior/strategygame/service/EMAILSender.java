package macior.strategygame.service;

import macior.strategygame.models.ActivationLink;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EMAILSender {

    private static String address = "http://127.0.0.1:8080//api/activationLink/";

    public static int sendActivationLink(ActivationLink link) {
        String to = link.getEmail();
        String login = link.getLogin();
        String code = address + link.getActivationLink();
        String messageText = "Welcome, " + login + ". Your activation link is: \n" + code + "\n"
                    + "Enter it to create new account!\n";
        return send("CREATING ACCOUNT", messageText, to, login);
    }

    public static int send(String topic, String messageToSend, String email, String username) {
        String host="smtp.gmail.com";
        final String user="d21gsd21gs@gmail.com";//change accordingly
        final String password="desertzmiensem123456";//change accordingly

        String to = email;//change accordingly

        //Get the session object
        Properties props = new Properties();
        props.put("mail.smtp.host",host);
        props.put("user", user);
        props.put("password", password);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user,password);
                    }
                });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject(topic);
            message.setText(messageToSend);

            System.out.println("Sending the message!");
            //send the message
            Transport.send(message);

            System.out.println("message sent successfully...");

        } catch (MessagingException e) {
            return -1;
        }
        return 0;
    }
}
