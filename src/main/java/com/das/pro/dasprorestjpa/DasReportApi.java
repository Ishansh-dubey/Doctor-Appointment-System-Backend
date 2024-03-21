package com.das.pro.dasprorestjpa;



import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class DasReportApi {
	
	@Autowired
	private ReportRepo reportRepo;
	

	
//	@PostMapping("/uploadReport")
//    public ResponseEntity<String> uploadReport( 
//    		@RequestParam("booking_report_id") String bookingId,
//          @RequestParam("patient_report_name") String patientReportName,
//          @RequestParam("doctor_report_id") String doctorReportId,
//          @RequestParam("booking_date") LocalDate bookingDate,
//          @RequestParam("pdf") MultipartFile file) {
//        String fileName = file.getOriginalFilename();
//        try {
//        	// Convert MultipartFile to byte array
//            byte[] fileContent = file.getBytes();
//            
//         // Create a new ReportInfoEntity instance
//            ReportInfoEntity uploadedReport = new ReportInfoEntity();
//            uploadedReport.setBooking_report_id(bookingId);
//            uploadedReport.setPatient_report_name(patientReportName);
//            uploadedReport.setDoctor_report_id(doctorReportId);
//            uploadedReport.setBooking_date(bookingDate);
//            uploadedReport.setPdfContent(fileContent);
//            reportRepo.save(uploadedReport);
//            System.out.println("upload success");
//            return ResponseEntity.ok().body("File uploaded successfully. ID: " + uploadedReport.getReport_id());
//        } catch (Exception e) {
//        	System.out.println("fail to upload");
//            return ResponseEntity.badRequest().body("Failed to upload file " + fileName + ": " + e.getMessage());
//        }
//	}
	@PostMapping("/uploadReport")
    public ResponseEntity<Map<String, Object>> uploadReport( 
    		@RequestParam("booking_report_id") String bookingId,
          @RequestParam("patient_report_name") String patientReportName,
          @RequestParam("doctor_report_id") String doctorReportId,
          @RequestParam("booking_date") LocalDate bookingDate,
          @RequestParam("pdf") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        Map<String, Object> response = new HashMap<>(); // Initialize the response map

        try {
        	// Convert MultipartFile to byte array
            byte[] fileContent = file.getBytes();
            
         // Create a new ReportInfoEntity instance
            ReportInfoEntity uploadedReport = new ReportInfoEntity();
            uploadedReport.setBooking_report_id(bookingId);
            uploadedReport.setPatient_report_name(patientReportName);
            uploadedReport.setDoctor_report_id(doctorReportId);
            uploadedReport.setBooking_date(bookingDate);
            uploadedReport.setPdfContent(fileContent);
            reportRepo.save(uploadedReport);
            System.out.println("upload success");
            
            response.put("message", "File uploaded successfully");
            response.put("report_id", uploadedReport.getReport_id());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
        	System.out.println("fail to upload");
        	response.put("error", "Booking Failed");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
	}
	
//	@GetMapping("/downloadReport")
//	public ResponseEntity<Map<String, Object>

	@GetMapping("/downloadReport")
	public ResponseEntity<byte[]> downloadReport(@RequestParam("report_id" ) String report_id) {
		System.out.println(report_id);
	    try {
	        // Retrieve the report from the database based on the booking ID
	        Optional<ReportInfoEntity> reportOptional = reportRepo.findById(report_id);
	        
	        if (reportOptional.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        }
	        
	        ReportInfoEntity report = reportOptional.get();
	        byte[] pdfContent = report.getPdfContent();
	        
	        // Set the headers to indicate that you're sending a PDF file
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_PDF);
	        headers.setContentDispositionFormData("filename", "report.pdf");
	        headers.setContentLength(pdfContent.length);
	        System.out.println("download pdf success");
	        return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);
	        
	    } catch (Exception e) {
	        System.out.println("Failed to download report: " + e.getMessage());
	        System.out.println("download pdf fail");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}
}
