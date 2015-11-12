/*
 * Copyright 2007 Yusuke Yamamoto
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package twitter4j.examples.favorite;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Lists favorited statuses
 *
 * @author Yusuke Yamamoto - yusuke at mac.com
 */
public final class GetFavoritesproba {
    /**
     * Usage: java twitter4j.examples.favorite.GetFavorites
     *
     * @param args message
     * @throws InterruptedException 
     */
   public static void main(String[] args) throws InterruptedException {
	
        try {
            int pagenumber = 1;
            int count = 20;
            List<Status> statuses = new ArrayList<Status>();
            
        	ConfigurationBuilder cb = new ConfigurationBuilder();
        	cb.setOAuthConsumerKey("9vj1uaNEO4T6AUQc7OEUw0yOm");
        	cb.setOAuthConsumerSecret("LkzFqvGzV19fBdW1baOHY5ZkWazSm6HudWWCXBRr8redigzRca");
        	cb.setOAuthAccessToken("3750039196-JLCU91Is6RNHgwwzUEbYYNlIwxqReKsg6C7wYG1");
        	cb.setOAuthAccessTokenSecret("Z5ugnSlUvhQuOYjFoQbKrxYcCkkT84cNnjYJbHYCyP5fH");
        	
        	Twitter twitter = new TwitterFactory(cb.build()).getInstance();
        	while(true){
        	  Paging page = new Paging(pagenumber, count);
        	  int size = statuses.size();
        	  statuses.addAll(twitter.getFavorites(page));
        	  if(statuses.size()== size){
            	break;
        	  }
            
        	  for (Status status : statuses) {
                System.out.println(status.getId()+" @" + status.getUser().getScreenName() + " - " + status.getText());
        	  }
            //System.out.println("done.");
        	  //System.exit(0);
        	}
        } catch (TwitterException te) {
            te.printStackTrace();
            String errorea = te.toString();
            String azpikatea = errorea.substring(errorea.indexOf("secondsUntilReset"),errorea.indexOf("secondsUntilReset")+ "secondsUntilReset=000".length());
            //System.out.println("Failed to get favorites: " + te.getMessage());
            String[] zatiak = azpikatea.split("=");
            System.out.println("Falta diren segunduak:" + zatiak[1]);
            System.out.println("Gehiago lortzeko pixka bat itxaron behar duzu...");
            
            System.exit(-1);
        }
        }
    
}
