package iplanalyser;

import com.opencsv.bean.CsvBindByName;

public class BowlerCsvData {

	@CsvBindByName(column = "PLAYER", required = true)
	public String name;
	
	@CsvBindByName(column = "Mat", required = true)
	public Integer matches;
	
	@CsvBindByName(column = "Inns", required = true)
	public Integer innings;
	
	@CsvBindByName(column = "Ov", required = true)
	public Integer overs;
	
	@CsvBindByName(column = "Runs", required = true)
	public Integer runs;
	
	@CsvBindByName(column = "Wkts", required = true)
	public String wickets;
	
	@CsvBindByName(column = "BBI", required = true)
	public Double bbi;
	
	@CsvBindByName(column = "Avg", required = true)
	public Integer bowlerAverage;
	

	@CsvBindByName(column = "Econ", required = true)
	public Integer economy;
	
	@CsvBindByName(column = "SR", required = true)
	public Double strikeRate;
	
	
	@CsvBindByName(column = "4w", required = true)
	public Integer fourWickets;
	
	@CsvBindByName(column = "5w", required = true)
	public Integer fiveWickets;

	@Override
	public String toString() {
		return "Name=" + name + ","+" Matches=" + matches + ","+" Innings=" + innings + ","+"Overs="+overs+","+" Runs=" + runs
				+ ","+" Wickets=" + wickets + ","+" BBI=" + bbi + ","+" Average=" + bowlerAverage + ","+"Economy="
				+ economy + ","+" Strike Rate=" + strikeRate + ","+" 4 Wickets=" + fourWickets + ","+" 5 Wickets=" + fiveWickets;
				
	}

	
}