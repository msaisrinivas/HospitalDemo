package com.spring.HospitalDemo.controller;

import com.spring.HospitalDemo.entity.*;
import com.spring.HospitalDemo.services.*;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/rooms")
public class RoomsController {

  @Autowired private RoomsService roomsService;

  @Autowired private RoomService roomService;

  @Autowired private PatientService patientService;

  @Autowired private EmployeeService employeeService;

  @Autowired private AuthoritiesService authoritiesService;

  @GetMapping("/list")
  public String findAllRooms(Model model) {
    List<RoomsPatients> rooms = roomsService.findAllRoooms();

    List<Rooms> roomsList = roomService.findAll();

    model.addAttribute("rooms", rooms);
    model.addAttribute("roomsList", roomsList);

    return "rooms.html";
  }

  @GetMapping("/allot")
  public String allotRooms(@RequestParam("id") int id, Model model) {
    model.addAttribute("roomId", id);

    List<Patient> patients = patientService.findPatientforRooms();

    model.addAttribute("patients", patients);

    return "roomallot.html";
  }

  @GetMapping("/select")
  public String selectPatient(
      @RequestParam("patientId") int patientId, @RequestParam("id") int id) {
    Optional<RoomsPatients> room = roomsService.findRoomById(id);
    if (!room.isEmpty()) {
      if (patientId == -1) {
        room.get().setPatientId(null);
      } else {
        room.get().setPatientId(patientId);
      }
      roomsService.updateRoom(room);
    }
    return "redirect:/rooms/list";
  }

  @GetMapping("/doctor")
  public String getDoctors(Model model) {
    List<Employee> employees = employeeService.findAllForAdmin();

    model.addAttribute("doctors", employees);

    return "doctors.html";
  }

  @GetMapping("/docDelete")
  public String docDelete(@RequestParam("username") String username) {
    patientService.deleteByDoc(username);
    authoritiesService.deleteByUsername(username);
    employeeService.delete(employeeService.findByUsername(username));
    return "redirect:/rooms/doctor";
  }
}
