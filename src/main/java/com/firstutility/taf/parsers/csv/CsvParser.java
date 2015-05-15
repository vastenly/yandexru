package com.firstutility.taf.parsers.csv;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class CsvParser {
    public static final String CSV_DELIMETER = ",";
    private static final Logger log = Logger.getLogger(CsvParser.class);
    private static final int FIRST_ROW = 0;
    private static final int FIRST_ROW_WITH_DATA = 1;
    private static final int FIRST_FIELD = 0;

    public static List<Map<String,String>> parseCsv(String csvPath) {
        log.info("[CsvParser] Parsing input data from  = [" + csvPath + "].");
		List<String> lines = FileOperator.readFileToLines(csvPath);
		return parseCsvLines(lines);

	}

	public static List<Map<String,String>> parseCsv(InputStream inputStream) {
		List<String> lines = FileOperator.readFileToLines(inputStream);
		 log.info("[CsvParser] Parsing input data.");
		return parseCsvLines(lines);
	}

    private static List<Map<String,String>> parseCsvLines(List<String> lines) {
        log.info("[CsvParser] Complete entities");
        String[] title = lines.get(FIRST_ROW).split(CSV_DELIMETER);
        List<Map<String,String>> parsedLines = new ArrayList<Map<String, String>>();
        try{
            for (int currentRowWithData = FIRST_ROW_WITH_DATA; currentRowWithData < lines.size(); currentRowWithData++) {
                Map<String,String> currentLineWithTitle = new HashMap<String, String>();
                    for(int currentFieldNumber = FIRST_FIELD; currentFieldNumber < title.length; currentFieldNumber ++){
                        currentLineWithTitle.put(title[currentFieldNumber], lines.get(currentRowWithData).split(CSV_DELIMETER)[currentFieldNumber]);
                    }
                parsedLines.add(currentLineWithTitle);
            }
        }catch (NullPointerException ex){
            ex.printStackTrace();
        }catch (IndexOutOfBoundsException ex){
            ex.printStackTrace();
        }

        return parsedLines;
    }
}
