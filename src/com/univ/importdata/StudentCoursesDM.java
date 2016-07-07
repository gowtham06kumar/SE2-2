package com.univ.importdata;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.univ.beans.Student;
import com.univ.beans.StudentCourse;

public class StudentCoursesDM {
	public static List<StudentCourse> importStudentCourseData(String fileName) {

		//fileName = "STC.DUMP.csv";
		String line = null;
		String[] token;
		List<StudentCourse> studentCourseList = new ArrayList<StudentCourse>();
		Student student = null;
		StudentCourse stuCourse = null;
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			// while there is data in file do this
			while ((line = bufferedReader.readLine()) != null) {
				// split data by comma
				token = line.split(",");
				student = new Student();
				stuCourse = new StudentCourse();
				student.setId(token[0]);
				//student.setDegreeCode(token[1]);
				stuCourse.setCourseID(token[1]);
				student.setSemester(token[3]);
				stuCourse.setStudent(student);
				//stuCourse.setStudent(token[0]);
				stuCourse.setCourse(token[2]);
				stuCourse.setGrade(token[4]);
				studentCourseList.add(stuCourse);
			}
			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");

		}
		return studentCourseList;
	}
}
