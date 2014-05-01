package graphical_elements;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.MaskFormatter;

import controll.GraphicControll;


public class GraphicPanelPopup extends JDialog implements ActionListener {
	
	private JLabel label1;
	private JTextField from;
	private JTextField till;
	private JLabel label2;
	private JLabel label3;
	private JButton OK;
	protected GraphicControll gc;
	
	int FYear;
	int FMonth;
	int FDay;
	int TYear;
	int TMonth;
	int TDay;
	
	public String S;
	
	public GraphicPanelPopup(Frame owner) throws ParseException {
		super(owner);
		initComponents();
	}

	public GraphicPanelPopup(Dialog owner) throws ParseException {
		super(owner);
		initComponents();
	}
	
	public GraphicPanelPopup(String s) throws ParseException {
		S = s;
		initComponents();
	}

	private void initComponents() throws ParseException {
		label1 = new JLabel();
		label2 = new JLabel();
		label3 = new JLabel();
		OK = new JButton();
		
		OK.addActionListener(this);
		MaskFormatter mft = new MaskFormatter("####-##-##");
		from = new JFormattedTextField(mft);
		till = new JFormattedTextField(mft);
		from.setColumns(8);
		till.setColumns(8);

		//======== this ========
		setTitle("Intervallum megadása");
		Container contentPane = getContentPane();

		//---- label1 ----
		label1.setText("Milyen id\u0151tartom\u00e1ny adatait szeretn\u00e9 l\u00e1tni?");
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setFont(new Font("Tahoma", Font.BOLD, 16));

		//---- label2 ----
		label2.setText("Ett\u0151l:");
		label2.setFont(new Font("Tahoma", Font.PLAIN, 14));

		//---- label3 ----
		label3.setText("Eddig:");
		label3.setFont(new Font("Tahoma", Font.PLAIN, 14));

		//---- OK ----
		OK.setText("OK");

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(contentPaneLayout.createParallelGroup()
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addComponent(label1, GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
							.addComponent(label2, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
							.addComponent(from, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addGap(35, 35, 35)
							.addComponent(label3, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
							.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
								.addComponent(OK, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(till, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE))
							.addGap(44, 44, 44))))
		);
		contentPaneLayout.setVerticalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(label1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(from, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(till, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label2, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(label3, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
					.addGap(18, 18, 18)
					.addComponent(OK, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(15, Short.MAX_VALUE))
		);
		pack();
		setLocationRelativeTo(getOwner());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(OK)){
			try {
				FYear = Integer.parseInt(from.getText(0, 4))-1900;
				FMonth = Integer.parseInt(from.getText(5, 2))-1;
				FDay = Integer.parseInt(from.getText(8, 2));
				TYear = Integer.parseInt(till.getText(0, 4))-1900;
				TMonth = Integer.parseInt(till.getText(5, 2))-1;
				TDay = Integer.parseInt(till.getText(8, 2));
				Date dateFrom = new Date(FYear, FMonth, FDay);
				Date dateTill = new Date(TYear, TMonth, TDay);
				gc = new GraphicControll(S, dateFrom, dateTill);
				setVisible(false);
			} catch (BadLocationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
}