package model;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class ResetPasswordEmail {

    public static void send(String to, String subject, String message, final String user, final String password)
    {
        //Create an instance of Properties Class
        Properties prop = new Properties();

        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");


        /*Pass Properties object(properties) and Authenticator Object
         * for authentication to Session instance*/

        Session session = Session.getInstance(prop, new javax.mail.Authenticator(){

            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(user, password);
            }

        });

        try
        {
            /*Create an instance of MIME Message,
             * it accepts MIME types and headers*/

            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(user));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);


            /*Now Transport Class is used to  deliver the message to the recipients*/

            Transport.send(mimeMessage);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }
}
