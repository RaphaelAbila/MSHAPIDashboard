package com.raphaelabila.apidashboard.controller;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.raphaelabila.apidashboard.entity.apiuser.Apikey;
import com.raphaelabila.apidashboard.entity.apiuser.Apirequestlogs;
import com.raphaelabila.apidashboard.entity.apiuser.Apiuser;
import com.raphaelabila.apidashboard.entity.apiuser.Userkeyview;
import com.raphaelabila.apidashboard.repository.apiuser.ApiKeyRepository;
import com.raphaelabila.apidashboard.repository.apiuser.ApiUserRepository;
import com.raphaelabila.apidashboard.repository.apiuser.ApirequestlogsRepository;
import com.raphaelabila.apidashboard.repository.apiuser.ApiuserendpointsRepository;
import com.raphaelabila.apidashboard.repository.apiuser.RestResponse;
import com.raphaelabila.apidashboard.repository.apiuser.UserkeyviewRepository;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

@Controller
@RequestMapping("/authenticate")
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

    //////////////////// GENERATE ACCESS KEYS//////////////////////////////
    @RequestMapping(value = "/generateapikey", method = RequestMethod.GET, consumes = "application/json")
    public @ResponseBody RestResponse generateaccesskey(@RequestBody String json) throws NoSuchAlgorithmException {
        RestResponse response = new RestResponse();
        String Endpoint = "Generate API Key";
        String password = "";
        String username = "";
        String results = "";

        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArrayRequired = jsonObject.getJSONArray("required");
        if (jsonArrayRequired.length() == 1) {
            password = jsonArrayRequired.getJSONObject(0).getString("password");
            username = jsonArrayRequired.getJSONObject(0).getString("username");

            // BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            // String encodedPassword = passwordEncoder.encode(password);
            
            // Boolean tester = passwordEncoder.matches(password, encodedPassword);
            
            Apiuser founduser = user.findByUsername(username);
            Apiuser usz = new Apiuser();
            if (founduser != null) {
                
                //////////////// check if user had generated api key before/////////
                Apikey appkey = keyzz.findBykeyname(username + "_key");
                if (appkey != null) {
                    Apikey kyz = new Apikey();
                    results = "Generated API Key Successfully";
                    response.setData(kyz.getKey());
                    response.setReturnCode("00");
                    response.setReturnMessage("SUCCESS");
                    logAPIRequest(results, username, username + "_key", Endpoint, Boolean.TRUE);
                } else {
                    String apikey = generate(128);
                    Apikey keyval = new Apikey();
                    keyval.setActiive(Boolean.FALSE);
                    keyval.setKey(apikey);
                    keyval.setKeyname(username + "_key");
                    keyval.setStatus("Pending");
                    keyval.setApiuserid(usz.getApiuserid());
                    keyzz.save(keyval);

                    results = "Generated Access Key Successfully";
                    response.setData(apikey);
                    response.setReturnCode("00");
                    response.setReturnMessage("SUCCESS");
                    logAPIRequest(results, username, username + "_key", Endpoint, Boolean.TRUE);
                }

            } else {
                results = "Invalid User Credentials";
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

    //////////////////// GET ACCESS TOKEN ////////////////////////////
    @RequestMapping(value = "/getaccesstoken", method = RequestMethod.GET, consumes = "application/json")
    public @ResponseBody RestResponse getAccessToken(@RequestBody String json) throws NoSuchAlgorithmException {
        RestResponse response = new RestResponse();
        String Endpoint = "Get Token";
        String username = "unknown";
        String key = "";
        String results = "";

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArrayRequired = jsonObject.getJSONArray("required");
            if (jsonArrayRequired.length() == 1) {
                key = jsonArrayRequired.getJSONObject(0).getString("apikey");

                ///////////// Check if key/user exists//////////////////////////
                Userkeyview userrecord = keyviewrepo.fetchuserbykey(key);
                if (userrecord != null) {
                    username = userrecord.getUsername();
                    //// check if user is active ///////////////////////
                    if (userrecord.getActive() == Boolean.TRUE
                            && ("Approved").equalsIgnoreCase(userrecord.getStatus())) {
                        //// check if key is active ///////////////////////
                        if (userrecord.getKeyactive() == Boolean.TRUE
                                && ("Approved").equalsIgnoreCase(userrecord.getKeystatus())) {
                            //// generate token for user ///////////////////////
                            String token = createJWT(key, username, 600000);

                            results = "Generated Access Token Successfully";
                            response.setData("Bearer:" +" "+ token);
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
                    results = "Invalid API Key";
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
        } catch (Exception e) {
            System.out.println(e);
        }
        return response;
    }

    //////////////////// TEST TWO END POINT////////////////////////////
    @RequestMapping(value = "/apigateway", method = RequestMethod.GET, consumes = "application/json")
    public @ResponseBody RestResponse consumption(@RequestBody String json) {
        RestResponse response = new RestResponse();
        String Endpoint = "unknown";
        String key = "";
        String username = "";
        String results = "";

        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArrayRequired = jsonObject.getJSONArray("required");
        if (jsonArrayRequired.length() == 1) {
            Endpoint = jsonArrayRequired.getJSONObject(0).getString("destination");
            String token = jsonArrayRequired.getJSONObject(0).getString("token");

            Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("SECRET_KEY"))
                    .parseClaimsJws(token).getBody();

            ////////////// get username and key fron token ////////////////////
            username = claims.getSubject();
            key = claims.getId();

            try {
                decodeJWT(token);

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

                                    String uri = "https://stoplight.io/mocks/sscs-activity/ubts-api/26109322/users/142";
                                    RestTemplate restTemplate = new RestTemplate();
                                    results = restTemplate.getForObject(uri,String.class);
                                    
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
            } catch (Exception e) {
                System.out.println(e);
                results = "EXCEPTION::" + e;
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

    ////////////////////////// METHODS BEGIN HERE ////////////////////////

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

    public static String generate(final int keyLen) throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(keyLen);
        SecretKey secretKey = keyGen.generateKey();
        byte[] encoded = secretKey.getEncoded();
        return DatatypeConverter.printHexBinary(encoded).toLowerCase();
    }

    public String createJWT(String key, String subject, long ttlMillis) {
        String issuer = "ihfmis";
        // The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("SECRET_KEY");
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        // Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(key).setIssuedAt(now).setSubject(subject).setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        // if it has been specified, let's add the expiration
        if (ttlMillis > 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        // Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    public Claims decodeJWT(String jwt) {
        // This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("SECRET_KEY"))
                .parseClaimsJws(jwt).getBody();
        return claims;
    }
}
