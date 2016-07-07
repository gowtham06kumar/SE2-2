package com.univ.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.univ.beans.Course;
import com.univ.beans.DegreePlanReq;
import com.univ.beans.Faculty;
import com.univ.beans.Schedule;
import com.univ.beans.Student;
import com.univ.beans.StudentCourse;
import com.univ.importdata.CourseDM;
import com.univ.importdata.FacultyDM;
import com.univ.importdata.StudentCoursesDM;
import com.univ.importdata.StudentDM;

public class ScheduleService {

	public List<Schedule> prepareSchedule(String semester, List<Course> courseData,List<Faculty> facultyList, List<StudentCourse> studentCourseData, List<Student> studentList) {
		
		HashMap<String, ArrayList<String>> studentCourseListFinal = new HashMap<String, ArrayList<String>>();
		
		if(courseData == null) {
			courseData = CourseDM.loadCourses("TestDataCourses.csv");
		}
		if(facultyList == null) {
			facultyList = FacultyDM.importFacultyDataFromCsv("TestDataFaculty.csv");
		}
		if(studentCourseData == null) {
			studentCourseData = StudentCoursesDM.importStudentCourseData("STC.DUMP.csv");
		}
		if(studentList == null) {
			studentList = StudentDM.importStudentData("STU.DUMP.csv");
		}
		List<Course> courseList = getCoursesOfferedInSemester(semester, courseData);
		
		List<DegreePlanReq> degreeList = getDegreePLanRequ();
		
		studentCourseListFinal = studentCoursesYetToComplete(studentCourseData, studentList,degreeList);
	
		List<Schedule> scheduleList = assignFacultyToCourses(semester, courseList, facultyList);

		scheduleList = assignStudents(scheduleList, studentCourseListFinal);


		ArrayList<Schedule> updatedSch=new ArrayList<>();
		updatedSch.addAll(scheduleList);
		for (Schedule sch : updatedSch) {
			if (sch.getNumberOfStudentsToComplteCourse() > 25) {
				sch.setRemainingStudents(sch.getNumberOfStudentsToComplteCourse() - 25);
				sch.setNumberOfStudentsToComplteCourse(25);
				sch.setCourseAvailable(true);
			} else if (sch.getNumberOfStudentsToComplteCourse() < 25 && sch.getNumberOfStudentsToComplteCourse() > 17) {
				sch.setRemainingStudents(0);
				sch.setCourseAvailable(true);
			}else if (sch.getNumberOfStudentsToComplteCourse() <= 17) {
				sch.setRemainingStudents(sch.getNumberOfStudentsToComplteCourse());
				sch.setCourseAvailable(false);
				//scheduleList.remove(sch);
			}
		}
		
		scheduleList = addSections(scheduleList,courseList);
		return scheduleList;

	}

	public static List<Schedule> assignStudents(List<Schedule> scheduleList, HashMap<String, ArrayList<String>> studentCourseListFinal) {
		
		for (Schedule sch : scheduleList) {
			int numberOfStudentsToComplteCourse = 0;
			for (Entry<String, ArrayList<String>> entry : studentCourseListFinal.entrySet()) {
				ArrayList<String> coursesTobCompltedByStudent = entry.getValue();
				// for(int i=0;i<coursesTobCompltedByStudent.size();i++)
				// System.out.println(coursesTobCompltedByStudent.get(i).toString());
				for (String course : coursesTobCompltedByStudent) {
					if (sch.getCourse().equals(course)) {
						numberOfStudentsToComplteCourse = numberOfStudentsToComplteCourse + 1;
						sch.setNumberOfStudentsToComplteCourse(numberOfStudentsToComplteCourse);
						// scheduleList.add(sch);
					}
				}
			}

		}
		List<Schedule> scheduleListFinal = new ArrayList<Schedule>();
		for (Schedule sch : scheduleList) {
			if (sch.getNumberOfStudentsToComplteCourse() > 0)
				scheduleListFinal.add(sch);
		}
		// for(int i=0;i<scheduleList.size();i++)
		// System.out.println("Course "+scheduleList.get(i).getCourse()+"
		// Strength"+scheduleList.get(i).getNumberOfStudentsToComplteCourse());
		return scheduleListFinal;
	}

/*	public static List<Schedule> addSections(List<Schedule> scheduleList) {

		int i = 1;

		for (Schedule sch : scheduleList) {
			sch.setSecnum("Sec" + i++);
		}

		return scheduleList;
	}*/
	public static List<Schedule> addSections(List<Schedule> scheduleList, List<Course> courseList) {

		for (Schedule sch : scheduleList) {
		sch.setSecnum(sch.getCourse()+"-"+getCourseName(sch.getCourse(),courseList));
		sch.setAcademicLevel("Graduate");
		sch.setCredits(3);
		}

		return scheduleList;
		}
	 public static String getCourseName(String courseId,List<Course> courseList){

		 for(Course course:courseList)
		 if(course.getCode().equalsIgnoreCase(courseId))
		 return course.getName();

		 return null;
		 }

	public static List<Schedule> assignFacultyToCourses(String semester, List<Course> courseList, List<Faculty> facList) {

		List<Schedule> scheduleList = new ArrayList<Schedule>();

		for (Course course : courseList) {
			if (semester.equalsIgnoreCase("fall")) {
				for (Faculty fac : facList) {
					
					Schedule sch = new Schedule();
					
					if (course.getTeachers().contains(",")) {
						String[] faculties = course.getTeachers().split(",");
						for (int j = 0; j < faculties.length; j++) {
							faculties[j] = faculties[j].replace("\"", "");
							if (faculties[j].equalsIgnoreCase(fac.getLastName())
									&& Integer.parseInt(fac.getMaxLoadFall()) > 0) {
								// System.out.println("In If " + faculties[j] +
								// " " + fac.getLastName() + " " + j);
								sch.setCourse(course.getCode());
								sch.setFaculty(fac.getLastName());
								sch.setSemester("fall");
								scheduleList.add(sch);
								fac.setMaxLoadFall(Integer
										.toString((Integer.parseInt(fac.getMaxLoadFall()) - course.getCreditHours())));
							}
						}
					}
					else if (course.getTeachers().equalsIgnoreCase(fac.getLastName())
							&& Integer.parseInt(fac.getMaxLoadFall()) >= course.getCreditHours()) {
						sch.setCourse(course.getCode());
						sch.setFaculty(fac.getLastName());
						sch.setSemester("fall");
						scheduleList.add(sch);
						fac.setMaxLoadFall(
								Integer.toString((Integer.parseInt(fac.getMaxLoadFall()) - course.getCreditHours())));
					}
					
				}
			} else if (semester.equalsIgnoreCase("summer")) {
				for (Faculty fac : facList) {
					Schedule sch = new Schedule();
					if (course.getTeachers().contains(",")) {
						String[] faculties = course.getTeachers().split(",");
						for (int j = 0; j < faculties.length; j++) {
							faculties[j] = faculties[j].replace("\"", "");
							if (faculties[j].equalsIgnoreCase(fac.getLastName())
									&& Integer.parseInt(fac.getMaxLoadSummer()) >= course.getCreditHours()) {
								System.out.println("In If " + faculties[j] + " " + fac.getLastName() + " " + j);
								sch.setCourse(course.getCode());
								sch.setFaculty(fac.getLastName());
								sch.setSemester("summer");
								scheduleList.add(sch);
								fac.setMaxLoadSummer(Integer.toString(
										(Integer.parseInt(fac.getMaxLoadSummer()) - course.getCreditHours())));
							}
						}
					}
					if (course.getTeachers().equalsIgnoreCase(fac.getLastName())
							&& Integer.parseInt(fac.getMaxLoadSummer()) >= course.getCreditHours()) {
						sch.setCourse(course.getCode());
						sch.setFaculty(fac.getLastName());
						sch.setSemester("summer");
						scheduleList.add(sch);
						fac.setMaxLoadSummer(
								Integer.toString((Integer.parseInt(fac.getMaxLoadSummer()) - course.getCreditHours())));
					}
					
				}
			} else if (semester.equalsIgnoreCase("spring")) {
				for (Faculty fac : facList) {
					Schedule sch = new Schedule();
					if (course.getTeachers().contains(",")) {
						String[] faculties = course.getTeachers().split(",");
						for (int j = 0; j < faculties.length; j++) {
							faculties[j] = faculties[j].replace("\"", "");
							if (faculties[j].equalsIgnoreCase(fac.getLastName())
									&& Integer.parseInt(fac.getMaxLoadSpring()) > 0) {
								System.out.println("In If " + faculties[j] + " " + fac.getLastName() + " " + j);
								sch.setCourse(course.getCode());
								sch.setFaculty(fac.getLastName());
								sch.setSemester("spring");
								scheduleList.add(sch);
								fac.setMaxLoadSpring(Integer.toString(
										(Integer.parseInt(fac.getMaxLoadSpring()) - course.getCreditHours())));
							}
						}
					}
					if (course.getTeachers().equalsIgnoreCase(fac.getLastName())
							&& Integer.parseInt(fac.getMaxLoadSpring()) > 0) {
						sch.setCourse(course.getCode());
						sch.setFaculty(fac.getLastName());
						sch.setSemester("spring");
						scheduleList.add(sch);
						fac.setMaxLoadSpring(
								Integer.toString((Integer.parseInt(fac.getMaxLoadSpring()) - course.getCreditHours())));
					}

				}
			}

		}
		return scheduleList;
	}

	public static List<DegreePlanReq> getDegreePLanRequ() {
		String line = null;
		String[] coursedetails;

		List<DegreePlanReq> degreeList = new ArrayList<DegreePlanReq>();
		DegreePlanReq degree = null;

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader("TestDataDegreePlanReq.csv");

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			// while there is data in file do this
			while ((line = bufferedReader.readLine()) != null) {
				if (line.contains("DegreeCode")) {
					continue;
				}
				String line1 = remCommaFmData(line);
				coursedetails = line1.split(",");
				degree = new DegreePlanReq();
				degree.setDegreeCode(coursedetails[0]);
				degree.setDesc(coursedetails[1]);
				degree.setHours(coursedetails[2]);
				degree.setType(coursedetails[3]);
				String tempString;
				String[] myString;
				ArrayList<String> courseList = new ArrayList<String>();
				tempString = coursedetails[4].replaceAll("\"", "");
				myString = tempString.replaceAll("@", ",").split(",");
				for (int i = 0; i < myString.length; i++) {
					courseList.add(myString[i]);
				}
				degree.setCourses(courseList);

				//if (degreeCode.equalsIgnoreCase(degree.getDegreeCode()))
					degreeList.add(degree);
			}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return degreeList;
	}

	/*private List<Course> getCoursesRequired(List<Course> courseList2, List<DegreePlanReq> degreeList) {
		for (Course course : courseList2) {
			// String[] courses;
			ArrayList<String> courses;
			for (DegreePlanReq degree : degreeList) {
				if (degree.getType().equalsIgnoreCase("Required")) {
					courses = degree.getCourses();// .split(",");
					// for (int i = 0; i <= courses.length; i++) {
					// }
				}
			}
		}

		return null;
	}*/

	public static List<Course> getCoursesOfferedInSemester(String semester, List<Course> courseList2) {

		List<Course> fallList = new ArrayList<Course>();
		List<Course> springList = new ArrayList<Course>();
		List<Course> summerList = new ArrayList<Course>();

		for (Course course : courseList2) {
			if (course.getOfferedFall().equalsIgnoreCase("Y"))
				fallList.add(course);
			if (course.getOfferedSpring().equalsIgnoreCase("Y"))
				springList.add(course);
			if (course.getOfferedSummer().equalsIgnoreCase("Y"))
				summerList.add(course);
		}

		if (semester.equalsIgnoreCase("fall"))
			return fallList;
		else if (semester.equalsIgnoreCase("spring"))
			return springList;
		else if (semester.equalsIgnoreCase("summer"))
			return summerList;
		else
			return null;
	}

	public static HashMap<String, ArrayList<String>> studentCoursesYetToComplete(List<StudentCourse> studentCourseData, List<Student> studentList, List<DegreePlanReq> degreeList) {
		
		HashMap<String, ArrayList<String>> Studenthm = new HashMap<>();
		HashMap<String, ArrayList<String>> Studenthm2 = new HashMap<>();
		HashMap<String, ArrayList<String>> studentCourseListFinal = new HashMap<String, ArrayList<String>>();
		
		for (Student student : studentList) {
			ArrayList<String> allCourses = getAllCourses(student.getDegreeCode(), degreeList);
			if (allCourses.size() > 0)
				Studenthm.put(student.getId(), allCourses);

		}
		for (Student student : studentList) {
			ArrayList coursesCompletedList = new ArrayList<>();
			for (StudentCourse studentCourses : studentCourseData) {
				if (student.getId().equals(studentCourses.getStudent().getId())) {
					coursesCompletedList.add(studentCourses.getCourseID());
					Studenthm2.put(student.getId(), coursesCompletedList);
				}
			}
		}
		System.out.println("Size of hash map total course" + Studenthm.size());
		for (Entry<String, ArrayList<String>> entry : Studenthm.entrySet()) {
			System.out.println("Student ID all courses " + entry.getKey() + "Student Courses " + entry.getValue());
		}
		/*
		 * System.out.println("Size of hash map courses completed"
		 * +Studenthm2.size ()); for(Entry<String, ArrayList<String>>
		 * entry2:Studenthm2.entrySet()){ System.out.println(
		 * "Student ID completed" +entry2.getKey()+"Student Courses "
		 * +entry2.getValue()); }
		 */

		studentCourseListFinal = getCoursesNotCompleted(Studenthm, Studenthm2);
		return studentCourseListFinal;
	}

	public static ArrayList<String> getAllCourses(String degreeCode, List<DegreePlanReq> degreeList) {

		ArrayList<String> allCourses = new ArrayList();
		// allCourses=null;
		for (DegreePlanReq degree : degreeList) {
			if (degree.getDegreeCode().equals(degreeCode))
				allCourses.addAll(degree.getCourses());
		}
		return allCourses;

	}

	public static HashMap<String, ArrayList<String>> getCoursesNotCompleted(HashMap<String, ArrayList<String>> Studenthm, HashMap<String, ArrayList<String>> Studenthm2) {
		
		HashMap<String, ArrayList<String>> studentCourseListFinal = new HashMap<String, ArrayList<String>>();
		
		for (Entry<String, ArrayList<String>> entry2 : Studenthm2.entrySet()) {
			for (Entry<String, ArrayList<String>> entry : Studenthm.entrySet()) {
				if (entry2.getKey().equals(entry.getKey())) {
					ArrayList<String> allCourses = new ArrayList<String>();
					allCourses = entry.getValue();
					ArrayList<String> coursesCompleted = new ArrayList<String>();
					coursesCompleted = entry2.getValue();
					ArrayList<String> coursesNotCompleted = new ArrayList();
					coursesNotCompleted.addAll(allCourses);
					// coursesNotCompleted=null;
					// System.out.println("Size of course nt
					// cmpltd"+coursesNotCompleted.size());
					// System.out.println("Size of course
					// cmpltd"+coursesCompleted.size());
					for (String course : allCourses) {
						for (String courseComp : coursesCompleted) {
							if (course.equals(courseComp)) {
								coursesNotCompleted.remove(course);
								// coursesNotCompleted.add(course);
							}
						}
					}
					studentCourseListFinal.put(entry.getKey(), coursesNotCompleted);
				}
			}
		}
		/*
		 * System.out.println("size of hashmap"+studentCourseListFinal.size());
		 * for(Entry<String, ArrayList<String>>
		 * entry3:studentCourseListFinal.entrySet()){ System.out.println(
		 * "Student ID To be completed" +entry3.getKey()+"Student Courses "
		 * +entry3.getValue()); }
		 */
		return studentCourseListFinal;
	}

	public static String remCommaFmData(String str) {

		boolean inQuotes = false;
		String copy = new String();

		for (int i = 0; i < str.length(); ++i) {
			if (str.charAt(i) == '"')
				inQuotes = !inQuotes;
			if (str.charAt(i) == ',' && inQuotes)
				copy += '@';
			else
				copy += str.charAt(i);
		}

		return copy;
	}
	public static List<DegreePlanReq> getReqCoursesFromDegree() {
		String line = null;
		String[] coursedetails;

	//	List<DegreePlanReq> degreeList = new ArrayList<DegreePlanReq>();
		List<DegreePlanReq> requiredCourseList = new ArrayList<DegreePlanReq>();
		DegreePlanReq degree = null;

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader("TestDataDegreePlanReq.csv");

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			// while there is data in file do this
			while ((line = bufferedReader.readLine()) != null) {
				if (line.contains("DegreeCode")) {
					continue;
				}
				String line1 = remCommaFmData(line);
				coursedetails = line1.split(",");
				if(coursedetails[3].equals("Required")){
				degree = new DegreePlanReq();
				degree.setDegreeCode(coursedetails[0]);
				degree.setDesc(coursedetails[1]);
				degree.setHours(coursedetails[2]);
				degree.setType(coursedetails[3]);
				String tempString;
				String[] myString;
				ArrayList<String> courseList = new ArrayList<String>();
				tempString = coursedetails[4].replaceAll("\"", "");
				myString = tempString.replaceAll("@", ",").split(",");
				for (int i = 0; i < myString.length; i++) {
					courseList.add(myString[i]);
				}
				degree.setCourses(courseList);
				requiredCourseList.add(degree);
			}
		}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return requiredCourseList;
	}
	public static List<DegreePlanReq> getElecCoursesFromDegree() {
		String line = null;
		String[] coursedetails;
		List<DegreePlanReq> electiveCourseList = new ArrayList<DegreePlanReq>();
		DegreePlanReq degree = null;

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader("TestDataDegreePlanReq.csv");

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			// while there is data in file do this
			while ((line = bufferedReader.readLine()) != null) {
				if (line.contains("DegreeCode")) {
					continue;
				}
				String line1 = remCommaFmData(line);
				coursedetails = line1.split(",");
				if(coursedetails[3].equals("Elective")){
				degree = new DegreePlanReq();
				degree.setDegreeCode(coursedetails[0]);
				degree.setDesc(coursedetails[1]);
				degree.setHours(coursedetails[2]);
				degree.setType(coursedetails[3]);
				String tempString;
				String[] myString;
				ArrayList<String> courseList = new ArrayList<String>();
				tempString = coursedetails[4].replaceAll("\"", "");
				myString = tempString.replaceAll("@", ",").split(",");
				for (int i = 0; i < myString.length; i++) {
					courseList.add(myString[i]);
				}
				degree.setCourses(courseList);
				electiveCourseList.add(degree);
			}
		}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return electiveCourseList;

	}
	

}
