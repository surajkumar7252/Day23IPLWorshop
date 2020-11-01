package iplanalyser;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import com.opencsv.exceptions.CsvException;

public class IPLMainTest 
{   private static final Logger log = LogManager.getLogger(IPLMainTest.class); 
	private  final static String BATTING_CSV_FILE_PATH ="src/main/resources/FactsheetMostRuns.csv"; 
	private  final static String BOWLING_CSV_FILE_PATH = "src/main/resources/FactsheetMostWkts.csv"; 
	
    private IPLMain iplMain=new IPLMain();
	@Test
public void batsmenCsvFileWhenLoaded_ShouldReturnTheBatsmanList() {
	List<BatsmanCsvData> listOfBatsman;;
	try {
		listOfBatsman = iplMain.batsmenCsvDataLoader(BATTING_CSV_FILE_PATH);
		Assert.assertEquals(35, listOfBatsman.size());
	} catch (CsvException e) {
		e.printStackTrace();
	}
}
	@Test

public void batsmenCsvFileWhenLoaded_ShouldReturnTheBatsmanAverages() {
	List<BatsmanCsvData> topAverageBatsman;
	try {
		topAverageBatsman = iplMain.topFiveAverages(BATTING_CSV_FILE_PATH);
		log.info(topAverageBatsman.get(0));
		Assert.assertEquals("David Warner" , topAverageBatsman.get(0).name);
		Assert.assertEquals(5, topAverageBatsman.size());
		
	} catch (CsvException e) {
		e.printStackTrace();
	}
}

	public void batsmenCsvFileWhenLoaded_ShouldReturnTheBatsmanStrikerates() {
		List<BatsmanCsvData> topStrikeRates;
		try {
			topStrikeRates = iplMain.topFiveStrikeRates(BATTING_CSV_FILE_PATH);
			log.info(topStrikeRates.get(0));
			Assert.assertEquals("Ishant Sharma", topStrikeRates.get(0).name);
			Assert.assertEquals(5, topStrikeRates.size());
			
		} catch (CsvException e) {
			e.printStackTrace();
		}
	}
	public void batsmenCsvFileWhenLoaded_ShouldReturnTheMaxSixesAndFoursHitter() {
		List<BatsmanCsvData> topSixesAndFoursHitter;
		try {
			topSixesAndFoursHitter = iplMain.topSixesAndFoursHitter(BATTING_CSV_FILE_PATH);
			log.info(topSixesAndFoursHitter.get(0));
			Assert.assertEquals("Andre Russell", topSixesAndFoursHitter.get(0).name);
			Assert.assertEquals(5, topSixesAndFoursHitter.size());
			
		} catch (CsvException e) {
			e.printStackTrace();
		}
	}
}
