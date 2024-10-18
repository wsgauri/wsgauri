package com.project.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Model.User;

public interface comRepo extends JpaRepository<User, Integer> {
	
	User findByEmail(String email);

	  User findByResetToken(String resetToken);
		
		
	
}
