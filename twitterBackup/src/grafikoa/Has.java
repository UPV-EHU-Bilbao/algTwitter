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

import javax.swing.*;
import twitter4j.examples.oauth.*;

public class Has extends JFrame{
	ImageIcon wlogo = new ImageIcon("src/media1/logoTwitter.png");
	JLabel logoTwitter = new JLabel( new ImageIcon("src/media1/loginTwitter.png")) ;
	JLabel loginOn = new JLabel("LOGIN ON TWITTER");
	JButton login = new JButton("LOGIN");
	
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
		has.setSize(450,500);
		has.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	private void gridBagHasieratu() {
		edukiontzia = getContentPane();
		eskema = new GridBagLayout();
		edukiontzia.setLayout(eskema);
		mugak = new GridBagConstraints();
		
		getContentPane().setBackground(Color.WHITE);
		
		this.setIconImage(wlogo.getImage());
		
		login.setBackground(Color.decode("#cccccc"));
		//login.setBounds(20, 30, 30, 20);
		gehituOsagaia(login, 9, 1, 5, 1);
		mugak.insets = new Insets(3, 3, 3, 3);
		
		loginOn.setFont(new Font("Britannic Bold",Font.BOLD,44));
		loginOn.setForeground(Color.decode("#7ea6e0"));
		gehituOsagaia(loginOn, 4, 2, 3, 1);
		mugak.insets = new Insets(3, 3, 3, 3);
		
		//logoTwitter.s
		gehituOsagaia(logoTwitter, 1, 3, 1,1);
		mugak.insets = new Insets(3, 3, 3, 3);
		
		login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GetAccessToken.konektatu();
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
