package com.des.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
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
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateLoginUserUI extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2081892232435887126L;
	private JPanel contentPane;
	private JTextField txtUserId;
	private JPasswordField passwordField;
	private JPasswordField cnfrmPasswordField;
	private JComboBox<String> cmbRole;

	/**
	 * Create the frame.
	 */
	public CreateLoginUserUI()
	{
		setTitle("Create Login User");
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
					if(frame instanceof CreateLoginUserUI==false)
					{
						frame.dispose();
					}
				}
			}
		});
	}
	
	private void setButtons(JPanel panel)
	{
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if("".equals(txtUserId.getText()) || "".equals(passwordField.getText()))
				{
					JOptionPane.showMessageDialog(null, "User Id & Password both are required field");					
				}
				else if(!passwordField.getText().equals(cnfrmPasswordField.getText()))
				{
					JOptionPane.showMessageDialog(null, "Password & Confirm Password does not match");
				}
				else
				{
					try
					{
						if(!EventManager.isUserExists(txtUserId.getText()))
						{
							EventManager.saveUser(txtUserId.getText(), passwordField.getText(), cmbRole.getSelectedItem().toString());
							JOptionPane.showMessageDialog(null, "User created successfully");
							setDefaults();
						}
						else
						{
							JOptionPane.showMessageDialog(null, "User already exists");
							setDefaults();
						}
						
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
		btnSave.setBounds(206, 283, 89, 23);
		panel.add(btnSave);
		
	}

	private void setTextBoxes(JPanel panel)
	{
		txtUserId = new JTextField();
		txtUserId.setBounds(237, 44, 137, 20);
		panel.add(txtUserId);
		txtUserId.setColumns(10);
		txtUserId.requestFocus();
		
		passwordField = new JPasswordField();
		passwordField.setBounds(240, 90, 134, 20);
		panel.add(passwordField);
		
		cnfrmPasswordField = new JPasswordField();
		cnfrmPasswordField.setBounds(240, 140, 134, 20);
		panel.add(cnfrmPasswordField);
		
		cmbRole = new JComboBox<String>();
		cmbRole.setModel(new DefaultComboBoxModel<String>(new String[] {"Admin", "User"}));
		cmbRole.setBounds(242, 192, 132, 20);
		panel.add(cmbRole);
	}

	public void setLables(JPanel panel)
	{
		JLabel lblUserId = new JLabel("User Id");
		lblUserId.setBounds(35, 44, 195, 24);
		panel.add(lblUserId);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(35, 90, 195, 24);
		panel.add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setBounds(35, 140, 195, 24);
		panel.add(lblConfirmPassword);
		
		JLabel lblRole = new JLabel("Role");
		lblRole.setBounds(35, 194, 195, 24);
		panel.add(lblRole);
	}
	
	public void setDefaults()
	{
		txtUserId.setText("");
		passwordField.setText("");
		cnfrmPasswordField.setText("");
		txtUserId.requestFocus();
		cmbRole.setSelectedIndex(0);
	}
	
	public void close()
	{
		super.dispose();
	}
}
