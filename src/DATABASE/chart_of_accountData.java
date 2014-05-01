package DATABASE;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


//Számlatükör adatait kezeli!
public class chart_of_accountData {

	private DATABASE db;
	private ResultSet resultset_COA;
	private PreparedStatement preparedstatement_COA;
	
	public chart_of_accountData() throws Exception{
		db = new DATABASE();
	}
	
	//Visszaadja a 10-zel osztható számlaszámokta, tehát a fõbb számlaszámokat!
	protected ArrayList<String> GetNamestoCharts() throws Exception{
		db.MakeConnection();
		resultset_COA = db.statement.executeQuery("SELECT NAME FROM FINANCIAL.chart_of_account WHERE 0 = idCHART_OF_ACCOUNT%10");
		ArrayList<String> names = new ArrayList<String>();
		
		while(resultset_COA.next()){
			names.add(resultset_COA.getString("NAME"));
		}
		
		db.close(resultset_COA, preparedstatement_COA );
		db.close();
		
		return names;
	}
	
	
	//A 10 és 20 (Források) és a 30 és a 40 (Költségek) között lévõ számla számok nevét adja vissza.
	public ArrayList<String> GetNamestoCharts(ArrayList<Boolean> main) throws Exception{
		db.MakeConnection();
		resultset_COA = db.statement.executeQuery("SELECT * FROM FINANCIAL.chart_of_account where (idCHART_OF_ACCOUNT>=10 AND   idCHART_OF_ACCOUNT<20) OR (idCHART_OF_ACCOUNT>=30 AND   idCHART_OF_ACCOUNT<40)");
		ArrayList<String> names = new ArrayList<String>();
		
		while(resultset_COA.next()){
			names.add(resultset_COA.getString("NAME"));
			if(((resultset_COA.getInt("idCHART_OF_ACCOUNT"))%10) ==0){
				main.add(true);
			}else
				main.add(false);
		}
		
		db.close(resultset_COA, preparedstatement_COA);
		db.close();
		return names;
	}

	//Adott név alapján megadja, h melyik IDrõl van szó!
	public int GetID(String s) throws Exception{
		db.MakeConnection();
		resultset_COA = db.statement.executeQuery("SELECT idCHART_OF_ACCOUNT FROM FINANCIAL.chart_of_account WHERE NAME='"+s+"'");
		int ID = 0;
		
		while(resultset_COA.next()){
			ID = resultset_COA.getInt("idCHART_OF_ACCOUNT");
		}
		
		db.close(resultset_COA, preparedstatement_COA);
		db.close();
		
		return ID;
	}
	
	//Visszaadja a Források Neveit
	public ArrayList<String> GetFundsNames() throws Exception{
		db.MakeConnection();
		resultset_COA = db.statement.executeQuery("SELECT * FROM FINANCIAL.chart_of_account where (idCHART_OF_ACCOUNT>10 AND   idCHART_OF_ACCOUNT<20)");
		ArrayList<String> names = new ArrayList<String>();
		
		while(resultset_COA.next()){
			names.add(resultset_COA.getString("NAME"));
		}
		
		db.close(resultset_COA, preparedstatement_COA);
		db.close();
		return names;
	}

			//Megadja az IDje alapján, hogy nészerint melyik számláról van szó
			public String GetAccoutName(int ID) throws Exception{
				db.MakeConnection();
				resultset_COA = db.statement.executeQuery("SELECT NAME FROM FINANCIAL.chart_of_account WHERE idCHART_OF_ACCOUNT='"+ID+"'");
				String name = new String();
				
				while(resultset_COA.next()){
					name = resultset_COA.getString("NAME");
				}
				
				db.close(resultset_COA, preparedstatement_COA);
				db.close();
				return name;
			}
}
