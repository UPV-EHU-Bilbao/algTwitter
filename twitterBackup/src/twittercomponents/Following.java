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

public class Following {

	private static Following mFollowing = null;
	Twitter twitter;
	private DBKudeatzaile dbk = DBKudeatzaile.getInstantzia();

	private Following(){
		twitter = TokenKud.getToken().getMyTwitter();
	}
	public static synchronized Following getMfollowing(){
		if(mFollowing == null){
			mFollowing = new Following();
		}return mFollowing;
	}
	
	/*
	 * #####################################################################
	 * ###########BACKUP METODOAK________________DATUBASEAN GORDE###########
	 * #####################################################################
	 */
	
	
	/**
	 * Gure kontuarekin jarraitzen ditugunen backup-a egiten du.
	 */
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
	 * Gure kontuarekin jarraitzen ditugun user-ak bistaratzen ditu.
	 * @return ArrayList<String> - Jarraitutakoak ArrayList-ean bueltatzen ditu.
	 */
	/*
	 * #####################################################################
	 * ###########TAULAN BISTARATZEKO METODOA###############################
	 * #####################################################################
	 */
	public ArrayList<String> viewFollowing(String user){
		ArrayList<String> lista = new ArrayList<String>();
		String agindua = "SELECT userIzena FROM jarraituak WHERE userId='"+user+"' ORDER BY id DESC;";
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
