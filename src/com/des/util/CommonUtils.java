package com.des.util;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import com.des.event.EventManager;

public class CommonUtils
{
	private static Pattern				pattern;
	private static Matcher				matcher;

	private static final String	EMAIL_PATTERN	= "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public static boolean validate(final String hex)
	{
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(hex);
		return matcher.matches();
	}
}

//old validation logic
/*if("".equals(txtFullName.getText()) || "".equals(txtEmail.getText()) || "".equals(txtfrmtdDob.getText()) || (!"".equals(txtfrmtdAnniversary.getText()) && Integer.parseInt(txtfrmtdAnniversary.getText().split("-")[2]) - Integer.parseInt(txtfrmtdDob.getText().split("-")[2])<18) || !CommonUtils.validate(txtEmail.getText()))
{
	JOptionPane.showMessageDialog(null, "Please Enter Valid Full Name , Email & Date Of Birth");
	txtFullName.requestFocus();
}*/


/*Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
int x = (int) ((dimension.getWidth() - getWidth()) / 2);
int y = (int) ((dimension.getHeight() - getHeight()) / 2);
*/

//PDF Export
/*List<Map<String,Object>> customers = EventManager.getAllCustomerList();
if(PDFUtil.generatePDF(customers))
	JOptionPane.showMessageDialog(null, "PDF has been exported successfully.");
else
	JOptionPane.showMessageDialog(null, "PDF could not exported successfully , Please try again");*/