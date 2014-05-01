package DATABASE;

import java.sql.ResultSet;


//Bej�veteleket �s k�lt�seket tartalmazza (a MainPanelben l�v� chartnak kell!)
public class incomes_outcomesData {

	DATABASE db;
	private ResultSet resultset_IO;
	
	public incomes_outcomesData() throws Exception{
		db = new DATABASE();
		db.MakeConnection();
	}
	
	//A Incomes_Outcomes t�bl�b�l (ami a havi bev�teleket �s kiad�sokat tartalmazza) visszaadja az aktu�lis h�nap bev�teleinek �s kiad�sainak �rt�k�t.
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
