package controll;

import DATABASE.incomes_outcomesData;

public class incomes_outcomesContr {
	
	private incomes_outcomesData IO_Data;
	
	public incomes_outcomesContr() throws Exception{
		IO_Data = new incomes_outcomesData();
	}
	
	public double[] incom_outcome_amount() throws Exception{
		return IO_Data.incom_outcome_amount();
	}

}
