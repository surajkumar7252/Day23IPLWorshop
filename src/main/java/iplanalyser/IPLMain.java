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
            log.info("\n3.Top Sixes and fours hitter.");
            log.info("\n4.Top Sixes and fours hitter with Best Batting Strike Rates.");
            log.info("\n5.Batsman With Best Strike Rate with Best Batting Strike Rates.");
            log.info("\n6.Batsman With Most Runs  with Best Batting Average.");
            log.info("\n7.Bowler with Best Bowling Average.");
            log.info("\n8.Bowler with Best Bowling Strike Rate.");
            log.info("\n8.Bowler with Best Bowling Economy.");
            
            

        	choice=sc.nextInt();
        	switch(choice) {
        	case 1: newIplMain.topFiveAverages(BATTING_CSV_FILE_PATH);
        	        break;
        	case 2:  newIplMain.topFiveStrikeRates(BATTING_CSV_FILE_PATH);
        	        break;
        	case 3: newIplMain.topSixesAndFoursHitter(BATTING_CSV_FILE_PATH);
	                break;
        	case 4: newIplMain.topStrikeRatesWithMaxSixesAndFours(BATTING_CSV_FILE_PATH);
                     break;
        	case 5: newIplMain.topStrikeRatesWithMaxAverage(BATTING_CSV_FILE_PATH);
                    break; 
        	case 6: newIplMain.topRunMakerWithBestAverages(BATTING_CSV_FILE_PATH);
                     break; 
            
        	case 7: newIplMain.topFiveBowlingAverages(BOWLING_CSV_FILE_PATH);
                    break; 
        	case 8: newIplMain.topBowlingStrikeRates(BOWLING_CSV_FILE_PATH);
                    break; 
        	case 9: newIplMain.topBowlingEconomy(BOWLING_CSV_FILE_PATH);
                    break;
   
        	}
        	
        }while(choice>0 && choice<=8);
        
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
    
    public List<BowlerCsvData> bowlerCsvDataLoader(String bowlingCsvFilePath) throws CsvException {
		try (Reader reader = Files.newBufferedReader(Paths.get(bowlingCsvFilePath));) {
			ICsvCreator csvCreator = CsvBuilderFactory.createBuilderEntry();
			List<BowlerCsvData> listOfBowler = csvCreator.getCSVList(reader,BowlerCsvData.class);
			return listOfBowler;
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
    public List<BatsmanCsvData> topSixesAndFoursHitter(String BatsmanCsvFilePath) throws CsvException {
		List<BatsmanCsvData> listOfBatsman=batsmenCsvDataLoader(BatsmanCsvFilePath);
		List<BatsmanCsvData> topStrikeRates=listOfBatsman.stream().sorted((firstBatsman,secondBatsman)->(secondBatsman.sixes).compareTo((firstBatsman.sixes)))
				.limit(5).collect(Collectors.toList());
		return topStrikeRates;
	}
    
    public List<BatsmanCsvData> topStrikeRatesWithMaxSixesAndFours(String BatsmanCsvFilePath) throws CsvException {
		List<BatsmanCsvData> topSixAndFourHitter=topSixesAndFoursHitter(BatsmanCsvFilePath);
		List<BatsmanCsvData> topStrikeRateSixAndFourHitter=topSixAndFourHitter.stream().sorted((firstBatsman,secondBatsman)->(secondBatsman.strikeRate).compareTo((firstBatsman.strikeRate)))
				.limit(5).collect(Collectors.toList());
		return topStrikeRateSixAndFourHitter;
	}
    
    public List<BatsmanCsvData> topStrikeRatesWithMaxAverage(String BatsmanCsvFilePath) throws CsvException {
		List<BatsmanCsvData> topFiveStrikeRatePlayers=topFiveStrikeRates(BatsmanCsvFilePath);
		List<BatsmanCsvData> topStrikeRateWithMaximumAverage=topFiveStrikeRatePlayers.stream().sorted((firstBatsman,secondBatsman)->(secondBatsman.avg).compareTo((firstBatsman.avg)))
				.limit(5).collect(Collectors.toList());
		return topStrikeRateWithMaximumAverage;
	}
    
    public List<BatsmanCsvData> topRunMaker(String BatsmanCsvFilePath) throws CsvException {
		List<BatsmanCsvData> listOfBatsman=batsmenCsvDataLoader(BatsmanCsvFilePath);
		List<BatsmanCsvData> maxRunPlayers=listOfBatsman.stream().sorted((firstBatsman,secondBatsman)->(secondBatsman.runs).compareTo((firstBatsman.runs)))
				.limit(5).collect(Collectors.toList());
		return maxRunPlayers;
	}
    public List<BatsmanCsvData> topRunMakerWithBestAverages(String BatsmanCsvFilePath) throws CsvException {
		List<BatsmanCsvData> maxRunPlayers=topRunMaker(BatsmanCsvFilePath);
		List<BatsmanCsvData> MaxRunMakerWithBestAverages=maxRunPlayers.stream().sorted((firstBatsman,secondBatsman)->(secondBatsman.avg).compareTo((firstBatsman.avg)))
				.limit(5).collect(Collectors.toList());
		return MaxRunMakerWithBestAverages;
	}
    
    public List<BowlerCsvData> topFiveBowlingAverages(String BowlerCsvFilePath) throws CsvException {
		List<BowlerCsvData> listOfBowler=bowlerCsvDataLoader(BowlerCsvFilePath);
		List<BowlerCsvData> topperAverage=listOfBowler.stream().sorted((firstBowler,secondBowler)->secondBowler.bowlerAverage.compareTo(firstBowler.bowlerAverage))
				.limit(5).collect(Collectors.toList());
		return topperAverage;
	}
    
    public List<BowlerCsvData> topBowlingStrikeRates(String BowlerCsvFilePath) throws CsvException {
		List<BowlerCsvData> listOfBowler=bowlerCsvDataLoader(BowlerCsvFilePath);
		List<BowlerCsvData> bowlerWithTopBowlingStrikeRates=listOfBowler.stream().sorted((firstBowler,secondBowler)->secondBowler.strikeRate.compareTo(firstBowler.strikeRate))
				.limit(5).collect(Collectors.toList());
		return bowlerWithTopBowlingStrikeRates;
	}
    
    public List<BowlerCsvData> topBowlingEconomy(String BowlerCsvFilePath) throws CsvException {
  		List<BowlerCsvData> listOfBowler=bowlerCsvDataLoader(BowlerCsvFilePath);
  		List<BowlerCsvData> bowlerWithTopBowlingEconomy=listOfBowler.stream().sorted((firstBowler,secondBowler)->secondBowler.economy.compareTo(firstBowler.economy))
  				.limit(5).collect(Collectors.toList());
  		return bowlerWithTopBowlingEconomy;
  	}
    
}
