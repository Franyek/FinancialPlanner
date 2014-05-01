package DATABASE;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;




public class DATABASE {
	
	protected Connection connection;
	protected Statement statement;
	protected ResultSet resultset;
	protected ResultSet resultsetIO;
	protected PreparedStatement preparedStatement;
	
	public SimpleDateFormat dateformat;
	
	public DATABASE() throws Exception{
		dateformat= new SimpleDateFormat("yyyy-mm-dd");
	}
	
	public void readDATABASE() throws Exception {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/financial?" + "user=root&password=lutuNai6");
			statement = connection.createStatement();
		} catch(Exception e) {
			throw e;
		} finally {
			close(); 
		}
	}

	
	//Kapcsolat létrehozása a JAVA kód és az adatbázis között!
	public void MakeConnection() throws Exception{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost/financial?" + "user=root&password=lutuNai6");
			statement = connection.createStatement();
			
		} catch(Exception e){
			throw e;
		}
	}
	
	
	//Számlatükör számláinak a nevét adja vissza
	/*public ArrayList<String> GetAccountName() throws Exception{
		ArrayList<String> AccountNames = new ArrayList<String>();
		MakeConnection();
		resultset = statement.executeQuery("SELECT * FROM financial.chart_of_account WHERE idCHART_OF_ACCOUNT >10 && (idCHART_OF_ACCOUNT%10)!=0;");
		AccountNames.size();
		
		while(resultset.next()){
			AccountNames.add(resultset.getString("NAME"));
		}
		return AccountNames;
	}
	
	
	//Visszaadja a legutóbbi x napban felvett rekordot, csak az a különbség a következõhöz képest, hogy az ID is szerepel benne.
	public void WriteRecords(int d, ArrayList<Integer> IDs, ArrayList<java.sql.Date> dates, ArrayList<Integer> Froms,
			ArrayList<Integer> Wheres, ArrayList<Double> amounts, ArrayList<String> Comments) throws Exception{
		MakeConnection();
		resultset = statement.executeQuery("SELECT * FROM financial.RECORDS WHERE DATE_SUB(CURDATE() ,INTERVAL "+ d+ " DAY) <= Date;");
		
		while(resultset.next()){
			IDs.add(resultset.getInt("idRecords"));
			dates.add(resultset.getDate("Date"));
			Froms.add(resultset.getInt("From"));
			Wheres.add(resultset.getInt("Where"));
			amounts.add(resultset.getDouble("Amount"));
			Comments.add(resultset.getString("Comment"));
		}
		
		close();
	}

	
	public ArrayList<String> WriteNameOfAccount() throws Exception{
		ArrayList<String> names = new ArrayList<String>();
		MakeConnection();
		resultset = statement.executeQuery("select * from FINANCIAL.bank_of_accounts");
		
		while(resultset.next()){
			names.add(resultset.getString("NAMES"));
		}
		
		close();
		return names;
	}
	
	public ArrayList<String> WriteAmountOfAccount() throws Exception{
		ArrayList<String> amounts = new ArrayList<String>();
		MakeConnection();
		resultset = statement.executeQuery("select * from FINANCIAL.bank_of_accounts");
		
		while(resultset.next()){
			amounts.add(resultset.getString("AMOUNT"));
		}
		
		close();
		return amounts;
	}*/

	
	
	//Lezárja az adatbázist!
	public void close (){
		try{
			if(resultset != null)
				resultset.close();
			if(statement != null)
				statement.close();
			if(connection != null)
				connection.close();
		} catch (Exception e){
			
		}
		
	}
	
	public void close(ResultSet rs, PreparedStatement ps){
		try{
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
			if(connection != null)
				connection.close();
		} catch (Exception e){
			
		}
	}
}
