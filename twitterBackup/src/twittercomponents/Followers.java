package twittercomponents;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBKudeatzaile;
import db.Eragiketak;
import exceptions.TimeTo;
import logikoa.TokenKud;
import twitter4j.IDs;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class Followers {
	private static Followers mfollowers = null;
	private DBKudeatzaile dbk = DBKudeatzaile.getInstantzia();
	Twitter twitter;

	private Followers(){
		twitter = TokenKud.getToken().getMyTwitter();
	}
	public static synchronized Followers getMfollowers(){
		if(mfollowers == null){
			mfollowers = new Followers();
		}return mfollowers;
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
	            TimeTo.getMessage(TokenKud.getToken().timeTo(te.toString()));
	        }
	}
	/*
	 * #####################################################################
	 * ###########BACKUP METODOAK________________DATUBASEAN GORDE###########
	 * #####################################################################
	 */
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
            //timeTo(te.toString());
            TimeTo.getMessage(TokenKud.getToken().timeTo(te.toString()));
        }
	}
	
	public ArrayList<String> viewFollowers(String user){
		ArrayList<String> lista = new ArrayList<String>();
		String agindua = "SELECT userIzena FROM jarraitzaileak WHERE userId='"+user+"';";
		try {
			
			ResultSet rs = dbk.execSQL(agindua);
			while(rs.next()){
					lista.add(rs.getString("userIzena"));
			}
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}return lista;
	}
	
}