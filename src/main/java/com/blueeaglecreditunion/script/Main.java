package com.blueeaglecreditunion.script;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tools.ant.DirectoryScanner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class Main {


    /**
     * Connection to the database.
     */
    private static Connection connection = null;
    private static ArrayList<MemberInformation> memberInformationArrayList = new ArrayList<MemberInformation>();
    private static HashMap<String,String> xlsxMap = new HashMap<>();

    /**
     * Entry point to run the functions
     * @throws Exception
     */
    private static void run() throws Exception {



        DirectoryScanner scanner = new DirectoryScanner();
        scanner.setIncludes(new String[]{"Non-User*"});
        scanner.setBasedir("M:\\Marketing_Plan\\Non-User Detail Report");
        scanner.setCaseSensitive(false);
        scanner.scan();

        String[] files = scanner.getIncludedFiles();

        File myFile = new File("M:\\Marketing_Plan\\Non-User Detail Report\\" + files[0]);


        System.out.println("Grabbing data from keystone...");
        ArrayList<MemberInformation> xlsxData = getXLSXFile(myFile); // grabs the data inside of the xlsx

       // System.out.println(xlsxData.size());

        System.out.println("Gathering data from xlsx file...");
        getMembersInformation();

        //System.out.println(xlsxMap.size());

        CreateSheet createSheet = new CreateSheet();

        System.out.println("Parsing data...");
        parseMemberCreditCardNumber(xlsxData); //filter to get members full information.

       // System.out.println(memberInformationArrayList.size());


        System.out.println("Creating workbook...");
        createSheet.createWorkBook(memberInformationArrayList);



    }

    /**
     * Parses through the members credit card numbers and matches if they exist in the csv file provided.
     * @param cardNumbers
     */
    public static void parseMemberCreditCardNumber(ArrayList<MemberInformation> cardNumbers) {

        for (MemberInformation memberInformation : cardNumbers) {

            MemberInformation member = new MemberInformation();

            if (xlsxMap.containsKey(memberInformation.getCardNumber())) {

                member.setCardPrefix(memberInformation.getCardPrefix());
                member.setCardNumber(memberInformation.getCardNumber());
                member.setCardExpirationDate(memberInformation.getCardExpirationDate());
                member.setMemberNumber(memberInformation.getMemberNumber());
                member.setPrimaryAccountNumber(memberInformation.getPrimaryAccountNumber());
                member.setMemberName(memberInformation.getMemberName());
                member.setMemberPhoneNumber(memberInformation.getMemberPhoneNumber());
                member.setPrimaryAddressLineOne(memberInformation.getPrimaryAddressLineOne());
                member.setPrimaryAddressLineTwo(memberInformation.getPrimaryAddressLineTwo());
                member.setCity(memberInformation.getCity());
                member.setState(memberInformation.getState());
                member.setPostalCode(memberInformation.getPostalCode());
                member.setPrimaryCountry(memberInformation.getPrimaryCountry());
                member.setLastActivityDate(memberInformation.getLastActivityDate());
                member.setEmail(xlsxMap.get(memberInformation.getCardNumber()));

                memberInformationArrayList.add(member);
            }
        }
    }

    /**
     * Gets and stores the members information from getMemberCardData() query.
     * @return - returns Arraylist of member into
     * @throws Exception
     */
    public static void getMembersInformation() throws Exception{

        String sql = SQLList.getMemberCardData();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next())
        {
            xlsxMap.put(resultSet.getString(1),resultSet.getString(2));
        }
        statement.close();
    }

    /**
     * Read in a XLSX file and store it in a hashmap
     * @param myFile
     * @throws IOException
     */
    public static ArrayList<MemberInformation> getXLSXFile(File myFile) throws IOException {

        FileInputStream fis = new FileInputStream(myFile);

        ArrayList<MemberInformation> share_list = new ArrayList<MemberInformation>();


        // Finds the workbook instance for XLSX file
        XSSFWorkbook myWorkBook = new XSSFWorkbook (fis);

        // Return first sheet from the XLSX workbook
        XSSFSheet mySheet = myWorkBook.getSheetAt(0);

        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = mySheet.iterator();

        // Traversing over each row of XLSX file
        while (rowIterator.hasNext()) {
            MemberInformation xlsxFile = new MemberInformation();

            Row row = rowIterator.next();

            xlsxFile.setCardPrefix(row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString());
            xlsxFile.setCardNumber(row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString());
            xlsxFile.setCardExpirationDate(row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString());
            xlsxFile.setMemberNumber(row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString());
            xlsxFile.setPrimaryAccountNumber(row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString());


            xlsxFile.setMemberName(row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString());
            xlsxFile.setMemberPhoneNumber(row.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString());
            xlsxFile.setPrimaryAddressLineOne(row.getCell(7, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString());
            xlsxFile.setPrimaryAddressLineTwo(row.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString());
            xlsxFile.setCity(row.getCell(9, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString());
            xlsxFile.setState(row.getCell(10, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString());
            xlsxFile.setPostalCode(row.getCell(11, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString());
            xlsxFile.setPrimaryCountry(row.getCell(12, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString());

            xlsxFile.setLastActivityDate(row.getCell(13, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString());


            share_list.add(xlsxFile);

        }

            share_list.sort(Comparator.comparing(MemberInformation::getCardNumber)); //sorting by loginID

            return share_list;

    }


    /**
     * Established a connection to the database using DB2 Driver then runs the program.
     * @param args
     * @throws Throwable
     */
    public static void main(String[] args) throws Throwable {

        String jdbcDriver = "com.ibm.db2.jcc.DB2Driver";

        Class.forName(jdbcDriver);
        System.out.println("Loaded the JDBC driver");

        //establishes the connection to the database.
        connection = DriverManager.getConnection(myPassword.getDatabaseURL,
                myPassword.getUsername(), myPassword.getPassword());
        System.out.println("Created a JDBC connection to the data source\n");

        run(); // runs the program
        //closes out the connection to DB
        connection.close();
    }
}
