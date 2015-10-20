package grafikoa;

import logikoa.PinKudeaketa;

public class Start {
	public static void main(String[] args) {
		if(PinKudeaketa.getPin().badagoFitx()){
			OrrNagusia.bistaratu();
		}else{
			Has.bistaratu();
		}
		
		
	}
}
