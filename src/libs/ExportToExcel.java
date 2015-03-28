package libs;

import init.Main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import objects.ExtensionFilter;


public class ExportToExcel {

	public static void export(Main frame, String[] head, String [][] body)
    {
		JFileChooser dialog = new JFileChooser();
		FileFilter type1 = new ExtensionFilter("Text", ".txt");
		FileFilter type2 = new ExtensionFilter("Excel", new String[] { ".xlsx", ".xls", ".xlsm"});
        FileFilter type3 = new ExtensionFilter("SCV", ".csv");
        dialog.addChoosableFileFilter(type1);
        dialog.addChoosableFileFilter(type2);
        dialog.addChoosableFileFilter(type3);
		dialog.setFileFilter(type3);
        dialog.setApproveButtonText("Выбрать"); //выбрать название для кнопки согласия
        dialog.setDialogTitle("Выберите куда сохранить файл"); // выбрать название
        dialog.setDialogType(JFileChooser.OPEN_DIALOG); // выбрать тип диалога Open или Save		        
        dialog.showOpenDialog(frame);
        //setVisible(true);
        
        File file = dialog.getSelectedFile();
        System.out.println("file path: " + file.getPath());
        String file_save = "";
        if(file.getPath().indexOf(".") != -1) {
        	file_save = file.getPath().substring(0, file.getPath().indexOf("."));
        } else {
        	file_save = file.getPath();
        }
        
        
		generateCsvFile(file_save + ".csv", head, body);
	}
	
	
	@SuppressWarnings("unused")
	private static void generateCsvFile(String sFileName, String[][] pBody) {
		try {
			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(sFileName), "UTF-8");
			//FileWriter writer = new FileWriter(sFileName);
			for(String[] row : pBody) {
				for(String cell : row) {
					byte[] b = cell.getBytes("UTF-8");
					cell = new String(b, "UTF-8");
					cell = cell.replace(',', '.');
					cell = cell.replace(';', ',');
					writer.append(cell);
					writer.append(';');
				}
				writer.append('\n');
			}	 
		    writer.flush();
		    writer.close();
		} catch(IOException e) {
		     e.printStackTrace();
		} 
    }
	
	private static void generateCsvFile(String sFileName, String[] pHeader, String[][] pBody) {
		try {
			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(sFileName), "UTF-8");
			//FileWriter writer = new FileWriter(sFileName);
			for(String cell : pHeader) {
				byte[] b = cell.getBytes("UTF-8");
				cell = new String(b, "UTF-8");
				cell = cell.replace(',', '.');
				cell = cell.replace(';', ',');
				writer.append(cell);
				writer.append(';');
			}
			writer.append('\n');
			
			
			for(String[] row : pBody) {
				for(String cell : row) {
					byte[] b = cell.getBytes("UTF-8");
					cell = new String(b, "UTF-8");
					cell = cell.replace(',', '.');
					cell = cell.replace(';', ',');
					writer.append(cell);
					writer.append(';');
				}
				writer.append('\n');
			}	 
		    writer.flush();
		    writer.close();
		} catch(IOException e) {
		     e.printStackTrace();
		} 
    }

}
