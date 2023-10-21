package com.streamit.spring.security;

import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CryptoGraphy {
	
	private static final Logger logger = LoggerFactory.getLogger(CryptoGraphy.class);
	
	/**
	 * <p>
	 * @param tokken
	 * @return
	 * @author Roshan
	 */
	public String encrypt (String tokken) {
		logger.debug("tokken to be encrypt {}",tokken);
		String encodedTokken = Base64.getEncoder().encodeToString(tokken.getBytes());
		logger.debug("encoded String {}", encodedTokken);
		return encodedTokken;
	}
	
	/**
	 * 
	 * <p>
	 * @param tokken
	 * @return
	 * @author Roshan
	 */
	public String decrypt (String tokken) {
		logger.debug("tokken to be decrypt {}",tokken);
		String decodedTokken = Base64.getEncoder().encodeToString(tokken.getBytes());
		logger.debug("decoded String {}", decodedTokken);
		return decodedTokken;
	}
	

}
