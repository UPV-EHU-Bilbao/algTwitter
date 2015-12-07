package twittercomponents;

import db.Eragiketak;
import exceptions.TimeTo;
import logikoa.TokenKud;
import twitter4j.IDs;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class Following {

	private static Following mFollowing = null;
	Twitter twitter;
	private Following(){
		twitter = TokenKud.getToken().getMyTwitter();
	}
	public static synchronized Following getMfollowing(){
		if(mFollowing == null){
			mFollowing = new Following();
		}return mFollowing;
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
            TimeTo.getMessage(TokenKud.getToken().timeTo(te.toString()));

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
	        //timeTo(te.toString());
            TimeTo.getMessage(TokenKud.getToken().timeTo(te.toString()));

	    }
		
	   }
	
	
}
