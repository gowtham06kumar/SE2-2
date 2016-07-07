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

import com.univ.beans.University;
import com.univ.importdata.UniversityDM;
import com.univ.screens.MenuScreen;

public class UniversityMaintScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	List<University> universityList;
	private JTable table_1;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the frame.
	 */
	public UniversityMaintScreen() {
	}

	public UniversityMaintScreen(List<University> univList) {
		setTitle("UniversityMaintainenceScreen");
		setVisible(true);
		setLocation(400, 250);

		universityList = univList;

		DefaultTableModel dtm = new DefaultTableModel();

		dtm.addColumn("Name");
		dtm.addColumn("Abbrevation");

		for (University university : universityList) {
			System.out.println(university.toString());
			dtm.addRow(new Object[] { university.getName(),
					university.getAbbrevation() });
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
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(22, 8, 62, 14);
		panel_1.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(116, 5, 86, 20);
		panel_1.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Abbrevation");
		lblNewLabel_1.setBounds(22, 33, 63, 14);
		panel_1.add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.setBounds(116, 30, 86, 20);
		panel_1.add(textField_1);
		textField_1.setColumns(10);

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
							textField_1.getText() });

					MenuScreen.universityList = UniversityDM
							.getListfromTable(dtm);
				} else {
					JOptionPane.showMessageDialog(null,
							"Please Enter University Name");
				}

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
					MenuScreen.universityList = UniversityDM
							.getListfromTable(dtm);
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
					MenuScreen.universityList = UniversityDM
							.getListfromTable(dtm);
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
				} catch (ArrayIndexOutOfBoundsException ae) {
					ae.printStackTrace();
				}

			}
		});
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	}
}
