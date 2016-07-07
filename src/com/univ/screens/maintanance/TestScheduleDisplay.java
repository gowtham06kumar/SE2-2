package com.univ.screens.maintanance;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TestScheduleDisplay extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblNewLabel_3;

	/**
	 * Create the frame.
	 */
	public TestScheduleDisplay(String semester,int studentsCount,int coursesWithinFill,int coursesExceed) {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 545, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Students with sections they need");
		lblNewLabel.setBounds(49, 62, 190, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Courses within fill");
		lblNewLabel_1.setBounds(49, 114, 131, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Courses exceed 100%");
		lblNewLabel_2.setBounds(49, 164, 131, 14);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(286, 59, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText(""+studentsCount);
		
		textField_1 = new JTextField();
		textField_1.setBounds(286, 111, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(""+coursesWithinFill);
		
		textField_2 = new JTextField();
		textField_2.setBounds(286, 161, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setText(""+coursesExceed);
		
		lblNewLabel_3 = new JLabel("Test Schedule for Semester - "+semester);
		lblNewLabel_3.setBounds(108, 22, 216, 14);
		contentPane.add(lblNewLabel_3);
	}
}
