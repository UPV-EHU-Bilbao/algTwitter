package exportOptions;

import java.sql.ResultSet;
import db.DBKudeatzaile;

public class ExcelFile {
	DBKudeatzaile dbk = DBKudeatzaile.getInstantzia();

	public void tweetFile(String user){
		ResultSet rs = dbk.execSQL("SELECT * FROM txio WHERE userIzena='"+user+"';");
		
		
	}
}
