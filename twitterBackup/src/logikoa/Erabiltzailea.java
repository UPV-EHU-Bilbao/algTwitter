package logikoa;

import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBKudeatzaile;

//Erabiltzailearen user id gordeko da
public class Erabiltzailea {

	private String userId;
	private DBKudeatzaile dbk = DBKudeatzaile.getInstantzia();
	
	private static Erabiltzailea mErab = null;
	
	private Erabiltzailea(){
		
	}
	public static synchronized Erabiltzailea getErab(){
		if(mErab==null){
			mErab = new Erabiltzailea();
		}
		return mErab;
	}
	public String getUserId() {
		return userId;
	}
	//db-an gordeta dagoen user id -a izango da erabiltzaile saia irekita dagoenenan gordeko den izena
	public void setUserId() {
		String userId = "";
		try {
			ResultSet rs = dbk.execSQL("SELECT izena FROM user;");
			int x = 0;
			while(rs.next()){
				//rs osoan dagoena sartuko du String -ean
				userId = userId+rs.getString(x);
			}
			rs.close();
			this.userId = userId;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
