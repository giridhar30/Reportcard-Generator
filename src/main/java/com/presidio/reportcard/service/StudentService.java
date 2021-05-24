package com.presidio.reportcard.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.presidio.reportcard.model.Student;
import com.presidio.reportcard.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired private StudentRepository studentRepository;

	@Transactional
	public List<Student> listAll() {
		return studentRepository.findAll();
	}

	@Transactional
	public void save(Student student) {
		studentRepository.save(student);
	}

	@Transactional
	public boolean studentExists(Student student) {
		
		return studentRepository.findStudentByRegisterNo(student.getRegisterNo())!=null;
		
	}

	@Transactional
	public Student findsStudentByRegisterNo(String registerNo) {
		return studentRepository.findStudentByRegisterNo(registerNo);
	}
	
}
