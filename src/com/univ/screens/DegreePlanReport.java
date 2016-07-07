package com.univ.screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.univ.beans.Degree;
import com.univ.beans.Student;
import com.univ.importdata.DegreeDM;
import com.univ.importdata.StudentDM;
import com.univ.screens.maintanance.DegreePlanReportDisplayScreen;
import com.univ.service.DegreePlanReportService;

public class DegreePlanReport extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -114365266044137547L;
	private JPanel contentPane;
	List<Student> studentData = null;
	List<Degree> degreeData=null;


	public DegreePlanReport() {}
	public void generateDegreeReport(){
		System.out.println("In Degree Plan screen");
		setTitle("Degree Plan Report");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblSelectYear = new JLabel("Select Year");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"2015", "2016", "2017", "2018", "2019"}));
		
		JLabel lblSelectSemester = new JLabel("Select Semester");
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Fall", "Spring", "Summer"}));
		
		JButton btnGenerateReport = new JButton("Generate Report");
		btnGenerateReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DegreePlanReportService service =new DegreePlanReportService();

				Map<String, Integer> hm=null;

				studentData=StudentDM.importStudentData("STU.DUMP.csv");
				degreeData=DegreeDM.storeDegreeData("TestDataDegrees.csv");
				hm=service.generateDegreePlanReport(comboBox_1.getSelectedItem().toString(),comboBox.getSelectedItem().toString() ,studentData,degreeData);
				if(hm.isEmpty())
					JOptionPane.showMessageDialog(null, "No students available for seleceted semester and year");
				else
					new DegreePlanReportDisplayScreen(hm);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(85)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnGenerateReport)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSelectYear)
								.addComponent(lblSelectSemester))
							.addGap(42)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(125, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(67)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSelectYear)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSelectSemester)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnGenerateReport)
					.addContainerGap(71, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);

	}
}
