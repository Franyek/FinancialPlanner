package graphical_elements;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JDialog;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import javax.swing.JTable;
import javax.swing.table.*;
import java.util.Vector;

import controll.ChartOFAccountContr;
import controll.RecordsContr;


public class recording_popoup extends JDialog implements ItemListener {

	private JLabel label1;
	private JLabel label2;
	private JScrollPane scrollPane1;
	private List list1;
	private JScrollPane scrollPane2;
	private List list2;
	private JLabel label3;
	private JLabel label4;
	private JTextField textField1;
	private JScrollPane scrollPane3;
	private JTextArea textArea1;
	private JButton button1;
	private JScrollPane scrollPane4;
	private JTable table1;
	private JButton button2;
	private JButton button3;
	
	private Vector<String> colNames;
	private DefaultTableModel tableModel;
	private String FromS;
	private String WhereS;
	private String amountS;
	private String commentS;
	private RecordsContr reccontroll;
	private ChartOFAccountContr COAcontroll;
	
	
	
	public recording_popoup() throws Exception{
		FromS = new String();
		WhereS= new String();
		amountS = new String();
		commentS = new String();
		COAcontroll = new ChartOFAccountContr();
		
		label1 = new JLabel();
		label2 = new JLabel();
		scrollPane1 = new JScrollPane();
		list1 = new List();
		scrollPane2 = new JScrollPane();
		list2 = new List();
		label3 = new JLabel();
		label4 = new JLabel();
		textField1 = new JTextField();
		scrollPane3 = new JScrollPane();
		textArea1 = new JTextArea();
		button1 = new JButton();
		scrollPane4 = new JScrollPane();
		table1 = new JTable();
		button2 = new JButton();
		button3 = new JButton();

		//======== this ========
		setTitle("Rekord rögzítés");
		Container contentPane = getContentPane();

		//---- label1 ----
		label1.setText("Honnan?");
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setFont(new Font("Trebuchet MS", Font.BOLD, 16));

		//---- label2 ----
		label2.setText("Hova vándorol a pénzed?");
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		label2.setFont(new Font("Trebuchet MS", Font.BOLD, 16));

		//======== scrollPane1 ========
		{
			scrollPane1.setViewportView(list1);
		}

		//======== scrollPane2 ========
		{
			scrollPane2.setViewportView(list2);
		}
		
		for (int i=0; i<COAcontroll.GetAccountsName().size(); i++){
			list1.add(COAcontroll.GetAccountsName().get(i));
			list2.add(COAcontroll.GetAccountsName().get(i));
		}

		//---- label3 ----
		label3.setText("Összeg:");
		label3.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		label3.setHorizontalAlignment(SwingConstants.CENTER);

		//---- label4 ----
		label4.setText("Megjegyzés:");
		label4.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		label4.setHorizontalAlignment(SwingConstants.CENTER);

		//======== scrollPane3 ========
		{
			scrollPane3.setViewportView(textArea1);
		}

		//---- button1 ----
		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				FromS = list1.getSelectedItem();
				WhereS = list2.getSelectedItem();
				amountS = textField1.getText();
				commentS = textArea1.getText();
				Vector<String> Elements = new Vector<String>();
				Elements.add(FromS);
				Elements.add(WhereS);
				Elements.add(amountS);
				Elements.add(commentS);
				
				tableModel.insertRow(0, Elements);
			}
		});
		button1.setText("OK");

		//======== scrollPane4 ========
		colNames = new Vector<String>();
		colNames.add("Mibõl?");
		colNames.add("Mire?");
		colNames.add("Mennyit?");
		colNames.add("Komment?");
		tableModel = new DefaultTableModel(colNames, 0);
		table1 = new JTable(tableModel);
		
		{
			scrollPane4.setViewportView(table1);
		}

		//---- button2 ----
		button2.setText("Törlés");
		button2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int srow = table1.getSelectedRow();
				tableModel.removeRow(srow);
			}
		});

		//---- button3 ----
		button3.setText("Mentés és Kilépés");
		button3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				for(int i=0; tableModel.getRowCount()> i; i++){
					String c = new String((String)tableModel.getValueAt(i, 0));
					String b = new String((String)tableModel.getValueAt(i, 1));
					String a = new String((String)tableModel.getValueAt(i, 2));
					String d = new String((String)tableModel.getValueAt(i, 3));
					
					double am = Double.parseDouble(a);
					
					try {
						reccontroll = new RecordsContr();
						reccontroll.SetRecord(c, b, am, d);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				setVisible(false);
			}
		});

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
					.addGap(10, 10, 10)
					.addComponent(label1, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
					.addGap(29, 29, 29)
					.addComponent(label2, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
					.addGap(10, 10, 10))
				.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
					.addGroup(contentPaneLayout.createParallelGroup()
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
								.addComponent(label4, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label3, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)))
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addGap(51, 51, 51)
							.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)))
					.addGap(43, 43, 43)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addComponent(button1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
						.addComponent(textField1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
						.addComponent(scrollPane3, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
						.addComponent(scrollPane2, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
					.addGap(51, 51, 51))
				.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addContainerGap(21, Short.MAX_VALUE)
							.addComponent(scrollPane4, GroupLayout.PREFERRED_SIZE, 415, GroupLayout.PREFERRED_SIZE))
						.addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
							.addGap(66, 66, 66)
							.addComponent(button2, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
							.addGap(132, 132, 132)
							.addComponent(button3, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)))
					.addGap(23, 23, 23))
		);
		contentPaneLayout.setVerticalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addGap(23, 23, 23)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(label1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(label2, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addGroup(contentPaneLayout.createParallelGroup()
						.addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
					.addGap(18, 18, 18)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(label3, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(18, 18, 18)
					.addGroup(contentPaneLayout.createParallelGroup()
						.addComponent(scrollPane3, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
						.addComponent(label4, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addGap(18, 18, 18)
					.addComponent(button1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(18, 18, 18)
					.addComponent(scrollPane4, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
					.addGap(18, 18, 18)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(button3, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(button2, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		pack();
		setLocationRelativeTo(getOwner());
		
		setLocationRelativeTo(null);
		setVisible(true);

	}
	
	public void itemStateChanged( ItemEvent e ){
		FromS = list1.getSelectedItem();
		WhereS = list2.getSelectedItem();
		
		System.out.println("Honnan: "+ FromS +"Hova: "+WhereS);
	}	
}