package com.univ.screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.univ.beans.Course;
import com.univ.beans.Degree;
import com.univ.beans.Faculty;
import com.univ.beans.Schedule;
import com.univ.beans.Student;
import com.univ.beans.StudentCourse;
import com.univ.beans.University;
import com.univ.importdata.CourseDM_old;
import com.univ.importdata.DegreeDM;
import com.univ.importdata.FacultyDM;
import com.univ.importdata.StudentCoursesDM;
import com.univ.importdata.StudentDM;
import com.univ.importdata.UniversityDM;
import com.univ.importscreens.StudentCourseDetailsImportScreen;
import com.univ.importscreens.StudentDetailsImportScreen;
import com.univ.screens.maintanance.CourseMaintScreen;
import com.univ.screens.maintanance.DegreePlanMaintScreen;
import com.univ.screens.maintanance.FacultyMaintScreen;
import com.univ.screens.maintanance.UniversityMaintScreen;

public class MenuScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static List<Course> courseList;
	public static List<Degree> degreeList;
	public static List<Faculty> facultyData;
	public static List<University> universityList;
	public static List<Schedule> scheduleList;
	public static List<StudentCourse> studentCourseData;
	public static List<Student> studentData;
	public static Map<String,List<Schedule>> completeScheduleHashMap = new HashMap<>();

	/**
	 * Create the frame.
	 */
	public MenuScreen() {
		setVisible(true);
		setSize(400, 400);
		setLocation(400, 250);

		setTitle("ESCGradSchool ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 424, 21);
		getContentPane().add(menuBar);

		JMenu mnNewMenu = new JMenu("Maintainence");
		menuBar.add(mnNewMenu);

		JMenuItem mntmUniversity = new JMenuItem("University");
		mntmUniversity.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(universityList == null)
				{
					universityList = UniversityDM.loadUniversityData("TestDataUniversityName.csv");
				}	
				new UniversityMaintScreen(universityList);
			}
		});
		mnNewMenu.add(mntmUniversity);

		JMenuItem mntmCourses = new JMenuItem("Courses");
		mntmCourses.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(courseList == null)
				{
					courseList = CourseDM_old.loadCourses("TestDataCourses.csv");
				}
				new CourseMaintScreen(courseList);
			}
		});
		mnNewMenu.add(mntmCourses);

		JMenuItem mntmFaculty = new JMenuItem("Faculty");
		mntmFaculty.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(facultyData == null)
				{
					facultyData = FacultyDM.importFacultyDataFromCsv("TestDataFaculty.csv");
				}
				new FacultyMaintScreen(facultyData);
			}
		});
		mnNewMenu.add(mntmFaculty);
		
		JMenuItem mntmDegree = new JMenuItem("Degree");
		mntmDegree.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(degreeList == null)
				{
					degreeList = DegreeDM.storeDegreeData("TestDataDegrees.csv");
				}			
				new DegreePlanMaintScreen(degreeList);
			}
		});
		mnNewMenu.add(mntmDegree);
		
		JMenuItem mntmForecast = new JMenuItem("UpdateForecast");
		mntmForecast.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new UpdateForecast();
			}
		});
		mnNewMenu.add(mntmForecast);

		JMenu mnNewMenu_1 = new JMenu("Import");
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmStudentdata = new JMenuItem("StudentData");
		mntmStudentdata.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(studentData == null)
				{
					studentData = StudentDM.importStudentData("STU.DUMP.csv");
				}	
				new StudentDetailsImportScreen();
			}
		});
		mnNewMenu_1.add(mntmStudentdata);
		
		JMenuItem mntmStudentCoursedata = new JMenuItem("StudentCourseData");
		mntmStudentCoursedata.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(studentCourseData == null)
				{
					studentCourseData = StudentCoursesDM.importStudentCourseData("StudentData.csv");
				}
				new StudentCourseDetailsImportScreen();
			}
		});
		mnNewMenu_1.add(mntmStudentCoursedata);

		JMenu mnNewMenu_2 = new JMenu("Schedule");
		menuBar.add(mnNewMenu_2);

		JMenuItem mntmGenerate = new JMenuItem("Generate");
		mntmGenerate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				ScheduleGenerationScreen schscreen = new ScheduleGenerationScreen();
				schscreen.generateSchedule(courseList, facultyData, studentCourseData, studentData);
				
			}
		});
		mnNewMenu_2.add(mntmGenerate);

		JMenuItem mntmTest = new JMenuItem("Test");
		mntmTest.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				if(completeScheduleHashMap.isEmpty())
				{
					//System.out.println("Please generate the schedule...");
					JOptionPane.showMessageDialog(null, "Please generate the schedule...");
				}
				else
				{
					new TestScheduleScreen(completeScheduleHashMap);
				}
				
			}
		});
		mnNewMenu_2.add(mntmTest);

		JMenu mnNewMenu_3 = new JMenu("Report");
		menuBar.add(mnNewMenu_3);

		JMenuItem mntmFacultyload = new JMenuItem("FacultyLoad");
		mntmFacultyload.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(completeScheduleHashMap.isEmpty())
				{
					System.out.println("Please generate the schedule...");
				}
				else
				{
					FacultyReportScreen frs=new FacultyReportScreen();
					for (Entry<String, List<Schedule>> entry : completeScheduleHashMap.entrySet()) {
						System.out.println("Main Menu"+entry.getKey());
					}
					frs.generateFacultyReport(completeScheduleHashMap);
				}
				
			}
		});
		mnNewMenu_3.add(mntmFacultyload);

		JMenuItem mntmDegreePlan = new JMenuItem("Degree Plan");
		mntmDegreePlan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					DegreePlanReport drs=new DegreePlanReport();
					drs.generateDegreeReport();
			}
		});
		mnNewMenu_3.add(mntmDegreePlan);
		
		JMenuItem mntmStudentReport = new JMenuItem("Student Report");
		mntmStudentReport.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new StudentReportDisplay();
			}
		});
		mnNewMenu_3.add(mntmStudentReport);

		JMenu mnNewMenu_4 = new JMenu("System");
		menuBar.add(mnNewMenu_4);

		JMenuItem mntmUsers = new JMenuItem("Users");
		mnNewMenu_4.add(mntmUsers);

		JMenuItem mntmUpdatePassword = new JMenuItem("Update Password");
		mntmUpdatePassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new UpdatePasswordScreen(completeScheduleHashMap);
			}
		});
		mnNewMenu_4.add(mntmUpdatePassword);

		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LoginScreen();
			}
		});
		mnNewMenu_4.add(mntmLogout);

		JLabel lblOklahomaChristianUniversity = new JLabel("Oklahoma Christian University");
		lblOklahomaChristianUniversity.setBounds(135, 121, 202, 33);
		getContentPane().add(lblOklahomaChristianUniversity);

	}
}
