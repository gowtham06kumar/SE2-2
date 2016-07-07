package com.univ.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.univ.beans.DegreePlanReq;
import com.univ.beans.Schedule;
import com.univ.beans.Student;
import com.univ.beans.StudentCourse;
import com.univ.importdata.StudentCoursesDM;
import com.univ.importdata.StudentDM;

public class TestScheduleService {

	Map<String, List<Schedule>> completeScheduleHashMap;
	String semester;

	public TestScheduleService(String semester,
			Map<String, List<Schedule>> completeScheduleHashMap) {
		this.semester = semester;
		this.completeScheduleHashMap = completeScheduleHashMap;
	}

	public int getstudentsCount() {

		List<StudentCourse> studentCourseData = StudentCoursesDM.importStudentCourseData("STC.DUMP.csv");
		List<Student> studentList = StudentDM.importStudentData("STU.DUMP.csv");
		List<DegreePlanReq> degreeList = ScheduleService.getDegreePLanRequ();
		
		HashMap<String, ArrayList<String>> studentCourseListFinal = ScheduleService.studentCoursesYetToComplete(studentCourseData, studentList,degreeList);
		
		int studentsCount = 0;
		for(Entry<String, ArrayList<String>> entry:studentCourseListFinal.entrySet())
		{
			int countOfCoursesToComplete = entry.getValue().size();
			int temp = 0;
			for(String course:entry.getValue())
			{
				if(!isCoursePresent(course,completeScheduleHashMap.get(semester)))
					break;
				else
					temp++;
			}
			
			if(temp == countOfCoursesToComplete || temp >= 3)
				studentsCount++;
		}

		return studentsCount;
	}

	private boolean isCoursePresent(String course, List<Schedule> list) {
		
		boolean isCoursePresent = false;
		
		for(Schedule sch:list)
		{
			if(course.equalsIgnoreCase(sch.getCourse()))
				isCoursePresent = true;
		}
		
		return isCoursePresent;		
	}

	public int getcoursesWithinFill() {

		int count = 0;

		for (Schedule sch : completeScheduleHashMap.get(semester)) {
			if (!sch.isCourseAvailable())
				count++;
		}

		return count;
	}

	public int getcoursesExceed() {

		int count = 0;

		for (Schedule sch : completeScheduleHashMap.get(semester)) {
			if (sch.getNumberOfStudentsToComplteCourse() >= 25)
				count++;
		}
		return count;
	}

}
