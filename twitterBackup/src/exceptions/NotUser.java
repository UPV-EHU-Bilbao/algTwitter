package exceptions;

import javax.swing.JOptionPane;

public class NotUser{
	private static NotUser myNot = null;
	private NotUser(){
		String mezua = "Ez dago erabiltzailerik izen horrekin";
		JOptionPane.showMessageDialog(null,mezua ,"ERROR",JOptionPane.ERROR_MESSAGE);
	}
	public static synchronized NotUser getmNo(){
		if(myNot == null){
			myNot = new NotUser();
		}return myNot;
	}
}
