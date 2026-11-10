package com.balasanthosh.doctormanagement.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorRequest {
    @NotBlank(message = "Name is required !")
    @Size(min = 3, max = 30, message = "Name must between 3 to 30 characters !\nName format : [ A-Z, a-z, '.', space ]")
    private String doctorName;

    @NotBlank(message = "Email is required !")
    @Email(message = "Please enter a valid email !\nEmail format : [ balasanthosh@fmail.com ]")
    @Size(min = 3, max = 100, message = "Email must be between 3 to 100 characters !")
    private String doctorEmail;

    @NotNull(message="Age must be between 22 to 60 !")
    private byte doctorAge;

    @NotBlank(message = "Phone number is required !")
    @Size(min = 10, max = 10, message = "Phone number must be 10 digits !\nPhone number format : [ 7878787878 ]")
    private String doctorPhoneNumber;
}
