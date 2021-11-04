package com.raphaelabila.apidashboard.repository.apiuser;

import java.util.List;

import javax.transaction.Transactional;

import com.raphaelabila.apidashboard.entity.apiuser.Apiuserendpoints;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ApiuserendpointsRepository extends JpaRepository<Apiuserendpoints, Long>{
 
    @Transactional
    @Query(value = "SELECT * FROM users.apiuserendpoints u WHERE u.apiuserid=?", nativeQuery = true)
    public List<Apiuserendpoints> findbyuserId(Long userid);

    @Transactional
    @Query(value = "SELECT COUNT(u.endpointid) FROM users.apiuserendpoints u WHERE  u.name=?1  AND u.username=?2", nativeQuery = true)
    public int checkendpointaccess(String endpoint, String username);
    
    
}
