package com.streamit.spring.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.streamit.spring.model.ErrorConstants;
import com.streamit.spring.model.Db.ThumbNail;
import com.streamit.spring.model.Db.Video;
import com.streamit.spring.model.Dto.FileDataDto;
import com.streamit.spring.model.Dto.FileUploadReponseDto;
import com.streamit.spring.model.Dto.ProcessFileDto;
import com.streamit.spring.model.Dto.ResponseDTO;
import com.streamit.spring.model.Dto.UploadVideoandThumbNailDto;
import com.streamit.spring.repository.ThumbNailRepository;
import com.streamit.spring.repository.VideoRepository;

/**
 * 
 */
@Service
public class FileUploadServiceImpl {
	
	
	private Logger logger = LoggerFactory.getLogger(FileUploadServiceImpl.class );
	
	@Autowired
	private VideoRepository videoRepo; 
	
	@Autowired
	private ThumbNailRepository thumbNailRep;
	
	@Value("${video.folder.path}")
	private String filePath;
	
	/**
	 * 
	 * <p>
	 * @param userId
	 * @param dto
	 * @return
	 * @throws IOException
	 */
	public FileUploadReponseDto saveFileInDb ( UploadVideoandThumbNailDto dto) throws IOException {
		MultipartFile video = dto.getVideo();
		MultipartFile thumbNail = dto.getThumbNail();

		
		String originalFilename = video.getOriginalFilename();
		String videoExtension = StringUtils.getFilenameExtension(originalFilename);
		if (videoExtension == null) {
			return new FileUploadReponseDto(2, ErrorConstants.ERROR_CODE_INVALID_VIDEO_UPLOAD, ErrorConstants.ERRMSG_INVALID_VIDEO_UPLOAD);
		}
		
		String thumbNailExtension = StringUtils.getFilenameExtension(thumbNail.getOriginalFilename());
		if (thumbNailExtension == null) {
			return new FileUploadReponseDto(2, ErrorConstants.ERROR_CODE_MISSING_THUMBNAIL_TITLE, ErrorConstants.ERRMSG_MISSING_THUMBNAIL_TITLE);
		}
		
		FileDataDto thumbNailFileDetail = null;
		try {
			 thumbNailFileDetail = saveThumbNail(thumbNail, thumbNail.getOriginalFilename());
		} catch (Exception e) {
			logger.error("error occurred when saving thumbnail data");
			logger.error(e.getMessage(),e);
			return new FileUploadReponseDto(2,
											ErrorConstants.ERROR_CODE_INTERNAL_SERVER_ERROR,
											ErrorConstants.ERRMSG_INTERNAL_SERVER_ERROR + " error occurred when saving thumbnail data");
		}
		
		FileDataDto videoFileDetails = null;
		try {
		    videoFileDetails = saveVideoFile(video, originalFilename);
		} catch (Exception e) {
			logger.error("error occurred when saving video data");
			logger.error(e.getMessage(),e);
			return new FileUploadReponseDto(2,
										    ErrorConstants.ERROR_CODE_INTERNAL_SERVER_ERROR,
										    ErrorConstants.ERRMSG_INTERNAL_SERVER_ERROR + " error occurred when saving video data");
		}
		
		return new  FileUploadReponseDto(1,
										 videoFileDetails.getFilePath(),
										 videoFileDetails.getFileName(),
										 thumbNailFileDetail.getFilePath(), 
				                         thumbNailFileDetail.getFileName());
	}
	
	/**
	 * 
	 * <p>
	 * @param videofile
	 * @param originalFilename
	 * @return
	 * @throws Exception
	 */
	private FileDataDto saveVideoFile (MultipartFile videofile, String originalFilename) throws Exception {
		File targetFile = null;
		try ( InputStream inputStream = videofile.getInputStream() ) {
			byte[] buffer = new byte[inputStream.available()];
			inputStream.read(buffer);
			Calendar instance = Calendar.getInstance();
			int year = instance.get(Calendar.YEAR);
			int month = instance.get(Calendar.MONTH);
		    String folderName =  year +"-"+month;
		    File directory = new  File(filePath +"/"+ folderName);
		    if (! directory.exists()) {
		    	directory.mkdir();
		    }
		    targetFile = new File(directory, originalFilename);
		    OutputStream outStream = new FileOutputStream(targetFile);
		    outStream.write(buffer);
		    outStream.close();
		    
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		   throw new Exception(e.getMessage(), e.getCause());
		}
	
		FileDataDto videoDataDto = new FileDataDto(targetFile.getName(), 
								  				   targetFile.getAbsolutePath());
		return videoDataDto;
	}
	
	/**
	 * 
	 * <p>
	 * @param thumbNailfile
	 * @param originalFilename
	 * @param userId
	 * @param title
	 * @return
	 * @throws Exception 
	 */
	private FileDataDto saveThumbNail(MultipartFile thumbNailfile, String originalFilename) throws Exception {
	  File targetFile = null;
		try ( InputStream inputStream = thumbNailfile.getInputStream() ) {
			byte[] buffer = new byte[inputStream.available()];
			inputStream.read(buffer);
			Calendar instance = Calendar.getInstance();
			int year = instance.get(Calendar.YEAR);
			int month = instance.get(Calendar.MONTH);
		    String folderName =  year +"-"+month;
		    File directory = new  File(filePath +"/"+ folderName);
		    if (! directory.exists()) {
		    	directory.mkdir();
		    }
		    targetFile = new File(directory, originalFilename);
		    OutputStream outStream = new FileOutputStream(targetFile);
		    outStream.write(buffer);
		    outStream.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage(), e.getCause());
		}
		
        FileDataDto thumbNailDto = new FileDataDto(targetFile.getName(), 
												   targetFile.getAbsolutePath());
		return thumbNailDto;
	} 
	
	
	/**
	 * 
	 * <p>
	 * @param dto
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	public ResponseDTO processFile (ProcessFileDto dto, Integer userId) throws IOException {
		
		ResponseDTO response = validateRequest(dto);
		if (response != null) {
			return response;
		}
		
		ThumbNail thumbNail = saveDataOfThumbNail(dto, userId);
		
		saveDataOfVideo(dto, userId, thumbNail.getThnId());
		
		return new ResponseDTO(1) ;
	}
	
	/**
	 * 
	 * <p>
	 * @param dto
	 * @return
	 */
	private ResponseDTO validateRequest (ProcessFileDto dto) {
		
		String videoTitle = dto.getVideoTitle();
		String thumbNailTitle = dto.getThumbNailTitle();
		String videoFileName = dto.getVideoFileName();
		String videoFilePath = dto.getVideoFilePath();
		String thubnailFileName = dto.getThubnailFileName();
		String thumbnailFilePath = dto.getThumbnailFilePath();
		
		if (videoTitle == null || videoTitle.length() <= 0) {
			return new  ResponseDTO(2, ErrorConstants.ERROR_CODE_MISSING_VIDEO_TITLE, ErrorConstants.ERRMSG_MISSING_VIDEO_TITLE);
		} else if (thumbNailTitle == null || thumbNailTitle.length() <= 0) {
			return new ResponseDTO(2,ErrorConstants.ERROR_CODE_MISSING_THUMBNAIL,ErrorConstants.ERROR_CODE_MISSING_THUMBNAIL);
		} else if (videoFileName == null || videoFileName.length() <= 0) {
			return new ResponseDTO(2);
		} else if (videoFilePath == null || videoFilePath.length() <= 0) {
			return new ResponseDTO(2);
		} else if (thubnailFileName == null || thubnailFileName.length() <= 0) {
			return new ResponseDTO(2);
		} else if (thumbnailFilePath == null || thumbnailFilePath.length() <= 0) {
			return new ResponseDTO(2);
		} else {
			return null;
		}
		
	}
	
	/**
	 * 
	 * <p>
	 * @param dto
	 * @param userId
	 * @return
	 */
	private ThumbNail saveDataOfThumbNail (ProcessFileDto dto, int userId) {
		String baseFileRef = userId+"thumb"+System.currentTimeMillis();
		String thumbRef = Base64.getEncoder().encodeToString(baseFileRef.getBytes());
        ThumbNail thumbnail	= new ThumbNail(dto.getThumbNailTitle(), 
        									dto.getThubnailFileName(), 
        									dto.getThumbnailFilePath(),
        									thumbRef,
        									new Date());	
		return this.thumbNailRep.save(thumbnail);
	}
	
	
	/**
	 * 
	 * <p>
	 * @param dto
	 * @param userId
	 * @param thumNailId
	 * @return
	 * @throws IOException
	 */
	private Video saveDataOfVideo (ProcessFileDto dto, int userId, int thumNailId) throws IOException {
		String baseFileRef = userId+"video"+System.currentTimeMillis();
		String videoRef = Base64.getEncoder().encodeToString(baseFileRef.getBytes());
		
		Path videoPath = Paths.get(dto.getVideoFilePath());
		long videoSize = Files.size(videoPath);
		Video videoDb = new Video(userId, 
				                  dto.getVideoTitle(), 
								  dto.getVideoFileName(), 
								  dto.getVideoFilePath(),
								  videoSize,
								  new Date(),
								  videoRef,
								  thumNailId);
		
		return this.videoRepo.save(videoDb);
	}
	
	
	
}
