package com.streamit.spring.common;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.streamit.spring.model.Db.SrchHdr;
import com.streamit.spring.model.Dto.Source;
import com.streamit.spring.repository.SrcHdrRepository;
import com.streamit.spring.security.CryptoGraphy;

@Component
public class SourceInitializer  implements CommandLineRunner{

	
	@Autowired
	private SrcHdrRepository srchHdrRepo;
	
	@Autowired
	private CryptoGraphy cryptoUtil;
	
	private static final Logger logger = LoggerFactory.getLogger(SourceInitializer.class);
	
	
	public static Map<String, Source> srcHdrMap = new HashMap<>();
	
	/**
	 * create a static map of srcHdr entries with their authtoken
	 * <p>
	 * @throws JsonProcessingException -> sr_config parsing is not possible
	 */
	private void generateMaps () throws JsonProcessingException {
		List<SrchHdr> dataList = this.srchHdrRepo.findAll();
		for (SrchHdr srcHdr : dataList) {
			String token = srcHdr.getSrAppid()+"~" +srcHdr.getSrKey();
			String encryptToken = cryptoUtil.encrypt(token);
			Source source = new Source(srcHdr.getSrConfig());
			srcHdrMap.put( encryptToken, source);
			logger.debug(srcHdr.getSrcName()+" = "+encryptToken);
		}
	}
	
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		generateMaps();
	}

}
