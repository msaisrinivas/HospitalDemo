package com.spring.HospitalDemo.services;

import com.spring.HospitalDemo.entity.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    public List<Patient> findAllPatients();

    public Optional<Patient> findPatient(int id);

    public void addPatient(Patient patient);

    public List<Patient> findPatientByDoc(String doctorName);

    List<Patient> findPatientforRooms();

    void deleteByDoc(String username);
}
