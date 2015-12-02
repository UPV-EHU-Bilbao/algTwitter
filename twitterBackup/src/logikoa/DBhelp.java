package logikoa;

import db.Eragiketak;
import twitter4j.Twitter;

public class DBhelp {
	private static DBhelp nireHelp = null;
	private DBhelp(){}
	public static synchronized DBhelp getHelp(){
		if( nireHelp == null){
			nireHelp = new DBhelp();
		}return nireHelp;
	}
	public void updateDB(Twitter nireTwitt){
		long tweet = Eragiketak.getEragiketak().azkenTweetId();
		
	}
}
