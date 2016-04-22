package com.des.ui;

import javax.swing.JPanel;

import com.des.event.EventManager;
import com.des.util.ExcelSheetUtil;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;

public class HeaderPanelUI extends JPanel
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -8798146628708684108L;
	private static JLabel		lblUserId;

	public static void setUserId(String userId)
	{
		lblUserId = new JLabel(userId);
		lblUserId.setVisible(false);
	}

	public static String getUserId()
	{
		return lblUserId.getText();
	}

	/**
	 * Create the panel.
	 */
	public HeaderPanelUI()
	{
		setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnDashboard = new JButton("Dashboard");
		btnDashboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				new DashboardUI().setVisible(true);
			}
		});
		add(btnDashboard);

		String addCustomer = "Add\nCustomer";
		JButton btnAddCustomer = new JButton("<html>" + addCustomer.replaceAll("\\n", "<br>") + "</html>");
		btnAddCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				new AddCustomerUI().setVisible(true);
			}
		});
		add(btnAddCustomer);
		
		String manageCustomer = "Manage\nCustomer";
		JButton btnManageCustomers = new JButton("<html>" + manageCustomer.replaceAll("\\n", "<br>") + "</html>");
		btnManageCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				new ManageCustomerUI().setVisible(true);
			}
		});
		add(btnManageCustomers);

		JButton btnNotifications = new JButton("Notifications");
		btnNotifications.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				new NotificationUI().setVisible(true);
			}
		});
		add(btnNotifications);

		String exportToExcel = "Export To\nExcelsheet";
		JButton btnExportToExcel = new JButton("<html>" + exportToExcel.replaceAll("\\n", "<br>") + "</html>");
		btnExportToExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				List<Map<String, Object>> customers;
				try
				{
					customers = EventManager.getAllCustomerList();
					if (ExcelSheetUtil.generateExcelSheet(customers))
						JOptionPane.showMessageDialog(null, "Excelsheet has been exported successfully.");
					else
						JOptionPane.showMessageDialog(null,
								"Excelsheet could not exported successfully , Please try again");
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
		});
		add(btnExportToExcel);

		String createLoginUser = "Create\nLogin\nUser";
		JButton btnCreateLoginUser = new JButton("<html>" + createLoginUser.replaceAll("\\n", "<br>") + "</html>");
		btnCreateLoginUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				new CreateLoginUserUI().setVisible(true);
			}
		});
		add(btnCreateLoginUser);

		String changePassword = "Change\nPassword";
		JButton btnChangePassword = new JButton("<html>" + changePassword.replaceAll("\\n", "<br>") + "</html>");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				new ChangePasswordUI().setVisible(true);
			}
		});
		add(btnChangePassword);

		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				HeaderPanelUI.setUserId("");
				JOptionPane.showMessageDialog(null, "Logout successfully");
				new LoginUI().setVisible(true);
			}
		});
		add(btnLogout);
	}

	public void close()
	{
		super.setVisible(false);
	}

}
