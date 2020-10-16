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

			final Iterator<IndiaCensusCSV> censusCsvIterator = new OpenCSVBuilder()
					.returnsIteratorToTheLoadingFunction(reader, IndiaStateCodeCSV.class);

			return returnsCountOfEntries(censusCsvIterator, IndiaCensusCSV.class);
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

			final Iterator<IndiaStateCodeCSV> StateIterator = new OpenCSVBuilder()
					.returnsIteratorToTheLoadingFunction(reader, IndiaStateCodeCSV.class);

			return returnsCountOfEntries(StateIterator, IndiaStateCodeCSV.class);
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

	private <E> int returnsCountOfEntries(Iterator<E> iterator, Class csvClass) {
		Iterable<E> csvIterable = () -> iterator;
		int numberOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
		return numberOfEntries;
	}
}
