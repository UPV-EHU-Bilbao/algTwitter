package exportOptions;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import db.DBKudeatzaile;

public class ExcelFile {
	static DBKudeatzaile dbk = DBKudeatzaile.getInstantzia();
	 //Blank workbook
    static XSSFWorkbook workbook = new XSSFWorkbook();
     
    //Create a blank sheet
    static XSSFSheet sheetTxio;
    static XSSFSheet sheetFav = workbook.createSheet("Faboritoak");
    static XSSFSheet sheetRt = workbook.createSheet("RT");
    static XSSFSheet sheetFllw = workbook.createSheet("Jarraituak");
    static XSSFSheet sheetFoll = workbook.createSheet("Jarraitzaileak");
	
    public void tweetFile(String user){
		ResultSet rs = dbk.execSQL("SELECT * FROM txio WHERE userIzena='"+user+"';");	
	}
    public static void main(String[] args){
    	/*
	       * TXIO SHEET EGITEKO
	       */
		txioPage();
	      /*
	       * FAV SHEET EGITEKO
	       */
	      favPage();
	      /*
	       * RT SHEET EGITEKO
	       */
	      rtPage();
	      /*
	       * FOLLOWERS SHEET EGITEKO
	       */
	      jarraitzaileakPage();
	      /*
	       * FOLLOWING SHEET EGITEKO
	       */
	      jarraituakPage();
	      
	      try
	      {
	          //Write the workbook in file system
	          FileOutputStream out = new FileOutputStream(new File("howtodoinjava_demo.xlsx"));
	          workbook.write(out);
	          out.close();
	          System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");
	      }
	      catch (Exception e) {
	          e.printStackTrace();
	      }
	}
    public static void txioPage(){
    	sheetTxio = workbook.createSheet("Txioak");
    	Row row = sheetTxio.createRow(0);
    	row.createCell(0).setCellValue("ID");
    	row.createCell(1).setCellValue("User");
    	row.createCell(2).setCellValue("Txioa");
    	String agindua = "SELECT id, nork, txioa FROM txio WHERE userIzena='ISADtaldea'";
    	int zut = 1;
    		try{
    			ResultSet rs = dbk.execSQL(agindua);
    			while(rs.next()){
        			row = sheetTxio.createRow(zut);
        			for(int err=0; err<3; err++){
        				row.createCell(err).setCellValue(rs.getString(err+1));
        			}
    			}
    		} catch (SQLException e) {
        		System.out.println("EZ DAUKAZU TXIORIK!!!");
    		}
    }
    public static void favPage(){
    	sheetFav = workbook.createSheet("FAV");
    	Row row = sheetFav.createRow(0);
    	row.createCell(0).setCellValue("ID");
    	row.createCell(1).setCellValue("User");
    	row.createCell(2).setCellValue("Txioa");
    	String agindua = "SELECT idFav, nork, txioa FROM fav WHERE userIzena='ISADtaldea'";
    	int zut = 1;
    		try{
    			ResultSet rs = dbk.execSQL(agindua);
    			while(rs.next()){
        			row = sheetFav.createRow(zut);
        			for(int err=0; err<3; err++){
        				row.createCell(err).setCellValue(rs.getString(err+1));
        			}
    			}
    		} catch (SQLException e) {
        		System.out.println("EZ DAUKAZU TXIORIK!!!");
    		}
    }
    public static void rtPage(){
    	sheetRt = workbook.createSheet("RT");
    	Row row = sheetRt.createRow(0);
    	row.createCell(0).setCellValue("ID");
    	row.createCell(1).setCellValue("User");
    	row.createCell(2).setCellValue("Txioa");
    	String agindua = "SELECT id, nork, txioa FROM rt WHERE userIzena='ISADtaldea'";
    	int zut = 1;
    		try{
    			ResultSet rs = dbk.execSQL(agindua);
    			while(rs.next()){
        			row = sheetRt.createRow(zut);
        			for(int err=0; err<3; err++){
        				row.createCell(err).setCellValue(rs.getString(err+1));
        			}
    			}
    		} catch (SQLException e) {
        		System.out.println("EZ DAUKAZU TXIORIK!!!");
    		}
    }
    public static void jarraituakPage(){
    	sheetFllw = workbook.createSheet("Jarraituak");
    	Row row = sheetFllw.createRow(0);
    	row.createCell(0).setCellValue("ID");
    	row.createCell(1).setCellValue("User");
    	String agindua = "SELECT id, userIzena FROM jarraituak WHERE userIzena='ISADtaldea'";
    	int zut = 1;
    		try{
    			ResultSet rs = dbk.execSQL(agindua);
    			while(rs.next()){
        			row = sheetFllw.createRow(zut);
        			for(int err=0; err<2; err++){
        				row.createCell(err).setCellValue(rs.getString(err+1));
        			}
    			}
    		} catch (SQLException e) {
        		System.out.println("EZ DUZU INOR JARRAITZEN!!!");
    		}
    }
    public static void jarraitzaileakPage(){
    	sheetFoll = workbook.createSheet("Jarraitzaileak");
    	Row row = sheetFoll.createRow(0);
    	row.createCell(0).setCellValue("ID");
    	row.createCell(1).setCellValue("User");
    	String agindua = "SELECT id, userIzena FROM txio WHERE userIzena='ISADtaldea'";
    	int zut = 1;
    		try{
    			ResultSet rs = dbk.execSQL(agindua);
    			while(rs.next()){
        			row = sheetFoll.createRow(zut);
        			for(int err=0; err<2; err++){
        				row.createCell(err).setCellValue(rs.getString(err+1));
        			}
    			}
    		} catch (SQLException e) {
        		System.out.println("EZ DAUKAZU TXIORIK!!!");
    		}
    }
}
