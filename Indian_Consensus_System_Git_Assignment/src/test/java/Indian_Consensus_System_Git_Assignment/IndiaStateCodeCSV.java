package Indian_Consensus_System_Git_Assignment;

import com.opencsv.bean.CsvBindByName;

public class IndiaStateCodeCSV {

	@CsvBindByName(column = "StateName", required = true)
	public String StateName;

	@CsvBindByName(column = "StateCode", required = true)
	public String StateCode;

	@Override
	public String toString() {
		return "IndiaStateCodeCSV [StateName=" + StateName + ", StateCode=" + StateCode + "]";
	}

}
