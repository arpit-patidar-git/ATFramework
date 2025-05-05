package com.qa.opencart.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class CSVUtil {
	
	private static final String CSV_DATA_FILE = "./src/test/resources/testData/products.csv";
	private static List<String[]> rows;
	
	
	public static Object[][] getCSVData(){
		String file = CSV_DATA_FILE;
		CSVReader reader;
		
		try {
			reader = new CSVReader(new FileReader(file));
			rows = reader.readAll();
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CsvException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Object[][] data = new Object[rows.size()][];
		for(int i=0; i<rows.size();i++) {
			data[i]=rows.get(i);
		}
		
		return data;
 	}

}
