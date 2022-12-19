package com.spring.HospitalDemo.services;

public interface AuthoritiesService {
  public void save(String username);

  void deleteByUsername(String username);
}
