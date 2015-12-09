package proba;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;



public class MyTableModel extends AbstractTableModel{

	private Vector<String> columnNames = new Vector<String>();
	private Vector<Lag> data = new Vector<Lag>();
	public MyTableModel(ArrayList<String[]> status){
		hasieratuZutabeIzenak();
		kargatu(status);
	}

	class Lag{
		String izena;
		String abizena;
		public Lag(String izena, String abizena) {
			super();
			this.izena = izena;
			this.abizena = abizena;
		}
		public Object getBalioa(int i) {
			switch (i) {
			case 0:
				return izena;
			case 1:
				return abizena;
			default:
				System.out.println("ERROREA");
				return null;	
			}
			
			
		}
		
		
		public void insertElementAt(Object value, int i){
			switch (i) {
			case 0:
				izena = (String) value;
				break;
			case 1:
				abizena = (String) value;
				break;
			default:
				System.out.println("ERROREA");	
			}
		}
	}
	public void kargatu(ArrayList<String[]> status){
		for(int n=0; n<status.size();n++){
			Lag e = new Lag(status.get(n)[0],status.get(n)[1]);
			data.add(e);
		}
		
	}
	public void hasieratuZutabeIzenak(){
		
		columnNames.add("User");
		columnNames.add("Tweet");
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
	public void setValueAt(Object value, int row, int col){
		data.get(row).insertElementAt(value, col);
	}
	public String getColumnName(int col){
		return columnNames.get(col);
	}
	public boolean isCellEditable(int row, int col){
		return false;
	}
}
