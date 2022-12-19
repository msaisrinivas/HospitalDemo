package com.spring.HospitalDemo.entity;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {

  @Id
  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "enabled")
  private int enabled = 1;

  public Employee() {}

  public Employee(String username, String password, int enabled) {
    this.username = username;
    this.password = password;
    this.enabled = enabled;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getEnabled() {
    return enabled;
  }

  public void setEnabled(int enabled) {
    this.enabled = enabled;
  }

  @Override
  public String toString() {
    return "Employee{"
        + "username='"
        + username
        + '\''
        + ", password='"
        + password
        + '\''
        + ", enabled="
        + enabled
        + '}';
  }
}
