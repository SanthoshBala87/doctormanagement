package com.balasanthosh.doctormanagement.service.validation;

import com.balasanthosh.doctormanagement.dto.request.DoctorRequest;
import com.balasanthosh.doctormanagement.exception.InvalidDataException;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class DoctorValidation {
    public void validateDoctorDetails(DoctorRequest doctorRequest) {
        if (doctorRequest == null) {
            throw new InvalidDataException(Map.of(
                    "exception", "Please fill all details !"
            ));
        }
        Map<String, String> exceptions = new LinkedHashMap<>();
        validateName(doctorRequest.getDoctorName(), exceptions);
        validateEmail(doctorRequest.getDoctorEmail(), exceptions);
        validateAge(doctorRequest.getDoctorAge(), exceptions);
        validatePhoneNumber(doctorRequest.getDoctorPhoneNumber(), exceptions);
        if (!exceptions.isEmpty()) {
            throw new InvalidDataException(exceptions);
        }

    }

    void validateName(String name, Map<String, String> exceptions) {
        if (name == null || name.isBlank() || !name.trim().matches("^[A-Za-z\\.\\s]{3,30}$")) {
            exceptions.put("doctorName", "Please enter a valid name !\nFormat : [ Balasanthosh ].");
        }
    }

    void validateEmail(String email, Map<String, String> exceptions) {
        if (email == null || email.isBlank() || !email.trim().matches("^[A-Za-z][A-Za-z0-9\\.\\,\\_\\-]*@[A-Za-z]{2,15}\\.[A-Za-z]{2,15}$")) {
            exceptions.put("doctorEmail", "Please enter a valid email !\nFormat : [ balasanthosh@fmail.com ].");
        }
    }

    void validateAge(byte age, Map<String, String> exceptions) {
        if (age < 22 || age > 60) {
            exceptions.put("doctorAge", "Please enter a valid age !\nAge must be between 22 to 60.");
        }
    }

    void validatePhoneNumber(String phoneNumber, Map<String, String> exceptions) {
        if (phoneNumber == null || phoneNumber.isBlank() || !phoneNumber.trim().matches("^[6-9][0-9]{9}$")) {
            exceptions.put("doctorPhoneNumber", "Please enter a valid phone number !\nFormat : [ 7878787878 ].");
        }
    }

    public void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new InvalidDataException(Map.of(
                    "exception", "Please enter a valid id !"
            ));
        }
    }

}
