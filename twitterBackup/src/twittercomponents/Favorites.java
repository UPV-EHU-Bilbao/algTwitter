package twittercomponents;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBKudeatzaile;
import db.Eragiketak;
import exceptions.NoBackup;
import exceptions.TimeTo;
import logikoa.TokenKud;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class Favorites {
	private static Favorites mfavorites = null;
	private Twitter twitter;
	DBKudeatzaile dbk = DBKudeatzaile.getInstantzia();

	private Favorites(){
		twitter = TokenKud.getToken().getMyTwitter();
	}
	public static synchronized Favorites getMfav(){
		if (mfavorites == null){
			mfavorites = new Favorites();
		}return mfavorites;
	}
	/*
	 * #####################################################################
	 * ###########BACKUP METODOAK________________DATUBASEAN GORDE###########
	 * #####################################################################
	 */
	public void backupFavorites(){
		try {
            long max = Eragiketak.getEragiketak().azkenFavId(twitter.getScreenName());
            System.out.println(max); 
            if(max ==0){
            	getfav();
            }else{
            	getFavPage(max);	
            }
        } catch (TwitterException te) {
        	System.out.println("Gehiago lortzeko pixka bat itxaron behar duzu...");
           // timeTo(te.toString());
            TimeTo.getMessage(TokenKud.getToken().timeTo(te.toString()));
        }
	}
		
		public void getFavPage(long max){
		      try {
		    	  int pagenumber = 1;
		            int count = 20;
		            List<Status> statuses = new ArrayList<Status>();
	          	while(true){
	          		Paging pag = new Paging(pagenumber, count).sinceId(max);
	          				//, max);
	          		int size = statuses.size();
	          		statuses = twitter.getFavorites(pag);
	          		//int zenbat = statuses.size();
	          		statuses.addAll(twitter.getFavorites(pag));
	          		if(statuses.size()== size){
	          		//if(zenbat == 0){
	          			break;
	          		}
	          	}
	          	for (Status status: statuses){
	          		String[] favTweet = new String [3];
	          		favTweet[0] = Long.toString(status.getId());
	          		favTweet[1] = status.getUser().getScreenName();
	          		favTweet[2] = status.getText();
	          		Eragiketak.getEragiketak().favGorde(favTweet, twitter.getScreenName());
	          	}
		        } catch (TwitterException te) {
		            System.out.println("Gehiago lortzeko pixka bat itxaron behar duzu...");
		            //timeTo(te.toString());
		            //System.exit(-1);
		            TimeTo.getMessage(TokenKud.getToken().timeTo(te.toString()));
		        }
		        }
		public void getfav(){
			try {
	            List<Status> statuses = twitter.getFavorites();
	            for (Status status : statuses) {
	            	String[] favTweet = new String [3];
	          		favTweet[0] = Long.toString(status.getId());
	          		favTweet[1] = status.getUser().getScreenName();
	          		favTweet[2] = status.getText();
	          		Eragiketak.getEragiketak().favGorde(favTweet, twitter.getScreenName());
	            }
	            
	        } catch (TwitterException te) {
	        	TimeTo.getMessage(TokenKud.getToken().timeTo(te.toString()));
	        }
		}
		/*
		 * #####################################################################
		 * ###########TAULAN BISTARATZEKO METODOA###############################
		 * #####################################################################
		 */
	public ArrayList<String[]> viewFavorites(String user){
		ArrayList<String[]> lista = new ArrayList<String[]>();
		String agindua = "SELECT nork,txioa FROM fav WHERE userIzena='"+user+"' ORDER BY idFav DESC;";
		try {
			
			ResultSet rs = dbk.execSQL(agindua);
			while(rs.next()){
				String[] status = new String[2];
					status[0] = rs.getString("nork");
					status[1] = rs.getString("txioa");
					lista.add(status);
			}		
			rs.close();
			if(lista.size() == 0){
				NoBackup.getback();
			}
		} catch (SQLException e) {
			NoBackup.getback();
		}return lista;
	}
}
