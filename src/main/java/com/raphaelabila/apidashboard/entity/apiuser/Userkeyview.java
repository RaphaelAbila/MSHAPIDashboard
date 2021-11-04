/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raphaelabila.apidashboard.entity.apiuser;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Raphael
 */
@Entity
@Table(name = "userkeyview", catalog = "api_dashboard", schema = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userkeyview.findAll", query = "SELECT u FROM Userkeyview u")
    , @NamedQuery(name = "Userkeyview.findByApiuserid", query = "SELECT u FROM Userkeyview u WHERE u.apiuserid = :apiuserid")
    , @NamedQuery(name = "Userkeyview.findByPassword", query = "SELECT u FROM Userkeyview u WHERE u.password = :password")
    , @NamedQuery(name = "Userkeyview.findByUsername", query = "SELECT u FROM Userkeyview u WHERE u.username = :username")
    , @NamedQuery(name = "Userkeyview.findBySignupdate", query = "SELECT u FROM Userkeyview u WHERE u.signupdate = :signupdate")
    , @NamedQuery(name = "Userkeyview.findByActive", query = "SELECT u FROM Userkeyview u WHERE u.active = :active")
    , @NamedQuery(name = "Userkeyview.findByEmail", query = "SELECT u FROM Userkeyview u WHERE u.email = :email")
    , @NamedQuery(name = "Userkeyview.findByActivatedon", query = "SELECT u FROM Userkeyview u WHERE u.activatedon = :activatedon")
    , @NamedQuery(name = "Userkeyview.findByActivatedby", query = "SELECT u FROM Userkeyview u WHERE u.activatedby = :activatedby")
    , @NamedQuery(name = "Userkeyview.findByDeactivatedon", query = "SELECT u FROM Userkeyview u WHERE u.deactivatedon = :deactivatedon")
    , @NamedQuery(name = "Userkeyview.findByDeactivatedby", query = "SELECT u FROM Userkeyview u WHERE u.deactivatedby = :deactivatedby")
    , @NamedQuery(name = "Userkeyview.findByContact", query = "SELECT u FROM Userkeyview u WHERE u.contact = :contact")
    , @NamedQuery(name = "Userkeyview.findByNotes", query = "SELECT u FROM Userkeyview u WHERE u.notes = :notes")
    , @NamedQuery(name = "Userkeyview.findByStatus", query = "SELECT u FROM Userkeyview u WHERE u.status = :status")
    , @NamedQuery(name = "Userkeyview.findByApikeyid", query = "SELECT u FROM Userkeyview u WHERE u.apikeyid = :apikeyid")
    , @NamedQuery(name = "Userkeyview.findByUserid", query = "SELECT u FROM Userkeyview u WHERE u.userid = :userid")
    , @NamedQuery(name = "Userkeyview.findByKeyname", query = "SELECT u FROM Userkeyview u WHERE u.keyname = :keyname")
    , @NamedQuery(name = "Userkeyview.findByKey", query = "SELECT u FROM Userkeyview u WHERE u.key = :key")
    , @NamedQuery(name = "Userkeyview.findByKeyactive", query = "SELECT u FROM Userkeyview u WHERE u.keyactive = :keyactive")
    , @NamedQuery(name = "Userkeyview.findByKeystatus", query = "SELECT u FROM Userkeyview u WHERE u.keystatus = :keystatus")
    , @NamedQuery(name = "Userkeyview.findByDateactivated", query = "SELECT u FROM Userkeyview u WHERE u.dateactivated = :dateactivated")
    , @NamedQuery(name = "Userkeyview.findByDatedeactivated", query = "SELECT u FROM Userkeyview u WHERE u.datedeactivated = :datedeactivated")
    , @NamedQuery(name = "Userkeyview.findByEndpoints", query = "SELECT u FROM Userkeyview u WHERE u.endpoints = :endpoints")})
public class Userkeyview implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "apiuserid")
    @Id
    private BigInteger apiuserid;
    @Column(name = "password")
    private String password;
    @Column(name = "username")
    private String username;
    @Column(name = "signupdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date signupdate;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "email")
    private String email;
    @Column(name = "activatedon")
    @Temporal(TemporalType.TIMESTAMP)
    private Date activatedon;
    @Column(name = "activatedby")
    private BigInteger activatedby;
    @Column(name = "deactivatedon")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deactivatedon;
    @Column(name = "deactivatedby")
    private BigInteger deactivatedby;
    @Column(name = "contact")
    private String contact;
    @Column(name = "notes")
    private String notes;
    @Column(name = "status")
    private String status;
    @Column(name = "apikeyid")
    private BigInteger apikeyid;
    @Column(name = "userid")
    private BigInteger userid;
    @Column(name = "keyname")
    private String keyname;
    @Column(name = "key")
    private String key;
    @Column(name = "keyactive")
    private Boolean keyactive;
    @Column(name = "keystatus")
    private String keystatus;
    @Column(name = "dateactivated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateactivated;
    @Column(name = "datedeactivated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datedeactivated;
    @Column(name = "endpoints")
    private BigInteger endpoints;

    public Userkeyview() {
    }

    public BigInteger getApiuserid() {
        return apiuserid;
    }

    public void setApiuserid(BigInteger apiuserid) {
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

    public BigInteger getActivatedby() {
        return activatedby;
    }

    public void setActivatedby(BigInteger activatedby) {
        this.activatedby = activatedby;
    }

    public Date getDeactivatedon() {
        return deactivatedon;
    }

    public void setDeactivatedon(Date deactivatedon) {
        this.deactivatedon = deactivatedon;
    }

    public BigInteger getDeactivatedby() {
        return deactivatedby;
    }

    public void setDeactivatedby(BigInteger deactivatedby) {
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

    public BigInteger getApikeyid() {
        return apikeyid;
    }

    public void setApikeyid(BigInteger apikeyid) {
        this.apikeyid = apikeyid;
    }

    public BigInteger getUserid() {
        return userid;
    }

    public void setUserid(BigInteger userid) {
        this.userid = userid;
    }

    public String getKeyname() {
        return keyname;
    }

    public void setKeyname(String keyname) {
        this.keyname = keyname;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Boolean getKeyactive() {
        return keyactive;
    }

    public void setKeyactive(Boolean keyactive) {
        this.keyactive = keyactive;
    }

    public String getKeystatus() {
        return keystatus;
    }

    public void setKeystatus(String keystatus) {
        this.keystatus = keystatus;
    }

    public Date getDateactivated() {
        return dateactivated;
    }

    public void setDateactivated(Date dateactivated) {
        this.dateactivated = dateactivated;
    }

    public Date getDatedeactivated() {
        return datedeactivated;
    }

    public void setDatedeactivated(Date datedeactivated) {
        this.datedeactivated = datedeactivated;
    }

    public BigInteger getEndpoints() {
        return endpoints;
    }

    public void setEndpoints(BigInteger endpoints) {
        this.endpoints = endpoints;
    }

}
