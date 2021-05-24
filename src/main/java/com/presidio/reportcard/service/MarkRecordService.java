package com.presidio.reportcard.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.presidio.reportcard.model.MarkRecord;
import com.presidio.reportcard.repository.MarkRecordRepository;

@Service
public class MarkRecordService {

	@Autowired private MarkRecordRepository markRecordRepository;
	
	@Transactional
	public List<MarkRecord> listAll() {
		return markRecordRepository.findAll();
	}
	
	@Transactional
	public void save(MarkRecord mr) {
		mr.setTotal(
				mr.getMaths()+
				mr.getPhysics()+
				mr.getChemistry()+
				mr.getBiology()+
				mr.getHistory()
			);
		mr.setAvg(mr.getTotal()/5);
		markRecordRepository.save(mr);
	}
	
	@Transactional
	public boolean markRecordExists(MarkRecord mr) {
		
		return markRecordRepository.findMarkRecordById(mr.getId())!=null;
		
	}
	
	@Transactional
	public List<MarkRecord> getMarkList(String registerNo) {
		return markRecordRepository.findMarkrecordsByRegisterNo(registerNo);
	}
	
}
