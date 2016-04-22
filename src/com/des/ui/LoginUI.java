package com.des.ui;

import java.awt.BorderLayout;


import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import com.des.event.EventManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class LoginUI extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1238132644766693103L;
	private JPanel		contentPane;
	private JTextField	txtUserName;
	private final String USERNAME = "admin";
	private final String PASSWORD = "admin";
	private JPasswordField passwordField;
	private JButton btnLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run()
			{
				try
				{
					LoginUI frame = new LoginUI();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginUI()
	{
		setTitle("Harshvardhan Group");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 450, 250);
		setResizable(false);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		setLabels(panel);
		setTextBoxes(panel);
		setButtons(panel);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e)
			{
				for(Frame frame : getFrames())
				{
					if(frame instanceof LoginUI==false)
					{
						frame.dispose();
					}
				}
			}
		});
	}
	
	private void setButtons(JPanel panel)
	{
		btnLogin = new JButton("Login");
		btnLogin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{					
					setDefaultButton();
				}
			}

		});
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				String userName = txtUserName.getText();
				String password = passwordField.getText();
				
				try
				{
					if(EventManager.authenticateUser(userName, password))
					{					
						close();
						HeaderPanelUI.setUserId(userName);
						new DashboardUI().setVisible(true);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Incorrect Login Credential , Please Try again");
						txtUserName.setText("");
						passwordField.setText("");
						txtUserName.requestFocus();
					}
				}
				catch (HeadlessException e)
				{
					JOptionPane.showMessageDialog(null, "Database not available , Please contact System Admin");
					txtUserName.requestFocus();
				}
				catch (ClassNotFoundException e)
				{
					JOptionPane.showMessageDialog(null, "Database not available , Please contact System Admin");
					txtUserName.requestFocus();
				}
				catch (SQLException e)
				{
					JOptionPane.showMessageDialog(null, "Database not available , Please contact System Admin");
					txtUserName.requestFocus();
				}
			}
		});
		btnLogin.setBounds(177, 149, 89, 23);
		panel.add(btnLogin);
	}

	private void setTextBoxes(JPanel panel)
	{
		txtUserName = new JTextField();
		txtUserName.setBounds(177, 49, 173, 20);
		panel.add(txtUserName);
		txtUserName.setColumns(10);
		txtUserName.requestFocus();
		
		passwordField = new JPasswordField();
		passwordField.setBounds(177, 91, 173, 20);
		panel.add(passwordField);
		
	}

	private void setLabels(JPanel panel)
	{
		JLabel lblUserName = new JLabel("Username:");
		lblUserName.setBounds(63, 45, 76, 28);
		panel.add(lblUserName);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(63, 94, 76, 14);
		panel.add(lblPassword);
	}

	public void close()
	{
		super.setVisible(false);
	}
	
	public void setDefaultButton()
	{
		super.getRootPane().setDefaultButton(btnLogin);
	}
}
