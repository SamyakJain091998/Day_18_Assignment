package Indian_Consensus_System_Git_Assignment;

import java.io.Reader;
import java.util.Iterator;

import com.CSVExceptionJar.CSVException;

public interface ICSVBuilder<E> {
	public Iterator<E> returnsIteratorToTheLoadingFunction(Reader reader, Class csvClass)
			throws CensusAnalyserException, CSVException;
}
