package iplanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opencsv.exceptions.CsvException;


public class IPLMain 
{
	private static final Logger log = LogManager.getLogger(IPLMain.class); 
	private List<BatsmanCsvData> listOfBatsman;
	private List<BowlerCsvData>listOfBowler;
	
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
            log.info("\n9.Bowler with Best Bowling Economy.");
            log.info("\n10.Bowler with Max Four and Five Wickets With Max strike rate.");
            log.info("\n11.Bowler with Best Average and With Max strike rate.");
            log.info("\n12.Bowler with Best Average and With Max Wickets.");
            log.info("\n13.Allrounders with best batting and balling average.");
            log.info("\n14.Allrounders with Most Wickets and Most Runs.");
            log.info("\n15.Batsman with Most Hunderds and Best Average.");
            
            

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
        	case 10: newIplMain.topFiveAndFourWicketTakersWithBestBowlingStrikeRate(BOWLING_CSV_FILE_PATH);
        	        break;
        	case 11: newIplMain.bestBowlingAverageWithBestBowlingStrikeRate(BOWLING_CSV_FILE_PATH);
	                break;
        	case 12: newIplMain.bestBowlingAverageWithMaxWickets(BOWLING_CSV_FILE_PATH);
                    break;
        	case 13: newIplMain.BestBowlingAndBattingAveragePlayers();
                    break;
        	case 14: newIplMain.bestRunAndWicketTakers();
        	        break;
        	case 15: newIplMain.topHunderdScorerWithBestAverages(BATTING_CSV_FILE_PATH);
        	        break;
        	}
        	
        }while(choice>0 && choice<=15);
        
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
    
    public List<BatsmanCsvData> topHunderdScorerWithBestAverages(String BatsmanCsvFilePath) throws CsvException {
		List<BatsmanCsvData> topFiveAveragePlayers=topFiveAverages(BatsmanCsvFilePath);
		List<BatsmanCsvData> topHunderdScorerWithBestAveragePlayers=topFiveAveragePlayers.stream().sorted((firstBatsman,secondBatsman)->secondBatsman.hundreds.compareTo(firstBatsman.hundreds))
				.limit(5).collect(Collectors.toList());
		return topHunderdScorerWithBestAveragePlayers;
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
    
    public List<BowlerCsvData> topFiveAndFourWicketTakers(String BowlerCsvFilePath) throws CsvException {
  		List<BowlerCsvData> listOfBowler=bowlerCsvDataLoader(BowlerCsvFilePath);
  		List<BowlerCsvData> topFiveAndFourWicketTakingBowlers=listOfBowler.stream().sorted((firstBowler,secondBowler)->(secondBowler.getMaxFourAndFiveWicket).compareTo((firstBowler.getMaxFourAndFiveWicket)))
  				.limit(5).collect(Collectors.toList());
  		return topFiveAndFourWicketTakingBowlers;
  	}
    
    public List<BowlerCsvData> topFiveAndFourWicketTakersWithBestBowlingStrikeRate(String BowlerCsvFilePath) throws CsvException {
  		List<BowlerCsvData> topFiveAndFourWicketTakingBowlers=topFiveAndFourWicketTakers(BowlerCsvFilePath);
  		List<BowlerCsvData> topFiveAndFourWicketTakingBowlersWithBestStrikeRate=topFiveAndFourWicketTakingBowlers.stream().sorted((firstBowler,secondBowler)->(secondBowler.strikeRate).compareTo((firstBowler.strikeRate)))
  				.limit(5).collect(Collectors.toList());
  		return topFiveAndFourWicketTakingBowlersWithBestStrikeRate;
  	}
    
    public List<BowlerCsvData> bestBowlingAverageWithBestBowlingStrikeRate(String BowlerCsvFilePath) throws CsvException {
  		List<BowlerCsvData> topFiveBowlingAverageBowlers=topFiveBowlingAverages(BowlerCsvFilePath);
  		List<BowlerCsvData> bolwersWithBestBowlingAverageWithBestBowlingStrikeRate=topFiveBowlingAverageBowlers.stream().sorted((firstBowler,secondBowler)->(secondBowler.strikeRate).compareTo((firstBowler.strikeRate)))
  				.limit(5).collect(Collectors.toList());
  		return bolwersWithBestBowlingAverageWithBestBowlingStrikeRate;
  	}
    
    public List<BowlerCsvData> bestBowlingAverageWithMaxWickets(String BowlerCsvFilePath) throws CsvException {
  		List<BowlerCsvData> topFiveBowlingAverageBowlers=topFiveBowlingAverages(BowlerCsvFilePath);
  		List<BowlerCsvData> bolwersWithBestBowlingAverageWithMaxWickets=topFiveBowlingAverageBowlers.stream().sorted((firstBowler,secondBowler)->(secondBowler.wickets).compareTo((firstBowler.wickets)))
  				.limit(5).collect(Collectors.toList());
  		return bolwersWithBestBowlingAverageWithMaxWickets;
  	}
    
    public List<BowlerCsvData> BestBowlingAndBattingAveragePlayers() {
		   List<BowlerCsvData>allRounderPlayers=listOfBowler.stream().filter(bowler->{
			   return (bowler.bowlerAverage!=0) && listOfBatsman.stream().anyMatch(batsman->batsman.name.equals(bowler.name));}).collect(Collectors.toList());
		 allRounderPlayers.stream().sorted(Comparator.comparing(localAllRoundPlayers->{
		    	BatsmanCsvData batsman= listOfBatsman.stream().filter(batsMan->batsMan.name.equals(localAllRoundPlayers.name)).findFirst().orElse(null);
			return localAllRoundPlayers.bowlerAverage*(1/batsman.avg);
		})).collect(Collectors.toList());
	}
    public List<BowlerCsvData> bestRunAndWicketTakers() {
    	 List<BowlerCsvData>allRounderPlayers=listOfBowler.stream().filter(bowler->{
			   return (bowler.wickets!=0) && listOfBatsman.stream().anyMatch(batsman->batsman.name.equals(bowler.name));}).collect(Collectors.toList());
		   allRounderPlayers.stream().sorted(Comparator.comparing(localAllRoundPlayers->{
		    	BatsmanCsvData batsman= listOfBatsman.stream().filter(batsMan->batsMan.name.equals(localAllRoundPlayers.name)).findFirst().orElse(null);
		    	return 1 / (localAllRoundPlayers.wickets * batsman.runs);
		})).collect(Collectors.toList());
	}
	}
    

