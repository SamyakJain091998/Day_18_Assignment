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
	List<IndiaCensusCSV> IndiaCSVList = null;

	// Loads csv file, reads it and count the number of entries using stream api.
	// Returns count
	public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException, CSVException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {

			ICSVBuilder csvBuilder = CSVBuilderFactor.createCSVBuilder();
//			Iterator<IndiaCensusCSV> censusCsvIterator = csvBuilder.returnsIteratorToTheLoadingFunction(reader,
//					IndiaCensusCSV.class);
			IndiaCSVList = csvBuilder.returnsListToTheLoadingFunction(reader, IndiaCensusCSV.class);
			return IndiaCSVList.size();
//			return returnsCountOfEntries(censusCsvIterator, IndiaCensusCSV.class);
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
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES);
		}
	}

	private <E> int returnsCountOfEntries(Iterator<E> iterator, Class csvClass) {
		Iterable<E> csvIterable = () -> iterator;
		int numberOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
		return numberOfEntries;
	}

	public String getStateWiseSortedData() throws CensusAnalyserException {
		if(IndiaCSVList == null || IndiaCSVList.size() == 0) {
			throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
		}
		Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.state);
		this.sort(censusComparator);
		String sortedStateCensusJson = new Gson().toJson(IndiaCSVList);
		return sortedStateCensusJson;
	}

	private void sort(Comparator<IndiaCensusCSV> censusComparator) {
		// TODO Auto-generated method stub
		for (int i = 0; i < IndiaCSVList.size() - 1; i++) {
			for (int j = 0; j < IndiaCSVList.size() - i - 1; j++) {
				IndiaCensusCSV census1 = IndiaCSVList.get(j);
				IndiaCensusCSV census2 = IndiaCSVList.get(j + 1);
				if (censusComparator.compare(census1, census2) > 0) {
					IndiaCSVList.set(j, census2);
					IndiaCSVList.set(j + 1, census1);
				}
			}
		}
	}
}
