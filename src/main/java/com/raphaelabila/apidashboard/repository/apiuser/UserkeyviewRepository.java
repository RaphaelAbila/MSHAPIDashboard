package com.raphaelabila.apidashboard.repository.apiuser;

import java.util.List;

import com.raphaelabila.apidashboard.entity.apiuser.Userkeyview;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserkeyviewRepository extends JpaRepository<Userkeyview, Long>{
    @Query(value="SELECT * FROM users.userkeyview u WHERE u.username=?",nativeQuery=true)
    public Userkeyview findByUsername(String username);

    @Query(value="SELECT * FROM users.userkeyview u WHERE u.status=? AND u.active=?",nativeQuery=true)
    public List<Userkeyview> findUserWithStatus(String status , Boolean active);

    @Query(value="SELECT * FROM users.userkeyview u WHERE u.keystatus=? AND u.keyactive=?",nativeQuery=true)
    public List<Userkeyview> findKeyWithStatus(String status , Boolean active);

    @Query(value="SELECT * FROM users.userkeyview u WHERE u.username=? AND u.keystatus=? AND u.keyactive=?",nativeQuery=true)
    public List<Userkeyview> findByBoolStatus(String username,String keystatus, Boolean keyactive);

    @Query(value="SELECT * FROM users.userkeyview u WHERE  u.active=?1 AND u.keyactive=?1 AND u.status=?2 AND u.keystatus=?2",nativeQuery=true)
    public List<Userkeyview> fetchactiveuserkey(Boolean keyactive, String status);

    @Query(value="SELECT * FROM users.userkeyview u WHERE u.apikeyid=?",nativeQuery=true)
    public Userkeyview findbykeyid(Long apikeyid);

    @Query(value = "SELECT COUNT(u.apiuserid) FROM users.userkeyview u WHERE  u.keyactive=?1  AND u.keystatus=?2 AND u.key=?3 AND u.username=?4", nativeQuery = true)
    public int checkactivekey(Boolean keyactive, String keystatus, String key,String username);

    @Query(value = "SELECT COUNT(u.apiuserid) FROM users.userkeyview u WHERE  u.active=?1  AND u.status=?2  AND u.username=?3", nativeQuery = true)
    public int checkactiveuser(Boolean active, String status,String username);
}
