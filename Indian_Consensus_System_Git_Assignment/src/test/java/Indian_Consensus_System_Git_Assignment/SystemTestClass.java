package Indian_Consensus_System_Git_Assignment;

import static org.junit.Assert.*;

import java.nio.file.Paths;

import org.junit.Test;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.rules.ExpectedException;

public class SystemTestClass {
	private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";

	// Default Test
	@Ignore
	@Test
	public void test() {
		Assert.assertEquals(true, Paths.get(INDIA_CENSUS_CSV_FILE_PATH) != null);
	}

	// Checks if the number of entries in IndiaStateCensusData.csv file equals 2.
	@Test
	public void given_Indian_Census_CSV_File_Returns_Correct_Records() {
		try {
			CensusAnalyzer censusAnalyser = new CensusAnalyzer();
			int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
			Assert.assertEquals(2, numOfRecords);
		} catch (CensusAnalyserException e) {
		}
	}

}
