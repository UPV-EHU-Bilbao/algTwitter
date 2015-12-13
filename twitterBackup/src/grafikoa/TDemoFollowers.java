package grafikoa;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import logikoa.TableF;
import twittercomponents.Followers;

@SuppressWarnings("serial")
public class TDemoFollowers extends JPanel {

	public TDemoFollowers(String user){
		super(new GridLayout(1, 0));
		ArrayList<String> statuses = Followers.getMfollowers().viewFollowers(user);

		TableF model = new TableF(statuses);
		
		JTable taula = new JTable(model);
		taula.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		taula.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 14));

		JScrollPane scrollPane = new JScrollPane(taula);

		add(scrollPane);
	}

	
	public void createAndShowGUI(){
		setVisible(true);
		
	}
}
