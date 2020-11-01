package iplanalyser;

import java.io.Reader;
import java.util.List;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCsvBuilder implements ICsvCreator {
	private <T> CsvToBean<T> csvToBean(Reader reader, Class<T> csvBindedClass) throws RuntimeException {
		CsvToBeanBuilder<T> beanBuilder = new CsvToBeanBuilder<>(reader);
		beanBuilder.withType(csvBindedClass);
		beanBuilder.withIgnoreLeadingWhiteSpace(true);
		CsvToBean<T> csvToBean = beanBuilder.build();
		return csvToBean;
	}
	
	public <T> List<T> getCSVList(Reader reader, Class<T> bindClass) {
		
			CsvToBean<T> csvToBean = this.csvToBean(reader, bindClass);
			List<T> csvList = csvToBean.parse();
			return  csvList;
		}
	}


