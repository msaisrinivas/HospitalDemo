package com.spring.HospitalDemo.controller;

import com.spring.HospitalDemo.DTO.EmployeeDTO;
import com.spring.HospitalDemo.entity.Employee;
import com.spring.HospitalDemo.services.AuthoritiesService;
import com.spring.HospitalDemo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

  @Autowired private EmployeeService employeeService;

  @Autowired private AuthoritiesService authoritiesService;
  private Exception UserFoundException;

  @GetMapping("/myLoginPage")
  public String myLoginPage() {
    return "login.html";
  }

  @GetMapping("/")
  public String patient() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    String role = auth.getAuthorities().toString();

    if (role.equals("[ROLE_DOCTOR]")) {
      return "redirect:/patients/list?username=" + auth.getName();
    } else {
      return "redirect:/rooms/list";
    }
  }

  @GetMapping("/access")
  public String accessDenied() {
    return "access.html";
  }

  @GetMapping("/signIn")
  public String signIn(Model model) {
    model.addAttribute("employee", new EmployeeDTO());
    return "sign-in.html";
  }

  @PostMapping("/addEmp")
  public String addEmp(@ModelAttribute("employee") EmployeeDTO employeeDTO) throws Exception {
    Employee employee = employeeService.findByUsername(employeeDTO.getUsername());
    if (employee != null) {
      throw UserFoundException;
    }
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    employeeDTO.setPassword(encoder.encode(employeeDTO.getPassword()));
    employeeService.save(employeeDTO.toEmployee());
    authoritiesService.save(employeeDTO.getUsername());

    return "redirect:/myLoginPage?signIn";
  }
}
