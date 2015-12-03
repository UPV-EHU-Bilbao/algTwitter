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
    static XSSFSheet sheetTxio = workbook.createSheet("Txio");
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
		Map<String, Object[]> txiodata = new TreeMap<String, Object[]>();
		int posizioa = 1;
		ArrayList<Object []> datuak = txioLortu();
		datuak.get(i)[0];
		for(int iT=0; iT<datuak.size(); iT++){
			txiodata.put(, new Object[]{)
		}
	      txiodata.put("1", new Object[] {"Id", "User", "Txio"});
	      txiodata.put("2", new Object[] {1, "Amit", "Shukla"});
	      txiodata.put("3", new Object[] {2, "Lokesh", "Gupta"});
	      txiodata.put("4", new Object[] {3, "John", "Adwards"});
	      txiodata.put("5", new Object[] {4, "Brian", "Schultz"});
	      /*
	       * TXIO SHEET EGITEKO
	       */
	      Set<String> keysetxio = txiodata.keySet();
	      int rownum = 0;
	      for (String key : keysetxio)
	      {
	          Row row = sheetTxio.createRow(rownum++);
	          Object [] objArr = txiodata.get(key);
	          int cellnum = 0;
	          for (Object obj : objArr)
	          {
	             Cell cell = row.createCell(cellnum++);
	             if(obj instanceof String)
	                  cell.setCellValue((String)obj);
	              else if(obj instanceof Integer)
	                  cell.setCellValue((Integer)obj);
	          }
	      }
	      
	      /*
	       * FAV SHEET EGITEKO
	       */
	      Map<String, Object[]> favdata = new TreeMap<String, Object[]>();
	      favdata.put("1", new Object[] {"Id", "User", "Txio"});
	      favdata.put("2", new Object[] {1, "Amit", "Shukla"});
	      favdata.put("3", new Object[] {2, "Lokesh", "Gupta"});
	      favdata.put("4", new Object[] {3, "John", "Adwards"});
	      favdata.put("5", new Object[] {4, "Brian", "Schultz"});
	      Set<String> keysetfAV = favdata.keySet();
	      int rownumfav = 0;
	      for (String key : keysetfAV)
	      {
	          Row row = sheetFav.createRow(rownumfav++);
	          Object [] objArr = favdata.get(key);
	          int cellnum = 0;
	          for (Object obj : objArr)
	          {
	             Cell cell = row.createCell(cellnum++);
	             if(obj instanceof String)
	                  cell.setCellValue((String)obj);
	              else if(obj instanceof Integer)
	                  cell.setCellValue((Integer)obj);
	          }
	      }
	      /*
	       * RT SHEET EGITEKO
	       */
	      Map<String, Object[]> rtdata = new TreeMap<String, Object[]>();
	      rtdata.put("1", new Object[] {"Id", "User", "Txio"});
	      rtdata.put("2", new Object[] {1, "Amit", "Shukla"});
	      rtdata.put("3", new Object[] {2, "Lokesh", "Gupta"});
	      rtdata.put("4", new Object[] {3, "John", "Adwards"});
	      rtdata.put("5", new Object[] {4, "Brian", "Schultz"});
	      Set<String> keysetRT = rtdata.keySet();
	      int rownumRT = 0;
	      for (String key : keysetRT)
	      {
	          Row row = sheetRt.createRow(rownumRT++);
	          Object [] objArr = rtdata.get(key);
	          int cellnum = 0;
	          for (Object obj : objArr)
	          {
	             Cell cell = row.createCell(cellnum++);
	             if(obj instanceof String)
	                  cell.setCellValue((String)obj);
	              else if(obj instanceof Integer)
	                  cell.setCellValue((Integer)obj);
	          }
	      }
	      /*
	       * FOLLOWERS SHEET EGITEKO
	       */
	      Map<String, Object[]> followersdata = new TreeMap<String, Object[]>();
	      followersdata.put("1", new Object[] {"Id", "User"});
	      followersdata.put("2", new Object[] {1, "Amit"});
	      followersdata.put("3", new Object[] {2, "Lokesh"});
	      followersdata.put("4", new Object[] {3, "John"});
	      followersdata.put("5", new Object[] {4, "Brian"});
	      Set<String> keysetfollw = favdata.keySet();
	      int rownumfollower = 0;
	      for (String key : keysetfollw)
	      {
	          Row row = sheetFllw.createRow(rownumfollower++);
	          Object [] objArr = followersdata.get(key);
	          int cellnum = 0;
	          for (Object obj : objArr)
	          {
	             Cell cell = row.createCell(cellnum++);
	             if(obj instanceof String)
	                  cell.setCellValue((String)obj);
	              else if(obj instanceof Integer)
	                  cell.setCellValue((Integer)obj);
	          }
	      }
	      /*
	       * FOLLOWING SHEET EGITEKO
	       */
	      Map<String, Object[]> followingsdata = new TreeMap<String, Object[]>();
	      followingsdata.put("1", new Object[] {"Id", "User"});
	      followingsdata.put("2", new Object[] {1, "Amit"});
	      followingsdata.put("3", new Object[] {2, "Lokesh"});
	      followingsdata.put("4", new Object[] {3, "John"});
	      followingsdata.put("5", new Object[] {4, "Brian"});
	      Set<String> keysetfollwing = favdata.keySet();
	      int rownumfollowing = 0;
	      for (String key : keysetfollwing)
	      {
	          Row row = sheetFoll.createRow(rownumfollowing++);
	          Object [] objArr = followingsdata.get(key);
	          int cellnum = 0;
	          for (Object obj : objArr)
	          {
	             Cell cell = row.createCell(cellnum++);
	             if(obj instanceof String)
	                  cell.setCellValue((String)obj);
	              else if(obj instanceof Integer)
	                  cell.setCellValue((Integer)obj);
	          }
	      }
	      
	      try
	      {
	          //Write the workbook in file system
	          FileOutputStream out = new FileOutputStream(new File("howtodoinjava_demo.xlsx"));
	          workbook.write(out);
	          out.close();
	          System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");
	      }
	      catch (Exception e)
	      {
	          e.printStackTrace();
	      }
	}
    public static ArrayList<Object[]> txioLortu(){
    	ArrayList<Object []> txioak = new ArrayList<>();
    	String agindua = "SELECT * FROM txio WHERE userIzena='ISADtaldea'";
    	try {
			ResultSet rs = dbk.execSQL(agindua);
			while(rs.next()){
				Object [] elem = new Object [3];
				elem[0] = rs.getString("id");
				elem[1] = rs.getString("nork");
				elem[2]= rs.getString("txioa");
				txioak.add(elem);
				}
		} catch (SQLException e) {
			System.out.println("EZ DA GEHITU TOKEN!!");
			e.printStackTrace();
		}
    	return txioak;
    }
}
