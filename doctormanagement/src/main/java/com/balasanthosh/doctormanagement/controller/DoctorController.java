package com.balasanthosh.doctormanagement.controller;

import com.balasanthosh.doctormanagement.dto.request.DoctorRequest;
import com.balasanthosh.doctormanagement.dto.response.DoctorResponse;
import com.balasanthosh.doctormanagement.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/doctor")
@CrossOrigin(origins="http://127.0.0.1:5500")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public ResponseEntity<DoctorResponse> createDoctor(@Valid @RequestBody DoctorRequest doctorRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.createDoctor(doctorRequest));
    }

    @GetMapping
    public ResponseEntity<Map<String, DoctorResponse>> readAllDoctors() {
        return ResponseEntity.ok(doctorService.readAllDoctors());
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<DoctorResponse> readByDoctorId(@PathVariable Long doctorId) {
        return ResponseEntity.ok(doctorService.readByDoctorId(doctorId));
    }

    @PutMapping("/{doctorId}")
    public ResponseEntity<DoctorResponse> updateDoctor(@Valid @RequestBody DoctorRequest doctorRequest, @PathVariable Long doctorId) {
        return ResponseEntity.ok(doctorService.updateDoctor(doctorRequest, doctorId));
    }

    @DeleteMapping("/{doctorId}")
    public ResponseEntity<String> deleteByDoctorId(@PathVariable Long doctorId) {
        return ResponseEntity.ok(doctorService.deleteDoctor(doctorId));
    }

}
