package com.presidio.reportcard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.presidio.reportcard.common.APIResponse;
import com.presidio.reportcard.model.MarkRecord;
import com.presidio.reportcard.service.MarkRecordService;

@RestController
@RequestMapping("/marks")
public class MarkRecordController {

	@Autowired private MarkRecordService markRecordService;
	
	@GetMapping("/")
	public ResponseEntity<List<MarkRecord>> listAll() {
		return new ResponseEntity<List<MarkRecord>>(markRecordService.listAll(), HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<APIResponse> create(@RequestBody MarkRecord mr) {
		
		markRecordService.save(mr);
		return new ResponseEntity<APIResponse>(new APIResponse(true, "mark record saved successfully"), HttpStatus.CREATED);
	}
	
	
	
}
