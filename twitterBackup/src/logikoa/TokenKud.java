package logikoa;

import java.awt.Desktop;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class TokenKud {

	private static TokenKud mToken = null;
	
	private TokenKud(){}
	
	public static synchronized TokenKud getToken(){
		if(mToken==null){
			mToken = new TokenKud();
		}return mToken;
	}
	
	public void hasieratuToken() throws TwitterException{
		 Twitter twitter = new TwitterFactory().getInstance();
         RequestToken requestToken = twitter.getOAuthRequestToken();
         System.out.println("Got request token.");
         System.out.println("Request token: " + requestToken.getToken());
         System.out.println("Request token secret: " + requestToken.getTokenSecret());
         AccessToken accessToken = null;
         
       //  while (null == accessToken) {
             System.out.println("Open the following URL and grant access to your account:");
             System.out.println(requestToken.getAuthorizationURL());
             try {
                 Desktop.getDesktop().browse(new URI(requestToken.getAuthorizationURL()));
             } catch (UnsupportedOperationException ignore) {
             } catch (IOException ignore) {
             } catch (URISyntaxException e) {
                 throw new AssertionError(e);
             }
             System.out.println("Pin -a orain egiaztatuko da...");
           
	}
	public void enterPin(String pPin) throws TwitterException{
		Twitter twitter = new TwitterFactory().getInstance();
        RequestToken requestToken = twitter.getOAuthRequestToken();
        System.out.println("Kaixo hemen nago.");
       // System.out.println("Request token: " + requestToken.getToken());
       // System.out.println("Request token secret: " + requestToken.getTokenSecret());
        AccessToken accessToken = null;
       // try {
            if (pPin.length() > 0) {
                accessToken = twitter.getOAuthAccessToken(requestToken, pPin);
            } else {
                accessToken = twitter.getOAuthAccessToken(requestToken);
            }
       // } catch (TwitterException te) {
            //if (401 == te.getStatusCode()) {
              //  System.out.println("Unable to get the access token./Pin -a gaizki egongo da...");
            //} else {
                //te.printStackTrace();
           // }
       // }
  //  }
   
    try {
		PinKudeaketa.getPin().eratuFitxategia(requestToken.getToken(), requestToken.getTokenSecret());
	} catch (FileNotFoundException e) {
		System.out.println("FILE NOT FOUND!!!!");
	}
    System.exit(0);
		
	}
}
