package grafikoa;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import logikoa.TableM;
import twittercomponents.DirectMessages;

public class TDemoMD extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8193743007028641755L;

	public TDemoMD(String user){
		super(new GridLayout(1, 0));
		ArrayList<String[]> statuses = DirectMessages.getmDirect().viewFavorites(user);

		TableM model = new TableM(statuses);
		
		JTable taula = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(taula);

		add(scrollPane);
	}
	public void createAndShowGUI(){

		//	TDemoFav newContentPane = new TDemoFav();
			setVisible(true);
		}
	
}
