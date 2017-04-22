package com.mkyong.stock;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class StudentModel implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="roll_no")
	private int rollNo;
	@Column(unique=true,nullable=false)
	private String subject;
	@Column(nullable=false)
	private String name;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="student",cascade=CascadeType.ALL)
	private List<SubjectModel> subjectList;
	
	@Override
	public String toString() {
		return "StudentModel [rollNo=" + rollNo + ", subject=" + subject + ", name=" + name + ", subjectList="
				+ subjectList + "]";
	}
	
	public int getRollNo() {
		return rollNo;
	}
	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<SubjectModel> getSubjectList() {
		return subjectList;
	}
	public void setSubjectList(List<SubjectModel> subjectList) {
		this.subjectList = subjectList;
	}
}
