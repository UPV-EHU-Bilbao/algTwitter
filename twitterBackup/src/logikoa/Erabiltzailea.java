package logikoa;

import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBKudeatzaile;
import db.Eragiketak;

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
		userId = Eragiketak.getEragiketak().erabiltzaileIzena();
		
	}
	
}
