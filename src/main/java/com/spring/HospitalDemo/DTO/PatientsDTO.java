package com.spring.HospitalDemo.DTO;

import com.spring.HospitalDemo.entity.Patient;
import java.util.Optional;
import javax.validation.constraints.*;
import lombok.Data;

@Data
public class PatientsDTO {
  private int id;

  @NotBlank(message = "First Name is Required")
  private String firstName;

  @NotBlank(message = "Last Name is Required")
  private String lastName;

  @NotNull(message = "Age is Required")
  @Min(1)
  @Max(120)
  private int age;

  @NotNull(message = "Phone number is Required")
  private int phoneNumber;

  @Email
  @NotBlank(message = "Email is Required")
  private String email;

  @NotBlank(message = "Description is Required")
  private String description;

  private String doctorName;
  private String status;

  public PatientsDTO() {}

  public PatientsDTO(Optional<Patient> patient) {
    if (patient.isPresent()) {
      this.id = patient.get().getId();
      this.firstName = patient.get().getFirstName();
      this.lastName = patient.get().getLastName();
      this.age = patient.get().getAge();
      this.phoneNumber = patient.get().getPhoneNumber();
      this.email = patient.get().getEmail();
      this.description = patient.get().getDescription();
      this.doctorName = patient.get().getDoctorName();
      this.status = patient.get().getStatus();
    }
  }

  public Patient toPatient() {
    return new Patient(
        id, firstName, lastName, age, phoneNumber, email, description, doctorName, status);
  }
}
