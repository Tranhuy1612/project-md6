package com.ra.repository;

import com.ra.model.entity.RoleName;
import com.ra.model.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Roles, Long> {
	
	Optional<Roles> findByRoleName(RoleName roleName);
	
}
