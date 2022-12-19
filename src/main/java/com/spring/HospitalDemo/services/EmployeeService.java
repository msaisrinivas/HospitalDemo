package com.spring.HospitalDemo.services;

import com.spring.HospitalDemo.entity.Employee;
import java.util.List;

public interface EmployeeService {
  public void save(Employee employee);

  Employee findByUsername(String username);

  List<Employee> findAllForAdmin();

  void delete(Employee employee);
}
