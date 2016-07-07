package com.univ.screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.univ.beans.Schedule;
import com.univ.service.LoginService;
import javax.swing.JPasswordField;

public class UpdatePasswordScreen extends JFrame {


	private static final long serialVersionUID = 3748773685022232014L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Create the frame.
	 */
	public UpdatePasswordScreen(Map<String,List<Schedule>> completeScheduleHashMap) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnUpdatePassword = new JButton("Update Password");
		
		JLabel lblUsername = new JLabel("UserName");
		
		JLabel lblPassword = new JLabel("Password");
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		
		passwordField_1 = new JPasswordField();
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(97)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblPassword)
								.addComponent(lblUsername)
								.addComponent(lblConfirmPassword))
							.addGap(72)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField)
								.addComponent(passwordField)
								.addComponent(passwordField_1)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(141)
							.addComponent(btnUpdatePassword)))
					.addContainerGap(83, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(48)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblUsername)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblConfirmPassword)
						.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(39)
					.addComponent(btnUpdatePassword)
					.addContainerGap(45, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		btnUpdatePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(" User and Passwd 1 : "+textField.getText() +" &" +passwordField.getPassword().toString());
				
				if(passwordField.getText().equals(passwordField_1.getText()) && !(passwordField.getText().isEmpty()))
				{
					LoginService loginService = new LoginService();
					
					System.out.println(" User and Passwd 2 : "+textField.getText() +" &" +passwordField.getPassword().toString());
					boolean passwdUpdatedStatus = loginService.updatePassword(textField.getText(), passwordField.getText());
					
					if(passwdUpdatedStatus)
						JOptionPane.showMessageDialog(null, "Password Updated...");
					else
						JOptionPane.showMessageDialog(null, "UserName not correct...");
				}
				dispose();
			}
		
		});
	}
}
