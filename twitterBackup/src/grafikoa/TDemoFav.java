package grafikoa;



import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import logikoa.TableG;
import twittercomponents.Favorites;

@SuppressWarnings("serial")
public class TDemoFav extends JPanel{
		//private JScrollPane scrollPane;
		public TDemoFav(String user) {
			
			super(new GridLayout(1, 0));
			ArrayList<String[]> statuses = Favorites.getMfav().viewFavorites(user);

			TableG model = new TableG(statuses);
			
			JTable taula = new JTable(model);
			taula.setFont(new Font("Century Gothic", Font.PLAIN, 12));
			taula.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 14));
			JScrollPane scrollPane = new JScrollPane(taula);

			add(scrollPane);
		}
		public void createAndShowGUI(){

		//	TDemoFav newContentPane = new TDemoFav();
			setVisible(true);
		}

}
