package controll;

import java.util.ArrayList;

import DATABASE.*;


//Számlatükört kezeli!
public class ChartOFAccountContr {

	private chart_of_accountData COAdb;
	public RecordingData Rdb;
	
	public ChartOFAccountContr() throws Exception{
		COAdb = new chart_of_accountData();
		Rdb = new RecordingData();
	}
	
	public String GetAccountsName(int ID) throws Exception{
		String name = new String();
		name = COAdb.GetAccoutName(ID);
		return name;
	}
	
	public ArrayList<String> GetAccountsName() throws Exception{
		ArrayList<String> name = new ArrayList<String>();
		name = Rdb.GetAccountName();
		return name;
	}
}
