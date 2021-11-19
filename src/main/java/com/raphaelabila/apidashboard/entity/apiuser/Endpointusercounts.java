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
@Table(name = "endpointusercounts", catalog = "api_dashboard", schema = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Endpointusercounts.findAll", query = "SELECT e FROM Endpointusercounts e")
    , @NamedQuery(name = "Endpointusercounts.findByEndpointid", query = "SELECT e FROM Endpointusercounts e WHERE e.endpointid = :endpointid")
    , @NamedQuery(name = "Endpointusercounts.findByName", query = "SELECT e FROM Endpointusercounts e WHERE e.name = :name")
    , @NamedQuery(name = "Endpointusercounts.findByUrl", query = "SELECT e FROM Endpointusercounts e WHERE e.url = :url")
    , @NamedQuery(name = "Endpointusercounts.findByDateadded", query = "SELECT e FROM Endpointusercounts e WHERE e.dateadded = :dateadded")
    , @NamedQuery(name = "Endpointusercounts.findByDetails", query = "SELECT e FROM Endpointusercounts e WHERE e.details = :details")
    , @NamedQuery(name = "Endpointusercounts.findByAddedby", query = "SELECT e FROM Endpointusercounts e WHERE e.addedby = :addedby")
    , @NamedQuery(name = "Endpointusercounts.findByUsers", query = "SELECT e FROM Endpointusercounts e WHERE e.users = :users")})
public class Endpointusercounts implements Serializable {

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
    @Column(name = "users")
    private BigInteger users;

    public Endpointusercounts() {
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

    public BigInteger getUsers() {
        return users;
    }

    public void setUsers(BigInteger users) {
        this.users = users;
    }

}
