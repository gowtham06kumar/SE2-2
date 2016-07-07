package com.univ.screens;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.univ.beans.Schedule;
import com.univ.screens.maintanance.FacultyReportDisplayScreen;
import com.univ.screens.maintanance.ScheduleDisplayScreen;
import com.univ.service.FacultyReportService;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FacultyReportScreen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * @param scheduleList 
	 */
	public FacultyReportScreen(){}
	public void generateFacultyReport(Map<String,List<Schedule>> completeScheduleHashMap) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblSelectSemester = new JLabel("Select Semester");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Fall", "Spring", "Summer"}));
		
		JButton btnDisplayFacReport = new JButton("Display Report");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(37)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnDisplayFacReport)
						.addComponent(lblSelectSemester))
					.addGap(49)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(163, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(69)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSelectSemester)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addComponent(btnDisplayFacReport)
					.addContainerGap(91, Short.MAX_VALUE))
		);
		
		btnDisplayFacReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				FacultyReportService service=new FacultyReportService();
				Map<String,Integer> hm=service.prepareReport(comboBox.getSelectedItem().toString(),completeScheduleHashMap);
				if(hm.isEmpty())
					JOptionPane.showMessageDialog(null, "Please generate schedule");
				else{
				dispose();
				new FacultyReportDisplayScreen(hm);
				}
			
				//generateFacultyReport(comboBox.getSelectedItem());
			}
		});
		contentPane.setLayout(gl_contentPane);
	}
	
	
}
