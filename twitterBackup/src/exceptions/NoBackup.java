package exceptions;

import javax.swing.JOptionPane;

public class NoBackup {

	private static NoBackup mBackup = null;
	private NoBackup(){
		String mezua = "WAIT! Lehenago Backup bat egin beharko zenuke..." ;
		JOptionPane.showMessageDialog(null,
			    mezua,
			    "NO BACKUP",
			    JOptionPane.NO_OPTION);
	}
	public static synchronized NoBackup getback(){
		if(mBackup == null){
			mBackup = new NoBackup();
		}return mBackup;
	}
}
