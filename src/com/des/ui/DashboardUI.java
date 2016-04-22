package com.des.ui;

import java.awt.BorderLayout;


import java.awt.Dimension;
import java.awt.Frame;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.des.event.EventManager;
import com.des.util.MailUtil;
import javax.swing.JLabel;
import java.awt.Font;

public class DashboardUI extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5051265025021358005L;
	private JPanel	contentPane;

	/**
	 * Create the frame.
	 */
	public DashboardUI()
	{
		setTitle("Dashboard");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setBounds(100, 100, 850, 550);
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
		
		HeaderPanelUI hpu = new HeaderPanelUI();
		contentPane.add(hpu, BorderLayout.NORTH);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e)
			{
				Frame[] f = getFrames();
				for(Frame frame : getFrames())
				{
					if(frame instanceof DashboardUI==false)
					{
						frame.dispose();
					}
				}
			}
		});

	}

	private void setLabels(JPanel panel)
	{
		JLabel lblText = new JLabel("Customers have Special day today");
		lblText.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		lblText.setBounds(107, 109, 696, 92);
		panel.add(lblText);
		
		JLabel lblCount = new JLabel("");
		lblCount.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		lblCount.setBounds(38, 125, 59, 61);
		panel.add(lblCount);
		
		JLabel lblTotal = new JLabel("");
		lblTotal.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblTotal.setBounds(737, 371, 66, 54);
		panel.add(lblTotal);
		
		JLabel lblYouHaveTotal = new JLabel("Total Customer :");
		lblYouHaveTotal.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblYouHaveTotal.setBounds(522, 381, 205, 35);
		panel.add(lblYouHaveTotal);

		List<Map<String, Object>> customers;
		try
		{
			customers = EventManager.getAllCustomerList();
			int cnt = 0;
			int all = 0;
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
						cnt++;
					}
					all++;
				}
			}
			
			lblCount.setText(String.valueOf(cnt));
			lblTotal.setText(String.valueOf(all));
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
