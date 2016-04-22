package com.des.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DateFormatter;
import javax.swing.text.MaskFormatter;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.des.event.EventManager;
import com.des.util.CommonUtils;

public class AddCustomerUI extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2114330648233914398L;
	private JPanel				contentPane;
	private JTextField			txtFullName;
	private JFormattedTextField	txtfrmtdDob;
	private JTextField			txtLandmark;
	private JFormattedTextField			txtfrmtdPincode;
	private JFormattedTextField	txtfrmtdAnniversary;
	private JFormattedTextField			txtfrmtdContactNo;
	private JTextField			txtEmail;
	private JTextArea			txtAreaAddress;
	private JRadioButton		rdbtnSingle;
	private JRadioButton		rdbtnMarried;
	private JRadioButton		rdbtnMale;
	private JRadioButton		rdbtnFemale;
	private JPanel anniversaryPannel;
	private JButton btnSave;
	private JLabel lblAnniversary;
	private JLabel lblFullName;
	private JLabel lblDob;
	private JLabel lblGender;
	private JLabel lblAddress;
	private JLabel lblLandmark;
	private JLabel lblPincode;
	private JLabel lblContactNo;
	private JLabel lblEmail;
	private JLabel lblMaritialStatus;	
	private JTextField txtCity;
	private JLabel lblddMmm;
	private JLabel label;
	private JLabel lblCity;

	/**
	 * Create the frame.
	 */
	public AddCustomerUI()
	{		
		setTitle("Add Customer Detail");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setBounds(100, 100, 850, 550);
		setResizable(false);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));		
		setContentPane(contentPane);

		// set panel layout
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		// set anniversary pannel for custom display
		anniversaryPannel = new JPanel();
		anniversaryPannel.setBounds(393, 214, 372, 55);
		panel.add(anniversaryPannel);
		anniversaryPannel.setLayout(null);

		// default set anniversary pannel to false
		anniversaryPannel.setVisible(false);

		setLables(panel, anniversaryPannel);

		setTextFields(panel, anniversaryPannel);

		setRadioButton(panel, anniversaryPannel);

		setButton(panel, anniversaryPannel);		
				
		panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtFullName, txtfrmtdDob, rdbtnMale, rdbtnFemale, lblAnniversary, txtfrmtdAnniversary, anniversaryPannel, lblFullName, lblDob, lblGender, lblAddress, lblLandmark, lblPincode, lblContactNo, lblEmail, lblMaritialStatus, txtAreaAddress, txtLandmark, txtfrmtdPincode, txtfrmtdContactNo, txtEmail, rdbtnSingle, rdbtnMarried, btnSave}));
		
		HeaderPanelUI hpu = new HeaderPanelUI();
		contentPane.add(hpu, BorderLayout.NORTH);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e)
			{
				Frame[] f = getFrames();
				for(Frame frame : getFrames())
				{
					if(frame instanceof AddCustomerUI==false)
					{
						frame.dispose();
					}
				}
			}
		});
	}
	
	private void setButton(JPanel panel, JPanel anniversaryPannel)
	{
		
		btnSave = new JButton("Save");
		btnSave.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{					
					setDefaultButton();
				}
			}
		});

		// save button button onclick event
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event)
			{
				String gender;
				String maritialStatus;

				// gender
				if (rdbtnMale.isSelected())
				{
					gender = rdbtnMale.getText();
				}
				else if (rdbtnFemale.isSelected())
				{
					gender = rdbtnFemale.getText();
				}
				else
				{
					gender = null;
				}

				// maritial status
				if (rdbtnMarried.isSelected())
				{
					maritialStatus = rdbtnMarried.getText();
				}
				else if (rdbtnSingle.isSelected())
				{
					maritialStatus = rdbtnSingle.getText();
				}
				else
				{
					maritialStatus = null;
				}
							
				if("".equals(txtFullName.getText()) || "".equals(txtCity.getText()))
				{
					JOptionPane.showMessageDialog(null, "Full Name & City both are required field");
					txtFullName.requestFocus();
				}
				else
				{
					try
					{
						// String fullName, String address, String landMark, String
						// pinCode, String email, String contactNo, String gender,
						// String maritialStatus, String dob, String anniversary , String city
						EventManager.saveCustomer(txtFullName.getText(), txtAreaAddress.getText(), txtLandmark.getText(),txtfrmtdPincode.getText(), txtEmail.getText(), txtfrmtdContactNo.getText(), gender, maritialStatus,txtfrmtdDob.getText(), txtfrmtdAnniversary.getText(),txtCity.getText());
						JOptionPane.showMessageDialog(null, "Your Data saved successfully");
						setDefaultValues();	
					}
					catch(Exception e)
					{
						System.out.println(e);
						JOptionPane.showMessageDialog(null, "Database not available , Please contact System Admin");
					}
				}
			}
		});
		
		btnSave.setBounds(352, 397, 89, 23);
		panel.add(btnSave);		
	}

	private void setRadioButton(JPanel panel, JPanel anniversaryPannel)
	{
		// group radio buttons
		ButtonGroup genderGroup = new ButtonGroup();
		// group radio buttons
		ButtonGroup maritialStatusGroup = new ButtonGroup();

		rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setBounds(144, 93, 109, 23);
		// set default to true
		rdbtnMale.setSelected(true);
		panel.add(rdbtnMale);
		genderGroup.add(rdbtnMale);

		rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setBounds(144, 119, 109, 23);
		panel.add(rdbtnFemale);
		genderGroup.add(rdbtnFemale);

		// set anniversary panel false
		rdbtnSingle = new JRadioButton("Single");
		rdbtnSingle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event)
			{
				if (rdbtnSingle.isSelected())
				{
					txtfrmtdAnniversary.setValue(null);
					anniversaryPannel.setVisible(false);					
				}
			}
		});
		rdbtnSingle.setBounds(531, 141, 109, 23);
		// set default to true
		rdbtnSingle.setSelected(true);
		panel.add(rdbtnSingle);
		maritialStatusGroup.add(rdbtnSingle);

		// set anniversary panel true
		rdbtnMarried = new JRadioButton("Married");
		rdbtnMarried.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event)
			{
				if (rdbtnMarried.isSelected())
				{
					anniversaryPannel.setVisible(true);
					txtfrmtdAnniversary.setValue(null);
				}
			}
		});
		rdbtnMarried.setBounds(531, 167, 109, 23);
		panel.add(rdbtnMarried);
		maritialStatusGroup.add(rdbtnMarried);

	}

	private void setTextFields(JPanel panel, JPanel anniversaryPannel)
	{
		// set date format
		DateFormat format = new SimpleDateFormat("dd-MMMM-yyyy");
		DateFormatter df = new DateFormatter(format);

		// set mask formatter
		MaskFormatter maskContactNo = null;
		MaskFormatter maskPinCode = null;
		//
		// Create a MaskFormatter for accepting phone number, the # symbol
		// accept
		// only a number. We can also set the empty value with a place holder
		// character.
		//
		try
		{
			maskContactNo = new MaskFormatter("##########");
			//maskContactNo.setPlaceholderCharacter('_');
			
			maskPinCode = new MaskFormatter("######");
			//maskPinCode.setPlaceholderCharacter('_');
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		
		txtFullName = new JTextField();
		txtFullName.setBounds(134, 35, 205, 20);
		panel.add(txtFullName);
		txtFullName.setColumns(10);
		txtFullName.requestFocus();

		txtfrmtdDob = new JFormattedTextField(df);
		txtfrmtdDob.setToolTipText("date in dd-mmm-yyyy format");
		txtfrmtdDob.setBounds(134, 66, 102, 20);
		panel.add(txtfrmtdDob);
		txtfrmtdDob.setColumns(10);
		txtfrmtdDob.setValue(null);

		txtfrmtdAnniversary = new JFormattedTextField(df);
		txtfrmtdAnniversary.setToolTipText("date in dd-mmm-yyyy format");
		txtfrmtdAnniversary.setBounds(112, 19, 107, 20);
		anniversaryPannel.add(txtfrmtdAnniversary);
		txtfrmtdAnniversary.setColumns(10);
		txtfrmtdAnniversary.setValue(null);

		txtAreaAddress = new JTextArea();
		txtAreaAddress.setColumns(10);
		txtAreaAddress.setRows(10);
		txtAreaAddress.setBounds(134, 153, 205, 100);
		panel.add(txtAreaAddress);

		txtLandmark = new JTextField();
		txtLandmark.setBounds(134, 278, 215, 20);
		panel.add(txtLandmark);
		txtLandmark.setColumns(10);

		txtfrmtdPincode = new JFormattedTextField(maskPinCode);
		txtfrmtdPincode.setBounds(134, 343, 63, 20);
		panel.add(txtfrmtdPincode);
		txtfrmtdPincode.setColumns(6);

		txtfrmtdContactNo = new JFormattedTextField(maskContactNo);
		txtfrmtdContactNo.setToolTipText("10 digit mobile number");
		txtfrmtdContactNo.setColumns(10);
		txtfrmtdContactNo.setBounds(504, 41, 102, 20);
		panel.add(txtfrmtdContactNo);

		txtEmail = new JTextField();
		txtEmail.setToolTipText("Email address should be in proper format");
		txtEmail.setColumns(10);
		txtEmail.setBounds(504, 95, 205, 20);
		panel.add(txtEmail);
		
		txtCity = new JTextField();
		txtCity.setBounds(134, 312, 205, 20);
		panel.add(txtCity);
		txtCity.setColumns(10);
	}

	private void setLables(JPanel panel, JPanel anniversaryPannel)
	{
		lblFullName = new JLabel("Full Name");
		lblFullName.setBounds(26, 38, 98, 20);
		panel.add(lblFullName);

		lblDob = new JLabel("DOB");
		lblDob.setBounds(26, 69, 98, 17);
		panel.add(lblDob);

		lblGender = new JLabel("Gender");
		lblGender.setBounds(26, 109, 98, 20);
		panel.add(lblGender);

		lblAddress = new JLabel("Address");
		lblAddress.setBounds(26, 157, 98, 20);
		panel.add(lblAddress);

		lblLandmark = new JLabel("Landmark");
		lblLandmark.setBounds(26, 281, 98, 20);
		panel.add(lblLandmark);

		lblPincode = new JLabel("Pincode");
		lblPincode.setBounds(26, 346, 98, 20);
		panel.add(lblPincode);

		lblContactNo = new JLabel("Contact No");
		lblContactNo.setBounds(393, 43, 101, 17);
		panel.add(lblContactNo);

		lblEmail = new JLabel("Email Id");
		lblEmail.setBounds(393, 92, 101, 27);
		panel.add(lblEmail);

		lblAnniversary = new JLabel("Anniversary");
		lblAnniversary.setBounds(10, 14, 92, 30);
		anniversaryPannel.add(lblAnniversary);

		lblMaritialStatus = new JLabel("Maritial Status");
		lblMaritialStatus.setBounds(393, 143, 101, 37);
		panel.add(lblMaritialStatus);
		
		label = new JLabel("(dd - mmm - yyyy)");
		label.setBounds(236, 14, 114, 31);
		anniversaryPannel.add(label);
		
		lblCity = new JLabel("City");
		lblCity.setBounds(26, 315, 98, 17);
		panel.add(lblCity);
		
		lblddMmm = new JLabel("(dd - mmm - yyyy)");
		lblddMmm.setBounds(246, 62, 114, 31);
		panel.add(lblddMmm);
	}
	
	public void setDefaultValues()
	{
		txtAreaAddress.setText("");
		txtEmail.setText("");
		txtfrmtdAnniversary.setValue(null);
		txtfrmtdDob.setValue(null);
		txtfrmtdContactNo.setText("");
		txtfrmtdPincode.setText("");
		txtFullName.setText("");
		txtLandmark.setText("");
		txtCity.setText("");
		rdbtnMale.setSelected(true);
		rdbtnFemale.setSelected(false);
		rdbtnMarried.setSelected(false);
		rdbtnSingle.setSelected(true);
		anniversaryPannel.setVisible(false);
		txtFullName.requestFocus();
	}
	
	public void setDefaultButton()
	{
		super.getRootPane().setDefaultButton(btnSave);
	}
}