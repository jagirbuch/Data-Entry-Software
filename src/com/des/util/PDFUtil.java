package com.des.util;

import java.io.FileOutputStream;

import java.util.List;
import java.util.Map;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFUtil
{

	// public static void main(String[] args)
	public static boolean generatePDF(List<Map<String, Object>> customers)
	{
		Document document = new Document();
		try
		{
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Users.pdf"));
			document.open();
			PdfPTable table = null;
			if (!customers.isEmpty())
				table = new PdfPTable(customers.get(0).keySet().size()); // 3
																			// columns.
			else
				table = new PdfPTable(11); // 3 columns.
			table.setWidthPercentage(100); // Width 100%
			table.setSpacingBefore(10f); // Space before table
			table.setSpacingAfter(10f); // Space after table

			// Set Column widths
			float[] columnWidths = { 1f, 3f, 2f, 3f, 2f, 2f, 3f, 3f, 3f, 2f, 3f };
			table.setWidths(columnWidths);

			PdfPCell cellId = new PdfPCell(new Paragraph("Id"));
			cellId.setBorderColor(BaseColor.BLACK);
			cellId.setPaddingLeft(10);
			cellId.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellId.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cellId);

			PdfPCell cellFullName = new PdfPCell(new Paragraph("Full Name"));
			cellFullName.setBorderColor(BaseColor.BLACK);
			cellFullName.setPaddingLeft(10);
			cellFullName.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellFullName.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cellFullName);

			PdfPCell cellGender = new PdfPCell(new Paragraph("Gender"));
			cellGender.setBorderColor(BaseColor.BLACK);
			cellGender.setPaddingLeft(10);
			cellGender.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellGender.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cellGender);

			PdfPCell cellAddress = new PdfPCell(new Paragraph("Address"));
			cellAddress.setBorderColor(BaseColor.BLACK);
			cellAddress.setPaddingLeft(10);
			cellAddress.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellAddress.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cellAddress);

			PdfPCell cellLandMark = new PdfPCell(new Paragraph("LandMark"));
			cellLandMark.setBorderColor(BaseColor.BLACK);
			cellLandMark.setPaddingLeft(10);
			cellLandMark.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellLandMark.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cellLandMark);

			PdfPCell cellPinCode = new PdfPCell(new Paragraph("PinCode"));
			cellPinCode.setBorderColor(BaseColor.BLACK);
			cellPinCode.setPaddingLeft(10);
			cellPinCode.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellPinCode.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cellPinCode);

			PdfPCell cellContactNo = new PdfPCell(new Paragraph("Contact No"));
			cellContactNo.setBorderColor(BaseColor.BLACK);
			cellContactNo.setPaddingLeft(10);
			cellContactNo.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellContactNo.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cellContactNo);

			PdfPCell cellEmail = new PdfPCell(new Paragraph("Email"));
			cellEmail.setBorderColor(BaseColor.BLACK);
			cellEmail.setPaddingLeft(10);
			cellEmail.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellEmail.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cellEmail);

			PdfPCell cellDob = new PdfPCell(new Paragraph("Dob"));
			cellDob.setBorderColor(BaseColor.BLACK);
			cellDob.setPaddingLeft(10);
			cellDob.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellDob.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cellDob);

			PdfPCell cellMaritialStatus = new PdfPCell(new Paragraph("Maritial Status"));
			cellMaritialStatus.setBorderColor(BaseColor.BLACK);
			cellMaritialStatus.setPaddingLeft(10);
			cellMaritialStatus.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellMaritialStatus.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cellMaritialStatus);

			PdfPCell cellAnniversary = new PdfPCell(new Paragraph("Anniversary"));
			cellAnniversary.setBorderColor(BaseColor.BLACK);
			cellAnniversary.setPaddingLeft(10);
			cellAnniversary.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellAnniversary.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(cellAnniversary);

			if (!customers.isEmpty())
			{
				for (Map<String, Object> map : customers)
				{
					PdfPCell icellId = new PdfPCell(new Paragraph(map.get("id").toString()));
					icellId.setBorderColor(BaseColor.BLACK);
					icellId.setPaddingLeft(10);
					icellId.setHorizontalAlignment(Element.ALIGN_CENTER);
					icellId.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(icellId);

					PdfPCell icellFullName = new PdfPCell(new Paragraph(map.get("fullName").toString()));
					icellFullName.setBorderColor(BaseColor.BLACK);
					icellFullName.setPaddingLeft(10);
					icellFullName.setHorizontalAlignment(Element.ALIGN_CENTER);
					icellFullName.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(icellFullName);

					PdfPCell icellGender = new PdfPCell(new Paragraph(map.get("gender").toString()));
					icellGender.setBorderColor(BaseColor.BLACK);
					icellGender.setPaddingLeft(10);
					icellGender.setHorizontalAlignment(Element.ALIGN_CENTER);
					icellGender.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(icellGender);

					PdfPCell icellAddress = new PdfPCell(new Paragraph(map.get("address").toString()));
					icellAddress.setBorderColor(BaseColor.BLACK);
					icellAddress.setPaddingLeft(10);
					icellAddress.setHorizontalAlignment(Element.ALIGN_CENTER);
					icellAddress.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(icellAddress);

					PdfPCell icellLandMark = new PdfPCell(new Paragraph(map.get("landMark").toString()));
					icellLandMark.setBorderColor(BaseColor.BLACK);
					icellLandMark.setPaddingLeft(10);
					icellLandMark.setHorizontalAlignment(Element.ALIGN_CENTER);
					icellLandMark.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(icellLandMark);

					PdfPCell icellPinCode = new PdfPCell(new Paragraph(map.get("pinCode").toString()));
					icellPinCode.setBorderColor(BaseColor.BLACK);
					icellPinCode.setPaddingLeft(10);
					icellPinCode.setHorizontalAlignment(Element.ALIGN_CENTER);
					icellPinCode.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(icellPinCode);

					PdfPCell icellContactNo = new PdfPCell(new Paragraph(map.get("contactNo").toString()));
					icellContactNo.setBorderColor(BaseColor.BLACK);
					icellContactNo.setPaddingLeft(10);
					icellContactNo.setHorizontalAlignment(Element.ALIGN_CENTER);
					icellContactNo.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(icellContactNo);

					PdfPCell icellEmail = new PdfPCell(new Paragraph(map.get("email").toString()));
					icellEmail.setBorderColor(BaseColor.BLACK);
					icellEmail.setPaddingLeft(10);
					icellEmail.setHorizontalAlignment(Element.ALIGN_CENTER);
					icellEmail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(icellEmail);

					PdfPCell icellDob = new PdfPCell(new Paragraph(map.get("dob").toString()));
					icellDob.setBorderColor(BaseColor.BLACK);
					icellDob.setPaddingLeft(10);
					icellDob.setHorizontalAlignment(Element.ALIGN_CENTER);
					icellDob.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(icellDob);

					PdfPCell icellMaritialStatus = new PdfPCell(new Paragraph(map.get("maritialStatus").toString()));
					icellMaritialStatus.setBorderColor(BaseColor.BLACK);
					icellMaritialStatus.setPaddingLeft(10);
					icellMaritialStatus.setHorizontalAlignment(Element.ALIGN_CENTER);
					icellMaritialStatus.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(icellMaritialStatus);

					PdfPCell icellAnniversary = new PdfPCell(new Paragraph(map.get("anniversary").toString()));
					icellAnniversary.setBorderColor(BaseColor.BLACK);
					icellAnniversary.setPaddingLeft(10);
					icellAnniversary.setHorizontalAlignment(Element.ALIGN_CENTER);
					icellAnniversary.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(icellAnniversary);
					/*for (Map.Entry<String, Object> entry : map.entrySet())
					{
						String key = entry.getKey();
						Object value = entry.getValue();
						PdfPCell cell = new PdfPCell(new Paragraph(value.toString()));
						cell.setBorderColor(BaseColor.BLACK);
						cell.setPaddingLeft(10);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						table.addCell(cell);
					}*/
				}
			}
			document.add(table);
			document.close();
			writer.close();
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
