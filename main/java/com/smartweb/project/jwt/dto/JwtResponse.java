package com.smartweb.project.jwt.dto;

public class JwtResponse {
	private String username;
    private String jwtToken;
    
	public JwtResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JwtResponse(String username, String jwtToken) {
	
		this.username = username;
		this.jwtToken = jwtToken;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
    
    

}