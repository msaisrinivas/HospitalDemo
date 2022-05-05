package com.spring.HospitalDemo.services;

import com.spring.HospitalDemo.DAO.AuthoritiesRepository;
import com.spring.HospitalDemo.DAO.EmployeeRepository;
import com.spring.HospitalDemo.entity.Authorities;
import com.spring.HospitalDemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
//        Authorities authorities = new Authorities(username, "ROLE_DOCTOR");
//
//        authoritiesRepository.save(authorities);
    }

    @Override
    public Employee findByUsername(String username) {
        return employeeRepository.getByUsername(username);
    }

    @Override
    public List<Employee> findAllForAdmin() {
        return employeeRepository.findAllForAdmin();
    }

    @Override
    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }
}
