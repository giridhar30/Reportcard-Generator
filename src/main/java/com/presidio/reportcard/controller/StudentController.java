package com.presidio.reportcard.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.presidio.reportcard.common.APIResponse;
import com.presidio.reportcard.model.Student;
import com.presidio.reportcard.service.ReportcardService;
import com.presidio.reportcard.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired 
	private StudentService studentService;
	
	@Autowired
	private ReportcardService rgs;
	
	@GetMapping("/")
	public ResponseEntity<List<Student>> listAll() {
		return new ResponseEntity<List<Student>>(studentService.listAll(), HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<APIResponse> create(@RequestBody Student student) {
		if (studentService.studentExists(student)) 
			return new ResponseEntity<APIResponse>(new APIResponse(false, "student already exists"), HttpStatus.CONFLICT);
		
		studentService.save(student);
		return new ResponseEntity<APIResponse>(new APIResponse(true, "student saved successfully"), HttpStatus.CREATED);
	}
	
	@GetMapping("/{regno}")
	public ResponseEntity<Object> getStudent(@PathVariable("regno") String registerNo) {
		Student s = studentService.findsStudentByRegisterNo(registerNo);
		if (s != null) {
			return new ResponseEntity<Object>(s, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(new APIResponse(false, "student does not exist"), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/{regno}/reportcard")
	public void generateReportcard(HttpServletResponse response, @PathVariable("regno") String registerNo) throws Exception {
		
//		response.setContentType("application/pdf");
//		DateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
//		String now = df.format(new Date());
		
		String hk = "Content-Disposition";
		String hv = "attachment: filename='reportcard" + registerNo + ".pdf'";
		response.setHeader(hk, hv);
		
		rgs.generate(response, registerNo);
		
	}
	
	@GetMapping("/{regno}/reportcard/mail")
	public ResponseEntity<APIResponse> sendReportcard(@PathVariable("regno") String registerNo) {
		int mailStatus = rgs.mail(registerNo);
		if (mailStatus == 0) {
			return new ResponseEntity<APIResponse>(new APIResponse(true, "mail sent"), HttpStatus.OK);
		} else if (mailStatus == 2) {
			return new ResponseEntity<APIResponse>(new APIResponse(false, "mail not sent"), HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<APIResponse>(new APIResponse(false, "student does not exist"), HttpStatus.NOT_FOUND);
		}
	}

}
