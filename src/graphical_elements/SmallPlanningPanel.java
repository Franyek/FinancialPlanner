package graphical_elements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SmallPlanningPanel extends JPanel {
	
	private JScrollPane scrollPane1;
	private JButton button1;
	private JLabel label1;
	private PlanningRecordsPopup popup;
	private Warning NSI;
	
	public PlanRecordsPanel pan;
	
	public SmallPlanningPanel() throws Exception {
		pan = new PlanRecordsPanel();
		initComponents();
	}

	private void initComponents() throws Exception {
		scrollPane1 = new JScrollPane();
		scrollPane1 = pan.scrollPane1;
		button1 = new JButton();
		label1 = new JLabel();

		//---- button1 ----
		button1.setText("Kijelölt tétel kifizetése");
		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(e.getSource().equals(button1)){
					if(pan.IsRowSelected()){
						try {
							int i = Integer.parseInt(pan.GetSelectedItem(0).toString());
							popup = new PlanningRecordsPopup(i, pan.GetSelectedItem(2).toString(),
									pan.GetSelectedItem(3).toString(), pan.GetSelectedItem(4).toString());
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else{
						NSI = new Warning("Nincs kiválasztott tétel!");
						NSI.setVisible(true);
					}
						
				}
			}
		});

		//---- label1 ----
		label1.setText("Jövõben esedékes kifizetések");
		label1.setHorizontalAlignment(SwingConstants.CENTER);

		GroupLayout layout = new GroupLayout(this);
		layout.setHonorsVisibility(false);
		setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup()
						.addComponent(button1, GroupLayout.Alignment.TRAILING)
						.addComponent(label1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
						.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE))
					.addContainerGap())
		);
		layout.setVerticalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addGap(17, 17, 17)
					.addComponent(label1)
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
					.addGap(11, 11, 11)
					.addComponent(button1)
					.addContainerGap())
		);
	}


}