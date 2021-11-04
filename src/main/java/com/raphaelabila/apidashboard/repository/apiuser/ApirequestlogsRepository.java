package com.raphaelabila.apidashboard.repository.apiuser;

import javax.transaction.Transactional;

import com.raphaelabila.apidashboard.entity.apiuser.Apirequestlogs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ApirequestlogsRepository extends JpaRepository<Apirequestlogs, Long>{

    @Transactional
    @Query(value = "SELECT COUNT(u.apirequestlogid) FROM users.apirequestlogs u WHERE u.status=?", nativeQuery = true)
    public Double countlogs(Boolean status);
    
}
