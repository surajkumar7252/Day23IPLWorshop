package iplanalyser;

public class CsvBuilderFactory {
	public static ICsvCreator createBuilderEntry() {
		return new OpenCsvBuilder();
	}

}
