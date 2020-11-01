package iplanalyser;
import com.opencsv.bean.CsvBindByName;

public class BatsmanCsvData {
	
        	@CsvBindByName(column = "PLAYER", required = true)
			public String name;
			
			@CsvBindByName(column = "Mat", required = true)
			public Integer matches;
			
			@CsvBindByName(column = "Inns", required = true)
			public Integer innings;
			
			@CsvBindByName(column = "NO", required = true)
			public Integer notOut;
			
			@CsvBindByName(column = "Runs", required = true)
			public Integer runs;
			
			@CsvBindByName(column = "HS", required = true)
			public String highestScore;
			
			@CsvBindByName(column = "Avg", required = true)
			public Double avg;
			
			@CsvBindByName(column = "BF", required = true)
			public Integer ballFaced;
			
			@CsvBindByName(column = "SR", required = true)
			public Double strikeRate;
			
			@CsvBindByName(column = "100", required = true)
			public Integer hundreds;
			
			@CsvBindByName(column = "50", required = true)
			public Integer fifty;
			
			@CsvBindByName(column = "4s", required = true)
			public Integer fours;
			
			@CsvBindByName(column = "6s", required = true)
			public Integer sixes;

			@Override
			public String toString() {
				return "Name=" + name + ","+" Matches=" + matches + ","+" Innings=" + innings + ","+" Runs=" + runs
						+ ","+" Highest Score=" + highestScore + ","+" Average=" + avg + ","+" Strike Rate=" + strikeRate + ","+"Hundreds="
						+ hundreds + ","+" Fifties=" + fifty + ","+" Fours=" + fours + ","+"Sixes=" + sixes;
						
			}

			public double getBatsmanAverage() {
				return avg;
			}
		}