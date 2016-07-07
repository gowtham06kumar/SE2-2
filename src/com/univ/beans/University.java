package com.univ.beans;

public class University {
	private String name;
	private String abbrevation;

	public String getName() {
		return name;
	}

	public University() {
	}

	public University(String name, String abbrevation) {
		super();
		this.name = name;
		this.abbrevation = abbrevation;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbrevation() {
		return abbrevation;
	}

	public void setAbbrevation(String abbrevation) {
		this.abbrevation = abbrevation;
	}

}
