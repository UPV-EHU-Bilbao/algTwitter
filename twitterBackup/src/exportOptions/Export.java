package exportOptions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import jdk.jfr.events.FileWriteEvent;

public class Export {
	private static Export mExport = null;
	private Export(){}
	public static synchronized Export getExport(){
		if(mExport == null){
			mExport = new Export();
		}
		return mExport;
	}
	public void saveFile(){
		try{
			String izena = "";
			JFileChooser file = new JFileChooser();
	//		int aukera = file.showSaveDialog(areaTexto);
			File gorde = file.getSelectedFile();
			
			if (gorde != null){
				FileWriter save = new FileWriter(gorde);
				//save.write(arg0);
				save.close();
				JOptionPane.showMessageDialog(null,
				         "Fitxategia gorde da",
				             "Garrantzitsua!",JOptionPane.INFORMATION_MESSAGE);
				    }
			}catch(IOException ex){
				JOptionPane.showMessageDialog(null,
				        "Zure fitxategia ez da gorde!",
				           "Error!",JOptionPane.WARNING_MESSAGE);
		}
	}
	
}
