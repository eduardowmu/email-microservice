package br.edu.msemail.controller;

import br.edu.msemail.dto.EmailDto;
import br.edu.msemail.model.Email;
import br.edu.msemail.service.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/email")
public class EmailController {
    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/sendEmail")
    public ResponseEntity<Email> sendingEmail(@RequestBody @Valid EmailDto emailDto) {
        Email email = new Email();
        BeanUtils.copyProperties(emailDto, email);


        return new ResponseEntity<>(this.emailService.sendEmail(email), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Email>> getEmails() {
        return ResponseEntity.ok(this.emailService.listAll());
    }
}
