package com.smartweb.project.jwt.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.smartweb.project.dto.UserDTO;
import com.smartweb.project.jwt.entity.Role;
import com.smartweb.project.jwt.entity.User;
import com.smartweb.project.jwt.repository.RoleDao;
import com.smartweb.project.jwt.repository.UserDao;


@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;
    


    public void initRoleAndUser() {
    	// 	admin role
        Role adminRole = new Role();
        adminRole.setRoleName("ADMIN");
        adminRole.setDescription("Admin role");
        if(!roleDao.existsByRoleName("ADMIN")) {
        	roleDao.save(adminRole);	
        }
      
        // admin user
        User adminUser = new User();
        adminUser.setUsername("admin");
        adminUser.setPassword(passwordEncoder.encode("123"));
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRoles(adminRoles);
        adminUser.setFullName("Admin_Full_Name");
        adminUser.setEmail("administrator@gmail.com");
        if(!userDao.existsByUsername("adminUser")) {
        	userDao.save(adminUser);        	
        }
        
    	// 	user role
        Role userRole = new Role();
        userRole.setRoleName("USER");  
        userRole.setDescription("User role");
        if(!roleDao.existsByRoleName("USER")) {
        	roleDao.save(userRole);
        }
      
        // "user" user
        User user = new User();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("123"));
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        user.setRoles(userRoles);
        if(!userDao.existsByUsername("user")) {
        	userDao.save(user);        	
        }
          //---------------------------------------------------------------------
    }

    public User registerNewUser(UserDTO userDTO) throws Exception {
    	if(!userDao.existsByUsername(userDTO.getUserName())) {
    		User newUser = new User();
           newUser.setUsername(userDTO.getUserName());
           newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
           
        	Role role = roleDao.findByRoleName("User");
            Set<Role> userRoles = new HashSet<>();
            userRoles.add(role);
            newUser.setRoles(userRoles);
            System.out.println("registerNewUser > new user created.");
            return userDao.save(newUser);
    	}else
    	throw new  Exception();
   	
    }
    public User registerNewAdminUser(UserDTO userDTO) {
        User newUser = new User();
        newUser.setUsername(userDTO.getUserName());
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        
    	Role givenRole = roleDao.findByRoleName(userDTO.getRoleName());
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(givenRole);
    	newUser.setRoles(userRoles);
    	return userDao.save(newUser);
    
    }

 
}
