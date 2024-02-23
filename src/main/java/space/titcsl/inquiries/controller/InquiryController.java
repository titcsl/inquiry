package space.titcsl.inquiries.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import space.titcsl.inquiries.dto.InquiryDto;
import space.titcsl.inquiries.entity.Inquiry;
import space.titcsl.inquiries.exception.AllExceptionHandler;
import space.titcsl.inquiries.repository.InquiryRepository;
import space.titcsl.inquiries.service.InquiryService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/${space.titcsl.api.version}/inquiry")
public class InquiryController {

    private final InquiryService inquiryService;
    private final InquiryRepository inquiryRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createInquiry(@RequestBody InquiryDto inquiryDto, HttpServletRequest request){
        String ip_addr = request.getRemoteAddr();
        try {
            return ResponseEntity.ok(inquiryService.createInquiry(inquiryDto, ip_addr));
        }catch (AllExceptionHandler ex) {
            Map<String, String> response = new HashMap<>();
            response.put("message", ex.getMessage());
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(response);
        }
    }

    @GetMapping("/8vd356nkod/okget")
    public ResponseEntity<?> getInquiry(@RequestBody InquiryDto inquiryDto){
        String email = inquiryDto.getEmail();
        try {
            Inquiry inquiry = inquiryRepository.findByEmail(email);
            return ResponseEntity.ok(inquiry);
        }catch (AllExceptionHandler ex) {
            Map<String, String> response = new HashMap<>();
            response.put("message", ex.getMessage());
            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(response);
        }
    }



}
