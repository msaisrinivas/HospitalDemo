package com.spring.HospitalDemo.controller;

import com.spring.HospitalDemo.entity.Patient;
import com.spring.HospitalDemo.entity.RoomsPatients;
import com.spring.HospitalDemo.services.PatientService;
import com.spring.HospitalDemo.services.RoomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/rooms")
public class RoomsController {

    @Autowired
    private RoomsService roomsService;

    @Autowired
    private PatientService patientService;

    @GetMapping("/list")
    public String findAllRooms(Model model)
    {
        List<RoomsPatients> rooms= roomsService.findAllRoooms();

        model.addAttribute("rooms",rooms);

        return "rooms.html";
    }

    @GetMapping("/allot")
    public String allotRooms(@RequestParam("id") int id,Model model)
    {
        model.addAttribute("roomId",id);

        List<Patient> patients = patientService.findPatientforRooms();

        model.addAttribute("patients",patients);

        return "roomallot.html";
    }

    @GetMapping("/select")
    public String selectPatient(
            @RequestParam("patientId") int patientId,
            @RequestParam("id") int id)
    {
        Optional<RoomsPatients> room= roomsService.findRoomById(id);
        if(!room.isEmpty())
        {
            if(patientId==-1)
            {
                room.get().setPatientId(null);
            }
            else
            {
                room.get().setPatientId(patientId);
            }
            roomsService.updateRoom(room);
        }
        return "redirect:/rooms/list";
    }
}
