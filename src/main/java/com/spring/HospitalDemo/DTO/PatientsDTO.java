package com.spring.HospitalDemo.DTO;

import com.spring.HospitalDemo.entity.Patient;
import lombok.Data;

import java.util.Optional;


@Data
public class PatientsDTO {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private int phoneNumber;
    private String email;
    private String description;
    private String doctorName;
    private String status;

    public PatientsDTO() {
    }

    public PatientsDTO(Optional<Patient> patient) {
        if(patient.isPresent())
        {
            this.id = patient.get().getId();
            this.firstName=patient.get().getFirstName();
            this.lastName=patient.get().getLastName();
            this.age=patient.get().getAge();
            this.phoneNumber=patient.get().getPhoneNumber();
            this.email = patient.get().getEmail();
            this.description = patient.get().getDescription();
            this.doctorName =patient.get().getDoctorName();
            this.status =patient.get().getStatus();
        }
    }

    public Patient toPatient()
    {
        return new Patient(id,firstName,lastName,age,phoneNumber,email,description,doctorName,status);
    }
}
