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
	/**
	 * Token -a gordetzeko erabiliko da erabiltzailea logeatzen den lehenengo aldian
	 * @param token
	 * @param tokenSecret
	 * @param user
	 * @throws SQLException
	 */
	public void tokenGorde(String token, String tokenSecret, String user) throws SQLException{
		String agindua = "INSERT INTO token(accessToken, accessTokenSecret, user)VALUES('"+token+"','"+tokenSecret+"','"+user+"')";
		dbk.execSQL(agindua);
	}
	/**
	 * Token secret -a gordetzeko erabiliko da erabiltzailea logeatzen den lehenengo aldian
	 * @param tokenSecret
	 * @throws SQLException
	 */
	public void tokenSecretGorde(String tokenSecret) throws SQLException{
		dbk.execSQL("INSERT INTO token(accessTokenSecret)VALUES('"+tokenSecret+"');");
		//rs = dbk.execSQL("INSERT INTO token(accessTokenSecret)VALUES("+tokenSecret+");");
	}
	public void consumerGorde(String c) throws SQLException{
		dbk.execSQL("INSERT INTO `token`(`consumerKey`)VALUES('"+c+"');");
	}
	public void consumerSecretGorde(String cS) throws SQLException{
		dbk.execSQL("INSERT INTO `token`(`consumerKeySecret`)VALUES('"+cS+"');");
	}
	public void pasaHitzaGorde(String user){
		dbk.execSQL("INSERT INTO user(password)VALUES('"+user+"');");
	}
	/**
	 * Logeatuta dagoen erabiltzailearen token -ak bilatuko ditu
	 * @param izena
	 * @return
	 */
	public String tokenBilatu(String izena){
		String token = "";
		try {
			ResultSet rs = dbk.execSQL("SELECT accessToken FROM token WHERE user='"+izena+"';");
			System.out.println("HAU DA RESULt SET: "+rs);
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
			ResultSet rs = dbk.execSQL("SELECT accessTokenSecret FROM `token` WHERE user='"+izena+"';");
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
	public String pasaHitzaBilatu(String user){
		String pasah= "";
		try {
			ResultSet rs = dbk.execSQL("SELECT password FROM user WHERE user='"+user+"';");
			while(rs.next()){
				//rs osoan dagoena sartuko du String -ean
				pasah= rs.getString("password");
				//System.out.println(token);
			}
		} catch (SQLException e) {
			System.out.println("EZ DAGO PASAHITZIK");
			//e.printStackTrace();
		}
		return pasah;
	}
	
	public void sartuErab(String pizena) throws SQLException{
		dbk.execSQL("INSERT INTO user(`izena`) VALUES('"+pizena+"');");
	}
	
	
	
	//twitter -EKO FAV,RT, HOMETIMELINE, FOLLOWERS & FOLLOWING
	//datuak datu basean gordetzeko MySQL sententziak
	public void favGorde(String [] tweet, String userId){
		
		dbk.execSQL("INSERT INTO `fav`(`idFav`,`nork`,`txioa`,`userIzena`)VALUES('"+tweet[0]+"','"+tweet[1]+"','"+tweet[2]+"','"+userId+"');");
	}
	public void followingGorde(String[] following, String userId){
		String id = following[0];
		String screenName = following[1];
		dbk.execSQL("INSERT INTO `jarraituak` VALUES('"+id+"','"+userId+"','"+screenName+"');");
	}
	public void followerGorde(String[] follow, String userId){
		String id = follow[0];
		String screenName = follow[1];
		dbk.execSQL("INSERT INTO `jarraitzaileak` VALUES('"+userId+"','"+screenName+"','"+id+"');");
	}
	public void tweetGorde(String[] tweet, String userId){
		dbk.execSQL("INSERT INTO txio(id, nork, txioa, userIzena)VALUES('"+tweet[0]+"','"+tweet[1]+"','"+tweet[2]+"','"+userId+"');");
	}
	public void dmGorde(String[] mezua, String userId){
		dbk.execSQL("INSERT INTO md(id, nork, mezua, userId) VALUES('"+mezua[0]+"','"+mezua[1]+"','"+mezua[2]+"','"+userId+"');");
	}
	public String userIzena(String id){
		String userId = "";
		try {
			ResultSet rs = dbk.execSQL("SELECT izena FROM user WHERE izena='"+id+"'");
			System.out.println(rs);
			while(rs.next()){
				//rs osoan dagoena sartuko du String -ean
				userId = rs.getString("izena");
				//System.out.println(userId);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userId;
	}
	/*
	 * ####################################################################
	 * ###########################MAXIMOAK BILATU###########################
	 * ####################################################################
	 */
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
	public long azkenTweetId(String user){
		String idS = "";
		if(kontatu(user) == 0){
			return 0;
		}else{
			try {
			
				ResultSet rs = dbk.execSQL("SELECT MAX(id) as maximoa FROM txio WHERE userIzena='"+user+"';");
			
					while(rs.next()){
					//rs osoan dagoena sartuko du String -ean
					idS = rs.getString("maximoa");
					System.out.println(idS);
					}
				rs.close();
			
			} catch (SQLException e) {
			
			}return Long.valueOf(idS);
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
	public long azkenmdId(String user){
		String id = null ;
		try {
			ResultSet rs = dbk.execSQL("SELECT MAX(id) as maximoa FROM txio WHERE userIzena='"+user+"';");
			while(rs.next()){
				//rs osoan dagoena sartuko du String -ean
				id = rs.getString("maximoa");
				System.out.println(id);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(id == null){
			return 0;
		}else{
			return Long.valueOf(id);
		}
		
	}
	public int kontatu(String user){
		int zenb = 0;
		try {
			ResultSet rs = dbk.execSQL("SELECT COUNT(*) as zenbat FROM txio WHERE userIzena='"+user+"'");
			while(rs.next()){
				//rs osoan dagoena sartuko du String -ean
				zenb = rs.getInt("zenbat");
				System.out.println(zenb);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return zenb;
	}
	/*
	 * #############################################################
	 * ###############ERABILTZAILEAREN DATUAK EZABATU###############
	 * #############################################################
	 */
	public void deleteAll(String user){
		dbk.execSQL("DELETE FROM fav WHERE userIzena='"+user+"';");
		dbk.execSQL("DELETE FROM txio WHERE userIzena='"+user+"';");
		dbk.execSQL("DELETE FROM jarraituak WHERE userIzena='"+user+"';");
		dbk.execSQL("DELETE FROM jarraitzaileak WHERE userId='"+user+"';");
		dbk.execSQL("DELETE FROM md WHERE userId='"+user+"';");
		dbk.execSQL("DELETE FROM user WHERE izena='"+user+"';");
		dbk.execSQL("DELETE FROM token WHERE user='"+user+"';");
		
	}


}
