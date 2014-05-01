package graphical_elements;
import java.awt.*;
import java.awt.event.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;



public class MainFrame extends JFrame implements ActionListener{

	private JMenuBar menuBar;
	private JButton MainButton;
	private JButton Balance;
	private JButton Graphic;
	private JButton Planing;
	private JButton Records;
	public JPanel line_startPanel;
	public MainPanel mainPanel;
	public BalancePanel balancePanel;
	public GraphicPanel graphicPanel;
	public PlanningPanel planingPanel;
	public AllRecordsPanel recordsPanel;

	public MainFrame() throws Exception{
		super("Financial Planer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		mainPanel = new MainPanel();
		line_startPanel = new JPanel();
		
		//Line_startPanel be�ll�t�sai
		line_startPanel.setLayout(new GridLayout(7, 1));
		line_startPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		//Line_startPanel gombjainak elk�sz�t�se, hozz�ad�sa, ActionListenerek hozz�ad�sa
		MainButton = new JButton("F�men�");
		Balance = new JButton("Eredm�nykimutat�s");
		Graphic = new JButton("Grafikus megjelen�t�s");
		Planing = new JButton("Tervezett tranzakci�k");
		Records = new JButton("R�gz�tett tranzakci�k");
		
		line_startPanel.add(MainButton);
		line_startPanel.add(Balance);
		line_startPanel.add(Graphic);
		line_startPanel.add(Planing);
		line_startPanel.add(Records);
		
		
		MainButton.addActionListener(this);
		Balance.addActionListener(this);
		Graphic.addActionListener(this);
		Planing.addActionListener(this);
		Records.addActionListener(this);
		
		//Menub�r megalkot�sa �s hozz�ad�sa a JFramehez
		menuBar = new JMenuBar();
		setmenubar(menuBar);
		setJMenuBar(menuBar);
		
		//Panelek hozz�ad�sa a Frame-hez, els�nek mindig a Main jelenik meg, amit gombnyom�sra lehet v�ltoztatni
		add(mainPanel, BorderLayout.CENTER);
		add(line_startPanel, BorderLayout.WEST);
		
		//Ablak m�retez�se, l�that�v� t�tele
		setSize(900, 700);
		setVisible(true);
	}
	
	//Gombnyom�sra megv�ltozik, hogy melyik panel jelenik meg!!!
	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(MainButton)){
			try {
				mainPanel = new MainPanel();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for(int i=0; i<getContentPane().getComponentCount(); i++){
				if(getContentPane().getComponents()[i]!=this.line_startPanel)
					getContentPane().remove(getContentPane().getComponents()[i]);
			}

			getContentPane().add(mainPanel);
			mainPanel.revalidate();
		}else if(e.getSource().equals(Balance)){
			try {
				balancePanel = new BalancePanel();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//getContentPane().remove(mainPanel);
			for(int i=0; i<getContentPane().getComponentCount(); i++){
				if(getContentPane().getComponents()[i]!=this.line_startPanel)
					getContentPane().remove(getContentPane().getComponents()[i]);
			}
			getContentPane().add(balancePanel);
			balancePanel.revalidate();
		}else if(e.getSource().equals(Graphic)){
			try {
				graphicPanel = new GraphicPanel();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//getContentPane().remove(mainPanel);
			for(int i=0; i<getContentPane().getComponentCount(); i++){
				if(getContentPane().getComponents()[i]!=this.line_startPanel)
					getContentPane().remove(getContentPane().getComponents()[i]);
			}
			getContentPane().add(graphicPanel);
			graphicPanel.revalidate();
		}else if(e.getSource().equals(Planing)){
			try {
				planingPanel = new PlanningPanel();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//getContentPane().remove(mainPanel);
			for(int i=0; i<getContentPane().getComponentCount(); i++){
				if(getContentPane().getComponents()[i]!=this.line_startPanel)
					getContentPane().remove(getContentPane().getComponents()[i]);
			}
			getContentPane().add(planingPanel);
			planingPanel.revalidate();
		}else if(e.getSource().equals(Records)){
			try {
				recordsPanel = new AllRecordsPanel(new Dimension(200, 450), true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//getContentPane().remove(mainPanel);
			for(int i=0; i<getContentPane().getComponentCount(); i++){
				if(getContentPane().getComponents()[i]!=this.line_startPanel)
					getContentPane().remove(getContentPane().getComponents()[i]);
			}
			getContentPane().add(recordsPanel);
			recordsPanel.revalidate();
		}
		
	}
	
	public void setmenubar (JMenuBar menubar){
		
		//MenuBar f� menupontjai
		JMenu FileMenu = new JMenu("File");
		JMenu EditMenu = new JMenu("Edit");
		
		//F� menupontok alpontjainak megalkot�sa
		JMenuItem newAction = new JMenuItem("New");
		JMenuItem openAction = new JMenuItem("Open");
		JMenuItem saveAction = new JMenuItem("Save");
		JMenuItem CopyAction = new JMenuItem("Copy");
		JMenuItem PasteAction = new JMenuItem("Paste");
		
		//Az alpontok hozz�ad�sa a f�menuh�z
		FileMenu.add(newAction);
		FileMenu.add(openAction);
		FileMenu.add(saveAction);
		EditMenu.add(CopyAction);
		EditMenu.add(PasteAction);
	
		//F�pontok hozz�ad�sa a Menubarhoz
		menubar.add(FileMenu);
		menubar.add(EditMenu);
	}
}
