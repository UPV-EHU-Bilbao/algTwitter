package grafikoa;
import twitter4j.examples.*;
import twitter4j.examples.favorite.GetFavorites;
import logikoa.*;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class OrrNagusia extends JFrame{
	ImageIcon wlogo = new ImageIcon("src/media1/logoTwitter.png");
	JLabel userId = new JLabel("Hi"+Erabiltzailea.getErab().getUserId());
	JLabel toDo = new JLabel("WHAT DO YOU WANT TO DO?");
	
	JButton backup = new JButton("Backup");
	JButton view = new JButton("View");
	JButton export = new JButton("Export");
	
	
	//APLIKAZIOTIK IRTETZEKO BOTOIA
	JButton logOut = new JButton("LogOut");
	
	//IRUDIAK
	ImageIcon retwImage = new ImageIcon("src/media1/images.png");
	ImageIcon favImage = new ImageIcon("src/media1/fav.jpg");
	
	//AUKERAK
	JCheckBox tweets = new JCheckBox("Tweets",false);
	//JCheckBox rt = new JCheckBox(new ImageIcon("src/media1/images.png"),false);
	//JCheckBox fav = new JCheckBox(new ImageIcon("src/media1/fav.jpg"),false);
	JCheckBox rt = new JCheckBox("RT",false);
	JCheckBox fav = new JCheckBox("FAV",false);
	JCheckBox dm = new JCheckBox("Direct Messages",false);
	JCheckBox followers = new JCheckBox("Followers",false);
	JCheckBox following = new JCheckBox("Following",false);
	JButton doit = new JButton("Do it!");
	
	
	
	private GridBagLayout eskema;
	private Container edukiontzia;
	private GridBagConstraints mugak;
	
	public OrrNagusia(){
		gridBagHasieratu();
	}
	public static void main(String[]args){
		bistaratu();
	}
	public static void bistaratu(){
		OrrNagusia has = new OrrNagusia();
		has.setTitle("Twitter App Login");
		has.setVisible(true);
		has.setSize(700,550);
		has.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	private void gridBagHasieratu() {
		edukiontzia = getContentPane();
		eskema = new GridBagLayout();
		edukiontzia.setLayout(eskema);
		mugak = new GridBagConstraints();
		
		getContentPane().setBackground(Color.decode("#7ea6e0"));
		
		this.setIconImage(wlogo.getImage());
		
		
		userId.setFont(new Font("Arial",Font.BOLD,40));
		gehituOsagaia(userId, 0, 0, 3, 1);
		mugak.insets = new Insets(3, 3, 3, 3);
		
		toDo.setFont(new Font("Britannic Bold",Font.BOLD,30));
		gehituOsagaia(toDo, 3, 1, 3, 1);
		mugak.insets = new Insets(3, 3, 3, 3);
		
		
		tweets.setBackground(Color.decode("#7ea6e0"));
		gehituOsagaia(tweets, 5, 1, 5, 1);
		mugak.insets = new Insets(3, 3, 3, 3);
		
		rt.setBackground(Color.decode("#7ea6e0"));
		gehituOsagaia(rt, 5, 3, 5, 1);
		mugak.insets = new Insets(3, 3, 3, 3);
		
		fav.setBackground(Color.decode("#7ea6e0"));
		gehituOsagaia(fav, 5, 4, 5, 1);
		mugak.insets = new Insets(3, 3, 3, 3);
		
		dm.setBackground(Color.decode("#7ea6e0"));
		gehituOsagaia(dm, 8, 1, 5, 1);
		mugak.insets = new Insets(3, 3, 3, 3);
		
		followers.setBackground(Color.decode("#7ea6e0"));
		gehituOsagaia(followers, 8, 4, 5, 1);
		mugak.insets = new Insets(3, 3, 3, 3);
		
		following.setBackground(Color.decode("#7ea6e0"));
		gehituOsagaia(following, 8, 11, 5, 1);
		mugak.insets = new Insets(3, 3, 3, 3);
		
		
		backup.setBackground(Color.decode("#cccccc"));
		//login.setBounds(20, 30, 30, 20);
		gehituOsagaia(backup, 11, 0, 3, 1);
		mugak.insets = new Insets(3, 3, 3, 3);
		
		view.setBackground(Color.decode("#cccccc"));
		gehituOsagaia(view, 11, 3, 3, 1);
		mugak.insets = new Insets(3, 3, 3, 3);
		
		export.setBackground(Color.decode("#cccccc"));
		gehituOsagaia(export, 11, 8, 3, 1);
		mugak.insets = new Insets(3, 3, 3, 3);
		
		logOut.setBackground(Color.decode("#cccccc"));
		gehituOsagaia(logOut, 12, 12, 5, 1);
		mugak.insets = new Insets(3, 3, 3, 3);
		
		
		
		
		
		//ORRNAGUSI -an dauden botoiak
		backup.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(rt.isSelected()){
					
				}
				if(fav.isSelected()){
					
				}
				if(tweets.isSelected()){
					
				}
				if(dm.isSelected()){
					
				}
				if(followers.isSelected()){
					
				}
				if(following.isSelected()){
					
				}	
			}
		});
		
		view.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(rt.isSelected()){
					
				}
				if(fav.isSelected()){
					TokenKud.getToken().getFavorites();
				}
				if(tweets.isSelected()){
					TokenKud.getToken().getTweets();
				}
				if(dm.isSelected()){
					TokenKud.getToken().getDirectMessage();
				}
				if(followers.isSelected()){
					
				}
				if(following.isSelected()){
					
				}
				
			}
		});
		
		//ERABILTZAILEAREN SAIOA ITXI, HAU DA, APLIKAZIOTIK GUZTIA EZABATU
		logOut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.
				
				int answer = JOptionPane.showConfirmDialog(null, "Are you sure?", "EXIT", JOptionPane.YES_NO_OPTION);
				if(answer == JOptionPane.YES_OPTION){
					dispose();
				}
				
				
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
