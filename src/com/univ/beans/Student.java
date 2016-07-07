package com.univ.beans;

public class Student {
	private String id;
	private String degreeCode;
	private String semester;


	public Student(String id, String degreeCode, String semester) {
		super();
		this.id = id;
		this.degreeCode = degreeCode;
		this.semester = semester;
	}

	public Student() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDegreeCode() {
		return degreeCode;
	}

	public void setDegreeCode(String degreeCode) {
		this.degreeCode = degreeCode;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

}
