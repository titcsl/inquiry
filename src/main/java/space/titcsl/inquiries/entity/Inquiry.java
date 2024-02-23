package space.titcsl.inquiries.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.Random;

@Entity
@Data
@Table(name = "_xinquiry")
public class Inquiry {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", unique = true)
    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String service;

    private String ip_address;

}
