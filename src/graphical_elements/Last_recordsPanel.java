package graphical_elements;

import javax.swing.*;
import java.awt.Dimension;



public class Last_recordsPanel extends JPanel {
	
	Dimension dim;
	RecordsPanel pan;
	
	private JLabel label1;
	private JScrollPane scrollPane1;
	
	public Last_recordsPanel() throws Exception {
		dim = new Dimension(380, 280);
		pan = new RecordsPanel(dim);
		initComponents();
	}

	private void initComponents() {
		scrollPane1 = new JScrollPane();
		scrollPane1 = pan.scrollPane1;
		label1 = new JLabel();

		//---- label1 ----
		label1.setText("Az elmúlt 30 napban rögzített rekordok");
		label1.setHorizontalAlignment(SwingConstants.CENTER);

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup()
						.addComponent(label1, GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
						.addComponent(scrollPane1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE))
					.addContainerGap())
		);
		layout.setVerticalGroup(
			layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
					.addGap(17, 17, 17)
					.addComponent(label1)
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(43, Short.MAX_VALUE))
		);
	}
}

