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
import java.util.Properties;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
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
	
	public void hasieratuToken() throws TwitterException{
		
		twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(consumerKey, consumerSecret);
		System.out.println("TWITTER APP -eko consumerKey: "+consumerKey);
		System.out.println("TWITTER APP -eko consumerSecret: "+consumerSecret);
		requestToken = twitter.getOAuthRequestToken();
		System.out.println("Request token lortuta : "+requestToken.toString());
		
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
	public void enterPin(String pin) throws TwitterException{
		System.out.println("AccesToken lortzen...");
		AccessToken accesToken = twitter.getOAuthAccessToken(requestToken, pin);
		System.out.println("ACCESSTOKEN LORTUTA!");
}}
