package com.spring.HospitalDemo.controller;

import com.spring.HospitalDemo.entity.Patient;
import com.spring.HospitalDemo.entity.RoomsPatients;
import com.spring.HospitalDemo.services.PatientService;
import com.spring.HospitalDemo.services.RoomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private RoomsService roomsService;


    @GetMapping("/list")
    public String findAllPatient(@RequestParam("username") String username, Model model)
    {
        List<Patient> patients = patientService.findPatientByDoc(username);

        model.addAttribute("patients",patients);
        return "home.html";
    }

    @GetMapping("/addform")
    public String addform(Model model)
    {
        Patient patient = new Patient();

        model.addAttribute("patient",patient);

        return "addpatient.html";
    }

    @PostMapping("/save")
    public String addPatient(@ModelAttribute("patient") Patient patient,@RequestParam("username") String username)
    {
        patient.setDoctorName(username);

        patientService.addPatient( patient);
        System.out.println("DONe in controller");
        if(patient.getStatus().equals("treated"))
        {
            RoomsPatients room = roomsService.findRoomByPatId(patient.getId());
            if(room != null)
            {
                room.setPatientId(null);
                roomsService.updateRoom(Optional.of(room));
            }
        }
        return "redirect:/patients/list?username="+username;
    }

    @GetMapping("/viewdet")
    public String viewdet(@RequestParam("patientId") int id,Model model)
    {
        Optional<Patient> patient= patientService.findPatient(id);

        RoomsPatients room = roomsService.findRoomByPatId(id);;

        if(room == null)
        {
            room = new RoomsPatients();
        }
        model.addAttribute("patient",patient.get());
        model.addAttribute("room",room);

        return "viewpatient.html";
    }

    @GetMapping("/updateform")
    public String addform(@RequestParam("patientId") int id,Model model)
    {
        Optional<Patient> patient= patientService.findPatient(id);

        model.addAttribute("patient",patient);

        return "addpatient.html";
    }

}
