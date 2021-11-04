/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raphaelabila.apidashboard.entity.apiuser;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Raphael
 */
@Entity
@Table(name = "endpointuser", catalog = "api_dashboard", schema = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Endpointuser.findAll", query = "SELECT e FROM Endpointuser e")
    , @NamedQuery(name = "Endpointuser.findByEndpointuserid", query = "SELECT e FROM Endpointuser e WHERE e.endpointuserid = :endpointuserid")
    , @NamedQuery(name = "Endpointuser.findByStatus", query = "SELECT e FROM Endpointuser e WHERE e.status = :status")
    , @NamedQuery(name = "Endpointuser.findByActive", query = "SELECT e FROM Endpointuser e WHERE e.active = :active")
    , @NamedQuery(name = "Endpointuser.findByApiuserid", query = "SELECT e FROM Endpointuser e WHERE e.apiuserid = :apiuserid")
    , @NamedQuery(name = "Endpointuser.findByEndpointid", query = "SELECT e FROM Endpointuser e WHERE e.endpointid = :endpointid")})
public class Endpointuser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "endpointuserid")
    private Long endpointuserid;
    @Column(name = "status")
    private Character status;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "apiuserid")
    private BigInteger apiuserid;
    @Column(name = "endpointid")
    private BigInteger endpointid;

    public Endpointuser() {
    }

    public Endpointuser(Long endpointuserid) {
        this.endpointuserid = endpointuserid;
    }

    public Long getEndpointuserid() {
        return endpointuserid;
    }

    public void setEndpointuserid(Long endpointuserid) {
        this.endpointuserid = endpointuserid;
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

    public BigInteger getApiuserid() {
        return apiuserid;
    }

    public void setApiuserid(BigInteger apiuserid) {
        this.apiuserid = apiuserid;
    }

    public BigInteger getEndpointid() {
        return endpointid;
    }

    public void setEndpointid(BigInteger endpointid) {
        this.endpointid = endpointid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (endpointuserid != null ? endpointuserid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Endpointuser)) {
            return false;
        }
        Endpointuser other = (Endpointuser) object;
        if ((this.endpointuserid == null && other.endpointuserid != null) || (this.endpointuserid != null && !this.endpointuserid.equals(other.endpointuserid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.raphaelabila.apidashboard.entity.apiuser.Endpointuser[ endpointuserid=" + endpointuserid + " ]";
    }

}
