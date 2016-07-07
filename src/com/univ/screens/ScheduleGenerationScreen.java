package com.univ.screens;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import com.univ.beans.Course;
import com.univ.beans.Degree;
import com.univ.beans.Faculty;
import com.univ.beans.Schedule;
import com.univ.beans.Student;
import com.univ.beans.StudentCourse;
import com.univ.beans.University;
import com.univ.screens.maintanance.ScheduleDisplayScreen;
import com.univ.service.ScheduleService;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ScheduleGenerationScreen extends JFrame {

	private JPanel contentPane;
	public List<Schedule> scheduleList;
	private String semester;
	/**
	 * Create the frame.
	 * @param facultyData 
	 * @param courseList 
	 */
	public ScheduleGenerationScreen(){
			
	}
	
	public void generateSchedule(List<Course> courseList,
			List<Faculty> facultyData, List<StudentCourse> studentCourseData,
			List<Student> studentData) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Select Semester");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Fall", "Spring", "Summer"}));
		
		JButton btnGetCourses = new JButton("Display Schedule");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(37)
							.addComponent(lblNewLabel)
							.addGap(47)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(45)
							.addComponent(btnGetCourses)))
					.addContainerGap(200, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(47)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(56)
					.addComponent(btnGetCourses)
					.addContainerGap(97, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		btnGetCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox.getSelectedItem().equals("Fall")){
					semester = comboBox.getSelectedItem().toString();
					System.out.println("In fall");
				}
				if(comboBox.getSelectedItem().equals("Spring"))
				{
					semester = comboBox.getSelectedItem().toString();
					System.out.println("In Spring");
				}	
				if(comboBox.getSelectedItem().equals("Summer"))
				{
					semester = comboBox.getSelectedItem().toString();
					System.out.println("In Summer");
				}
				
				ScheduleService service = new  ScheduleService();
				
				scheduleList = service.prepareSchedule(comboBox.getSelectedItem().toString(), courseList, facultyData,studentCourseData,studentData);
	
				MenuScreen.completeScheduleHashMap.put(semester, scheduleList);
				dispose();
				new ScheduleDisplayScreen(scheduleList);
			}
		
		});
		
	}
}
