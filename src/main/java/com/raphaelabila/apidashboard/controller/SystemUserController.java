package com.raphaelabila.apidashboard.controller;

import java.util.List;

import com.raphaelabila.apidashboard.entity.systemuser.Systemuser;
import com.raphaelabila.apidashboard.repository.systemuser.SystemUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class SystemUserController {
    @Autowired
    SystemUserRepository systemuserrepo;

@GetMapping("/")
public String dashboardHome(){
    return "login";
}

@PostMapping("/register")
public String processRegister(Systemuser user) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String encodedPassword = passwordEncoder.encode(user.getPassword());
    user.setPassword(encodedPassword);
    user.setOthername("User");
     
    systemuserrepo.saveAndFlush(user);
     
    return "login";
}

@GetMapping("/users")
public String listUsers(Model model) {
    List<Systemuser> listUsers = systemuserrepo.findAll();
    model.addAttribute("listUsers", listUsers);
     
    return "pages/table-elements";
}
    
}
