package grafikoa;
import logikoa.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import db.Eragiketak;

public class OrrNagusia extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ImageIcon wlogo = new ImageIcon("src/media1/logoTwitter.png");
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
	JPanel p1 = new JPanel();
	//IRUDIAK
	ImageIcon retwImage = new ImageIcon("src/media1/images.png");
	ImageIcon favImage = new ImageIcon("src/media1/fav.jpg");
	
	//AUKERAK JPanel
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
	JPanel nagusia = new JPanel();
	JTextArea viewText = new JTextArea();
	
	public OrrNagusia(){
		getContentPane().setBackground(Color.decode("#7ea6e0"));
		this.setIconImage(wlogo.getImage());
		
		//HEADERS
		header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
		header.setBackground(Color.decode("#7ea6e0"));
		userId.setFont(new Font("Arial",Font.BOLD,40));
		header.add(userId);
		toDo.setFont(new Font("Britannic Bold",Font.BOLD,30));
		header.add(toDo);
		
		
		getContentPane().add(header, BorderLayout.NORTH);
		
		//BOTOIAK
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		buttons.setBackground(Color.decode("#7ea6e0"));
		buttons.add(view);
		buttons.add(backup);
		buttons.add(export);
		
		getContentPane().add(buttons, BorderLayout.SOUTH);
		
		//AUKERAK
		bistaratuAukerak();
		getContentPane().add(aukerak, BorderLayout.WEST);
		
		//LOGOUT
		p1.setLayout(new BoxLayout(p1, BoxLayout.X_AXIS));
		p1.setBackground(Color.decode("#7ea6e0"));
		p1.add(logOut);
		getContentPane().add(p1, BorderLayout.EAST);
		
		//PANTAILARATZEA
		//nagusia.setLayout(mgr);
		nagusia.setBackground(Color.decode("#7ea6e0"));
		nagusia.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		getContentPane().add(nagusia, BorderLayout.CENTER);
		/* http://stackoverflow.com/questions/218155/how-do-i-change-jpanel-inside-a-jframe-on-the-fly
		 * myJFrame.getContentPane().removeAll()
myJFrame.getContentPane().invalidate()

myJFrame.getContentPane().add(newContentPanel)
myJFrame.getContentPane().revalidate()
		 */
		
		((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
	}
	private void actionListener(){
		//ORRNAGUSI -an dauden botoiak
				backup.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(rt.isSelected()){
							//TokenKud.getToken().
						}
						if(fav.isSelected()){
							TokenKud.getToken().backupFavorites();
						}
						if(tweets.isSelected()){
							TokenKud.getToken().backupTweets();
						}
						if(dm.isSelected()){
							//TokenKud.getToken().getSentDirectMessage();
						}
						if(followers.isSelected()){
							TokenKud.getToken().backupFollowers();
						}
						if(following.isSelected()){
							TokenKud.getToken().backupFollowing();
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
								//TokenKud.getToken().backupRt();
							}
							if(fav.isSelected()){
								//TokenKud.getToken().getFavPage();
							}
							if(tweets.isSelected()){
								TokenKud.getToken().tweetTaula();
								//TokenKud.getToken().aldatuVisible();
							}
							if(dm.isSelected()){
								TokenKud.getToken().getSentDirectMessage();
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
						Object[] options = { "Bai", "Ez" };
						int answer = JOptionPane.showOptionDialog(null, "Are you sure?", "EXIT", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE,null, options,options[0]);
						if(answer == JOptionPane.YES_OPTION){
							System.exit(0);
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
		aukerak.setLayout(new BoxLayout(aukerak, BoxLayout.Y_AXIS));
		aukerak.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		aukerak.add(tweets);
		aukerak.add(fav);
		aukerak.add(rt);
		aukerak.add(dm);
		aukerak.add(followers);
		aukerak.add(following);
	}
	
	public static void main(String[] args) {
		bistaratu();
		
	}
}