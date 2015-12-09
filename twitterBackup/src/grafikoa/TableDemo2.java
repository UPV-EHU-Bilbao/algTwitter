package grafikoa;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import logikoa.TableF;
import logikoa.TableG;

public class TableDemo2 extends JPanel {
	private TableF model;
	private JTable taula;
	private JScrollPane scrollPane;
	public TableDemo2(){
		super(new GridLayout(1, 0));
		model = new TableF();
		
		taula = new JTable(model);

		scrollPane = new JScrollPane(taula);

		add(scrollPane);
	}

	public void eguneratu(ArrayList<String> statuses){
		model.kargatu(statuses);
		taula.revalidate();
		revalidate();
		
	}
	public void createAndShowGUI(){
		JFrame frame = new JFrame("Table Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		TableDemo2 newContentPane = new TableDemo2();
		setVisible(true);
		frame.setContentPane(newContentPane);

		frame.pack();
		frame.setVisible(true);
	}

}

