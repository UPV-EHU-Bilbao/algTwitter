package grafikoa;

import java.awt.GridLayout;
import java.awt.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import logikoa.TableG;
import twitter4j.TwitterException;
import twittercomponents.Favorites;

@SuppressWarnings("serial")
public class TaulaGKud extends JPanel {

	//private static TaulaGKud mtaulaG = null;
	//List<Status> statuses = Favorites.getMfav().bistaratzeko();	
		private TaulaGKud() throws IllegalStateException, TwitterException{
//		public TableDemo(){
			super(new GridLayout(1,0));
	
			JTable table = new JTable(new TableG(Favorites.getMfav().bistaratzeko()));
	
			JScrollPane scrollPane = new JScrollPane(table);
	
	add(scrollPane);
		}
//		
//	}
//	public static synchronized TaulaGKud getmTaulaG(){
//		if( mtaulaG == null){
//			mtaulaG = new TaulaGKud();
//		}return mtaulaG;
//	}
	public void bistaratu() throws IllegalStateException, TwitterException{
		JTable taula = new JTable(new TableG(Favorites.getMfav().bistaratzeko()));
		JScrollPane scrollPane = new JScrollPane(taula);
		add(scrollPane);
		setVisible(true);
		
	}
	
	private static void createAndShowGUI() throws IllegalStateException, TwitterException{
		JFrame frame = new JFrame("Table Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		TaulaGKud newContentPane = new TaulaGKud();
		frame.setContentPane(newContentPane);
		
		frame.pack();
		frame.setVisible(true);
	}
	public static void main(String[]args){
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				try {
					createAndShowGUI();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TwitterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
}
