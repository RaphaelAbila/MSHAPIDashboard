/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raphaelabila.apidashboard.entity.apiuser;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "endpoint", catalog = "api_dashboard", schema = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Endpoint.findAll", query = "SELECT e FROM Endpoint e")
    , @NamedQuery(name = "Endpoint.findByEndpointid", query = "SELECT e FROM Endpoint e WHERE e.endpointid = :endpointid")
    , @NamedQuery(name = "Endpoint.findByName", query = "SELECT e FROM Endpoint e WHERE e.name = :name")
    , @NamedQuery(name = "Endpoint.findByUrl", query = "SELECT e FROM Endpoint e WHERE e.url = :url")
    , @NamedQuery(name = "Endpoint.findByDateadded", query = "SELECT e FROM Endpoint e WHERE e.dateadded = :dateadded")
    , @NamedQuery(name = "Endpoint.findByDetails", query = "SELECT e FROM Endpoint e WHERE e.details = :details")
    , @NamedQuery(name = "Endpoint.findByAddedby", query = "SELECT e FROM Endpoint e WHERE e.addedby = :addedby")})
public class Endpoint implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "endpointid")
    private Long endpointid;
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

    public Endpoint() {
    }

    public Endpoint(Long endpointid) {
        this.endpointid = endpointid;
    }

    public Long getEndpointid() {
        return endpointid;
    }

    public void setEndpointid(Long endpointid) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (endpointid != null ? endpointid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Endpoint)) {
            return false;
        }
        Endpoint other = (Endpoint) object;
        if ((this.endpointid == null && other.endpointid != null) || (this.endpointid != null && !this.endpointid.equals(other.endpointid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.raphaelabila.apidashboard.entity.apiuser.Endpoint[ endpointid=" + endpointid + " ]";
    }

}
