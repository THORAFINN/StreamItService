package com.streamit.spring.model.Db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "thumbnail")
public class ThumbNail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "thn_id") 
	private Integer thnId;
	
	@Column(name = "thn_name")
	private String thnName;
	
	@Column(name = "thn_filepath")
	private String thnFilepath;
	
	@Column(name = "thn_filename")
	private String thnFilename;
	
	@Column(name = "thn_ref")
	private String thnRef;
	
	@Column(name = "thn_crd_dt")
	private Date thnCrdDt;

	public ThumbNail(String thnName, String thnFilepath, String thnFilename, String thnRef, Date thnCrdDt) {
		super();
		this.thnName = thnName;
		this.thnFilepath = thnFilepath;
		this.thnFilename = thnFilename;
		this.thnRef = thnRef;
		this.thnCrdDt = thnCrdDt;
	}

	public ThumbNail() {
		super();
	}

	public Integer getThnId() {
		return thnId;
	}

	public void setThnId(Integer thnId) {
		this.thnId = thnId;
	}

	public String getThnName() {
		return thnName;
	}

	public void setThnName(String thnName) {
		this.thnName = thnName;
	}

	public String getThnFilepath() {
		return thnFilepath;
	}

	public void setThnFilepath(String thnFilepath) {
		this.thnFilepath = thnFilepath;
	}

	public String getThnFilename() {
		return thnFilename;
	}

	public void setThnFilename(String thnFilename) {
		this.thnFilename = thnFilename;
	}

	public String getThnRef() {
		return thnRef;
	}

	public void setThnRef(String thnRef) {
		this.thnRef = thnRef;
	}

	public Date getThnCrdDt() {
		return thnCrdDt;
	}

	public void setThnCrdDt(Date thnCrdDt) {
		this.thnCrdDt = thnCrdDt;
	}
	
	
	
	
	
}
