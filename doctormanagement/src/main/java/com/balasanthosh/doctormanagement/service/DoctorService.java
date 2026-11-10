package com.balasanthosh.doctormanagement.service;

import com.balasanthosh.doctormanagement.dto.request.DoctorRequest;
import com.balasanthosh.doctormanagement.dto.response.DoctorResponse;

import java.util.Map;

public interface DoctorService {
    DoctorResponse createDoctor(DoctorRequest doctorRequest);

    Map<String, DoctorResponse> readAllDoctors();

    DoctorResponse readByDoctorId(Long doctorId);

    DoctorResponse updateDoctor(DoctorRequest doctorRequest, Long doctorId);

    String deleteDoctor(Long doctorId);

}
