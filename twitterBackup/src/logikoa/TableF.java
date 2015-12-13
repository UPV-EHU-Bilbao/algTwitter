package logikoa;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class TableF extends AbstractTableModel{
	private Vector<String> columnNames = new Vector<String>();
	private Vector<Lag> data = new Vector<Lag>();
	public TableF(ArrayList<String> f){
		hasieratuZutabeIzenak();
		kargatu(f);
	}

	class Lag{
		String user;
		public Lag(String user) {
			super();
			this.user = user;
		}
		
		/**
		 * Gure array-ean i gelaxkan dagoen Objektua bueltatzen du.
		 * @param i
		 * @return Object - Objektu bat jasotzen du.
		 */
		public Object getBalioa(int i) {
			switch (i) {
			case 0:
				return user;
			default:
				System.out.println("ERROREA");
				return null;	
			}
		}
		
		/**
		 * Gelaxka batean (Gure array-an i indizean) balio bat sartzen du.
		 * @param value, i - Balioa eta indizea jasotzen ditu.
		 */
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
	
	/**
	 * Gure user-ak kargatzen ditugu.
	 * @param statuses
	 */
	public void kargatu(ArrayList<String> statuses){
		for (String status : statuses) {
			data.add(new Lag(status));
		}		
	}
	
	/**
	 * Gure zutabeen izenen taulan "Twitter User" izena sartzen dugu.
	 */
	public void hasieratuZutabeIzenak(){	
		columnNames.add("Twitter User");
	}
	@Override
	/**
	 * Gure taularen zutabe kopurua bueltatzen du.
	 * @return int - Gure taularen zutabe kopurua bueltatzen du.
	 */
	public int getColumnCount() {
		return columnNames.size();
	}

	@Override
	/**
	 * Gure taularen lerro kopurua bueltatzen du.
	 * @return int - Gure taularen lerro kopurua bueltatzen du.
	 */
	public int getRowCount() {
		return data.size();
	}

	@Override
	/**
	 * Gelaxka batean (row eta col-ek esaten dutena) dagoen Objektua bueltatzen du.
	 * @param row, col - Lerro eta zutabe bat jasotzen du.
	 * @return Object - Objektu bat bueltatzen du.
	 */
	public Object getValueAt(int row, int col) {
		return data.get(row).getBalioa(col);
	}

	/**
	 * Guk eskatzen diogun zutabearen klasea bueltatzen du.
	 * @param col - Zutabe baten id-a.
	 * @return Class - Zutabearen klasea bueltatzen du.
	 */
	public Class getColumnClass(int col){
		return data.get(0).getBalioa(col).getClass();
	}
	
	/**
	 * Gure taularen zutabe baten izena eskatzen dugu, zutabearen zenbakia emanez.
	 * @param col - Zutabe bat jasotzen du.
	 * @return String - zutabearen izena bueltatzen du.
	 */
	public String getColumnName(int col){
		return columnNames.get(col);
	}

	/**
	 * Gelaxka batean (row eta col-ek esaten dutena) balio bat sartzen du.
	 * @param value, row, col - Balioa, lerroa eta zutabea jasotzen ditu
	 */
	public void setValueAt(Object value, int row, int col){
		data.get(row).insertElementAt(value, col);
	}
	
	/**
	 * Gure gelaxka editagarria den ala ez esaten du
	 * @param row, col - Lerroa eta zutabea jasotzen ditu
	 * @return boolean - Boolean bat bueltatzen du.
	 */
	public boolean isCellEditable(int row, int col){
		return false;
	}
}
