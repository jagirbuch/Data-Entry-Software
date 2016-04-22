package com.des.ui;

import java.awt.BorderLayout;


import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.des.event.EventManager;
import com.des.util.MailUtil;

public class NotificationUI extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5051265025021358005L;
	private JPanel	contentPane;
	private JTable	table;

	/**
	 * Create the frame.
	 */
	public NotificationUI()
	{
		setTitle("Notifications");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();		
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		setSize(screenSize);
		setVisible(true);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(15, 15));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		setGridView(panel);
		setButtons(contentPane);

		// Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(table);
		// Add the scroll pane to this panel.
		getContentPane().add(scrollPane);
		
		HeaderPanelUI hpu = new HeaderPanelUI();
		contentPane.add(hpu, BorderLayout.NORTH);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e)
			{
				Frame[] f = getFrames();
				for(Frame frame : getFrames())
				{
					if(frame instanceof NotificationUI==false)
					{
						frame.dispose();
					}
				}
			}
		});
	}

	private void setGridView(JPanel panel)
	{
		List<Map<String, Object>> customers;
		try
		{
			customers = EventManager.getAllCustomerList();
			// create model for jtable
			DefaultTableModel model = new DefaultTableModel(new String[] { "Id", "Full Name", "Gender", "Contact Number",
					"Email", "Maritial Status", "Date Of Birth", "Anniversary" }, 0);
			if (!customers.isEmpty())
			{
				for (Map<String, Object> map : customers)
				{
					boolean anniversary = false;
					boolean bDt = false;
					if (!map.get("anniversary").equals(""))
					{
						anniversary = isSpecialDay(map.get("anniversary").toString());
					}

					if (!map.get("dob").equals(""))
					{
						bDt = isSpecialDay(map.get("dob").toString());
					}

					if (bDt || anniversary)
					{
						model.addRow(new Object[] { map.get("id"), map.get("fullName"), map.get("gender"),
								map.get("contactNo"), map.get("email"), map.get("maritialStatus"), map.get("dob"),
								map.get("anniversary") });
					}

				}
			}

			table = new JTable(model);
			table.setPreferredScrollableViewportSize(new Dimension(500, 70));
			table.setFillsViewportHeight(true);
			
			DefaultTableCellRenderer stringRenderer = (DefaultTableCellRenderer) table.getDefaultRenderer(String.class);
			stringRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			
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

	private void setButtons(JPanel panel)
	{
		JButton btnSendMail = new JButton("SendMail");
		btnSendMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event)
			{
				if (table.getSelectedRow() == -1)
				{
					JOptionPane.showMessageDialog(null, "Please select appropriate row");
				}
				else
				{
					List<String> to = new ArrayList<String>();
					for(Integer rowId : table.getSelectedRows())
					{
						to.add(table.getValueAt(rowId, 4).toString());						
					}
					if(MailUtil.sendMail(to))
						JOptionPane.showMessageDialog(null, "Email Sent Successfully");
					else
						JOptionPane.showMessageDialog(null, "Email could not be sent , Please select valid email address or check whether your credential are correct or not , Or check specialday.vm exists or not at correct place");
					
				}
			}
		});
		contentPane.add(btnSendMail, BorderLayout.SOUTH);
	}

	private boolean isSpecialDay(String date)
	{
		DateFormat format = new SimpleDateFormat("dd-MMMM-yyyy");
		Date sysDate = new Date();
		try
		{
			Date dt = format.parse(date);
			Calendar cal1 = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			cal1.setTime(dt);
			cal2.setTime(sysDate);
			if (cal1.get(Calendar.DATE) == cal2.get(Calendar.DATE)
					&& cal1.get(Calendar.MONTH) + 1 == cal2.get(Calendar.MONTH) + 1)
				return true;
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		return false;
	}

}
