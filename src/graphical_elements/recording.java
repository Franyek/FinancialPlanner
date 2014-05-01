package graphical_elements;


import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;

public class recording extends JPanel implements ActionListener, KeyListener{
	
	private JButton recording_button;
	private JButton delet_button;
	private JButton Plrecording_button;
	private JButton Pldelet_button;
	private recording_popoup popup;
	private delet_popup del_popup; 
	private recordingComingRecords RecPlanPopup;
	private planedRecorsdDelet PRDelet;
	
	public recording(){
		delet_button = new JButton("Tranzakció törlése");
		recording_button = new JButton("Tranzakció rögzítése");
		Pldelet_button = new JButton("Tervezett tranzakció törlése");
		Plrecording_button = new JButton("Tervezett tranzakció rögzítése");
		
		setLayout(new FlowLayout(FlowLayout.CENTER, 3, 15));
		setSize(500, 300);
		
		add(delet_button);
		add(recording_button);
		add(Pldelet_button);
		add(Plrecording_button);
		
		Pldelet_button.addActionListener(this);
		Plrecording_button.addActionListener(this);
		recording_button.addActionListener(this);
		delet_button.addActionListener(this);
	}
	
	public void keyPressed (KeyEvent e){
		
	}
	//TODO 2 gomb actionListener hiányzik
	public void actionPerformed (ActionEvent e){
		
		if(e.getSource().equals(recording_button)){
			try {
				popup = new recording_popoup();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(e.getSource().equals(delet_button)){
			try {
				del_popup = new delet_popup();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(e.getSource().equals(Plrecording_button)){
			try {
				RecPlanPopup = new recordingComingRecords();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(e.getSource().equals(Pldelet_button)){
			try {
				PRDelet = new planedRecorsdDelet();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
