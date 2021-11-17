package com.obsqura.extentreportlistener;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class SendEmailTest {
    public static void sendEmail(String filePath, String fileName, String rMailId) {
        final String username = "sheenroniya@gmail.com";
        final String password = "R0n!y@sheen";

        String eDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Transport transport = session.getTransport();


            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress("test@gmail.com"));

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(rMailId));

            message.setSubject("Automation Execution Healing Results_" + eDate);

            BodyPart messageBodyPart = new MimeBodyPart();
            BodyPart attachmentPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();

            String file = filePath + "//" + fileName;
            DataSource source = new FileDataSource(file);
            attachmentPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setText("Dear Stakeholder,\n" +
                    "\n" +
                    "Please be informed that there are change in attribute values in \"Auto Healing Bot contatc\" application. Changes are noticed during automation execution on " + eDate + ". Attached details of changed attributes.\n" +
                    "\n" +
                    "Thanks & Regards,\n" +
                    "Automation Team");
            attachmentPart.setFileName(fileName);
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentPart);
            message.setContent(multipart);
            transport.connect();
            Transport.send(message);
            System.out.println("MAIL TRIGGERED");
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
