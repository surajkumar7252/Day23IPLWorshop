package iplanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opencsv.exceptions.CsvException;


public class IPLMain 
{
	private static final Logger log = LogManager.getLogger(IPLMain.class); 
	
	public  final static String BATTING_CSV_FILE_PATH ="src/main/resources/FactsheetMostRuns.csv"; 
	public  final static String BOWLING_CSV_FILE_PATH = "src/main/resources/FactsheetMostWkts.csv"; 
	public static Scanner sc= new Scanner(System.in);
    public static void main( String[] args ) throws CsvException
    {
        log.info( "IPL 2019 STATISTICS") ;
        IPLMain newIplMain= new IPLMain();
        int choice=0;
        do {
        	log.info("\nWhat You want to perform: -");
        	log.info("\n1.Batsman With Best Batting Average");
            log.info("\n2.Batsman With Best Strike Rate");

        	choice=sc.nextInt();
        	switch(choice) {
        	case 1: newIplMain.topFiveAverages(BATTING_CSV_FILE_PATH);
        	        break;
        	case 2:  newIplMain.topFiveStrikeRates(BATTING_CSV_FILE_PATH);
        	        break;
        	
        	}
        	
        }while(choice>0 && choice<=2);
        
  }
    
    public List<BatsmanCsvData> batsmenCsvDataLoader(String BatsmanCsvFilePath) throws CsvException {
		try (Reader reader = Files.newBufferedReader(Paths.get(BatsmanCsvFilePath));) {
			ICsvCreator csvCreator = CsvBuilderFactory.createBuilderEntry();
			List<BatsmanCsvData> listOfBatsman = csvCreator.getCSVList(reader,BatsmanCsvData.class);
			return listOfBatsman;
		} catch (IOException e) {
			throw new CsvException("File Error");
		}
	}
    
    public List<BatsmanCsvData> topFiveAverages(String BatsmanCsvFilePath) throws CsvException {
		List<BatsmanCsvData> listOfBatsman=batsmenCsvDataLoader(BatsmanCsvFilePath);
		List<BatsmanCsvData> topperAverage=listOfBatsman.stream().sorted((firstBatsman,secondBatsman)->secondBatsman.avg.compareTo(firstBatsman.avg))
				.limit(5).collect(Collectors.toList());
		return topperAverage;
	}
    public List<BatsmanCsvData> topFiveStrikeRates(String BatsmanCsvFilePath) throws CsvException {
		List<BatsmanCsvData> listOfBatsman=batsmenCsvDataLoader(BatsmanCsvFilePath);
		List<BatsmanCsvData> topStrikeRates=listOfBatsman.stream().sorted((firstBatsman,secondBatsman)->secondBatsman.strikeRate.compareTo(firstBatsman.strikeRate))
				.limit(5).collect(Collectors.toList());
		return topStrikeRates;
	}
}
