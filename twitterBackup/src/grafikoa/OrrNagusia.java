package grafikoa;
import logikoa.*;
import twitter4j.TwitterException;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import db.Eragiketak;
import exportOptions.ExcelFile;

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
	JCheckBox fav = new JCheckBox("FAV",false);
	JCheckBox dm = new JCheckBox("Direct Messages",false);
	JCheckBox followers = new JCheckBox("Followers",false);
	JCheckBox following = new JCheckBox("Following",false);
	JButton doit = new JButton("Do it!");
	
	//JPanel NAGUSIA
	JPanel nagusia = new JPanel();
	JTextArea viewText = new JTextArea();
	
	JPanel lag = new JPanel();
	
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
*/
		//getContentPane().add(new nagusia)
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
							DirectMessages.getmDirect().backupDirectMessage();
							DirectMessages.getmDirect().backupSentDirectMessage();
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
						viewText.setText("KAIXO HEMEN NAGO");
						//TokenKud.getToken().getFavorites();
						
							if(fav.isSelected()){
								try {
									bistaratuFav();
									bistaratuNagusia();
								} catch (IllegalStateException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (TwitterException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
							}
							if(tweets.isSelected()){
								try {
									lag = HomeTimeLine.getMhome().getTweets();
									lag.setVisible(true);
									bistaratuNagusia();
								} catch (IllegalStateException | TwitterException e1) {
									System.out.println("ez da panela egin");
								}
							}
							if(dm.isSelected()){
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
						Object[] options = { "Bai", "Ez" };
						int answer = JOptionPane.showOptionDialog(null, "Are you sure?", "EXIT", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE,null, options,options[0]);
						if(answer == JOptionPane.YES_OPTION){
							System.exit(0);
						}
						
						
						}
				});
				export.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						try{
							ExcelFile.getMExcel().createExcel();
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
		aukerak.setLayout(new BoxLayout(aukerak, BoxLayout.Y_AXIS));
		aukerak.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		aukerak.add(tweets);
		aukerak.add(fav);
		aukerak.add(dm);
		aukerak.add(followers);
		aukerak.add(following);
	}
	public void bistaratuNagusia(){
		nagusia.setLayout(new BoxLayout(aukerak, BoxLayout.Y_AXIS));
		nagusia.setVisible(true);
		
	}
	public void bistaratuFav() throws IllegalStateException, TwitterException{
		JTable taula = new JTable(new TableG(Favorites.getMfav().bistaratzeko()));
		JScrollPane scrollPane = new JScrollPane(taula);
		nagusia.add(scrollPane);
	}
	public static void main(String[] args) {
		bistaratu();
		
	}
}