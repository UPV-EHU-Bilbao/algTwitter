package exceptions;

import javax.swing.JOptionPane;

public class TimeTo {
	private static TimeTo myTime = null;
	
	private TimeTo(String time) {
		String mezua = "Gehiago lortzeko pixka bat itxaron beharko duzu!: " + time +" seg.";
		JOptionPane.showMessageDialog(null,
			    mezua,
			    "ITXARON",
			    JOptionPane.NO_OPTION);
	}
	public static synchronized TimeTo getMessage(String time){
		if (myTime == null){
			myTime = new TimeTo(time);
		}
		return myTime;
	}
}
