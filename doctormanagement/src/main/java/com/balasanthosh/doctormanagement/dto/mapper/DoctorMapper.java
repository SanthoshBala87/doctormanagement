package com.balasanthosh.doctormanagement.dto.mapper;

import com.balasanthosh.doctormanagement.dto.request.DoctorRequest;
import com.balasanthosh.doctormanagement.dto.response.DoctorResponse;
import com.balasanthosh.doctormanagement.entity.Doctor;

public class DoctorMapper {
    public static Doctor toEntity(DoctorRequest doctorRequest) {
        Doctor doctor = new Doctor();
        doctor.setDoctorName(doctorRequest.getDoctorName().trim());
        doctor.setDoctorEmail(doctorRequest.getDoctorEmail().trim().toLowerCase());
        doctor.setDoctorAge(doctorRequest.getDoctorAge());
        doctor.setDoctorPhoneNumber(doctorRequest.getDoctorPhoneNumber().trim());
        return doctor;
    }

    public static DoctorResponse toResponse(Doctor doctor) {
        DoctorResponse doctorResponse = new DoctorResponse();
        doctorResponse.setDoctorId(doctor.getDoctorId());
        doctorResponse.setDoctorName(doctor.getDoctorName());
        doctorResponse.setDoctorEmail(doctor.getDoctorEmail());
        doctorResponse.setDoctorAge(doctor.getDoctorAge());
        doctorResponse.setDoctorPhoneNumber(doctor.getDoctorPhoneNumber());
        return doctorResponse;
    }

}
