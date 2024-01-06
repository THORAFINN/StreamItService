package com.streamit.spring.model.Db;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *  create table user (
 *  uid int primary key,
 *  lid int references login(id),
 *  rid int not null default 0,
 *  crddt datetime  not null default now(),
 *  moddt datetime    
 *  );
 */

@Entity
@Table(name = "user")
public class User {
	
	@Id
	@Column(name = "uid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer uid;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "lid")
	private Login login;
	
	@Column(name = "rid")
	private Integer rid;
	
	@Column(name = "crddt")
	private Date crdDt;
	
	@Column(name = "moddt")
	private Date modDt;
	
	public User (Login login, int rid, Date crdDt) {
		this.login = login;
		this.rid = rid;
		this.crdDt = crdDt;
	}
	
	public User () {
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public Date getCrdDt() {
		return crdDt;
	}

	public void setCrdDt(Date crdDt) {
		this.crdDt = crdDt;
	}

	public Date getModDt() {
		return modDt;
	}

	public void setModDt(Date modDt) {
		this.modDt = modDt;
	}
	
	
}
