package com.sunknowledge.dme.rcm.documentutil;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;

import java.io.IOException;
import java.util.HashMap;

public class CellFieldEvent implements PdfPCellEvent {
    protected PdfFormField radioGroup;
    protected String elementValue;
    protected String elementType;
    protected boolean isChecked;
    HashMap<String, String> map = new HashMap<>();

    public CellFieldEvent(PdfFormField radioGroup, String elementValue, String elementType, boolean isChecked) {
        this.radioGroup = radioGroup;
        this.elementValue = elementValue;
        this.elementType = elementType;
        this.isChecked = isChecked;
    }

    public void cellLayout(PdfPCell cell, Rectangle rectangle, PdfContentByte[] canvases) {
        final PdfWriter writer = canvases[0].getPdfWriter();

        // define the coordinates of the middle
        float x = (rectangle.getLeft() + rectangle.getRight()) / 2;
        float y = (rectangle.getTop() + rectangle.getBottom()) / 2;

        try {
            if (elementType.equals("checkbox")) {
                // define the position of a check box that measures 10 by 10
                //Resize postion of checkbox
                Rectangle rect = new Rectangle(x - 5, y - 5, x + 5, y + 5);
                //Rectangle rect = new Rectangle(x - 10, y - 5, x , y + 5);
                RadioCheckField pdfElementObj = new RadioCheckField(writer, rect, elementValue, elementValue);
                pdfElementObj.setCheckType(RadioCheckField.TYPE_CHECK);
                pdfElementObj.setBorderWidth(BaseField.BORDER_WIDTH_THIN);
                pdfElementObj.setBorderColor(BaseColor.BLACK);
                pdfElementObj.setBackgroundColor(BaseColor.WHITE);
                //if (map.containsKey(elementValue)) {
                pdfElementObj.setChecked(isChecked);
                //}
                writer.addAnnotation(pdfElementObj.getCheckField());
            } else if(elementType.equals("radioButton")){
                //Resize postion of radio button
                Rectangle rect = new Rectangle(x - 10, y - 5, x , y + 5);
                RadioCheckField pdfElementObj = new RadioCheckField(writer, rect, elementValue, elementValue);
                //if (map.containsKey(elementValue)) {
                pdfElementObj.setChecked(isChecked);
                //}
                pdfElementObj.setBorderColor(BaseColor.BLACK);
                radioGroup.addKid(pdfElementObj.getRadioField());
            }
            else if(elementType.equals("both")){
                Rectangle rect = new Rectangle(x - 8, y - 5, x + 2 , y + 5);
                RadioCheckField pdfElementObj = new RadioCheckField(writer, rect, elementValue, elementValue);
                pdfElementObj.setCheckType(RadioCheckField.TYPE_CHECK);
                pdfElementObj.setBorderWidth(BaseField.BORDER_WIDTH_THIN);
                pdfElementObj.setBorderColor(BaseColor.BLACK);
                pdfElementObj.setBackgroundColor(BaseColor.WHITE);
                //if (map.containsKey(elementValue)) {
                pdfElementObj.setChecked(isChecked);
                // }
                writer.addAnnotation(pdfElementObj.getCheckField());
            }
        } catch (final IOException ioe) {
            throw new ExceptionConverter(ioe);
        } catch (final DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }
}
