package com.raphaelabila.apidashboard.entity.apiuser;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "apikey", catalog = "api_dashboard", schema = "users")
public class Apikey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long apikeyid;
    private Long apiuserid;
    @Column(name = "keyname",length=2147483647)
    private String keyname;
    @Column(name = "key",length=2147483647)
    private String key;
    Boolean actiive;
    @Column(name = "status",length=2147483647)
    private String status;
    Date dateactivated;
    Date datedeactivated;

    public Long getApikeyid() {
        return apikeyid;
    }
    public void setApikeyid(Long apikeyid) {
        this.apikeyid = apikeyid;
    }
    public Long getApiuserid() {
        return apiuserid;
    }
    public void setApiuserid(Long apiuserid) {
        this.apiuserid = apiuserid;
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
    public Boolean getActiive() {
        return actiive;
    }
    public void setActiive(Boolean actiive) {
        this.actiive = actiive;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
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
    
}
