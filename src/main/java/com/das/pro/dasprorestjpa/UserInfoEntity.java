package com.das.pro.dasprorestjpa;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Login")
public class UserInfoEntity {
	
	@Id
	String user_id;
	String name;
	LocalDate date_of_birth;
	String address;
	Long contact;
	Long other_contact;
	
	String role;
	String email_id;
	String response;
	String password;
	
	
	 String doctor_id;
		
	 String specification;
	 String hospital_address;
	 Long official_contact;
	 String official_email_id;

//	 @OneToOne(mappedBy = "userInfo", cascade = CascadeType.ALL)
//	     DoctorInfoEntity doctorInfo;
//	
	 
//	 @OneToOne(mappedBy = "userInfo", fetch = FetchType.LAZY)
//	    private DoctorInfoEntity doctorInfo;
	 
	 @OneToOne(mappedBy = "userInfoEntity" , cascade = CascadeType.ALL) //correct one final one
	 DoctorInfoEntity doctorInfoEntity;
	 
	 @OneToOne(mappedBy = "userInfoEntity" , cascade = CascadeType.ALL) //correct one final one
	 PatientInfoEntity patientInfoEntity;
//	 @OneToOne(cascade = CascadeType.ALL)
//	    @JoinColumn(name = "doctor_id")
//	    DoctorInfoEntity doctorInfoEntity;
	
	public String getDoctor_id() {
		return doctor_id;
	}
	public void setDoctor_id(String doctor_id) {
		this.doctor_id = doctor_id;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public String getHospital_address() {
		return hospital_address;
	}
	public void setHospital_address(String hospital_address) {
		this.hospital_address = hospital_address;
	}
	public Long getOfficial_contact() {
		return official_contact;
	}
	public void setOfficial_contact(Long official_contact) {
		this.official_contact = official_contact;
	}
	public String getOfficial_email_id() {
		return official_email_id;
	}
	public void setOfficial_email_id(String official_email_id) {
		this.official_email_id = official_email_id;
	}
	
	
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(LocalDate date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getContact() {
		return contact;
	}
	public void setContact(Long contact) {
		this.contact = contact;
	}
	public Long getOther_contact() {
		return other_contact;
	}
	public void setOther_contact(Long other_contact) {
		this.other_contact = other_contact;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
//	public DoctorInfoEntity getDoctorInfo() {
//		return doctorInfo;
//	}
//	public void setDoctorInfo(DoctorInfoEntity doctorInfo) {
//		this.doctorInfo = doctorInfo;
//	}

	
	

	
	
}
