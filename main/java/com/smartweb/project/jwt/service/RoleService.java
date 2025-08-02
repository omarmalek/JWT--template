package com.smartweb.project.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartweb.project.jwt.dto.RoleDTO;
import com.smartweb.project.jwt.entity.Role;
import com.smartweb.project.jwt.repository.RoleDao;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public RoleDTO createNewRole(RoleDTO roleDTO) {
    	Role role = new Role();
    	role.setRoleName(roleDTO.getRoleName());
    	role.setDescription(roleDTO.getRoleDescription());
        roleDao.save(role);
        return (roleDTO);
    }
}
