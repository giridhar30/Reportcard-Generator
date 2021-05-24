package com.presidio.reportcard.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping("/")
	public ResponseEntity<String> hello() {
		return new ResponseEntity<String>("Welcome to Result Generator", HttpStatus.OK);
	}
	
}
