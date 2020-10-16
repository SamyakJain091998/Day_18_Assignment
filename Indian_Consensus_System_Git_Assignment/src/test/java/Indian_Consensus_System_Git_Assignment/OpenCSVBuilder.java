package Indian_Consensus_System_Git_Assignment;

import java.io.Reader;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCSVBuilder<E> implements ICSVBuilder<E> {

	public OpenCSVBuilder() {
	}

	public Iterator<E> returnsIteratorToTheLoadingFunction(Reader reader, Class csvClass) throws CSVException {
		try {
			CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(csvClass);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<E> csvToBean = csvToBeanBuilder.build();
			return csvToBean.iterator();
		} catch (IllegalStateException e) {
			// TODO: handle exception
			throw new CSVException(e.getMessage(), CSVException.CSVExceptionType.UNABLE_TO_PARSE);
		}
	}
}
