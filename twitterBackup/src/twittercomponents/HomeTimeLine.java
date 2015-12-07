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

public class HomeTimeLine {
	private static HomeTimeLine mHome = null;
	Twitter twitter;
	JPanel panel;
	private HomeTimeLine(){
		twitter = TokenKud.getToken().getMyTwitter();
	}
	
	public static synchronized HomeTimeLine getMhome(){
		if (mHome == null){
			mHome = new HomeTimeLine();
		}
		return mHome;
	}

	/*
	 * ###########################################
	 * ###########BISTARATZEKO METODOAK###########
	 * ###########################################
	 */
	public JPanel getTweets() throws IllegalStateException, TwitterException{
		long max = Eragiketak.getEragiketak().azkenTweetId(twitter.getScreenName());
		if(max != 0){
			try {
				int pagenumber = 1;
				int count = 20;
				List<Status> statuses = new ArrayList<Status>();
				while(true){
					Paging page = new Paging(pagenumber, count,max);
					int size = statuses.size();
					statuses.addAll(twitter.getHomeTimeline(page));
					if(statuses.size()== size){
						break;
        	   }
				}
					panel = makeAtable(statuses);
        	
				
        } catch (TwitterException te) {
            System.out.println("Gehiago lortzeko pixka bat itxaron behar duzu...");
           // TokenKud.getToken().timeTo(te.toString());
            TimeTo.getMessage(TokenKud.getToken().timeTo(te.toString()));
		}}else{
			try {
				int pagenumber = 1;
				int count = 20;
				List<Status> statuses = new ArrayList<Status>();
				while(true){
					Paging page = new Paging(pagenumber, count);
					int size = statuses.size();
					statuses.addAll(twitter.getHomeTimeline(page));
					if(statuses.size()== size){
						break;
        	   }
				}
					panel = makeAtable(statuses);
        	
				
        } catch (TwitterException te) {
            System.out.println("Gehiago lortzeko pixka bat itxaron behar duzu...");
           // TokenKud.getToken().timeTo(te.toString());
            TimeTo.getMessage(TokenKud.getToken().timeTo(te.toString()));
        }}
	
		return panel;
		
        }
	/*
	 * #####################################################################
	 * ###########BACKUP METODOAK________________DATUBASEAN GORDE###########
	 * #####################################################################
	 */
	public void backupTweets(){
		try {
			long max = Eragiketak.getEragiketak().azkenTweetId(twitter.getScreenName());
			if(max == 0){
		            List<Status> statuses = twitter.getHomeTimeline();
		            for (Status status : statuses) {
		                String [] tweet = new String [3];
		                tweet[0] = Long.toString(status.getId());
		                tweet[1] = status.getUser().getScreenName();
		                tweet[2] = status.getText();
		                Eragiketak.getEragiketak().tweetGorde(tweet, twitter.getScreenName());
		            }
			}else{
				getHomeTimeline(max);
			}
        } catch (TwitterException te) {
            System.out.println("Gehiago lortzeko pixka bat itxaron behar duzu...");
            //timeTo(te.toString());
            TimeTo.getMessage(TokenKud.getToken().timeTo(te.toString()));
        }
	}
	public void getHomeTimeline(long max){
		 try {
	    	  int pagenumber = 1;
	            int count = 20;
	            List<Status> statuses = new ArrayList<Status>();
        	while(true){
        		Paging pag = new Paging(pagenumber, count, max);
        		int size = statuses.size();
        		statuses.addAll(twitter.getHomeTimeline(pag));
        		if(statuses.size()== size){
        			break;
        		}
        	}
        	for (Status status: statuses){
        		String[] favTweet = new String [3];
        		favTweet[0] = Long.toString(status.getId());
        		favTweet[1] = status.getUser().getScreenName();
        		favTweet[2] = status.getText();
        		Eragiketak.getEragiketak().tweetGorde(favTweet, twitter.getScreenName());
        	}
	        } catch (TwitterException te) {
	            System.out.println("Gehiago lortzeko pixka bat itxaron behar duzu...");
	            //timeTo(te.toString());
	            TimeTo.getMessage(TokenKud.getToken().timeTo(te.toString()));
	        }
	}
	public JPanel makeAtable(List<Status> l1){
		JPanel p1 = new JPanel();
		JTable table = new JTable(new TableG(l1));
		JScrollPane scrollPane = new JScrollPane(table);
		p1.add(scrollPane);
		return p1;
		
	}
}
