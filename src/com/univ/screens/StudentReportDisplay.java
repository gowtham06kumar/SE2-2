package com.univ.screens;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.univ.beans.DegreePlanReq;
import com.univ.beans.Schedule;
import com.univ.beans.Student;
import com.univ.beans.StudentCourse;
import com.univ.importdata.StudentCoursesDM;
import com.univ.importdata.StudentDM;
//import com.univ.screens.maintanance.StudentReportService;
import com.univ.service.ScheduleService;
import com.univ.service.StudentReportService;

public class StudentReportDisplay extends JFrame{

	public List<Schedule> scheduleList;
	private static final long serialVersionUID = 4406724596143778906L;
	private JPanel contentPane;
	private JTable table_1;
	
	public StudentReportDisplay(){
		setVisible(true);
		setLocation(400,250);
	

		DefaultTableModel dtm = new DefaultTableModel();
		List<StudentReportService> srsList;
		
		StudentReportService srservice= new StudentReportService();
		List<Student> studentData=new ArrayList<Student>();
		List<DegreePlanReq> requiredDegreeList=new ArrayList<DegreePlanReq>();
		List<DegreePlanReq> electiveDegreeList=new ArrayList<DegreePlanReq>();
		List<StudentCourse> studentCourseData=new ArrayList<StudentCourse>();
		studentCourseData = StudentCoursesDM.importStudentCourseData("STC.DUMP.csv");
		studentData = StudentDM.importStudentData("STU.DUMP.csv");
		
		requiredDegreeList=ScheduleService.getReqCoursesFromDegree();
		for(int i=0;i<requiredDegreeList.size();i++)
			System.out.println("Degree Courses"+requiredDegreeList.get(i).getDegreeCode()+" "+requiredDegreeList.get(i).getCourses());
		electiveDegreeList=ScheduleService.getElecCoursesFromDegree();
		for(int i=0;i<electiveDegreeList.size();i++)
			System.out.println("Degree Courses"+electiveDegreeList.get(i).getDegreeCode()+" "+electiveDegreeList.get(i).getCourses());
		srsList=srservice.generateStudentReport(studentData,studentCourseData,requiredDegreeList,electiveDegreeList);
	
		dtm.addColumn("Student Name");
		dtm.addColumn("Number of courses already completed");
		dtm.addColumn("Number of Required courses yet to be completed");
		dtm.addColumn("Number of Elective courses completed");
		dtm.addColumn("Year Passing out");
		for (StudentReportService studentList : srsList)
		{
			
			dtm.addRow(new Object[]{studentList.getStudentID(),studentList.getNoOfCoursesCompleted(),studentList.getNoOfCoursesNotCompleted(),studentList.getNoOfElectiveCoursesCompleted(),studentList.getYearPassingOut()});
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
		
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		
	}
	
}
