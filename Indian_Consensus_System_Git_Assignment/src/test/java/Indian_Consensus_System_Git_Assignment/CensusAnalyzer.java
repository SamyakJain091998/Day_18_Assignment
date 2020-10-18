package Indian_Consensus_System_Git_Assignment;

import com.CSVExceptionJar.CSVException;
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
	public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException, CSVException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {

			ICSVBuilder csvBuilder = CSVBuilderFactor.createCSVBuilder();
//			Iterator<IndiaCensusCSV> censusCsvIterator = csvBuilder.returnsIteratorToTheLoadingFunction(reader,
//					IndiaCensusCSV.class);
			List<IndiaCensusCSV> IndiaCSVList = csvBuilder.returnsListToTheLoadingFunction(reader,
					IndiaCensusCSV.class);
			return IndiaCSVList.size();
//			return returnsCountOfEntries(censusCsvIterator, IndiaCensusCSV.class);
		} catch (IOException e) {
			// TODO: handle exception
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		} catch (RuntimeException e) {
			e.printStackTrace();
			System.out.println("e.egtmesg() : " + e.getMessage());
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES);
		}
	}

	// Loads csv file, reads it and count the number of entries using stream api.
	// Returns count
	public int loadStateCode(String indiaStateCSVFilePath) throws CensusAnalyserException, CSVException {
		try (Reader reader = Files.newBufferedReader(Paths.get(indiaStateCSVFilePath));) {
			ICSVBuilder csvBuilder = CSVBuilderFactor.createCSVBuilder();
//			Iterator<IndiaStateCodeCSV> StateIterator = csvBuilder.returnsIteratorToTheLoadingFunction(reader,
//					IndiaStateCodeCSV.class);
			List<IndiaStateCodeCSV> IndiaStateCodeCSVList = csvBuilder.returnsListToTheLoadingFunction(reader,
					IndiaStateCodeCSV.class);
			return IndiaStateCodeCSVList.size();
//			return returnsCountOfEntries(StateIterator, IndiaStateCodeCSV.class);
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
