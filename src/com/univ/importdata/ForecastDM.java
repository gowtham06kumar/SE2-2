package com.univ.importdata;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.univ.beans.SemesterForecast;

public class ForecastDM {
	
	public static List<SemesterForecast> loadforecasts(String fileName){
		
		String list[];
		String line = null;
						
		fileName = "TestDataDegrees.csv";
		List<SemesterForecast> forecastList = new ArrayList<SemesterForecast>();
		SemesterForecast forecast =null;
		
		try {
	        // FileReader reads text files in the default encoding.
	        FileReader fileReader = new FileReader(fileName);

	        // Always wrap FileReader in BufferedReader.
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	        
	        
	        //while there is data in file do this
	        while((line = bufferedReader.readLine()) != null) 
	        {
	        	
	        	if(line.contains("DegreeCode"))
	        	{
	        		continue;
	        	}
	        	
	        		        	
	        	list = line.split(",");
	        	forecast=new SemesterForecast();
	        	
	        	forecast.setDegreeCode(list[0]);
	        	forecast.setGradSchool(list[1]);	        		        	
	        	forecast.setDegreeName(list[2]);
	        	forecast.setForecase(list[3]);
	   	        	
	        	forecastList.add(forecast);
	        }
	        bufferedReader.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return forecastList;
	}

}
