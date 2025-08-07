package com.project.jobApp.user;

import java.util.Optional;

import com.project.jobApp.job.JobDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
	Optional<User> findByEmail(String email);
	
	void createUser(User user);

	org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username);
	
	User saveUser(User user);
}
