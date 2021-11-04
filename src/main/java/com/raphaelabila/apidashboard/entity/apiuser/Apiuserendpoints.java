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
@Table(name = "apiuserendpoints", catalog = "api_dashboard", schema = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Apiuserendpoints.findAll", query = "SELECT a FROM Apiuserendpoints a")
    , @NamedQuery(name = "Apiuserendpoints.findByEndpointid", query = "SELECT a FROM Apiuserendpoints a WHERE a.endpointid = :endpointid")
    , @NamedQuery(name = "Apiuserendpoints.findByName", query = "SELECT a FROM Apiuserendpoints a WHERE a.name = :name")
    , @NamedQuery(name = "Apiuserendpoints.findByUrl", query = "SELECT a FROM Apiuserendpoints a WHERE a.url = :url")
    , @NamedQuery(name = "Apiuserendpoints.findByDateadded", query = "SELECT a FROM Apiuserendpoints a WHERE a.dateadded = :dateadded")
    , @NamedQuery(name = "Apiuserendpoints.findByDetails", query = "SELECT a FROM Apiuserendpoints a WHERE a.details = :details")
    , @NamedQuery(name = "Apiuserendpoints.findByAddedby", query = "SELECT a FROM Apiuserendpoints a WHERE a.addedby = :addedby")
    , @NamedQuery(name = "Apiuserendpoints.findByEndpointuserid", query = "SELECT a FROM Apiuserendpoints a WHERE a.endpointuserid = :endpointuserid")
    , @NamedQuery(name = "Apiuserendpoints.findByApiuserid", query = "SELECT a FROM Apiuserendpoints a WHERE a.apiuserid = :apiuserid")
    , @NamedQuery(name = "Apiuserendpoints.findByStatus", query = "SELECT a FROM Apiuserendpoints a WHERE a.status = :status")
    , @NamedQuery(name = "Apiuserendpoints.findByActive", query = "SELECT a FROM Apiuserendpoints a WHERE a.active = :active")
    , @NamedQuery(name = "Apiuserendpoints.findByPassword", query = "SELECT a FROM Apiuserendpoints a WHERE a.password = :password")
    , @NamedQuery(name = "Apiuserendpoints.findByUsername", query = "SELECT a FROM Apiuserendpoints a WHERE a.username = :username")
    , @NamedQuery(name = "Apiuserendpoints.findByEmail", query = "SELECT a FROM Apiuserendpoints a WHERE a.email = :email")
    , @NamedQuery(name = "Apiuserendpoints.findByContact", query = "SELECT a FROM Apiuserendpoints a WHERE a.contact = :contact")})
public class Apiuserendpoints implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "endpointid")
    @Id
    private BigInteger endpointid;
    @Column(name = "name")
    private String name;
    @Column(name = "url")
    private String url;
    @Column(name = "dateadded")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateadded;
    @Column(name = "details")
    private String details;
    @Column(name = "addedby")
    private BigInteger addedby;
    @Column(name = "endpointuserid")
    private BigInteger endpointuserid;
    @Column(name = "apiuserid")
    private BigInteger apiuserid;
    @Column(name = "status")
    private Character status;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "password")
    private String password;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "contact")
    private String contact;

    public Apiuserendpoints() {
    }

    public BigInteger getEndpointid() {
        return endpointid;
    }

    public void setEndpointid(BigInteger endpointid) {
        this.endpointid = endpointid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getDateadded() {
        return dateadded;
    }

    public void setDateadded(Date dateadded) {
        this.dateadded = dateadded;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public BigInteger getAddedby() {
        return addedby;
    }

    public void setAddedby(BigInteger addedby) {
        this.addedby = addedby;
    }

    public BigInteger getEndpointuserid() {
        return endpointuserid;
    }

    public void setEndpointuserid(BigInteger endpointuserid) {
        this.endpointuserid = endpointuserid;
    }

    public BigInteger getApiuserid() {
        return apiuserid;
    }

    public void setApiuserid(BigInteger apiuserid) {
        this.apiuserid = apiuserid;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

}
