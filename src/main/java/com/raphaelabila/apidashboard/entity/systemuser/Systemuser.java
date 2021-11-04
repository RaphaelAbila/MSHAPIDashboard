package com.raphaelabila.apidashboard.entity.systemuser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "systemuser", catalog = "api_dashboard", schema = "users")
public class Systemuser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;
    @Column(name = "username",length=2147483647)
    private String username;
    @Column(name = "firstname",length=2147483647)
    private String firstname;
    @Column(name = "lastname",length=2147483647)
    private String lastname;
    @Column(name = "othername",length=2147483647)
    private String othername;
    @Column(name = "email",length=2147483647)
    private String email;
    @Column(name = "password",length=2147483647)
    private String password;
    
    public Long getUserid() {
        return userid;
    }
    public void setUserid(Long userid) {
        this.userid = userid;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getOthername() {
        return othername;
    }
    public void setOthername(String othername) {
        this.othername = othername;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
}
