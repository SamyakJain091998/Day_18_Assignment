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
	public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {

			CsvToBeanBuilder<IndiaCensusCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(IndiaCensusCSV.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<IndiaCensusCSV> csvToBean = csvToBeanBuilder.build();
			final Iterator<IndiaCensusCSV> censusCsvIterator = csvToBean.iterator();
			Iterable<IndiaCensusCSV> csvIterable = () -> censusCsvIterator;

			int numOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
			System.out.println("num of entries : " + numOfEntries);
//			int numOfEntries = 0;
//			while (censusCsvIterator.hasNext()) {
//				numOfEntries++;
//				IndiaCensusCSV censusData = censusCsvIterator.next();
//			}
			return numOfEntries;
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		} catch (IllegalStateException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
		} catch (RuntimeException e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES);
		}
	}

}
