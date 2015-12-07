package twittercomponents;

import java.util.List;

import db.Eragiketak;
import exceptions.TimeTo;
import logikoa.TokenKud;
import twitter4j.DirectMessage;
import twitter4j.Paging;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class DirectMessages {
	private static DirectMessages mDirect = null;
	Twitter twitter;
	
	private DirectMessages(){
		twitter = TokenKud.getToken().getMyTwitter();
	}

	public static synchronized DirectMessages getmDirect(){
		if(mDirect == null){
			mDirect = new DirectMessages();
		}return mDirect;
	}
	
	/*
	 * ###########################################
	 * ###########BISTARATZEKO METODOAK###########
	 * ###########################################
	 */
	public void getDirectMessage(){
        try {
            Paging paging = new Paging(1);
            List<DirectMessage> messages;
            do {
                messages = twitter.getDirectMessages(paging);
                for (DirectMessage message : messages) {
                    System.out.println("Noiz: "+message.getCreatedAt()+"From: @" + message.getSenderScreenName() + " id:" + message.getId() + " - "
                            + message.getText());
                }
                paging.setPage(paging.getPage() + 1);
            } while (messages.size() > 0 && paging.getPage() < 10);
            System.out.println("done.");
            //System.exit(0);
        } catch (TwitterException te) {
        	System.out.println("Gehiago lortzeko pixka bat itxaron behar duzu...");
            TimeTo.getMessage(TokenKud.getToken().timeTo(te.toString()));
        }
	}
	public void getSentDirectMessage(){
		 try {
	            Paging page = new Paging(1);
	            List<DirectMessage> directMessages;
	            do {
	                directMessages = twitter.getSentDirectMessages(page);
	                for (DirectMessage message : directMessages) {
	                    System.out.println("Noiz: "+message.getCreatedAt()+"To: @" + message.getRecipientScreenName() + " id:" + message.getId() + " - "
	                            + message.getText());
	                    String[]mezua = new String[3];
	                    mezua[0] = Long.toString(message.getId());
	                    mezua[1] = message.getRecipientScreenName();
	                    mezua[2] = message.getText();
	                    Eragiketak.getEragiketak().dmGorde(mezua, twitter.getScreenName());
	                }
	                page.setPage(page.getPage() + 1);
	            } while (directMessages.size() > 0 && page.getPage() < 10);
	            System.out.println("done.");
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
	public void backupSentDirectMessage(){
		 try {
	            Paging page = new Paging(1);
	            List<DirectMessage> directMessages;
	            do {
	                directMessages = twitter.getSentDirectMessages(page);
	                for (DirectMessage message : directMessages) {
	                    System.out.println("Noiz: "+message.getCreatedAt()+"To: @" + message.getRecipientScreenName() + " id:" + message.getId() + " - "
	                            + message.getText());
	                    String[]mezua = new String[3];
	                    mezua[0] = Long.toString(message.getId());
	                    mezua[1] = message.getRecipientScreenName();
	                    mezua[2] = message.getText();
	                    Eragiketak.getEragiketak().dmGorde(mezua, twitter.getScreenName());
	                }
	                page.setPage(page.getPage() + 1);
	            } while (directMessages.size() > 0 && page.getPage() < 10);
	            System.out.println("done.");
	            //System.exit(0);
	        } catch (TwitterException te) {
	        	System.out.println("Gehiago lortzeko pixka bat itxaron behar duzu...");
	            TimeTo.getMessage(TokenKud.getToken().timeTo(te.toString()));
	        }
	}
	public void backupDirectMessage(){
        try {
            Paging paging = new Paging(1);
            List<DirectMessage> messages;
            do {
                messages = twitter.getDirectMessages(paging);
                for (DirectMessage message : messages) {
                    System.out.println("Noiz: "+message.getCreatedAt()+"From: @" + message.getSenderScreenName() + " id:" + message.getId() + " - "
                            + message.getText());
                    String[]mezua = new String[3];
                    mezua[0] = Long.toString(message.getId());
                    mezua[1] = message.getRecipientScreenName();
                    mezua[2] = message.getText();
                    Eragiketak.getEragiketak().dmGorde(mezua, twitter.getScreenName());
                }
                paging.setPage(paging.getPage() + 1);
            } while (messages.size() > 0 && paging.getPage() < 10);
            System.out.println("done.");
            //System.exit(0);
        } catch (TwitterException te) {
        	System.out.println("Gehiago lortzeko pixka bat itxaron behar duzu...");
            TimeTo.getMessage(TokenKud.getToken().timeTo(te.toString()));
        }
	}
	
}
