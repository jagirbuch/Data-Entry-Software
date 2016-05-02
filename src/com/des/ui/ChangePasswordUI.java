package com.des.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.des.event.EventManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChangePasswordUI extends JFrame
{

	private JPanel contentPane;
	private JPasswordField pwdCurrentpassword;
	private JPasswordField pwdNewPassword;
	private JPasswordField pwdRetypePassword;

	/**
	 * Create the frame.
	 */
	public ChangePasswordUI()
	{
		setTitle("Change Password");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		/*setBounds(100, 100, 850, 550);
		setResizable(false);
		setLocationRelativeTo(null);*/
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		setSize(screenSize);
		setVisible(true);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		setLables(panel);	
		setTextBoxes(panel);
		setButtons(panel);		
		
		HeaderPanelUI hpu = new HeaderPanelUI();
		contentPane.add(hpu, BorderLayout.NORTH);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e)
			{
				for(Frame frame : getFrames())
				{
					if(frame instanceof ChangePasswordUI==false)
					{
						frame.dispose();
					}
				}
			}
		});
		
	}

	private void setButtons(JPanel panel)
	{
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if("".equals(pwdCurrentpassword.getText()) || "".equals(pwdNewPassword.getText()))
				{
					JOptionPane.showMessageDialog(null, "Please enter required field");
				}
				else if(!pwdNewPassword.getText().equals(pwdRetypePassword.getText()))
				{
					JOptionPane.showMessageDialog(null, "Password & Confirm Password does not match");
				}
				else
				{
					try
					{
						if(EventManager.authenticateUser(HeaderPanelUI.getUserId(), pwdCurrentpassword.getText()))
						{
							EventManager.changePassword(HeaderPanelUI.getUserId(), pwdNewPassword.getText());
							JOptionPane.showMessageDialog(null, "Password change successfully");
							close();
							new DashboardUI().setVisible(true);
						}											
						else					
						{
							JOptionPane.showMessageDialog(null, "Your current password does not match with system");
							pwdCurrentpassword.requestFocus();
						}
					}
					catch (HeadlessException e1)
					{
						JOptionPane.showMessageDialog(null, "Database not available , Please contact System Admin");
					}
					catch (ClassNotFoundException e1)
					{
						JOptionPane.showMessageDialog(null, "Database not available , Please contact System Admin");
					}
					catch (SQLException e1)
					{
						JOptionPane.showMessageDialog(null, "Database not available , Please contact System Admin");
					}
				}
			}
		});
		btnChangePassword.setBounds(158, 224, 160, 35);
		panel.add(btnChangePassword);
		
	}

	private void setTextBoxes(JPanel panel)
	{
		pwdCurrentpassword = new JPasswordField();
		pwdCurrentpassword.setBounds(253, 55, 134, 20);
		panel.add(pwdCurrentpassword);
		
		pwdNewPassword = new JPasswordField();
		pwdNewPassword.setBounds(253, 100, 134, 20);
		panel.add(pwdNewPassword);
		
		pwdRetypePassword = new JPasswordField();
		pwdRetypePassword.setBounds(253, 151, 134, 20);
		panel.add(pwdRetypePassword);
		
	}

	private void setLables(JPanel panel)
	{
		JLabel lblCurrentPassword = new JLabel("Current Password");
		lblCurrentPassword.setBounds(45, 55, 180, 28);
		panel.add(lblCurrentPassword);
		
		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setBounds(45, 100, 180, 28);
		panel.add(lblNewPassword);
		
		JLabel lblRetypePassword = new JLabel("Retype Password");
		lblRetypePassword.setBounds(45, 151, 180, 28);
		panel.add(lblRetypePassword);
		
	}

	public void close()
	{
		super.dispose();
	}
}