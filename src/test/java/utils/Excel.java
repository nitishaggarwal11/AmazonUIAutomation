package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedHashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {
	
	private static LinkedHashMap<String, String> testData;
	
	/***
	 * function to get Test Data of the specified row
	 * @param rowValue
	 * @return excelData
	 * @throws Exception
	 */
	public void initTestCaseData(String rowValue) throws Exception{
		int rowNumber;
		String cellValue 	= 	null;
		
		testData = new LinkedHashMap<String, String>();
		
        InputStream input 	= 	new FileInputStream(System.getProperty("user.dir") + File.separator + "TestData" + File.separator + "TestData.xlsx");

		XSSFWorkbook workbook 	= 	new XSSFWorkbook(input);				 	//Opening workbook
        XSSFSheet sheet 		= 	workbook.getSheetAt(0);				 		//Opening worksheet
        
        rowNumber 				= 	getRowNumber(sheet, rowValue);				//getting Row Number
        int noOfColumns 		= 	sheet.getRow(0).getPhysicalNumberOfCells();			//getting Column Count

        if (rowNumber == 0) {
			throw new Exception("Test Data Id: " + rowValue + " is not present in the Test Data sheet");
		}

        /*Getting Test Data along with Column Name in a LinkedHashMap*/
		for (int i = 0; i < noOfColumns; i++) {
			String columnName  	= output(sheet, 0, i);				//getting Header
			cellValue  			= output(sheet, rowNumber, i);		//getting Cell value
			testData.put(columnName, cellValue);
		}
	}
	
	/***
	 * function to return Cell value according to specified Row and Column 
	 * @param sheet	- XSSFSheet
	 * @param rowNumber
	 * @param colNumber
	 * @return variableValue
	 */
	private String output(XSSFSheet sheet, int rowNumber, int colNumber) {
		DataFormatter df 	 	= 	new DataFormatter();
		XSSFRow row 		 	= 	sheet.getRow(rowNumber);
        XSSFCell cell 		 	= 	row.getCell(colNumber);
        String variableValue 	= 	df.formatCellValue(cell);
        
        return variableValue;
    }
	
	/***
	 * function to find Row number according to the Search value specified
	 * @param sheet
	 * @param searchValue
	 * @return row.getRowNum()
	 */
	private int getRowNumber(XSSFSheet sheet, String searchValue) {
		for (Row row : sheet) {
	        for (Cell cell : row) {
	            if (cell.getCellType() == CellType.STRING) {
	                if (cell.getRichStringCellValue().getString().trim().equalsIgnoreCase(searchValue)) {
	                	return row.getRowNum();
	                }
				}
	        }
	    }
		return 0;
	}
	
	/***
	 * @description Function to get the Test data instance 
	 * @return
	 */
	public static LinkedHashMap<String, String> getData() {
		return testData;
	}
	
}
