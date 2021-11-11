package com.raphaelabila.apidashboard.entity.apiuser;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "apiuser", catalog = "api_dashboard", schema = "users")
public class Apiuser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long apiuserid;
    @Column(name = "password",length=2147483647)
    private String password;
    @Column(name = "username",length=2147483647)
    private String username;
    private Date signupdate;
    private Boolean active;
    @Column(name = "email",length=2147483647)
    private String email;
    private Date activatedon;
    private Long activatedby;
    private Date deactivatedon;
    private Long deactivatedby;
    @Column(name = "contact",length=2147483647)
    private String contact;
    @Column(name = "notes",length=2147483647)
    private String notes;
    @Column(name = "status",length=2147483647)
    private String status;
    @Column(name = "organization",length=2147483647)
    private String organization;

    public Long getApiuserid() {
        return apiuserid;
    }
    public void setApiuserid(Long apiuserid) {
        this.apiuserid = apiuserid;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Date getSignupdate() {
        return signupdate;
    }
    public void setSignupdate(Date signupdate) {
        this.signupdate = signupdate;
    }
    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Date getActivatedon() {
        return activatedon;
    }
    public void setActivatedon(Date activatedon) {
        this.activatedon = activatedon;
    }
    public Long getActivatedby() {
        return activatedby;
    }
    public void setActivatedby(Long activatedby) {
        this.activatedby = activatedby;
    }
    public Date getDeactivatedon() {
        return deactivatedon;
    }
    public void setDeactivatedon(Date deactivatedon) {
        this.deactivatedon = deactivatedon;
    }
    public Long getDeactivatedby() {
        return deactivatedby;
    }
    public void setDeactivatedby(Long deactivatedby) {
        this.deactivatedby = deactivatedby;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getOrganization() {
        return organization;
    }
    public void setOrganization(String organization) {
        this.organization = organization;
    }
}
