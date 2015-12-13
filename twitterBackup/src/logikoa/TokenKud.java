package logikoa;
import twitter4j.*;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

import db.Eragiketak;
import exceptions.TimeTo;
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
	/*
	 * #####################################################
	 * ###########HASIERATU GABEKO ERABILTZAILEAK###########
	 * #####################################################
	 */
	
	/**
	 * Gure tokenak hasieratzen ditu
	 * @throws TwitterException
	 * @throws IOException
	 * @throws SQLException
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
	
	/**
	 * Gure accesstoken eta requesttoken-ak lortzen ditu.
	 * @param pin - Twitter-ek ematen digun pin-a
	 * @throws TwitterException
	 * @throws IOException
	 * @throws SQLException
	 */
	public void enterPin(String pin) throws TwitterException, IOException, SQLException{
		System.out.println("AccesToken lortzen...");
		accessToken = twitter.getOAuthAccessToken(requestToken, pin);
		accessToken.getToken();
		System.out.println("ACCESSTOKEN LORTUTA!: "+accessToken);
		System.out.println("Kaixo"+twitter.getScreenName());
		
		//userId DB -an gorde
		Eragiketak.getEragiketak().sartuErab(twitter.getScreenName());
		Erabiltzailea.getErab().setUserId(twitter.getScreenName());
		System.out.println("Oraingo erabiltzailea: "+twitter.getScreenName());
		
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
	
	/**
	 * Gure token-ak sartzen ditu eta Twitter-en instantzia bat sortzen du CB-en
	 * @param izena
	 */
	public void getSession(String izena){
		System.out.println(izena);
		String accesstoken = Eragiketak.getEragiketak().tokenBilatu(izena);
		String secret = Eragiketak.getEragiketak().tokenSecretBilatu(izena);
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setOAuthConsumerKey(consumerKey);
		cb.setOAuthConsumerSecret(consumerSecret);
		cb.setOAuthAccessToken(accesstoken);
		cb.setOAuthAccessTokenSecret(secret);
		twitter = new TwitterFactory(cb.build()).getInstance();
	}
	public Twitter getMyTwitter(){
		return twitter;
	}	
	
	
	
	/*
	 * #####################################################################
	 * ########################SINCE ID -ak#################################
	 * #####################################################################
	 */
	
	/**
	 * Gure follower-ak gordetzen ditu haien id eta izenarekin.
	 * @param max
	 */
	public void getFollowersPage(long max){
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
            TimeTo.getMessage(timeTo(te.toString()));
        }	
	}
	public void getFollowingPage(long max){
		
	}
	
	/*
	 * ###############################################
	 * ###################TIME TO NEXT################
	 * ###############################################
	 */
	
	
	/**
	 * Reset-a egiteko falta diren segunduak harttu eta bueltatzen ditu String moduan
	 * @param errorea
	 * @return
	 */
	public String timeTo(String errorea){
		 String azpikatea = errorea.substring(errorea.indexOf("secondsUntilReset"),errorea.indexOf("secondsUntilReset")+ "secondsUntilReset=000".length());
         String[] zatiak = azpikatea.split("=");
         System.out.println("Falta diren segunduak:" + zatiak[1]);
         //String denbora = zatiak[1]+"segundu...";
         System.out.println("Gehiago lortzeko pixka bat itxaron behar duzu...");
         return zatiak[1];
	}
	

}
