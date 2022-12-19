package com.spring.HospitalDemo.controller;

import com.spring.HospitalDemo.DTO.PatientsDTO;
import com.spring.HospitalDemo.entity.Patient;
import com.spring.HospitalDemo.entity.RoomsPatients;
import com.spring.HospitalDemo.services.PatientService;
import com.spring.HospitalDemo.services.RoomsService;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patients")
public class PatientController {

  @Autowired private PatientService patientService;

  @Autowired private RoomsService roomsService;

  @GetMapping("/list")
  public String findAllPatient(@RequestParam("username") String username, Model model) {
    List<Patient> patients = patientService.findPatientByDoc(username);

    model.addAttribute("patients", patients);
    return "home.html";
  }

  @GetMapping("/addform")
  public String addform(Model model) {
    PatientsDTO patientsDTO = new PatientsDTO();

    model.addAttribute("patient", patientsDTO);

    return "addpatient.html";
  }

  @PostMapping("/save")
  public String addPatient(
      @Valid @ModelAttribute("patient") PatientsDTO patientsDTO,
      @RequestParam("username") String username)
      throws MethodArgumentNotValidException, ConstraintViolationException, BindException {
    patientsDTO.setDoctorName(username);

    Patient patient = patientsDTO.toPatient();

    if (patient.getStatus().equals("treated")) {
      RoomsPatients room = roomsService.findRoomByPatId(patient.getId());
      if (room != null) {
        room.setPatientId(null);
        roomsService.updateRoom(Optional.of(room));
      }
    }
    patientService.addPatient(patient);
    return "redirect:/patients/list?username=" + username;
  }

  @GetMapping("/viewdet")
  public String viewdet(@RequestParam("patientId") int id, Model model) {
    Optional<Patient> patient = patientService.findPatient(id);

    RoomsPatients room = roomsService.findRoomByPatId(id);

    if (room == null) {
      room = new RoomsPatients();
    }
    if (patient.isEmpty()) patient = Optional.of(new Patient());
    model.addAttribute("patient", patient.get());
    model.addAttribute("room", room);

    return "viewpatient.html";
  }

  @GetMapping("/updateform")
  public String addform(@RequestParam("patientId") int id, Model model) {
    Optional<Patient> patient = patientService.findPatient(id);

    PatientsDTO patientsDTO = new PatientsDTO(patient);

    model.addAttribute("patient", patientsDTO);

    return "addpatient.html";
  }
}
