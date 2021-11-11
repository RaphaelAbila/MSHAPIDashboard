package com.raphaelabila.apidashboard.repository.apiuser;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import com.raphaelabila.apidashboard.entity.apiuser.Apikey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ApiKeyRepository extends JpaRepository<Apikey, Long>{
    @Query(value="SELECT * FROM users.apikey u WHERE u.keyname=?",nativeQuery=true)
    public Apikey findBykeyname(String keyname);

    @Query(value="SELECT * FROM users.apiuser u WHERE u.status=:status AND u.actiive=:active",nativeQuery=true)
    public List<Apikey> findKeyWithStatus(String status , Boolean active);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users.apikey   SET actiive=?1 ,status=?2 ,dateactivated=?4, datedeactivated=?5 WHERE apikeyid =?3", nativeQuery = true)
    public int manageKey(Boolean active, String status, Long userid, Date activated, Date deactivated );

    @Transactional
    @Query(value = "SELECT COUNT(apikeyid) FROM users.apikey WHERE status=?1 AND actiive=?2", nativeQuery = true)
    public int countactivekeys(String status, Boolean active);

    @Query(value="SELECT u.key FROM users.apikey u WHERE u.apikeyid=?",nativeQuery=true)
    public String findbykeyid(Long apikeyid);

    @Query(value="SELECT COUNT(u.apikeyid) FROM users.apikey u WHERE u.key=?",nativeQuery=true)
    public int findbykey(String key);
    
}
