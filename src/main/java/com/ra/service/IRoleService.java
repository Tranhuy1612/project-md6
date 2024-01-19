package com.ra.service;

import com.ra.model.entity.RoleName;
import com.ra.model.entity.Roles;

public interface IRoleService {
	
	Roles findByRoleName(RoleName roleName);
	
}
