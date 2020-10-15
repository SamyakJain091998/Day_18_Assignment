package Indian_Consensus_System_Git_Assignment;

import static org.junit.Assert.*;

import java.nio.file.Paths;

import org.junit.Test;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.rules.ExpectedException;

public class SystemTestClass {
	private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
	private static final String WRONG_CSV_FILE_PATH = "desktop/IndiaStateCensusData.csv"; // no file present in this
	private static final String INDIA_CENSUS_EMPTY_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusDataEmpty.csv";
	private static final String INDIAN_CENSUS_CSV_WRONG_DELIMITER = "./src/test/resources/IndiaStateCensusDataWrongDelimeter.csv";
	private static final String INDIAN_CENSUS_CSV_WRONG_HEADER = "./src/test/resources/IndiaStateCensusDataWrongHeader.csv";
	// path
	// Default Test

	@Ignore
	@Test
	public void test() {
		Assert.assertEquals(true, Paths.get(INDIA_CENSUS_CSV_FILE_PATH) != null);
	}

	// Checks if the number of entries in IndiaStateCensusData.csv file equals 2.
	// TC1.1
	@Ignore
	@Test
	public void given_Indian_Census_CSV_File_Returns_Correct_Records() {
		try {
			CensusAnalyzer censusAnalyser = new CensusAnalyzer();
			int numberOfEntries = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
			Assert.assertEquals(2, numberOfEntries);
		} catch (CensusAnalyserException e) {
		}
	}

	// Handles exception when wrong file is given as an input to loadIndiaCensusData
	// function
	// TC1.2
	@Ignore
	@Test
	public void given_India_Census_Data_With_Wrong_File_Should_Throw_Exception() {
		try {
			CensusAnalyzer censusAnalyser = new CensusAnalyzer();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
		} catch (CensusAnalyserException e) {
			System.out.println("Oops! There's an exception, but it's handled. So, no worries...");
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}

	// Handles exception when wrong file type/ empty file is given as an input to
	// loadIndiaCensusData
	// function
	// TC1.3
	@Ignore
	@Test
	public void given_Empty_Csv_File_Should_Return_Custom_Exception_Type() {
		try {
			CensusAnalyzer censusAnalyser = new CensusAnalyzer();
			int numberOfEntries = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_EMPTY_CSV_FILE_PATH);
		} catch (CensusAnalyserException e) {
			System.out.println("Oops! There's an exception, but it's handled. So, no worries...");
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
		}
	}

	// Handles exception when file with wrong delimeter is given as an input to
	// loadIndiaCensusData
	// function
	// TC1.4
	@Ignore
	@Test
	public void given_Wrong_Delimiter_In_India_Census_Data_Should_Return_Custom_Exception_Type() {
		try {
			CensusAnalyzer censusAnalyser = new CensusAnalyzer();
			int numberOfEntries = censusAnalyser.loadIndiaCensusData(INDIAN_CENSUS_CSV_WRONG_DELIMITER);
		} catch (CensusAnalyserException e) {
			System.out.println("Oops! There's an exception, but it's handled. So, no worries...");
			System.out.println("e.type is : " + e.type);
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
		}
	}

	// Handles exception when file with wrong headers is given as an input to
	// loadIndiaCensusData
	// function
	// TC1.5
	@Test
	public void given_Missing_Header_In_India_Census_Data_Should_Return_Custom_Exception_Type() {
		try {
			CensusAnalyzer censusAnalyser = new CensusAnalyzer();
			int numberOfEntries = censusAnalyser.loadIndiaCensusData(INDIAN_CENSUS_CSV_WRONG_HEADER);
		} catch (CensusAnalyserException e) {
			System.out.println("Oops! There's an exception, but it's handled. So, no worries...");
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
		}
	}
}
