package grafikoa;
import logikoa.*;


import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class OrrNagusia extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ImageIcon wlogo = new ImageIcon("src/media1/logoTwitter.png");
	JLabel userId = new JLabel("Hi "+Erabiltzailea.getErab().getUserId());
	JLabel toDo = new JLabel("WHAT DO YOU WANT TO DO?");
	
	//botoien JPanel
	JPanel buttons;
	JButton backup = new JButton("Backup");
	JButton view = new JButton("View");
	JButton export = new JButton("Export");
	JButton logOut = new JButton("LogOut"); //APLIKAZIOTIK IRTETZEKO BOTOIA
	
	//IRUDIAK
	ImageIcon retwImage = new ImageIcon("src/media1/images.png");
	ImageIcon favImage = new ImageIcon("src/media1/fav.jpg");
	
	//AUKERAK JPanel
	JPanel aukerak;
	JCheckBox tweets = new JCheckBox("Tweets",false);
	//JCheckBox rt = new JCheckBox(new ImageIcon("src/media1/images.png"),false);
	//JCheckBox fav = new JCheckBox(new ImageIcon("src/media1/fav.jpg"),false);
	JCheckBox rt = new JCheckBox("RT",false);
	JCheckBox fav = new JCheckBox("FAV",false);
	JCheckBox dm = new JCheckBox("Direct Messages",false);
	JCheckBox followers = new JCheckBox("Followers",false);
	JCheckBox following = new JCheckBox("Following",false);
	JButton doit = new JButton("Do it!");
	
	//JPanel NAGUSIA
	JPanel nagusia = new JPanel(new GridLayout(6, 6));
	JTextArea viewText = new JTextArea();
	
	
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
		has.setSize(900,900);
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
		gehituOsagaia(userId, 1, 1, 3, 1);
		mugak.insets = new Insets(3, 3, 3, 3);
		
		toDo.setFont(new Font("Britannic Bold",Font.BOLD,30));
		gehituOsagaia(toDo, 3, 1, 3, 1);
		mugak.insets = new Insets(3, 3, 3, 3);
		
		//aukerak panela gehitu
		gridJPaukerak();
		gehituOsagaia(aukerak, 5, 1, 3, 1);
		mugak.insets = new Insets(3, 3, 3, 3);
		
		//buttons panela gehitu
		gridJPbuttons();
		gehituOsagaia(buttons, 6, 1, 3, 1);
		mugak.insets = new Insets(3, 3, 3, 3);
		
		//nagusia panela gehitu
		viewPanel();
		gehituOsagaia(nagusia, 7, 3, 3, 1);
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
				viewText.setText("KAIXO HEMEN NAGO");
				//TokenKud.getToken().getFavorites();
				
					//TokenKud.getToken().getFavPage();
					if(rt.isSelected()){
						
					}
					if(fav.isSelected()){
						TokenKud.getToken().getFavPage();
					}
					if(tweets.isSelected()){
						TokenKud.getToken().getTweets();
					}
					if(dm.isSelected()){
						TokenKud.getToken().getDirectMessage();
					}
					if(followers.isSelected()){
						TokenKud.getToken().getFollowers();
					}
					if(following.isSelected()){
						TokenKud.getToken().getFollowing();
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
	public void gridJPaukerak(){
		aukerak = new JPanel(new GridLayout(6, 1));
		aukerak.setBackground(Color.decode("#7ea6e0"));
		aukerak.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		aukerak.add(tweets);
		aukerak.add(fav);
		aukerak.add(rt);
		aukerak.add(dm);
		aukerak.add(followers);
		aukerak.add(following);
		
	}
	public void gridJPbuttons(){
		buttons = new JPanel(new GridLayout(1,4));
		buttons.add(view);
		buttons.add(backup);
		buttons.add(export);
		buttons.add(logOut);
		
	}
	
	public void viewPanel(){
		nagusia = new JPanel(new GridLayout(6,6));
		nagusia.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		this.add(viewText);
	}
}
