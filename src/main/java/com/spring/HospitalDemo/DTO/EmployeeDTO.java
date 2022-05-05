package com.spring.HospitalDemo.DTO;

import com.spring.HospitalDemo.entity.Employee;
import lombok.Data;

import javax.persistence.Column;

@Data
public class EmployeeDTO {
    private String username;
    private String password;
    private int enabled =1;

    public EmployeeDTO() {
    }

    public EmployeeDTO(Employee employee) {
        this.username = employee.getUsername();
        this.password = employee.getPassword();
        this.enabled = employee.getEnabled();
    }

    public Employee toEmployee()
    {
        return new Employee(username,password,enabled);
    }
}
