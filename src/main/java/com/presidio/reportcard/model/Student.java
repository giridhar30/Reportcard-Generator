package com.presidio.reportcard.model;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student {

	private @Id String registerNo;
	private String name;
	private String dob;
	private String fatherName;
	private String motherName;
	private String phone;
	private String mailId;
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "registerNo")
	private List<MarkRecord> marks;
	
	Student() {}
	
	public String getRegisterNo() {
		return registerNo;
	}

	public String getName() {
		return name;
	}

	public String getDob() {
		return dob;
	}

	public String getFatherName() {
		return fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public String getPhone() {
		return phone;
	}

	public String getMailId() {
		return mailId;
	}

	public List<MarkRecord> getMarks() {
		return marks;
	}

	public void setMarks(List<MarkRecord> marks) {
		this.marks = marks;
	}
	
}
