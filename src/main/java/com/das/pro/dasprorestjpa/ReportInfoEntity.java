package com.das.pro.dasprorestjpa;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Report")
public class ReportInfoEntity {
	
	@Id
	@Column(name = "report_id")
	String report_id;
	
	

	

	String booking_report_id;
	String patient_report_name;
	String doctor_report_id;
	LocalDate booking_date;
	String response;
	
	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	@Lob
	@Column(name = "pdf_content", columnDefinition = "LONGBLOB")
	byte[] pdfContent;
	
	

		// Generate UUID for booking_id
		public ReportInfoEntity() {
	         this.report_id = UUID.randomUUID().toString();
	    }
	
	@OneToOne
	@JoinColumn(name = "booking_id")
	BookingInfoEntity bookingInfoEntity; 
	
	@ManyToOne(targetEntity = PatientInfoEntity.class)
	@JoinColumn(name="patient_id")
	List<PatientInfoEntity> patientInfoEntity;
	
	@ManyToOne(cascade = CascadeType.ALL, targetEntity = DoctorInfoEntity.class)
	@JoinColumn(name="doctor_id")
	List<DoctorInfoEntity> doctorInfoEntity;

	
	public String getReport_id() {
		return report_id;
	}

	public void setReport_id(String report_id) {
		this.report_id = report_id;
	}
	
	

	public String getBooking_report_id() {
		return booking_report_id;
	}

	public void setBooking_report_id(String booking_report_id) {
		this.booking_report_id = booking_report_id;
	}

	public String getPatient_report_name() {
		return patient_report_name;
	}

	public void setPatient_report_name(String patient_report_name) {
		this.patient_report_name = patient_report_name;
	}

	public String getDoctor_report_id() {
		return doctor_report_id;
	}

	public void setDoctor_report_id(String doctor_report_id) {
		this.doctor_report_id = doctor_report_id;
	}


	
	public LocalDate getBooking_date() {
		return booking_date;
	}

	public void setBooking_date(LocalDate booking_date) {
		this.booking_date = booking_date;
	}

	public BookingInfoEntity getBookingInfoEntity() {
		return bookingInfoEntity;
	}

	public void setBookingInfoEntity(BookingInfoEntity bookingInfoEntity) {
		this.bookingInfoEntity = bookingInfoEntity;
	}

	

	public byte[] getPdfContent() {
		return pdfContent;
	}

	public void setPdfContent(byte[] pdfContent) {
		this.pdfContent = pdfContent;
	}

	public List<PatientInfoEntity> getPatientInfoEntity() {
		return patientInfoEntity;
	}

	public void setPatientInfoEntity(List<PatientInfoEntity> patientInfoEntity) {
		this.patientInfoEntity = patientInfoEntity;
	}

	public List<DoctorInfoEntity> getDoctorInfoEntity() {
		return doctorInfoEntity;
	}

	public void setDoctorInfoEntity(List<DoctorInfoEntity> doctorInfoEntity) {
		this.doctorInfoEntity = doctorInfoEntity;
	}

	
	

	
}
