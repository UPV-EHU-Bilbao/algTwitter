package logikoa;
import twitter4j.*;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import db.Eragiketak;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

public class TokenKud {

	private static TokenKud mToken = null;
	private String consumerKey;
	private String consumerSecret;
	private RequestToken requestToken;
	private Twitter twitter;
	private AccessToken accessToken;
	private TableG txioTaula;
	private JTable taula;
	private JPanel tpanel;
	
	private TokenKud(){
		consumerKey = "9vj1uaNEO4T6AUQc7OEUw0yOm";
		consumerSecret = "LkzFqvGzV19fBdW1baOHY5ZkWazSm6HudWWCXBRr8redigzRca";
		
	}
	
	public static synchronized TokenKud getToken(){
		if(mToken==null){
			mToken = new TokenKud();
		}return mToken;
	}
	/*
	 * #####################################################
	 * ###########HASIERATU GABEKO ERABILTZAILEAK###########
	 * #####################################################
	 */
	public void hasieratuToken() throws TwitterException, IOException, SQLException{
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setOAuthConsumerKey(consumerKey);
		cb.setOAuthConsumerSecret(consumerSecret);
		twitter = new TwitterFactory(cb.build()).getInstance();
		
		requestToken = twitter.getOAuthRequestToken();
		try {
				Desktop.getDesktop().browse(new URI(requestToken.getAuthorizationURL()));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
	
		System.out.println("HASIERATZEA, hasieratu(), ondo burutu da!");
		
           
	}
	public void enterPin(String pin) throws TwitterException, IOException, SQLException{
		System.out.println("AccesToken lortzen...");
		accessToken = twitter.getOAuthAccessToken(requestToken, pin);
		accessToken.getToken();
		System.out.println("ACCESSTOKEN LORTUTA!: "+accessToken);
		System.out.println("Kaixo"+twitter.getScreenName());
		
		//userId DB -an gorde
		Eragiketak.getEragiketak().sartuErab(twitter.getScreenName());
		
		//tokenak datu basean gorde
		System.out.println("ZURE TOKEN-ak GORDEKO DIRA...");
		System.out.println("Accesstoken: "+accessToken.getToken());
		System.out.println("AccesstokenSecret: "+accessToken.getTokenSecret());
		Eragiketak.getEragiketak().tokenGorde(accessToken.getToken(), accessToken.getTokenSecret(),twitter.getScreenName());
	}
	/*
	 * #####################################################################
	 * ###########HASIERATUTAKO ERABILTZAILEEN SAIOA BERRESKURATU###########
	 * #####################################################################
	 */
	public void getSession(String izena){
		String accesstoken = Eragiketak.getEragiketak().tokenBilatu(izena);
		String secret = Eragiketak.getEragiketak().tokenSecretBilatu(izena);
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setOAuthConsumerKey(consumerKey);
		cb.setOAuthConsumerSecret(consumerSecret);
		cb.setOAuthAccessToken(accesstoken);
		cb.setOAuthAccessTokenSecret(secret);
		twitter = new TwitterFactory(cb.build()).getInstance();
		
		
	}
	
	/*
	 * ###########################################
	 * ###########BISTARATZEKO METODOAK###########
	 * ###########################################
	 */
	public void getFollowers(){
		 try {
	            
	            long cursor = -1;
	            IDs ids;
	            System.out.println("Listing followers's ids.");
	            do {
	                
	                ids = twitter.getFollowersIDs(cursor);
	                
	                for (long id : ids.getIDs()) {
	                    System.out.println(id);
	                    System.out.println(twitter.showUser(id).getScreenName());
	                }
	            } while ((cursor = ids.getNextCursor()) != 0);
	            //System.exit(0);
	        } catch (TwitterException te) {
	        	System.out.println("Gehiago lortzeko pixka bat itxaron behar duzu...");
	            timeTo(te.toString());
	        }
	}
	public void getFollowing(){
		try {
            long cursor = -1;
            IDs ids;
            System.out.println("Listing following ids.");
            do {
                    ids = twitter.getFriendsIDs(cursor);
                
                for (long id : ids.getIDs()) {
                    System.out.println(id);
                    System.out.println(twitter.showUser(id).getScreenName());   
                }
            } while ((cursor = ids.getNextCursor()) != 0);
            System.exit(0);
        } catch (TwitterException te) {
        	System.out.println("Gehiago lortzeko pixka bat itxaron behar duzu...");
            timeTo(te.toString());
        }
	}
	
	public void getFavorites(){
		try {
           
            List<Status> statuses = twitter.getFavorites();
            for (Status status : statuses) {
                System.out.println("Noiz "+status.getCreatedAt().toString()+"@" + status.getUser().getScreenName() + " - " + status.getText());
            }
            System.out.println("done.");
            //System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get favorites: " + te.getMessage());
            System.exit(-1);
        }
	}
	public void getDirectMessage(){
        try {
            Paging paging = new Paging(1);
            List<DirectMessage> messages;
            do {
                messages = twitter.getDirectMessages(paging);
                for (DirectMessage message : messages) {
                    System.out.println("Noiz: "+message.getCreatedAt()+"From: @" + message.getSenderScreenName() + " id:" + message.getId() + " - "
                            + message.getText());
                }
                paging.setPage(paging.getPage() + 1);
            } while (messages.size() > 0 && paging.getPage() < 10);
            System.out.println("done.");
            //System.exit(0);
        } catch (TwitterException te) {
        	System.out.println("Gehiago lortzeko pixka bat itxaron behar duzu...");
            timeTo(te.toString());
        }
	}
	public void getSentDirectMessage(){
		 try {
	            Paging page = new Paging(1);
	            List<DirectMessage> directMessages;
	            do {
	                directMessages = twitter.getSentDirectMessages(page);
	                for (DirectMessage message : directMessages) {
	                    System.out.println("Noiz: "+message.getCreatedAt()+"To: @" + message.getRecipientScreenName() + " id:" + message.getId() + " - "
	                            + message.getText());
	                    String[]mezua = new String[3];
	                    mezua[0]= message.getCreatedAt().toString();
	                    mezua[1] = message.getRecipientScreenName();
	                    mezua[2] = Long.toString(message.getId());
	                    mezua[3] = message.getText();
	                    Eragiketak.getEragiketak().dmGorde(mezua, twitter.getScreenName());
	                }
	                page.setPage(page.getPage() + 1);
	            } while (directMessages.size() > 0 && page.getPage() < 10);
	            System.out.println("done.");
	            //System.exit(0);
	        } catch (TwitterException te) {
	        	System.out.println("Gehiago lortzeko pixka bat itxaron behar duzu...");
	            timeTo(te.toString());
	        }
	}
	public void getTweets(){
		try {
            int pagenumber = 1;
            int count = 20;
            List<Status> statuses = new ArrayList<Status>();
        	while(true){
        	  Paging page = new Paging(pagenumber, count);
        	  int size = statuses.size();
        	  statuses.addAll(twitter.getHomeTimeline(page));
        	  if(statuses.size()== size){
            	break;
        	  }
            
        	  for (Status status : statuses) {
                System.out.println(status.getId()+" @" + status.getUser().getScreenName() + " - " + status.getText());
        	  }
        	}
        	
        } catch (TwitterException te) {
            System.out.println("Gehiago lortzeko pixka bat itxaron behar duzu...");
            timeTo(te.toString());
           // System.exit(-1);
        }
        }
	
	

	/*
	 * #####################################################################
	 * ###########BACKUP METODOAK________________DATUBASEAN GORDE###########
	 * #####################################################################
	 */
	public void backupTweets(){
		try {
			long max = Eragiketak.getEragiketak().azkenTweetId(twitter.getScreenName());
			if(max == 0){
		            List<Status> statuses = twitter.getHomeTimeline();
		            for (Status status : statuses) {
		                String [] tweet = new String [3];
		                tweet[0] = Long.toString(status.getId());
		                tweet[1] = status.getUser().getScreenName();
		                tweet[2] = status.getText();
		                Eragiketak.getEragiketak().tweetGorde(tweet, twitter.getScreenName());
		            }
			}else{
				getHomeTimeline(max);
			}
        } catch (TwitterException te) {
            System.out.println("Gehiago lortzeko pixka bat itxaron behar duzu...");
            timeTo(te.toString());
        }
	}
	public void backupFollowing(){
	  try{
		long cursor = -1;
        IDs ids;
        do {
            ids = twitter.getFriendsIDs(cursor);
            for (long id : ids.getIDs()) {
            String[] following= new String[2];
            following[0] = Long.toString(id);
            System.out.println(following[0]);
            following[1] = twitter.showUser(id).getScreenName();
            System.out.println(following[1]);
            Eragiketak.getEragiketak().followingGorde(following,twitter.getScreenName());
            }
        }while ((cursor = ids.getNextCursor()) != 0);
        System.exit(0);
    } catch (TwitterException te) {
    	System.out.println("Gehiago lortzeko pixka bat itxaron behar duzu...");
        timeTo(te.toString());
    }
	
   }
	public void backupFollowers(){
		try {
            long cursor = -1;
            IDs ids;
            do { 
                ids = twitter.getFollowersIDs(cursor);
                for (long id : ids.getIDs()) {
                    String[] follow= new String[2];
                    follow[0] = Long.toString(id);
                    follow[1] = twitter.showUser(id).getScreenName();
                    Eragiketak.getEragiketak().followerGorde(follow,twitter.getScreenName());  
                }
            } while ((cursor = ids.getNextCursor()) != 0);
        } catch (TwitterException te) {
        	System.out.println("Gehiago lortzeko pixka bat itxaron behar duzu...");
            timeTo(te.toString());
        }
	}
	public void backupFavorites(){
		try {
	           
			List<Status> statuses = null;
            long max = Eragiketak.getEragiketak().azkenFavId(twitter.getScreenName());
            System.out.println(max);
           if ( max == 0){
            	statuses = twitter.getFavorites();
            	for (Status status : statuses) {
            		String[] favTweet = new String [3];
            		favTweet[0] = Long.toString(status.getId());
            		favTweet[1] = status.getUser().getScreenName();
            		favTweet[2] = status.getText();
            		Eragiketak.getEragiketak().favGorde(favTweet, twitter.getScreenName());
                
            	}
            }else{
            	getFavPage(max);	
            }
        } catch (TwitterException te) {
        	System.out.println("Gehiago lortzeko pixka bat itxaron behar duzu...");
            timeTo(te.toString());
        }
		
	}
	public void backupRt(){
		/*try {
			List<Status> statuses = null;
			long max = Eragiketak.getEragiketak().azkenRtId();
		 if (max == 0){
	            Twitter twitter = new TwitterFactory().getInstance();
	            //List<Status> statuses = twitter.getretw;
	            for (Status status : statuses) {
	                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
	            }
		 }else{
			 
		 }
	        } catch (TwitterException te) {
	        	System.out.println("Gehiago lortzeko pixka bat itxaron behar duzu...");
	            timeTo(te.toString());
	        }*/
	}
	
	

	/*
	 * #####################################################################
	 * ########################SINCE ID -ak#################################
	 * #####################################################################
	 */
	public void getHomeTimeline(long max){
		 try {
	    	  int pagenumber = 1;
	            int count = 20;
	            List<Status> statuses = new ArrayList<Status>();
         	while(true){
         		Paging pag = new Paging(pagenumber, count, max);
         		int size = statuses.size();
         		statuses.addAll(twitter.getHomeTimeline(pag));
         		if(statuses.size()== size){
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
	            timeTo(te.toString());
	        }
	}
	public void getFavPage(long max){
	      try {
	    	  int pagenumber = 1;
	            int count = 20;
	            List<Status> statuses = new ArrayList<Status>();
          	while(true){
          		Paging pag = new Paging(pagenumber, count, max);
          		int size = statuses.size();
          		statuses.addAll(twitter.getFavorites(pag));
          		if(statuses.size()== size){
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
	            timeTo(te.toString());
	            //System.exit(-1);
	        }
	        }
	public void getFollowersPage(long max){
		
			long cursor = -1;
			int pagenumber = 1;
			int count = 20;
			List<User> users = new ArrayList<User>();
			while(true){
				Paging pag = new Paging(pagenumber, count, max);
				int size = users.size();
				//users.addAll(twitter.getFollowersList(pag, cursor));
			}
		  	
		
	}
	public void getFollowingPage(long max){
		
	}
	
	/*
	 * ###############################################
	 * ###################TIME TO NEXT################
	 * ###############################################
	 */
	public void timeTo(String errorea){
		 String azpikatea = errorea.substring(errorea.indexOf("secondsUntilReset"),errorea.indexOf("secondsUntilReset")+ "secondsUntilReset=000".length());
         String[] zatiak = azpikatea.split("=");
         System.out.println("Falta diren segunduak:" + zatiak[1]);
         //String denbora = zatiak[1]+"segundu...";
         System.out.println("Gehiago lortzeko pixka bat itxaron behar duzu...");
	}
	
	//-------------------------------
	//TAULAK ERAIKITZEN             |
	//-------------------------------
	
	public void tweetTaula(){
		try {
            int pagenumber = 1;
            int count = 20;
            List<Status> statuses = new ArrayList<Status>();
        	while(true){
        	  Paging page = new Paging(pagenumber, count);
        	  int size = statuses.size();
        	  statuses.addAll(twitter.getHomeTimeline(page));
        	  if(statuses.size()== size){
            	break;
        	  }
        	}
        	//TAULA ERAIKI
        	
        	txioTaula = new TableG(statuses);
        	JTable taula = new JTable(txioTaula);
        	tpanel = new JPanel();
        	tpanel.setSize(100,100);
        	tpanel.add(taula);
        	tpanel.setVisible(true);
        	//taulaPanel(taula);
        } catch (TwitterException te) {
            System.out.println("Gehiago lortzeko pixka bat itxaron behar duzu...");
            timeTo(te.toString());
           // System.exit(-1);
        }
	}

	public void taulaPanel(JTable t){
		tpanel = new JPanel();
		tpanel.add(t);
		tpanel.setVisible(false);
	}
	public void aldatuVisible(){
		tpanel.setVisible(true);
	}

}
