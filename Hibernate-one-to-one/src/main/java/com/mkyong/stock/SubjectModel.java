package com.mkyong.stock;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="subjects")
public class SubjectModel implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	@Column(name="subj_name",nullable=false)
	private String subject;
	@Column(name="marks",nullable=false)
	private int marks;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="roll_no",referencedColumnName="roll_no")
	private StudentModel student;
	
	public SubjectModel(){}
	public SubjectModel(int id, String subject, int marks, StudentModel student){
		this.id = id;
		this.subject = subject;
		this.marks = marks;
		this.student = student;
	}
	
	@Override
	public String toString() {
		return "SubjectModel [id=" + id + ", subject=" + subject + ", marks=" + marks +"]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	public StudentModel getStudent() {
		return student;
	}
	public void setStudent(StudentModel student) {
		this.student = student;
	}
	
	
}
