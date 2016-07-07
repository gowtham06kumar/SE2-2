package com.univ.screens.maintanance;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.univ.beans.Course;
import com.univ.beans.Schedule;
import com.univ.importdata.CourseDM_old;
import com.univ.screens.MenuScreen;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class ScheduleDisplayScreen extends JFrame{

	public List<Schedule> scheduleList;
	private static final long serialVersionUID = 4406724596143778906L;
	private JPanel contentPane;
	private JTable table_1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	
	public ScheduleDisplayScreen(){}
	public ScheduleDisplayScreen(List<Schedule> scheduleList){
		setVisible(true);
		setLocation(400,250);
	
		this.scheduleList = scheduleList;
		
		DefaultTableModel dtm = new DefaultTableModel();
		
		dtm.addColumn("semester");
		dtm.addColumn("course");
		dtm.addColumn("secnum");
		dtm.addColumn("faculty");
		dtm.addColumn("Student Strength");
		dtm.addColumn("Credit hours");
		dtm.addColumn("Academic Level");
		//dtm.addColumn("capacity");
		
		for(Schedule sch : scheduleList)
		{
			//System.out.println(sch.toString());
			if(sch.isCourseAvailable())
				dtm.addRow(new Object[]{sch.getSemester(),sch.getCourse(),sch.getSecnum(),sch.getFaculty(),sch.getNumberOfStudentsToComplteCourse(),sch.getCredits(),sch.getAcademicLevel()});
		}
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 459, 327);
	
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		table_1 = new JTable();
		table_1.setModel(dtm);
		JScrollPane scrollPane = new JScrollPane(table_1);
		contentPane.add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Course code");
		lblNewLabel.setBounds(22, 8, 62, 14);
		panel_1.add(lblNewLabel);
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		
	}
}
