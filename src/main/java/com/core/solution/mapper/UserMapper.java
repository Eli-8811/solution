package com.core.solution.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.core.solution.exception.SolutionException;
import com.core.solution.model.entity.EntityUser;
import com.core.solution.model.payload.SignupRequest;
import com.core.solution.model.payload.UserRequest;

@Mapper
public interface UserMapper {

	List<EntityUser> getUsers() throws SolutionException ;	

	EntityUser getUser(@Param("username") String username) throws SolutionException;

	void patchUser(@Param("userRequest") UserRequest userRequest) throws SolutionException;

	void putUser(@Param("userRequest") UserRequest userRequest) throws SolutionException;
	
	void signupUser(@Param("signupRequest") SignupRequest signupRequest) throws SolutionException;

	List<EntityUser> getUsersByRangeDate(@Param("datetimeStart") Date datetimeStart, @Param("datetimeEnd") Date datetimeEnd) throws SolutionException;	
	
}
