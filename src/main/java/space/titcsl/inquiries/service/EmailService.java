package space.titcsl.inquiries.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import space.titcsl.inquiries.entity.Inquiry;
import space.titcsl.inquiries.repository.InquiryRepository;

@Service
@RequiredArgsConstructor
public class EmailService {

    @Autowired
    private final JavaMailSender emailSender;
    private final InquiryRepository inquiryRepository;

    public void NewInquiry(String email) {
        Inquiry inquiry = inquiryRepository.findByEmail(email);
        String to = "titcsl.mail@gmail.com";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("NEW INQUIRY CIRCULAR INQUIRY ID [" + inquiry.getId() + "]");
        message.setText("New inquiry has just started with email id:- [" + inquiry.getEmail() + "]. Thank You!");
        emailSender.send(message);
    }

    public void OngoingNewInquiry(String email) {
        Inquiry inquiry = inquiryRepository.findByEmail(email);
        String to = "titcsl.mail@gmail.com";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("ELSE TRIED WITH INQUIRY CIRCULAR INQUIRY ID [" + inquiry.getId() + "]");
        message.setText("Old inquiry has just started with email id:- [" + inquiry.getEmail() + "]. Thank You!");
        emailSender.send(message);
    }
}
