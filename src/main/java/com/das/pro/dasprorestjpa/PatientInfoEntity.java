package com.das.pro.dasprorestjpa;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Patient")
public class PatientInfoEntity {
	
	@Id
	String patient_id;
	LocalDate age;
	Long contact;
	LocalDate booking_date;
	
	@OneToOne(cascade = CascadeType.ALL) //correct one final one
	 @JoinColumn(name = "user_id")
	 UserInfoEntity userInfoEntity;
	
	@OneToMany(mappedBy = "patientInfoEntity")
	List<ReportInfoEntity> reportInfoEntity;

	

	public String getPatient_id() {
		return patient_id;
	}

	public List<ReportInfoEntity> getReportInfoEntity() {
		return reportInfoEntity;
	}

	public void setReportInfoEntity(List<ReportInfoEntity> reportInfoEntity) {
		this.reportInfoEntity = reportInfoEntity;
	}

	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}

	public LocalDate getAge() {
		return age;
	}

	public void setAge(LocalDate localDate) {
		this.age = localDate;
	}

	public Long getContact() {
		return contact;
	}

	public void setContact(Long contact) {
		this.contact = contact;
	}

	public LocalDate getBooking_date() {
		return booking_date;
	}

	public void setBooking_date(LocalDate booking_date) {
		this.booking_date = booking_date;
	}

	public UserInfoEntity getUserInfoEntity() {
		return userInfoEntity;
	}

	public void setUserInfoEntity(UserInfoEntity userInfoEntity) {
		this.userInfoEntity = userInfoEntity;
	}

}
