package by.epam.fitness.mail;
import com.sun.mail.smtp.SMTPTransport;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class GoogleMailThread extends Thread {
    private String username;
    private String password;
    private String title;
    private String recipientMail;
    private String message;

    public GoogleMailThread(String username, String password, String title, String recipientMail, String message) {
        this.username = username;
        this.password = password;
        this.title = title;
        this.recipientMail = recipientMail;
        this.message = message;
    }

    @Override
    public void run() {
        Properties sessionConfig = new Properties();
        sessionConfig.setProperty("by.epam.fitness.mail.smtps.host", "smtp.gmail.com");
        sessionConfig.setProperty("by.epam.fitness.mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        sessionConfig.setProperty("by.epam.fitness.mail.smtp.socketFactory.fallback", "false");
        sessionConfig.setProperty("by.epam.fitness.mail.smtp.socketFactory.port", "465");
        sessionConfig.setProperty("by.epam.fitness.mail.smtp.port", "465");
        sessionConfig.setProperty("by.epam.fitness.mail.smtps.auth", "true");
        sessionConfig.setProperty("by.epam.fitness.mail.transport.protocol", "smtp");
        sessionConfig.setProperty("by.epam.fitness.mail.smtp.quitwait", "false");

        Session session = Session.getInstance(sessionConfig);
        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setFrom(new InternetAddress(username + "@gmail.com"));
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientMail, false));
            mimeMessage.setSubject(title);
            mimeMessage.setText(message, "UTF-8");
            mimeMessage.setSentDate(new Date());

            try (SMTPTransport transport = (SMTPTransport) session.getTransport("smtps")) {
                transport.connect("smtp.gmail.com", username, password);
                transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        System.out.println("ok thread");
    }
}
