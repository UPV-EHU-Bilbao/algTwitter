package grafikoa;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.*;


import logikoa.TokenKud;
import twitter4j.TwitterException;
import twitter4j.examples.oauth.*;

public class Has extends JFrame{
	ImageIcon wlogo = new ImageIcon("src/media1/logoTwitter.png");
	JLabel logoTwitter = new JLabel( new ImageIcon("src/media1/loginTwitter.png")) ;
	JLabel loginOn = new JLabel("WELCOME! / ONGI ETORRI!");
	JLabel descript = new JLabel("Aurrera botoia sakatzean nabigatzailea irekiko da, ");
	JLabel descript2 = new JLabel("bertan agertzen zaizkizun pausuak jarraitu ondoren,");
	JLabel descript3 = new JLabel("zuriunean pantailan agertzen zaizkizun zenbakiak kopiatu!");
	//JTextField jtpin  = new JTextField(7);
	JButton login = new JButton("Aurrera");
	
	private GridBagLayout eskema;
	private Container edukiontzia;
	private GridBagConstraints mugak;
	
	public Has(){
		gridBagHasieratu();
	}
	public static void main(String[]args){
		bistaratu();
	}
	public static void bistaratu(){
		Has has = new Has();
		has.setTitle("Twitter App Login");
		has.setVisible(true);
		has.setSize(900,400);
		has.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	private void gridBagHasieratu() {
		edukiontzia = getContentPane();
		eskema = new GridBagLayout();
		edukiontzia.setLayout(eskema);
		mugak = new GridBagConstraints();
		
		getContentPane().setBackground(Color.WHITE);
		
		this.setIconImage(wlogo.getImage());
		
		/*jtpin.setSize(100,155);
		gehituOsagaia(jtpin, 9, 4, 3, 1);
		mugak.insets = new Insets(3, 3, 3, 3);*/
		
		loginOn.setFont(new Font("Britannic Bold",Font.BOLD,40));
		loginOn.setForeground(Color.decode("#7ea6e0"));
		gehituOsagaia(loginOn, 4, 1, 3, 1);
		mugak.insets = new Insets(3, 3, 3, 3);
		
		descript.setFont(new Font("Britannic Bold",Font.BOLD,15));
		descript.setForeground(Color.decode("#7ea6e0"));
		gehituOsagaia(descript, 5, 1, 3, 1);
		mugak.insets = new Insets(3, 3, 3, 3);
		
		descript2.setFont(new Font("Britannic Bold",Font.BOLD,15));
		descript2.setForeground(Color.decode("#7ea6e0"));
		gehituOsagaia(descript2, 6, 1, 3, 1);
		mugak.insets = new Insets(3, 3, 3, 3);
		
		descript3.setFont(new Font("Britannic Bold",Font.BOLD,15));
		descript3.setForeground(Color.decode("#7ea6e0"));
		gehituOsagaia(descript3, 7, 1, 3, 1);
		mugak.insets = new Insets(3, 3, 3, 3);
		
		//logoTwitter.s
		gehituOsagaia(logoTwitter, 1, 4, 3,1);
		mugak.insets = new Insets(3, 3, 3, 3);
		
		
		gehituOsagaia(login, 8, 4, 3, 1);
		mugak.insets = new Insets(3, 3, 3, 3);
		login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//String pin = jtpin.getText();
				try {
					TokenKud.getToken().hasieratuToken();
					PinOrr.bistaratu();
					dispose();
				} catch (TwitterException | IOException e1) {
					// TODO Auto-generated catch block
					System.out.println("Zerbait gaizki dago...");
				}
				//GetAccessToken.konektatu();
				
			}
		});
		
	}
	private void gehituOsagaia(Component osagaia, int errenkada, int zutabea, int zabalera, int altuera) {
		mugak.gridx = zutabea;
		mugak.gridy = errenkada;

		mugak.gridwidth = zabalera;
		mugak.gridheight = altuera;

		eskema.setConstraints(osagaia, mugak);
		edukiontzia.add(osagaia);
	}
}
