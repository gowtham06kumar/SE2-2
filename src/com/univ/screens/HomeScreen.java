package com.univ.screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class HomeScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the frame.
	 */
	public HomeScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LoginScreen();
			}
		});
		btnNewButton.setBounds(147, 146, 155, 38);
		getContentPane().add(btnNewButton);
		JLabel lblWelcomeToEsc = new JLabel("Welcome To ESC Grad School");
		lblWelcomeToEsc.setBounds(147, 71, 231, 14);
		getContentPane().add(lblWelcomeToEsc);

	}
}
