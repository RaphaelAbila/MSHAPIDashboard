package com.raphaelabila.apidashboard.repository.apiuser;

import java.util.Date;

import javax.transaction.Transactional;

import com.raphaelabila.apidashboard.entity.apiuser.Apirequestlogs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ApirequestlogsRepository extends JpaRepository<Apirequestlogs, Long>{

    @Transactional
    @Query(value = "SELECT COUNT(u.apirequestlogid) FROM users.apirequestlogs u WHERE u.status=?", nativeQuery = true)
    public Double countlogs(Boolean status);

    @Transactional
    @Query(value = "SELECT COUNT(u.apirequestlogid) FROM users.apirequestlogs u WHERE u.status=?1 AND DATE(u.loggedate)>=?2 AND DATE(u.loggedate)<=?3", nativeQuery = true)
    public Long countlogstatistics(Boolean status, Date statedate, Date enddate);
    
}
