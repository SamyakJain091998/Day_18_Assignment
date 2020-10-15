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
	public int loadStateCode(String indiaCensusCSVFilePath) throws IOException {
		try (Reader reader = Files.newBufferedReader(Paths.get(indiaCensusCSVFilePath));) {

			CsvToBeanBuilder<IndiaStateCodeCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(IndiaStateCodeCSV.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<IndiaStateCodeCSV> csvToBean = csvToBeanBuilder.build();
			final Iterator<IndiaStateCodeCSV> stateCsvIterator = csvToBean.iterator();
			Iterable<IndiaStateCodeCSV> csvIterable = () -> stateCsvIterator;

			int numOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
			System.out.println("num of entries : " + numOfEntries);
//		int numOfEntries = 0;
//		while (censusCsvIterator.hasNext()) {
//			numOfEntries++;
//			IndiaCensusCSV censusData = censusCsvIterator.next();
//		}
			return numOfEntries;
		}
	}
}
