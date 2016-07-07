package com.univ.service;

public class DegreePlanService {
	
	public String[] getDegrees()
	{
		String[] Degrees = new String[] { "MSCS.SFTW.ENG", "MSE.SFTW.ENG", "MSE.CE", "MSE.EE", "MSE.ME" };
		return Degrees;
	}
	
	public String[] getSemesters()
	{
		String[] semesters = new String[] { "Fall", "Spring", "Summer"};
		return semesters;
	}

}
