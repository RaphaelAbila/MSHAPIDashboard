package com.raphaelabila.apidashboard.repository.apiuser;

import javax.transaction.Transactional;

import com.raphaelabila.apidashboard.entity.apiuser.Endpoint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface EndpointRepository extends JpaRepository<Endpoint, Long>{
    @Transactional
    @Modifying
    @Query(value = "UPDATE users.endpoint SET name=?2 ,url=?4, details=?3 WHERE endpointid=?1", nativeQuery = true)
    public int updateendpoint(Long id, String endpointname ,String details, String url);
    
    
}
