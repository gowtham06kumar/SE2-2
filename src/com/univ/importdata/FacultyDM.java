package com.univ.importdata;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.univ.beans.Faculty;

public class FacultyDM {
	
	public static  List<Faculty> importFacultyDataFromCsv(String fileName){
		String line,headerLine = null;
		String[] facultyDetails;
		//Faculty faculty=new Faculty();
        List<Faculty> listOfFaculty=new ArrayList<Faculty>();
		  try {
		        // FileReader reads text files in the default encoding.
		        FileReader fileReader = new FileReader(fileName);
		        // Always wrap FileReader in BufferedReader.
		        BufferedReader bufferedReader =  new BufferedReader(fileReader); 
		        headerLine = bufferedReader.readLine();
		        while((line = bufferedReader.readLine()) != null)
		        {	
		        	Faculty faculty=new Faculty();
		        	facultyDetails = line.split(",");
		        	faculty.setLastName(facultyDetails[0]);
		        	faculty.setFirstName(facultyDetails[1]);
		        	faculty.setGradSchool(facultyDetails[2]);
		        	faculty.setDegree(facultyDetails[3]);
		        	faculty.setTitle(facultyDetails[4]);
		        	faculty.setDaysToTeach(facultyDetails[5]);
		        	faculty.setMaxLoadFall(facultyDetails[6]);
		        	faculty.setMaxLoadSpring(facultyDetails[7]);
		        	faculty.setMaxLoadSummer(facultyDetails[8]);  
		        	listOfFaculty.add(faculty);
		        }
		        bufferedReader.close();
		  	}
		    catch(FileNotFoundException ex) 
		    {
		        System.out.println(
		            "Unable to open file '" + 
		            fileName + "'");                
		    }
		    catch(IOException ex) {
		        System.out.println(
		            "Error reading file '" 
		            + fileName + "'");   	
	}
		 
		  return listOfFaculty;
	}

	public static List<Faculty> getListfromTable(DefaultTableModel dtm){
		List<Faculty> facultyList = new ArrayList<Faculty>();
		
		for(int i=0;i<dtm.getRowCount();i++)
		{
				Faculty faculty = new Faculty();
				
				faculty.setLastName(dtm.getValueAt(i, 0).toString());
				faculty.setFirstName(dtm.getValueAt(i, 1).toString());
				faculty.setGradSchool(dtm.getValueAt(i, 2).toString());
				faculty.setDegree(dtm.getValueAt(i, 3).toString());
				faculty.setTitle(dtm.getValueAt(i, 4).toString());
				faculty.setDaysToTeach(dtm.getValueAt(i, 5).toString());
				faculty.setMaxLoadFall(dtm.getValueAt(i, 6).toString());
				faculty.setMaxLoadSpring(dtm.getValueAt(i, 7).toString());
				faculty.setMaxLoadSummer(dtm.getValueAt(i, 8).toString());
				
				facultyList.add(faculty);
		}
		
		return facultyList;
		
	}
}
