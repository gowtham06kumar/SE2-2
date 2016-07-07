package com.univ.importdata;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.univ.beans.Student;



public class StudentDM {

	public static List<Student> importStudentData(String fileName) {

		String line = null;
		String[] token;
		List<Student> studentList = new ArrayList<Student>();
		Student student = null;
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
				student.setId(token[0]);
				student.setDegreeCode(token[1]);
				student.setSemester(token[2]);
				studentList.add(student);
			}

			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");

		}
		return studentList;
	}
}
