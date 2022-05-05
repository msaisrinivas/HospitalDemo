package com.spring.HospitalDemo.services;

import com.spring.HospitalDemo.entity.Authorities;

public interface AuthoritiesService {
    public void save(String username);

    void deleteByUsername(String username);
}
