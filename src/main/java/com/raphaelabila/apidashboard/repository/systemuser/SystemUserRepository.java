package com.raphaelabila.apidashboard.repository.systemuser;

import com.raphaelabila.apidashboard.entity.systemuser.Systemuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SystemUserRepository extends JpaRepository<Systemuser, Long> {
    @Query(value="SELECT * FROM users.systemuser u WHERE u.username=?1",nativeQuery=true)
    public Systemuser findByUsername(String username);
    
}
