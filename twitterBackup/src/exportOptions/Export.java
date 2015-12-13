package exportOptions;

import java.io.File;

import javax.swing.JFileChooser;

public class Export {
	private static Export mExport = null;
	private JFileChooser  jfc = new JFileChooser();
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
	public String saveWin() {
		jfc.setAcceptAllFileFilterUsed(false);  
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//  
       /* jfc.setFileFilter(  new FileFilter(){  
                    public boolean accept(File f) {  
                        // TODO Auto-generated method stub  
                        return f.getName().toLowerCase().endsWith(".xls");  
                    }  
                    public String getDescription() {  
                        // TODO Auto-generated method stub  
                        return "Excel File";  
                    }  
                }  
        );  */
        jfc.showSaveDialog(null);  
        String resultSave = jfc.getSelectedFile().getPath();  
        System.out.println(resultSave);  
        return resultSave;  
	
	}
}
