package com.univ.main;

import javax.swing.JOptionPane;

import com.univ.screens.HomeScreen;

public class ECSGradDemo {

	public static void main(String[] args) {
		try {
			HomeScreen frame = new HomeScreen();
			frame.setSize(600, 400);
			frame.setLocation(400, 250);
			frame.setVisible(true);
			frame.setTitle("ECSGradSchool");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
