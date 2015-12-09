package grafikoa;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import logikoa.TableG;
import twittercomponents.HomeTimeLine;

@SuppressWarnings("serial")
public class TDemoTweets extends JPanel {
	//private JScrollPane scrollPane;
	public TDemoTweets() {
		
		super(new GridLayout(1, 0));
		ArrayList<String[]> statuses = HomeTimeLine.getMhome().viewTxio();

		TableG model = new TableG(statuses);
		
		JTable taula = new JTable(model);

		JScrollPane scrollPane = new JScrollPane(taula);

		add(scrollPane);
	}

	public void createAndShowGUI(){

		TDemoTweets newContentPane = new TDemoTweets();
		setVisible(true);
	}

}
