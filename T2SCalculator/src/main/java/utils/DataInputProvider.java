package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import wapper.GenericWapper;

public class DataInputProvider extends GenericWapper {
	
	public static String[][] getSheet(String dataSheetName) throws FileNotFoundException,IOException{
		
		FileInputStream fis = new FileInputStream(new File("./data/"+dataSheetName+".xlsx"));
		XSSFWorkbook wbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = wbook.getSheetAt(0);
		int rowCount = sheet.getLastRowNum();
		int colCount = sheet.getRow(0).getLastCellNum();
		String[][] obj = new String[rowCount][colCount];
		for (int i = 1; i < rowCount+1; i++) {
			
			XSSFRow row = sheet.getRow(i);
			
			for (int j = 0; j < colCount; j++) {
				
				XSSFCell cell = row.getCell(j);
				String cellValue = cell.getStringCellValue();
				obj[i-1][j]=cellValue;
			}
			
		}
		return obj;
	}	
}
