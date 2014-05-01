package graphical_elements;
import info.clearthought.layout.TableLayout;
import info.clearthought.layout.TableLayoutConstraints;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JLabel;

import controll.*;

public class BalancePanel extends JPanel {

	public String name = new String("Balance");
	public BalanceControll controll;
	public JPanel names;
	public JPanel title;
	public JPanel amounts;
	public JPanel month;
	
	
	public BalancePanel() throws Exception{
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		controll = new BalanceControll();
		controll.GetNames();
		controll.GetAmounts();
		
		names = new JPanel();
		Names(names);
		
		title = new JPanel();
		Title(title);
		amounts = new JPanel();
		month = new JPanel();
		Month(month);
		
		Amount(amounts);
		
		JPanel szamok = new JPanel();
		szamok.add(names);
		szamok.add(amounts);
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		add(title);
		add(month);
		add(szamok);
	}
	//Kiírja a hónap választót
	public void Month(JPanel p){
		String[] lb ={"2012-03"}; 
		JComboBox comboBox3 = new JComboBox(lb);
		JLabel month = new JLabel("Válasszon hónapot");
		p.add(month);
		p.add(comboBox3);
	}
	
	
	//Kiírja a sorok értékeit
	public void Names (JPanel p){
		p.setLayout(new TableLayout(new double[][] {
				{TableLayout.PREFERRED, TableLayout.PREFERRED},
				{TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED,TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED}}));
			((TableLayout)p.getLayout()).setHGap(5);
			((TableLayout)p.getLayout()).setVGap(5);

			for(int i = 0; controll.names.size()>i; i++ ){
				if(controll.main.get(i).equals(true))
					p.add(new JLabel(controll.names.get(i)), new TableLayoutConstraints(0, i, 0, i, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
				else
					p.add(new JLabel(controll.names.get(i)), new TableLayoutConstraints(1, i, 1, i, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
			}

			JLabel end = new JLabel("Havi egyenleg");
			end.setFont(new Font("Tahoma", Font.BOLD, 17));
			end.setForeground(new Color(40, 106, 255));
			p.add(end, new TableLayoutConstraints(0, controll.names.size()+1, 0, controll.names.size()+1, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
	}
	
	//Kiírja a Panel nevét
	public void Title (JPanel p){
		JLabel month = new JLabel("Válasszon hónapot");
		JLabel cim = new JLabel("Eredménykimutatás");
		String[] lb ={"2012-03"}; 
		JComboBox comboBox3 = new JComboBox(lb);
		cim.setFont(new Font("Tahoma", Font.BOLD, 22));
		p.add(cim);
	}
	
	//Kiírja az értékeket
	public void Amount (JPanel p){
		p.setLayout(new TableLayout(new double[][] {
				{TableLayout.PREFERRED, TableLayout.PREFERRED},
				{TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED,TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED, TableLayout.PREFERRED}}));
			((TableLayout)p.getLayout()).setHGap(5);
			((TableLayout)p.getLayout()).setVGap(5);

			for(int i = 0; controll.amounts.size()>i; i++ ){
				String number = controll.amounts.get(i).toString();
				p.add(new JLabel("                                                                                                             "), new TableLayoutConstraints(0, i, 0, i, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
				p.add(new JLabel(number), new TableLayoutConstraints(1, i, 1, i, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
			}
			JLabel end = new JLabel("43000");
			end.setFont(new Font("Tahoma", Font.BOLD, 17));
			end.setForeground(new Color(40, 106, 255));

			p.add(new JLabel("2000"), new TableLayoutConstraints(1, 14, 1, 14, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));
			
			p.add(end, new TableLayoutConstraints(1, controll.names.size()+1, 1, controll.names.size()+1, TableLayoutConstraints.FULL, TableLayoutConstraints.FULL));

	}
}