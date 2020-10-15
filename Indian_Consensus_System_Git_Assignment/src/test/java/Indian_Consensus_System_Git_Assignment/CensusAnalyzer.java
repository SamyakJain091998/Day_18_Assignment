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
	public int loadStateCode(String indiaStateCSVFilePath) throws CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(indiaStateCSVFilePath));) {

			CsvToBeanBuilder<IndiaStateCodeCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(IndiaStateCodeCSV.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<IndiaStateCodeCSV> csvToBean = csvToBeanBuilder.build();
			final Iterator<IndiaStateCodeCSV> stateCsvIterator = csvToBean.iterator();
			Iterable<IndiaStateCodeCSV> csvIterable = () -> stateCsvIterator;

			int numberOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
//		int numOfEntries = 0;
//		while (censusCsvIterator.hasNext()) {
//			numOfEntries++;
//			IndiaCensusCSV censusData = censusCsvIterator.next();
//		}
			return numberOfEntries;
		} catch (IOException e) {
			System.out.println("type : " + e.getClass());
			// TODO: handle exception
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		} catch (IllegalStateException e) {
			System.out.println("type : " + e.getClass());
			// TODO: handle exception
			throw new CensusAnalyserException(e.getMessage(), 
					CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
		} catch (RuntimeException e) {
			System.out.println("type : " + e.getClass());
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES);
		}
	}

}
