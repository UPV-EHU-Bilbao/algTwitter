package twittercomponents;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import db.DBKudeatzaile;
import db.Eragiketak;
import exceptions.TimeTo;
import logikoa.TokenKud;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class HomeTimeLine {
	private static HomeTimeLine mHome = null;
	Twitter twitter;
	JPanel panel;
	DBKudeatzaile dbk = DBKudeatzaile.getInstantzia();
	private HomeTimeLine(){
		twitter = TokenKud.getToken().getMyTwitter();
	}
	
	public static synchronized HomeTimeLine getMhome(){
		if (mHome == null){
			mHome = new HomeTimeLine();
		}
		return mHome;
	}

	/*
	 * #####################################################################
	 * ###########BACKUP METODOAK________________DATUBASEAN GORDE###########
	 * #####################################################################
	 */
	
	/**
	 * Txioen backup-a egiten du.
	 */
	public void backupTweets(){
		try {
			long max = Eragiketak.getEragiketak().azkenTweetId(twitter.getScreenName());
			
				getHomeTimeline(max);
			
        } catch (TwitterException te) {
            System.out.println("Gehiago lortzeko pixka bat itxaron behar duzu...");
            //timeTo(te.toString());
            TimeTo.getMessage(TokenKud.getToken().timeTo(te.toString()));
        }
	}
	public void getHomeTimeline(long max){
		 try {
	    	  int pagenumber = 1;
	            int count = 20;
	            List<Status> statuses = new ArrayList<Status>();
        	while(true){
        		Paging pag = new Paging(pagenumber, count).sinceId(max);
        				//, max);
        		int size = statuses.size();
        		statuses.addAll(twitter.getHomeTimeline(pag));
        		statuses = twitter.getHomeTimeline(pag);
        		//int zenbat = statuses.size();
        		if(statuses.size()== size){
        		//if( zenbat == 0){
        			break;
        		}
        	}
        	for (Status status: statuses){
        		String[] favTweet = new String [3];
        		favTweet[0] = Long.toString(status.getId());
        		favTweet[1] = status.getUser().getScreenName();
        		favTweet[2] = status.getText();
        		Eragiketak.getEragiketak().tweetGorde(favTweet, twitter.getScreenName());
        	}
	        } catch (TwitterException te) {
	            System.out.println("Gehiago lortzeko pixka bat itxaron behar duzu...");
	            //timeTo(te.toString());
	            TimeTo.getMessage(TokenKud.getToken().timeTo(te.toString()));
	        }
	}

	/**
	 * Txioak bistaratzen ditu.
	 * @param user
	 * @return ArrayList<String> - Txioak bueltatzen ditu ArrayList batean.
	 */
	/*
	 * #####################################################################
	 * ###########TAULAN BISTARATZEKO METODOA###############################
	 * #####################################################################
	 */
public ArrayList<String[]> viewTxio(String user){
		
		//String[] status = new String[2];
		ArrayList<String[]> lista = new ArrayList<String[]>();
		String agindua = "SELECT nork,txioa FROM txio WHERE userIzena='"+user+"' ORDER BY id DESC;";
		try {
			
			ResultSet rs = dbk.execSQL(agindua);
			while(rs.next()){
				String[] status = new String[2];
					status[0] = rs.getString("nork");
					status[1] = rs.getString("txioa");
					lista.add(status);
			}
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}return lista;
	}
}
