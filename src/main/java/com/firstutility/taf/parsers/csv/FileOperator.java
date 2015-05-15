package com.firstutility.taf.parsers.csv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileOperator {

	public static BufferedReader reader = null;

	public static List<String> readFileToLines(String filePath) {
		try {
			reader = new BufferedReader(new FileReader(filePath));
			return readToLines();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<String> readFileToLines(InputStream input) {
		InputStreamReader is = new InputStreamReader(input);
		reader = new BufferedReader(is);
		return readToLines();
	}

	private static List<String> readToLines() {
		List<String> lines = new ArrayList<String>();
		try {
			String line = "";
			while ((line = reader.readLine()) != null)
				lines.add(line);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}
}
