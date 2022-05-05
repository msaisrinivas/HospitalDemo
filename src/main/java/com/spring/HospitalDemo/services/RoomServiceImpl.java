package com.spring.HospitalDemo.services;

import com.spring.HospitalDemo.DAO.RoomRepository;
import com.spring.HospitalDemo.DAO.RoomsRepository;
import com.spring.HospitalDemo.entity.Rooms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService{

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<Rooms> findAll() {
        return roomRepository.findAll();
    }
}
