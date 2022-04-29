package com.spring.HospitalDemo.entity;

import javax.persistence.*;

@Entity
@Table(name = "rooms_patients")
public class RoomsPatients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "room_id")
    private int roomId;

    @Column(name = "bed_number")
    private int bedNumber;

    @Column(name = "patient_id")
    private Integer patientId;

    public RoomsPatients() {
    }

    public RoomsPatients(int id, int roomId, int bedNumber, Integer patientId) {
        this.id = id;
        this.roomId = roomId;
        this.bedNumber = bedNumber;
        this.patientId = patientId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(int bedNumber) {
        this.bedNumber = bedNumber;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    @Override
    public String toString() {
        return "RoomsPatients{" +
                "id=" + id +
                ", roomId=" + roomId +
                ", bedNumber=" + bedNumber +
                ", patientId=" + patientId +
                '}';
    }
}
