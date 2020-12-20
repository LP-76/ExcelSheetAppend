package com.blueeaglecreditunion.script;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Creates sheets with the data passed into createWorkBook function.
 */

public class CreateSheet {

    private static XSSFWorkbook workbook = new XSSFWorkbook();

    public CreateSheet(){

    }

    public CreateSheet( XSSFWorkbook workbook){
        this.workbook = workbook;
    }

    public XSSFWorkbook getWorkbook() {
        return workbook;
    }


    public void setWorkbook(XSSFWorkbook workbook) {
        this.workbook = workbook;
    }

    /**
     * Creates a workbook and sheet.
     * @param memberInformationArrayList - array list of member information
     * @throws IOException
     */
    public static void createWorkBook(List<MemberInformation> memberInformationArrayList) throws IOException {
        XSSFSheet spreadsheet = workbook.createSheet("TJ Results");

        int rowIndex = 1;
        XSSFRow row = spreadsheet.createRow(0);
        XSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("Card prefix");
        cell = row.createCell(1);
        cell.setCellValue("Card Number");
        cell = row.createCell(2);
        cell.setCellValue("Card Expiration Date");

        cell = row.createCell(3);
        cell.setCellValue("Member Number");
        cell = row.createCell(4);
        cell.setCellValue("Primary Account Number");

        cell = row.createCell(5);
        cell.setCellValue("Name");
        cell = row.createCell(6);
        cell.setCellValue("Primary Phone Number");
        cell = row.createCell(7);
        cell.setCellValue("Primary Address Line 01");
        cell = row.createCell(8);
        cell.setCellValue("Primary Address Line 02");
        cell = row.createCell(9);
        cell.setCellValue("Primary City");
        cell = row.createCell(10);
        cell.setCellValue("Primary State");
        cell = row.createCell(11);
        cell.setCellValue("Primary ZIP Code");
        cell = row.createCell(12);
        cell.setCellValue("Primary Country");
        cell = row.createCell(13);
        cell.setCellValue("Date Last Activity");
        cell = row.createCell(14);
        cell.setCellValue("Email");



        for (MemberInformation memberInformation : memberInformationArrayList) {
            row = spreadsheet.createRow(rowIndex);

            cell = row.createCell(0);
            cell.setCellValue(memberInformation.getCardPrefix());
            cell = row.createCell(1);
            cell.setCellValue(memberInformation.getCardNumber());
            cell = row.createCell(2);
            cell.setCellValue(memberInformation.getCardExpirationDate());
            cell = row.createCell(3);
            cell.setCellValue(memberInformation.getMemberNumber());
            cell = row.createCell(4);
            cell.setCellValue(memberInformation.getPrimaryAccountNumber());

            cell = row.createCell(5);
            cell.setCellValue(memberInformation.getMemberName());
            cell = row.createCell(6);
            cell.setCellValue(memberInformation.getMemberPhoneNumber());
            cell = row.createCell(7);
            cell.setCellValue(memberInformation.getPrimaryAddressLineOne());
            cell = row.createCell(8);
            cell.setCellValue(memberInformation.getPrimaryAddressLineTwo());
            cell = row.createCell(9);
            cell.setCellValue(memberInformation.getCity());
            cell = row.createCell(10);
            cell.setCellValue(memberInformation.getState());
            cell = row.createCell(11);
            cell.setCellValue(memberInformation.getPostalCode());
            cell = row.createCell(12);
            cell.setCellValue(memberInformation.getPrimaryCountry());
            cell = row.createCell(13);
            cell.setCellValue(memberInformation.getLastActivityDate());
            cell = row.createCell(14);
            cell.setCellValue(memberInformation.getEmail());

            rowIndex++;

        }

        // CHANGES THE WIDTH OF THE COLUMN
        for (int i = 0; i < memberInformationArrayList.size() ; i++) {
            spreadsheet.autoSizeColumn(i);
        }


        String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());


        FileOutputStream out = new FileOutputStream(new File("M:\\Marketing_Plan\\Non-User Detail Report\\" + timeStamp+ " Non-User Detail - Detail.xlsx"));

        workbook.write(out);
        out.close();
        System.out.println("File Created.");

    }
}
