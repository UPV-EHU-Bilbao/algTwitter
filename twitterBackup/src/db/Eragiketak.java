package db;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Eragiketak {
	DBKudeatzaile dbk = DBKudeatzaile.getInstantzia();
	private static Eragiketak mEragiketak = null;
	private Eragiketak(){}
	
	public static synchronized Eragiketak getEragiketak(){
		if(mEragiketak == null){
			mEragiketak = new Eragiketak();
		}return mEragiketak;
	}
	public void tokenGorde(String token, String tokenSecret){
		try {
			ResultSet rs = dbk.execSQL("INSERT INTO twitterDB.token(`"+token+"`"+"`"+tokenSecret+"`);");
		} catch (SQLException e) {
			System.out.println("EZ DA GEHITU TOKEN!!");
			e.printStackTrace();
		}
	}
	public void gordeRequest(String requestToken){
		try {
			ResultSet rs = dbk.execSQL("INSERT INTO twitterDB.token(`"+requestToken+"`);");
		} catch (SQLException e) {
			System.out.println("EZ DA GEHITU TOKEN!!");
			e.printStackTrace();
		}
	}
	public String tokenBilatu(){
		String token = "";
		try {
			ResultSet rs = dbk.execSQL("SELECT accessToken FROM token );");
			int x = 0;
			while(rs.next()){
				//rs osoan dagoena sartuko du String -ean
				token= token+rs.getString(x);
			}
		} catch (SQLException e) {
			System.out.println("EZ DA GEHITU TOKEN!!");
			e.printStackTrace();
		}return token;
	}
	public String erabiltzaileIzena(){
		String userId = "";
		try {
			ResultSet rs = dbk.execSQL("SELECT izena FROM user;");
			int x = 0;
			while(rs.next()){
				//rs osoan dagoena sartuko du String -ean
				userId = userId+rs.getString(x);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userId;
	}
	public void sartuErab(String izena){
		try {
			ResultSet rs = dbk.execSQL("INSERT INTO twitterDB.user(`"+izena+"`);");
		} catch (SQLException e) {
			System.out.println("EZ DA GEHITU TOKEN!!");
			e.printStackTrace();
		}
	}
	public String secretBilatu(){
		String secret = "";
		try {
			ResultSet rs = dbk.execSQL("SELECT accessTokenSecret FROM token );");
			int x = 0;
			while(rs.next()){
				//rs osoan dagoena sartuko du String -ean
				secret= secret+rs.getString(x);
			}
		} catch (SQLException e) {
			System.out.println("EZ DA GEHITU TOKEN!!");
			e.printStackTrace();
		}return secret;
	}



}
