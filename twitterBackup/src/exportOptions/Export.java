package exportOptions;

import java.io.File;
import java.io.FileOutputStream;
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
	public void fileSave(String f, String name){
	      final JFileChooser chooser=new JFileChooser();
	    //  chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	      chooser.setDialogTitle(f);
	      chooser.setCurrentDirectory(new File(System.getProperties().getProperty("user.home")));
	      chooser.setFileFilter(new javax.swing.filechooser.FileFilter()
	      {
	         public boolean accept(final File f)
	        {
	          return f.isDirectory();
	        }
	        public String getDescription(){
	          return "Folder To Save In";
	        }
	      }
	    );
	      final int r=chooser.showSaveDialog(null);
	      File file;

	      if (r == JFileChooser.APPROVE_OPTION) 
	      {
	        if (name != null) 
	        {
	            file=new File(chooser.getSelectedFile().getPath() + File.separator + name);
	        }
	        else 
	        {
	            // file=new File(filename);
	          file=new File(chooser.getSelectedFile().getPath());
	        }
	      }
	}
	/*
	 * http://stackoverflow.com/questions/26925275/how-to-save-file-in-java-using-jfilechooser
	 */
}
