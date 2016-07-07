package com.univ.screens;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.univ.service.DegreePlanService;

public class UpdateForecast extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panel, panel1, panel2, panel3;
	private JLabel label1, label2;
	private JComboBox<String> comboBox1;
	private JButton button1;
	JTextField students_count;

	DegreePlanService degreeService;

	UpdateForecast() {
		setVisible(true);
		setSize(400, 400);
		setLocation(400, 250);
		degreeService = new DegreePlanService();

		panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 55, 50));
		label1 = new JLabel("Degree");
		String[] semesters = degreeService.getDegrees();
		comboBox1 = new JComboBox<String>(semesters);
		comboBox1.setPreferredSize(new Dimension(120, 20));
		panel1.add(label1);
		panel1.add(comboBox1);

		panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 55, 50));
		label2 = new JLabel("Expected Students");
		students_count = new JTextField();
		students_count.setPreferredSize(new Dimension(100, 20));
		panel2.add(label2);
		panel2.add(students_count);

		panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 55, 50));
		button1 = new JButton("Update");
		panel3.add(button1);

		panel = new JPanel(new GridLayout(4, 1));
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);

		add(panel, BorderLayout.CENTER);
		setTitle("ECSGradSchool");

		button1.addActionListener(this);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("In UpdateForecase Screen...");
		dispose();
	}
}
