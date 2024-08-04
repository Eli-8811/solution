package com.core.solution.bussines;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.core.solution.access.UserRepository;
import com.core.solution.exception.SolutionException;
import com.core.solution.model.entity.EntityUser;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class UserLoadService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) {
		
		EntityUser entityUser = null;
		
		try {
			
			entityUser = this.userRepository.getUser(username);
			entityUser.setRoles(entityUser.getRoles());
			
		} catch (SolutionException e) {
			
			e.printStackTrace();
			
		} catch (UsernameNotFoundException e) {
			
			e.printStackTrace();
			
		}	
		
		return UserDetailService.build(entityUser);
		
	}

}