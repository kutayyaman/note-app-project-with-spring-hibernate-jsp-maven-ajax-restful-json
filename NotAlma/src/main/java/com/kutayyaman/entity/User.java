package com.kutayyaman.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String username;
	
	private String name;
	private String surname;
	private String email;
	private String password;
	private boolean active=false;
	private Date create_date=new Date();
	private String keyreg;
	
	@Transient //pass2'yi veritabaninda user tablosunda bir column olarak olusturmasini istemedigim icin yazdim.bu pass2'yi kullanici uye olurken 2. kez yazdigi kontrol amacli sifre icin kullancam cunku veritabaninda ihtiyacimiz yok.
	private String pass2;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	
	public String getKeyreg() {
		return keyreg;
	}
	public void setKeyreg(String keyreg) {
		this.keyreg = keyreg;
	}
	public String getPass2() {
		return pass2;
	}
	public void setPass2(String pass2) {
		this.pass2 = pass2;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", name=" + name + ", surname=" + surname + ", email="
				+ email + ", password=" + password + ", active=" + active + ", create_date=" + create_date + ", keyreg="
				+ keyreg + ", pass2=" + pass2 + "]";
	}
	
	
	
	
}
