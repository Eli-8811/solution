package com.core.solution.bussines;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.core.solution.access.UserRepository;
import com.core.solution.model.entity.EntityUser;
import com.core.solution.model.payload.UserRequest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	
	@Transactional
	public UserRequest createUser(UserRequest userRequest) {		
		userRequest.setUserId(this.userRepository.createUser(userRequest));
		return userRequest;
	}
	
	public EntityUser getUser(String username) {
		EntityUser entityUser = null;
		entityUser = this.userRepository.getUser(username);
		return entityUser;
	}
	
	public List<EntityUser> getUsers() {				
		return this.userRepository.getUsers();			
	}

	@Transactional
	public void patchUser(UserRequest userRequest) {
		this.userRepository.patchUser(userRequest);
	}

	@Transactional
	public void putUser(UserRequest userRequest) {	
		this.userRepository.putUser(userRequest);			
	}
	
	@Transactional
	public void deleteUser(String username) {
		this.userRepository.deleteUser(username);
	}

}
