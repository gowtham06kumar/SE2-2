package com.univ.screens.maintanance;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.univ.beans.Faculty;
import com.univ.importdata.FacultyDM;
import com.univ.screens.MenuScreen;

public class FacultyMaintScreen extends JFrame {

	List<Faculty> facultyData;

	private static final long serialVersionUID = -7453617270157980163L;

	private JPanel contentPane;
	private JTable table_1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;

	public FacultyMaintScreen() {
	}

	public FacultyMaintScreen(List<Faculty> facData) {

		setVisible(true);
		setLocation(400, 250);

		facultyData = facData;

		DefaultTableModel dtm = new DefaultTableModel();

		dtm.addColumn("LastName");
		dtm.addColumn("FirstName");
		dtm.addColumn("GradSchool");
		dtm.addColumn("Degree");
		dtm.addColumn("Title");
		dtm.addColumn("DaysToTeach");
		dtm.addColumn("MaxLoadFall");
		dtm.addColumn("MaxLoadSpring");
		dtm.addColumn("MaxLoadSummer");

		for (Faculty faculty : facultyData) {
			dtm.addRow(new Object[] { faculty.getLastName(),
					faculty.getFirstName(), faculty.getGradSchool(),
					faculty.getDegree(), faculty.getTitle(),
					faculty.getDaysToTeach(), faculty.getMaxLoadFall(),
					faculty.getMaxLoadSpring(), faculty.getMaxLoadSummer() });
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 459, 528);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		table_1 = new JTable();
		table_1.setModel(dtm);
		JScrollPane scrollPane = new JScrollPane(table_1);
		contentPane.add(scrollPane);
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("LastName");
		lblNewLabel.setBounds(22, 8, 62, 14);
		panel_1.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(116, 5, 86, 20);
		panel_1.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("FirstName");
		lblNewLabel_1.setBounds(22, 33, 63, 14);
		panel_1.add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.setBounds(116, 30, 86, 20);
		panel_1.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblDescription = new JLabel("GradSchool");
		lblDescription.setBounds(22, 58, 62, 14);
		panel_1.add(lblDescription);

		textField_2 = new JTextField();
		textField_2.setBounds(116, 55, 86, 20);
		panel_1.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblCourseHours = new JLabel("Degree");
		lblCourseHours.setBounds(22, 83, 68, 14);
		panel_1.add(lblCourseHours);

		textField_3 = new JTextField();
		textField_3.setBounds(116, 80, 86, 20);
		panel_1.add(textField_3);
		textField_3.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Title");
		lblNewLabel_2.setBounds(22, 108, 84, 14);
		panel_1.add(lblNewLabel_2);

		textField_4 = new JTextField();
		textField_4.setBounds(116, 105, 86, 20);
		panel_1.add(textField_4);
		textField_4.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("DaysToTeach");
		lblNewLabel_3.setBounds(22, 133, 84, 14);
		panel_1.add(lblNewLabel_3);

		textField_5 = new JTextField();
		textField_5.setBounds(116, 130, 86, 20);
		panel_1.add(textField_5);
		textField_5.setColumns(10);

		JLabel lblMaxloadfall = new JLabel("MaxLoadFall");
		lblMaxloadfall.setBounds(22, 158, 62, 14);
		panel_1.add(lblMaxloadfall);

		textField_6 = new JTextField();
		textField_6.setBounds(116, 155, 86, 20);
		panel_1.add(textField_6);
		textField_6.setColumns(10);

		JLabel lblMaxloadspring = new JLabel("MaxLoadSpring");
		lblMaxloadspring.setBounds(22, 183, 84, 14);
		panel_1.add(lblMaxloadspring);

		textField_7 = new JTextField();
		textField_7.setBounds(116, 180, 86, 20);
		panel_1.add(textField_7);
		textField_7.setColumns(10);

		JLabel lblMaxloadsummer = new JLabel("MaxLoadSummer");
		lblMaxloadsummer.setBounds(22, 208, 84, 14);
		panel_1.add(lblMaxloadsummer);

		textField_8 = new JTextField();
		textField_8.setBounds(116, 205, 86, 20);
		panel_1.add(textField_8);
		textField_8.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2);

		JButton btnAdd = new JButton("Add");
		panel_2.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model = (DefaultTableModel) table_1
						.getModel();
				if (!textField.getText().trim().equals("")) {
					model.addRow(new Object[] { textField.getText(),
							textField_1.getText(), textField_2.getText(),
							textField_3.getText(), textField_4.getText(),
							textField_5.getText(), textField_6.getText(),
							textField_7.getText(), textField_8.getText() });
					MenuScreen.facultyData = FacultyDM.getListfromTable(dtm);
				} else
					JOptionPane.showMessageDialog(null,
							"Please Enter Section Number");

			}
		});

		JButton btnEdit = new JButton("Edit");
		panel_2.add(btnEdit);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model = (DefaultTableModel) table_1
						.getModel();
				if (table_1.getSelectedRow() == -1) {
					if (table_1.getRowCount() == 0)
						JOptionPane.showMessageDialog(null, "Table is empty");
					else
						JOptionPane.showMessageDialog(null,
								"Please Select row to update");
				} else {
					model.setValueAt(textField.getText(),
							table_1.getSelectedRow(), 0);
					model.setValueAt(textField_1.getText(),
							table_1.getSelectedRow(), 1);
					model.setValueAt(textField_2.getText(),
							table_1.getSelectedRow(), 2);
					model.setValueAt(textField_3.getText(),
							table_1.getSelectedRow(), 3);
					model.setValueAt(textField_4.getText(),
							table_1.getSelectedRow(), 4);
					model.setValueAt(textField_5.getText(),
							table_1.getSelectedRow(), 5);
					model.setValueAt(textField_6.getText(),
							table_1.getSelectedRow(), 6);
					model.setValueAt(textField_7.getText(),
							table_1.getSelectedRow(), 7);
					model.setValueAt(textField_8.getText(),
							table_1.getSelectedRow(), 8);
					MenuScreen.facultyData = FacultyDM.getListfromTable(dtm);
				}

			}
		});

		JButton btnDelete = new JButton("Delete");
		panel_2.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table_1
						.getModel();
				if (table_1.getSelectedRow() == -1) {
					if (table_1.getRowCount() == 0)
						JOptionPane.showMessageDialog(null, "Table is empty");
					else
						JOptionPane.showMessageDialog(null,
								"Please Select row to delete");
				} else {
					model.removeRow(table_1.getSelectedRow());
					MenuScreen.facultyData = FacultyDM.getListfromTable(dtm);
				}
			}
		});

		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					DefaultTableModel model = (DefaultTableModel) table_1
							.getModel();

					textField.setText(model.getValueAt(
							table_1.getSelectedRow(), 0).toString());
					textField_1.setText(model.getValueAt(
							table_1.getSelectedRow(), 1).toString());
					textField_2.setText(model.getValueAt(
							table_1.getSelectedRow(), 2).toString());
					textField_3.setText(model.getValueAt(
							table_1.getSelectedRow(), 3).toString());
					textField_4.setText(model.getValueAt(
							table_1.getSelectedRow(), 4).toString());
					textField_5.setText(model.getValueAt(
							table_1.getSelectedRow(), 5).toString());
					textField_6.setText(model.getValueAt(
							table_1.getSelectedRow(), 6).toString());
					textField_7.setText(model.getValueAt(
							table_1.getSelectedRow(), 7).toString());
					textField_8.setText(model.getValueAt(
							table_1.getSelectedRow(), 8).toString());
				} catch (ArrayIndexOutOfBoundsException ae) {
					ae.printStackTrace();
				}

			}
		});
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

	}
}
