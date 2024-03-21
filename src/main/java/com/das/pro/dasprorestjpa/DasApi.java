package com.das.pro.dasprorestjpa;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties.Credential;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class DasApi {

	@Autowired
	private LoginRepo repo;

	@Autowired
	private DoctorRepo doctorRepo;
	
	@Autowired
	private PatientRepo patientRepo;



	@GetMapping("/getdata")
	public String GetData() {
		Optional<UserInfoEntity> item = repo.findById("ishansh");
		if(item.isPresent()) {
			System.out.println(item.get().date_of_birth);
			return "Successfull";
		} else {
			return "Failure";	
		}
	}


	@PutMapping("/updatedata")
	public String UpdateData() {
		Optional<UserInfoEntity> item = repo.findById("ishansh");
		if(item.isPresent()) {
			UserInfoEntity existingEntity = item.get();
			existingEntity.setAddress("USA");
			repo.save(existingEntity);
			return "Entity updated successfully";
		} else {

			return "Entity with ishansh not found";
		}
	}


	@DeleteMapping("/deletedata")
	public String DeleteData() {
		Optional<UserInfoEntity> item = repo.findById("ishansh");
		if(item.isPresent()) {
			UserInfoEntity existingEntity = item.get();

			repo.delete(existingEntity);
			return "Entity Deleted successfully";
		} else {

			return "Failed";
		}
	}


	//	@PostMapping(path = "/signinuser")
	//	public UserInfoEntity getDataBody(@RequestBody UserInfoEntity credential) {
	//		System.out.println(credential);
	//		UserInfoEntity res = new UserInfoEntity();
	//
	//		// Check if the credential is not null
	//		if (credential != null) {
	//			// Retrieve the user entity from the database based on the provided user_id
	//			Optional<UserInfoEntity> item = repo.findById(credential.getUser_id());
	//
	//			// Check if the user entity exists and the password matches
	//			if (item.isPresent() && item.get().getPassword().equals(credential.getPassword())) {
	//				
	//				System.out.println("Success");
	//				res.setResponse("success");
	//			} else {
	//				System.out.println("Failure");
	//				res.setResponse("failure");
	//			}
	//		} else {
	//			System.out.println("Credential is null");
	//			res.setResponse("failure");
	//		}
	//
	//		return res;
	//	}

	@PostMapping(path = "/signinuser")
	public ResponseEntity<Map<String, Object>> getDataBody(@RequestBody UserInfoEntity credential) {
		System.out.println(credential);
		Map<String, Object> response = new HashMap<>();

		// Check if the credential is not null
		if (credential != null) {
			// Retrieve the user entity from the database based on the provided user_id
			Optional<UserInfoEntity> item = repo.findById(credential.getUser_id());

			// Check if the user entity exists and the password matches
			if (item.isPresent() && item.get().getPassword().equals(credential.getPassword())) {
				System.out.println("Success");
				// Return user information as JSON response
				response.put("user", item.get());
				response.put("role", item.get().getRole());
				System.out.println("doc not pres.");

				//Fetch the doctor info entity data
				Optional<DoctorInfoEntity> doctorItem = doctorRepo.findById(credential.getUser_id());
				if (doctorItem.isPresent()) {
	                // Add doctor information to response
	                response.put("doctor", doctorItem.get());
	                System.out.println("Doctor information found");
	            } else {
	                System.out.println("No doctor information found for user: " + credential.getUser_id());
	            }
				response.put("message", "success");
				return ResponseEntity.ok(response);
			} else {
				System.out.println("Failure");
				response.put("message", "failure");
				return ResponseEntity.ok(response);
			}
		} else {
			System.out.println("Credential is null");
			response.put("message", "failure");
			return ResponseEntity.ok(response);
		}
	}


	//	@GetMapping("/doctors/{user_id}") //To get doctor info
	//	public ResponseEntity<Map<String, Object>> getDoctorInfo(@PathVariable String user_id) {
	//	    try {
	//	        // Retrieve doctor information based on the user_id
	//	        Optional<DoctorInfoEntity> doctor = doctorRepo.findById(user_id);
	//	        if (doctor.isPresent()) {
	//	            // Construct response object with doctor information
	//	            Map<String, Object> response = new HashMap<>();
	//	            response.put("user", doctor.get()); // Add doctor details
	//	            response.put("message", "success");
	//	            return ResponseEntity.ok(response);
	//	        } else {
	//	            // Doctor not found
	//	            Map<String, Object> response = new HashMap<>();
	//	            response.put("message", "Doctor not found");
	//	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	//	        }
	//	    } catch (Exception e) {
	//	        // Error occurred
	//	        Map<String, Object> response = new HashMap<>();
	//	        response.put("message", "Error fetching doctor information");
	//	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	//	    }
	//	}




	@PostMapping("/signupdata")
	public ResponseEntity<Map<String, Object>> signup(@RequestBody UserInfoEntity user) {
		System.out.println("signup");
		Map<String, Object> response = new HashMap<>();
		try {
			if (

					user.getUser_id() == null || user.getUser_id().trim().isEmpty() ||
					user.getName() == null || user.getName().trim().isEmpty() ||
					user.getDate_of_birth() == null ||                   
					user.getAddress() == null || user.getAddress().trim().isEmpty() ||
					user.getContact() == 0 || 
					user.getContact() == 0 ||
					user.getRole() == null || user.getRole().trim().isEmpty() ||
					user.getEmail_id() == null || user.getEmail_id().trim().isEmpty() ||
					user.getPassword() == null || user.getPassword().trim().isEmpty()
					) {
				response.put("error", "All fields are required");
				return ResponseEntity.badRequest().body(response);
			}
			if (user.getRole().equalsIgnoreCase("Doctor")) {
				// If user's role is Doctor, save doctor information separately
				DoctorInfoEntity doctorInfo = new DoctorInfoEntity();
				doctorInfo.setDoctor_id(user.getDoctor_id());
				doctorInfo.setDoctor_name(user.getName());
				doctorInfo.setSpecification(user.getSpecification());
				doctorInfo.setHospital_address(user.getHospital_address());
				doctorInfo.setOfficial_contact(user.getOfficial_contact());
				doctorInfo.setOfficial_email_id(user.getOfficial_email_id());
				doctorInfo.setUserInfoEntity(user); 

				doctorRepo.save(doctorInfo);
				System.out.println("doctor role");
			}
				
				if(!user.getRole().equalsIgnoreCase("Doctor")) {
					PatientInfoEntity patientInfo = new PatientInfoEntity();
					patientInfo.setPatient_id(user.getUser_id());
					patientInfo.setAge(user.getDate_of_birth());
					patientInfo.setContact(user.getContact());
					patientInfo.setUserInfoEntity(user);
					
					patientRepo.save(patientInfo);
					System.out.println("patient role");
				}

//				// Clear doctor-related fields in UserInfoEntity
//				user.setDoctor_id(null);
//				user.setSpecification(null);
//				user.setHospital_address(null);
//				user.setOfficial_contact(null);
//				user.setOfficial_email_id(null);
			
			System.out.println("Signup success");
			repo.save(user);
			response.put("message", "Signup successfull");

			return ResponseEntity.ok(response);
		} catch (Exception e) {
			System.out.println("failed");
			response.put("error", "Signup Failed");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}


	}

	//	@PostMapping("/signupdata")
	//    public ResponseEntity<DoctorInfoEntity> createDoctor(@RequestBody DoctorInfoEntity doctorInfo) {
	//        DoctorInfoEntity savedDoctorInfo = doctorRepo.save(doctorInfo);
	//        return ResponseEntity.ok(savedDoctorInfo);
	//    }
}




