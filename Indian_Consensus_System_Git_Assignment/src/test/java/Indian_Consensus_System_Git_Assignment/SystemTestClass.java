package Indian_Consensus_System_Git_Assignment;

import static org.junit.Assert.*;

import java.nio.file.Paths;

import org.junit.Test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.rules.ExpectedException;
import com.CSVExceptionJar.*;
public class SystemTestClass {

	private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
	private static final String WRONG_CSV_FILE_PATH = "desktop/IndiaStateCensusData.csv"; // no file present in this
	private static final String INDIA_CENSUS_EMPTY_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusDataEmpty.csv";
	private static final String INDIAN_CENSUS_CSV_WRONG_DELIMITER = "./src/test/resources/IndiaStateCensusDataWrongDelimeter.csv";
	private static final String INDIAN_CENSUS_CSV_WRONG_HEADER = "./src/test/resources/IndiaStateCensusDataWrongHeader.csv";

	private static final String INDIAN_CSV_STATE_PATH = "./src/test/resources/IndiaStateCode.csv";
	private static final String STATE_DATA_CSV_FILE_WRONG_PATH = "desktop/IndiaStateCode.csv";
	private static final String INDIAN_STATE_DATA_EMPTY_FILE = "./src/test/resources/IndiaStateCodeEmptyFile.csv";
	private static final String INDIAN_STATE_CSV_WRONG_DELIMITER = "./src/test/resources/IndiaStateCodeDelimeterFile.csv";
	private static final String INDIAN_STATE_CSV_WRONG_HEADER = "./src/test/resources/IndiaStateCodeWrongHeaderFile.csv";

	CensusAnalyzer censusAnalyser = null;

	// Test function which initialized every time a test case is being run.
	@Before
	public void initialize() {
		censusAnalyser = new CensusAnalyzer();
		ExpectedException exceptionRule = ExpectedException.none();
		exceptionRule.expect(CensusAnalyserException.class);
	}

	// path
	// Default Test
	@Ignore
	@Test
	public void test() {
		Assert.assertEquals(true, true);
	}

	// Checks if the number of entries in IndiaStateCensusData.csv file equals 2.
	// TC1.1
	@Test
	public void given_Indian_Census_CSV_File_Returns_Correct_Records() throws CSVException {
		try {
			int numberOfEntries = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
			Assert.assertEquals(2, numberOfEntries);
		} catch (CensusAnalyserException e) {
		}
	}

	// Handles exception when wrong file is given as an input to loadIndiaCensusData
	// function
	// TC1.2
	@Test
	public void given_India_Census_Data_With_Wrong_File_Should_Throw_Exception() throws CSVException {
		try {
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
	@Test
	public void given_Empty_Csv_File_Should_Return_Custom_Exception_Type() throws CSVException {
		try {
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
	@Test
	public void given_Wrong_Delimiter_In_India_Census_Data_Should_Return_Custom_Exception_Type() throws CSVException {
		try {
			int numberOfEntries = censusAnalyser.loadIndiaCensusData(INDIAN_CENSUS_CSV_WRONG_DELIMITER);
		} catch (CensusAnalyserException e) {
			System.out.println("Oops! There's an exception, but it's handled. So, no worries...");
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
		}
	}

	// Handles exception when file with wrong headers is given as an input to
	// loadIndiaCensusData
	// function
	// TC1.5
	@Test
	public void given_Missing_Header_In_India_Census_Data_Should_Return_Custom_Exception_Type() throws CSVException {
		try {
			int numberOfEntries = censusAnalyser.loadIndiaCensusData(INDIAN_CENSUS_CSV_WRONG_HEADER);
		} catch (CensusAnalyserException e) {
			System.out.println("Oops! There's an exception, but it's handled. So, no worries...");
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
		}
	}

	// --------------------------------------------------------------------------------------------

	// Checks if the number of entries in IndiaStateCensusData.csv file equals 2.
	// TC2.1
	@Test
	public void given_Indian_State_CSV_Should_Return_Exact_Count() throws CSVException {
		try {
			int numberOfStateCode = censusAnalyser.loadStateCode(INDIAN_CSV_STATE_PATH);
			Assert.assertEquals(2, numberOfStateCode);
		} catch (CensusAnalyserException e) {
		}
	}

	// Throws a custom exception when a wrong file (wrong file path) is given as an
	// input to the function.
	// TC2.2
	@Test
	public void given_Indian_State_Data_With_Wrong_File_Should_Throw_Exception() throws CSVException {
		try {
			int numberOfEntries = censusAnalyser.loadStateCode(STATE_DATA_CSV_FILE_WRONG_PATH);
		} catch (CensusAnalyserException e) {
			System.out.println("Oops! There's an exception, but it's handled. So, no worries...");
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}

	// Throws a custom exception when an empty file is given as an
	// input to the function.
	// TC2.3
	@Test
	public void given_Empty_State_Data_Csv_File_Should_Return_Custom_Exception_Type() throws CSVException {
		try {
			int numberOfEntries = censusAnalyser.loadStateCode(INDIAN_STATE_DATA_EMPTY_FILE);
		} catch (CensusAnalyserException e) {
			System.out.println("Oops! There's an exception, but it's handled. So, no worries...");
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
		}
	}

	// Throws a custom exception when a file with wrong delimeter is given as an
	// input to the function.
	// TC2.4
	@Test
	public void given_Wrong_Delimiter_In_India_State_Data_Should_Return_Custom_Exception_Type() throws CSVException {
		try {
			int numberOfEntries = censusAnalyser.loadStateCode(INDIAN_STATE_CSV_WRONG_DELIMITER);
		} catch (CensusAnalyserException e) {
			System.out.println("Oops! There's an exception, but it's handled. So, no worries...");
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
		}
	}

	// Throws a custom exception when a file with missing header is given as an
	// input to the function.
	// TC2.5
	@Test
	public void given_Missing_Header_In_India_State_Data_Should_Return_Custom_Exception_Type() throws CSVException {
		try {
			int numberOfEntries = censusAnalyser.loadStateCode(INDIAN_STATE_CSV_WRONG_HEADER);
		} catch (CensusAnalyserException e) {
			System.out.println("Oops! There's an exception, but it's handled. So, no worries...");
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
		}
	}
}