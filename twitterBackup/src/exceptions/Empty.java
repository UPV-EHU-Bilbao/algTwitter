package exceptions;

import javax.swing.JOptionPane;

public class Empty {

	private static Empty mEmpty = null;
	
	private Empty(){
		String mezua = "Zerbait idatzi beharko zenuke...";
		JOptionPane.showMessageDialog(null,
			    mezua,
			    "Oops!",
			    JOptionPane.NO_OPTION);
	}
	public static synchronized Empty getEmpty(){
		if (mEmpty == null){
			mEmpty = new Empty();
		}return mEmpty;
	}
}
