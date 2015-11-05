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
	public void tokenGorde(String token, String tokenSecret) throws SQLException{
		dbk.execSQL("INSERT INTO `twittermysql`.`token`(`accessToken`,`accessTokenSecret`)VALUES('"+token+"','"+tokenSecret+"');");
	}
	public void tokenSecretGorde(String tokenSecret) throws SQLException{
		dbk.execSQL("INSERT INTO `twittermysql`.`token`(`accessTokenSecret`)VALUES('"+tokenSecret+"');");
		//rs = dbk.execSQL("INSERT INTO token(accessTokenSecret)VALUES("+tokenSecret+");");
	}
	public void consumerGorde(String c) throws SQLException{
		dbk.execSQL("INSERT INTO `twittermysql`.`token`(`consumerKey`)VALUES('"+c+"');");
	}
	public void consumerSecretGorde(String cS) throws SQLException{
		dbk.execSQL("INSERT INTO `twittermysql`.`token`(`consumerKeySecret`)VALUES('"+cS+"');");
	}
	public String tokenBilatu(){
		String token = "";
		try {
			ResultSet rs = dbk.execSQL("SELECT accessToken FROM twittermysql.token;");
			while(rs.next()){
				//rs osoan dagoena sartuko du String -ean
				token= rs.getString("accessToken");
				System.out.println(token);
			}
		} catch (SQLException e) {
			System.out.println("EZ DA GEHITU TOKEN!!");
			//e.printStackTrace();
		}return token;
	}
	
	public String tokenSecretBilatu(){
		String token = "";
		try {
			ResultSet rs = dbk.execSQL("SELECT accessTokenSecret FROM twittermysql.token;");
			while(rs.next()){
				//rs osoan dagoena sartuko du String -ean
				token= rs.getString("accessTokenSecret");
				System.out.println(token);
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
			while(rs.next()){
				//rs osoan dagoena sartuko du String -ean
				userId = rs.getString("izena");
				System.out.println(userId);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userId;
	}
	public void sartuErab(String pizena) throws SQLException{
		dbk.execSQL("INSERT INTO `twittermysql`.`user`(`izena`) VALUES('"+pizena+"');");
	}
	
	



}
