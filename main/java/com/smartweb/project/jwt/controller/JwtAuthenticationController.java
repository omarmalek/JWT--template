package com.smartweb.project.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.smartweb.project.dto.UserDTO;
import com.smartweb.project.jwt.dto.JwtRequest;
import com.smartweb.project.jwt.dto.JwtResponse;
import com.smartweb.project.jwt.dto.RoleDTO;
import com.smartweb.project.jwt.entity.User;
import com.smartweb.project.jwt.service.JwtUserDetailsService;
import com.smartweb.project.jwt.service.RoleService;
import com.smartweb.project.jwt.service.UserService;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    @Lazy
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

//    @PostConstruct  //we call this method just one time to prevent duplicate of Admin user.
//    public void initRoleAndUser() {
//        userService.initRoleAndUser();
   // }

    @PostMapping("/authenticate")
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
    	System.out.print("\nclass: JwtAuthenticationController. jwtRequest.userName: " + jwtRequest.getUsername());
    	
        return jwtUserDetailsService.createJwtToken(jwtRequest);
    }
    @GetMapping("/checkUserAuthentication")
    @PreAuthorize("hasRole('USER')")
    public String checkUserAuthentication() {
        return "User is authenticated";
    }
    @GetMapping("/checkAdminAuthentication")
    @PreAuthorize("hasRole('ADMIN')")
    public String checkAdminAuthentication() {
        return "Admin is authenticated";
    }

    @PostMapping("/registerNewUser")
    public User registerNewUser(@RequestBody UserDTO user) throws Exception {
        return userService.registerNewUser(user);
    }

    @PostMapping("/registerNewAdminUser")
    @PreAuthorize("hasRole('ADMIN')")
    public User registerNewAdminUser(@RequestBody UserDTO user) {
        return userService.registerNewAdminUser(user);
    }

    @PostMapping("/createNewRole")
    public RoleDTO createNewRole(@RequestBody RoleDTO role) {
        return roleService.createNewRole(role);
    }

    @GetMapping("/forAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public String forAdmin() {
        return "This URL is only accessible to the admin";
    }

    @GetMapping("/forUser")
    @PreAuthorize("hasRole('USER')")
    public String forUser() {
        return "This URL is only accessible to the user";
    }

    @GetMapping("/everybody")
    public String forEveryBody() {
        return "Welcome Everybody";
    }
}
