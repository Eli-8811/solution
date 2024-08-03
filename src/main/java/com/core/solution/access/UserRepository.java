package com.core.solution.access;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.core.solution.mapper.UserMapper;
import com.core.solution.model.entity.EntityUser;
import com.core.solution.model.payload.UserRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class UserRepository extends GenericRepository {

	public Integer createUser(UserRequest userRequest) {		
		UserMapper mapper = super.getSqlSession().getMapper(UserMapper.class);		
		mapper.createUser(userRequest);
		return userRequest.getUserId();
	}
	
	public List<EntityUser> getUsers() {			
		UserMapper mapper = super.getSqlSession().getMapper(UserMapper.class);
		return mapper.getUsers();
	}

	public EntityUser getUser(String username) {
		UserMapper mapper = super.getSqlSession().getMapper(UserMapper.class);		
		return mapper.getUser(username);
	}

	public void patchUser(UserRequest userRequest) {
		UserMapper mapper = super.getSqlSession().getMapper(UserMapper.class);
		mapper.patchUser(userRequest);
	}

	public void putUser(UserRequest userRequest) {
		UserMapper mapper = super.getSqlSession().getMapper(UserMapper.class);
		mapper.putUser(userRequest);			
	}
	
	public void deleteUser(String username) {
		UserMapper mapper = super.getSqlSession().getMapper(UserMapper.class);
		mapper.deleteUser(username);
	}
		
}
