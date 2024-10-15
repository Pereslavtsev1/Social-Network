package org.example.socialnetwork.service;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import jakarta.mail.Message.RecipientType;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.example.socialnetwork.exception.FailedSendEmailException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class MailService {
    @Value("${secret.email.address}")
    private String emailAddress;
    private final Gmail gmail;

    public void sendEmail(String toEmailAddress, String subject, String content) {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage email = new MimeMessage(session);
        try {
            email.setFrom(new InternetAddress(emailAddress));
            email.addRecipient(RecipientType.TO,
                    new InternetAddress(toEmailAddress));
            email.setSubject(subject);
            email.setText(content);

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            email.writeTo(buffer);
            byte[] rawMessageBytes = buffer.toByteArray();
            String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
            Message message = new Message();
            message.setRaw(encodedEmail);
            gmail.users().messages().send("me", message).execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new FailedSendEmailException();
        }
    }
}
