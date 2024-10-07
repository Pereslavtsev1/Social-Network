package org.example.socialnetwork.services;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.example.socialnetwork.exceptions.EmailFailedToSendException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;
    private final Gmail gmailService;
    @Value("${mail.email.sender}")
    private String emailForSendMessage;

    public void createEmail(String toEmailAddress, String subject, String bodyText) throws MessagingException, IOException {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        MimeMessage email = new MimeMessage(session);
        email.setFrom(new InternetAddress(emailForSendMessage));
        email.addRecipient(jakarta.mail.Message.RecipientType.TO, new InternetAddress(toEmailAddress));
        email.setSubject(subject);
        email.setText(bodyText);

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        email.writeTo(buffer);
        byte[] bytes = buffer.toByteArray();
        String encodedEmail = Base64.encodeBase64String(bytes); // использование Base64.encodeBase64String вместо Base64.encodeBase64URLSafeString
        Message message = new Message();
        message.setRaw(encodedEmail);
        try {
            gmailService.users().messages().send("me", message).execute();
        } catch (GoogleJsonResponseException e) {
            e.printStackTrace();
            throw new EmailFailedToSendException();
        }
    }
}
