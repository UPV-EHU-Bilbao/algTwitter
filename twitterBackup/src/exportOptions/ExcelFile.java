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
	DBKudeatzaile dbk = DBKudeatzaile.getInstantzia();
	private static ExcelFile mExcel = null;
	 //Blank workbook
   private XSSFWorkbook workbook = new XSSFWorkbook();
     
    //Create a blank sheet
    private XSSFSheet sheetTxio;
    private XSSFSheet sheetFav ;
    private XSSFSheet sheetFllw ;
    private XSSFSheet sheetFoll ;
    
    private ExcelFile(){}
    
    public static synchronized ExcelFile getMExcel(){
    	if(mExcel == null){
    		mExcel = new ExcelFile();
    	}return mExcel;
    }
	
    public void createExcel(String user){
    	String filePath = Export.getExport().saveWin();
    	/*
	       * TXIO SHEET EGITEKO
	       */
		txioPage(user);
	      /*
	       * FAV SHEET EGITEKO
	       */
	      favPage(user);
	      
	      /*
	       * FOLLOWERS SHEET EGITEKO
	       */
	      jarraitzaileakPage(user);
	      /*
	       * FOLLOWING SHEET EGITEKO
	       */
	      jarraituakPage(user);
	      
	      String  filename = "backupTwitter.xls";
	      try
	      {
	          //Write the workbook in file system
	          FileOutputStream out = new FileOutputStream(filePath+"//"+filename);
	          workbook.write(out);
	         // out.close();
	          //String name = "";
		      //Export.getExport().fileSave(filename, name);

	          System.out.println(filename+" written successfully on disk.");
	      }
	      catch (Exception e) {
	          e.printStackTrace();
	      }
	    //  Export.getExport().fileSave();
	}
    public  void txioPage(String user){
    	sheetTxio = workbook.createSheet("Txioak");
    	Row row = sheetTxio.createRow(0);
    	row.createCell(0).setCellValue("ID");
    	row.createCell(1).setCellValue("User");
    	row.createCell(2).setCellValue("Txioa");
    	String agindua = "SELECT id, nork, txioa FROM txio WHERE userIzena='"+user+"';";
    	int zut = 1;
    		try{
    			ResultSet rs = dbk.execSQL(agindua);
    			while(rs.next()){
        			row = sheetTxio.createRow(zut);
        			for(int err=0; err<3; err++){
        				row.createCell(err).setCellValue(rs.getString(err+1));
        			}
        			zut++;
    			}
    		} catch (SQLException e) {
        		System.out.println("EZ DAUKAZU TXIORIK!!!");
    		}
    }
    public void favPage(String user){
    	sheetFav = workbook.createSheet("FAV");
    	Row row = sheetFav.createRow(0);
    	row.createCell(0).setCellValue("ID");
    	row.createCell(1).setCellValue("User");
    	row.createCell(2).setCellValue("Txioa");
    	String agindua = "SELECT idFav, nork, txioa FROM fav WHERE userIzena='"+user+"';";
    	int zut = 1;
    		try{
    			ResultSet rs = dbk.execSQL(agindua);
    			while(rs.next()){
        			row = sheetFav.createRow(zut);
        			for(int err=0; err<3; err++){
        				row.createCell(err).setCellValue(rs.getString(err+1));
        			}zut++;
    			}
    		} catch (SQLException e) {
        		System.out.println("EZ DAUKAZU TXIORIK!!!");
    		}
    }
   
    public void jarraituakPage(String user){
    	sheetFllw = workbook.createSheet("Jarraituak");
    	Row row = sheetFllw.createRow(0);
    	row.createCell(0).setCellValue("ID");
    	row.createCell(1).setCellValue("User");
    	String agindua = "SELECT id, userIzena FROM jarraituak WHERE userId='"+user+"';";
    	int zut = 1;
    		try{
    			ResultSet rs = dbk.execSQL(agindua);
    			while(rs.next()){
        			row = sheetFllw.createRow(zut);
        			for(int err=0; err<3; err++){
        				row.createCell(err).setCellValue(rs.getString(err+1));
        			}zut++;
    			}
    		} catch (SQLException e) {
        		System.out.println("EZ DUZU INOR JARRAITZEN!!!");
    		}
    }
    public void jarraitzaileakPage(String user){
    	sheetFoll = workbook.createSheet("Jarraitzaileak");
    	Row row = sheetFoll.createRow(0);
    	row.createCell(0).setCellValue("ID");
    	row.createCell(1).setCellValue("User");
    	String agindua = "SELECT id, userIzena FROM jarraitzaileak WHERE userId='"+user+"';";
    	int zut = 1;
    		try{
    			ResultSet rs = dbk.execSQL(agindua);
    			while(rs.next()){
        			row = sheetFoll.createRow(zut);
        			for(int err=0; err<2; err++){
        				row.createCell(err).setCellValue(rs.getString(err+1));
        				
        			}zut++;
    			}
    		} catch (SQLException e) {
        		System.out.println("EZ DAUKAZU TXIORIK!!!");
    		}
    }
}
