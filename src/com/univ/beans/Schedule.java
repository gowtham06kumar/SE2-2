package com.univ.beans;

public class Schedule {
	
	private String semester;
	private String course;
	private String secnum;
	private String faculty;
	private int numberOfStudentsToComplteCourse;
	private int remainingStudents;
	private boolean isCourseAvailable;
	private int credits;
	public int getCredits() {
		return credits;
	}
	public void setCredits(int credits) {
		this.credits = credits;
	}
	public String getAcademicLevel() {
		return academicLevel;
	}
	public void setAcademicLevel(String academicLevel) {
		this.academicLevel = academicLevel;
	}
	private String academicLevel;
	
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getSecnum() {
		return secnum;
	}
	public void setSecnum(String secnum) {
		this.secnum = secnum;
	}
	public String getFaculty() {
		return faculty;
	}
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
	public int getNumberOfStudentsToComplteCourse() {
		return numberOfStudentsToComplteCourse;
	}
	public void setNumberOfStudentsToComplteCourse(int numberOfStudentsToComplteCourse) {
		this.numberOfStudentsToComplteCourse = numberOfStudentsToComplteCourse;
	}
	public int getRemainingStudents() {
		return remainingStudents;
	}
	public void setRemainingStudents(int remainingStudents) {
		this.remainingStudents = remainingStudents;
	}
	public boolean isCourseAvailable() {
		return isCourseAvailable;
	}
	public void setCourseAvailable(boolean isCourseAvailable) {
		this.isCourseAvailable = isCourseAvailable;
	}
	

}
