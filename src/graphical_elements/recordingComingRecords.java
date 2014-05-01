package graphical_elements;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import controll.ChartOFAccountContr;
import controll.PlanningContr;

public class recordingComingRecords  extends JDialog implements ActionListener {
	
	private JDialog this2;
	private JLabel label1;
	private JLabel label2;
	private JScrollPane scrollPane1;
	private List list1;
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
	private JRadioButton radioButton1;
	private JRadioButton radioButton2;
	private JFormattedTextField formattedTextField1;
	private JFormattedTextField formattedTextField2;
	private JComboBox comboBox1;
	private JLabel label5;
	private JLabel label7;
	private Date datek;
	private Date datev;
	private Date datech;
	private Date d;
	
	private String FromS;
	private String DateS;
	private String AmountS;
	private String CommentS;
	private Vector<String> colNames;
	
	private DefaultTableModel tableModel;
	
	private ChartOFAccountContr COAcontroll;
	private PlanningContr Pcontroll;
	
	public recordingComingRecords(Frame owner) throws Exception{
		super(owner);
		initComponents();
	}

	public recordingComingRecords(Dialog owner) throws Exception {
		super(owner);
		initComponents();
	}
	
	public recordingComingRecords() throws Exception{
		initComponents();
	}

	private void initComponents() throws Exception {
		
		label7 = new JLabel();
		this2 = new JDialog();
		label1 = new JLabel();
		label2 = new JLabel();
		scrollPane1 = new JScrollPane();
		list1 = new List();
		label3 = new JLabel();
		label4 = new JLabel();
		MaskFormatter mft = new MaskFormatter("####-##-##");
		textField1 = new JFormattedTextField();
		formattedTextField2 = new JFormattedTextField(mft);
		formattedTextField2.setColumns(8);
		scrollPane3 = new JScrollPane();
		textArea1 = new JTextArea();
		button1 = new JButton();
		scrollPane4 = new JScrollPane();
		table1 = new JTable();
		button2 = new JButton();
		button3 = new JButton();
		radioButton1 = new JRadioButton();
		radioButton2 = new JRadioButton();
		formattedTextField1 = new JFormattedTextField(mft);
		formattedTextField1.setColumns(8);
		comboBox1 = new JComboBox();
		label5 = new JLabel();

		COAcontroll = new ChartOFAccountContr(); 
		
		//======== this2 ========
		{
			this2.setTitle("Rekord r\u00f6gz\u00edt\u00e9s");
			Container this2ContentPane = this2.getContentPane();
			
			String[] choices = {"", "Hetente", "Havonta", "Évente"};
			comboBox1 = new JComboBox(choices);
			
			//---- label1 ----
			label1.setText("Mire költesz?");
			label1.setHorizontalAlignment(SwingConstants.CENTER);
			label1.setFont(new Font("Trebuchet MS", Font.BOLD, 16));

			//---- label2 ----
			label2.setText("Rendszeress\u00e9ge:");
			label2.setHorizontalAlignment(SwingConstants.CENTER);
			label2.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
	
			for (int i=0; i<COAcontroll.GetAccountsName().size(); i++){
				list1.add(COAcontroll.GetAccountsName().get(i));
			}
			
			//======== scrollPane1 ========
			{
				scrollPane1.setViewportView(list1);
			}
			
			label7.setText("Rendszeress\u00e9gi id\u0151szak v\u00e9ge:");
			label7.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
			

			//---- label3 ----
			label3.setText("\u00d6sszeg:");
			label3.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
			label3.setHorizontalAlignment(SwingConstants.CENTER);

			//---- label4 ----
			label4.setText("Megjegyz\u00e9s:");
			label4.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
			label4.setHorizontalAlignment(SwingConstants.CENTER);

			//======== scrollPane3 ========
			{
				scrollPane3.setViewportView(textArea1);
			}

			//---- button1 ----
			button1.setText("OK");
			button1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if(radioButton2.isSelected()){
						if(comboBox1.getSelectedItem().equals("Hetente")){
							SetDates(7, true);
						}else if(comboBox1.getSelectedItem().equals("Havonta")){
							SetDates(1, false);
						}else if(comboBox1.getSelectedItem().equals("Évente")){
							SetDates(12, false);
						}
							
					} else if(radioButton1.isSelected()){
						DateS = formattedTextField1.getText();
						FromS = list1.getSelectedItem();
						AmountS = textField1.getText();
						CommentS = textArea1.getText();
						Vector<String> Elements = new Vector<String>();
						Elements.add(DateS);
						Elements.add(FromS);
						Elements.add(AmountS);
						Elements.add(CommentS);
						
						tableModel.insertRow(0, Elements);
					}
				}
			});

			
			colNames = new Vector<String>();
			colNames.add("Mikor?");
			colNames.add("Mire?");
			colNames.add("Mennyit?");
			colNames.add("Komment?");
			tableModel = new DefaultTableModel(colNames, 0);
			table1 = new JTable(tableModel);
			
			
			//======== scrollPane4 ========
			{
				scrollPane4.setViewportView(table1);
			}

			//JButton button4 = new JButton();
			
			//---- button2 ----
			button2.setText("T\u00f6rl\u00e9s");
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
					try {
					for(int i=0; tableModel.getRowCount()> i; i++){
						
						String When = new String((String)tableModel.getValueAt(i, 0));
						String Where = new String((String)tableModel.getValueAt(i, 1));
						String Amount = new String((String)tableModel.getValueAt(i, 2));
						String Comment = new String((String)tableModel.getValueAt(i, 3));
						
						DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						
						d = formatter.parse(When);
						double am = Double.parseDouble(Amount);
						
						Pcontroll = new PlanningContr();
						Pcontroll.SetComingRecord(d, Where, am, Comment);
					}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					setVisible(false);
				}
			});

			//---- radioButton1 ----
			radioButton1.setText("Egyszeri");
			radioButton1.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
			radioButton1.addActionListener(this);
			radioButton1.setSelected(true);

			//---- radioButton2 ----
			radioButton2.setText("Rendszeres");
			radioButton2.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
			radioButton2.addActionListener(this);
			
			ButtonGroup group = new ButtonGroup();
			group.add(radioButton1);
			group.add(radioButton2);
			
			//---- label5 ----
			label5.setText("Esed\u00e9kess\u00e9g kezdete:");
			label5.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));

			GroupLayout this2ContentPaneLayout = new GroupLayout(this2ContentPane);
			
			this2ContentPane.setLayout(this2ContentPaneLayout);
			this2ContentPaneLayout.setHorizontalGroup(
				this2ContentPaneLayout.createParallelGroup()
					.addGroup(GroupLayout.Alignment.TRAILING, this2ContentPaneLayout.createSequentialGroup()
						.addGroup(this2ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
							.addGroup(this2ContentPaneLayout.createSequentialGroup()
								.addGroup(this2ContentPaneLayout.createParallelGroup()
									.addGroup(this2ContentPaneLayout.createSequentialGroup()
										.addContainerGap()
										.addGroup(this2ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
											.addComponent(label4, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(label3, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)))
									.addGroup(this2ContentPaneLayout.createSequentialGroup()
										.addGap(45, 45, 45)
										.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(this2ContentPaneLayout.createParallelGroup()
									.addGroup(GroupLayout.Alignment.TRAILING, this2ContentPaneLayout.createSequentialGroup()
										.addComponent(formattedTextField2, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
										.addGap(28, 28, 28))
									.addComponent(label7, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
									.addComponent(label5, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
									.addGroup(GroupLayout.Alignment.TRAILING, this2ContentPaneLayout.createSequentialGroup()
										.addComponent(formattedTextField1, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
										.addGap(28, 28, 28))
									.addGroup(GroupLayout.Alignment.TRAILING, this2ContentPaneLayout.createSequentialGroup()
										.addGroup(this2ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
											.addComponent(comboBox1, 0, 172, Short.MAX_VALUE)
											.addComponent(radioButton2, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
											.addComponent(button1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
											.addComponent(scrollPane3, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
											.addComponent(textField1, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
											.addComponent(radioButton1, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))
										.addGap(28, 28, 28))))
							.addGroup(this2ContentPaneLayout.createSequentialGroup()
								.addContainerGap()
								.addGroup(this2ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
									.addGroup(GroupLayout.Alignment.LEADING, this2ContentPaneLayout.createSequentialGroup()
										.addComponent(label1, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
										.addGap(5, 5, 5)
										.addComponent(label2, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE))
									.addGroup(this2ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
										.addComponent(scrollPane4, GroupLayout.PREFERRED_SIZE, 415, GroupLayout.PREFERRED_SIZE)
										.addGroup(GroupLayout.Alignment.LEADING, this2ContentPaneLayout.createSequentialGroup()
											.addComponent(button2, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(button3, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE))))))
						.addGap(23, 23, 23))
			);
			this2ContentPaneLayout.setVerticalGroup(
				this2ContentPaneLayout.createParallelGroup()
					.addGroup(this2ContentPaneLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(this2ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(label1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addComponent(label2, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addGap(7, 7, 7)
						.addGroup(this2ContentPaneLayout.createParallelGroup()
							.addGroup(this2ContentPaneLayout.createSequentialGroup()
								.addComponent(radioButton1, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(radioButton2, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(label5)
								.addGap(1, 1, 1)
								.addComponent(formattedTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(label7)
								.addGap(1, 1, 1)
								.addComponent(formattedTextField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
						.addGap(29, 29, 29)
						.addGroup(this2ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(label3, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
							.addComponent(textField1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(this2ContentPaneLayout.createParallelGroup()
							.addComponent(scrollPane3, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
							.addComponent(label4, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addComponent(button1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addComponent(scrollPane4, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addGroup(this2ContentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(button2, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addComponent(button3, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(20, Short.MAX_VALUE))
			);
			
			this2.pack();
			this2.setLocationRelativeTo(this2.getOwner());
			
			this2.setLocationRelativeTo(null);
			this2.setVisible(true);
		}
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.equals(radioButton1)){
			//radioButton1.setSelected(true);
			//radioButton2.setSelected(false);
		} else if(e.equals(radioButton2)){
			//radioButton2.setSelected(true);
			//radioButton1.setSelected(false);
		}
	}
	
	public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, + days); //minus number would decrement the days
        return cal.getTime();
    }
	
	public static Date addMonths(Date date, int months)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, + months); //minus number would decrement the days
        return cal.getTime();
    }
	
	public void SetDates(int days, boolean b){
		DateFormat formatter;
		formatter = new SimpleDateFormat("yyyy-MM-dd");
			try {
				datek = formatter.parse(formattedTextField1.getText());
				datev = formatter.parse(formattedTextField2.getText());

				datech= datek;
				if(b){
					while(datech.before(datev)){
						FromS = list1.getSelectedItem();
						AmountS = textField1.getText();
						CommentS = textArea1.getText();
						Vector<String> Elements = new Vector<String>();
						Elements.add(formatter.format(datech));
						Elements.add(FromS);
						Elements.add(AmountS);
						Elements.add(CommentS);
						tableModel.insertRow(0, Elements);
						
						datech = addDays(datech, days);
					}
				} else{
					while(datech.before(datev)){
						FromS = list1.getSelectedItem();
						AmountS = textField1.getText();
						CommentS = textArea1.getText();
						Vector<String> Elements = new Vector<String>();
						Elements.add(formatter.format(datech));
						Elements.add(FromS);
						Elements.add(AmountS);
						Elements.add(CommentS);
						tableModel.insertRow(0, Elements);
						
						datech = addMonths(datech, days);
					}
				}
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
}
