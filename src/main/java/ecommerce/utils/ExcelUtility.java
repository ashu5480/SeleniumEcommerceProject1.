package ecommerce.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	private static Workbook workbook;
	private static Sheet sheet;
	
	public static void testDataInExcel(String filePath, String sheetName) throws IOException {
		FileInputStream fis = new FileInputStream(filePath);
		workbook = new XSSFWorkbook(fis);
		sheet=workbook.getSheet(sheetName);
		fis.close();
	}
	
	public static Object[][] getTestData(){
		int rowCount = sheet.getPhysicalNumberOfRows();
		int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
		DataFormatter formatter = new DataFormatter();
		Object[][] data = new Object[rowCount-1][colCount];
		for(int i=1; i<rowCount; i++) {
			Row row = sheet.getRow(i);
			for(int j=0; j<colCount; j++) {
				Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				data[i - 1][j] = formatter.formatCellValue(cell); 
			}
		}
		return data;
	}
	
	public static void setStatus(String status, int colNum, String filePath) throws IOException {
		FileInputStream fis = new FileInputStream(filePath);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet("Sheet1");
		int rowCount = sheet.getPhysicalNumberOfRows();
		for(int i=1; i<rowCount; i++) {
			Row row = sheet.getRow(i);
			Cell cell = row.getCell(colNum);
			if(cell==null) {
				cell=row.createCell(colNum);
			}
			cell.setCellValue(status);
			FileOutputStream fos = new FileOutputStream(filePath);
			workbook.write(fos);
			fos.close();
		}
	}
}
