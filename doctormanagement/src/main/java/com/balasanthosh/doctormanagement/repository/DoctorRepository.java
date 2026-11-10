package com.balasanthosh.doctormanagement.repository;

import com.balasanthosh.doctormanagement.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    boolean existsByDoctorEmailIgnoreCase(String doctorEmail);
    boolean existsByDoctorPhoneNumber(String doctorPhoneNumber);

    boolean existsByDoctorEmailIgnoreCaseAndDoctorIdNot(String doctorEmail, Long doctorId);
    boolean existsByDoctorPhoneNumberAndDoctorIdNot(String doctorPhoneNumber, Long doctorId);

}
