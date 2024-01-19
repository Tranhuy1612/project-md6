package com.ra.repository;

import com.ra.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<Users, Long> {
	Optional<Users> findByUserName(String userName);
	
	Boolean existsByUserName(String userName);
}
