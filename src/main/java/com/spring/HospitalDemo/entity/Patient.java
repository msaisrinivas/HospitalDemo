package com.spring.HospitalDemo.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

//    @NotBlank()
    @Column(name = "first_name")
    private String firstName;

//    @NotBlank(message = "Last Name is Required")
    @Column(name = "last_name")
    private String lastName;

//    @NotNull(message = "Age is Required")
//    @Min(1)
//    @Max(120)
    @Column(name = "age")
    private int age;


    //@NotNull(message = "Phone number is Required")
    @Column(name = "phone_number")
    private int phoneNumber;


    //@NotBlank(message = "Email is Required")
    @Column(name = "email")
    private String email;


    //@NotBlank(message = "Description is Required")
    @Column(name = "Description")
    private String description;

    @Column(name = "doctor_name")
    private String doctorName;

    @Column(name = "status")
    private String status;

    public Patient() {
    }

    public Patient(int id, String firstName, String lastName, int age, int phoneNumber, String email, String description, String doctorName, String status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.description = description;
        this.doctorName = doctorName;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                ", doctor_name='" + doctorName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
