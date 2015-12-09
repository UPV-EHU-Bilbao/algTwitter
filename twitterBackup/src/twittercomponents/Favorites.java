package twittercomponents;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import db.DBKudeatzaile;
import db.Eragiketak;
import exceptions.TimeTo;
import logikoa.TableG;
import logikoa.TokenKud;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class Favorites {
	private static Favorites mfavorites = null;
	private Twitter twitter;
	private List<Status> statuses = new ArrayList<Status>();
	DBKudeatzaile dbk = DBKudeatzaile.getInstantzia();

	private Favorites(){
		twitter = TokenKud.getToken().getMyTwitter();
	}
	public static synchronized Favorites getMfav(){
		if (mfavorites == null){
			mfavorites = new Favorites();
		}return mfavorites;
	}
	/*
	 * ###########################################
	 * ###########BISTARATZEKO METODOAK###########
	 * ###########################################
	 */
	public List<Status> getFavorites() throws IllegalStateException, TwitterException{
		List<Status> toAdd= new ArrayList<Status>();
		long max = Eragiketak.getEragiketak().azkenFavId(twitter.getScreenName());

			try {
				int pagenumber = 1;
				int count = 20;
				List<Status> statuses = new ArrayList<Status>();
				while(true){
					Paging page = new Paging(pagenumber, count).sinceId(max);
							//, max);
					//int size = statuses.size();
					statuses = twitter.getFavorites(page);
					//statuses.addAll(twitter.getFavorites(page));
					int zenbat = statuses.size();
					//if(statuses.size()== size){
					if(zenbat == 0){
						break;
        	        }
           
			  }
				
			for(Status status: statuses){
				toAdd.add(status);
			}
			} catch (TwitterException te) {
				te.printStackTrace();
				System.out.println("Failed to get favorites: " + te.getMessage());
				System.exit(-1);
				TimeTo.getMessage(TokenKud.getToken().timeTo(te.toString()));
			}

		return statuses;
	}
	/*
	 * #####################################################################
	 * ###########BACKUP METODOAK________________DATUBASEAN GORDE###########
	 * #####################################################################
	 */
	public void backupFavorites(){
		try {
	           
			List<Status> statuses = null;
            long max = Eragiketak.getEragiketak().azkenFavId(twitter.getScreenName());
            System.out.println(max);       
            	getFavPage(max);	
            
        } catch (TwitterException te) {
        	System.out.println("Gehiago lortzeko pixka bat itxaron behar duzu...");
           // timeTo(te.toString());
            TimeTo.getMessage(TokenKud.getToken().timeTo(te.toString()));
        }
	}
		
		public void getFavPage(long max){
		      try {
		    	  int pagenumber = 1;
		            int count = 20;
		            List<Status> statuses = new ArrayList<Status>();
	          	while(true){
	          		Paging pag = new Paging(pagenumber, count).sinceId(max);
	          				//, max);
	          		int size = statuses.size();
	          		statuses = twitter.getFavorites(pag);
	          		//int zenbat = statuses.size();
	          		statuses.addAll(twitter.getFavorites(pag));
	          		if(statuses.size()== size){
	          		//if(zenbat == 0){
	          			break;
	          		}
	          	}
	          	for (Status status: statuses){
	          		String[] favTweet = new String [3];
	          		favTweet[0] = Long.toString(status.getId());
	          		favTweet[1] = status.getUser().getScreenName();
	          		favTweet[2] = status.getText();
	          		Eragiketak.getEragiketak().favGorde(favTweet, twitter.getScreenName());
	          	}
		        } catch (TwitterException te) {
		            System.out.println("Gehiago lortzeko pixka bat itxaron behar duzu...");
		            //timeTo(te.toString());
		            //System.exit(-1);
		            TimeTo.getMessage(TokenKud.getToken().timeTo(te.toString()));
		        }
		        }
		
	public ArrayList<String[]> viewFavorites(){
		ArrayList<String[]> lista = new ArrayList<String[]>();
		String agindua = "SELECT nork,txioa FROM fav WHERE userIzena='ISADtaldea';";
		try {
			
			ResultSet rs = dbk.execSQL(agindua);
			while(rs.next()){
				String[] status = new String[2];
					status[0] = rs.getString("nork");
					status[1] = rs.getString("txioa");
					lista.add(status);
			}		
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}return lista;
	}
}
