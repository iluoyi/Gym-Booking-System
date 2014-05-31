import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
/**
 * This is the panel for maintain the dairy of a specialist.
 * The user is to set a time to be available or unavailable 
 * for booking.
 * @author royal (luoyi.zxx@gmail.com)
 */
public class Dairy_Panel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTable jTable_dairy = null;
	private JButton jbt_unavailable = null;
	private JTextArea jta_excuse = null;
	private JButton jButton_submit = null;
	private JButton jButton_cancel = new JButton("Cancel(Set Available)");
	Vector<String> dairyColumn = new Vector<String>();
	Vector<Vector<String>> dairyRow = new Vector<Vector<String>>();

	private JPanel jPanel = null;
	private JScrollPane jScrollPane = null;
	/**
	 * This is the default constructor
	 */
	public Dairy_Panel() {
		super();
		initialize();
		
		//add listener to the jbt_unavailable button
		jbt_unavailable.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//refresh the appointment changing Table
				refreshDairy_Table();		
			}
		});
		//add listener to the submit button
		jButton_submit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if ((jTable_dairy.getSelectedRowCount()!=0) && (jTable_dairy.getSelectedColumn()!=0)
					&& ((((String) jTable_dairy.getValueAt(jTable_dairy.getSelectedRow(), jTable_dairy.getSelectedColumn())).equals("Available")))){
					int column = jTable_dairy.getSelectedColumn();
					String time_Period = null;
					HandleAppointment dealApp = new HandleAppointment();
								
					if (column==1) {time_Period = "AM";} else {time_Period = "PM";}
					Appointment newPermit = new Appointment();
					newPermit.setData((String) jTable_dairy.getValueAt(jTable_dairy.getSelectedRow(), 0));
					newPermit.setTimePeriod(time_Period);
					newPermit.setMemberID("000");
					newPermit.setSpectID(UserInterface.getUserID());
					newPermit.setRoomNo("NONE");
					newPermit.setFlag("0");
					
					dealApp.insertAppointment(newPermit);
					new AddStaff().addLeaveReason(UserInterface.getUserID(),(String) jTable_dairy.getValueAt(jTable_dairy.getSelectedRow(), 0), time_Period, jta_excuse.getText());
					//refresh the appointment changing Table
					refreshDairy_Table();
					JOptionPane.showMessageDialog(null, "Operation succeeds !", "Warning", JOptionPane.INFORMATION_MESSAGE);
					jta_excuse.setText("");
				
				}
			}
		});
		//for cancel button
		jButton_cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if ((jTable_dairy.getSelectedRowCount()!=0) && (jTable_dairy.getSelectedColumn()!=0)
					&& ((((String) jTable_dairy.getValueAt(jTable_dairy.getSelectedRow(), jTable_dairy.getSelectedColumn())).equals("Unavailable")))){
					int column = jTable_dairy.getSelectedColumn();
					String time_Period = null;
					HandleAppointment dealApp = new HandleAppointment();
								
					if (column==1) {time_Period = "AM";} else {time_Period = "PM";}
					Appointment cancelPermit = new Appointment();
					cancelPermit.setData((String) jTable_dairy.getValueAt(jTable_dairy.getSelectedRow(), 0));
					cancelPermit.setTimePeriod(time_Period);
					cancelPermit.setMemberID("000");
					
					dealApp.deleteAppointment(cancelPermit);
					
					//refresh the appointment changing Table
					refreshDairy_Table();		
					JOptionPane.showMessageDialog(null, "Operation succeeds!", "Warning", JOptionPane.INFORMATION_MESSAGE);
					
				}
			}
		});	
	}
	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(678, 458);
		this.setLayout(new BorderLayout());
		this.add(getJScrollPane(), BorderLayout.CENTER);
		this.add(getJPanel(), BorderLayout.EAST);
	}

	/**
	 * This method initializes jScrollPane
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJTable());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTable
	 *
	 * @return javax.swing.JTable
	 */
	private JTable getJTable() {
		dairyColumn.add("Date");
		dairyColumn.add("AM");
		dairyColumn.add("PM");
		
		for (int i=0; i<50; i++){
			Vector<String> row = new Vector<String>();
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			dairyRow.add(row);
		}
		

		if (jTable_dairy == null) {
			jTable_dairy = new JTable(new AllTableModel(dairyRow, dairyColumn));
			jTable_dairy.setShowGrid(true);
			jTable_dairy.setRowHeight(30);
		}
		
		return jTable_dairy;
	}

	/**
	 * This method initializes jPanel
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setVgap(20);
			flowLayout.setHgap(15);
			jPanel = new JPanel();
			jPanel.setLayout(flowLayout);
			jPanel.setPreferredSize(new Dimension(220, 530));
			jPanel.add(getJButton(), null);
			JPanel temp = new JPanel(new BorderLayout());
			temp.add(new JLabel("Reason:"), BorderLayout.NORTH);
			temp.add(getJTextArea(), null);
			jPanel.add(temp);
			jPanel.add(getJButton_submit(), null);
			jPanel.add(jButton_cancel);
		}
		return jPanel;
	}

	/**
	 * This method initializes jButton
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButton() {
		if (jbt_unavailable == null) {
			jbt_unavailable = new JButton();
			jbt_unavailable.setText("Asking for Leave");
			jbt_unavailable.setPreferredSize(new Dimension(173, 28));
		}
		return jbt_unavailable;
	}

	/**
	 * This method initializes jTextArea
	 *
	 * @return javax.swing.JTextArea
	 */
	private JScrollPane getJTextArea() {
			jta_excuse = new JTextArea(16,16);
			jta_excuse.setText(" ");
			jta_excuse.setLineWrap(true);
			jta_excuse.setEditable(true);
			JScrollPane notesPane = new JScrollPane(jta_excuse);
			notesPane.setBackground(Color.white);
			notesPane.setBounds(520, 20, 160, 220);
		return notesPane;
	}

	/**
	 * This method initializes jButton_submit
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButton_submit() {
		if (jButton_submit == null) {
			jButton_submit = new JButton();
			jButton_submit.setText("Submit(Set Unavailable)");
		}
		return jButton_submit;
	}
	/**
	 * This method is used to refresh the Dairy Table
	 */
	public void refreshDairy_Table(){
		dairyRow.clear();
		
		DateTest dt = new DateTest();
		HandleAppointment dealApp = new HandleAppointment();
		
		for (int i=0; i<30; i++){
			Vector<String> tempRow = new Vector<String>();
			tempRow.add(dt.changeDateForm()[i]);
			
			if (dealApp.isTimeforLeave(dt.changeDateForm()[i], "AM", UserInterface.getUserID())){
				tempRow.add("Unavailable");
					}else if (dealApp.isTimeforWork(dt.changeDateForm()[i], "AM", UserInterface.getUserID())){
						tempRow.add("An Appointment"); }
					else {tempRow.add("Available");}
			
			if (dealApp.isTimeforLeave(dt.changeDateForm()[i], "PM", UserInterface.getUserID())){
				tempRow.add("Unavailable");
					}else if (dealApp.isTimeforWork(dt.changeDateForm()[i], "PM", UserInterface.getUserID())){
						tempRow.add("An Appointment"); }
					else {tempRow.add("Available");}
			
			dairyRow.add(tempRow);
				}
		SwingUtilities.updateComponentTreeUI(jTable_dairy);
	}
}
