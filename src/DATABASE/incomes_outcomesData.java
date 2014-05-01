package DATABASE;

import java.sql.ResultSet;


//Bejöveteleket és költéseket tartalmazza (a MainPanelben lévõ chartnak kell!)
public class incomes_outcomesData {

	DATABASE db;
	private ResultSet resultset_IO;
	
	public incomes_outcomesData() throws Exception{
		db = new DATABASE();
		db.MakeConnection();
	}
	
	//A Incomes_Outcomes táblából (ami a havi bevételeket és kiadásokat tartalmazza) visszaadja az aktuális hónap bevételeinek és kiadásainak értékét.
		public double[] incom_outcome_amount() throws Exception{
			double[] amounts = {0, 0};
			db.MakeConnection();
			resultset_IO = db.statement.executeQuery("select * from FINANCIAL.incomes_outcomes WHERE Years_Months = (select max(Years_Months) from FINANCIAL.incomes_outcomes);");
			
			while(resultset_IO.next()){
				amounts[0] = resultset_IO.getDouble("Income");
				amounts[1] = resultset_IO.getDouble("Outcome");
			}
			db.close(resultset_IO, null);
			db.close();
			return amounts;
		}
}
