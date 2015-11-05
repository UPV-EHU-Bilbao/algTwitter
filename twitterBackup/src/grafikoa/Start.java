package grafikoa;

import java.io.IOException;

import db.Eragiketak;
import logikoa.TokenKud;
//import twitter4j.Twitter;
//import twitter4j.TwitterFactory;

public class Start {
	public static void main(String[] args) throws IOException {
		if(Eragiketak.getEragiketak().tokenBilatu() == null){
			Has.bistaratu();
		}else{
			System.out.println(Eragiketak.getEragiketak().tokenBilatu());
			TokenKud.getToken().getSession();
			OrrNagusia.bistaratu();
		}
	}
}
