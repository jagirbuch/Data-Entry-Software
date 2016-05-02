package com.des.ui;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;
import javax.swing.text.MaskFormatter;

import com.des.event.EventManager;
import com.des.util.CommonUtils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

public class UpdateCustomerUI extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6925533628394125220L;
	private JPanel				contentPane;
	private JTextField			txtFullName;
	private JFormattedTextField	txtfrmtdDob;
	private JTextField			txtLandmark;
	private JFormattedTextField	txtfrmtdPincode;
	private JFormattedTextField	txtfrmtdAnniversary;
	private JFormattedTextField	txtfrmtdContactNo;
	private JTextField			txtEmail;
	private JTextArea			txtAreaAddress;
	private JRadioButton		rdbtnSingle;
	private JRadioButton		rdbtnMarried;
	private JRadioButton		rdbtnMale;
	private JRadioButton		rdbtnFemale;
	private JPanel				anniversaryPannel;
	private JPanel				panel;
	private Integer				cId;
	private JButton btnUpdate;
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
	public UpdateCustomerUI()
	{
		setTitle("Customer Update");
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

		// set panel layout
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		// set anniversary pannel for custom display
		anniversaryPannel = new JPanel();
		anniversaryPannel.setBounds(480, 211, 445, 62);
		panel.add(anniversaryPannel);
		anniversaryPannel.setLayout(null);
		
		HeaderPanelUI hpu = new HeaderPanelUI();
		contentPane.add(hpu, BorderLayout.NORTH);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e)
			{
				Frame[] f = getFrames();
				for(Frame frame : getFrames())
				{
					if(frame instanceof UpdateCustomerUI==false)
					{
						frame.dispose();
					}
				}
			}
		});

		// default set anniversary pannel to false
		anniversaryPannel.setVisible(false);

		setLables(panel, anniversaryPannel);

		setTextFields(panel, anniversaryPannel);

		setRadioButton(panel, anniversaryPannel);

		setButton(panel, anniversaryPannel);
		panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtFullName, txtfrmtdDob, rdbtnMale, rdbtnFemale, txtAreaAddress, txtLandmark, txtfrmtdPincode, txtfrmtdContactNo, txtEmail, rdbtnSingle, anniversaryPannel, rdbtnMarried, txtfrmtdAnniversary, btnUpdate, lblAnniversary, lblFullName, lblDob, lblGender, lblAddress, lblLandmark, lblPincode, lblContactNo, lblEmail, lblMaritialStatus}));
	}

	private void setButton(JPanel panel, JPanel anniversaryPannel)
	{
		btnUpdate = new JButton("Update");
		btnUpdate.addKeyListener(new KeyAdapter() {
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
		btnUpdate.addActionListener(new ActionListener() {
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
					// Integer id , String fullName, String address, String
					// landMark, String
					// pinCode, String email, String contactNo, String gender,
					// String maritialStatus, String dob, String anniversary , String city
					try
					{
						EventManager.updateCustomer(cId, txtFullName.getText(), txtAreaAddress.getText(),
								txtLandmark.getText(), txtfrmtdPincode.getText(), txtEmail.getText(),
								txtfrmtdContactNo.getText(), gender, maritialStatus, txtfrmtdDob.getText(),
								txtfrmtdAnniversary.getText(),txtCity.getText());
						JOptionPane.showMessageDialog(null, "Your Data updated successfully");
						closeFrame();
						new ManageCustomerUI().setVisible(true);
					}
					catch (ClassNotFoundException e)
					{
						JOptionPane.showMessageDialog(null, "Database not available , Please contact System Admin");
					}
					catch (SQLException e)
					{
						JOptionPane.showMessageDialog(null, "Database not available , Please contact System Admin");
					}
				}
			}
		});

		btnUpdate.setBounds(524, 394, 89, 23);
		panel.add(btnUpdate);

	}

	private void closeFrame()
	{
		super.setVisible(false);
	}

	private void setRadioButton(JPanel panel, JPanel anniversaryPannel)
	{
		// group radio buttons
		ButtonGroup genderGroup = new ButtonGroup();
		// group radio buttons
		ButtonGroup maritialStatusGroup = new ButtonGroup();

		rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setBounds(215, 96, 109, 23);
		// set default to true
		rdbtnMale.setSelected(true);
		panel.add(rdbtnMale);
		genderGroup.add(rdbtnMale);

		rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setBounds(215, 122, 109, 23);
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
		rdbtnSingle.setBounds(703, 138, 109, 23);
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
		rdbtnMarried.setBounds(703, 164, 109, 23);
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
		txtFullName.setBounds(205, 38, 205, 20);
		panel.add(txtFullName);
		txtFullName.setColumns(10);
		txtFullName.requestFocus();

		txtfrmtdDob = new JFormattedTextField(df);
		txtfrmtdDob.setToolTipText("date in dd-mmm-yyyy format");
		txtfrmtdDob.setBounds(205, 69, 102, 20);
		panel.add(txtfrmtdDob);
		txtfrmtdDob.setColumns(10);
		txtfrmtdDob.setValue(null);

		txtfrmtdAnniversary = new JFormattedTextField(df);
		txtfrmtdAnniversary.setToolTipText("date in dd-mmm-yyyy format");
		txtfrmtdAnniversary.setBounds(197, 18, 107, 20);
		anniversaryPannel.add(txtfrmtdAnniversary);
		txtfrmtdAnniversary.setColumns(10);
		txtfrmtdAnniversary.setValue(null);

		txtAreaAddress = new JTextArea();
		txtAreaAddress.setColumns(10);
		txtAreaAddress.setRows(10);
		txtAreaAddress.setBounds(205, 156, 205, 100);
		panel.add(txtAreaAddress);

		txtLandmark = new JTextField();
		txtLandmark.setBounds(205, 281, 215, 20);
		panel.add(txtLandmark);
		txtLandmark.setColumns(10);

		txtfrmtdPincode = new JFormattedTextField(maskPinCode);
		txtfrmtdPincode.setBounds(205, 346, 63, 20);
		panel.add(txtfrmtdPincode);
		txtfrmtdPincode.setColumns(6);

		txtfrmtdContactNo = new JFormattedTextField(maskContactNo);
		txtfrmtdContactNo.setToolTipText("10 digit mobile number");
		txtfrmtdContactNo.setColumns(10);
		txtfrmtdContactNo.setBounds(676, 38, 102, 20);
		panel.add(txtfrmtdContactNo);

		txtEmail = new JTextField();
		txtEmail.setToolTipText("Email address should be in proper format");
		txtEmail.setColumns(10);
		txtEmail.setBounds(676, 92, 205, 20);
		panel.add(txtEmail);
		
		txtCity = new JTextField();
		txtCity.setBounds(205, 315, 205, 20);
		panel.add(txtCity);
		txtCity.setColumns(10);
	}

	private void setLables(JPanel panel, JPanel anniversaryPannel)
	{
		lblFullName = new JLabel("Full Name");
		lblFullName.setBounds(26, 38, 137, 20);
		panel.add(lblFullName);

		lblDob = new JLabel("DOB");
		lblDob.setBounds(26, 69, 137, 17);
		panel.add(lblDob);

		lblGender = new JLabel("Gender");
		lblGender.setBounds(26, 109, 137, 20);
		panel.add(lblGender);

		lblAddress = new JLabel("Address");
		lblAddress.setBounds(26, 157, 137, 20);
		panel.add(lblAddress);

		lblLandmark = new JLabel("Landmark");
		lblLandmark.setBounds(26, 281, 137, 20);
		panel.add(lblLandmark);

		lblPincode = new JLabel("Pincode");
		lblPincode.setBounds(26, 346, 137, 20);
		panel.add(lblPincode);

		lblContactNo = new JLabel("Contact No");
		lblContactNo.setBounds(480, 40, 445, 17);
		panel.add(lblContactNo);

		lblEmail = new JLabel("Email Id");
		lblEmail.setBounds(480, 89, 445, 27);
		panel.add(lblEmail);

		lblAnniversary = new JLabel("Anniversary");
		lblAnniversary.setBounds(10, 14, 92, 30);
		anniversaryPannel.add(lblAnniversary);

		lblMaritialStatus = new JLabel("Maritial Status");
		lblMaritialStatus.setBounds(480, 140, 445, 37);
		panel.add(lblMaritialStatus);
		
		label = new JLabel("(dd - mmm - yyyy)");
		label.setBounds(321, 13, 114, 31);
		anniversaryPannel.add(label);
		
		lblCity = new JLabel("City");
		lblCity.setBounds(26, 315, 137, 17);
		panel.add(lblCity);
		
		lblddMmm = new JLabel("(dd - mmm - yyyy)");
		lblddMmm.setBounds(317, 65, 114, 31);
		panel.add(lblddMmm);
	}

	public void loadData(Integer id)
	{
		cId = id;
		List<Map<String, Object>> customer;
		try
		{
			customer = EventManager.getCustomer(id);
			txtAreaAddress.setText(customer.get(0).get("address").toString());
			txtEmail.setText(customer.get(0).get("email").toString());
			txtFullName.setText(customer.get(0).get("fullName").toString());
			txtLandmark.setText(customer.get(0).get("landMark").toString());
			txtfrmtdAnniversary.setText(customer.get(0).get("anniversary").toString());
			txtfrmtdContactNo.setText(customer.get(0).get("contactNo").toString());
			txtfrmtdPincode.setText(customer.get(0).get("pinCode").toString());
			txtfrmtdDob.setText(customer.get(0).get("dob").toString());
			txtCity.setText(customer.get(0).get("city").toString());
			String gender = customer.get(0).get("gender").toString();
			String maritialStatus = customer.get(0).get("maritialStatus").toString();
			if ("Male".equals(gender))
			{
				rdbtnFemale.setSelected(false);
				rdbtnMale.setSelected(true);
			}
			else
			{
				rdbtnMale.setSelected(false);
				rdbtnFemale.setSelected(true);
			}
			if ("Single".equals(maritialStatus))
			{
				rdbtnMarried.setSelected(false);
				rdbtnSingle.setSelected(true);
				anniversaryPannel.setVisible(false);
			}
			else
			{
				rdbtnSingle.setSelected(false);
				rdbtnMarried.setSelected(true);
				anniversaryPannel.setVisible(true);
			}
		}
		catch (ClassNotFoundException | SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Database not available , Please contact System Admin");
		}
		
	}
	public void setDefaultButton()
	{
		super.getRootPane().setDefaultButton(btnUpdate);
	}
}