package com.univ.importdata;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import com.univ.beans.University;

public class UniversityDM {
	public static List<University> loadUniversityData(String fileName) {
		String line = null;
		String[] universitydetails;

		List<University> universityList = new ArrayList<University>();
		University university = null;

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			// while there is data in file do this
			while ((line = bufferedReader.readLine()) != null) {

				if (line.contains("Name")) {
					continue;
				}
				universitydetails = line.split(",");
				university = new University();
				university.setName(universitydetails[0]);
				university.setAbbrevation(universitydetails[1]);
				universityList.add(university);
			}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return universityList;
	}

	public static List<University> getListfromTable(DefaultTableModel dtm) {
		List<University> universityList = new ArrayList<University>();

		for (int i = 0; i < dtm.getRowCount(); i++) {
			University univercity = new University();

			univercity.setName(dtm.getValueAt(i, 0).toString());
			univercity.setAbbrevation(dtm.getValueAt(i, 1).toString());
			universityList.add(univercity);
		}

		return universityList;

	}
}
