package com.spring.HospitalDemo.services;

import com.spring.HospitalDemo.entity.Rooms;
import java.util.List;

public interface RoomService {
  List<Rooms> findAll();
}
