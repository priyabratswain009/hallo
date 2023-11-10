package com.sunknowledge.dme.rcm.documentutil;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.sunknowledge.dme.rcm.domain.DeliveryItems;

import java.io.IOException;
import java.util.List;

public class ProofOfDeliveryDocumentTableBuilder {
    public static PdfPTable createDeliveryDocumentMainBodyTitlePOD(String text) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 1f});
        table.setSpacingAfter(3);
        table.addCell(createTitleRowLargeTextPOD(text));
        return table;
    }

    public static PdfPTable createDeliveryDocumentMainBodyTitlePOD1(String text) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 1f});
        table.setSpacingAfter(5);
        table.addCell(createTitleRowLargeTextPOD(text));
        return table;
    }

    public static PdfPTable createTableForTextAndInputBoxSpacedPOD(String labelText, String textLabelAlignment , int spacingAfter, String inputBoxVal) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(70);
        table.setWidths(new float[]{ 0.35f, 0.7f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingAfter(spacingAfter);
        if(textLabelAlignment.equals("LEFT")){
            table.addCell(createCellWithBodyLebelText(labelText, Element.ALIGN_LEFT, Font.BOLD));
        } else if (textLabelAlignment.equals("RIGHT")) {
            table.addCell(createCellWithBodyLebelText(labelText, Element.ALIGN_RIGHT, Font.BOLD));
        }

        table.addCell(createCellWithBodyInputBoxWithPaddingDME(inputBoxVal, "WHITE", 10));

        return table;
    }

    public static PdfPTable createDeliveryDocumentDynamicRowTableHeaderPOD( List<DeliveryItems> deliveryItems ) throws DocumentException, IOException
    {
        System.out.println("=======================SIZE==================delivery Item==================>"+deliveryItems.size());
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{ 0.2f, 0.2f, 0.2f, 0.3f, 0.1f});
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setSpacingAfter(3);

        table.addCell(createCellWithBodyInputBoxWithPaddingPOD("Item Number", "BLACK", Font.BOLD));
        table.addCell(createCellWithBodyInputBoxWithPaddingPOD("Item Serial Number", "BLACK", Font.BOLD));
        table.addCell(createCellWithBodyInputBoxWithPaddingPOD("Manufacturer Name", "BLACK", Font.BOLD));
        table.addCell(createCellWithBodyInputBoxWithPaddingPOD("Item Description", "BLACK", Font.BOLD));
        table.addCell(createCellWithBodyInputBoxWithPaddingPOD("Quantity", "BLACK", Font.BOLD));

        //Populate Order Details
        for (int i = 0; i < deliveryItems.size(); i++){
            System.out.println("=======================XXXXXXXXXXXXXXXXXXX=============================");
            String itemNumber = deliveryItems.get(i).getItemNo() != null ? deliveryItems.get(i).getItemNo() : "";
            String itemSlNo = deliveryItems.get(i).getItemSerial() != null ? deliveryItems.get(i).getItemSerial() : "";
            String itemManufacturerName = deliveryItems.get(i).getItemManufacturerName() != null ? deliveryItems.get(i).getItemManufacturerName() : "";
            String itemDesc = deliveryItems.get(i).getItemDescription() != null ? deliveryItems.get(i).getItemDescription() : "";
            String itemQuantity = deliveryItems.get(i).getItemQuantity() != null ? String.valueOf(deliveryItems.get(i).getItemQuantity()) : "";

            table.addCell(createCellWithBodyInputBoxAlignedPOD(itemNumber, "BLACK", Font.NORMAL));
            table.addCell(createCellWithBodyInputBoxAlignedPOD(itemSlNo, "BLACK", Font.NORMAL));
            table.addCell(createCellWithBodyInputBoxAlignedPOD(itemManufacturerName, "BLACK", Font.NORMAL));
            table.addCell(createCellWithBodyInputBoxAlignedPOD(itemDesc, "BLACK", Font.NORMAL));
            table.addCell(createCellWithBodyInputBoxAlignedPOD(itemQuantity, "BLACK", Font.NORMAL));
        }
        return table;
    }

    private static PdfPCell createCellWithBodyInputBoxAlignedPOD(String inputBoxVal, String inputboxBorderColor, int fontType) throws DocumentException, IOException {
        // Create a PdfPCell with black border color
        Font font = new Font(Font.FontFamily.HELVETICA, 10, fontType, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(inputBoxVal, font));
        // cell.setBorderColorTop(BaseColor.GRAY);
        if(inputboxBorderColor.equals("BLACK")){
            cell.setBorderColor(BaseColor.BLACK);
        }
        else if (inputboxBorderColor.equals("WHITE")){
            cell.setBorderColor(BaseColor.WHITE);
        }
        cell.setBorder(Rectangle.BOX); // Set the border style to a box
        //cell.setBorderWidth(2);
        cell.setBorderWidth(0.1f);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return cell;
    }

    private static PdfPCell createCellWithBodyInputBoxWithPaddingPOD(String inputBoxVal, String inputboxBorderColor, int fontType) throws DocumentException, IOException {
        // Create a PdfPCell with black border color
        Font font = new Font(Font.FontFamily.HELVETICA, 10, fontType, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(inputBoxVal, font));
        // cell.setBorderColorTop(BaseColor.GRAY);
        if(inputboxBorderColor.equals("BLACK")){
            cell.setBorderColor(BaseColor.BLACK);
        }
        else if (inputboxBorderColor.equals("WHITE")){
            cell.setBorderColor(BaseColor.WHITE);
        }
        cell.setBorder(Rectangle.BOX); // Set the border style to a box
        //cell.setBorderWidth(2);
        cell.setBorderWidth(0.1f);
        return cell;
    }

    private static PdfPCell createTitleRowLargeTextPOD(String text)
    {
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPaddingTop(3f);
        cell.setPaddingLeft(1f);
        cell.setPaddingBottom(3f);
        cell.setBorder(0);
        cell.setBorderWidthBottom(0.55f);
        cell.setBackgroundColor(BaseColor.WHITE);
        cell.setBorderColor(BaseColor.WHITE);
        cell.setBorderColorBottom(BaseColor.BLACK);
        return cell;
    }

    private static PdfPCell createCellWithBodyLebelText(String text) {
        Font font = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallSubTitle5Style(cell);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private static PdfPCell createCellWithBodyInputBoxWithPaddingDME(String inputBoxVal, String inputboxBorderColor, int fontSize) throws DocumentException, IOException {
        // Create a PdfPCell with black border color
        Font font = new Font(Font.FontFamily.HELVETICA, fontSize, Font.NORMAL, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(inputBoxVal, font));
        // cell.setBorderColorTop(BaseColor.GRAY);
        if(inputboxBorderColor.equals("BLACK")){
            cell.setBorderColor(BaseColor.BLACK);
        }
        else if (inputboxBorderColor.equals("WHITE")){
            cell.setBorderColor(BaseColor.WHITE);
        }
        cell.setBorderWidth(0.1f);
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

    private static PdfPCell createCellWithBodyLebelText(String text, int elementAlignement, int fontType) {
        Font font = new Font(Font.FontFamily.HELVETICA, 10, fontType, BaseColor.BLACK);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        titleRowTextSmallSubTitle5Style(cell, elementAlignement);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private static void titleRowTextSmallSubTitle5Style(PdfPCell cell, int elementAlignement) {
        cell.setHorizontalAlignment(elementAlignement);
        cell.setPaddingTop(3f);
        cell.setPaddingLeft(1f);
        cell.setPaddingBottom(5f);
        cell.setBackgroundColor(BaseColor.WHITE);
        cell.setBorder(0);
        cell.setBorderWidthBottom(0.1f);
        cell.setBorderColorBottom(BaseColor.BLACK);
        cell.setBorderColorTop(BaseColor.BLACK);
    }
}
