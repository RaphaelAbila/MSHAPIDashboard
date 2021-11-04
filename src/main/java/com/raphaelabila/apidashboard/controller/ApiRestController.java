package com.raphaelabila.apidashboard.controller;

import java.util.Date;

import com.raphaelabila.apidashboard.entity.apiuser.Apirequestlogs;
import com.raphaelabila.apidashboard.repository.apiuser.ApiKeyRepository;
import com.raphaelabila.apidashboard.repository.apiuser.ApiUserRepository;
import com.raphaelabila.apidashboard.repository.apiuser.ApirequestlogsRepository;
import com.raphaelabila.apidashboard.repository.apiuser.ApiuserendpointsRepository;
import com.raphaelabila.apidashboard.repository.apiuser.RestResponse;
import com.raphaelabila.apidashboard.repository.apiuser.UserkeyviewRepository;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class ApiRestController {
    @Autowired
    UserkeyviewRepository keyviewrepo;
    @Autowired
    ApirequestlogsRepository log;
    @Autowired
    ApiuserendpointsRepository apiuserendpointsview;
    @Autowired
    ApiKeyRepository keyzz;
    @Autowired
    ApiUserRepository user;

    //////////////////// TEST TWO END POINT////////////////////////////
    @RequestMapping(value = "/tests", method = RequestMethod.GET, consumes = "application/json")
    public @ResponseBody RestResponse consumption(@RequestBody String json) {
        RestResponse response = new RestResponse();
        String Endpoint = "Get Access";
        String key = "";
        String username = "";
        String results = "";

        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArrayRequired = jsonObject.getJSONArray("required");
        if (jsonArrayRequired.length() == 1) {
            key = jsonArrayRequired.getJSONObject(0).getString("key");
            username = jsonArrayRequired.getJSONObject(0).getString("username");

            int validuser = user.checkvaliduser(username);
            if (validuser > 0) {
                // Check whether key exists
                int validkey = keyzz.findbykey(key);
                if (validkey > 0) {
                    // check for end point access
                    int access = apiuserendpointsview.checkendpointaccess(Endpoint, username);
                    if (access > 0) {
                        // check for active user
                        int activeuser = keyviewrepo.checkactiveuser(Boolean.TRUE, "Approved", username);
                        if (activeuser > 0) {
                            // check for key activation
                            int activekey = keyviewrepo.checkactivekey(Boolean.TRUE, "Approved", key, username);
                            if (activekey > 0) {
                                //// success /////
                                results = "Legible to access this Endpoint";
                                response.setData(results);
                                response.setReturnCode("00");
                                response.setReturnMessage("SUCCESS");
                                logAPIRequest(results, username, username + "_key", Endpoint, Boolean.TRUE);
                            } else {
                                results = "Inactive API Key";
                                response.setData(results);
                                response.setReturnCode("01");
                                response.setReturnMessage("FAILURE");
                                logAPIRequest(results, username, username + "_key", Endpoint, Boolean.FALSE);
                            }
                        } else {
                            results = "Inactive API user";
                            response.setData(results);
                            response.setReturnCode("01");
                            response.setReturnMessage("FAILURE");
                            logAPIRequest(results, username, username + "_key", Endpoint, Boolean.FALSE);
                        }
                    } else {
                        results = "Endpoint Access Denied";
                        response.setData(results);
                        response.setReturnCode("01");
                        response.setReturnMessage("FAILURE");
                        logAPIRequest(results, username, username + "_key", Endpoint, Boolean.FALSE);
                    }
                } else {
                    results = "Invalid API Key";
                    response.setData(results);
                    response.setReturnCode("01");
                    response.setReturnMessage("FAILURE");
                    logAPIRequest(results, username, username + "_key", Endpoint, Boolean.FALSE);
                }
            } else {
                results = "Invalid API User";
                response.setData(results);
                response.setReturnCode("01");
                response.setReturnMessage("FAILURE");
                logAPIRequest(results, username, username + "_key", Endpoint, Boolean.FALSE);
            }
        } else {
            results = "Improper Request Body Or Empty parameters";
            response.setData(results);
            response.setReturnCode("01");
            response.setReturnMessage("FAILURE");
            logAPIRequest(results, username, username + "_key", Endpoint, Boolean.FALSE);
        }
        return response;
    }

    public String logAPIRequest(String reasons, String username, String key, String endpoint, Boolean status) {
        String results = "";
        try {
            Apirequestlogs apirequestlogs = new Apirequestlogs();
            apirequestlogs.setDescription(reasons);
            apirequestlogs.setRequestedby(username);
            apirequestlogs.setRequestingkey(key);
            apirequestlogs.setStatus(status);
            apirequestlogs.setLoggedate(new Date());
            apirequestlogs.setEndpoint(endpoint);
            log.save(apirequestlogs);
            results = "success";
        } catch (Exception e) {
            results = "failed";
        }
        return results;
    }
}
