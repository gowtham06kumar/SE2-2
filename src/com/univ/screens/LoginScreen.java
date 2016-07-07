package com.univ.screens;

import java.awt.event.ActionListener;

import com.univ.service.LoginService;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class LoginScreen extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5488940067428900971L;

	JButton SUBMIT;
	JPanel panel1, panel2, panel3, panel;
	JLabel userName, password;
	JTextField userName_text, pwd_text;
	private JPanel panel_1;
	private JLabel lblNewLabel;

	public LoginScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(400, 400);
		setLocation(400, 250);

		userName = new JLabel();
		userName.setText("Username:");
		userName_text = new JTextField(15);

		panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 55, 50));
		panel1.add(userName);
		panel1.add(userName_text);

		password = new JLabel();
		password.setText("Password:");
		pwd_text = new JPasswordField(15);

		panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 55, 50));
		panel2.add(password);
		panel2.add(pwd_text);

		SUBMIT = new JButton("SUBMIT");

		panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 55, 50));
		panel3.add(SUBMIT);

		panel = new JPanel(new GridLayout(4, 1));
		
		panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setVgap(35);
		panel.add(panel_1);
		
		lblNewLabel = new JLabel("Welcome To ESC Grad School");
		panel_1.add(lblNewLabel);
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);

		getContentPane().add(panel, BorderLayout.CENTER);
		SUBMIT.addActionListener(this);
		setTitle("ECSGradSchool");
	}

	public void actionPerformed(ActionEvent ae) {
		String value1 = userName_text.getText();
		String value2 = pwd_text.getText();
		LoginService service = new LoginService();
		if (service.validate(value1, value2)) {
			dispose();
			new MenuScreen();
		} else {
			System.out.println("enter the valid username and password");
			JOptionPane.showMessageDialog(this, "Incorrect login or password",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
