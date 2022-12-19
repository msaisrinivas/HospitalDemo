package com.spring.HospitalDemo.DAO;

import com.spring.HospitalDemo.entity.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Integer> {

  @Query("SELECT a FROM Authorities a WHERE a.userName=?1")
  Authorities findByUsername(String username);

  int deleteByUserName(String username);
}
