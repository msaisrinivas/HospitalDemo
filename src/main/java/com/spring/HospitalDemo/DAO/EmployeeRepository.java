package com.spring.HospitalDemo.DAO;

import com.spring.HospitalDemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    Employee getByUsername(String username);

    @Query("SELECT e FROM Employee e where e.username in (SELECT a.userName FROM Authorities a where a.authority='ROLE_DOCTOR')")
    List<Employee> findAllForAdmin();
}
