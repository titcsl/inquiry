package space.titcsl.inquiries.service;

import org.springframework.http.ResponseEntity;
import space.titcsl.inquiries.dto.InquiryDto;

public interface InquiryService {

    ResponseEntity<?> createInquiry(InquiryDto inquiryDTO, String ip_addr);
}
