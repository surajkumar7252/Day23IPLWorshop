package iplanalyser;

import java.io.Reader;
import java.util.List;
import com.opencsv.exceptions.CsvException;

public interface ICsvCreator {
	
	public <T> List<T>  getCSVList(Reader reader, Class<T> bindClass) throws CsvException;

}
