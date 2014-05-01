package graphical_elements;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import controll.PlanningContr;

public class PlanningRecordsPopup extends JDialog {
	
	private JScrollPane scrollPane1;
	private JList list1;
	private JLabel label1;
	private JButton button1;
	private JCheckBox checkBox1;
	private PlanningContr PlanControll;
	private Warning W;
	
	public int ID;
	public String Where;
	public String Amount;
	public String Comment;
	public String From;
	
	public PlanningRecordsPopup(Frame owner) throws Exception {
		super(owner);
		initComponents();
	}

	public PlanningRecordsPopup(int i, String W, String A, String C) throws Exception {
		ID = i;
		Where = W;
		Amount = A;
		Comment = C;
		PlanControll = new PlanningContr();
		initComponents();
	}

	private void initComponents() throws Exception {
		DefaultListModel model = new DefaultListModel();
		ArrayList<String> items = new ArrayList<String>();
		items = PlanControll.GetFundsName();
		for (int i=0; i<items.size(); i++) {
		    model.add(i, items.get(i));
		}
		
		scrollPane1 = new JScrollPane();
		list1 = new JList(model);
		label1 = new JLabel();
		button1 = new JButton();
		checkBox1 = new JCheckBox();

		//======== this ========
		setTitle("Tervezett költség kifizetése");
		Container contentPane = getContentPane();

		//======== scrollPane1 ========
		{
			scrollPane1.setViewportView(list1);
		}

		//---- label1 ----
		label1.setText("Mibõl fizeted ki?");
		label1.setHorizontalAlignment(SwingConstants.CENTER);

		//---- button1 ----
		button1.setText("Kifizetve");
		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(checkBox1.isSelected())
					tweet(list1.getSelectedValue().toString()+"-t");
				if(list1.getSelectedIndex() >= 0){
					try {
						PlanControll.PayOut(ID, list1.getSelectedValue().toString(), Where, Amount, Comment);
						setVisible(false);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					W = new Warning("Nem választott ki számlát!");
				}
			}
		});
		
		checkBox1.setText("Twitteld ki");

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addGap(42, 42, 42)
					.addComponent(button1, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(53, Short.MAX_VALUE))
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addGap(25, 25, 25)
					.addGroup(contentPaneLayout.createParallelGroup()
						.addComponent(checkBox1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
						.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
						.addComponent(label1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
					.addGap(21, 21, 21))
		);
		contentPaneLayout.setVerticalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(label1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addComponent(checkBox1)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(button1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addGap(35, 35, 35))
		);
		pack();
		setLocationRelativeTo(getOwner());
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void tweet(String S){
		PlanControll.tweet(S);
	}
	
}
