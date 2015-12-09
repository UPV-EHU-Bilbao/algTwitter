package logikoa;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import logikoa.TableG.Lag;

@SuppressWarnings("serial")
public class TableF extends AbstractTableModel{
	private Vector<String> columnNames = new Vector<String>();
	private Vector<Lag> data = new Vector<Lag>();
	public TableF(ArrayList<String> f){
//) {
		hasieratuZutabeIzenak();
		kargatu(f);
	}

	class Lag{
		String user;
		public Lag(String user) {
			super();
			this.user = user;
		}
		public Object getBalioa(int i) {
			switch (i) {
			case 0:
				return user;
			
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
			default:
				System.out.println("ERROREA");	
			}
		}
	}
	public void kargatu(ArrayList<String> statuses){
		for (String status : statuses) {
			data.add(new Lag(status));
		}
		
	}
	public void hasieratuZutabeIzenak(){	
		columnNames.add("Twitter User");
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
	public Class getColumnClass(int col){
		return data.get(0).getBalioa(col).getClass();
	}
	public String getColumnName(int col){
		return columnNames.get(col);
	}

	public void setValueAt(Object value, int row, int col){
		data.get(row).insertElementAt(value, col);
	}
	public boolean isCellEditable(int row, int col){
		return false;
	}
}
