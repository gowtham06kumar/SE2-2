package com.univ.importdata;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.univ.beans.Degree;

public class DegreeDM {

	public static List<Degree> storeDegreeData(String fileName) {

		//fileName = "TestDataDegrees.csv";
		String line = null;
		String[] token;

		List<Degree> degreePlanList = new ArrayList<Degree>();
		Degree degree = null;

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			// while there is data in file do this
			while ((line = bufferedReader.readLine()) != null) {

				if (line.contains("DegreeCode")) {
					continue;
				}

				token = line.split(",");
				degree = new Degree();
				degree.setDegreeCode(token[0]);
				degree.setGradSchool(token[1]);
				degree.setDegreeName(token[2]);
				degree.setForecast(token[3]);
				degreePlanList.add(degree);
			}

			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");

		}
		return degreePlanList;
	}

	public static List<Degree> getListfromTable(DefaultTableModel dtm) {
		List<Degree> degreeList = new ArrayList<Degree>();

		for (int i = 0; i < dtm.getRowCount(); i++) {
			Degree degree = new Degree();

			degree.setDegreeCode(dtm.getValueAt(i, 0).toString());
			degree.setGradSchool(dtm.getValueAt(i, 1).toString());
			degree.setDegreeName(dtm.getValueAt(i, 2).toString());
			degree.setForecast(dtm.getValueAt(i, 3).toString());
			degreeList.add(degree);
		}

		return degreeList;

	}
}
