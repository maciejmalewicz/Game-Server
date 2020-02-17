package macior.strategygame.service;

import macior.strategygame.models.ActivationLink;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EMAILSender {

    private static String address = "file:///C:/Users/grzeg/Desktop/startegy-game-client/RegistrationPage/Index.html";

    public static void sendActivationLink(ActivationLink link) {
        String host="smtp.gmail.com";
        final String user="d21gsd21gs@gmail.com";//change accordingly
        final String password="desertzmiensem123456";//change accordingly

        String to = link.getEmail();//change accordingly
        String login = link.getLogin();
        String code = link.getActivationLink();

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

        //Compose the message

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject("CREATING ACCOUNT");
            message.setText("Welcome, " + login + ". Your activation link is: " + code + "\n"
            + "Enter it to create new account!\n"
            + "Enter code here: " + address);

            System.out.println("Sending the message!");
            //send the message
            Transport.send(message);

            System.out.println("message sent successfully...");

        } catch (MessagingException e) {e.printStackTrace();}
    }

}
