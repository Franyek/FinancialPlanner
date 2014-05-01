package DATABASE;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PlanningData {

	protected DATABASE db;
	private RecordingData Rdb;
	
	private ResultSet resultset_Plan;
	private PreparedStatement preparedstatement_Plan;
	
	public PlanningData() throws Exception{
		db = new DATABASE();
		Rdb = new RecordingData();
	}
	
	
	//Visszaadja a következõ 30 napban esedékes recordokat!
	public void WriteRecords(ArrayList<Integer> IDs,ArrayList<java.sql.Date> dates, ArrayList<Integer> Wheres,
			ArrayList<Double> amounts, ArrayList<String> Comments) throws Exception{
		db.MakeConnection();
		resultset_Plan = db.statement.executeQuery("SELECT * FROM financial.coming_records WHERE DATE_ADD(CURDATE() ,INTERVAL 30 DAY) >= DATE;");
		
		while(resultset_Plan.next()){
			IDs.add(resultset_Plan.getInt("idcoming_records"));
			dates.add(resultset_Plan.getDate("Date"));
			Wheres.add(resultset_Plan.getInt("Where"));
			amounts.add(resultset_Plan.getDouble("Amount"));
			Comments.add(resultset_Plan.getString("Comment"));
		}
		
		db.close(resultset_Plan, preparedstatement_Plan);
		db.close();
	}
	
	public void WriteRecords(Date From, Date Till, ArrayList<Integer> ID, ArrayList<java.sql.Date> dates,
			ArrayList<Integer> Wheres, ArrayList<Double> amounts, ArrayList<String> Comments) throws Exception{
		db.MakeConnection();
		
		resultset_Plan = db.statement.executeQuery("SELECT * FROM financial.coming_records WHERE Date >= '"+ From +"' AND Date <= '"+Till+"';");
		
		while(resultset_Plan.next()){
			ID.add(resultset_Plan.getInt("idcoming_records"));
			dates.add(resultset_Plan.getDate("Date"));
			Wheres.add(resultset_Plan.getInt("Where"));
			amounts.add(resultset_Plan.getDouble("Amount"));
			Comments.add(resultset_Plan.getString("Comment"));
		}
		
		db.close(resultset_Plan, preparedstatement_Plan);
		db.close();
	}
	
	
	//Hamarosan bekövetkezõ tételt rögzít
	public void SetComingRecord(Date d, int Where, double Amount, String Comment) throws Exception{
		db.MakeConnection();
		resultset_Plan = db.statement.executeQuery("SELECT * FROM FINANCIAL.coming_records");
		resultset_Plan.last();
		int ID = resultset_Plan.getInt("idcoming_records");
		
		preparedstatement_Plan = db.connection.prepareStatement("INSERT INTO financial.coming_records (Date, `Where`, Amount, Comment) " +
				"VALUES ('"+d+"', "+Where+", "+Amount+", '"+Comment+"');");
		preparedstatement_Plan.executeUpdate();
		
		db.close(resultset_Plan, preparedstatement_Plan);
		db.close();
	}
	
	//Rekord befizetés
	public void PaidRecord(int PaidRecordID, int From, int Where, double amount, String comment) throws Exception{
		Rdb.RecordsSet(From, Where, amount, comment);
		DeletComingRecords(PaidRecordID);
	}
	
	//tervezett rekord törlése a táblázatból
	public void DeletComingRecords(int PaidRecordID) throws Exception{
		db.MakeConnection();
			
		preparedstatement_Plan = db.connection.prepareStatement("DELETE FROM financial.coming_records WHERE idcoming_records="+PaidRecordID+";");
		preparedstatement_Plan.executeUpdate();
		
		db.close(resultset_Plan, preparedstatement_Plan);
		db.close();
	}
	
	
}
