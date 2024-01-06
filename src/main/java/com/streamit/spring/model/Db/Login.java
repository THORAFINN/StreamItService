package com.streamit.spring.model.Db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * create table login (
 *	id int auto_increment primary key,
 *	name varchar(256) not null,
 *	username varchar(100) not null,
 *	password varchar(30) not null,
 *	crdt_dt datetime not null default now(),
 *	mod_dt datetime );
 * 
 * <p>
 * @author acer
 *
 */
@Entity
@Table(name="login")
public class Login {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "username")
	private String username ;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "crdt_dt")
	private Date crdtDt;
	
	@Column(name = "mod_dt")
	private Date modDt ;
	
	@Column(name = "email")
	private String email;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCrdtDt() {
		return crdtDt;
	}

	public void setCrdtDt(Date crdtDt) {
		this.crdtDt = crdtDt;
	}

	public Date getModDt() {
		return modDt;
	}

	public void setModDt(Date modDt) {
		this.modDt = modDt;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Login() {
		super();
	}

	public Login(String name, String username, String password, Date crdtDt, String email) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.crdtDt = crdtDt;
		this.email = email;
	}
	
	
	

}
