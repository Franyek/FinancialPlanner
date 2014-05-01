package graphical_elements;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import java.awt.GridBagConstraints;


import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;

import controll.incomes_outcomesContr;

public class MainPanel extends JPanel  {
	
	public recording recordingPanel;
	public Balance balances;
	public Last_recordsPanel last_recordsP;
	public SmallPlanningPanel smallPlanningPanel;
	public JPanel costs_incomes;
	
	public incomes_outcomesContr IOCntr;

	public String Name = new String("Main");
	
	
	public MainPanel() throws Exception{
		IOCntr = new incomes_outcomesContr();
		
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		GridBagConstraints c = new GridBagConstraints();
		
		double[] in_out = IOCntr.incom_outcome_amount();
		recordingPanel = new recording();
		costs_incomes = new Costs_incomes(in_out);
		balances = new Balance();
		last_recordsP = new Last_recordsPanel();
		smallPlanningPanel = new SmallPlanningPanel();
		
		setLayout(new FormLayout(
				"default, $lcgap, default",
				"2*(default, $lgap), default"));
		
		add(recordingPanel, CC.xywh(1, 1, 3, 1));
		add(costs_incomes, CC.xy(1, 3));
		add(balances, CC.xy(3, 3));
		add(last_recordsP, CC.xy(1, 5));
		add(smallPlanningPanel, CC.xy(3, 5));
		
		}
}
