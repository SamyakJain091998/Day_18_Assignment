package Indian_Consensus_System_Git_Assignment;

import static org.junit.Assert.*;

import java.nio.file.Paths;

import org.junit.Test;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.rules.ExpectedException;

public class SystemTestClass {
	private static final String INDIAN_CSV_STATE_PATH = "./src/test/resources/IndiaStateCode.csv";
	private static final String STATE_DATA_CSV_FILE_WRONG_PATH = "desktop/IndiaStateCode.csv";
	private static final String INDIAN_STATE_DATA_EMPTY_FILE = "./src/test/resources/IndiaStateCodeEmptyFile.csv";
	private static final String INDIAN_STATE_CSV_WRONG_DELIMITER = "./src/test/resources/IndiaStateCodeDelimeterFile.csv";
	
	// path
	// Default Test

	@Ignore
	@Test
	public void test() {
		Assert.assertEquals(true, true);
	}

	// Checks if the number of entries in IndiaStateCensusData.csv file equals 2.
	// TC1.1
	@Ignore
	@Test
	public void given_Indian_State_CSV_Should_Return_Exact_Count() {
		try {
			CensusAnalyzer censusAnalyser = new CensusAnalyzer();
			int numberOfStateCode = censusAnalyser.loadStateCode(INDIAN_CSV_STATE_PATH);
			Assert.assertEquals(2, numberOfStateCode);
		} catch (CensusAnalyserException e) {
		}
	}

	@Ignore
	@Test
	public void given_Indian_State_Data_With_Wrong_File_Should_Throw_Exception() {
		try {
			CensusAnalyzer censusAnalyser = new CensusAnalyzer();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			int numberOfEntries = censusAnalyser.loadStateCode(STATE_DATA_CSV_FILE_WRONG_PATH);
		} catch (CensusAnalyserException e) {
			System.out.println("Oops! There's an exception, but it's handled. So, no worries...");
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}

	@Ignore
	@Test
	public void given_Empty_State_Data_Csv_File_Should_Return_Custom_Exception_Type() {
		try {
			CensusAnalyzer censusAnalyser = new CensusAnalyzer();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			int numberOfEntries = censusAnalyser.loadStateCode(STATE_DATA_CSV_FILE_WRONG_PATH);
		} catch (CensusAnalyserException e) {
			System.out.println("Oops! There's an exception, but it's handled. So, no worries...");
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void given_Wrong_Delimiter_In_India_State_Data_Should_Return_Custom_Exception_Type() {
		try {
			CensusAnalyzer censusAnalyser = new CensusAnalyzer();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			int numberOfEntries = censusAnalyser.loadStateCode(INDIAN_STATE_CSV_WRONG_DELIMITER);
		} catch (CensusAnalyserException e) {
			System.out.println("Oops! There's an exception, but it's handled. So, no worries...");
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
		}
	}
}