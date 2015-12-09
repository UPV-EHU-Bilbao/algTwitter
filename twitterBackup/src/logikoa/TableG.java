package logikoa;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import twitter4j.Status;



@SuppressWarnings("serial")
public class TableG extends AbstractTableModel{
	private Vector<String> columnNames = new Vector<String>();
	private Vector<Lag> data = new Vector<Lag>();
	public TableG(List<Status> statuses) {
		hasieratuZutabeIzenak();
		//data = (Vector<Status>) statuses;
		kargatu(statuses);
	}

	class Lag{
		String user;
		String tweet;
		public Lag(String user, String tweet) {
			super();
			this.user = user;
			this.tweet = tweet;
		}
		public Object getBalioa(int i) {
			switch (i) {
			case 0:
				return user;
			case 1:
				return tweet;
			
			default:
				System.out.println("ERROREA");
				return null;	
			}
			
			
		}
		public void insertElementAt(Object value, int i){
			switch (i) {
			case 0:
				user = (String) value;
				break;
			case 1:
				tweet = (String) value;
				break;
			default:
				System.out.println("ERROREA");	
			}
		}
	}
	public void kargatu(List<Status> statuses){
		for (Status status : statuses) {
			Lag l = new Lag(status.getUser().getScreenName(),status.getText());
			data.add(l);
		}
		
	}
	public void hasieratuZutabeIzenak(){	
		columnNames.add("Twitter User");
		columnNames.add("Txioa");
	}
	@Override
	public int getColumnCount() {
		return columnNames.size();
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		return data.get(row).getBalioa(col);
	}
	
	public String getColumnName(int col){
		return columnNames.get(col);
	}

	public void setValueAt(Object value, int row, int col){
		data.get(row).insertElementAt(value, col);
	}
}
