package graphical_elements;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Vector;
import java.sql.Date;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import  controll.*;

public class PlanRecordsPanel {
		
		PlanningContr PlanningContr;
		ChartOFAccountContr AccControll; 
		
		private JTable table;
		private DefaultTableModel tableModel = new DefaultTableModel();
		public JScrollPane scrollPane1;
		private int selectedrow;
		
		//Az utolsó 30 nap rekordjait jeleníti meg
		public PlanRecordsPanel() throws Exception{
			AccControll = new ChartOFAccountContr();
			PlanningContr = new PlanningContr();
			PlanningContr.GetPRecords();
			
			Vector<String> colNames = new Vector<String>();
			colNames.add("ID");
			colNames.add("Mikor?");
			colNames.add("Mire?");
			colNames.add("Mennyit?");
			colNames.add("Egyéb megjegyzés?");
			tableModel = new DefaultTableModel(colNames, 0);
			
			for(int i=0; i<PlanningContr.dates.size(); i++){
				Object[] row ={ PlanningContr.IDs.get(i), PlanningContr.dates.get(i), AccControll.GetAccountsName(PlanningContr.Wheres.get(i)), PlanningContr.amounts.get(i), PlanningContr.Comments.get(i)};
				tableModel.addRow(row);
			}
			table = new JTable(tableModel);

			scrollPane1 = new JScrollPane(table);
		}
		
		public boolean IsRowSelected(){
			if(table.getSelectedRow()>=0){
				return true;
			}else{
				return false;
			}
		}
		
		
		//Adott dátum tartományban jeleníti meg a rekordokat
		@SuppressWarnings("deprecation")
		public PlanRecordsPanel(Dimension dim,int Fyear, int Fmonth, int Fday, int Tyear, int Tmonth, int Tday) throws Exception{
			Date dateFrom = new Date(Fyear, Fmonth, Fday);
			Date dateTill = new Date(Tyear, Tmonth, Tday);
			
			AccControll = new ChartOFAccountContr();
			PlanningContr = new PlanningContr();
			PlanningContr.GetPRecords(dateFrom, dateTill);
			
			Vector<String> colNames = new Vector<String>();
			colNames.add("ID");
			colNames.add("Mikor?");
			colNames.add("Mibõl?");
			colNames.add("Mire?");
			colNames.add("Mennyit?");
			colNames.add("Egyéb megjegyzés?");
			tableModel = new DefaultTableModel(colNames, 0);
			
			for(int i=0; i<PlanningContr.dates.size(); i++){
				Object[] row ={PlanningContr.IDs.get(i), PlanningContr.dates.get(i), AccControll.GetAccountsName(PlanningContr.Wheres.get(i)), PlanningContr.amounts.get(i), PlanningContr.Comments.get(i)};
				tableModel.addRow(row);
			}
			table = new JTable(tableModel);
			table.setPreferredSize(dim);
			
			scrollPane1 = new JScrollPane(table);
			scrollPane1.setPreferredSize(dim);
			
		}

		private DefaultTableModel defTableModel(DefaultTableModel tableModel){
			
			Vector<String> colNames = new Vector<String>();
			colNames.add("ID");
			colNames.add("Mikor?");
			colNames.add("Mire?");
			colNames.add("Mennyit?");
			colNames.add("Egyéb megjegyzés?");
			tableModel = new DefaultTableModel(colNames, 0);
			
			return tableModel;
		}
		
		public void deletrow() throws Exception{
			selectedrow = table.getSelectedRow();
			PlanningContr.DeletComingRecord((Integer)tableModel.getValueAt(selectedrow, 0));
			tableModel.removeRow(selectedrow);
		}
		
		public Object GetSelectedItem(int i){
			selectedrow = table.getSelectedRow();
			return table.getModel().getValueAt(selectedrow, i);
		}
}
