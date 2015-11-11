package twitter4j.examples.tweets;
import java.util.ArrayList;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class GetAllTweets {
	
		public static void main (String args[]) {
			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setOAuthConsumerKey("");
			cb.setOAuthConsumerSecret("");
			cb.setOAuthAccessToken("");
			cb.setOAuthAccessTokenSecret("");
			//meter datos de twitter
			Twitter twitter = new TwitterFactory().getInstance();
		        ArrayList <Status> statuses = new ArrayList<Status>();
		        int pageno = 1;
		        long id = statuses.lastIndexOf(statuses);
		        while (true) {
		            try {
		                int size = statuses.size();
		                Paging page = new Paging(pageno++, 100).sinceId(id);
		                statuses.addAll(twitter.getUserTimeline(page));
		                id = statuses.lastIndexOf(statuses);
		                if (statuses.size() == size || pageno==3) {
		                    break;
		                }
		            } catch (TwitterException e) {
		            
		            }
		            System.out.println(statuses);
		        }
		     
		}
}
