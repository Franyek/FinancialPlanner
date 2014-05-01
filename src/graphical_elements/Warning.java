package graphical_elements;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Warning extends JDialog implements ActionListener {
	
	private JLabel label1;
	private JButton button1;
	
	public Warning(String s) {
		initComponents(s);
	}

	private void initComponents( String S) {

		label1 = new JLabel();
		button1 = new JButton();

		//======== this ========
		setTitle("Hiba!");
		Container contentPane = getContentPane();

		//---- label1 ----
		label1.setText(S);
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setForeground(Color.red);
		label1.setFont(new Font("Trebuchet MS", Font.BOLD, 24));

		//---- button1 ----
		button1.setText("OK");
		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
					setVisible(false);
			}
		});

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
							.addComponent(button1, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
							.addGap(136, 136, 136))))
		);
		contentPaneLayout.setVerticalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addGap(22, 22, 22)
					.addComponent(label1, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addComponent(button1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(14, Short.MAX_VALUE))
		);
		pack();
		setLocationRelativeTo(getOwner());
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}