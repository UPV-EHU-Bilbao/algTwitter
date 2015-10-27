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
import java.util.List;
import java.util.Properties;

import twitter4j.DirectMessage;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class TokenKud {

	private static TokenKud mToken = null;
	private String consumerKey;
	private String consumerSecret;
	private RequestToken requestToken;
	private Twitter twitter;
	
	private TokenKud(){
		consumerKey = "9vj1uaNEO4T6AUQc7OEUw0yOm";
		consumerSecret = "LkzFqvGzV19fBdW1baOHY5ZkWazSm6HudWWCXBRr8redigzRca";
	}
	
	public static synchronized TokenKud getToken(){
		if(mToken==null){
			mToken = new TokenKud();
		}return mToken;
	}
	
	public void hasieratuToken() throws TwitterException, IOException{
		
		twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(consumerKey, consumerSecret);
		System.out.println("TWITTER APP -eko consumerKey: "+consumerKey);
		System.out.println("TWITTER APP -eko consumerSecret: "+consumerSecret);
		requestToken = twitter.getOAuthRequestToken();
		System.out.println("Request token lortuta : "+requestToken.getToken());
		System.out.println("Request token lortuta : "+requestToken.getTokenSecret());
		
		//GURE APP -eko URL -era berbidali
		try {
			System.out.println("URL honetara "+requestToken.getAuthenticationURL().toString()+" berbidaltzen...");
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
	public void enterPin(String pin) throws TwitterException, IOException{
		System.out.println("AccesToken lortzen...");
		AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, pin);
		System.out.println("ACCESSTOKEN LORTUTA!");
		
	}
	public void getFavorites(){
		try {
           
            List<Status> statuses = twitter.getFavorites();
            for (Status status : statuses) {
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
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
                    System.out.println("From: @" + message.getSenderScreenName() + " id:" + message.getId() + " - "
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
	                    System.out.println("To: @" + message.getRecipientScreenName() + " id:" + message.getId() + " - "
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
	//token -ak DB -an gorde
	public void saveTokens(){
		requestToken.getToken();
		requestToken.getTokenSecret();
	}

}
