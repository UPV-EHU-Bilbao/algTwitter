package grafikoa;

import java.io.IOException;

import db.Eragiketak;
import logikoa.TokenKud;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

public class Start {
	public static void main(String[] args) throws IOException {
		//String token = Eragiketak.getEragiketak().tokenBilatu();
		//String secret = Eragiketak.getEragiketak().tokenSecretBilatu();
	//	String erab = Eragiketak.getEragiketak().erabiltzaileIzena();
		//Twitter t = new TwitterFactory().getInstance();
		//System.out.println(t);
		if(Eragiketak.getEragiketak().tokenBilatu() == null){
			Has.bistaratu();
		}else{
			TokenKud.getToken().getSession();
			OrrNagusia.bistaratu();
		}
	}
}
