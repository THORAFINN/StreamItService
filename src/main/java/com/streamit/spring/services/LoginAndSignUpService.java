package com.streamit.spring.services;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.streamit.spring.model.ErrorConstants;
import com.streamit.spring.model.Db.Login;
import com.streamit.spring.model.Dto.LoginUserDto;
import com.streamit.spring.model.Dto.RegisterUserDto;
import com.streamit.spring.model.Dto.ResponseDTO;
import com.streamit.spring.repository.LoginRepository;
import com.streamit.spring.security.CryptoGraphy;
import com.streamit.spring.repository.LoginRepository;

@Service
public class LoginAndSignUpService {
	
	@Autowired
	private LoginRepository  loginRepo;
	
	@Autowired
	private CryptoGraphy cryptoUtil;
	
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
		
		Login login = new Login(dto.getName(), 
								dto.getUserName(),	
								dto.getPassword(),	
								new Date());
		
		try {
		    logger.debug("login body to save in db {}", login); 
			loginRepo.save(login);	
			return new ResponseDTO(1); 
		     
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return new ResponseDTO(2, ErrorConstants.ERROR_CODE_INTERNAL_SERVER_ERROR, ErrorConstants.ERRMSG_INTERNAL_SERVER_ERROR);
		}
	} 
	

	
	
}
