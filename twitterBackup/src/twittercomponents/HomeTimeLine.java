package twittercomponents;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import db.DBKudeatzaile;
import db.Eragiketak;
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
	 * @throws TwitterException 
	 * @throws IllegalStateException 
	 */
	public void backupTweets() {
			long max = 0;
			try {
				max = Eragiketak.getEragiketak().azkenTweetId(twitter.getScreenName());
				if(max == 0){
					getTweets();
				}else{
					getHomeTimeline(max);
				}
			} catch (TwitterException te) {
				String time = TokenKud.getToken().timeTo(te.toString());
		           String mezua = "Gehiago lortzeko pixka bat itxaron beharko duzu!: " + time +" seg.";
		   		JOptionPane.showMessageDialog(null,
		   			    mezua,
		   			    "ITXARON",
		   			    JOptionPane.NO_OPTION);
			}
			
        
	}
	public void getHomeTimeline(long max)throws TwitterException{
	    	  int pagenumber = 1;
	            int count = 80;
	            List<Status> statuses = new ArrayList<Status>();
        	while(true){
        		Paging pag = new Paging(pagenumber, count).sinceId(max);
        				//, max);
        		int size = statuses.size();
        		statuses.addAll(twitter.getHomeTimeline(pag));
        		statuses = twitter.getHomeTimeline(pag);
        		if(statuses.size()== size){
        			break;
        		}
        	}
        	for (Status status: statuses){
        		
        		String[] favTweet = new String [3];
        		favTweet[0] = Long.toString(status.getId());
        		favTweet[1] = status.getUser().getScreenName();
        		favTweet[2] = status.getText();
        		System.out.println(favTweet[1]+" "+favTweet[2]);
        		Eragiketak.getEragiketak().tweetGorde(favTweet, twitter.getScreenName());
        	}

	}
	public void getTweets()throws TwitterException{
            List<Status> statuses = twitter.getHomeTimeline();
            for (Status status : statuses) {
            	String[] favTweet = new String [3];
        		favTweet[0] = Long.toString(status.getId());
        		favTweet[1] = status.getUser().getScreenName();
        		favTweet[2] = status.getText();
        		Eragiketak.getEragiketak().tweetGorde(favTweet, twitter.getScreenName());
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
			if(lista.size() == 0){
				String mezua = "WAIT! Lehenago Backup bat egin beharko zenuke..." ;
				JOptionPane.showMessageDialog(null,
					    mezua,
					    "NO BACKUP",
					    JOptionPane.NO_OPTION);
			}
		} catch (SQLException e) {
		}return lista;
	}
}
