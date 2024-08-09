package com.core.solution.bussines;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.core.solution.access.UserRepository;
import com.core.solution.exception.SolutionData;
import com.core.solution.exception.SolutionException;
import com.core.solution.model.entity.EntityUser;
import com.core.solution.model.payload.SignupRequest;
import com.core.solution.model.payload.UserRequest;
import com.core.solution.utils.MessagesBussines;
import com.core.solution.utils.Constants;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {


	private final UserRepository userRepository;
	private final PasswordEncoder encoder;

	public EntityUser getUser(String username) throws SolutionException {		
		EntityUser entityUser = null;		
		boolean isValidUsername = this.isValidUsername(username.trim());		
		if(isValidUsername) {			
			entityUser = this.userRepository.getUser(username);			
			if (entityUser == null) {
				throw new SolutionException(String.format(MessagesBussines.MESSAGE_NULL_USERNAME, username),
						new SolutionData(MessagesBussines.SUCCESS, 
								MessagesBussines.TITLE_NULL_USERNAME,
								String.format(MessagesBussines.MESSAGE_NULL_USERNAME, username),
								MessagesBussines.CODE_NULL_USERNAME));
			}			
		} else {			
			throw new SolutionException(MessagesBussines.MESSAGE_ERROR_USERNAME,
					new SolutionData(
							MessagesBussines.SUCCESS, 
							MessagesBussines.TITLE_ERROR_USERNAME,
							MessagesBussines.MESSAGE_ERROR_USERNAME,
							MessagesBussines.CODE_ERROR_USERNAME));			
		}		
		return entityUser;	
	}

	public List<EntityUser> getUsers() throws SolutionException {
		try {
			return this.userRepository.getUsers();	
		} catch (Exception e) {
			throw new SolutionException(MessagesBussines.MESSAGE_ERROR_GET_ALL_USER,
					new SolutionData(
							MessagesBussines.SUCCESS, 
							MessagesBussines.TITLE_ERROR_GET_ALL_USER,
							MessagesBussines.MESSAGE_ERROR_GET_ALL_USER,
							MessagesBussines.CODE_ERROR_GET_ALL_USER));
		}		
	}

	@Transactional
	public void patchUser(UserRequest userRequest) throws SolutionException {
		String username = userRequest.getUsername();
		try {
			this.userRepository.patchUser(userRequest);	
		} catch (Exception e) {
			throw new SolutionException(String.format(MessagesBussines.MESSAGE_ERROR_PATCH_USER, username),
					new SolutionData(
							MessagesBussines.SUCCESS, 
							MessagesBussines.TITLE_ERROR_PATCH_USER,
							String.format(MessagesBussines.MESSAGE_ERROR_PATCH_USER, username),
							MessagesBussines.CODE_ERROR_PATCH_USER));
		}
		
	}

	@Transactional
	public void putUser(UserRequest userRequest) throws SolutionException {
		String username = userRequest.getUsername();
		try {
			this.userRepository.putUser(userRequest);
		} catch (Exception e) {
			throw new SolutionException(String.format(MessagesBussines.MESSAGE_ERROR_PUT_USER, username),
					new SolutionData(
							MessagesBussines.SUCCESS, 
							MessagesBussines.TITLE_ERROR_PUT_USER,
							String.format(MessagesBussines.MESSAGE_ERROR_PUT_USER, username),
							MessagesBussines.CODE_ERROR_PUT_USER));
		}		
	}

	@Transactional
	public SignupRequest signupUser(SignupRequest signupRequest) throws SolutionException {

		boolean isValidUsername = this.isValidUsername(signupRequest.getUsername());
		boolean isValidPassword = this.isValidPassword(signupRequest.getPassword().trim());
		boolean isValidPhone = this.isValidPhone(signupRequest.getPhone());
		boolean isValidEmail = this.isValidEmail(signupRequest.getEmail());
		
		if(!isValidUsername) {
			throw new SolutionException(MessagesBussines.MESSAGE_ERROR_USERNAME,
					new SolutionData(
							MessagesBussines.SUCCESS, 
							MessagesBussines.TITLE_ERROR_USERNAME,
							MessagesBussines.MESSAGE_ERROR_USERNAME,
							MessagesBussines.CODE_ERROR_USERNAME));			
		}	
		
		if(isValidPassword) {
			signupRequest.setPassword(this.encoder.encode(signupRequest.getPassword().trim()));
		} else {
			throw new SolutionException(MessagesBussines.MESSAGE_ERROR_CREATE_PASSWORD,
					new SolutionData(
							MessagesBussines.SUCCESS, 
							MessagesBussines.TITLE_ERROR_CREATE_PASSWORD,
							MessagesBussines.MESSAGE_ERROR_CREATE_PASSWORD,
							MessagesBussines.CODE_ERROR_CREATE_PASSWORD));
		}
		
		if(!isValidPhone) {
			throw new SolutionException(MessagesBussines.MESSAGE_ERROR_PHONE,
					new SolutionData(
							MessagesBussines.SUCCESS, 
							MessagesBussines.TITLE_ERROR_PHONE,
							MessagesBussines.MESSAGE_ERROR_PHONE,
							MessagesBussines.CODE_ERROR_PHONE));	
		}		
		
		if(!isValidEmail) {
			throw new SolutionException(MessagesBussines.MESSAGE_ERROR_EMAIL,
					new SolutionData(
							MessagesBussines.SUCCESS, 
							MessagesBussines.TITLE_ERROR_EMAIL,
							MessagesBussines.MESSAGE_ERROR_EMAIL,
							MessagesBussines.CODE_ERROR_EMAIL));	
		}		
		
		signupRequest.setUserId(this.userRepository.signupUser(signupRequest));	
		
		return signupRequest;
		
	}

	public boolean isValidUsername(String username) {
        if (username == null || username.isEmpty()) {
            return false;
        }
        return Pattern.matches(Constants.LETTERS_ONLY_PATTERN, username);
    }
	
    private boolean isValidPassword(String password) {
        if (password == null || password.length() < Constants.MIN_LENGTH) {
            return false;
        }
        boolean hasLowercase = Pattern.matches(Constants.LOWERCASE_PATTERN, password);
        boolean hasUppercase = Pattern.matches(Constants.UPPERCASE_PATTERN, password);
        boolean hasDigit = Pattern.matches(Constants.DIGIT_PATTERN, password);
        boolean hasSpecialChar = Pattern.matches(Constants.SPECIAL_CHARACTER_PATTERN, password);
        return hasLowercase && hasUppercase && hasDigit && hasSpecialChar;
    }

    private boolean isValidPhone(Long phone) {
        Pattern p = Pattern.compile(Constants.PHONE_PATTERN);
        Matcher m = p.matcher(phone.toString());
        return (m.matches());
    }
    
    public boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return Pattern.matches(Constants.EMAIL_PATTERN, email);
    }
    
}
