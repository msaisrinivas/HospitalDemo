package com.spring.HospitalDemo.services;

import com.spring.HospitalDemo.DAO.PatientsRepository;
import com.spring.HospitalDemo.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements com.spring.HospitalDemo.services.PatientService {

    @Autowired
    private PatientsRepository patientsRepository;

    @Override
    public List<Patient> findAllPatients() {
        return patientsRepository.findAll();
    }

    @Override
    public Optional<Patient> findPatient(int id) {
        return patientsRepository.findById(id);
    }

    @Override
    public void addPatient(Patient patient) {
        patientsRepository.save(patient);
    }


    @Override
    public List<Patient> findPatientByDoc(String doctorName) {
        return  patientsRepository.findByDoctorName(doctorName);
    }

    @Override
    public List<Patient> findPatientforRooms() {
        return patientsRepository.findPatientForRooms();
    }
}
