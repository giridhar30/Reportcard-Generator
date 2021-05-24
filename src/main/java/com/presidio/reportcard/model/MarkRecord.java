package com.presidio.reportcard.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "mark_records")
public class MarkRecord {

	private @Id @GeneratedValue long id;
	private String registerNo;
	private int term;
	private float maths;
	private float physics;
	private float chemistry;
	private float biology;
	private float history;
	private double total;
	private double avg;
//	@ManyToOne
//	@JoinColumn(name = "register_no")
//	private Student student;
	
	MarkRecord() {}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public int getTerm() {
		return term;
	}
	
	public void setTerm(int term) {
		this.term = term;
	}

	public float getMaths() {
		return maths;
	}

	public float getPhysics() {
		return physics;
	}

	public float getChemistry() {
		return chemistry;
	}

	public float getBiology() {
		return biology;
	}

	public float getHistory() {
		return history;
	}

	public double getTotal() {
		return total;
	}

	public double getAvg() {
		return avg;
	}

	public void setMaths(float maths) {
		this.maths = maths;
	}

	public void setPhysics(float physics) {
		this.physics = physics;
	}

	public void setChemistry(float chemistry) {
		this.chemistry = chemistry;
	}

	public void setBiology(float biology) {
		this.biology = biology;
	}

	public void setHistory(float history) {
		this.history = history;
	}

//	public Student getStudent() {
//		return student;
//	}
//
//	public void setStudent(Student student) {
//		this.student = student;
//	}

	public void setTotal(double total) {
		this.total = total;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public String getRegisterNo() {
		return registerNo;
	}

	public void setRegisterNo(String registerNo) {
		this.registerNo = registerNo;
	}

}
