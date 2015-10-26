package logikoa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class PinKudeaketa {
	private static PinKudeaketa mPin = null;
	private File tokenFitx = null;
	private FileOutputStream fileOs = null;
	
	private PinKudeaketa(){}
	public static synchronized PinKudeaketa getPin(){
		if(mPin == null){
			mPin = new PinKudeaketa();
		}return mPin;
	}
	
	public void eratuFitxategia(String paccess, String pSecret) throws IOException{
		tokenFitx = new File("src/logikoa/token");
		fileOs = new FileOutputStream(tokenFitx);
		String content = paccess + "n" + pSecret;
		//tokenGorde(paccess, pSecret);
		if(!tokenFitx.exists()){
			tokenFitx.createNewFile();}
		
			byte[]contentInBytes = content.getBytes();
			fileOs.write(contentInBytes);
			fileOs.flush();
			fileOs.close();
		
	}
	
	public boolean badagoFitx(){
		return tokenFitx.exists();
	}
	
	public void tokenGorde(String token1, String token2) throws FileNotFoundException{
		PrintWriter pw = new PrintWriter("twitter4j.properties");
		pw.println("oauth.accessToken="+token1);
		pw.println("oauth.accessTokenSecret="+token2);
			//tokenFitx.close()
	}
	
}
