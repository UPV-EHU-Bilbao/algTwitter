package logikoa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class PinKudeaketa {
	private static PinKudeaketa mPin = null;
	private File tokenFitx = null;
	
	private PinKudeaketa(){}
	public static synchronized PinKudeaketa getPin(){
		if(mPin == null){
			mPin = new PinKudeaketa();
		}return mPin;
	}
	
	public void eratuFitxategia(String paccess, String pSecret) throws FileNotFoundException{
		tokenFitx = new File("src/token");
		tokenGorde(paccess, pSecret);
	}
	
	public boolean badagoFitx(){
		return tokenFitx.exists();
	}
	
	public void tokenGorde(String token1, String token2) throws FileNotFoundException{
		PrintWriter pw = new PrintWriter(tokenFitx);
		pw.println("oauth.accessToken="+token1);
		pw.println("oauth.accessTokenSecret="+token2);
			//tokenFitx.close()
	}
	
}
