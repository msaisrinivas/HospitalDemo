package com.spring.HospitalDemo.services;

import com.spring.HospitalDemo.DAO.AuthoritiesRepository;
import com.spring.HospitalDemo.entity.Authorities;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthoritiesServiceImpl implements AuthoritiesService {

  @Autowired private AuthoritiesRepository authoritiesRepository;

  @Override
  public void save(String username) {

    Authorities authorities = new Authorities(username, "ROLE_DOCTOR");

    authoritiesRepository.save(authorities);
  }

  @Transactional
  @Override
  public void deleteByUsername(String username) {
    int d_rec = authoritiesRepository.deleteByUserName(username);
  }
}
