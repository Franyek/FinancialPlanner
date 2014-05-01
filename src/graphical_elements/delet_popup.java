package graphical_elements;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.text.BadLocationException;


public class delet_popup extends JDialog implements ItemListener {

	JPanel begin;
	RecordsPanel rec;
	
	private JButton Keres;
	private JFormattedTextField from;
	private JFormattedTextField till;
	private Dimension dim;
	private JButton Delet;
	private JPanel btnPanel;
	
	int FYear;
	int FMonth;
	int FDay;
	int TYear;
	int TMonth;
	int TDay;
	
	AllRecordsPanel pan;
	
	public delet_popup() throws Exception{
		pan = new AllRecordsPanel(new Dimension(600, 200), true);
		btnPanel = new JPanel();
		setTitle("Rekord törlése");
		Container contentPane = getContentPane();
		Delet = new JButton("Törlés");
		Delet.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					pan.deletRec();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		pan.setAlignmentX(contentPane.CENTER_ALIGNMENT);
		Delet.setAlignmentX(contentPane.LEFT_ALIGNMENT);
		contentPane.add(pan);
		contentPane.add(Delet);
		pack();
		setSize(600, 500);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(Keres)){
			try {
				FYear = Integer.parseInt(from.getText(0, 4))-1900;
				FMonth = Integer.parseInt(from.getText(5, 2));
				FDay = Integer.parseInt(from.getText(8, 2));
				TYear = Integer.parseInt(till.getText(0, 4))-1900;
				TMonth = Integer.parseInt(till.getText(5, 2));
				TDay = Integer.parseInt(till.getText(8, 2));
				System.out.println(FYear);
				pan.remove(pan.rec.scrollPane1);
				//remove(rec.scrollPane1);
				pan.rec = new RecordsPanel(dim, FYear,  FMonth, FDay, TYear, TMonth, TDay, true);
				add(rec.scrollPane1);
				validate();
				repaint();
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
