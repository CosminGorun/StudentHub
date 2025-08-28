package ro.studenthub.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ro.studenthub.backend.model.Mail;

import java.util.Random;

@Service
public class MailService {

    @Autowired
    private JavaMailSender sender;

    private MailService(JavaMailSender sender) {
        this.sender = sender;
    }
    public void sendEmail(Mail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail.getTo());
        message.setSubject(mail.getSubject());
        message.setText(mail.getBody());
        sender.send(message);
    }
    public String getCode(){
        Random random=new Random();
        Integer number=100000+random.nextInt(900000);
        return number.toString();
    }
}
