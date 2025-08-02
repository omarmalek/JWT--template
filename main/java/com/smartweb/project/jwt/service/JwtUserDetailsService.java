
package com.smartweb.project.jwt.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.smartweb.project.jwt.configuration.JwtTokenUtil;
import com.smartweb.project.jwt.dto.JwtRequest;
import com.smartweb.project.jwt.dto.JwtResponse;
import com.smartweb.project.jwt.entity.User;
import com.smartweb.project.jwt.repository.UserDao;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private JwtTokenUtil jwtUtil;

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;


    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
    	
    	//Extract username and password from request.
        String userName = jwtRequest.getUsername();
        String userPassword = jwtRequest.getPassword();
        System.out.print("\nclass: JwtUserDetailsService. userName: " + userName);
        System.out.print("\nclass: JwtUserDetailsService. password: " + userPassword);
        //validate user
        authenticate(userName, userPassword);//This method returns 'void' anyway, it works as a "Checkpoint". if this procedure catch an error everything stops right here.

        //fetching user details from the database using the username.
        UserDetails userDetails = loadUserByUsername(userName);
        
        //Generate Token
        String newGeneratedToken = jwtUtil.generateToken(userDetails);
        
        

        //fetching usr info from the database using the username. 
       User user = userDao.findByUsername(userName);
        
       
        return new JwtResponse(user.getUsername(),newGeneratedToken);
    }

    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	// 1. اجلب المستخدم من قاعدة البيانات
		User user = userDao.findByUsername(username);  // custom user from database
		
		if (user == null) throw new UsernameNotFoundException("User not found" + username);
		
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
					getAuthority(user));
		
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
        return authorities;
    }

    private void authenticate(String userName, String userPassword) throws Exception {
        try {
        	authenticationConfiguration.getAuthenticationManager()
            .authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));

        } catch (DisabledException e) {
        	System.out.println(" \nclass:JwtUserDetailsService > method: authenticate msg: USER_DISABLED. ");
            throw new Exception("USER_DISABLED", e);
            
        } catch (BadCredentialsException e) {
        	System.out.println("\nclass:JwtUserDetailsService > method: authenticate msg: INVALID_CREDENTIALS. ");
            throw new Exception("INVALID_CREDENTIALS", e);
        }catch(Exception e) {
        	System.out.println("\nclass:JwtUserDetailsService > method: authenticate msg: somthing goes wrong! ");
        }
    }
}
