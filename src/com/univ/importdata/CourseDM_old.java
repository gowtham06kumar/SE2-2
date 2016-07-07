package com.univ.importdata;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.univ.beans.Course;

public class CourseDM_old {
	
	public static List<Course> loadCourses(String fileName)
	{
		String line = null;
		String[] coursedetails;
				
		List<Course> courseList = new ArrayList<Course>();
		Course course =null;
		
		try {
	        // FileReader reads text files in the default encoding.
	        FileReader fileReader = new FileReader(fileName);

	        // Always wrap FileReader in BufferedReader.
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	        
	        
	        //while there is data in file do this
	        while((line = bufferedReader.readLine()) != null) 
	        {
	        	
	        	if(line.contains("CourseCode"))
	        	{
	        		continue;
	        	}
	        	
	        	String line1 = remCommaFmData(line);
	        	
	        	coursedetails = line1.split(",");
	        	course=new Course();
	        	
	        	course.setCode(coursedetails[0]);
	        	course.setName(coursedetails[1]);	        		        	
	        	course.setDescription(coursedetails[2].replaceAll("@", ","));
	        	course.setCreditHours(Integer.parseInt(coursedetails[3]));
	        	course.setClassCapacity(Integer.parseInt(coursedetails[4]));
	        	//course.set(Integer.parseInt(coursedetails[4]));
	        	
	        	courseList.add(course);
	        }
	        bufferedReader.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return courseList;
	}
	
	/**
	 * 
	 * Replace commas in description with !@ 
	 * 
	 */
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

	public static List<Course> getListfromTable(DefaultTableModel dtm){
		List<Course> courseList = new ArrayList<Course>();
		
		for(int i=0;i<dtm.getRowCount();i++)
		{
				Course course = new Course();
				
				course.setCode(dtm.getValueAt(i, 0).toString());
				course.setName(dtm.getValueAt(i, 1).toString());
				course.setDescription(dtm.getValueAt(i, 2).toString());
				course.setClassCapacity(Integer.parseInt(dtm.getValueAt(i, 3).toString()));
				course.setCreditHours(Integer.parseInt(dtm.getValueAt(i, 4).toString()));
				
				courseList.add(course);
		}
		
		return courseList;
		
	}
}
