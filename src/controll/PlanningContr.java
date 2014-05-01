package controll;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

import org.scribe.builder.*;
import org.scribe.builder.api.*;
import org.scribe.model.*;
import org.scribe.oauth.*;

import DATABASE.*;

public class PlanningContr {

	private PlanningData Pdb;
	protected chart_of_accountData COAdb;
	private RecordingData Rdb;
	
	public ArrayList<Integer> IDs = new ArrayList<Integer>();
	public ArrayList<java.sql.Date> dates = new ArrayList<java.sql.Date>();
	public ArrayList<Integer> Wheres = new ArrayList<Integer>();
	public ArrayList<Double> amounts = new ArrayList<Double>();
	public ArrayList<String> Comments = new ArrayList<String>();
	
	private static final String PROTECTED_RESOURCE_URL = "https://api.twitter.com/1/statuses/update.json";
	
	public PlanningContr() throws Exception{
		Pdb = new PlanningData();
		COAdb = new chart_of_accountData();
		Rdb = new RecordingData();
	}
	
	public ArrayList<String> GetChartNames() throws Exception{
		ArrayList<String> d = new ArrayList<String>();
		
		for(int i=0; Wheres.size() > i; i++){
			d.add(COAdb.GetAccoutName(Wheres.get(i)));
		}
		
		return d;
	}
	
	//Tervezett rekord törlése
	public void DeletComingRecord(int ID) throws Exception{
		Pdb.DeletComingRecords(ID);
	}
	
	
	//tervezett tétel felvétele
	public void SetComingRecord(java.util.Date d, String Where, double Amount, String Comment) throws Exception{
		long l = d.getTime();
		Date da = new Date(l);
		Pdb.SetComingRecord(da, COAdb.GetID(Where), Amount, Comment);
	}
	
	
	public void GetPRecords() throws Exception{
		Pdb.WriteRecords(IDs, dates, Wheres, amounts, Comments);
	}

	
	public ArrayList<String> GetFundsName () throws Exception{
		return COAdb.GetFundsNames();
	}
	
	//Visszaadja a tervezett rekordokat valamettõl valmeddig.
	public void GetPRecords(Date From, Date Till) throws Exception{
		Pdb.WriteRecords(From, Till, IDs, dates, Wheres, amounts, Comments);
	}
	
	//Kifizatés következtében a tervezett Record törlõdik és Rögzítünk egy rendes rekorodot
	public void PayOut(int ID, String From, String Where, String amount, String Comment) throws Exception{
		Rdb.RecordsSet(COAdb.GetID(From), COAdb.GetID(Where), Double.parseDouble(amount), Comment);
		Pdb.DeletComingRecords(ID);
	}
	
	
	
	//Twittel, hogy ha kifizettünk egy tervezett számlát!
	public void tweet(String S){
		    OAuthService service = new ServiceBuilder()
		                                .provider(TwitterApi.class)
		                                .apiKey("6icbcAXyZx67r8uTAUM5Qw")
		                                .apiSecret("SCCAdUUc6LXxiazxH3N0QfpNUvlUy84mZ2XZKiv39s")
		                                .build();
		    Scanner in = new Scanner(System.in);

		    System.out.println("=== Twitter's OAuth Workflow ===");
		    System.out.println();

		    // Obtain the Request Token
		    System.out.println("Fetching the Request Token...");
		    Token requestToken = service.getRequestToken();
		    System.out.println("Got the Request Token!");
		    System.out.println();

		    System.out.println("Now go and authorize Scribe here:");
		    System.out.println(service.getAuthorizationUrl(requestToken));
		    System.out.println("And paste the verifier here");
		    System.out.print(">>");
		    Verifier verifier = new Verifier(in.nextLine());
		    System.out.println();

		    // Trade the Request Token and Verfier for the Access Token
		    System.out.println("Trading the Request Token for an Access Token...");
		    Token accessToken = service.getAccessToken(requestToken, verifier);
		    System.out.println("Got the Access Token!");
		    System.out.println("(if your curious it looks like this: " + accessToken + " )");
		    System.out.println();

		    // Now let's go and ask for a protected resource!
		    System.out.println("Now we're going to access a protected resource...");
		    OAuthRequest request = new OAuthRequest(Verb.POST, PROTECTED_RESOURCE_URL);
		    request.addBodyParameter("status", "Kifizettem a"+S);
		    service.signRequest(accessToken, request);
		    Response response = request.send();
		    System.out.println("Got it! Lets see what we found...");
		    System.out.println();
		    System.out.println(response.getBody());
	}
}