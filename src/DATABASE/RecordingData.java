package DATABASE;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.*;

//Rekordokat tartalmazó tábla
public class RecordingData {

	private DATABASE db;
	private chart_of_accountData COAdb;
	private ResultSet resultset;
	private ResultSet resultset_BOA;
	private ResultSet resultset_IO;
	private ResultSet resultset_R;
	
	private PreparedStatement preparedstatement_BOA;
	private PreparedStatement preparedstatement_IO;
	private PreparedStatement preparedstatement_R;
	
	public RecordingData() throws Exception{
		db = new DATABASE();
		COAdb = new chart_of_accountData();
	}
	
	//Rekordok felvétele. Ez befojásolja a bank_account-ot és az incomes_outcomes-ot.
	//Frissiti az income_outcome és bank_of_accounts táblát
	public void RecordsSet(int From, int Where, double amount, String comment) throws Exception{
		db.MakeConnection();
		
		preparedstatement_R = db.connection.prepareStatement("INSERT INTO financial.`records` (`Date`, `From`, `Where`, `Amount`, `Comment`) " +
				"VALUES (CURDATE(), "+From+", "+Where+", "+amount+", '"+comment+"');");
		preparedstatement_R.executeUpdate();
		
		int in =0;
		int out = 0;
		
		//Bank_of_accounts frissítése
		if(From>20 && From<30){
			preparedstatement_BOA = db.connection.prepareStatement("UPDATE financial.bank_of_accounts "+
					"SET AMOUNT = AMOUNT- "+amount +
					" WHERE idBANK_OF_ACCOUNTS= " +From);
			preparedstatement_BOA.executeUpdate();
			out +=amount;
		} else if(Where > 20 && Where <30){
			preparedstatement_BOA = db.connection.prepareStatement("UPDATE financial.bank_of_accounts "+
					"SET AMOUNT = AMOUNT+ "+amount+
					" WHERE idBANK_OF_ACCOUNTS= "+Where);
			preparedstatement_BOA.executeUpdate();
			in+=amount;
		}
		
		java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
		
		resultset_IO = db.statement.executeQuery("SELECT MAX(Years_Months) FROM FINANCIAL.incomes_outcomes ;");
		resultset_IO.next();
		Date d = resultset_IO.getDate("MAX(Years_Months)");
		//incomes_outcomes frissítése
		if(d.getMonth() != date.getMonth()){
			preparedstatement_IO = db.connection.prepareStatement("INSERT INTO FINANCIAL.incomes_outcomes (Years_Months, Income, Outcome)" 
					+" VALUES ('"+(1900+date.getYear())+"-"+(date.getMonth()+1)+"-01', "+in+", "+out+")");
			preparedstatement_IO.executeUpdate();
		}else{
			preparedstatement_IO = db.connection.prepareStatement("UPDATE FINANCIAL.incomes_outcomes "+
					"SET Income= Income+"+in+", Outcome= Outcome+"+out
					+" WHERE  Years_Months='"+(1900+date.getYear())+"-"+(date.getMonth()+1)+"-01'");
			preparedstatement_IO.executeUpdate();
		}
		close();
		db.close();
	}
	
	//Visszaadja a meghatározott IDjüek összegét, az adott intervallumban, a megadott ArrayListekbe
	public void GetNamesandAmounts(Date From, Date Till, ArrayList<String> NAMES, ArrayList<Double> amounts, int from, int till) throws Exception{
		db.MakeConnection();
		String name =null;
		if(from==10){
			resultset = db.statement.executeQuery("SELECT SUM(Amount), financial.records.From "
					+"FROM financial.records "
					+"WHERE financial.records.From > "+from+" AND financial.records.From <"+till+" AND Date>'"+From+"' AND Date<'"+Till
					+"' Group By financial.records.From");
			while(resultset.next()){
				name = COAdb.GetAccoutName(resultset.getInt("From"));
				NAMES.add(name);
				amounts.add(resultset.getDouble("SUM(Amount)"));
			}
		}
		else {
			resultset = db.statement.executeQuery("SELECT SUM(Amount), financial.records.Where "
					+"FROM financial.records "
					+"WHERE financial.records.Where > "+from+" AND financial.records.Where <"+till+" AND Date>'"+From+"' AND Date<'"+Till
					+"' Group By financial.records.Where");
			while(resultset.next()){
				name = COAdb.GetAccoutName(resultset.getInt("Where"));
				NAMES.add(name);
				amounts.add(resultset.getDouble("SUM(Amount)"));
			}
		}		
		db.close();
		close();
	}

	//Lekérdezi a Forrás és Költség számlák összegét
	public void GetAnounts(ArrayList<Double> amounts) throws Exception{
		db.MakeConnection();
		
		//Számlatükör adatai
		int[] f = {11, 12, 13, 14, 15};
		int[] k = {31, 32, 33, 34, 35, 36, 37, 38, 39};
		//Lekérdezni a Source értékeit, ami egyszerû, mert csak a From oldalán lehet
		resultset_R = db.statement.executeQuery("SELECT FINANCIAL.RECORDS.FROM, AMOUNT "+ 
				"FROM FINANCIAL.RECORDS where (FINANCIAL.RECORDS.FROM>=10 AND FINANCIAL.RECORDS.FROM<20) "+
				"GROUP BY FINANCIAL.RECORDS.FROM");
		amounts.add(0.0);
		int i = 0;
		//FEltölteni a kapott értékekkel
		while(resultset_R.next()){
			if(f[i] == resultset_R.getInt("FROM"))
				amounts.add(resultset_R.getDouble("AMOUNT"));
			else
				amounts.add(0.0);
			i++;
		}
		double sum=0;//Kiszámoljuk a források összesent
		for(int w=0; w< amounts.size(); w++)
			sum+=amounts.get(w);
		amounts.set(0, sum); 
		
		//Ugyenezt megcsináljuk a költségekkel is
		resultset_R = db.statement.executeQuery("SELECT FINANCIAL.RECORDS.WHERE, AMOUNT "+ 
			"FROM FINANCIAL.RECORDS where (FINANCIAL.RECORDS.WHERE>=30 AND FINANCIAL.RECORDS.WHERE<40) "+
			"GROUP BY FINANCIAL.RECORDS.WHERE");
		
		i = 0;
		int size = amounts.size();
		amounts.add(0.0);
		while(resultset_R.next()){
			if(k[i] == resultset_R.getInt("WHERE"))
				amounts.add(resultset_R.getDouble("AMOUNT"));
			else
				amounts.add(0.0);
			i++;
		}
		sum=0;
		for(int w=size; w< amounts.size(); w++)
			sum+=amounts.get(w);
		
		amounts.set(size, sum);
		close();
		db.close();	
	}
	
	//ID alapján kikeresi az adott rekordot és törli a táblából
	public void DeletRecord(int ID) throws Exception{
			db.MakeConnection();
			resultset_R = db.statement.executeQuery("SELECT * FROM FINANCIAL.RECORDS");
			
			preparedstatement_R = db.connection.prepareStatement("DELETE FROM financial.records WHERE idRecords="+ID+";");
			preparedstatement_R.executeUpdate();
			db.close(resultset_R, preparedstatement_R);
			db.close();
		}
	
	//Visszaadja a legutóbbi x napban felvett rekordot. 
	public void WriteRecords(int d, ArrayList<java.sql.Date> dates, ArrayList<Integer> Froms,
				ArrayList<Integer> Wheres, ArrayList<Double> amounts, ArrayList<String> Comments) throws Exception{
			db.MakeConnection();
			resultset_R = db.statement.executeQuery("SELECT * FROM financial.RECORDS WHERE DATE_SUB(CURDATE() ,INTERVAL "+d+" DAY) <= Date;");
			
			while(resultset_R.next()){
				dates.add(resultset_R.getDate("Date"));
				Froms.add(resultset_R.getInt("From"));
				Wheres.add(resultset_R.getInt("Where"));
				amounts.add(resultset_R.getDouble("Amount"));
				Comments.add(resultset_R.getString("Comment"));
			}
			
			db.close(resultset_R, preparedstatement_R);
			db.close();
		}
	
	//Kiírja a recordokat valamettõl (Form) valameddig (Till)
		public void WriteRecords(Date From, Date Till, ArrayList<Integer> ID, ArrayList<java.sql.Date> dates, ArrayList<Integer> Froms,
				ArrayList<Integer> Wheres, ArrayList<Double> amounts, ArrayList<String> Comments) throws Exception{
			db.MakeConnection();
			
			resultset_R = db.statement.executeQuery("SELECT * FROM financial.RECORDS WHERE Date >= '"+ From +"' AND Date <= '"+Till+"';");
			
			while(resultset_R.next()){
				ID.add(resultset_R.getInt("idRecords"));
				dates.add(resultset_R.getDate("Date"));
				Froms.add(resultset_R.getInt("From"));
				Wheres.add(resultset_R.getInt("Where"));
				amounts.add(resultset_R.getDouble("Amount"));
				Comments.add(resultset_R.getString("Comment"));
			}
			
			db.close(resultset_R, preparedstatement_R);
			db.close();
		}
	
	public ArrayList<String> GetAccountName() throws Exception{
			ArrayList<String> AccountNames = new ArrayList<String>();
			db.MakeConnection();
			resultset_R = db.statement.executeQuery("SELECT * FROM financial.chart_of_account WHERE idCHART_OF_ACCOUNT >10 && (idCHART_OF_ACCOUNT%10)!=0;");
			AccountNames.size();
			
			while(resultset_R.next()){
				AccountNames.add(resultset_R.getString("NAME"));
			}
			
			db.close(resultset_R, null);
			db.close();
			return AccountNames;
		}
		
	public void close (){
		try{
			if(resultset != null)
				resultset.close();
			if(resultset_R != null)
				resultset_R.close();
			if(resultset_BOA != null)
				resultset_BOA.close();
			if(resultset_IO != null)
				resultset_IO.close();
			if(preparedstatement_BOA != null)
				preparedstatement_BOA.close();
			if(preparedstatement_IO != null)
				preparedstatement_IO.close();
		} catch (Exception e){
			
		}
	}
}
