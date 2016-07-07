package com.univ.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.univ.beans.DegreePlanReq;
import com.univ.beans.Student;
import com.univ.beans.StudentCourse;
import com.univ.service.ScheduleService;

public class StudentReportService {

	int NoOfCoursesCompleted;
	String StudentID;
	int NoOfCoursesNotCompleted;
	int NoOfElectiveCoursesCompleted;

	public int getNoOfElectiveCoursesCompleted() {
		return NoOfElectiveCoursesCompleted;
	}

	public void setNoOfElectiveCoursesCompleted(int noOfElectiveCoursesCompleted) {
		NoOfElectiveCoursesCompleted = noOfElectiveCoursesCompleted;
	}

	public String getYearPassingOut() {
		return yearPassingOut;
	}

	public void setYearPassingOut(String yearPassingOut) {
		this.yearPassingOut = yearPassingOut;
	}

	String yearPassingOut;

	public int getNoOfCoursesCompleted() {
		return NoOfCoursesCompleted;
	}

	public void setNoOfCoursesCompleted(int noOfCoursesCompleted) {
		NoOfCoursesCompleted = noOfCoursesCompleted;
	}

	public int getNoOfCoursesNotCompleted() {
		return NoOfCoursesNotCompleted;
	}

	public void setNoOfCoursesNotCompleted(int noOfCoursesNotCompleted) {
		NoOfCoursesNotCompleted = noOfCoursesNotCompleted;
	}

	public String getStudentID() {
		return StudentID;
	}

	public void setStudentID(String studentID) {
		StudentID = studentID;
	}

	public List<StudentReportService> generateStudentReport(List<Student> studentData,
			List<StudentCourse> studentCourseData, List<DegreePlanReq> degreeList,
			List<DegreePlanReq> electiveDegreeList) {

		HashMap<String, ArrayList<String>> requiredCoursesNeedToBeCompleted = new HashMap<String, ArrayList<String>>();
		HashMap<String, ArrayList<String>> electiveCoursesNeedToBeCompleted = new HashMap<String, ArrayList<String>>();
		HashMap<String, ArrayList<String>> allCoursesCompleted = new HashMap<String, ArrayList<String>>();
		// HashMap<String, ArrayList<String>> electiveCoursesCompleted = new
		// HashMap<String, ArrayList<String>>();
		HashMap<String, ArrayList<String>> requiredCoursesNotCompleted = new HashMap<String, ArrayList<String>>();
		HashMap<String, ArrayList<String>> electiveCoursesNotCompleted = new HashMap<String, ArrayList<String>>();

		for (Student student : studentData) {
			ArrayList<String> allCourses = ScheduleService.getAllCourses(student.getDegreeCode(), degreeList);
			if (allCourses.size() > 0)
				requiredCoursesNeedToBeCompleted.put(student.getId(), allCourses);
		}
		for (Student student : studentData) {
			ArrayList requiredCoursesCompletedList = new ArrayList();
			for (StudentCourse studentCourses : studentCourseData) {
				if (student.getId().equals(studentCourses.getStudent().getId())) {
					requiredCoursesCompletedList.add(studentCourses.getCourseID());
					allCoursesCompleted.put(student.getId(), requiredCoursesCompletedList);
				}
			}
		}
		requiredCoursesNotCompleted = ScheduleService.getCoursesNotCompleted(requiredCoursesNeedToBeCompleted,allCoursesCompleted);

		List<StudentReportService> srsList = new ArrayList();
		for (Entry<String, ArrayList<String>> entry1 : allCoursesCompleted.entrySet()) {
			for (Entry<String, ArrayList<String>> entry2 : requiredCoursesNotCompleted.entrySet()) {
				StudentReportService srs = new StudentReportService();
				if (entry1.getKey().equals(entry2.getKey())) {
					srs.setStudentID(entry1.getKey());
					srs.setNoOfCoursesCompleted(entry1.getValue().size());
					srs.setNoOfCoursesNotCompleted((entry2.getValue().size()));
					srsList.add(srs);

				}
			}
		}
		for (Student student : studentData) {
			ArrayList<String> allCourses = ScheduleService.getAllCourses(student.getDegreeCode(), electiveDegreeList);
			if (allCourses.size() > 0)
				electiveCoursesNeedToBeCompleted.put(student.getId(), allCourses);
		}
		
		for(Entry<String,ArrayList<String>> entry : electiveCoursesNeedToBeCompleted.entrySet() )
		{
			System.out.println("Stduent "+entry.getKey()+" "+entry.getValue());
		}

		Map<String, ArrayList<String>> stuEleCourse = new HashMap<String, ArrayList<String>>();

		for (Entry<String, ArrayList<String>> entry1 : allCoursesCompleted.entrySet()) {
			for (Entry<String, ArrayList<String>> entry2 : electiveCoursesNeedToBeCompleted.entrySet()) {
				if (entry1.getKey().equals(entry2.getKey())) {
					List<String> electiveCoursesCompleted = new ArrayList<String>();
					// electiveCoursesCompleted.addAll(entry2.getValue());//elective
					List<String> totalCourses = entry1.getValue();// total
					List<String> electiveCourseNeedToCompleted = entry2.getValue();// elective
					for (String course : totalCourses) {
						for (String elecourse : electiveCourseNeedToCompleted) {
							if (course.equals(elecourse)) {
								electiveCoursesCompleted.add(elecourse);
							}
						}
					}
					stuEleCourse.put(entry1.getKey(), (ArrayList<String>) electiveCoursesCompleted);
				}
			}
		}

		// electiveCoursesNotCompleted=ScheduleService.getCoursesNotCompleted(electiveCoursesNeedToBeCompleted,
		// electiveCoursesCompleted);

		for (StudentReportService student : srsList) {
			for (Entry<String, ArrayList<String>> entry1 : stuEleCourse.entrySet()) {
				if (student.getStudentID().equals(entry1.getKey()))
					student.setNoOfElectiveCoursesCompleted(entry1.getValue().size());
			}
		}
		for (StudentReportService student : srsList) {
			for (Student stu : studentData) {
				if (student.getStudentID().equals(stu.getId()))
					student.setYearPassingOut(stu.getSemester());
			}
		}

		return srsList;
	}

}
