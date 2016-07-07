package com.univ.beans;

public class Degree {
	private String degreeCode;
	private String gradSchool;
	private String degreeName;
	private String forecast;

	public Degree() {
		this.degreeCode = null;
		this.gradSchool = null;
		this.degreeName = null;
		this.forecast = null;
	}

	public Degree(String degreeCode, String gradSchool, String degreeName,
			String forecast) {
		this.degreeCode = degreeCode;
		this.gradSchool = gradSchool;
		this.degreeName = degreeName;
		this.forecast = forecast;
	}

	public String getDegreeCode() {
		return degreeCode;
	}

	public void setDegreeCode(String degreeCode) {
		this.degreeCode = degreeCode;
	}

	public String getGradSchool() {
		return gradSchool;
	}

	public void setGradSchool(String gradSchool) {
		this.gradSchool = gradSchool;
	}

	public String getDegreeName() {
		return degreeName;
	}

	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}

	public String getForecast() {
		return forecast;
	}

	public void setForecast(String forecast) {
		this.forecast = forecast;
	}

}
