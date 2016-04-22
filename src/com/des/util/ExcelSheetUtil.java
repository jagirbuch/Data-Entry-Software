package com.des.util;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelSheetUtil
{
	public static boolean generateExcelSheet(List<Map<String, Object>> customers)
	{
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet spreadsheet = workbook.createSheet("cell types");
		XSSFRow row = spreadsheet.createRow((short) 0);
		row.createCell(0).setCellValue("Id");
		row.createCell(1).setCellValue("Full Name");
		row.createCell(2).setCellValue("Gender");
		row.createCell(3).setCellValue("Address");
		row.createCell(4).setCellValue("Land Mark");
		row.createCell(5).setCellValue("Pin Code");
		row.createCell(6).setCellValue("Contact Number");
		row.createCell(7).setCellValue("Email");
		row.createCell(8).setCellValue("Dob");
		row.createCell(9).setCellValue("Maritial Status");
		row.createCell(10).setCellValue("Anniversary");

		//List<Map<String, Object>> customers = new DbManager().executeQuery("SELECT * FROM customer");
		if (!customers.isEmpty())
		{
			int outerCnt = 1;
			for (Map<String, Object> map : customers)
			{
				row = spreadsheet.createRow((short) outerCnt);
				row.createCell(0).setCellValue(map.get("id").toString());
				row.createCell(1).setCellValue(map.get("fullName").toString());
				row.createCell(2).setCellValue(map.get("gender").toString());
				row.createCell(3).setCellValue(map.get("address").toString());
				row.createCell(4).setCellValue(map.get("landMark").toString());
				row.createCell(5).setCellValue(map.get("pinCode").toString());
				row.createCell(6).setCellValue(map.get("contactNo").toString());
				row.createCell(7).setCellValue(map.get("email").toString());
				row.createCell(8).setCellValue(map.get("dob").toString());
				row.createCell(9).setCellValue(map.get("maritialStatus").toString());
				row.createCell(10).setCellValue(map.get("anniversary").toString());
				outerCnt++;
			}
		}

		FileOutputStream out = null;
		try
		{
			out = new FileOutputStream(new File("Users.xlsx"));
			workbook.write(out);
			out.close();
			return true;
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			return false;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
		
	}
}
