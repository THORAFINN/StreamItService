package com.streamit.spring.model.Db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * create table video (
 *	v_id int auto_increment primary key,
 *	user_id int not null,
 *	v_name varchar(256) not null,
 *	v_file_name varchar (256) not null,
 *	v_file_path varchar(506) not null,
 *	v_file_size int not null,
 *	v_crd_dt datetime not null default now(),
 *  v_ref varchar(128) not null
 *	foreign key (user_id) references login (id));
 * <p>
 * @author acer
 *
 */
@Entity
@Table(name="video")
public class Video {
	
	@Id
	@Column(name="v_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer vId;
	
	@Column(name="user_id",nullable = false)
	private Integer userId;
	
	@Column(name="v_name",nullable = false)
	private String vName;
	
	@Column(name="v_file_name")
	private String vFileName;
	
	@Column(name="v_file_path")
	private String vFilePath;
	
	@Column(name="v_file_size")
	private Long vFileSize;
	
	@Column(name="v_crd_dt")
	private Date vCrdDt;
	
	@Column(name = "v_ref")
	private String vRef;
	
	@Column(name = "thn_id")
	private Integer thnId;
	
	
	
	
	public Video() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Video (Integer userId, String vName, String vFileName, String vFilePath, Long vFileSize, Date vCrdDt, String vRef, Integer thnId ) {
		this.userId = userId;
		this.vName = vName;
		this.vFileName = vFileName;
		this.vFilePath = vFilePath;
		this.vFileSize = vFileSize;
		this.vCrdDt = vCrdDt;
		this.vRef = vRef;
		this.thnId = thnId;
    }

	public Integer getvId() {
		return vId;
	}

	public void setvId(Integer vId) {
		this.vId = vId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getvName() {
		return vName;
	}

	public void setvName(String vName) {
		this.vName = vName;
	}

	public String getvFileName() {
		return vFileName;
	}

	public void setvFileName(String vFileName) {
		this.vFileName = vFileName;
	}

	public String getvFilePath() {
		return vFilePath;
	}

	public void setvFilePath(String vFilePath) {
		this.vFilePath = vFilePath;
	}

	public Long getvFileSize() {
		return vFileSize;
	}

	public void setvFileSize(Long vFileSize) {
		this.vFileSize = vFileSize;
	}

	public Date getvCrdDt() {
		return vCrdDt;
	}

	public void setvCrdDt(Date vCrdDt) {
		this.vCrdDt = vCrdDt;
	}

	public String getvRef() {
		return vRef;
	}

	public void setvRef(String vRef) {
		this.vRef = vRef;
	} 
	
	
}
