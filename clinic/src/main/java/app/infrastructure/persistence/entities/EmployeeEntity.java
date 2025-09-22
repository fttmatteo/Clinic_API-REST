package app.infrastructure.persistence.entities;

import jakarta.persistence.*;
import java.sql.Date;
import app.domain.model.emuns.Role;

@Entity
@Table(name="employees",
       uniqueConstraints = {
         @UniqueConstraint(name="uk_employee_document", columnNames="document"),
         @UniqueConstraint(name="uk_employee_username", columnNames="username")
       })
public class EmployeeEntity {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long employeeId;

  @Column(nullable=false) private int document;
  @Column(nullable=false, length=120) private String fullName;
  @Column(nullable=false) private Date birthDate;
  @Column(length=100) private String address;
  @Column(nullable=false) private int phoneNumber;
  @Column(nullable=false, length=255) private String email;

  @Enumerated(EnumType.STRING)
  @Column(nullable=false, length=40) private Role role;

  @Column(nullable=false, length=30) private String userName;
  @Column(nullable=false, length=100) private String password;

  public Long getEmployeeId() { return employeeId; }
  public void setId(Long employeeId) { this.employeeId = employeeId; }
  public int getDocument() { return document; }
  public void setDocument(int document) { this.document = document; }
  public String getFullName() { return fullName; }
  public void setFullName(String fullName) { this.fullName = fullName; }
  public Date getBirthDate() { return birthDate; }
  public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }
  public String getAddress() { return address; }
  public void setAddress(String address) { this.address = address; }
  public int getPhoneNumber() { return phoneNumber; }
  public void setPhoneNumber(int phoneNumber) { this.phoneNumber = phoneNumber; }
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }
  public Role getRole() { return role; }
  public void setRole(Role role) { this.role = role; }
  public String getUserName() { return userName; }
  public void setUserName(String userName) { this.userName = userName; }
  public String getPassword() { return password; }
  public void setPassword(String password) { this.password = password; }
}
