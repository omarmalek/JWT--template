package com.smartweb.project.dto;

public class UserDTO {
	
	private long id;
	private String userName;
	private String password;
	private String roleName;
	private String rolDescription;
	private String email ;
	private String phoneNumber;
	private String address;
	
	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDTO(long id, String userName, String password, String roleName, String rolDescription, String email,
			String phoneNumber, String address) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.roleName = roleName;
		this.rolDescription = rolDescription;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRolDescription() {
		return rolDescription;
	}

	public void setRolDescription(String rolDescription) {
		this.rolDescription = rolDescription;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
