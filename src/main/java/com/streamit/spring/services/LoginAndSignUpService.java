package com.streamit.spring.services;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.streamit.spring.model.ErrorConstants;
import com.streamit.spring.model.Db.Login;
import com.streamit.spring.model.Db.User;
import com.streamit.spring.model.Dto.RegisterUserDto;
import com.streamit.spring.model.Dto.ResponseDTO;
import com.streamit.spring.repository.UserRepository;

@Service
public class LoginAndSignUpService {
		
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	private Logger logger = LoggerFactory.getLogger(LoginAndSignUpService.class); 

	/**
	 * 
	 * <p>
	 * @param dto
	 * @return
	 */
	public ResponseDTO registerNewUser (RegisterUserDto dto) {
		if (dto.getName().length() <= 0) {
		  return new ResponseDTO(2, ErrorConstants.ERROR_CODE_INVALID_NAME_VALUE, ErrorConstants.ERRMSG_INVALID_NAME_VALUE); 	
		} 
		
		if (dto.getPassword().length() <= 0) {
			return new ResponseDTO(2, ErrorConstants.ERROR_CODE_INVALID_PASSWORD_VALUE, ErrorConstants.ERRMSG_INVALID_PASSWORD_VALUE);
		} 
		
		if (dto.getUserName().length() <= 0) {
			return new ResponseDTO(2, ErrorConstants.ERROR_CODE_INVALID_USERNAME_VALUE, ErrorConstants.ERRMSG_INVALID_USERNAME_VALUE);
		}
		
		if (dto.getEmail().length()<=0) {
			return new ResponseDTO(2, ErrorConstants.ERROR_CODE_INVALID_USEREMAIL_VALUE, ErrorConstants.ERRMSG_INVALID_USEREMAIL_VALUE);
		}
		
		String encodedPassword = encoder.encode(dto.getPassword());
		
		Login login = new Login(dto.getName(), 
								dto.getUserName(),	
								encodedPassword,	
								new Date(), 
								dto.getEmail());
		
		User user = new User(login, 2, new Date());
		
		try {
		    logger.debug("user body to save in db {}", user); 
			userRepository.save(user);
			return new ResponseDTO(1); 
		     
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new ResponseDTO(2, ErrorConstants.ERROR_CODE_INTERNAL_SERVER_ERROR, ErrorConstants.ERRMSG_INTERNAL_SERVER_ERROR);
		}
	} 
	

	
	
}
