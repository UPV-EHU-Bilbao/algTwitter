package grafikoa;

import java.awt.Font;
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
	public TDemoTweets(String user) {
		
		super(new GridLayout(1, 0));
		ArrayList<String[]> statuses = HomeTimeLine.getMhome().viewTxio(user);

		TableG model = new TableG(statuses);
		
		JTable taula = new JTable(model);
		taula.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		taula.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 14));

		JScrollPane scrollPane = new JScrollPane(taula);

		add(scrollPane);
	}

	public void createAndShowGUI(){

	//	TDemoTweets newContentPane = new TDemoTweets();
		setVisible(true);
	}

}
