package space.titcsl.inquiries.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import space.titcsl.inquiries.entity.Inquiry;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
    Inquiry findByEmail(String email);
    Inquiry findByPhone(String phone);
}

