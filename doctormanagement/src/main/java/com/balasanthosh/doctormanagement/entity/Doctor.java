package com.balasanthosh.doctormanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "doctors")
@Getter
@Setter
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long doctorId;

    @Column(name = "name", nullable = false, length = 30)
    private String doctorName;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String doctorEmail;

    @Column(name = "age", nullable = false)
    private byte doctorAge;

    @Column(name = "phone_number", nullable = false, unique = true, length = 10)
    private String doctorPhoneNumber;

}
