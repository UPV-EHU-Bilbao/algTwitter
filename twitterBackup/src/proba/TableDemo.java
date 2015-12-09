package proba;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import twittercomponents.Favorites;

public class TableDemo extends JPanel {
	public TableDemo(){
		super(new GridLayout(1,0));
		ArrayList<String[]> statuses = Favorites.getMfav().viewFavorites();
		
		JTable table = new JTable(new MyTableModel(statuses));
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		add(scrollPane);
	}
	private static void createAndShowGUI(){
		JFrame frame = new JFrame("Table Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		TableDemo newContentPane = new TableDemo();
		frame.setContentPane(newContentPane);
		
		frame.pack();
		frame.setVisible(true);
	}
	public static void main(String[]args){
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				createAndShowGUI();
			}
		});
	}

}
