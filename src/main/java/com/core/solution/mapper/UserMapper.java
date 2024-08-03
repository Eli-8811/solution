package com.core.solution.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.exceptions.PersistenceException;

import com.core.solution.model.entity.EntityUser;
import com.core.solution.model.payload.UserRequest;


@Mapper
public interface UserMapper {

	void createUser(@Param("userRequest") UserRequest userRequest) throws PersistenceException;
	
	List<EntityUser> getUsers() throws PersistenceException ;	

	EntityUser getUser(@Param("username") String username) throws PersistenceException;

	void patchUser(@Param("userRequest") UserRequest userRequest) throws PersistenceException;

	void putUser(@Param("userRequest") UserRequest userRequest) throws PersistenceException;
	
	void deleteUsers() throws PersistenceException;		

	void deleteUser(@Param("username") String username) throws PersistenceException;

}
