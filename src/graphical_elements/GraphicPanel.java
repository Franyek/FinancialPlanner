package graphical_elements;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.sql.Date;


import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;

import controll.GraphicControll;


public class GraphicPanel extends JPanel implements ActionListener{
	
	public GraphicControll gc;
	public GraphicPanelPopup GPOP;
	
	private PieChart pc;
	private BarChart bc;
	private LineChart lc;
	private JPanel choose; 
	
	private JLabel label1;
	private JComboBox comboBoxADAT;
	private JLabel label2;
	private JComboBox comboBoxDIAGRAMS;
	private JButton button1;
	private String choosed;
	private String NameOfChart;
	
	public GraphicPanel() throws Exception{

		Date From = new Date(112,00,01);
		Date Till = new Date(112,06,01);
		gc = new GraphicControll("funds", From, Till);
		
		initComponents();
		
		pc = new PieChart(gc.a, gc.n, "Jelenlegi pénzeszközeink");

		add(choose);
		add(pc);
	}
	
	//Meg kell oldani, hogy a Panelek adatai a Chart classokban töltödjenek fel a DATABASEen keresztül
	//Gomb Action Listenere, meg kell változtatnia a képet, amit látunk
	private void button1ActionPerformed(ActionEvent e) {
		if(e.getSource().equals(button1)){
			String ch = (String) comboBoxDIAGRAMS.getSelectedItem();
			
			if(ch.equals("Oszlop diagram")){
				
				Component[] comp = {};
				comp = getComponents();
				remove(comp[1]);
				add(bc);
				revalidate();
			} else if(ch.equals("Kör diagram")){
				Component[] comp = {};
				comp = getComponents();
				remove(comp[1]);
				add(pc);
				revalidate();
			} else if(ch.equals("Vonal diagram")){
				Component[] comp = {};
				comp = getComponents();
				remove(comp[1]);
				add(lc);
				revalidate();
			}
			comboBoxADAT.setSelectedIndex(0);
			comboBoxDIAGRAMS.setSelectedIndex(0);
		}
	}

	private JPanel initComponents() {
		choose = new JPanel();
		String[] charts_names = {"","Kör diagram", "Oszlop diagram", "Vonal diagram"};
		String[] TypeOFDatas = {"", "Adott idõszak költségei", "Adott idõszak forrásai", "Jelenlegi pénzeszközeink"};
		
		label1 = new JLabel();
		comboBoxADAT= new JComboBox(TypeOFDatas);
		comboBoxADAT.addActionListener(this);
		label2 = new JLabel();
		comboBoxDIAGRAMS = new JComboBox(charts_names);
		comboBoxDIAGRAMS.addActionListener(this);
		button1 = new JButton();

		//======== this ========
		choose.setLayout(new FormLayout(
			"default",
			"4*(default, $lgap), default"));

		//---- label1 ----
		label1.setText("Milyen értékekrõl szeretne diagrammot?");
		choose.add(label1, CC.xy(1, 1));
		choose.add(comboBoxADAT, CC.xy(1, 3));

		//---- label2 ----
		label2.setText("Milyen diagrammot szeretne?");
		choose.add(label2, CC.xy(1, 5));
		choose.add(comboBoxDIAGRAMS, CC.xy(1, 7));

		//---- button1 ----
		button1.setText("Mutasd");
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button1ActionPerformed(e);
			}
		});
		choose.add(button1, CC.xy(1, 9, CC.CENTER, CC.DEFAULT));
		
		return choose;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(comboBoxADAT)){
			if(comboBoxADAT.getSelectedItem().equals("Adott idõszak költségei")){
				try {
					GPOP = new GraphicPanelPopup("costs");
					GPOP.setVisible(true);
					choosed = new String("costs");
					NameOfChart = new String("Adott idõszak költségei");
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(comboBoxADAT.getSelectedItem().equals("Adott idõszak forrásai")){
					NameOfChart = new String("Adott idõszak forrásai");
					choosed = new String("sources");
					try {
						GPOP = new GraphicPanelPopup("sources");
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					GPOP.setVisible(true);
			}
			else if(comboBoxADAT.getSelectedItem().equals("Jelenlegi pénzeszközeink")){
				NameOfChart = new String("Jelenlegi pénzeszközeink");
				choosed = new String("funds");
				try {
					gc = new GraphicControll(choosed);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}	
		}
		
		if(e.getSource().equals(comboBoxDIAGRAMS)){
			if(comboBoxDIAGRAMS.getSelectedItem().equals("Kör diagram")){
				if(choosed.equals("sources") || choosed.equals("costs")){
					pc = new PieChart(GPOP.gc.a, GPOP.gc.n, NameOfChart);
				}else if (choosed.equals("funds")){
					pc = new PieChart(gc.a, gc.n, NameOfChart);
				}
			}
			else if(comboBoxDIAGRAMS.getSelectedItem().equals("Oszlop diagram")){
				if(choosed.equals("sources") || choosed.equals("costs")){
					bc = new BarChart(GPOP.gc.a, GPOP.gc.n, NameOfChart);
				}else if (choosed.equals("funds")){
					bc = new BarChart(gc.a, gc.n, NameOfChart);
				}
			}
			else if(comboBoxDIAGRAMS.getSelectedItem().equals("Vonal diagram")){
				if(choosed.equals("sources") || choosed.equals("costs")){
					lc = new LineChart(GPOP.gc.a, GPOP.gc.n, NameOfChart);
				}else if (choosed.equals("funds")){
					lc = new LineChart(gc.a, gc.n, NameOfChart);
				}
			}
		}
		
	}
}