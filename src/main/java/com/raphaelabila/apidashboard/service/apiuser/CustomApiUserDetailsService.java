package com.raphaelabila.apidashboard.service.apiuser;

import com.raphaelabila.apidashboard.config.CustomApiUserDetails;
import com.raphaelabila.apidashboard.entity.apiuser.Apiuser;
import com.raphaelabila.apidashboard.repository.apiuser.ApiUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomApiUserDetailsService implements UserDetailsService{
    @Autowired
    private ApiUserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Apiuser user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not Found");
        }
        return new CustomApiUserDetails(user);
    }
    
}
