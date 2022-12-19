package com.spring.HospitalDemo.services;

import com.spring.HospitalDemo.DAO.RoomsRepository;
import com.spring.HospitalDemo.entity.RoomsPatients;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoomsServiceImpl implements RoomsService {

  @Autowired private RoomsRepository roomsRepository;

  @Override
  public List<RoomsPatients> findAllRoooms() {
    return roomsRepository.findAll();
  }

  @Override
  public Optional<RoomsPatients> findRoomById(int id) {
    return roomsRepository.findById(id);
  }

  @Override
  public RoomsPatients findRoomByPatId(int id) {
    return roomsRepository.findByPatientId(id);
  }

  @Override
  @Transactional
  public void updateRoom(Optional<RoomsPatients> room) {
    if (room.isPresent()) roomsRepository.save(room.get());
  }
}
