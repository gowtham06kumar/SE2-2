package com.univ.screens.maintanance;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

public class DegreePlanReportDisplayScreen extends JFrame{

	public List<Schedule> scheduleList;
	private static final long serialVersionUID = 4406724596143778906L;
	private JPanel contentPane;
	private JTable table_1;
	public DegreePlanReportDisplayScreen(){}
	public DegreePlanReportDisplayScreen(Map<String,Integer> hm){
		setVisible(true);
		setLocation(400,250);
	
		this.scheduleList = scheduleList;
		
		DefaultTableModel dtm = new DefaultTableModel();
		
		dtm.addColumn("Degeree Name");
		dtm.addColumn("Student Load");
		
		
		for (Entry<String, Integer> entry : hm.entrySet())
		{
			//System.out.println(sch.toString());
			dtm.addRow(new Object[]{entry.getKey(),entry.getValue()});
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
