package com.raphaelabila.apidashboard.service.systemuser;

import com.raphaelabila.apidashboard.config.CustomUserDetails;
import com.raphaelabila.apidashboard.entity.systemuser.Systemuser;
import com.raphaelabila.apidashboard.repository.systemuser.SystemUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private SystemUserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Systemuser user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not Found");
        }
        return new CustomUserDetails(user);
    }

}
