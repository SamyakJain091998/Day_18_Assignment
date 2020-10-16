package Indian_Consensus_System_Git_Assignment;

import com.google.gson.*;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class CensusAnalyzer {

	// Loads csv file, reads it and count the number of entries using stream api.
	// Returns count
	public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {

			final Iterator<IndiaCensusCSV> censusCsvIterator = this.returnsIteratorToTheLoadingFunction(reader,
					IndiaCensusCSV.class);
			Iterable<IndiaCensusCSV> csvIterable = () -> censusCsvIterator;

			int numOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
			System.out.println("num of entries : " + numOfEntries);
			return numOfEntries;
		} catch (IOException e) {
			// TODO: handle exception
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		} catch (RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES);
		}
	}

	// Loads csv file, reads it and count the number of entries using stream api.
	// Returns count
	public int loadStateCode(String indiaStateCSVFilePath) throws CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(indiaStateCSVFilePath));) {

			final Iterator<IndiaStateCodeCSV> StateIterator = this.returnsIteratorToTheLoadingFunction(reader,
					IndiaStateCodeCSV.class);
			Iterable<IndiaStateCodeCSV> csvIterable = () -> StateIterator;

			int numberOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
			return numberOfEntries;
		} catch (IOException e) {
			// TODO: handle exception
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		} catch (RuntimeException e) {
			System.out.println("exception msg is : " + e.getMessage());
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES);
		}
	}

	private <E> Iterator<E> returnsIteratorToTheLoadingFunction(Reader reader, Class csvClass)
			throws CensusAnalyserException {
		try {
			CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(csvClass);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<E> csvToBean = csvToBeanBuilder.build();
			return csvToBean.iterator();
		} catch (IllegalStateException e) {
			// TODO: handle exception
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
		}
	}
}
