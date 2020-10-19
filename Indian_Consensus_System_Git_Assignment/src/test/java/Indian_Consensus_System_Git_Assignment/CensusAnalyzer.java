package Indian_Consensus_System_Git_Assignment;

import com.CSVExceptionJar.CSVException;
import com.google.gson.*;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.ParameterizedType;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

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
	List<IndiaStateCodeCSV> IndiaStateCodeCSVList = null;

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
			IndiaStateCodeCSVList = csvBuilder.returnsListToTheLoadingFunction(reader, IndiaStateCodeCSV.class);
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
		Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.state);
		return getSortedDataBasisParameter(censusComparator, IndiaCSVList, IndiaCensusCSV.class);
	}

	public String getPopulationWiseSortedData() throws CensusAnalyserException {

		Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.population);
		return getSortedDataBasisParameter(censusComparator, IndiaCSVList, IndiaCensusCSV.class);
	}

	public String getDensityWiseSortedData() throws CensusAnalyserException {
		Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.densityPerSqKm);
		return getSortedDataBasisParameter(censusComparator, IndiaCSVList, IndiaCensusCSV.class);
	}

	public String getAreaWiseSortedData() throws CensusAnalyserException {
		Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.areaInSqKm);
		return getSortedDataBasisParameter(censusComparator, IndiaCSVList, IndiaCensusCSV.class);
	}

	public String getStateCodeWiseSortedData() throws CensusAnalyserException {
		Comparator<IndiaStateCodeCSV> censusComparator = Comparator.comparing(census -> census.StateCode);
		return getSortedDataBasisParameter(censusComparator, IndiaStateCodeCSVList, IndiaStateCodeCSV.class);
	}

	public <E> String getSortedDataBasisParameter(Comparator<E> censusComparator, List<E> processedList,
			Class classType) throws CensusAnalyserException {
		if (processedList == null || processedList.size() == 0) {
			throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
		}
//		Class<E> persistentClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass())
//				.getActualTypeArguments()[0];
		if (classType.equals(IndiaCensusCSV.class)) {
			this.sortCensus(censusComparator);
		}
		if (classType.equals(IndiaStateCodeCSV.class)) {
			this.sortStateCensus(censusComparator);
		}
		String sortedCodeJson = new Gson().toJson(processedList);
		return sortedCodeJson;
	}

	private <E> void sortCensus(Comparator<E> censusComparator) {
		// TODO Auto-generated method stub
		for (int i = 0; i < IndiaCSVList.size() - 1; i++) {
			for (int j = 0; j < IndiaCSVList.size() - i - 1; j++) {
				E census1 = (E) IndiaCSVList.get(j);
				E census2 = (E) IndiaCSVList.get(j + 1);
				if (censusComparator.compare(census1, census2) < 0) {
					IndiaCSVList.set(j, (IndiaCensusCSV) census2);
					IndiaCSVList.set(j + 1, (IndiaCensusCSV) census1);
				}
			}
		}
	}

	private <E> void sortStateCensus(Comparator<E> censusComparator) {
		// TODO Auto-generated method stub
		for (int i = 0; i < IndiaStateCodeCSVList.size() - 1; i++) {
			for (int j = 0; j < IndiaStateCodeCSVList.size() - i - 1; j++) {
				E census1 = (E) IndiaStateCodeCSVList.get(j);
				E census2 = (E) IndiaStateCodeCSVList.get(j + 1);
				if (censusComparator.compare(census1, census2) > 0) {
					IndiaStateCodeCSVList.set(j, (IndiaStateCodeCSV) census2);
					IndiaStateCodeCSVList.set(j + 1, (IndiaStateCodeCSV) census1);
				}
			}
		}
	}
}
