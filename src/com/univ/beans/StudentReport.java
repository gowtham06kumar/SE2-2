package com.univ.beans;

public class StudentReport {

	int NoOfCoursesCompleted;
	public int getNoOfCoursesCompleted() {
		return NoOfCoursesCompleted;
	}
	public void setNoOfCoursesCompleted(int noOfCoursesCompleted) {
		NoOfCoursesCompleted = noOfCoursesCompleted;
	}
	public String getStudentID() {
		return StudentID;
	}
	public void setStudentID(String studentID) {
		StudentID = studentID;
	}
	public int getNoOfCoursesNotCompleted() {
		return NoOfCoursesNotCompleted;
	}
	public void setNoOfCoursesNotCompleted(int noOfCoursesNotCompleted) {
		NoOfCoursesNotCompleted = noOfCoursesNotCompleted;
	}
	String StudentID;
	int NoOfCoursesNotCompleted;
}
