package com.univ.beans;

public class StudentCourse {
	private Student student;
	private String course;
	private String courseID;
	private String grade;

	public StudentCourse() {
	}

	public StudentCourse(Student student, String course, String grade) {
		super();
		this.student = student;
		this.course = course;
		this.grade = grade;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	

}
