package logikoa;

import java.io.File;

public class PinKudeaketa {
	private static PinKudeaketa mPin = null;
	private File tokenFitx = null;
	
	private PinKudeaketa(){}
	public static synchronized PinKudeaketa getPin(){
		if(mPin == null){
			mPin = new PinKudeaketa();
		}return mPin;
	}
	
	public void eratuFitxategia(String pErab){
		tokenFitx = new File("src/"+pErab+"token");
	}
	
	public boolean badagoFitx(){
		return tokenFitx.exists();
	}
	
}
