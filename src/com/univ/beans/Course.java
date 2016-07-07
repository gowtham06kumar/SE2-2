package com.univ.beans;

public class Course {

	private String code;
	private String name;
	private String description;
	private int creditHours;
	private int classCapacity;
	private String offeredSpring;
	private String offeredFall;
	private String offeredSummer;
	private String coursePreReq;
	private String teachers;
	
	public String getOfferedSpring() {
		return offeredSpring;
	}

	public void setOfferedSpring(String offeredSpring) {
		this.offeredSpring = offeredSpring;
	}

	public String getOfferedFall() {
		return offeredFall;
	}

	public void setOfferedFall(String offeredFall) {
		this.offeredFall = offeredFall;
	}

	public String getOfferedSummer() {
		return offeredSummer;
	}

	public void setOfferedSummer(String offeredSummer) {
		this.offeredSummer = offeredSummer;
	}

	public String getCoursePreReq() {
		return coursePreReq;
	}

	public void setCoursePreReq(String coursePreReq) {
		this.coursePreReq = coursePreReq;
	}

	public String getTeachers() {
		return teachers;
	}

	public void setTeachers(String teachers) {
		this.teachers = teachers;
	}


	public Course() {
		super();
	}

	public Course(String code, String name, String description,
			int creditHours, int classCap) {
		super();
		this.code = code;
		this.name = name;
		this.description = description;
		this.creditHours = creditHours;
		this.classCapacity = classCap;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCreditHours() {
		return creditHours;
	}

	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}

	public int getClassCapacity() {
		return classCapacity;
	}

	public void setClassCapacity(int classCapacity) {
		this.classCapacity = classCapacity;
	}

	public String toString() {
		return this.code + " , " + this.name + " , " + this.description;

	}
}
