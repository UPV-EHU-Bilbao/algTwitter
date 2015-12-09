package logikoa;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;




@SuppressWarnings("serial")
public class TableG extends AbstractTableModel{
	private Vector<String> columnNames = new Vector<String>();
	private ArrayList<Lag> data = new ArrayList<Lag>();
	public TableG(){
//) {
		hasieratuZutabeIzenak();
		//data = (Vector<Status>) statuses;
		//kargatu(statuses);
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
	public void kargatu(ArrayList<String[]> statuses){
//		for (int st=0; st<statuses.size(); st++){
//			Lag l = new Lag(statuses.get(st)[0], statuses.get(st)[1]);
//			System.out.println("user: "+statuses.get(st)[0]+"txio: "+statuses.get(st)[1]);
//			data.add(l);
//		}
		for (String[] status : statuses) {
			data.add(new Lag(status[0],status[1]));
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
