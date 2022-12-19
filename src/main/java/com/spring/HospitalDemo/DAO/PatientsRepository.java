package com.spring.HospitalDemo.DAO;

import com.spring.HospitalDemo.entity.Patient;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientsRepository extends JpaRepository<Patient, Integer> {

  public String PATIENTS_FOR_ROOMS =
      "SELECT p from Patient p where p.status='active' and p.id not in (select r.patientId from"
          + " RoomsPatients r where r.patientId is not null)";

  List<Patient> findByDoctorName(String doctorName);

  @Query(PATIENTS_FOR_ROOMS)
  List<Patient> findPatientForRooms();

  int deleteAllByDoctorName(String username);
}
