package com.petsonly.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petsonly.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByEmail(String email);
}
