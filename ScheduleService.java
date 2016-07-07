package com.univ.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.univ.beans.Course;
import com.univ.beans.Degree;
import com.univ.beans.DegreePlanReq;
import com.univ.beans.Faculty;
import com.univ.beans.Schedule;
import com.univ.importdata.CourseDM;

public class ScheduleService {
	
	//,courseListRequired;
	
	public List<Schedule> prepareSchedule(String semester,List<Course> courseDate,List<Faculty> facultyList)
	{
		//courseList = CourseDM.loadCourses("TestDataCourses.csv");
		
		List<Course> courseList = getCoursesOfferedInSemester(semester,courseDate);
		
		//courseListRequired = getCoursesRequired(courseList,getDegreePLanRequ());
		
		
		List<Schedule> scheduleList = assignFacultyToCourses(semester,courseList,facultyList);
		
		scheduleList = addSections(scheduleList);
		
		return scheduleList;
		
	}

	private List<Schedule> addSections(List<Schedule> scheduleList) {
		
		int i=1;
		
		for(Schedule sch:scheduleList)
		{
			sch.setSecnum("Sec"+i++);
		}
		
		return scheduleList;
	}

	private List<Schedule> assignFacultyToCourses(String semester,List<Course> courseList,List<Faculty> facList) {
		
		List<Schedule> scheduleList = new ArrayList<Schedule>();
		
		for(Course course : courseList)
		{
			Schedule sch=new Schedule();
			
			if(semester.equalsIgnoreCase("fall"))
			{
				for(Faculty fac :facList)
				{
					if(course.getTeachers().equalsIgnoreCase(fac.getLastName()) && Integer.parseInt(fac.getMaxLoadFall())>0)
					{	
						sch.setCourse(course.getCode());
						sch.setFaculty(fac.getLastName());
						sch.setSemester("fall");
					}
				}
				
			}
			else if(semester.equalsIgnoreCase("summer"))
			{
				for(Faculty fac :facList)
				{
					if(course.getTeachers().equalsIgnoreCase(fac.getLastName()) && Integer.parseInt(fac.getMaxLoadSummer())>0)
					{	
						sch.setCourse(course.getCode());
						sch.setFaculty(fac.getLastName());
						sch.setSemester("summer");
					}
				}
			}
			else if(semester.equalsIgnoreCase("spring"))
			{
				for(Faculty fac :facList)
				{
					if(course.getTeachers().equalsIgnoreCase(fac.getLastName()) && Integer.parseInt(fac.getMaxLoadSpring())>0)
					{	
						sch.setCourse(course.getCode());
						sch.setFaculty(fac.getLastName());
						sch.setSemester("spring");
					}
				}
			}
			
			scheduleList.add(sch);
		}
		
		return scheduleList;
	}

	private List<DegreePlanReq> getDegreePLanRequ() {
		String line = null;
		String[] coursedetails;
				
		List<DegreePlanReq> degreeList = new ArrayList<DegreePlanReq>();
		DegreePlanReq degree =null;
		
		try {
	        // FileReader reads text files in the default encoding.
	        FileReader fileReader = new FileReader("TestDataDegreePlanReq.csv");

	        // Always wrap FileReader in BufferedReader.
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	        
	        
	        //while there is data in file do this
	        while((line = bufferedReader.readLine()) != null) 
	        {
	        	
	        	if(line.contains("DegreeCode"))
	        	{
	        		continue;
	        	}
	        	
	        	String line1 = remCommaFmData(line);
	        	
	        	coursedetails = line1.split(",");
	        	degree=new DegreePlanReq();
	        	
	        	degree.setDegreeCode(coursedetails[0]);	        		        	
	        	degree.setDesc(coursedetails[1]);
	        	degree.setHours(coursedetails[2]);
	        	degree.setType(coursedetails[3]);
	        	degree.setCourses(coursedetails[4].replaceAll("@", ","));
	        	
	        	
	        	degreeList.add(degree);
	        }
	        bufferedReader.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return degreeList;
	}

	private List<Course> getCoursesRequired(List<Course> courseList2, List<DegreePlanReq> degreeList) {
		
		for(Course course:courseList2)
		{
			String[] courses;
			for(DegreePlanReq degree : degreeList)
			{
				if(degree.getType().equalsIgnoreCase("Required"))
				{
					courses = degree.getCourses().split(",");
					for(int i=0;i<=courses.length;i++)
					{
						
					}
				}
					
			}
			
		}
		
		return null;
	}

	private List<Course> getCoursesOfferedInSemester(String semester,List<Course> courseList2) {
		
		List<Course> fallList = new ArrayList<Course>();
		List<Course> springList = new ArrayList<Course>(); 
		List<Course> summerList = new ArrayList<Course>(); 
		
		for(Course course:courseList2)
		{
			if(course.getOfferedFall().equalsIgnoreCase("Y"))
				fallList.add(course);
			if(course.getOfferedSpring().equalsIgnoreCase("Y"))
				springList.add(course);
			if(course.getOfferedSummer().equalsIgnoreCase("Y"))
				summerList.add(course);		
		}
		
		if(semester.equalsIgnoreCase("fall"))
			return fallList;
		else if(semester.equalsIgnoreCase("spring"))
			return springList;
		else if(semester.equalsIgnoreCase("summer"))
			return summerList;
		else
			return null;
	}

	private static String remCommaFmData(String str) {
		 
		boolean inQuotes = false;
		String copy = new String();
		
		for(int i=0; i<str.length(); ++i)
        {
    	if (str.charAt(i)=='"')
    	    inQuotes = !inQuotes;
    	if (str.charAt(i)==',' && inQuotes)
    	    copy += '@';
    	else
    	    copy += str.charAt(i);
        }
		
		return copy;
	}
}
