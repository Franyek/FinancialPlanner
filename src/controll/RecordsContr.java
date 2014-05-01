package controll;

import java.sql.Date;
import java.util.ArrayList;

import DATABASE.*;

public class RecordsContr {

	private RecordingData Rdb;
	private chart_of_accountData COAdb;
	
	public ArrayList<Integer> ID = new ArrayList<Integer>();
	public ArrayList<java.sql.Date> dates = new ArrayList<java.sql.Date>();
	public ArrayList<Integer> Froms = new ArrayList<Integer>();
	public ArrayList<Integer> Wheres = new ArrayList<Integer>();
	public ArrayList<Double> amounts = new ArrayList<Double>();
	public ArrayList<String> Comments = new ArrayList<String>();
	
	public RecordsContr() throws Exception{
		Rdb = new RecordingData();
		COAdb = new chart_of_accountData();
	}
	
	public void GetsRecords(int d) throws Exception{
		Rdb.WriteRecords(d, dates, Froms, Wheres, amounts, Comments);
	}
	
	//Visszaadja az adott idõintervallum között lévõ rekordokat
	public void GetsRecords(Date From, Date Till) throws Exception{
		Rdb.WriteRecords(From, Till, ID, dates, Froms, Wheres, amounts, Comments);
	}
	
	//Az ID alapján kikeresi a törlendõ rekordot és kitörli az adatbázisból!
	public void DeleteRecords(int ID) throws Exception{
		Rdb.DeletRecord(ID);
	}
	
	public void SetRecord(String F, String W, Double am, String com) throws Exception{
		Rdb.RecordsSet(COAdb.GetID(F), COAdb.GetID(W), am, com);
	}
	
	
	
}
