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

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import logikoa.TokenKud;
import twitter4j.TwitterException;

public class PinOrr extends JFrame{
	ImageIcon wlogo = new ImageIcon("src/media1/logoTwitter.png");
	JLabel logoTwitter = new JLabel( new ImageIcon("src/media1/loginTwitter.png")) ;
	JLabel loginOn = new JLabel("LOGIN ON TWITTER");
	JLabel descript = new JLabel("Zuriunean nabigatzailean agertutako zenbakia sar ezazu, mesedez. :)");
	JLabel descript2 = new JLabel("Idatzi ondoren enter sakatu...");
	JTextField jtpin  = new JTextField(7);
	
	private GridBagLayout eskema;
	private Container edukiontzia;
	private GridBagConstraints mugak;
	
	public PinOrr(){
		gridBagHasieratu();
	}
	
	public static void main(String[]args){
		bistaratu();
	}
	public static void bistaratu(){
		PinOrr has = new PinOrr();
		has.setTitle("Twitter App Login");
		has.setVisible(true);
		has.setSize(680,400);
		has.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	private void gridBagHasieratu() {
		edukiontzia = getContentPane();
		eskema = new GridBagLayout();
		edukiontzia.setLayout(eskema);
		mugak = new GridBagConstraints();
		
		getContentPane().setBackground(Color.WHITE);
		
		this.setIconImage(wlogo.getImage());
		
		gehituOsagaia(logoTwitter, 1, 2, 3,1);
		mugak.insets = new Insets(3, 3, 3, 3);
		
		loginOn.setFont(new Font("Britannic Bold",Font.BOLD,40));
		loginOn.setForeground(Color.decode("#7ea6e0"));
		gehituOsagaia(loginOn, 2, 1, 3, 1);
		mugak.insets = new Insets(3, 3, 3, 3);
		
		descript.setFont(new Font("Britannic Bold",Font.BOLD,15));
		descript.setForeground(Color.decode("#7ea6e0"));
		gehituOsagaia(descript, 3, 1, 3, 1);
		mugak.insets = new Insets(3, 3, 3, 3);
		
		gehituOsagaia(jtpin, 4, 1, 3, 1);
		mugak.insets = new Insets(3, 3, 3, 3);
		
		descript2.setFont(new Font("Britannic Bold",Font.BOLD,15));
		descript2.setForeground(Color.decode("#7ea6e0"));
		gehituOsagaia(descript2, 5, 1, 3, 1);
		mugak.insets = new Insets(3, 3, 3, 3);
		
		//TEXT FIELD -ak burutuko duen ekintza
		jtpin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String pin = jtpin.getText();
				try {
					TokenKud.getToken().enterPin(pin);
					
				} catch (TwitterException e1) {
					// TODO Auto-generated catch block
					System.out.println("Zerbait gaizki dago...");
				}
				
				dispose();
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
