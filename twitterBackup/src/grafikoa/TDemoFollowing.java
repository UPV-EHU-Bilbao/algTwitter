package grafikoa;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import logikoa.TableF;
import twittercomponents.Following;


public class TDemoFollowing extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3043988523885102335L;


	//private JScrollPane scrollPane;
	public TDemoFollowing(String user){
		this.setLayout(new BorderLayout());
		//super(new GridLayout(1, 0));
		ArrayList<String> statuses = Following.getMfollowing().viewFollowing(user);

		TableF model = new TableF(statuses);
		
		JTable taula = new JTable(model);
		taula.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		taula.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 14));

		JScrollPane scrollPane = new JScrollPane(taula);
		
		add(scrollPane, BorderLayout.CENTER);
	}

	
	public void createAndShowGUI(){

		//TDemoFollowing newContentPane = new TDemoFollowing();
		setVisible(true);
		
	}
}
