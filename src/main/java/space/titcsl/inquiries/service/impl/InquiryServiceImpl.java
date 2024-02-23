package space.titcsl.inquiries.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import space.titcsl.inquiries.dto.InquiryDto;
import space.titcsl.inquiries.entity.Inquiry;
import space.titcsl.inquiries.entity.OutreCemtity;
import space.titcsl.inquiries.exception.AllExceptionHandler;
import space.titcsl.inquiries.repository.InquiryRepository;
import space.titcsl.inquiries.repository.OutCrumpyRepo;
import space.titcsl.inquiries.service.EmailService;
import space.titcsl.inquiries.service.InquiryService;

@Service
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService {


    private final InquiryRepository inquiryRepository;
    private final OutCrumpyRepo outCrumpyRepo;
    private final EmailService emailService;

    public ResponseEntity<?> createInquiry(InquiryDto inquiryDTO, String ip_addr) {
        if (inquiryDTO.getEmail() == null &&
                inquiryDTO.getFirstName() == null &&
                inquiryDTO.getLastName() == null &&
                inquiryDTO.getService() == null &&
                inquiryDTO.getPhone() == null) {
            throw new AllExceptionHandler("All fields in the inquiry are null");
        }

        Inquiry existingInquiryByEmail = inquiryRepository.findByEmail(inquiryDTO.getEmail());
        Inquiry existingInquiryByPhone = inquiryRepository.findByPhone(inquiryDTO.getPhone());

        if (existingInquiryByEmail != null || existingInquiryByPhone != null) {
            OutreCemtity outreCemtity = new OutreCemtity();
            outreCemtity.setEmail(inquiryDTO.getEmail());
            outreCemtity.setIp_addr(ip_addr);
            outCrumpyRepo.save(outreCemtity);
            emailService.OngoingNewInquiry(inquiryDTO.getEmail());
            throw new AllExceptionHandler("An inquiry with the same email or phone already exists, you will get email with old inquiry details shortly. Thank You!");
        }

        Inquiry inquiry = new Inquiry();

        inquiry.setEmail(inquiryDTO.getEmail());
        inquiry.setFirstName(inquiryDTO.getFirstName());
        inquiry.setLastName(inquiryDTO.getLastName());
        inquiry.setService(inquiryDTO.getService());
        inquiry.setPhone(inquiryDTO.getPhone());
        inquiry.setIp_address(ip_addr);

        inquiryRepository.save(inquiry);
        emailService.NewInquiry(inquiryDTO.getEmail());
        return ResponseEntity.ok("Your inquiry of service " + inquiry.getService() + " has just started. You will get an email from team TITCSL shortly.");
    }


}
