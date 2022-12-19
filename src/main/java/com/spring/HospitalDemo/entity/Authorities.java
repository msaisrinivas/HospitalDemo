package com.spring.HospitalDemo.entity;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
public class Authorities {

  @Id
  @Column(name = "username")
  private String userName;

  @Column(name = "authority")
  private String authority;

  public Authorities() {}

  public Authorities(String userName, String authority) {
    this.userName = userName;
    this.authority = authority;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getAuthority() {
    return authority;
  }

  public void setAuthority(String authority) {
    this.authority = authority;
  }

  @Override
  public String toString() {
    return "Authorities{"
        + "userName='"
        + userName
        + '\''
        + ", Authority='"
        + authority
        + '\''
        + '}';
  }
}
