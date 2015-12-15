package grafikoa;
import logikoa.*;
import twittercomponents.DirectMessages;
import twittercomponents.Favorites;
import twittercomponents.Followers;
import twittercomponents.Following;
import twittercomponents.HomeTimeLine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import db.Eragiketak;
import exportOptions.ExcelFile;

public class OrrNagusia extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ImageIcon wlogo = new ImageIcon(getClass().getResource("/media1/logoTwitter.png"));
	JLabel userId = new JLabel("Hi "+Erabiltzailea.getErab().getUserId());
	
	//HEADER
	JLabel toDo = new JLabel("WHAT DO YOU WANT TO DO?");
	JPanel header = new JPanel();
	JPanel aukerak = new JPanel();
	
	//botoien JPanel
	JPanel buttons = new JPanel();
	JButton backup = new JButton("Backup");
	JButton view = new JButton("View");
	JButton export = new JButton("Export");
	
	//APLIKAZIOTIK IRTETZEKO BOTOIA
	JButton logOut = new JButton("LogOut"); 
	
	
	//AUKERAK JPanel
	JCheckBox tweets = new JCheckBox("Tweets",false);
	JCheckBox fav = new JCheckBox("FAV",false);
	JCheckBox dm = new JCheckBox("Direct Messages",false);
	JCheckBox followers = new JCheckBox("Followers",false);
	JCheckBox following = new JCheckBox("Following",false);
	JButton doit = new JButton("Do it!");
	
	//JPanel NAGUSIA
	JPanel nagusia = new JPanel();
	
	JLabel back = new JLabel(new ImageIcon(getClass().getResource("/media1/twitterNew.png")));
	
	JPanel aldagaia = new JPanel();
	

	
	
	public OrrNagusia(){
		getContentPane().setBackground(Color.decode("#7ea6e0"));
		this.setIconImage(wlogo.getImage());
		
		//HEADERS
		header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
		header.setBackground(Color.decode("#7ea6e0"));
		userId.setFont(new Font("Century Gothic",Font.BOLD,40));
		header.add(userId);
		toDo.setFont(new Font("Britannic Bold",Font.BOLD,30));
		header.add(toDo);
		
		
		getContentPane().add(header, BorderLayout.NORTH);
		
		//BOTOIAK
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		buttons.setBackground(Color.decode("#7ea6e0"));
		Font f = new Font("Century Gothic",Font.BOLD,10);
		view.setFont(f);
		backup.setFont(f);
		export.setFont(f);
		logOut.setFont(f);
		buttons.add(view);
		buttons.add(backup);
		buttons.add(export);
		buttons.add(logOut);
		
		getContentPane().add(buttons, BorderLayout.SOUTH);
		
		//AUKERAK
		bistaratuAukerak();
		getContentPane().add(aukerak, BorderLayout.WEST);
		
		
		
		//PANTAILARATZEA
		nagusia.setBackground(Color.decode("#7ea6e0"));
		nagusia.setLayout(new BorderLayout());
		//nagusia.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		nagusia.setBorder(BorderFactory.createBevelBorder(NORMAL));
		nagusia.add(back, BorderLayout.CENTER);
		getContentPane().add(nagusia, BorderLayout.CENTER);
		aldagaia = nagusia;
		
		getContentPane().revalidate();
		 
		
		((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
	}
	private void actionListener(){
		//ORRNAGUSI -an dauden botoiak
				backup.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(fav.isSelected()){
							Favorites.getMfav().backupFavorites();
						}
						if(tweets.isSelected()){
							HomeTimeLine.getMhome().backupTweets();
						}
						if(dm.isSelected()){
							DirectMessages.getmDirect().backupNireMessages();
							DirectMessages.getmDirect().backupHartutakoMessages();
						}
						if(followers.isSelected()){
							Followers.getMfollowers().backupFollowers();
						}
						if(following.isSelected()){
							Following.getMfollowing().backupFollowing();
						}	
					}
				});
				
				view.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
							if(fav.isSelected()){
									TDemoFav td = new TDemoFav(Erabiltzailea.getErab().getUserId());
									td.createAndShowGUI();
									remove(aldagaia);
									aldagaia = td;
									getContentPane().add(td,BorderLayout.CENTER);
									getContentPane().revalidate();
									getContentPane().repaint();
								
							}
							if(tweets.isSelected()){
								TDemoTweets td = new TDemoTweets(Erabiltzailea.getErab().getUserId());
								td.createAndShowGUI();
								remove(aldagaia);
								aldagaia = td;
								getContentPane().add(td,BorderLayout.CENTER);
								getContentPane().revalidate();
								getContentPane().repaint();
							}
							if(dm.isSelected()){
								TDemoMD td = new TDemoMD(Erabiltzailea.getErab().getUserId());
								td.createAndShowGUI();
								remove(aldagaia);
								aldagaia = td;
								getContentPane().add(td,BorderLayout.CENTER);
								getContentPane().revalidate();
								getContentPane().repaint();
							}
							if(followers.isSelected()){
								TDemoFollowers td = new TDemoFollowers(Erabiltzailea.getErab().getUserId());
								td.createAndShowGUI();
								remove(aldagaia);
								aldagaia = td;
								getContentPane().add(td,BorderLayout.CENTER);
								getContentPane().revalidate();
								getContentPane().repaint();
							}
							if(following.isSelected()){

								TDemoFollowing td = new TDemoFollowing(Erabiltzailea.getErab().getUserId());
								td.createAndShowGUI();
								remove(aldagaia);
								aldagaia = td;
								getContentPane().add(td,BorderLayout.CENTER);
								getContentPane().revalidate();
								getContentPane().repaint();
							}
						
						
					}
				});
				
				//ERABILTZAILEAREN SAIOA ITXI, HAU DA, APLIKAZIOTIK GUZTIA EZABATU
				logOut.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						//JOptionPane.
						Object[] options = { "Bai", "Ez" };
						int answer = JOptionPane.showOptionDialog(null, "Ziur zaude? Zure datu guztiak aplikaziotik ezabatuko dira", "EXIT", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE,null, options,options[0]);
						if(answer == JOptionPane.YES_OPTION){
							Eragiketak.getEragiketak().deleteAll(Erabiltzailea.getErab().getUserId());
							JOptionPane.showMessageDialog(null,
								    "DATUAK EZABATUTA =(",
								    "AGUR BENHUR!",
								    JOptionPane.NO_OPTION);
							System.exit(0);
						}
						
						
						}
				});
				export.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						try{
							ExcelFile.getMExcel().createExcel(Erabiltzailea.getErab().getUserId());
							JOptionPane.showMessageDialog(null,
								    "Zure fitxategia eratu da!.",
								    "Info",
								    JOptionPane.PLAIN_MESSAGE);
						}catch(Exception ex){
							JOptionPane.showMessageDialog(null,
								    "EZ DA FITXATEGIA ERATU.",
								    "ERROR",
								    JOptionPane.ERROR_MESSAGE);
						}
							
						
						
					}
				});
					
	}
	public static void bistaratu(){
		OrrNagusia n = new OrrNagusia();
		n.setTitle("Twitter App");
		n.setVisible(true);
		n.setDefaultCloseOperation(EXIT_ON_CLOSE);
		n.pack();
		n.actionListener();
	}
	private void bistaratuAukerak(){
		Font f = new Font("Century Gothic",Font.BOLD,15);
		aukerak.setLayout(new BoxLayout(aukerak, BoxLayout.Y_AXIS));
		aukerak.setBorder(BorderFactory.createBevelBorder(NORMAL));
		aukerak.setOpaque(false);
		tweets.setFont(f);
		fav.setFont(f);
		dm.setFont(f);
		followers.setFont(f);
		following.setFont(f);
		ButtonGroup cbg = new ButtonGroup();
		cbg.add(tweets);
		cbg.add(fav);
		cbg.add(dm);
		cbg.add(followers);
		cbg.add(following);
		aukerak.add(tweets);
		aukerak.add(fav);
		aukerak.add(dm);
		aukerak.add(followers);
		aukerak.add(following);
	}
	public static void main(String[] args) {
		
		bistaratu();
		
	}
}