package com.spring.HospitalDemo.services;

import com.spring.HospitalDemo.entity.RoomsPatients;

import java.util.List;
import java.util.Optional;

public interface RoomsService {
    public List<RoomsPatients> findAllRoooms();

    Optional<RoomsPatients> findRoomById(int id);

    RoomsPatients findRoomByPatId(int id);

    void updateRoom(Optional<RoomsPatients> room);
}
