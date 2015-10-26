package grafikoa;

import java.io.IOException;

import logikoa.PinKudeaketa;

public class Start {
	public static void main(String[] args) throws IOException {
		PinKudeaketa.getPin().eratuFitxategia();
		/*if(!PinKudeaketa.getPin().badagoFitx()){
			
			PinKudeaketa.getPin().eratuFitxategia();
			Has.bistaratu();
		}else{
			OrrNagusia.bistaratu();
			
		}*/
		
		
	}
}
