package com.raphaelabila.apidashboard.repository.apiuser;

import javax.transaction.Transactional;

import com.raphaelabila.apidashboard.entity.apiuser.Endpointuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface EndpointuserRepository extends JpaRepository<Endpointuser, Long>{
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM users.endpointuser WHERE endpointuserid=?1", nativeQuery = true)
    public int deleteByUserId(Long endpointuserid);
    
    @Query(value = "SELECT COUNT(u.endpointuserid) FROM users.endpointuser u WHERE u.endpointid=?1 AND u.apiuserid=?2", nativeQuery = true)
    public int countAvailable(Long endpointid,Long apiuserid);
    
}
