package com.das.pro.dasprorestjpa;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BookingRepo extends CrudRepository<BookingInfoEntity, String> {

//	List<BookingInfoEntity> findAllByDoctorId(String doctor_id);
//	 @Query("SELECT b FROM BookingInfoEntity b JOIN b.doctorInfoEntity d WHERE d.doctor_id = :doctorId AND (b.booking_date < :bookingDate OR (b.booking_date = :bookingDate AND b.booking_time < :bookingTime))")
//	    List<BookingInfoEntity> findByDoctorAndBookingDateBefore(@Param("doctorId") String doctorId, @Param("bookingDate") LocalDate bookingDate, @Param("bookingTime") LocalTime bookingTime);
//
//	    @Query("SELECT b FROM BookingInfoEntity b JOIN b.doctorInfoEntity d WHERE d.doctor_id = :doctorId AND (b.booking_date = :bookingDate AND b.booking_time > :bookingTime)")
//	    List<BookingInfoEntity> findByDoctorAndBookingDateAndBookingTimeAfter(@Param("doctorId") String doctorId, @Param("bookingDate") LocalDate bookingDate, @Param("bookingTime") LocalTime bookingTime);

//		List<BookingInfoEntity> findByDoctorId(String doctor_id);

}

