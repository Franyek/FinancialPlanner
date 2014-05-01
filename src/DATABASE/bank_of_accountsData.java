package DATABASE;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class bank_of_accountsData {

	DATABASE db;
	
	private ResultSet resultset_BOA;
	private PreparedStatement preparedstatement_BOA;
	
	//Pénzt tartalmazó számlák összegét tartalmazza!
	public bank_of_accountsData() throws Exception{
		db = new DATABASE();
	}
	
	
	//Visszaadja a megadott ArrayListekben a sorok neveit és értékeit.
	public void GetNamesandAmounts(ArrayList<String> names, ArrayList<Double> amounts) throws Exception{
		db.MakeConnection();
		resultset_BOA = db.statement.executeQuery("SELECT NAMES, AMOUNT FROM FINANCIAL.bank_of_accounts");
		
		while(resultset_BOA.next()){
			names.add(resultset_BOA.getString("NAMES"));
			amounts.add(resultset_BOA.getDouble("AMOUNT"));
		}
		
		db.close(resultset_BOA, null);
		db.close();
	}
	
	public ArrayList<String> WriteNameOfAccount() throws Exception{
		ArrayList<String> names = new ArrayList<String>();
		db.MakeConnection();
		resultset_BOA = db.statement.executeQuery("select * from FINANCIAL.bank_of_accounts");
		
		while(resultset_BOA.next()){
			names.add(resultset_BOA.getString("NAMES"));
		}
		
		db.close(resultset_BOA, null);
		db.close();
		return names;
	}
	
	public ArrayList<String> WriteAmountOfAccount() throws Exception{
		ArrayList<String> amounts = new ArrayList<String>();
		db.MakeConnection();
		resultset_BOA = db.statement.executeQuery("select * from FINANCIAL.bank_of_accounts");
		
		while(resultset_BOA.next()){
			amounts.add(resultset_BOA.getString("AMOUNT"));
		}
		
		db.close(resultset_BOA, null);
		db.close();
		return amounts;
	}
}
