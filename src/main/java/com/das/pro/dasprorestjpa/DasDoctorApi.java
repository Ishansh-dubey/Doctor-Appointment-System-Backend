package com.das.pro.dasprorestjpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DasDoctorApi {
	
	@Autowired
	DoctorRepo doctorRepo;
	
	@GetMapping("/doctorlist")
	public ResponseEntity<List<DoctorInfoEntity>> getDoctorList() {
		List<DoctorInfoEntity> doctorList = (List<DoctorInfoEntity>) doctorRepo.findAll();
		return ResponseEntity.ok(doctorList);
		
	}

}
