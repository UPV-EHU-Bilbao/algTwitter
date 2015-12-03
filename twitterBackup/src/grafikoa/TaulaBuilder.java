package grafikoa;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import logikoa.TableG;
import twitter4j.Status;

public class TaulaBuilder extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public TaulaBuilder(List<Status> statuses){
super(new GridLayout(1,0));
		
		JTable table = new JTable(new TableG(statuses));
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		add(scrollPane);
	}
	private static void createAndShowGUI(){
		JFrame frame = new JFrame("Table Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		TaulaBuilder newContentPane = null;
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
}}
