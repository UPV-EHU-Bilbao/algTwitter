package grafikoa;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import logikoa.TableF;
import twittercomponents.Following;

@SuppressWarnings("serial")
public class TDemoFollowing extends JPanel {
	//private JScrollPane scrollPane;
	public TDemoFollowing(String user){
		super(new GridLayout(1, 0));
		ArrayList<String> statuses = Following.getMfollowing().viewFollowing(user);

		TableF model = new TableF(statuses);
		
		JTable taula = new JTable(model);

		JScrollPane scrollPane = new JScrollPane(taula);

		add(scrollPane);
	}

	
	public void createAndShowGUI(){

		//TDemoFollowing newContentPane = new TDemoFollowing();
		setVisible(true);
		
	}
}
