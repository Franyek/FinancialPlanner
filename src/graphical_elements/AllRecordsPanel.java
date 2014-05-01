package graphical_elements;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFormattedTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.MaskFormatter;
import javax.swing.JButton;
import javax.swing.BoxLayout;

public class AllRecordsPanel extends JPanel implements ActionListener {
	
	public RecordsPanel rec;
	Dimension dime;
	JFormattedTextField from;
	JFormattedTextField till;
	JButton OK;
	int FYear;
	int FMonth;
	int FDay;
	int TYear;
	int TMonth;
	int TDay;
	JPanel Buttons;
	boolean l;
	
	public AllRecordsPanel(Dimension dim, boolean k) throws Exception{
		l = k;
		dime = dim;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		Buttons = new JPanel();
		Buttons.setAlignmentX(this.CENTER_ALIGNMENT);
		OK = new JButton("OK");
		OK.addActionListener(this);
		MaskFormatter mft = new MaskFormatter("####-##-##");
		from = new JFormattedTextField(mft);
		till = new JFormattedTextField(mft);
		from.setColumns(8);
		till.setColumns(8);
		
		//dim = new Dimension(200, 450);		//Sztem (700, 450) az optimális
		rec = new RecordsPanel(dime, 0, 0, 0, 0, 0, 0, l);
		rec.scrollPane1.setAlignmentX(this.CENTER_ALIGNMENT);
		Buttons.add(new JLabel("Ettõl:"));
		Buttons.add(from);
		Buttons.add(new JLabel("Eddig:"));
		Buttons.add(till);
		Buttons.add(OK);
		add(Buttons);
		add(rec.scrollPane1);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(OK)){
			try {
				FYear = Integer.parseInt(from.getText(0, 4))-1900;
				FMonth = Integer.parseInt(from.getText(5, 2))-1;
				FDay = Integer.parseInt(from.getText(8, 2));
				TYear = Integer.parseInt(till.getText(0, 4))-1900;
				TMonth = Integer.parseInt(till.getText(5, 2))-1;
				TDay = Integer.parseInt(till.getText(8, 2));
				remove(rec.scrollPane1);
				rec = new RecordsPanel(dime, FYear,  FMonth, FDay, TYear, TMonth, TDay, l);
				add(rec.scrollPane1);
				revalidate();
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
	
	public void deletRec() throws Exception{
		rec.deletrow();
	}
}
