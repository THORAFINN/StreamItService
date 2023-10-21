package com.streamit.spring.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.streamit.spring.common.SourceInitializer;
import com.streamit.spring.model.ErrorConstants;
import com.streamit.spring.model.StreamItConstants;
import com.streamit.spring.model.Dto.FileUploadReponseDto;
import com.streamit.spring.model.Dto.LoginUserDto;
import com.streamit.spring.model.Dto.ProcessFileDto;
import com.streamit.spring.model.Dto.RegisterUserDto;
import com.streamit.spring.model.Dto.ResponseDTO;
import com.streamit.spring.model.Dto.Source;
import com.streamit.spring.model.Dto.UploadVideoandThumbNailDto;
import com.streamit.spring.services.FileUploadServiceImpl;
import com.streamit.spring.services.LoginAndSignUpService;

/**
 * 
 */
@RestController
@RequestMapping("/streamit")
public class StreamItController {
	
	private static final Logger logger = LoggerFactory.getLogger(StreamItController.class);
	
	@Autowired
	private FileUploadServiceImpl fileUploadService;
	
	@Autowired
	private LoginAndSignUpService loginAndSignUpService;
	
	
	/**
	 * 
	 * <p>
	 * @param searchParam
	 * @param thumbNail
	 * @param dto
	 * @param request
	 * @return
	 */
	@PostMapping(value = StreamItConstants.UPLOAD_FILE_API, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<FileUploadReponseDto> uploadFile (@RequestParam Map<String, String > searchParam ,
															@RequestParam ("thumbnail") MultipartFile thumbNail,
												            @ModelAttribute UploadVideoandThumbNailDto dto, 
												            HttpServletRequest request) {
		
		logger.info("Streamit Upload video api start");
		logger.debug ("searchParams {}", searchParam);
		logger.debug("request body {}", dto);
		
		String authTokken = request.getHeader(StreamItConstants.AUTHORIZATION);
		boolean isValid = iSValidTokken (authTokken, StreamItConstants.UPLOAD_FILE_API_TOKEN);
		if (!isValid) {
			logger.info("invalid auth tokken {}", authTokken);
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		dto.setThumbNail(thumbNail);
		FileUploadReponseDto response;
		try {
			response = fileUploadService.saveFileInDb( dto);
			logger.info("Streamit Upload video api end");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * 
	 * <p>
	 * @param dto
	 * @param request
	 * @return
	 */
	@PostMapping(StreamItConstants.PROCESS_FILE_API)
	public ResponseEntity<ResponseDTO> processFile (@RequestBody ProcessFileDto dto, HttpServletRequest request){
		logger.info("Streamit Upload video api start");
		logger.debug ("request body  {}", dto);
		
		String authTokken = request.getHeader(StreamItConstants.AUTHORIZATION);
		boolean isValid = iSValidTokken (authTokken, StreamItConstants.PROCESS_FILE_API_TOKEN);
		if (!isValid) {
			logger.info("invalid auth tokken {}", authTokken);
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		try {
			ResponseDTO response = fileUploadService.processFile(dto, dto.getUserId());
			logger.info("Streamit process video api end");
			return new ResponseEntity<> (response, HttpStatus.OK) ;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * 
	 * <p>
	 * @param registerUserDto
	 * @return
	 */
	@PostMapping(StreamItConstants.SIGN_UP_NEW_USER)
	public ResponseEntity<ResponseDTO> registerNewUser (@RequestBody RegisterUserDto registerUserDto,  HttpServletRequest request ){
		logger.info("StreamIt register New User api starts");
		logger.debug("request Body {}", registerUserDto);
		String authTokken = request.getHeader(StreamItConstants.AUTHORIZATION);
		boolean isValid = iSValidTokken (authTokken, StreamItConstants.REGISTER_USER_API_TOKEN);
		if (!isValid) {
			logger.info("invalid auth tokken {}", authTokken);
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		ResponseDTO responseDto = loginAndSignUpService.registerNewUser(registerUserDto);
		logger.debug("response from service {}", responseDto);
		logger.info("StreamIt register New User api ends");
		return new ResponseEntity<>(responseDto,HttpStatus.OK);
	}
	
	/**
	 * 
	 * <p>
	 * @param authToken
	 * @return
	 */
	public boolean iSValidTokken (String authToken, int apiToken) {
		if (authToken == null || authToken.length()<=0) {
			return false;
		}
		Source source = SourceInitializer.srcHdrMap.get(authToken);
		if (source == null) {
			return false;
		} 
		if (!source.getValidApis().contains(apiToken)) {
			return false;
		}
		return true;
	}
	
	
	
	
	
	

}
