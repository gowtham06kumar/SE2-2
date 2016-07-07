package com.univ.beans;

import java.util.ArrayList;

public class DegreePlanReq {

	private String degreeCode;
	private String desc;
	private String hours;
	private String type;
	private ArrayList<String> courses;
	public String getDegreeCode() {
		return degreeCode;
	}
	public void setDegreeCode(String degreeCode) {
		this.degreeCode = degreeCode;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getHours() {
		return hours;
	}
	public void setHours(String hours) {
		this.hours = hours;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ArrayList<String> getCourses() {
		return courses;
	}
	public void setCourses(ArrayList<String> courses) {
		this.courses = courses;
	}
	
	
}
