package controll;

import java.sql.Date;
import java.util.ArrayList;
import graphical_elements.*;

import DATABASE.*;

public class GraphicControll {
	
	protected bank_of_accountsData b_o_adb;
	protected RecordingData Rdb;
	protected GraphicPanelPopup GPOP;
	
	public ArrayList<Double> a = new ArrayList<Double>();		
	public ArrayList<String> n = new ArrayList<String>();
	
	public GraphicControll() throws Exception{
		b_o_adb = new bank_of_accountsData();
	}
	
	public GraphicControll(String option, Date From, Date till) throws Exception{
		Rdb = new RecordingData();
		if(option.equals("costs")){
			Rdb.GetNamesandAmounts(From, till, n, a, 30, 40);
		} else if (option.equals("sources")){
			Rdb.GetNamesandAmounts(From, till, n, a, 10, 20);
		} else if(option.equals("funds")){
			b_o_adb = new bank_of_accountsData();
			b_o_adb.GetNamesandAmounts(n, a);
		}
	}
	
	public GraphicControll(String option) throws Exception{
		b_o_adb = new bank_of_accountsData();
		if(option.equals("funds")){
			b_o_adb.GetNamesandAmounts(n, a);
		}
	}
	
	public void GetNamesandAmounts() throws Exception{
		b_o_adb.GetNamesandAmounts(n, a);
	}
	
}
