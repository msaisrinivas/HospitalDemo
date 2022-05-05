package com.spring.HospitalDemo.entity;


import javax.persistence.*;

@Entity
@Table(name = "rooms")
public class Rooms {

    @Id
    @Column(name = "room_id")
    private int roomId;

    @Column(name = "room_type")
    private String roomType;

    @Column(name = "max_beds")
    private int maxBeds;

    public Rooms() {
    }

    public Rooms(int roomId, String roomType, int maxBeds) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.maxBeds = maxBeds;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getMaxBeds() {
        return maxBeds;
    }

    public void setMaxBeds(int maxBeds) {
        this.maxBeds = maxBeds;
    }

    @Override
    public String toString() {
        return "Rooms{" +
                "roomId=" + roomId +
                ", roomType='" + roomType + '\'' +
                ", maxBeds=" + maxBeds +
                '}';
    }
}
