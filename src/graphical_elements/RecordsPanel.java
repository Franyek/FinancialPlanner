package graphical_elements;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Vector;
import java.sql.Date;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import  controll.*;


//Ez az oszt�ly olyan t�bl�kat fog l�trehozni, amiben a rekordok sz�ma lesz.
//Lesz egy konstruktor, amiben meg lehet adni, hogy mett�l meddig terjed� d�tum tartom�nyban jelenjenek meg a recodrok.
public class RecordsPanel{
	
	RecordsContr RecControll;
	ChartOFAccountContr AccControll;
	PlanningContr PlnControll; 
	
	private JTable table;
	private DefaultTableModel tableModel = new DefaultTableModel();
	public JScrollPane scrollPane1;
	private int selectedrow;
	boolean L;
	
	//Az utols� 30 nap rekordjait jelen�ti meg
	public RecordsPanel(Dimension dim) throws Exception{
			AccControll = new ChartOFAccountContr();
			RecControll = new RecordsContr();
			RecControll.GetsRecords(30);
			
			Vector<String> colNames = new Vector<String>();
			colNames.add("Mikor?");
			colNames.add("Mib�l?");
			colNames.add("Mire?");
			colNames.add("Mennyit?");
			colNames.add("Egy�b megjegyz�s?");
			tableModel = new DefaultTableModel(colNames, 0);
			
			for(int i=0; i<RecControll.dates.size(); i++){
				Object[] row ={ RecControll.dates.get(i), AccControll.GetAccountsName(RecControll.Froms.get(i)), AccControll.GetAccountsName(RecControll.Wheres.get(i)), RecControll.amounts.get(i), RecControll.Comments.get(i)};
				tableModel.addRow(row);
			}
			table = new JTable(tableModel);
			table.setPreferredSize(dim);
			//scrollPane1.setViewportView(table);
			
			scrollPane1 = new JScrollPane(table);
			scrollPane1.setPreferredSize(dim);
	}
	
	
	//Adott d�tum tartom�nyban jelen�ti meg a rekordokat
	@SuppressWarnings("deprecation")
	public RecordsPanel(Dimension dim,int Fyear, int Fmonth, int Fday, int Tyear, int Tmonth, int Tday, boolean k) throws Exception{
		L = k;
		
		Date dateFrom = new Date(Fyear, Fmonth, Fday);
		Date dateTill = new Date(Tyear, Tmonth, Tday);
		
		AccControll = new ChartOFAccountContr();
		RecControll = new RecordsContr();
		PlnControll = new PlanningContr();
		//Ha a k �rt�k igaz, akkor m�r a megt�rt�nt p�nz�gyi esem�nyeket r�gz�ti
		if(L == true){
			//RecControll.GetsRecords(dateFrom, dateTill, ID, Dates, Froms, Wheres, amounts, Comments);
			RecControll.GetsRecords(dateFrom, dateTill);
			
			//defTableModel(tableModel);
			Vector<String> colNames = new Vector<String>();
			colNames.add("ID");
			colNames.add("Mikor?");
			colNames.add("Mib�l?");
			colNames.add("Mire?");
			colNames.add("Mennyit?");
			colNames.add("Egy�b megjegyz�s?");
			tableModel = new DefaultTableModel(colNames, 0);
			
			for(int i=0; i<RecControll.dates.size(); i++){
				Object[] row ={RecControll.ID.get(i), RecControll.dates.get(i), AccControll.GetAccountsName(RecControll.Froms.get(i)), AccControll.GetAccountsName(RecControll.Wheres.get(i)), RecControll.amounts.get(i), RecControll.Comments.get(i)};
				tableModel.addRow(row);
			}
			//Ha a k �rt�ke hamis, akkor csak a tervezett p�nz mog�sokat veszi bele a t�bl�ba
		} else if (L == false){
			PlnControll.GetPRecords(dateFrom, dateTill);
			
			Vector<String> colNames = new Vector<String>();
			colNames.add("ID");
			colNames.add("Mikor?");
			colNames.add("Mire?");
			colNames.add("Mennyit?");
			colNames.add("Egy�b megjegyz�s?");
			tableModel = new DefaultTableModel(colNames, 0);
			ArrayList<String> Where = PlnControll.GetChartNames();
			
			
			for(int i=0; i<PlnControll.IDs.size(); i++){
				Object[] row ={PlnControll.IDs.get(i), PlnControll.dates.get(i),
						Where.get(i), PlnControll.amounts.get(i), PlnControll.Comments.get(i)};
				tableModel.addRow(row);
			}
			
		}
		
		
		table = new JTable(tableModel);
		table.setPreferredSize(dim);
		//scrollPane1.setViewportView(table);
		
		scrollPane1 = new JScrollPane(table);
		scrollPane1.setPreferredSize(dim);
		
	}

	private DefaultTableModel defTableModel(DefaultTableModel tableModel){
		Vector<String> colNames = new Vector<String>();
		//colNames.add("ID");
		colNames.add("Mikor?");
		colNames.add("Mib�l?");
		colNames.add("Mire?");
		colNames.add("Mennyit?");
		colNames.add("Egy�b megjegyz�s?");
		tableModel = new DefaultTableModel(colNames, 0);
		
		return tableModel;
	}
	
	public void deletrow() throws Exception{
		selectedrow = table.getSelectedRow();
		if(L == true)
			RecControll.DeleteRecords((Integer)tableModel.getValueAt(selectedrow, 0));
		else if(L == false)
			PlnControll.DeletComingRecord((Integer)tableModel.getValueAt(selectedrow, 0));
		tableModel.removeRow(selectedrow);
	}
}
