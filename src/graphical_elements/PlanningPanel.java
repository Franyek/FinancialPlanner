package graphical_elements;

import java.awt.Dimension;
import javax.swing.JPanel;


public class PlanningPanel extends JPanel{
	private AllRecordsPanel pan;
	
	public PlanningPanel() throws Exception{
		pan = new AllRecordsPanel(new Dimension(720, 450), false);
		add(pan);
	}
}