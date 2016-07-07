package com.univ.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.univ.beans.Degree;
import com.univ.beans.Student;
import com.univ.importdata.StudentDM;

public class DegreePlanReportService {
	public Map<String, Integer> generateDegreePlanReport(String semester, String year, List<Student> studentData,
			List<Degree> degreeData) {
		Map<String, Integer> hm = new HashMap<>();
		String[] ar = null;
		for (Student std : studentData){
			ar = splitByNumber(std.getSemester(), 4);
			System.out.println("Lengthe"+ar.length);
			for(int i=0;i<ar.length;i++)
			System.out.println(ar[0]+" "+ar[1]);
			for (Degree deg : degreeData) {
				if (deg.getDegreeCode().equals(std.getDegreeCode())) {
					if (semester.equalsIgnoreCase("Fall") ) {
						//ar[1]= "Fall";
						if (Integer.parseInt(ar[0]) > Integer.parseInt(year)) {
							if(hm.containsKey(deg.getDegreeCode()))
							{
								int currentValue=hm.get(deg.getDegreeCode());
								hm.put(deg.getDegreeCode(),currentValue+1);
							}
							else
							hm.put(deg.getDegreeCode(), 1);
						}
						else if(Integer.parseInt(ar[0]) == Integer.parseInt(year)){
							if(ar[1].equals("SU")||ar[1].equals("SP")){
								if(hm.containsKey(deg.getDegreeCode()))
								{
									int currentValue=hm.get(deg.getDegreeCode());
									hm.put(deg.getDegreeCode(),currentValue+1);
								}
								else
								hm.put(deg.getDegreeCode(), 1);
								
							}
							
							
						}
					}
					else if (semester.equalsIgnoreCase("Spring") ) {
						//ar[1]= "Fall";
						if (Integer.parseInt(ar[0]) > Integer.parseInt(year)) {
							if(hm.containsKey(deg.getDegreeCode()))
							{
								int currentValue=hm.get(deg.getDegreeCode());
								hm.put(deg.getDegreeCode(),currentValue+1);
							}
							else
							hm.put(deg.getDegreeCode(), 1);
						}
						
					}
					else if (semester.equalsIgnoreCase("Summer") ) {
						if (Integer.parseInt(ar[0]) > Integer.parseInt(year)) {
							if(hm.containsKey(deg.getDegreeCode()))
							{
								int currentValue=hm.get(deg.getDegreeCode());
								hm.put(deg.getDegreeCode(),currentValue+1);
							}
							else
							hm.put(deg.getDegreeCode(), 1);
						}
						else if(Integer.parseInt(ar[0]) == Integer.parseInt(year)){
							if(ar[1].equals("FA")||ar[1].equals("SP")){
								if(hm.containsKey(deg.getDegreeCode()))
								{
									int currentValue=hm.get(deg.getDegreeCode());
									hm.put(deg.getDegreeCode(),currentValue+1);
								}
								else
								hm.put(deg.getDegreeCode(), 1);
								
							}
							
							
						}
					}
				}
			}
		}
		System.out.println(hm);
		return hm;

	}

	private static String[] splitByNumber(String text, int number) {
		int inLength = text.length();
		int arLength = inLength / number;
		int left = inLength % number;
		if (left > 0) {
			++arLength;
		}
		String ar[] = new String[arLength];
		String tempText = text;
		for (int x = 0; x < arLength; ++x) {
			if (tempText.length() > number) {
				ar[x] = tempText.substring(0, number);
				tempText = tempText.substring(number);
			} else {
				ar[x] = tempText;
			}
		}
		return ar;
	}

}
