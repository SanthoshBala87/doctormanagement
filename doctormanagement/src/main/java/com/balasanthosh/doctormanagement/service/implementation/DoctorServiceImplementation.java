package com.balasanthosh.doctormanagement.service.implementation;

import com.balasanthosh.doctormanagement.dto.mapper.DoctorMapper;
import com.balasanthosh.doctormanagement.dto.request.DoctorRequest;
import com.balasanthosh.doctormanagement.dto.response.DoctorResponse;
import com.balasanthosh.doctormanagement.entity.Doctor;
import com.balasanthosh.doctormanagement.exception.DataNotFoundException;
import com.balasanthosh.doctormanagement.exception.DuplicateDataFoundException;
import com.balasanthosh.doctormanagement.repository.DoctorRepository;
import com.balasanthosh.doctormanagement.service.DoctorService;
import com.balasanthosh.doctormanagement.service.validation.DoctorValidation;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class DoctorServiceImplementation implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final DoctorValidation doctorValidation;

    public DoctorServiceImplementation(DoctorRepository doctorRepository, DoctorValidation doctorValidation) {
        this.doctorRepository = doctorRepository;
        this.doctorValidation = doctorValidation;
    }

    @Override
    public DoctorResponse createDoctor(DoctorRequest doctorRequest) {
        doctorValidation.validateDoctorDetails(doctorRequest);
        checkDuplicateDataToCreate(doctorRequest.getDoctorEmail().trim().toLowerCase(), doctorRequest.getDoctorPhoneNumber().trim());
        Doctor doctor = DoctorMapper.toEntity(doctorRequest);
        doctorRepository.save(doctor);
        return DoctorMapper.toResponse(doctor);
    }

    @Override
    public Map<String, DoctorResponse> readAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        if (doctors.isEmpty()) {
            Map<String, String> exceptions = new LinkedHashMap<>();
            exceptions.put("exception", "No doctors data found !");
            throw new DataNotFoundException(exceptions);
        }
        Map<String, DoctorResponse> doctorResponses = new LinkedHashMap<>();
        for (Doctor doctor : doctors) {
            doctorResponses.put("DID" + doctor.getDoctorId(), DoctorMapper.toResponse(doctor));
        }
        return doctorResponses;
    }

    @Override
    public DoctorResponse readByDoctorId(Long doctorId) {
        doctorValidation.validateId(doctorId);
        Map<String, String> exceptions = new LinkedHashMap<>();
        exceptions.put("exception", "No doctor data found for this '" + doctorId + "' doctor id !");
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new DataNotFoundException(exceptions));
        return DoctorMapper.toResponse(doctor);
    }

    @Override
    public DoctorResponse updateDoctor(DoctorRequest doctorRequest, Long doctorId) {
        doctorValidation.validateId(doctorId);
        Map<String, String> exceptions = new LinkedHashMap<>();
        if (!doctorRepository.existsById(doctorId)) {
            exceptions.put("exception", "No doctor data found for this '" + doctorId + "' doctor id !");
            throw new DataNotFoundException(exceptions);
        }
        doctorValidation.validateDoctorDetails(doctorRequest);
        checkDuplicateDataToUpdate(doctorRequest.getDoctorEmail().trim().toLowerCase(), doctorRequest.getDoctorPhoneNumber().trim(), doctorId);
        Doctor doctor = DoctorMapper.toEntity(doctorRequest);
        doctor.setDoctorId(doctorId);
        doctorRepository.save(doctor);
        return DoctorMapper.toResponse(doctor);
    }

    @Override
    public String deleteDoctor(Long doctorId) {
        doctorValidation.validateId(doctorId);
        Map<String, String> exceptions = new LinkedHashMap<>();
        if (!doctorRepository.existsById(doctorId)) {
            exceptions.put("exception", "No doctor data found for this '" + doctorId + "' doctor id !");
            throw new DataNotFoundException(exceptions);
        }
        doctorRepository.deleteById(doctorId);
        return "doctor data for this '" + doctorId + "' doctor id is successfully deleted !";
    }

    void checkDuplicateDataToCreate(String email, String phoneNumber) {
        Map<String, String> exceptions = new LinkedHashMap<>();
        if (doctorRepository.existsByDoctorEmailIgnoreCase(email)) {
            exceptions.put("doctorEmail", "Email already exists '" + email + "' !");
        }
        if (doctorRepository.existsByDoctorPhoneNumber(phoneNumber)) {
            exceptions.put("doctorPhoneNumber", "Phone number already exists '" + phoneNumber + "' !");
        }
        if (!exceptions.isEmpty()) {
            throw new DuplicateDataFoundException(exceptions);
        }
    }

    void checkDuplicateDataToUpdate(String email, String phoneNumber, Long id) {
        Map<String, String> exceptions = new LinkedHashMap<>();
        if (doctorRepository.existsByDoctorEmailIgnoreCaseAndDoctorIdNot(email, id)) {
            exceptions.put("doctorEmail", "Email already exists '" + email + "' !");
        }
        if (doctorRepository.existsByDoctorPhoneNumberAndDoctorIdNot(phoneNumber, id)) {
            exceptions.put("doctorPhoneNumber", "Phone number already exists '" + phoneNumber + "' !");
        }
        if (!exceptions.isEmpty()) {
            throw new DuplicateDataFoundException(exceptions);
        }
    }

}
