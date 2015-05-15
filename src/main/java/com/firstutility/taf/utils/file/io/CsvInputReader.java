package com.firstutility.taf.utils.file.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

public class CsvInputReader {
	
	public static List<String[]> readSupplyInformation(String registrationFile) {
		List<String[]> data = new ArrayList<String[]>();
		CSVReader reader = null;
        try {
        	reader = new CSVReader(new FileReader(new File(registrationFile)));
			data = reader.readAll();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return data;
	}
}
