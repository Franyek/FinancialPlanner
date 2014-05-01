package graphical_elements;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import controll.BankOfAccountContr;
import controll.ChartOFAccountContr;

public class Balance extends JPanel {
	
	public ChartOFAccountContr COAContr;
	public BankOfAccountContr BOAContr;
	
	public ArrayList<String> NameOfAccounts;
	public ArrayList<String> AmountOfAccounts;
	
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;
	private JLabel label7;
	private JLabel label8;
	private JLabel label9;
	
	public Balance() throws Exception {
		COAContr = new ChartOFAccountContr();
		BOAContr = new BankOfAccountContr(); 
		initComponents();
	}

	private void initComponents() throws Exception {
		
		NameOfAccounts = new ArrayList<String>();
		AmountOfAccounts = new ArrayList<String>();
		
		NameOfAccounts = BOAContr.WriteNameOfAccount(); //db.WriteNameOfAccount();
		AmountOfAccounts = BOAContr.WriteAmountOfAccount(); //db.WriteAmountOfAccount();
		int size = NameOfAccounts.size();

		label1 = new JLabel();
		label2 = new JLabel();
		label3 = new JLabel();
		label4 = new JLabel();
		label5 = new JLabel();
		label6 = new JLabel();
		label7 = new JLabel();
		label8 = new JLabel();
		label9 = new JLabel();

		//---- label1 ----
		label1.setText("P\u00e9nzeszk\u00f6zeink");
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		//label1.setFont(new Font("Tahoma", Font.BOLD, 16));

		//---- label2 ----
		label2.setText("K\u00e9szp\u00e9nz");
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		label2.setFont(new Font("Tahoma", Font.BOLD, 11));

		//---- label3 ----
		
		label3 = new JLabel((AmountOfAccounts.get(0)));
		label8.setHorizontalAlignment(SwingConstants.CENTER);
		label8.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		//---- label4 ----
		label4.setText("Banksz\u00e1mla");
		label4.setHorizontalAlignment(SwingConstants.CENTER);
		label4.setFont(new Font("Tahoma", Font.BOLD, 11));

		//---- label5 ----
		
		label5 = new JLabel((AmountOfAccounts.get(1)));
		label8.setHorizontalAlignment(SwingConstants.CENTER);
		label8.setFont(new Font("Tahoma", Font.BOLD, 11));

		//---- label6 ----
		label6.setText("\u00c9tkez\u00e9si utalv\u00e1ny");
		label6.setHorizontalAlignment(SwingConstants.CENTER);
		label6.setFont(new Font("Tahoma", Font.BOLD, 11));

		//---- label7 ----
		
		label7 = new JLabel((AmountOfAccounts.get(2)));
		label8.setHorizontalAlignment(SwingConstants.CENTER);
		label8.setFont(new Font("Tahoma", Font.BOLD, 11));

		//---- label8 ----
		label8.setText("Arany");
		label8.setHorizontalAlignment(SwingConstants.CENTER);
		label8.setFont(new Font("Tahoma", Font.BOLD, 11));

		//---- label9 ----
		
		label9 = new JLabel((AmountOfAccounts.get(3)));
		label8.setHorizontalAlignment(SwingConstants.CENTER);
		label8.setFont(new Font("Tahoma", Font.BOLD, 11));

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addGap(37, 37, 37)
					.addGroup(layout.createParallelGroup()
						.addGroup(layout.createSequentialGroup()
							.addComponent(label8, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(label9, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
							.addGroup(layout.createSequentialGroup()
								.addComponent(label6, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label7, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
							.addGroup(layout.createSequentialGroup()
								.addComponent(label4, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(label5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGroup(layout.createSequentialGroup()
								.addComponent(label2, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(label3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addComponent(label1, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(28, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addGap(23, 23, 23)
					.addComponent(label1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(18, 18, 18)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(label2, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(label3, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addGap(18, 18, 18)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(label4, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(label5, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(18, 18, 18)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(label6, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(label7, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(18, 18, 18)
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(label8, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(label9, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(51, Short.MAX_VALUE))
		);
	}
}

