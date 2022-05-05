package com.spring.HospitalDemo.DAO;

import com.spring.HospitalDemo.entity.RoomsPatients;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomsRepository extends JpaRepository<RoomsPatients,Integer> {
    RoomsPatients findByPatientId(int id);

}
