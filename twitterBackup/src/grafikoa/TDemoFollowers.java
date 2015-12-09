package grafikoa;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import logikoa.TableF;
import twittercomponents.Followers;
import twittercomponents.Following;

public class TDemoFollowers extends JPanel {

	public TDemoFollowers(){
		super(new GridLayout(1, 0));
		ArrayList<String> statuses = Followers.getMfollowers().viewFollowers();

		TableF model = new TableF(statuses);
		
		JTable taula = new JTable(model);

		JScrollPane scrollPane = new JScrollPane(taula);

		add(scrollPane);
	}

	
	public void createAndShowGUI(){

		TDemoFollowers newContentPane = new TDemoFollowers();
		setVisible(true);
		
	}
}
