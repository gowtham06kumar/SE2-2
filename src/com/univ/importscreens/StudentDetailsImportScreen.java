package com.univ.importscreens;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.univ.beans.Student;
import com.univ.importdata.StudentDM;
import com.univ.screens.MenuScreen;

public class StudentDetailsImportScreen extends JFrame implements
		ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7453617270157980163L;
	private JPanel panel, panel1, panel2, panel3;
	private JLabel label1;
	private JButton button1, button2;
	private JTextField filePath;
	private JFileChooser fileDialog;
	private JButton showFileDialogButton;

	public StudentDetailsImportScreen() {
		setVisible(true);
		setLocation(400, 250);
		setSize(600, 400);

		initComponents();
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	}

	private void initComponents() {

		panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 55, 50));
		label1 = new JLabel("Choose a file to import Student details...");
		panel1.add(label1);

		panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 55, 50));
		filePath = new JTextField(20);
		showFileDialogButton = new JButton("Open File");

		panel2.add(filePath);
		panel2.add(showFileDialogButton);

		panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 55, 50));
		button1 = new JButton("Display");
		panel3.add(button1);
		button2 = new JButton("Back");
		panel3.add(button2);
		panel = new JPanel(new GridLayout(4, 1));
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);
		setTitle("ECSGradSchool");

		add(panel, BorderLayout.CENTER);
		showFileDialogButton.addActionListener(this);
		button1.addActionListener(this);
		button2.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == showFileDialogButton) {
			fileDialog = new JFileChooser();
			fileDialog.setCurrentDirectory(new File(System
					.getProperty("user.home")));
			int result = fileDialog.showOpenDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileDialog.getSelectedFile();
				System.out.println("Selected file: "
						+ selectedFile.getAbsolutePath());
				filePath.setText("File Selected :"
						+ selectedFile.getAbsolutePath());
			}
		} else if (e.getSource() == button1) {
			dispose();
			List<Student> studentList = StudentDM
					.importStudentData("STU.DUMP.csv");

			DefaultTableModel dtm = new DefaultTableModel();

			dtm.addColumn("StudentID");
			dtm.addColumn("DegreeCode");
			dtm.addColumn("Semester");

			for (Student student : studentList) {
				dtm.addRow(new Object[] { student.getId(),
						student.getDegreeCode(), student.getSemester() });
			}

			new ImportTableDisplay(dtm);
		}else if (e.getSource() == button2) {
			dispose();
			new MenuScreen();
		}
	}
}
