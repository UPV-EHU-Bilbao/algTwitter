package twittercomponents;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import db.DBKudeatzaile;
import db.Eragiketak;
import logikoa.TokenKud;
import twitter4j.DirectMessage;
import twitter4j.Paging;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class DirectMessages {
	private static DirectMessages mDirect = null;
	Twitter twitter;
	DBKudeatzaile dbk = DBKudeatzaile.getInstantzia();
	private DirectMessages(){
		twitter = TokenKud.getToken().getMyTwitter();
	}

	public static synchronized DirectMessages getmDirect(){
		if(mDirect == null){
			mDirect = new DirectMessages();
		}return mDirect;
	}
	
	/*
	 * #####################################################################
	 * ###########BACKUP METODOAK________________DATUBASEAN GORDE###########
	 * #####################################################################
	 */
	/**
	 * Erabiltzaileak bidalitako mezuak gorde
	 */
	public void backupNireMessages(){
		long max;
		try{
			max = Eragiketak.getEragiketak().azkenmdId(twitter.getScreenName());
			if(max == 0){
				System.out.println("Ez daukazu md beraz....");
				getsm();
			}else{
				backupSentDirectMessage(max);
			}
		}catch(TwitterException te){
			String time = TokenKud.getToken().timeTo(te.toString());
	           String mezua = "Gehiago lortzeko pixka bat itxaron beharko duzu!: " + time +" seg.";
	   		JOptionPane.showMessageDialog(null,
	   			    mezua,
	   			    "ITXARON",
	   			    JOptionPane.NO_OPTION);
		}
	}
	public void backupSentDirectMessage(long max)throws TwitterException{
		
		 int pagenumber = 1;
         int count = 20;
         List<DirectMessage> directMessages = new ArrayList<DirectMessage>();
         while(true){
       		Paging pag = new Paging(pagenumber, count).sinceId(max);
       		int size = directMessages.size();
       		directMessages = twitter.getSentDirectMessages(pag);
       		directMessages.addAll(twitter.getSentDirectMessages(pag));
       		if(directMessages.size()== size){
       			break;
       		}
         }
         for (DirectMessage message : directMessages) {
             System.out.println("Noiz: "+message.getCreatedAt()+"From: @" + message.getSenderScreenName() + " id:" + message.getId() + " - "
                     + message.getText());
             String[]mezua = new String[3];
             mezua[0] = Long.toString(message.getId());
             mezua[1] = message.getRecipientScreenName();
             mezua[2] = message.getText();
             Eragiketak.getEragiketak().dmGorde(mezua, twitter.getScreenName());
         }
	}
	public void getsm()throws TwitterException{
            Paging paging = new Paging(1);
            List<DirectMessage> messages;
            do {
                messages = twitter.getSentDirectMessages(paging);
                for (DirectMessage message : messages) {
                    System.out.println("From: @" + message.getSenderScreenName() + " id:" + message.getId() + " - "
                            + message.getText());
                    String[]mezua = new String[3];
                    mezua[0] = Long.toString(message.getId());
                    mezua[1] = message.getRecipientScreenName();
                    mezua[2] = message.getText();
                    Eragiketak.getEragiketak().dmGorde(mezua, twitter.getScreenName());
                }
                paging.setPage(paging.getPage() + 1);
            } while (messages.size() > 0 && paging.getPage() < 10);
       
	}
	
	/**
	 * Erabiltzaileari bidalitako mezuen backup -a
	 */
	public void backupHartutakoMessages(){
		long max;
		try{
			max = Eragiketak.getEragiketak().azkenmdId(twitter.getScreenName());
			if(max== 0){
				getm();
			}else{
				backupDirectMessage(max);
			}
		}catch(TwitterException te){
			String time = TokenKud.getToken().timeTo(te.toString());
	           String mezua = "Gehiago lortzeko pixka bat itxaron beharko duzu!: " + time +" seg.";
	   		JOptionPane.showMessageDialog(null,
	   			    mezua,
	   			    "ITXARON",
	   			    JOptionPane.NO_OPTION);
		}
		
	}
	public void getm() throws TwitterException{
            Paging paging = new Paging(1);
            List<DirectMessage> messages;
            do {
                messages = twitter.getDirectMessages(paging);
                for (DirectMessage message : messages) {
                    System.out.println("From: @" + message.getSenderScreenName() + " id:" + message.getId() + " - "
                            + message.getText());
                    String[]mezua = new String[3];
                    mezua[0] = Long.toString(message.getId());
                    mezua[1] = message.getRecipientScreenName();
                    mezua[2] = message.getText();
                    Eragiketak.getEragiketak().dmGorde(mezua, twitter.getScreenName());
                }
                paging.setPage(paging.getPage() + 1);
            } while (messages.size() > 0 && paging.getPage() < 10);
	}
	public void backupDirectMessage(long max)throws TwitterException{
		
		 int pagenumber = 1;
         int count = 20;
         List<DirectMessage> directMessages = new ArrayList<DirectMessage>();
         while(true){
       		Paging pag = new Paging(pagenumber, count).sinceId(max);
       				//, max);
       		int size = directMessages.size();
       		directMessages = twitter.getDirectMessages(pag);
       		//int zenbat = statuses.size();
       		directMessages.addAll(twitter.getDirectMessages(pag));
       		if(directMessages.size()== size){
       		//if(zenbat == 0){
       			break;
       		}
         }
         for (DirectMessage message : directMessages) {
             System.out.println("Noiz: "+message.getCreatedAt()+"From: @" + message.getSenderScreenName() + " id:" + message.getId() + " - "
                     + message.getText());
             String[]mezua = new String[3];
             mezua[0] = Long.toString(message.getId());
             mezua[1] = message.getRecipientScreenName();
             mezua[2] = message.getText();
             Eragiketak.getEragiketak().dmGorde(mezua, twitter.getScreenName());
         }
	}
	/*
	 * #####################################################################
	 * ###########TAULAN BISTARATZEKO METODOA###############################
	 * #####################################################################
	 */
	public ArrayList<String[]> viewFavorites(String user){
		ArrayList<String[]> lista = new ArrayList<String[]>();
		String agindua = "SELECT nork,mezua FROM md WHERE userId='"+user+"' ORDER BY id DESC;";
		try {
			
			ResultSet rs = dbk.execSQL(agindua);
			while(rs.next()){
				String[] status = new String[2];
					status[0] = rs.getString("nork");
					status[1] = rs.getString("mezua");
					lista.add(status);
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
