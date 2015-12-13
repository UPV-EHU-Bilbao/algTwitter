package grafikoa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import db.Eragiketak;
import exceptions.Empty;
import exceptions.NotUser;
import logikoa.Erabiltzailea;
import logikoa.TokenKud;


public class Start extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel nagusia = new JLabel(new ImageIcon("src/media1/backTwitter.jpg"));
	
	ImageIcon wlogo = new ImageIcon("src/media1/logoTwitter.png");
	JLabel logoTwitter = new JLabel( new ImageIcon("src/media1/loginTwitter.png")) ;
	JLabel welcome = new JLabel("Ongi etorri TWITTER APP aplikaziora");
	JPanel panel = new JPanel();
	//erabiltzaile hauen saioa jadanik gordeta izango dugu
	JPanel panel1 = new JPanel();
	JLabel descript1 = new JLabel("Nor zara zu?");
	JTextField user = new JTextField(20);
	JButton buser = new JButton("Aurrera");
	
	//saioa gordeta EZ duten erabiltzaileentzat...
	JPanel panel2 = new JPanel();
	JLabel descript = new JLabel("Oraindik ez zaude logeaturik?");
	JButton go = new JButton("Hasi");
	
	JPanel center = new JPanel();
	Font f = new Font("Century Gothic",Font.BOLD,10);
	public Start(){
		//user.setText("ISADtaldea");
		gridBagHasieratu();
		getContentPane().setBackground(Color.white);
		this.setIconImage(wlogo.getImage());
		getContentPane().add(panel, BorderLayout.NORTH);
		getContentPane().add(center, BorderLayout.CENTER);
		
		((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
	}
	public static void main(String[]args){
		try {
			UIManager.setLookAndFeel(new javax.swing.plaf.nimbus.NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		bistaratu();
	}
	public static void bistaratu(){
		Start st = new Start();
		st.setTitle("Twitter App");
		st.setVisible(true);
		st.setResizable(false);
		//st.setSize(400,900);
		st.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		st.pack();
		st.actionListener();
		
		
	}
	private void gridBagHasieratu() {
		
		//panel hasieratu
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.WHITE);
		panel.add(logoTwitter);
		welcome.setFont(new Font("Britannic Bold",Font.BOLD,30));
		panel.add(welcome);
		
		
		//panel1 hasieratu
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		//panel1.setBackground(Color.WHITE);
		panel1.setOpaque(false);
		viewp1();
		
		//panel2 hasieratu
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
		panel2.setBackground(Color.WHITE);
		viewp2();	
		
		//centerpanel hasieratu
		center.setBackground(Color.WHITE);
		center.setLayout(new BoxLayout(center, BoxLayout.X_AXIS));
		center.add(panel1);
		center.add(panel2);
	}
	private void actionListener(){
		buser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(user.getText().length()!=0){
					String userId = user.getText();
					System.out.println(userId);
				
					String izena = Eragiketak.getEragiketak().userIzena(userId);
					System.out.println("res: "+izena);
					
					if(izena.equals(userId)){
						TokenKud.getToken().getSession(userId);
						Erabiltzailea.getErab().setUserId(userId);
						OrrNagusia.bistaratu();
						dispose();
					}else{
						NotUser.getmNo();
					}
				}else{
					System.out.println("Zerbait idatzi beharko zenuke...");
					Empty.getEmpty();
				
				
				}
			}
		});
		go.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					Has.bistaratu();
					dispose();
				
			}
		});
		
	}
	
	public void viewp1(){
		panel1.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		descript1.setFont(new Font("Century Gothic",Font.BOLD,15));
		descript1.setForeground(Color.decode("#7ea6e0"));

		//bektorean erabiltzaileak gehitu
		//cbuser = new JComboBox(users);
		panel1.add(descript1);
		panel1.add(user);
		buser.setFont(f);
		panel1.add(buser);
	}
	public void viewp2(){
		panel2.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		descript.setFont(new Font("Britannic Bold",Font.BOLD,15));
		descript.setForeground(Color.decode("#7ea6e0"));
		panel2.add(descript);
		go.setFont(f);
		panel2.add(go);
		
	}
}
