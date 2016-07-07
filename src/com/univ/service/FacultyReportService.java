package com.univ.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.univ.beans.Schedule;

public class FacultyReportService {
	public Map<String,Integer> prepareReport(String semester,Map<String,List<Schedule>> completeScheduleHashMap){
		System.out.println("Selected Semester"+semester);
		Map<String,Integer> hm=new HashMap();
		for (Entry<String, List<Schedule>> entry : completeScheduleHashMap.entrySet()) {
			if(semester.equalsIgnoreCase("Fall") && semester.equals(entry.getKey()))
			{
				List<Schedule> completeList=entry.getValue();
				for(Schedule sch : completeList){
						if(hm.containsKey(sch.getFaculty()))
						{
							int currentValue=hm.get(sch.getFaculty());
							hm.put(sch.getFaculty(),currentValue+3);
						}
						else
							hm.put(sch.getFaculty(),3);
				}
			}
			if(semester.equalsIgnoreCase("Spring") && semester.equals(entry.getKey()))
			{
				List<Schedule> completeList=entry.getValue();
				for(Schedule sch : completeList){
						if(hm.containsKey(sch.getFaculty()))
						{
							int currentValue=hm.get(sch.getFaculty());
							hm.put(sch.getFaculty(),currentValue+3);
						}
						else
							hm.put(sch.getFaculty(),3);
				}
			}
			if(semester.equalsIgnoreCase("Summer") && semester.equals(entry.getKey()))
			{
				List<Schedule> completeList=entry.getValue();
				for(Schedule sch : completeList){
						if(hm.containsKey(sch.getFaculty()))
						{
							int currentValue=hm.get(sch.getFaculty());
							hm.put(sch.getFaculty(),currentValue+3);
						}
						else
							hm.put(sch.getFaculty(),3);
				}
			}
		}
		return hm;
	}

}
