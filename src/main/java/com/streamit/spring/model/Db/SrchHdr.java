package com.streamit.spring.model.Db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 *  create table src_hdr (
 *	 sr_id int auto_increment primary key,
 *   sr_name varchar(64) not null,
 *   sr_active int not null,
 *   sr_config json,
 *   sr_moddt datetime default now(),
 *   sr_appid varchar(64) not null,
 *   sr_key varchar(64) not null);
 */
@Entity
@Table(name ="src_hdr")
public class SrchHdr {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sr_id")
	private Integer srId;
	
	@Column(name = "sr_name")
	private String srcName;
	
	@Column(name = "sr_active")
	private Integer srActive;
	
	@Column(name = "sr_config")
	private String srConfig;;
	
	@Column(name = "sr_moddt")
	private Date srModdt;
	
	@Column(name="sr_appid")
	private String srAppid;
	
	@Column(name="sr_key")
	private String srKey; 
	
	public SrchHdr() {
		super();
	}

	public Integer getSrId() {
		return srId;
	}

	public void setSrId(Integer srId) {
		this.srId = srId;
	}

	public String getSrcName() {
		return srcName;
	}

	public void setSrcName(String srcName) {
		this.srcName = srcName;
	}

	public Integer getSrActive() {
		return srActive;
	}

	public void setSrActive(Integer srActive) {
		this.srActive = srActive;
	}

	public String getSrConfig() {
		return srConfig;
	}

	public void setSrConfig(String srConfig) {
		this.srConfig = srConfig;
	}

	public Date getSrModdt() {
		return srModdt;
	}

	public void setSrModdt(Date srModdt) {
		this.srModdt = srModdt;
	}

	public String getSrAppid() {
		return srAppid;
	}

	public void setSrAppid(String srAppid) {
		this.srAppid = srAppid;
	}

	public String getSrKey() {
		return srKey;
	}

	public void setSrKey(String srKey) {
		this.srKey = srKey;
	}  

}
