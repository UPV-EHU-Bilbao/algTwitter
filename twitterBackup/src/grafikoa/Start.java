package grafikoa;

import java.io.IOException;

import db.Eragiketak;
import logikoa.TokenKud;

public class Start {
	public static void main(String[] args) throws IOException {
		String token = Eragiketak.getEragiketak().tokenBilatu();
		String secret = Eragiketak.getEragiketak().tokenSecretBilatu();
		String erab = Eragiketak.getEragiketak().erabiltzaileIzena();
		/*if (Eragiketak.getEragiketak().tokenBilatu()!=null){
			TokenKud.getToken().getSession();
		}else{
			Has.bistaratu();
		}*/
		
	}
}
