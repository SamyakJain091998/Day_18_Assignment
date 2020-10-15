package Indian_Consensus_System_Git_Assignment;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.Test;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.rules.ExpectedException;

public class SystemTestClass {
	private static final String INDIAN_CSV_STATE_PATH = "./src/test/resources/IndiaStateCode.csv";
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
	public void given_Indian_State_CSV_Should_Return_Exact_Count() throws IOException {
		CensusAnalyzer censusAnalyser = new CensusAnalyzer();
		int numberOfStateCode = censusAnalyser.loadStateCode(INDIAN_CSV_STATE_PATH);
		Assert.assertEquals(2, numberOfStateCode);

	}
}