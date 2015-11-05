package logikoa;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;



import db.Eragiketak;
import twitter4j.DirectMessage;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
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
//		String token = Eragiketak.getEragiketak().tokenBilatu();
//		String secret = Eragiketak.getEragiketak().tokenSecretBilatu();
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setOAuthConsumerKey(consumerKey);
		cb.setOAuthConsumerSecret(consumerSecret);
		//cb.setOAuthAccessToken(accessToken);
    	//cb.setOAuthAccessTokenSecret(accessTokenSecret);
		twitter = new TwitterFactory(cb.build()).getInstance();
		
//		System.out.println("TWITTER APP -eko consumerKey: "+consumerKey);
//		System.out.println("TWITTER APP -eko consumerSecret: "+consumerSecret);
		
		//consumer -ak datu basean gorde
//		Eragiketak.getEragiketak().consumerGorde(consumerKey);
//		Eragiketak.getEragiketak().consumerSecretGorde(consumerSecret);
		
		requestToken = twitter.getOAuthRequestToken();
		try {
			//	System.out.println("URL honetara "+requestToken.getAuthenticationURL().toString()+" berbidaltzen...");
				Desktop.getDesktop().browse(new URI(requestToken.getAuthorizationURL()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
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
		Eragiketak.getEragiketak().tokenGorde(accessToken.getToken());
		
		System.out.println("AccesstokenSecret: "+accessToken.getTokenSecret());
		Eragiketak.getEragiketak().tokenSecretGorde(accessToken.getTokenSecret());
		
		//System.out.println(requestToken);
		//Eragiketak.getEragiketak().rTokenGorde(requestToken.toString());
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
            te.printStackTrace();
            System.out.println("Failed to get messages: " + te.getMessage());
            System.exit(-1);
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
	            te.printStackTrace();
	            System.out.println("Failed to get sent messages: " + te.getMessage());
	            System.exit(-1);
	        }
	}
	public void getTweets(){
		//getgometimeline()
		try{
			User user = twitter.verifyCredentials();
			List<Status> statuses = twitter.getHomeTimeline();
			System.out.println("Showing @" + user.getScreenName() + "'s home timeline.");
			for (Status status : statuses) {
            System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
			}
		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to get timeline: " + te.getMessage());
			System.exit(-1);
    }
	}
	
	
	public void getSession(){
		String accesstoken = Eragiketak.getEragiketak().tokenBilatu();
		String secret = Eragiketak.getEragiketak().tokenSecretBilatu();
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setOAuthConsumerKey(consumerKey);
		cb.setOAuthConsumerSecret(consumerSecret);
		cb.setOAuthAccessToken(accesstoken);
		cb.setOAuthAccessTokenSecret(secret);
		twitter = new TwitterFactory(cb.build()).getInstance();
		
	}
	
}
