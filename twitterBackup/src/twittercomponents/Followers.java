package twittercomponents;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import db.DBKudeatzaile;
import db.Eragiketak;
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
	 * #####################################################################
	 * ###########BACKUP METODOAK________________DATUBASEAN GORDE###########
	 * #####################################################################
	 */
	
	
	/**
	 * Gure kontura jarraitzen gaituzten user-en backup-a egiten du.
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
                    System.out.println("follower cursor balioa while: "+cursor);
                }
            } while ((cursor = ids.getNextCursor()) != 0);
        } catch (TwitterException te) {
        	System.out.println("Gehiago lortzeko pixka bat itxaron behar duzu...");
        	String time = TokenKud.getToken().timeTo(te.toString());
	           String mezua = "Gehiago lortzeko pixka bat itxaron beharko duzu!: " + time +" seg.";
	   		JOptionPane.showMessageDialog(null,
	   			    mezua,
	   			    "ITXARON",
	   			    JOptionPane.NO_OPTION);
        }
	}
	
	/**
	 * Gure kontura jarraitzen gaituzten user-ak bistaratzen ditu.
	 * @return ArrayList<String> - Jarraitzen gaituztak bistaratu.
	 */
	/*
	 * #####################################################################
	 * ###########TAULAN BISTARATZEKO METODOA###############################
	 * #####################################################################
	 */
	public ArrayList<String> viewFollowers(String user){
		ArrayList<String> lista = new ArrayList<String>();
		String agindua = "SELECT userIzena FROM jarraitzaileak WHERE userId='"+user+"' ORDER BY id DESC;";
		try {
			
			ResultSet rs = dbk.execSQL(agindua);
			while(rs.next()){
					lista.add(rs.getString("userIzena"));
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
