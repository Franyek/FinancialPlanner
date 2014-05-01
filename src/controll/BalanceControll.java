package controll;

import java.util.ArrayList;

import DATABASE.*;

public class BalanceControll {

	public ArrayList<String> names;
	public ArrayList<Double> amounts;
	public ArrayList<Boolean> main;
	
	public chart_of_accountData COAdb;
	public RecordingData Rdb;
	
	public BalanceControll() throws Exception{
		COAdb = new chart_of_accountData();
		Rdb = new RecordingData();
	}
	
	public void GetNames() throws Exception{
		main = new ArrayList<Boolean>();
		names = new ArrayList<String>(COAdb.GetNamestoCharts( main));
	}
	
	public void GetAmounts() throws Exception{
		amounts = new ArrayList<Double>();
		Rdb.GetAnounts(amounts);
	}
	
	
	
}
