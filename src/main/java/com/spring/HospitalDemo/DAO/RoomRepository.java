package com.spring.HospitalDemo.DAO;

import com.spring.HospitalDemo.entity.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Rooms,Integer> {
}
