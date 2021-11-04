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
@Table(name = "apirequestlogs", catalog = "api_dashboard", schema = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Apirequestlogs.findAll", query = "SELECT a FROM Apirequestlogs a")
    , @NamedQuery(name = "Apirequestlogs.findByApirequestlogid", query = "SELECT a FROM Apirequestlogs a WHERE a.apirequestlogid = :apirequestlogid")
    , @NamedQuery(name = "Apirequestlogs.findByLoggedate", query = "SELECT a FROM Apirequestlogs a WHERE a.loggedate = :loggedate")
    , @NamedQuery(name = "Apirequestlogs.findByRequestedby", query = "SELECT a FROM Apirequestlogs a WHERE a.requestedby = :requestedby")
    , @NamedQuery(name = "Apirequestlogs.findByRequestingid", query = "SELECT a FROM Apirequestlogs a WHERE a.requestingid = :requestingid")
    , @NamedQuery(name = "Apirequestlogs.findByRequestingkey", query = "SELECT a FROM Apirequestlogs a WHERE a.requestingkey = :requestingkey")
    , @NamedQuery(name = "Apirequestlogs.findByEndpointid", query = "SELECT a FROM Apirequestlogs a WHERE a.endpointid = :endpointid")
    , @NamedQuery(name = "Apirequestlogs.findByEndpoint", query = "SELECT a FROM Apirequestlogs a WHERE a.endpoint = :endpoint")
    , @NamedQuery(name = "Apirequestlogs.findByStatus", query = "SELECT a FROM Apirequestlogs a WHERE a.status = :status")
    , @NamedQuery(name = "Apirequestlogs.findByDescription", query = "SELECT a FROM Apirequestlogs a WHERE a.description = :description")
    , @NamedQuery(name = "Apirequestlogs.findByApikeyid", query = "SELECT a FROM Apirequestlogs a WHERE a.apikeyid = :apikeyid")})
public class Apirequestlogs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "apirequestlogid", nullable = false)
    private Long apirequestlogid;
    @Column(name = "loggedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date loggedate;
    @Column(name = "requestedby", length = 2147483647)
    private String requestedby;
    @Column(name = "requestingid")
    private BigInteger requestingid;
    @Column(name = "requestingkey", length = 2147483647)
    private String requestingkey;
    @Column(name = "endpointid")
    private BigInteger endpointid;
    @Column(name = "endpoint", length = 2147483647)
    private String endpoint;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "description", length = 2147483647)
    private String description;
    @Column(name = "apikeyid")
    private BigInteger apikeyid;

    public Apirequestlogs() {
    }

    public Apirequestlogs(Long apirequestlogid) {
        this.apirequestlogid = apirequestlogid;
    }

    public Long getApirequestlogid() {
        return apirequestlogid;
    }

    public void setApirequestlogid(Long apirequestlogid) {
        this.apirequestlogid = apirequestlogid;
    }

    public Date getLoggedate() {
        return loggedate;
    }

    public void setLoggedate(Date loggedate) {
        this.loggedate = loggedate;
    }

    public String getRequestedby() {
        return requestedby;
    }

    public void setRequestedby(String requestedby) {
        this.requestedby = requestedby;
    }

    public BigInteger getRequestingid() {
        return requestingid;
    }

    public void setRequestingid(BigInteger requestingid) {
        this.requestingid = requestingid;
    }

    public String getRequestingkey() {
        return requestingkey;
    }

    public void setRequestingkey(String requestingkey) {
        this.requestingkey = requestingkey;
    }

    public BigInteger getEndpointid() {
        return endpointid;
    }

    public void setEndpointid(BigInteger endpointid) {
        this.endpointid = endpointid;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigInteger getApikeyid() {
        return apikeyid;
    }

    public void setApikeyid(BigInteger apikeyid) {
        this.apikeyid = apikeyid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (apirequestlogid != null ? apirequestlogid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Apirequestlogs)) {
            return false;
        }
        Apirequestlogs other = (Apirequestlogs) object;
        if ((this.apirequestlogid == null && other.apirequestlogid != null) || (this.apirequestlogid != null && !this.apirequestlogid.equals(other.apirequestlogid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.raphaelabila.apidashboard.entity.apiuser.Apirequestlogs[ apirequestlogid=" + apirequestlogid + " ]";
    }

}
