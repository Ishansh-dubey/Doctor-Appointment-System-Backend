package com.das.pro.dasprorestjpa;

import java.util.HashSet;
import java.util.List;
import java.util.Set;



import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "Doctor")
public class DoctorInfoEntity {


	@Id
	@Column(name = "doctor_id")
	 String doctor_id;
	 String doctor_name;
	 public String getDoctor_name() {
		return doctor_name;
	}

	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}

	

	String specification;
	 String hospital_address;
	 Long official_contact;
	 String official_email_id;
	 
	 
//
//	 @OneToOne
//    @JoinColumn(name = "user_id" , referencedColumnName = "user_id") // Foreign key reference
//     UserInfoEntity userInfo;
	 
//	 @OneToOne(fetch = FetchType.LAZY)
//	    @PrimaryKeyJoinColumn
//	    private UserInfoEntity userInfo;
	

	 

	@OneToOne(cascade = CascadeType.ALL) //correct one final one
	 @JoinColumn(name = "user_id")
	 UserInfoEntity userInfoEntity;
	 
//	 @OneToOne(mappedBy = "doctorInfoEntity")
//	    UserInfoEntity userInfoEntity;
	
	@ManyToMany(mappedBy = "doctorInfoEntity" , cascade = CascadeType.ALL  ) //correct one final one
	@JsonIgnore
	 List<BookingInfoEntity> bookingInfoEntity ;
	
	@OneToMany(mappedBy = "doctorInfoEntity")
	List<ReportInfoEntity> reportInfoEntity;
	
	

	

	

	
	

	

	public List<ReportInfoEntity> getReportInfoEntity() {
		return reportInfoEntity;
	}

	public void setReportInfoEntity(List<ReportInfoEntity> reportInfoEntity) {
		this.reportInfoEntity = reportInfoEntity;
	}

	public List<BookingInfoEntity> getBookingInfoEntity() {
		return bookingInfoEntity;
	}

	public void setBookingInfoEntity(List<BookingInfoEntity> bookingInfoEntity) {
		this.bookingInfoEntity = bookingInfoEntity;
	}

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

	public void setOfficial_contact(Long long1) {
		this.official_contact = long1;
	}

	public String getOfficial_email_id() {
		return official_email_id;
	}

	public void setOfficial_email_id(String official_email_id) {
		this.official_email_id = official_email_id;
	}

	
	

//	public UserInfoEntity getUserInfo() {
//		return userInfo;
//	}
//
//	public void setUserInfo(UserInfoEntity userInfo) {
//		this.userInfo = userInfo;
//	}

//	public void setUserInfoEntity(UserInfoEntity user) {
//		// TODO Auto-generated method stub
//		
//	}
	
	public UserInfoEntity getUserInfoEntity() {
        return userInfoEntity;
    }

    public void setUserInfoEntity(UserInfoEntity userInfoEntity) {
        this.userInfoEntity = userInfoEntity;
    }

}
