package com.raphaelabila.apidashboard.repository.apiuser;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import com.raphaelabila.apidashboard.entity.apiuser.Apiuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ApiUserRepository extends JpaRepository<Apiuser, Long> {
    @Query(value = "SELECT * FROM users.apiuser u WHERE u.username=?", nativeQuery = true)
    public Apiuser findByUsername(String username);

    @Query(value = "SELECT * FROM users.apiuser u WHERE u.status=? AND u.active=?", nativeQuery = true)
    public List<Apiuser> findUserWithStatus(String status, Boolean active);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users.apiuser   SET active=?1 ,status=?2, activatedon=?4,deactivatedon=?5 WHERE apiuserid =?3", nativeQuery = true)
    public int manageUser(Boolean active, String status, Long userid, Date activatedon, Date deactivatedon);

    @Transactional
    @Query(value = "SELECT COUNT(apiuserid) FROM users.apiuser WHERE status=?1 AND active=?2", nativeQuery = true)
    public Double countactiveusers(String status, Boolean active);
   
    @Query(value = "SELECT COUNT(apiuserid) FROM users.apiuser WHERE username=?1", nativeQuery = true)
    public int checkvaliduser(String username);

    @Query(value = "SELECT * FROM users.apiuser u WHERE u.password=? AND u.username=?", nativeQuery = true)
    public Apiuser getUserBypassword(String password, String username);
}
