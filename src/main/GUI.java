package main;
import java.awt.*;
import java.awt.event.*;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.*;


public class GUI extends JFrame implements ActionListener{
	
	private JMenuBar menuBar;
	private JButton Main;
	private JButton Balance;
	private JButton Graphic;
	private JButton Planing;
	private JPanel MainPanel;
	private JPanel BalancePanel;
	

	//Az elsõ próbálkozásom, amiben benne van azaz SQL kódos rész is, amire még szükségem lesz
	/*public GUI(){
		super("Financial planner");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		
		JPanel pan1 = new JPanel();
		JPanel pan2 = new JPanel();
		JPanel pan3 = new JPanel();
		JPanel pan4 = new JPanel();
		pan4.setLayout(new BorderLayout());
		pan4.add(new Button("Oldal Menuk"), BorderLayout.NORTH);
		pan4.add(new Button("Center"), BorderLayout.CENTER);
		pan4.add(new Button("Fájl Menük"), BorderLayout.LINE_START);
		//pan4.add(new Button("Center"), BorderLayout.WEST);
		//pan4.add(new Button("Center"), BorderLayout.NORTH);
		//pan4.add(new Button("Center"), BorderLayout.WEST);
		
		JLabel lab1 = new JLabel("First Tab!");
		JLabel lab2 = new JLabel("Third Tab!");
		
		TableModel dataModel = new AbstractTableModel() {
			public int getColumnCount() { return 6; }
			public int getRowCount() {return 30;}
			public Object getValueAt (int row, int col) {
				
				DATABASE sb = new DATABASE();
				String Alma = null;
				try {
					Alma = sb.GetRecord(row+1, col+1);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return Alma;
				}
		};
		
		JTable table01 = new JTable(dataModel);
		JScrollPane scrollpane = new JScrollPane(table01);
		
		lab1.setLayout(new BoxLayout(lab1, BoxLayout.Y_AXIS));
		lab2.setLayout(new BoxLayout(lab2, BoxLayout.Y_AXIS));
		
		lab1.setAlignmentX(Component.CENTER_ALIGNMENT);
		lab2.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		pan1.add(lab1);
		pan2.add(scrollpane);
		pan3.add(lab2);
		
		JTabbedPane pane = new JTabbedPane();
		
		pane.addTab("Main", pan1);
		pane.addTab("Records", pan2);
		pane.addTab("Financial Planing", pan3);
		pane.addTab("Rendezés", pan4);
		
		add(pane);
		
		setSize(600, 600);
		setVisible(true);
	}*/
	
	public GUI(){
		super("Financial Planer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		JPanel centerPanel = new JPanel();
		JPanel line_startPanel = new JPanel();
		
		line_startPanel.setLayout(new GridLayout(7, 1));
		
		
		centerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		line_startPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		centerPanel.add(new JLabel("Center"));
		
		Main = new JButton("Main");
		Balance = new JButton("Balance");
		Graphic = new JButton("Graphic");
		Planing = new JButton("Planning");
		
		line_startPanel.add(Main);
		line_startPanel.add(Balance);
		line_startPanel.add(Graphic);
		line_startPanel.add(Planing);
		
		add(centerPanel, BorderLayout.CENTER);
		add(line_startPanel, BorderLayout.WEST);
		
		//Menubár megalkotása és hozzáadása a JFramehez
		menuBar = new JMenuBar();
		setmenubar(menuBar);
		setJMenuBar(menuBar);
		
		//Ablak méretezése, láthatóvá tétele
		setSize(800, 600);
		setVisible(true);
		
		Main.addActionListener(this);
		Balance.addActionListener(this);
		Graphic.addActionListener(this);
		Planing.addActionListener(this);
	}
	
	
	public void setmenubar (JMenuBar menubar){
		
		//MenuBar fõ menupontjai
		JMenu FileMenu = new JMenu("File");
		JMenu EditMenu = new JMenu("Edit");
		
		//Fõ menupontok alpontjainak megalkotása
		JMenuItem newAction = new JMenuItem("New");
		JMenuItem openAction = new JMenuItem("Open");
		JMenuItem saveAction = new JMenuItem("Save");
		JMenuItem CopyAction = new JMenuItem("Copy");
		JMenuItem PasteAction = new JMenuItem("Paste");
		
		//Az alpontok hozzáadása a fõmenuhöz
		FileMenu.add(newAction);
		FileMenu.add(openAction);
		FileMenu.add(saveAction);
		EditMenu.add(CopyAction);
		EditMenu.add(PasteAction);
	
		//Fõpontok hozzáadása a Menubarhoz
		menubar.add(FileMenu);
		menubar.add(EditMenu);
	}
	
	public void setbutton(String name){

		
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(Main)){
			System.out.println("Main");
		}else if(e.getSource().equals(Balance)){
			JPanel BalancePanel = new JPanel();
		}else if(e.getSource().equals(Graphic)){
			CenterGraphic();
		}else if(e.getSource().equals(Planing)){
			CenterPlaning();
		}
		
	}

	public void CenterMain(){
		
	}
	
	public void CenterBalance(){
		JPanel BalancePanel = new JPanel();
		BalancePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		BalancePanel.add(new JLabel("Center"));
		
		add(BalancePanel, BorderLayout.CENTER);
		
		repaint();
	}
	
	public void CenterGraphic(){
		
	}
	
	public void CenterPlaning(){
		
	}
}
