package com.raphaelabila.apidashboard.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

import com.raphaelabila.apidashboard.entity.apiuser.Apikey;
import com.raphaelabila.apidashboard.entity.apiuser.Apiuser;
import com.raphaelabila.apidashboard.entity.apiuser.Userkeyview;
import com.raphaelabila.apidashboard.repository.apiuser.ApiKeyRepository;
import com.raphaelabila.apidashboard.repository.apiuser.ApiUserRepository;
import com.raphaelabila.apidashboard.repository.apiuser.UserkeyviewRepository;
import com.raphaelabila.apidashboard.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api")
public class ApiUserController {
    @Autowired
    private final ApplicationContext appContextx = new ClassPathXmlApplicationContext(
            "classpath:**/applicationContext.xml");

    @Autowired
    ApiUserRepository repo;
    @Autowired
    ApiKeyRepository keyrepo;
    @Autowired
    UserkeyviewRepository keyviewrepo;

    @GetMapping("/userregistration")
    public String dashboardHome() {
        return "apiuserform";
    }

    @PostMapping("/api_register")
    public String processRegister(Apiuser user, Model model) throws NoSuchAlgorithmException {
        String results = "";

        Apiuser available = repo.findByUsername(user.getUsername());
        if (available == null) {
            user.setActive(Boolean.FALSE);
            user.setSignupdate(new Date());
            String apikey = generate(128);
            user.setStatus("Pending");
            repo.save(user);

            Apikey keyval = new Apikey();
            keyval.setActiive(Boolean.FALSE);
            keyval.setKey(apikey);
            keyval.setKeyname(user.getUsername() + "_key");
            keyval.setStatus("Pending");
            keyval.setApiuserid(user.getApiuserid());
            keyrepo.save(keyval);

            model.addAttribute("apikey", apikey);
            results = "successpage";
        } else {
            results = "errorpage";
        }
        return results;
    }

    public static String generate(final int keyLen) throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(keyLen);
        SecretKey secretKey = keyGen.generateKey();
        byte[] encoded = secretKey.getEncoded();
        return DatatypeConverter.printHexBinary(encoded).toLowerCase();
    }

    @GetMapping("/inactiveapiusers")
    public String inactiveUsers(Model model) {
        List<Userkeyview> listUsers = keyviewrepo.findUserWithStatus("Pending", Boolean.FALSE);
        model.addAttribute("listUsers", listUsers);

        return "pages/apiUsers/inactiveapiusers";
    }

    @GetMapping("/activeapiusers")
    public String activeUsers(Model model) {
        List<Userkeyview> listUsers = keyviewrepo.findUserWithStatus("Approved", Boolean.TRUE);
        model.addAttribute("listUsers", listUsers);

        return "pages/apiUsers/activeapiusers";
    }

    @GetMapping("/deactivatedapiusers")
    public String deactivatedusers(Model model) {
        List<Userkeyview> listUsers = keyviewrepo.findUserWithStatus("Deactivated", Boolean.TRUE);
        model.addAttribute("listUsers", listUsers);

        return "pages/apiUsers/deactivatedapiusers";
    }

    @GetMapping("/inactiveapikeys")
    public String inactiveApikeys(Model model) {
        List<Userkeyview> listKeys = keyviewrepo.findKeyWithStatus("Pending", Boolean.FALSE);
        model.addAttribute("listKeys", listKeys);

        return "pages/apiKeys/inactivekeys";
    }

    @GetMapping("/activeapikeys")
    public String activeApikeys(Model model) {
        List<Userkeyview> listKeys = keyviewrepo.findKeyWithStatus("Approved", Boolean.TRUE);
        model.addAttribute("listKeys", listKeys);

        return "pages/apiKeys/activekeys";
    }

    @GetMapping("/deactivatedapikeys")
    public String deactivatedapikeys(Model model) {
        List<Userkeyview> listKeys = keyviewrepo.findKeyWithStatus("Deactivated", Boolean.TRUE);
        model.addAttribute("listKeys", listKeys);

        return "pages/apiKeys/deactivatedkeys";
    }

    @GetMapping("/manageusers")
    public String manageusers(@RequestParam Long apiuserid, @RequestParam Boolean active, @RequestParam String status,
            @RequestParam String page, Model model) {
        String results = "";
        try {

            ////// update record /////////////////
            repo.manageUser(active, status, apiuserid, new Date(), new Date());

            ///// Fetch page to return
            if (("inactive").equalsIgnoreCase(page)) {
                List<Userkeyview> listUsers = keyviewrepo.findUserWithStatus("Pending", Boolean.FALSE);
                model.addAttribute("listUsers", listUsers);
                results = "pages/apiUsers/inactiveapiusers";
            } else if (("active").equalsIgnoreCase(page)) {
                List<Userkeyview> listUsers = keyviewrepo.findUserWithStatus("Approved", Boolean.TRUE);
                model.addAttribute("listUsers", listUsers);
                results = "pages/apiUsers/activeapiusers";
            } else {
                List<Userkeyview> listUsers = keyviewrepo.findUserWithStatus("Deactivated", Boolean.TRUE);
                model.addAttribute("listUsers", listUsers);
                results = "pages/apiUsers/deactivatedapiusers";
            }

        } catch (Exception e) {
            System.out.println("Error::::::::::" + e);
        }
        return results;
    }

    @GetMapping("/managekeys")
    public String managekeys(@RequestParam Long apikeyid, @RequestParam Boolean active, @RequestParam String status,
            @RequestParam String page, Model model) {
        String results = "";
        try {

            ////// update record /////////////////
            keyrepo.manageKey(active, status, apikeyid, new Date(), new Date());
            Userkeyview keyz = keyviewrepo.findbykeyid(apikeyid);
            String email = keyz.getEmail();
            String key = keyz.getKey();
            
         

            ///// Fetch page to return
            if (("inactive").equalsIgnoreCase(page)) {
                List<Userkeyview> listKeys = keyviewrepo.findKeyWithStatus("Pending", Boolean.FALSE);
                model.addAttribute("listKeys", listKeys);
                results = "pages/apiKeys/inactivekeys";
                try {
                    // String mailTo = "timothyisaiah7@gmail.com";
                    String mailTo = email;
                    String subject = "API KEY ACTIVATION";
                    EmailService mailer = (EmailService) appContextx.getBean("emailService");
                    String Body = "I hope this emails finds you well, This is to inform you that your API Key:"+" "+ key +" "+"has been activated for usage. Thanks";
                    mailer.sendMail(mailTo, subject, Body);
                    System.out.println("Successfully sent email");

                } catch (Exception e) {
                    String result2 = e + "";
                    String log = "This Email cannot be sent due to a bad email address or poor Internet connection. Please check the email or connection and try again.";
                }
            } else if (("active").equalsIgnoreCase(page)) {
                List<Userkeyview> listKeys = keyviewrepo.findKeyWithStatus("Approved", Boolean.TRUE);
                model.addAttribute("listKeys", listKeys);
                results = "pages/apiKeys/activekeys";
            } else {
                List<Userkeyview> listKeys = keyviewrepo.findKeyWithStatus("Deactivated", Boolean.TRUE);
                model.addAttribute("listKeys", listKeys);
                results = "pages/apiKeys/deactivatedkeys";
            }

        } catch (Exception e) {
            System.out.println("Error::::::::::" + e);
        }
        return results;
    }
}
