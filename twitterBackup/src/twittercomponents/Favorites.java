package twittercomponents;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

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
	Twitter twitter;
	JPanel p1;
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
		if(max != 0){
			//getFavPage(max);
			try {
				int pagenumber = 1;
				int count = 20;
				List<Status> statuses = new ArrayList<Status>();
				while(true){
					Paging page = new Paging(pagenumber, count, max);
					int size = statuses.size();
					statuses.addAll(twitter.getFavorites(page));
					if(statuses.size()== size){
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
		}else{
			try {
				int pagenumber = 1;
				int count = 20;
				List<Status> statuses = new ArrayList<Status>();
				while(true){
					Paging page = new Paging(pagenumber, count);
					int size = statuses.size();
					statuses.addAll(twitter.getFavorites(page));
					if(statuses.size()== size){
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
		}return toAdd;
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
           if ( max == 0){
            	statuses = twitter.getFavorites();
            	for (Status status : statuses) {
            		String[] favTweet = new String [3];
            		favTweet[0] = Long.toString(status.getId());
            		favTweet[1] = status.getUser().getScreenName();
            		favTweet[2] = status.getText();
            		Eragiketak.getEragiketak().favGorde(favTweet, twitter.getScreenName());
                
            	}
            }else{
            	getFavPage(max);	
            }
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
	          		Paging pag = new Paging(pagenumber, count, max);
	          		int size = statuses.size();
	          		statuses.addAll(twitter.getFavorites(pag));
	          		if(statuses.size()== size){
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
		
		public JPanel makeAtable(List<Status> l1){
			JPanel panel = new JPanel();
			JTable table = new JTable(new TableG(l1));
			JScrollPane scrollPane = new JScrollPane(table);
			p1.add(scrollPane);
			return panel;
			
		}
		public List<Status> bistaratzeko() throws IllegalStateException, TwitterException{
			return getFavorites();
			
		}
	
}
