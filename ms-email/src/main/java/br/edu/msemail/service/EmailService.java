package br.edu.msemail.service;

import br.edu.msemail.model.Email;
import br.edu.msemail.repository.EmailRepository;
import br.edu.msemail.statusenum.StatusEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmailService {
    private final EmailRepository emailRepository;
    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(EmailRepository emailRepository, JavaMailSender mailSender) {
        this.emailRepository = emailRepository;
        this.mailSender = mailSender;
    }

    public Email sendEmail(Email email) {
        email.setSendDateEmail(LocalDateTime.now());
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email.getEmailFrom());
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());
            mailSender.send(message);

            email.setStatusEmail(StatusEmail.SENT);
        } catch(MailException e) {
            System.out.println(e.getMessage());
            email.setStatusEmail(StatusEmail.ERROR);
        } finally {
            return this.emailRepository.save(email);
        }
    }

    public List<Email> listAll() {
        return this.emailRepository.findAll();
    }
}
