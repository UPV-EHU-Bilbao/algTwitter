package logikoa;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
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
	
	public void eratuFitxategia() throws IOException{
		tokenFitx = new File("logikoa/token.txt");	
	}
	
	public boolean badagoFitx(){
		return tokenFitx.exists();
	}
	public void consumerGorde(String key, String secret) throws IOException{
		if (tokenFitx.exists()){
			BufferedWriter bw = new BufferedWriter(new FileWriter(tokenFitx));
			bw.write("Consumer key: "+key+"\n");
			bw.write("Consumer secret: "+secret+"\n");
		}
	}
	public void tokenGorde(String token1, String token2) throws IOException{
		if (tokenFitx.exists()){
			BufferedWriter bw = new BufferedWriter(new FileWriter(tokenFitx));
			bw.write("AccesToken: "+token1+"\n");
			bw.write("AccesTokenSecret: "+token2+"\n");
		}
	}
	public long getLength(){
		return tokenFitx.length();
	}
	
}
