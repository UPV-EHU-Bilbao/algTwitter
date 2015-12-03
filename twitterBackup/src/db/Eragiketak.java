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
	public void favGorde(String [] tweet, String userId){
		
		dbk.execSQL("INSERT INTO `twittermysql`.`fav`(`idFav`,`nork`,`txioa`,`userIzena`)VALUES('"+tweet[0]+"','"+tweet[1]+"','"+tweet[2]+"','"+userId+"');");
	}
	public void rtGorde(String tweet){
		dbk.execSQL("INSERT INTO `twittermysql`.`rt`(`id`,`data`,`nork`,`txioa`)VALUES();");
	}

	public void followingGorde(String[] following, String userId){
		String id = following[0];
		String screenName = following[1];
		dbk.execSQL("INSERT INTO `twittermysql`.`jarraituak` VALUES('"+id+"','"+userId+"','"+screenName+"');");
	}
	public void followerGorde(String[] follow, String userId){
		String id = follow[0];
		String screenName = follow[1];
		dbk.execSQL("INSERT INTO `twittermysql`.`jarraitzaileak` VALUES('"+userId+"','"+screenName+"','"+id+"');");
	}
	public void tweetGorde(String[] tweet, String userId){
		dbk.execSQL("INSERT INTO `twittermysql`.`txio`(`id`,`nork`,`txioa`,`userIzena`)VALUES('"+tweet[0]+"','"+tweet[1]+"','"+tweet[2]+"','"+userId+"');");
	}
	public void dmGorde(String[] follow, String userId){
		dbk.execSQL("INSERT INTO `twittermysql`.`md` VALUES("+follow[0]+",'"+follow[1]+"','"+follow[3]+",'"+follow[2]+",'"+userId+");");
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
	public long azkenFavId(String user){
		String favIDS = "";
		//long favId = 0;
		try {
			ResultSet rs = dbk.execSQL("SELECT MAX(idFav) as maximoa FROM fav WHERE userIzena='"+user+"'");
			while(rs.next()){
				//rs osoan dagoena sartuko du String -ean
				favIDS = rs.getString("maximoa");
				System.out.println(favIDS);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (favIDS == null){
			return 0;
		}else{
			return Long.valueOf(favIDS);
		}
	}
	public long azkenRtId(){
		long id = 0;
		try {
			ResultSet rs = dbk.execSQL("SELECT MAX(id) as maximoa FROM rt;");
			while(rs.next()){
				//rs osoan dagoena sartuko du String -ean
				id = Long.valueOf(rs.getString("maximoa"));
				System.out.println(id);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	public long azkenTweetId(String user){
		String idS = "";
		try {
			ResultSet rs = dbk.execSQL("SELECT MAX(id) as maximoa FROM txio WHERE userIzena='"+user+"';");
			while(rs.next()){
				//rs osoan dagoena sartuko du String -ean
				idS = rs.getString("maximoa");
				System.out.println(idS);
			}
			rs.close();
		} catch (SQLException e) {
			
		}
		if (idS == null){
			return 0;
		}else{
			return Long.valueOf(idS);
		}
	}
	public long azkenjarraitzId(){
		long id = 0;
		try {
			ResultSet rs = dbk.execSQL("SELECT MAX(id) as maximoa FROM jarraitzaileak;");
			while(rs.next()){
				//rs osoan dagoena sartuko du String -ean
				id = Long.valueOf(rs.getString("maximoa"));
				System.out.println(id);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	public long azkenjarraituakId(){
		long id = 0;
		try {
			ResultSet rs = dbk.execSQL("SELECT MAX(id) as maximoa FROM jarraituak;");
			while(rs.next()){
				//rs osoan dagoena sartuko du String -ean
				id = Long.valueOf(rs.getString("maximoa"));
				System.out.println(id);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	


}
