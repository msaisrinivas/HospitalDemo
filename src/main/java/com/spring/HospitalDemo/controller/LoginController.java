package com.spring.HospitalDemo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {


    @GetMapping("/myLoginPage")
    public String myLoginPage()
    {
        return "login.html";
    }

    @GetMapping("/")
    public String patient()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String role = auth.getAuthorities().toString();

        if(role.equals("[ROLE_DOCTOR]"))
        {
            return "redirect:/patients/list?username="+auth.getName();
        }
        else
        {
            return "redirect:/rooms/list";
        }
    }

    @GetMapping("/access")
    public String accessDenied()
    {
        return "access.html";
    }
}
