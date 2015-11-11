package grafikoa;

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
import java.io.IOException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import db.Eragiketak;
import javafx.scene.control.ComboBox;
import logikoa.TokenKud;


public class Start extends JFrame{
	ImageIcon wlogo = new ImageIcon("src/media1/logoTwitter.png");
	JLabel logoTwitter = new JLabel( new ImageIcon("src/media1/loginTwitter.png")) ;
	JLabel welcome = new JLabel("Ongi etorri TWITTER APP aplikaziora");
	//erabiltzaile hauen saioa jadanik gordeta izango dugu
	JPanel panel1 = new JPanel(new GridLayout(0, 1));
	JLabel descript1 = new JLabel("Nor zara zu?");
	JTextField user = new JTextField(40);
	JButton buser = new JButton("Aurrera");
	
	//saioa gordeta EZ duten erabiltzaileentzat...
	JPanel panel2 = new JPanel(new GridLayout(0, 2));
	JLabel descript = new JLabel("Oraindik ez zaude logeaturik?");
	JButton go = new JButton("Hasi");
	
	private GridBagLayout eskema;
	private Container edukiontzia;
	private GridBagConstraints mugak;
	
	public Start(){
		gridBagHasieratu();
	}
	public static void main(String[]args){
		bistaratu();
	}
	public static void bistaratu(){
		Start st = new Start();
		st.setTitle("Twitter App Login");
		st.setVisible(true);
		st.setResizable(false);
		st.setSize(400,900);
		st.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		st.pack();
		
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
		
		welcome.setFont(new Font("Arial",Font.BOLD,15));
		gehituOsagaia(welcome, 2, 1, 3, 1);
		mugak.insets = new Insets(3, 3, 3, 3);
		
		viewp1();
		gehituOsagaia(panel1, 4, 1, 3, 1);
		mugak.insets = new Insets(3, 3, 3, 3);

		viewp2();
		gehituOsagaia(panel2, 6, 1, 3, 1);
		mugak.insets = new Insets(3, 3, 3, 3);

		buser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(user.getText().length()!=0){
					String userId = user.getText();
					System.out.println(userId);
				
					
					 if(Eragiketak.getEragiketak().userIzena(userId)!= null){
						TokenKud.getToken().getSession(userId);
						OrrNagusia.bistaratu();
						dispose();
					
					}else{
						JOptionPane.showMessageDialog(null, "Ez dago erabiltzailerik izen horrekin","ERROR",JOptionPane.ERROR_MESSAGE);
						dispose();
					}
				}else{
					System.out.println("Zerbait idatzi beharko zenuke...");
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
	/*public static void main(String[] args) throws IOException {
		String izena="Leireva";
		if(Eragiketak.getEragiketak().tokenBilatu(izena) == null){
			Has.bistaratu();
		}else{
			//System.out.println(Eragiketak.getEragiketak().tokenBilatu());
			TokenKud.getToken().getSession(izena);
			OrrNagusia.bistaratu();
		}
	}*/
	public void viewp1(){
		panel1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		descript1.setFont(new Font("Britannic Bold",Font.BOLD,15));
		descript1.setForeground(Color.decode("#7ea6e0"));

		//bektorean erabiltzaileak gehitu
		//cbuser = new JComboBox(users);
		panel1.add(descript1);
		panel1.add(user);
		panel1.add(buser);
	}
	public void viewp2(){
		panel2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		descript.setFont(new Font("Britannic Bold",Font.BOLD,15));
		descript.setForeground(Color.decode("#7ea6e0"));
		panel2.add(descript);
		panel2.add(go);
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
