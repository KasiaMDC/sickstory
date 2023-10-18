package com.kasia.sickstory.security;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Resource
    private SecurityService securityService;

    /* This unsecured endpoint calculates the basic authentication header value for the frontend
     * */
    @GetMapping("/auth")
    @CrossOrigin
    public String auth(@RequestParam String username, @RequestParam String password) throws Exception {
        return securityService.generateBasicAuthHeader(username, password);
    }

    /* This request is used by the frontend to check if the login was successful
     * */
    @GetMapping("/testlogin")
    @CrossOrigin
    public void testLogin() {
    }

}
