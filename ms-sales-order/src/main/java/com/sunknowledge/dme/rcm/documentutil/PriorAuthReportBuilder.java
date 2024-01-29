package com.sunknowledge.dme.rcm.documentutil;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sunknowledge.dme.rcm.application.utils.ApplicationConstants;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.ParMaster;
import com.sunknowledge.dme.rcm.dto.cmn.EquipmentDetailsDTO;
import com.sunknowledge.dme.rcm.dto.cmn.IcdDetails;
import com.sunknowledge.dme.rcm.dto.cmn.InsuranceMasterDTO;
import com.sunknowledge.dme.rcm.dto.cmn.SWODataDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PriorAuthReportBuilder {

	public static void addQrCodeOnFooter(Long parId, PdfWriter writer) {
		String img = ApplicationConstants.TEMP_QRFILE_PATH + "/" + parId + ".png";
		Image image;
		try {
			image = Image.getInstance(img);
			image.setAlignment(Element.ALIGN_LEFT);
			image.setAbsolutePosition(30, 10);
			image.scalePercent(60f, 60f);
			writer.getDirectContent().addImage(image, true);
		} catch (IOException | DocumentException e) {
			e.printStackTrace();
		}
	}

    public static Image addQrCodeOnFooterInAwsBucket(Document document, byte[] qrCodeBytes, Image qrCodeImage) throws BadElementException, IOException {
        qrCodeImage.setAlignment(Element.ALIGN_LEFT);
        qrCodeImage.scalePercent(100f, 100f); // Adjust the size as needed
        qrCodeImage.setAbsolutePosition(30, 10);
        //qrCodeImage.setAbsolutePosition(document.leftMargin(), document.bottomMargin() - 30); // Adjust coordinates as needed
        return qrCodeImage;
    }

	public static PdfPTable PriorAuthInitReporMainTitle(int pageno) throws DocumentException, IOException {
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		table.setWidths(new float[] { 0.98f });
		table.addCell(createTitleRowLargeText("Prior Authorization Request - Initial", pageno));
		return table;
	}

	public static PdfPTable PriorAuthRenewalReporMainTitle(int pageno) throws DocumentException, IOException {
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		table.setWidths(new float[] { 0.98f });
		table.addCell(createTitleRowLargeText("Prior Authorization Request - Renewal", pageno));
		return table;
	}

	private static PdfPCell createTitleRowLargeText(String text, int pageno) {
		Font font = new Font(FontFamily.TIMES_ROMAN, 18, Font.BOLD, BaseColor.GRAY);
		PdfPCell cell = new PdfPCell(new Phrase(text, font));
		titleRowTextCenterLargeStyle(cell);
		cell.setBorder(PdfPCell.BOTTOM);
		return cell;
	}

	private static void titleRowTextCenterLargeStyle(PdfPCell cell) {
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPaddingTop(5f);
		cell.setPaddingBottom(5f);
		cell.setBackgroundColor(BaseColor.WHITE);
		cell.setBorderWidthBottom(0.55f);
	}

	public static PdfPTable createPriorAuthReportMainBodyTitle(String cellHeader1, String cellHeader2)
			throws DocumentException, IOException {
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
		table.setWidths(new float[] { 1.40f, 1.4f });
		table.setSpacingBefore(10);
		table.addCell(createCellWithBodyLebelWithBorder(cellHeader1));
		table.addCell(createCellWithBodyLebelWithBorder(cellHeader2));
		return table;
	}

	private static PdfPCell createCellWithBodyLebelWithBorder(String text) {
		Font font = new Font(FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(text, font));
		titleRowTextSmallSubTitle5StyleBorder(cell);
		cell.setBorder(PdfPCell.BOTTOM);
		return cell;
	}

	private static void titleRowTextSmallSubTitle5StyleBorder(PdfPCell cell) {
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setPaddingTop(3f);
		cell.setPaddingLeft(1f);
		cell.setPaddingBottom(3f);
		cell.setBackgroundColor(BaseColor.WHITE);
		cell.setBorder(2);
		cell.setBorderWidthBottom(0.5f);
		cell.setBorderColorBottom(BaseColor.BLACK);
		cell.setBorderColorTop(BaseColor.BLACK);
	}

	public static PdfPTable createPriorAuthReportProviderPatientDetails(SWODataDTO swoDataDTO)
			throws DocumentException {

		PdfPTable table = new PdfPTable(4);
		table.setWidthPercentage(100);
		table.setWidths(new float[] { 0.35f, 1, 0.3f, 1 });

		table.addCell(createCellWithBodyLebelText("Name:"));
		if (swoDataDTO.getBilling_branch_name() != null)
			table.addCell(createCellWithBodyValueText(swoDataDTO.getBilling_branch_name().toUpperCase()));
		else
			table.addCell(createCellWithBodyValueText(" "));
		table.addCell(createCellWithBodyLebelText("Name:"));
		if (swoDataDTO.getPatient_middle_name() != null)
			table.addCell(createCellWithBodyValueText(swoDataDTO.getPatient_first_name().toUpperCase() + " "
					+ swoDataDTO.getPatient_middle_name() + " " + swoDataDTO.getPatient_last_name().toUpperCase()));
		else
			table.addCell(createCellWithBodyValueText(swoDataDTO.getPatient_first_name().toUpperCase() + " "
					+ swoDataDTO.getPatient_last_name().toUpperCase()));

		table.addCell(createCellWithBodyLebelText("Address:"));
		table.addCell(createCellWithBodyValueText(swoDataDTO.getBranch_address_line_1()));
		table.addCell(createCellWithBodyLebelText("Address:"));
		table.addCell(createCellWithBodyValueText(swoDataDTO.getPatient_address_line_1()));

		table.addCell(createCellWithBodyLebelText(""));
		table.addCell(createCellWithBodyValueText(swoDataDTO.getBranch_address_line_2()));
		table.addCell(createCellWithBodyLebelText(""));
		table.addCell(createCellWithBodyValueText(swoDataDTO.getPatient_address_line_2()));

		table.addCell(createCellWithBodyLebelText(""));
		table.addCell(createCellWithBodyValueText(swoDataDTO.getBranch_city() + " " + swoDataDTO.getBranch_state() + " "
				+ swoDataDTO.getBranch_zip_code()));
		table.addCell(createCellWithBodyLebelText(""));
		table.addCell(createCellWithBodyValueText(
				swoDataDTO.getCity_name() + " " + swoDataDTO.getState_name() + " " + swoDataDTO.getZip_code()));

		table.addCell(createCellWithBodyLebelText("Phone:"));
		table.addCell(createCellWithBodyValueText(swoDataDTO.getBranch_contact_no_1()));
		table.addCell(createCellWithBodyLebelText("Patient Id No:"));
		table.addCell(createCellWithBodyValueText(swoDataDTO.getPatient_contact_no_1()));

		table.addCell(createCellWithBodyLebelText("Fax No:"));
		table.addCell(createCellWithBodyValueText(swoDataDTO.getBranch_fax()));
		table.addCell(createCellWithBodyLebelText("Policy No:"));
		table.addCell(createCellWithBodyValueText(swoDataDTO.getPrimary_insurer_policy_no()));

		table.addCell(createCellWithBodyLebelText("NPI:"));
		if (swoDataDTO.getBilling_branch_name() != null)
			table.addCell(createCellWithBodyValueText(swoDataDTO.getFacility_npi()!=null ? String.valueOf(swoDataDTO.getFacility_npi()) : ""));
		else
			table.addCell(createCellWithBodyValueText(" "));
		table.addCell(createCellWithBodyLebelText("DOB:"));
		table.addCell(createCellWithBodyValueText(swoDataDTO.getPatient_dob().toString()));

		return table;
	}

	private static PdfPCell createCellWithBodyLebelText(String text) {
		Font font = new Font(FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(text, font));
		titleRowTextSmallSubTitle5Style(cell);
		cell.setBorder(PdfPCell.NO_BORDER);
		return cell;
	}

	private static void titleRowTextSmallSubTitle5Style(PdfPCell cell) {
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setPaddingTop(3f);
		cell.setPaddingLeft(1f);
		cell.setPaddingBottom(3f);
		cell.setBackgroundColor(BaseColor.WHITE);
		cell.setBorder(0);
		cell.setBorderWidthBottom(0.1f);
		cell.setBorderColorBottom(BaseColor.BLACK);
		cell.setBorderColorTop(BaseColor.BLACK);
	}

	public static PdfPTable createPriorAuthReportMainBodyInsurancePhysicianDetails(SWODataDTO swoDataDTO,
			InsuranceMasterDTO objInsuranceMasterDTO) throws DocumentException, IOException {
		PdfPTable table = new PdfPTable(4);
		table.setWidthPercentage(100);
		table.setWidths(new float[] { 0.35f, 1, 0.3f, 1 });

		table.addCell(createCellWithBodyLebelText("Insurance Name:"));
		table.addCell(createCellWithBodyValueText(objInsuranceMasterDTO.getPrimary_insurer_name()));
		table.addCell(createCellWithBodyLebelText("Physician Name:"));
		table.addCell(createCellWithBodyValueText(swoDataDTO.getOrdering_provider_first_name() + " "
				+ swoDataDTO.getOrdering_provider_middle_name() + " " + swoDataDTO.getOrdering_provider_last_name()));

		table.addCell(createCellWithBodyLebelText("Address:"));
		table.addCell(createCellWithBodyValueText(objInsuranceMasterDTO.getPrimary_insurer_address_line_1()));
		table.addCell(createCellWithBodyLebelText("Address:"));
		table.addCell(createCellWithBodyValueText(swoDataDTO.getOrdering_provider_address_line_1()));

		table.addCell(createCellWithBodyValueText(" "));
		table.addCell(createCellWithBodyValueText(objInsuranceMasterDTO.getPrimary_insurer_address_line_2()));
		table.addCell(createCellWithBodyValueText(" "));
		table.addCell(createCellWithBodyValueText(swoDataDTO.getOrdering_provider_address_line_2()));

		table.addCell(createCellWithBodyValueText(" "));
		table.addCell(createCellWithBodyValueText(
				objInsuranceMasterDTO.getPrimary_insurer_city() + " " + objInsuranceMasterDTO.getPrimary_insurer_state()
						+ " " + objInsuranceMasterDTO.getPrimary_insurer_zip()));
		table.addCell(createCellWithBodyValueText(" "));
		table.addCell(createCellWithBodyValueText(swoDataDTO.getOrdering_provider_city() + ","
				+ swoDataDTO.getOrdering_provider_state() + "," + swoDataDTO.getOrdering_provider_zip()));

		table.addCell(createCellWithBodyLebelText("Phone:"));
		table.addCell(createCellWithBodyValueText(objInsuranceMasterDTO.getPrimary_insurer_contact_1()));
		table.addCell(createCellWithBodyLebelText("Phone:"));
		table.addCell(createCellWithBodyValueText(swoDataDTO.getOrdering_provider_contact_no_1()));

		table.addCell(createCellWithBodyLebelText("Fax No:"));
		table.addCell(createCellWithBodyValueText(objInsuranceMasterDTO.getPrimary_insurer_fax()));
		table.addCell(createCellWithBodyLebelText("Fax No:"));
		table.addCell(createCellWithBodyValueText(swoDataDTO.getOrdering_provider_fax()));

		table.addCell(createCellWithBodyValueText(" "));
		table.addCell(createCellWithBodyValueText(" "));
		table.addCell(createCellWithBodyLebelText("NPI:"));
		table.addCell(createCellWithBodyValueText(swoDataDTO.getOrdering_provider_npi()));

		return table;
	}

	public static PdfPTable createPriorAuthReportMainBodyPARHeader() throws DocumentException, IOException {
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		table.setWidths(new float[] { 1 });
		table.setSpacingBefore(10);
		table.addCell(createCellWithBodyLebelWithBorder("PAR INFORMATION:"));
		return table;
	}

	public static PdfPTable createPriorAuthReportMainBodyPARHeader2() throws DocumentException, IOException {
		PdfPTable table = new PdfPTable(4);
		table.setWidthPercentage(100);
		table.setWidths(new float[] { 1.5f, 2f, 2.2f, 2.3f });
		table.addCell(createCellWithBodyLebelWithBorder("PAR No"));
		table.addCell(createCellWithBodyLebelWithBorder("Initial Date"));
		table.addCell(createCellWithBodyLebelWithBorder("Expiration From"));
		table.addCell(createCellWithBodyLebelWithBorder("To"));
		return table;
	}

	public static PdfPTable createPriorAuthReportMainBodyDiagnosisHeader() throws DocumentException, IOException {
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		table.setWidths(new float[] { 1 });
		table.setSpacingBefore(10);
		table.addCell(createCellWithBodyLebelWithBorder("DIAGNOSIS:"));
		return table;
	}

	public static PdfPTable createPriorAuthReportMainBodyDiagnosisHeader2() throws DocumentException, IOException {
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
		table.setWidths(new float[] { 0.7f, 2.3f });
		table.addCell(createCellWithBodyLebelWithBorder("ICD-10 Code"));
		table.addCell(createCellWithBodyLebelWithBorder("Description"));
		return table;
	}

	public static PdfPTable createPriorAuthReportMainBodyItemDetailsHeader() throws DocumentException, IOException {
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		table.setWidths(new float[] { 1 });
		table.setSpacingBefore(10);
		table.addCell(createCellWithBodyLebelWithBorder("ITEM DETAILS:"));
		return table;
	}

	public static PdfPTable createPriorAuthReportMainBodyItemDetailsHeader2() throws DocumentException, IOException {
		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100);
		table.setWidths(new float[] { 0.3f, 0.7f, 0.5f, 0.5f, 2.1f, 0.9f });
		table.addCell(createCellWithBodyLebelWithBorder("Qty"));
		table.addCell(createCellWithBodyLebelWithBorder("Sale Type"));
		table.addCell(createCellWithBodyLebelWithBorder("HCPCS Code"));
		table.addCell(createCellWithBodyLebelWithBorder("Modifier"));
		table.addCell(createCellWithBodyLebelWithBorder("Item Name"));
		table.addCell(createCellWithBodyLebelWithBorder("Charge Amount"));
		return table;
	}

	public static PdfPTable createPriorAuthInitReportMainBodyAuthNo() throws DocumentException, IOException {
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		table.setWidths(new float[] { 2.5f });
		table.addCell(createCellWithBodyLebelText("Authorization #:"));
		return table;
	}

	public static PdfPTable createPriorAuthRenewalReportMainBodyAuthNo(String data)
			throws DocumentException, IOException {
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
		table.setWidths(new float[] { 2.5f, 2.5f });
		table.addCell(createCellWithBodyLebelText("Authorization #:"));
		table.addCell(authorizationNumberBodyLebelWithBorder(data));
		return table;
	}

	private static PdfPCell authorizationNumberBodyLebelWithBorder(String text) {
		Font font = new Font(FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(text, font));
		authorizationNumberStyleBorder(cell);
		cell.setBorder(PdfPCell.BOTTOM);
		return cell;
	}

	private static void authorizationNumberStyleBorder(PdfPCell cell) {
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setPaddingTop(3f);
		cell.setPaddingLeft(1f);
		cell.setPaddingBottom(3f);
		cell.setBackgroundColor(BaseColor.WHITE);
		cell.setBorder(2);
		cell.setBorderWidthBottom(0.5f);
		cell.setBorderColorBottom(BaseColor.BLACK);
		cell.setBorderColorTop(BaseColor.BLACK);
	}

	public static PdfPTable createPriorAuthInitReportMainBodyCoverageFrom() throws DocumentException, IOException {
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		table.setWidths(new float[] { 2.5f });
		table.addCell(createCellWithBodyLebelText("Coverage From:"));
		return table;
	}

	public static PdfPTable createPriorAuthRenewalReportMainBodyCoverageFrom(String data)
			throws DocumentException, IOException {
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
		table.setWidths(new float[] { 2.5f, 2.5f });
		table.addCell(createCellWithBodyLebelText("Coverage From:"));
		table.addCell(createCellWithBodyValueText(data));
		return table;
	}

	public static PdfPTable createPriorAuthInitReportMainBodyCoverageTo() throws DocumentException, IOException {
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		table.setWidths(new float[] { 2.5f });
		table.addCell(createCellWithBodyLebelText("Coverage To:"));
		return table;
	}

	public static PdfPTable createPriorAuthRenewalReportMainBodyCoverageTo(String data)
			throws DocumentException, IOException {
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
		table.setWidths(new float[] { 2.5f, 2.5f });
		table.addCell(createCellWithBodyLebelText("Coverage To:"));
		table.addCell(createCellWithBodyValueText(data));
		return table;
	}

	public static PdfPTable createPriorAuthInitReportMainBodyAuthorization() throws DocumentException, IOException {
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		table.setWidths(new float[] { 2.5f });
		table.addCell(createCellWithBodyLebelText("Authorized By:"));
		return table;
	}

	public static PdfPTable createPriorAuthRenewalReportMainBodyAuthorization(String data)
			throws DocumentException, IOException {
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
		table.setWidths(new float[] { 2.5f, 2.5f });
		table.addCell(createCellWithBodyLebelText("Authorized By:"));
		table.addCell(createCellWithBodyValueText(data));
		return table;
	}

	public static PdfPTable createPriorAuthInitReportMainBodyComments() throws DocumentException, IOException {
		PdfPTable table = new PdfPTable(1);
		table.setWidthPercentage(100);
		table.setWidths(new float[] { 2.5f });
		table.addCell(createCellWithBodyLebelText("Comments:"));
		return table;
	}

	public static PdfPTable createPriorAuthRenewalReportMainBodyComments(String data)
			throws DocumentException, IOException {
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
		table.setWidths(new float[] { 2.5f, 2.5f });
		table.addCell(createCellWithBodyLebelText("Comments:"));
		table.addCell(createCellWithBodyValueText(data));
		return table;
	}

	private static PdfPCell createCellWithBodyValueText(String text) {
		Font font = new Font(FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(text, font));
		titleRowTextSmallSubTitle5Style(cell);
		cell.setBorder(PdfPCell.NO_BORDER);
		return cell;
	}

	public static PdfPTable createOrderConfirmationMainBodyDiagnosisHeadData(SWODataDTO swoDataDTO)
			throws DocumentException, IOException {
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
		table.setWidths(new float[] { 0.7f, 2.3f });
		String code = "";
		String description = "";
//        code = getICDCodeOnDiagnosis(swoDataDTO);

		List<IcdDetails> icdDetailsList = getICDListOfCodes(swoDataDTO);
		System.out.println("=============>" + icdDetailsList.size());
		if (icdDetailsList.size() > 0) {
			int x = 0;
			if (icdDetailsList.size() == 1)
				x = 4;
			if (icdDetailsList.size() == 2)
				x = 3;
			if (icdDetailsList.size() == 3)
				x = 2;
			if (icdDetailsList.size() == 4)
				x = 1;
			for (int i = 0; i <= x; i++) {
				IcdDetails icdDetails = new IcdDetails();
				icdDetails.setCode(" ");
				icdDetails.setDescription(" ");
				icdDetailsList.add(icdDetails);
			}
		}
		icdDetailsList.stream().forEach(icd -> {
			table.addCell(createCellWithBodyValueText(icd.getCode()));
			table.addCell(createCellWithBodyValueTextWithJustified(icd.getDescription()));
		});
//        table.addCell(createCellWithBodyValueText(code));
//        table.addCell(createCellWithBodyValueText("Walker, rigid, wheeled, adjustable or fixed height"));
		return table;
	}

	private static List<IcdDetails> getICDListOfCodes(SWODataDTO swoDataDTO) {
		List<IcdDetails> icdDetailsList = new ArrayList<>();
		IcdDetails icdDetails = null;
		if (swoDataDTO.getIcd_10_diagnosis_code_1() != null) {
			icdDetails = new IcdDetails();
			icdDetails.setCode(swoDataDTO.getIcd_10_diagnosis_code_1().substring(0,
					swoDataDTO.getIcd_10_diagnosis_code_1().indexOf("||")));
			icdDetails.setDescription(swoDataDTO.getIcd_10_diagnosis_code_1()
					.substring(swoDataDTO.getIcd_10_diagnosis_code_1().lastIndexOf("||") + 2));
			icdDetailsList.add(icdDetails);
		}
		if (swoDataDTO.getIcd_10_diagnosis_code_2() != null) {
			icdDetails = new IcdDetails();
			icdDetails.setCode(swoDataDTO.getIcd_10_diagnosis_code_2().substring(0,
					swoDataDTO.getIcd_10_diagnosis_code_2().indexOf("||")));
			icdDetails.setDescription(swoDataDTO.getIcd_10_diagnosis_code_2()
					.substring(swoDataDTO.getIcd_10_diagnosis_code_2().lastIndexOf("||") + 2));
			icdDetailsList.add(icdDetails);
		}
		if (swoDataDTO.getIcd_10_diagnosis_code_3() != null) {
			icdDetails = new IcdDetails();
			icdDetails.setCode(swoDataDTO.getIcd_10_diagnosis_code_3().substring(0,
					swoDataDTO.getIcd_10_diagnosis_code_3().indexOf("||")));
			icdDetails.setDescription(swoDataDTO.getIcd_10_diagnosis_code_3()
					.substring(swoDataDTO.getIcd_10_diagnosis_code_3().lastIndexOf("||") + 2));
			icdDetailsList.add(icdDetails);
		}
		if (swoDataDTO.getIcd_10_diagnosis_code_4() != null) {
			icdDetails = new IcdDetails();
			icdDetails.setCode(swoDataDTO.getIcd_10_diagnosis_code_4().substring(0,
					swoDataDTO.getIcd_10_diagnosis_code_4().indexOf("||")));
			icdDetails.setDescription(swoDataDTO.getIcd_10_diagnosis_code_4()
					.substring(swoDataDTO.getIcd_10_diagnosis_code_4().lastIndexOf("||") + 2));
			icdDetailsList.add(icdDetails);
		}
		if (swoDataDTO.getIcd_10_diagnosis_code_5() != null) {
			icdDetails = new IcdDetails();
			icdDetails.setCode(swoDataDTO.getIcd_10_diagnosis_code_5().substring(0,
					swoDataDTO.getIcd_10_diagnosis_code_5().indexOf("||")));
			icdDetails.setDescription(swoDataDTO.getIcd_10_diagnosis_code_5()
					.substring(swoDataDTO.getIcd_10_diagnosis_code_5().lastIndexOf("||") + 2));
			icdDetailsList.add(icdDetails);
		}
		if (swoDataDTO.getIcd_10_diagnosis_code_6() != null) {
			icdDetails = new IcdDetails();
			icdDetails.setCode(swoDataDTO.getIcd_10_diagnosis_code_6().substring(0,
					swoDataDTO.getIcd_10_diagnosis_code_6().indexOf("||")));
			icdDetails.setDescription(swoDataDTO.getIcd_10_diagnosis_code_6()
					.substring(swoDataDTO.getIcd_10_diagnosis_code_6().lastIndexOf("||") + 2));
			icdDetailsList.add(icdDetails);
		}
		if (swoDataDTO.getIcd_10_diagnosis_code_7() != null) {
			icdDetails = new IcdDetails();
			icdDetails.setCode(swoDataDTO.getIcd_10_diagnosis_code_7().substring(0,
					swoDataDTO.getIcd_10_diagnosis_code_7().indexOf("||")));
			icdDetails.setDescription(swoDataDTO.getIcd_10_diagnosis_code_7()
					.substring(swoDataDTO.getIcd_10_diagnosis_code_7().lastIndexOf("||") + 2));
			icdDetailsList.add(icdDetails);
		}
		if (swoDataDTO.getIcd_10_diagnosis_code_8() != null) {
			icdDetails = new IcdDetails();
			icdDetails.setCode(swoDataDTO.getIcd_10_diagnosis_code_8().substring(0,
					swoDataDTO.getIcd_10_diagnosis_code_8().indexOf("||")));
			icdDetails.setDescription(swoDataDTO.getIcd_10_diagnosis_code_8()
					.substring(swoDataDTO.getIcd_10_diagnosis_code_8().lastIndexOf("||") + 2));
			icdDetailsList.add(icdDetails);
		}
		if (swoDataDTO.getIcd_10_diagnosis_code_9() != null) {
			icdDetails = new IcdDetails();
			icdDetails.setCode(swoDataDTO.getIcd_10_diagnosis_code_9().substring(0,
					swoDataDTO.getIcd_10_diagnosis_code_9().indexOf("||")));
			icdDetails.setDescription(swoDataDTO.getIcd_10_diagnosis_code_9()
					.substring(swoDataDTO.getIcd_10_diagnosis_code_9().lastIndexOf("||") + 2));
			icdDetailsList.add(icdDetails);
		}
		if (swoDataDTO.getIcd_10_diagnosis_code_10() != null) {
			icdDetails = new IcdDetails();
			icdDetails.setCode(swoDataDTO.getIcd_10_diagnosis_code_10().substring(0,
					swoDataDTO.getIcd_10_diagnosis_code_10().indexOf("||")));
			icdDetails.setDescription(swoDataDTO.getIcd_10_diagnosis_code_10()
					.substring(swoDataDTO.getIcd_10_diagnosis_code_10().lastIndexOf("||") + 2));
			icdDetailsList.add(icdDetails);
		}
		if (swoDataDTO.getIcd_10_diagnosis_code_11() != null) {
			icdDetails = new IcdDetails();
			icdDetails.setCode(swoDataDTO.getIcd_10_diagnosis_code_11().substring(0,
					swoDataDTO.getIcd_10_diagnosis_code_11().indexOf("||")));
			icdDetails.setDescription(swoDataDTO.getIcd_10_diagnosis_code_11()
					.substring(swoDataDTO.getIcd_10_diagnosis_code_11().lastIndexOf("||") + 2));
			icdDetailsList.add(icdDetails);
		}
		if (swoDataDTO.getIcd_10_diagnosis_code_12() != null) {
			icdDetails = new IcdDetails();
			icdDetails.setCode(swoDataDTO.getIcd_10_diagnosis_code_12().substring(0,
					swoDataDTO.getIcd_10_diagnosis_code_12().indexOf("||")));
			icdDetails.setDescription(swoDataDTO.getIcd_10_diagnosis_code_12()
					.substring(swoDataDTO.getIcd_10_diagnosis_code_12().lastIndexOf("||") + 2));
			icdDetailsList.add(icdDetails);
		}
		return icdDetailsList;
	}

	private static PdfPCell createCellWithBodyValueTextWithJustified(String text) {
		Font font = new Font(FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
		PdfPCell cell = new PdfPCell(new Phrase(text, font));
		titleRowTextSmallSubTitle5StyleWithJustified(cell);
		cell.setBorder(PdfPCell.NO_BORDER);
		return cell;
	}

	private static void titleRowTextSmallSubTitle5StyleWithJustified(PdfPCell cell) {
		cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell.setPaddingTop(3f);
		cell.setPaddingLeft(1f);
		cell.setPaddingBottom(3f);
		cell.setBackgroundColor(BaseColor.WHITE);
		cell.setBorder(0);
		cell.setBorderWidthBottom(0.1f);
		cell.setBorderColorBottom(BaseColor.BLACK);
		cell.setBorderColorTop(BaseColor.BLACK);
	}

	public static PdfPTable createOrderConfirmationMainTitleEquipmentData(List<EquipmentDetailsDTO> equipmentDetailsDTO,
			PdfWriter writer, int condition) throws DocumentException, IOException {
		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100);
		table.setWidths(new float[] { 0.3f, 0.7f, 0.5f, 0.5f, 2.1f, 0.9f });
		if (condition == 1) {
			var wrapper = new Object() {
				int ordinal = 1;
			};
			equipmentDetailsDTO.stream().filter(value -> wrapper.ordinal <= 5).forEach(equipment -> {
				table.addCell(createCellWithBodyValueText(equipment.getSales_order_details_qty().toString()));
				table.addCell(createCellWithBodyValueText(equipment.getSales_order_details_sale_type()));
				table.addCell(createCellWithBodyValueText(equipment.getSales_order_details_proc_code()));
				if (CommonUtilities.isStringNullOrBlank(equipment.getSales_order_details_modifier_1())) {
					if (CommonUtilities.isStringNullOrBlank(equipment.getSales_order_details_modifier_2())) {
						if (CommonUtilities.isStringNullOrBlank(equipment.getSales_order_details_modifier_3())) {
							if (CommonUtilities.isStringNullOrBlank(equipment.getSales_order_details_modifier_4())) {
								table.addCell(createCellWithBodyValueText(equipment.getSales_order_details_modifier_1()
										+ "," + equipment.getSales_order_details_modifier_2() + ","
										+ equipment.getSales_order_details_modifier_3() + ","
										+ equipment.getSales_order_details_modifier_4()));
							} else {
								table.addCell(createCellWithBodyValueText(equipment.getSales_order_details_modifier_1()
										+ "," + equipment.getSales_order_details_modifier_2() + ","
										+ equipment.getSales_order_details_modifier_3()));
							}
						} else {
							table.addCell(createCellWithBodyValueText(equipment.getSales_order_details_modifier_1()
									+ "," + equipment.getSales_order_details_modifier_2()));
						}
					} else {
						table.addCell(createCellWithBodyValueText(equipment.getSales_order_details_modifier_1()));
					}
				} else {
					table.addCell(createCellWithBodyValueTextWithJustified(" "));
				}

				table.addCell(createCellWithBodyValueTextWithJustified(equipment.getSales_order_details_item_name()));
				table.addCell(createCellWithBodyValueText(
						"     " + equipment.getSales_order_details_charge_amt().toString()));
				wrapper.ordinal++;
			});
		} else {
			;
			equipmentDetailsDTO.stream().skip(equipmentDetailsDTO.size() - 5).forEach(equipment -> {
				table.addCell(createCellWithBodyValueText(equipment.getSales_order_details_qty().toString()));
				table.addCell(createCellWithBodyValueText(equipment.getSales_order_details_proc_code()));
				table.addCell(createCellWithBodyValueTextWithJustified(equipment.getSales_order_details_item_name()));
				table.addCell(createCellWithBodyValueText(" "));
			});
		}
		return table;
	}

	public static PdfPTable createPriorAuthReportMainBodyParDetailsData(ParMaster pMdto) throws DocumentException {

		PdfPTable table = new PdfPTable(4);

		table.setWidthPercentage(100);
		table.setWidths(new float[] { 1.5f, 2f, 2.2f, 2.3f });

		table.addCell(createCellWithBodyValueText(pMdto.getParNo()));
		table.addCell(createCellWithBodyValueText(pMdto.getInitialDate().toString()));
		table.addCell(createCellWithBodyValueText(pMdto.getEffectiveStartDate().toString()));
		table.addCell(createCellWithBodyValueText(pMdto.getExpirationDate().toString()));

		return table;
	}
}
