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
		
		//Line_startPanel beállításai
		line_startPanel.setLayout(new GridLayout(7, 1));
		line_startPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		//Line_startPanel gombjainak elkészítése, hozzáadása, ActionListenerek hozzáadása
		MainButton = new JButton("Fõmenü");
		Balance = new JButton("Eredménykimutatás");
		Graphic = new JButton("Grafikus megjelenítés");
		Planing = new JButton("Tervezett tranzakciók");
		Records = new JButton("Rögzített tranzakciók");
		
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
		
		//Menubár megalkotása és hozzáadása a JFramehez
		menuBar = new JMenuBar();
		setmenubar(menuBar);
		setJMenuBar(menuBar);
		
		//Panelek hozzáadása a Frame-hez, elsõnek mindig a Main jelenik meg, amit gombnyomásra lehet változtatni
		add(mainPanel, BorderLayout.CENTER);
		add(line_startPanel, BorderLayout.WEST);
		
		//Ablak méretezése, láthatóvá tétele
		setSize(900, 700);
		setVisible(true);
	}
	
	//Gombnyomásra megváltozik, hogy melyik panel jelenik meg!!!
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
}
