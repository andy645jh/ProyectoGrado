package com.test.ctrl;

import groovy.sql.DataSet;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.engine.type.HorizontalAlignEnum;
import net.sf.jasperreports.engine.type.ModeEnum;
import net.sf.jasperreports.engine.type.PositionTypeEnum;

/**
 * Uses the Jasper Reports API to dynamically build columns and add them to the
 * Report
 */
public class DynamicReportBuilder {

    //The prefix used in defining the field name that is later used by the JasperFillManager

    public static final String COL_EXPR_PREFIX = "col";

    // The prefix used in defining the column header name that is later used by the JasperFillManager
    public static final String COL_HEADER_EXPR_PREFIX = "header";

    // The page width for a page in portrait mode with 10 pixel margins
    private final static int TOTAL_PAGE_WIDTH = 545;

    // The whitespace between columns in pixels
    private final static int SPACE_BETWEEN_COLS = 5;

    // The height in pixels of an element in a row and column
    private final static int COLUMN_HEIGHT = 12;

    // The total height of the column header or detail band
    private final static int BAND_HEIGHT = 15;

    // The left and right margin in pixels
    private final static int MARGIN = 10;

    // The JasperDesign object is the internal representation of a report
    private JasperDesign jasperDesign;

    // The number of columns that are to be displayed
    private int numColumns;

    public DynamicReportBuilder(JasperDesign jasperDesign, int numColumns) {
        this.jasperDesign = jasperDesign;
        this.numColumns = numColumns;
    }

    public void initConfig() throws JRException {
        //JasperDesign
        JasperDesign jasperDesign = new JasperDesign();
        jasperDesign.setName("The dynamically generated report");
        jasperDesign.setPageWidth(595);
        jasperDesign.setPageHeight(842);
        jasperDesign.setColumnWidth(515);
        jasperDesign.setColumnSpacing(0);
        jasperDesign.setLeftMargin(40);
        jasperDesign.setRightMargin(40);
        jasperDesign.setTopMargin(50);
        jasperDesign.setBottomMargin(50);

        JRDesignDataset dataset = new JRDesignDataset(true);
        JRDesignQuery q = new JRDesignQuery();
        
        
        //Fields
        JRDesignField field = new JRDesignField();
        field.setName("edad");
        field.setValueClass(java.lang.Integer.class);
        jasperDesign.addField(field);
        dataset.addField(field);
        
        field = new JRDesignField();
        field.setName("nombre");
        field.setValueClass(java.lang.String.class);
        jasperDesign.addField(field);
        dataset.addField(field);
        
        /*field = new JRDesignField();
        field.setName("LastName");
        field.setValueClass(java.lang.String.class);
        jasperDesign.addField(field);*/

    //some code
        //Detail
        JRDesignBand band = new JRDesignBand();
        band.setHeight(40);

        JRDesignStaticText staticText = new JRDesignStaticText();
        staticText.setX(0);
        staticText.setY(0);
        staticText.setWidth(60);
        staticText.setHeight(20);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
        //staticText.setStyle(boldStyle);
        staticText.setText("ID: ");
        staticText.getLineBox().getLeftPen().setLineWidth(1);
        staticText.getLineBox().getTopPen().setLineWidth(1);
        staticText.getLineBox().setLeftPadding(10);
        band.addElement(staticText);

        JRDesignTextField textField = new JRDesignTextField();
        textField.setX(60);
        textField.setY(0);
        textField.setWidth(200);
        textField.setHeight(20);
        textField.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
        //textField.setStyle(normalStyle);
        JRDesignExpression expression = new JRDesignExpression();
        expression.setValueClass(java.lang.Integer.class);
        expression.setText("$F{edad}");
        textField.setExpression(expression);
        textField.getLineBox().getTopPen().setLineWidth(1);
        textField.getLineBox().getRightPen().setLineWidth(1);
        textField.getLineBox().setLeftPadding(10);
        band.addElement(textField);

        staticText = new JRDesignStaticText();
        staticText.setX(0);
        staticText.setY(20);
        staticText.setWidth(60);
        staticText.setHeight(20);
        staticText.setMode(ModeEnum.OPAQUE);
        staticText.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
        //staticText.setStyle(boldStyle);
        staticText.setText("Name: ");
        staticText.getLineBox().getLeftPen().setLineWidth(1);
        staticText.getLineBox().getBottomPen().setLineWidth(1);
        staticText.getLineBox().setLeftPadding(10);
        band.addElement(staticText);

        textField = new JRDesignTextField();
        textField.setStretchWithOverflow(true);
        textField.setX(60);
        textField.setY(20);
        textField.setWidth(200);
        textField.setHeight(20);
        textField.setPositionType(PositionTypeEnum.FLOAT);
        //textField.setStyle(normalStyle);
        expression = new JRDesignExpression();
        expression.setValueClass(java.lang.String.class);
        expression.setText("$F{nombre}");
        textField.setExpression(expression);
        textField.getLineBox().getRightPen().setLineWidth(1);
        textField.getLineBox().getBottomPen().setLineWidth(1);
        textField.getLineBox().setLeftPadding(10);
        band.addElement(textField);
        
        ((JRDesignSection) jasperDesign.getDetailSection()).addBand(band);
        //jasperDesign.addDataset(dataset);
    }

    public void addDynamicColumns() throws JRException {

        JRDesignBand detailBand = new JRDesignBand();
        JRDesignBand headerBand = new JRDesignBand();

        JRDesignStyle normalStyle = getNormalStyle();
        JRDesignStyle columnHeaderStyle = getColumnHeaderStyle();
        jasperDesign.addStyle(normalStyle);
        jasperDesign.addStyle(columnHeaderStyle);

        int xPos = MARGIN;
        int columnWidth = (TOTAL_PAGE_WIDTH - (SPACE_BETWEEN_COLS * (numColumns - 1))) / numColumns;

        for (int i = 0; i < numColumns; i++) {

            // Create a Column Field
            JRDesignField field = new JRDesignField();
            field.setName(COL_EXPR_PREFIX + i);
            field.setValueClass(java.lang.String.class);
            jasperDesign.addField(field);

            // Create a Header Field
            JRDesignField headerField = new JRDesignField();
            headerField.setName(COL_HEADER_EXPR_PREFIX + i);
            headerField.setValueClass(java.lang.String.class);
            jasperDesign.addField(headerField);

            // Add a Header Field to the headerBand
            headerBand.setHeight(BAND_HEIGHT);
            JRDesignTextField colHeaderField = new JRDesignTextField();
            colHeaderField.setX(xPos);
            colHeaderField.setY(2);
            colHeaderField.setWidth(columnWidth);
            colHeaderField.setHeight(COLUMN_HEIGHT);
            colHeaderField.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
            colHeaderField.setStyle(columnHeaderStyle);
            JRDesignExpression headerExpression = new JRDesignExpression();
            headerExpression.setValueClass(java.lang.String.class);
            headerExpression.setText("$F{" + COL_HEADER_EXPR_PREFIX + i + "}");
            colHeaderField.setExpression(headerExpression);
            headerBand.addElement(colHeaderField);

            // Add text field to the detailBand
            detailBand.setHeight(BAND_HEIGHT);
            JRDesignTextField textField = new JRDesignTextField();
            textField.setX(xPos);
            textField.setY(2);
            textField.setWidth(columnWidth);
            textField.setHeight(COLUMN_HEIGHT);
            textField.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
            textField.setStyle(normalStyle);
            JRDesignExpression expression = new JRDesignExpression();
            expression.setValueClass(java.lang.String.class);
            expression.setText("$F{" + COL_EXPR_PREFIX + i + "}");
            textField.setExpression(expression);
            detailBand.addElement(textField);

            xPos = xPos + columnWidth + SPACE_BETWEEN_COLS;
        }

        jasperDesign.setColumnHeader(headerBand);
        ((JRDesignSection) jasperDesign.getDetailSection()).addBand(detailBand);
    }

    private JRDesignStyle getNormalStyle() {
        JRDesignStyle normalStyle = new JRDesignStyle();
        normalStyle.setName("Sans_Normal");
        normalStyle.setDefault(true);
        normalStyle.setFontName("SansSerif");
        normalStyle.setFontSize(8);
        normalStyle.setPdfFontName("Helvetica");
        normalStyle.setPdfEncoding("Cp1252");
        normalStyle.setPdfEmbedded(false);
        return normalStyle;
    }

    private JRDesignStyle getColumnHeaderStyle() {
        JRDesignStyle columnHeaderStyle = new JRDesignStyle();
        columnHeaderStyle.setName("Sans_Header");
        columnHeaderStyle.setDefault(false);
        columnHeaderStyle.setFontName("SansSerif");
        columnHeaderStyle.setFontSize(10);
        columnHeaderStyle.setBold(true);
        columnHeaderStyle.setPdfFontName("Helvetica");
        columnHeaderStyle.setPdfEncoding("Cp1252");
        columnHeaderStyle.setPdfEmbedded(false);
        return columnHeaderStyle;
    }

}
