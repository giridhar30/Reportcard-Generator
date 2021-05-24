package com.presidio.reportcard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.presidio.reportcard.model.Student;

public interface StudentRepository extends JpaRepository<Student, String> {
	
	Student findStudentByRegisterNo(String registerNo);

}
