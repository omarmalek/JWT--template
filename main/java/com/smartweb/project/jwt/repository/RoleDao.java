package com.smartweb.project.jwt.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.smartweb.project.jwt.entity.Role;



@Repository
public interface RoleDao extends CrudRepository<Role, Long> {

	boolean existsByRoleName(String string);
	
	
	Role findByRoleName(String rolename);

}
