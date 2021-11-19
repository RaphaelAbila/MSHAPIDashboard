package com.raphaelabila.apidashboard.controller;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raphaelabila.apidashboard.entity.apiuser.Apirequestlogs;
import com.raphaelabila.apidashboard.entity.apiuser.Apiuserendpoints;
import com.raphaelabila.apidashboard.entity.apiuser.Endpoint;
import com.raphaelabila.apidashboard.entity.apiuser.Endpointuser;
import com.raphaelabila.apidashboard.entity.apiuser.Endpointusercounts;
import com.raphaelabila.apidashboard.entity.apiuser.Userkeyview;
import com.raphaelabila.apidashboard.repository.apiuser.ApiKeyRepository;
import com.raphaelabila.apidashboard.repository.apiuser.ApiUserRepository;
import com.raphaelabila.apidashboard.repository.apiuser.ApirequestlogsRepository;
import com.raphaelabila.apidashboard.repository.apiuser.ApiuserendpointsRepository;
import com.raphaelabila.apidashboard.repository.apiuser.EndpointRepository;
import com.raphaelabila.apidashboard.repository.apiuser.EndpointuserRepository;
import com.raphaelabila.apidashboard.repository.apiuser.EndpointusercountsRepository;
import com.raphaelabila.apidashboard.repository.apiuser.UserkeyviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/dash")
public class DashboardController {
    @Autowired
    ApiUserRepository repo;
    @Autowired
    ApiKeyRepository keyrepo;
    @Autowired
    UserkeyviewRepository keyviewrepo;
    @Autowired
    EndpointRepository endrepo;
    @Autowired
    EndpointuserRepository enduserrepo;
    @Autowired
    ApiuserendpointsRepository enduserviewrepo;
    @Autowired
    ApirequestlogsRepository log;
    @Autowired
    EndpointusercountsRepository endrepocount;

    @GetMapping(value = "dashboard")
    public ModelMap mmDashboard() {
        return new ModelMap();
    }

    @GetMapping("/dashbordhome")
    public String deactivatedusers(Model model) {
        List<Userkeyview> listUsers = keyviewrepo.findAll();
        int userz = listUsers.size();

        model.addAttribute("apiusercount", userz);
        model.addAttribute("apiusers", listUsers);

        Long issuedkeys = keyrepo.count();
        model.addAttribute("issuedkeys", Integer.parseInt(issuedkeys.toString()));

        int activekeys = keyrepo.countactivekeys("Approved", Boolean.TRUE);
        model.addAttribute("activekeys", activekeys);

        Double successlogs = log.countlogs(Boolean.TRUE);
        Double failedlogs = log.countlogs(Boolean.FALSE);
        Double totalLogs = successlogs + failedlogs;
        model.addAttribute("apirequests", Math.round(totalLogs));
        model.addAttribute("successlogs", Math.round(((successlogs / totalLogs) * 100) * 100.0) / 100.0);
        model.addAttribute("failedlogs", Math.round(((failedlogs / totalLogs) * 100) * 100.0) / 100.0);

        Double activeusers = repo.countactiveusers("Approved", Boolean.TRUE);
        Double inactive = userz - activeusers;
        Double approvedclients = (activeusers / userz) * 100;
        Double unapprovedclients = (inactive / userz) * 100;
        model.addAttribute("approvedclients", Math.round(approvedclients * 100.0) / 100.0);
        model.addAttribute("unapprovedclients", Math.round(unapprovedclients * 100.0) / 100.0);

        model.addAttribute("plot1", Math.round(successlogs));
        model.addAttribute("plot2", Math.round(failedlogs));

        int year = Calendar.getInstance().get(Calendar.YEAR);
        model.addAttribute("year", year);
        return "pages/dashboard";
    }

    @GetMapping("/endpointhome")
    public String endpointsHome(Model model) {

        List<Endpointusercounts> endpoints = endrepocount.findAll();
        model.addAttribute("endpoints", endpoints);

        List<Userkeyview> userendpoints = keyviewrepo.fetchactiveuserkey(Boolean.TRUE, "Approved");
        model.addAttribute("userendpoints", userendpoints);

        return "pages/others/endpoints/endpointhome";
    }

    @PostMapping("/registerendpoint")
    public String createEndPoint(Endpoint end, Model model) throws NoSuchAlgorithmException {

        end.setDateadded(new Date());
        endrepo.save(end);
        List<Endpoint> endpoints = endrepo.findAll();
        model.addAttribute("endpoints", endpoints);

        List<Userkeyview> userendpoints = keyviewrepo.fetchactiveuserkey(Boolean.TRUE, "Approved");
        model.addAttribute("userendpoints", userendpoints);

        return "pages/others/endpoints/endpointhome";
    }

    @GetMapping("/logs")
    public String logs(Model model) {
        List<Apirequestlogs> userlogs = log.findAll();
        model.addAttribute("userlogs", userlogs);
        return "pages/others/requestlogs/apilogs";
    }

    @GetMapping("/registerclient")
    public String registerclient(Model model) {
        return "pages/others/systemusers/registerclient";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long endpointId) {
        ModelAndView mav = new ModelAndView("add-employee-form");
        Endpoint employee = endrepo.findById(endpointId).get();
        mav.addObject("employee", employee);
        return mav;
    }

    @GetMapping("/deleteendpoint")
    public String deleteEmployee(@RequestParam Long endpointid, Model model) {
        endrepo.deleteById(endpointid);
        List<Endpointusercounts> endpoints = endrepocount.findAll();
        model.addAttribute("endpoints", endpoints);
        return "pages/others/endpoints/endpointhome";
    }

    @PostMapping("/updateendpoint")
    public String updateendpoint(Endpoint end, Model model) throws NoSuchAlgorithmException {

        endrepo.updateendpoint(end.getEndpointid(), end.getName(), end.getDetails(), end.getUrl());

        List<Endpointusercounts> endpoints = endrepocount.findAll();
        model.addAttribute("endpoints", endpoints);

        List<Userkeyview> userendpoints = keyviewrepo.fetchactiveuserkey(Boolean.TRUE, "Approved");
        model.addAttribute("userendpoints", userendpoints);

        return "pages/others/endpoints/endpointhome";
    }

    @GetMapping("/getendpoints")
    public @ResponseBody List<Endpoint> getendpoints(@RequestParam Long apiuserid) {
        List<Endpoint> userendz = new ArrayList<>();

        List<Endpoint> endpoints = endrepo.findAll();

        for (Endpoint ends : endpoints) {
            Long endpointid = ends.getEndpointid();
            int counter = enduserrepo.countAvailable(endpointid,apiuserid);
            if(counter == 0 ){
                userendz.add(ends);
            }
        }
        return userendz;
    }

    @GetMapping("/submitendpoints")
    public @ResponseBody String submitendpoints(HttpServletRequest request, @RequestParam Long apiuserid)
            throws JsonMappingException, JsonProcessingException {
        List<Map> items = (ArrayList) new ObjectMapper().readValue(request.getParameter("endpoints"), List.class);
        int counter = items.size();
        int count = 0;
        String result = "";
        if (counter > 0) {
            for (Map item1 : items) {
                Map<String, Object> map = (HashMap) item1;
                Map<String, Object> item = new HashMap<>();
                final String name = map.get("name").toString();
                final long endpointid = Integer.parseInt(map.get("endpointid").toString());
                Endpointuser endpointuser = new Endpointuser();
                endpointuser.setApiuserid(BigInteger.valueOf(apiuserid));
                endpointuser.setEndpointid(BigInteger.valueOf(endpointid));
                endpointuser.setActive(Boolean.TRUE);
                enduserrepo.save(endpointuser);
                count++;
            }
            if (counter == count) {
                result = "success";
            } else {
                result = "failed";
            }
        }
        return result;
    }

    @GetMapping("/getuserendpoints")
    public @ResponseBody ModelAndView getuserendpoints(Model model, @RequestParam Long apiuserid) {
        List<Apiuserendpoints> listEndpoints = enduserviewrepo.findbyuserId(apiuserid);
        model.addAttribute("listEndpoints", listEndpoints);

        return new ModelAndView("pages/others/endpoints/userendpoints", "model", model);

    }

    @GetMapping("/deleteuserendpoint")
    public @ResponseBody String deleteuserendpoint(@RequestParam Long endpointuserid) {
        String results = "";
        try {
            enduserrepo.deleteByUserId(endpointuserid);
            results = "success";
        } catch (Exception e) {
            results = "failed";
        }
        return results;
    }
}
