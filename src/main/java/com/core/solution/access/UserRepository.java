package com.core.solution.access;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.core.solution.exception.SolutionData;
import com.core.solution.exception.SolutionException;
import com.core.solution.mapper.UserMapper;
import com.core.solution.model.entity.EntityUser;
import com.core.solution.model.payload.SignupRequest;
import com.core.solution.model.payload.UserRequest;
import com.core.solution.utils.MessagesAccess;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class UserRepository extends GenericRepository {

	public List<EntityUser> getUsers() throws SolutionException {		
		UserMapper mapper = super.getSqlSession().getMapper(UserMapper.class);
		List<EntityUser> listEntityUser = null;
		try {			
			listEntityUser = mapper.getUsers();			
		} catch (Exception e) {			
			throw new SolutionException(
					MessagesAccess.MESSAGE_ERROR_DB_GET_ALL_USER,
					new SolutionData(MessagesAccess.SUCCESS,
									  MessagesAccess.TITLE_ERROR_DB_GET_ALL_USER,
							          e.getCause().toString(),
							          MessagesAccess.CODE_ERROR_DB_GET_ALL_USER));			
		}				
		return listEntityUser;
	}

	public EntityUser getUser(String username) throws SolutionException {		
		UserMapper mapper = super.getSqlSession().getMapper(UserMapper.class);
		EntityUser entityUser = null;		
		try {			
			entityUser = mapper.getUser(username);			
		} catch (Exception e) {			
			throw new SolutionException(
					String.format(MessagesAccess.MESSAGE_ERROR_DB_GET_USER, username),
					new SolutionData(MessagesAccess.SUCCESS,
									  MessagesAccess.TITLE_ERROR_DB_GET_USER,
							          e.getCause().toString(),
							          MessagesAccess.CODE_ERROR_DB_GET_USER));			
		}		
		return entityUser;		
	}

	public void patchUser(UserRequest userRequest) throws SolutionException {
		UserMapper mapper = super.getSqlSession().getMapper(UserMapper.class);
		try {
			mapper.patchUser(userRequest);
		} catch (Exception e) {
			throw new SolutionException(
					String.format(MessagesAccess.MESSAGE_ERROR_DB_PATCH_USER, userRequest.getUsername()),
					new SolutionData(MessagesAccess.SUCCESS,
									  MessagesAccess.TITLE_ERROR_DB_PATCH_USER,
							          e.getCause().toString(),
							          MessagesAccess.CODE_ERROR_DB_PATCH_USER));	
		}
	}

	public void putUser(UserRequest userRequest) throws SolutionException {
		UserMapper mapper = super.getSqlSession().getMapper(UserMapper.class);
		try {
			mapper.putUser(userRequest);
		} catch (Exception e) {
			throw new SolutionException(
					String.format(MessagesAccess.MESSAGE_ERROR_DB_PUT_USER, userRequest.getUsername()),
					new SolutionData(MessagesAccess.SUCCESS,
									  MessagesAccess.TITLE_ERROR_DB_PUT_USER,
							          e.getCause().toString(),
							          MessagesAccess.CODE_ERROR_DB_PUT_USER));	
		}				
	}

	public Integer signupUser(SignupRequest signupRequest) throws SolutionException {		
		UserMapper mapper = super.getSqlSession().getMapper(UserMapper.class);		
		try {			
			mapper.signupUser(signupRequest);			
		} catch (Exception e) {
			throw new SolutionException(
					MessagesAccess.MESSAGE_ERROR_DB_CRATE_USER,
					new SolutionData(MessagesAccess.SUCCESS,
									  MessagesAccess.TITLE_ERROR_DB_CRATE_USER,
							          e.getCause().toString(),
							          MessagesAccess.CODE_ERROR_DB_CRATE_USER));			
		}		
		return signupRequest.getUserId();		
	}

	public List<EntityUser> getUsersByRangeDate(Date datetimeStart, Date datetimeEnd) throws SolutionException {
		UserMapper mapper = super.getSqlSession().getMapper(UserMapper.class);
		List<EntityUser> listEntityUser = null;
		try {			
			listEntityUser = mapper.getUsersByRangeDate(datetimeStart, datetimeEnd);			
		} catch (Exception e) {			
			throw new SolutionException(
					String.format(MessagesAccess.MESSAGE_ERROR_DB_GET_RANGE_USER, datetimeStart, datetimeEnd),
					new SolutionData(MessagesAccess.SUCCESS,
									  MessagesAccess.TITLE_ERROR_DB_GET_RANGE_USER,
							          e.getCause().toString(),
							          MessagesAccess.CODE_ERROR_DB_GET_RANGE_USER));			
		}				
		return listEntityUser;
	}
	
}
