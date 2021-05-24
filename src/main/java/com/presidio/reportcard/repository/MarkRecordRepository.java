package com.presidio.reportcard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.presidio.reportcard.model.MarkRecord;

public interface MarkRecordRepository extends JpaRepository<MarkRecord, Long> {

	MarkRecord findMarkRecordById(Long id);
	
	List<MarkRecord> findMarkrecordsByRegisterNo(String registerNo);
	
}
