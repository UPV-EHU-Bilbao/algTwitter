package logikoa;
import twitter4j.*;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import db.Eragiketak;
import twitter4j.DirectMessage;
import twitter4j.IDs;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
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
	
	private TokenKud(){
		consumerKey = "9vj1uaNEO4T6AUQc7OEUw0yOm";
		consumerSecret = "LkzFqvGzV19fBdW1baOHY5ZkWazSm6HudWWCXBRr8redigzRca";
		
	}
	
	public static synchronized TokenKud getToken(){
		if(mToken==null){
			mToken = new TokenKud();
		}return mToken;
	}
	
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
		//getgometimeline()
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
	public void getFavPage(){
	      try {
	            int pagenumber = 1;
	            int count = 20;
	            List<Status> statuses = new ArrayList<Status>();
	        	while(true){
	        	  Paging page = new Paging(pagenumber, count);
	        	  int size = statuses.size();
	        	  statuses.addAll(twitter.getFavorites(page));
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
	            //System.exit(-1);
	        }
	        }
	public void timeTo(String errorea){
		 String azpikatea = errorea.substring(errorea.indexOf("secondsUntilReset"),errorea.indexOf("secondsUntilReset")+ "secondsUntilReset=000".length());
         String[] zatiak = azpikatea.split("=");
         System.out.println("Falta diren segunduak:" + zatiak[1]);
         //String denbora = zatiak[1]+"segundu...";
         System.out.println("Gehiago lortzeko pixka bat itxaron behar duzu...");
	}
}
