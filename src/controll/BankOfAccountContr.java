package controll;

import java.util.ArrayList;

import DATABASE.bank_of_accountsData;

public class BankOfAccountContr {
	
	private bank_of_accountsData BOAdb;
	
	public BankOfAccountContr() throws Exception{
		BOAdb = new bank_of_accountsData();
	}
	
	public ArrayList<String> WriteAmountOfAccount() throws Exception{
		return BOAdb.WriteAmountOfAccount();
	}
	
	public ArrayList<String> WriteNameOfAccount() throws Exception{
		return BOAdb.WriteNameOfAccount();
	}
}
