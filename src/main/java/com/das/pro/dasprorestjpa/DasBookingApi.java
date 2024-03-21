package com.das.pro.dasprorestjpa;



import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DasBookingApi {

	@Autowired
	private BookingRepo bookRepo;
	
	@Autowired
	private DoctorRepo doctorRepo;
	
	@Autowired
	private ReportRepo reportRepo;

	

	@PostMapping("/Patientbooking")
	public ResponseEntity<Map<String, Object>> signup(@RequestBody BookingInfoEntity patient) {
		Map<String, Object> response = new HashMap<>();
		try {
			if (
					patient.getPatient_name() == null || patient.getPatient_name().trim().isEmpty() ||
					
					patient.getAge() == 0 || 
					patient.getContact() == 0 ||
					patient.getBooking_date() == null || 
					patient.getBooking_time() == null || 
					patient.getBooking_type() == null || 
					patient.getPayment_mode() == null 
					) {
				response.put("error", "All fields are required");
				return ResponseEntity.badRequest().body(response);
			}
			


			System.out.println("Booking success");
			
			bookRepo.save(patient);
			
			response.put("booking_id", patient.getBooking_id());
			response.put("booking_name", patient.getPatient_name()); 
			response.put("booking_date", patient.getBooking_date());
			response.put("booking_time", patient.getBooking_time());
			response.put("booking_type", patient.getBooking_type());
			
//			ReportInfoEntity reportInfo = new ReportInfoEntity();
//			reportInfo.setReport_id(patient.getBooking_id());
//			reportInfo.setPatient_report_name(patient.getPatient_name());
//			reportInfo.setDoctor_report_id(patient.getDoctor_id());
//			reportInfo.setBookingInfoEntity(patient);
//			
//			reportRepo.save(reportInfo);

			return ResponseEntity.ok(response);
		} catch (Exception e) {
			System.out.println("failed");
			response.put("error", "Booking Failed");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
//	  @GetMapping("/past/{doctor_id}")
//	    public ResponseEntity<List<BookingInfoEntity>> getPastBookings(@PathVariable String doctor_id) {
//	        // Assuming bookingDate is before today's date for past bookings
//	        List<BookingInfoEntity> pastBookings = bookRepo.findByDoctorAndBookingDateBefore(doctor_id, LocalDate.now(), LocalTime.now());
//	        return ResponseEntity.ok(pastBookings);
//	    }
//
//	    // Get live updates for a doctor (upcoming bookings for today)
//	    @GetMapping("/live/{doctor_id}")
//	    public ResponseEntity<List<BookingInfoEntity>> getLiveUpdates(@PathVariable String doctor_id) {
//	        // Assuming bookingTime is after current time for live updates
//	        List<BookingInfoEntity> liveUpdates = bookRepo.findByDoctorAndBookingDateAndBookingTimeAfter(doctor_id, LocalDate.now(), LocalTime.now());
//	        return ResponseEntity.ok(liveUpdates);
//	    }
//	@GetMapping("/docBooking/{doctor_id}")
//	public ResponseEntity<List<BookingInfoEntity>> getBooking(@PathVariable String doctor_id){
//		List<BookingInfoEntity> bookings = bookRepo.findByDoctorId(doctor_id);
//		return ResponseEntity.ok(bookings);
//	}
	
//	 @GetMapping("/bookings/{doctor_id}")
//	    public List<BookingInfoEntity> getDoctorBookings(@PathVariable String doctor_id) {
//	        // Retrieve the list of bookings for the specified doctorId
//	        List<BookingInfoEntity> doctorBookings = bookRepo.findAllByDoctorId(doctor_id);
//
//	        // You may want to perform additional processing or filtering here
//	        
//	        return doctorBookings;
//	    }
	 @GetMapping("/booking")
	 public ResponseEntity<List<BookingInfoEntity>> getDoctorBookings() {
			List<BookingInfoEntity> doctorBookings = (List<BookingInfoEntity>) bookRepo.findAll();
			return ResponseEntity.ok(doctorBookings);
			
		}

}
