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
	public void tokenGorde(String token, String tokenSecret, String user) throws SQLException{
		dbk.execSQL("INSERT INTO `twittermysql`.`token`(`accessToken`,`accessTokenSecret`,`user`)VALUES('"+token+"','"+tokenSecret+"','"+user+"');");
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
	public String tokenBilatu(String izena){
		String token = "";
		try {
			ResultSet rs = dbk.execSQL("SELECT * FROM `twittermysql`.`token` WHERE user='"+izena+"';");
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
	
	public String tokenSecretBilatu(String izena){
		String token = "";
		try {
			ResultSet rs = dbk.execSQL("SELECT * FROM `twittermysql`.`token` WHERE user='"+izena+"';");
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
	
	
	public void sartuErab(String pizena) throws SQLException{
		dbk.execSQL("INSERT INTO `twittermysql`.`user`(`izena`) VALUES('"+pizena+"');");
	}
	
	
	
	//twitter -EKO FAV,RT, HOMETIMELINE, FOLLOWERS & FOLLOWING
	//datuak datu basean gordetzeko MySQL sententziak
	public void favGorde(String tweet){
		
		//dbk.execSQL("INSERT INTO `twittermysql`.`fav`(`accessToken`,`accessTokenSecret`,`user`)VALUES('"+token+"','"+tokenSecret+"','"+user+"');");
	}
	public void rtGorde(String tweet){
		dbk.execSQL("INSERT INTO `twittermysql`.`rt`(`id`,`data`,`nork`,`txioa`)VALUES();");
	}

	public void followingGorde(String tweet){
		dbk.execSQL("INSERT INTO `twittermysql`.`jarraituak`(`id`,`nor`,`userId`,`userIzena`)VALUES();");
	}
	public void followerGorde(String tweet){
		dbk.execSQL("INSERT INTO `twittermysql`.`jarraitzaileak`(`id`,`nor`,`userId`,`userIzena`)VALUES();");
	}
	public void tweetGorde(String tweet){
		dbk.execSQL("INSERT INTO `twittermysql`.`txio`(`id`,`data`,`nork`,`txioa`)VALUES();");
	}
	public String userIzena(String id){
		String userId = "";
		try {
			ResultSet rs = dbk.execSQL("SELECT izena FROM user WHERE izena='"+id+"';");
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
}
