package com.balasanthosh.doctormanagement.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"doctorId", "doctorName", "doctorEmail", "doctorAge", "doctorPhoneNumber"})
public class DoctorResponse {
    private Long doctorId;
    private String doctorName;
    private String doctorEmail;
    private byte doctorAge;
    private String doctorPhoneNumber;

}
