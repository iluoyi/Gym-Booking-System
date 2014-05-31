import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

/**
 * This is the panel for booking. Users are able to 
 * create new appointments. * If an nutritionist or 
 * a trainer is booked, no room booking is required.
 * However, if a physiotherapist is booked, the user 
 * is asked to further book a room.
 * @author royal (luoyi.zxx@gmail.com)
 */
public class Booking_Panel extends JPanel {

	private static final long serialVersionUID = 1L;

	Vector<String> bookSpectColumn = new Vector<String>();
	Vector<Vector<String>> bookSpectRow = new Vector<Vector<String>>();
	Vector<String> bookRoomColumn = new Vector<String>();
	Vector<Vector<String>> bookRoomRow = new Vector<Vector<String>>();
	Vector<String> changeBookColumn = new Vector<String>();
	Vector<Vector<String>> changeBookRow = new Vector<Vector<String>>();
	Appointment newAppOfP = new Appointment();
	private JComboBox jComboBox_date = null;
	private JComboBox jComboBox_time = null;
	private JComboBox jComboBox_type = null;
	private JTable jTable_room = null;
	public JTable jTable_spectbook = null;
	private JTable jTable_changebook = null;
	private JButton jButton_new = null;
	private JButton jButton_change = null;
	public JButton jbt_submit = new JButton("Submit");
	private JButton jbt_back = new JButton("Back");
	private JButton jbt_final = new JButton("OK");
	private JButton jbt_delete = new JButton("Delete");

	JLabel note = new JLabel("You should further select a room since you have chosen a physiotherapist.");
	JLabel jLabel_type = new JLabel("Type");
	JLabel jLabel_time = new JLabel("Time");
	JLabel jLabel_date = new JLabel("Date");

	private JPanel jPanel_table = null;
	private JScrollPane jScrollPane_roombooking = null;
	private JScrollPane jScrollPane_spectbooking = null;
	private JScrollPane jScrollPane_changebooking = null;
	private CardLayout c = new CardLayout();

	/**
	 * This is the default constructor
	 */
	public Booking_Panel() {
		super();
		initialize();
		
		//Listen to the event from jComboBox_date for reorder
		jComboBox_date.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				if (e.getStateChange() == ItemEvent.SELECTED){
					refreshJspec_Table((String)jComboBox_date.getSelectedItem(), (String)jComboBox_time.getSelectedItem(), (String)jComboBox_type.getSelectedItem());
					refreshChanging_Table((String)jComboBox_date.getSelectedItem(), (String)jComboBox_time.getSelectedItem(), (String)jComboBox_type.getSelectedItem());
				}
			}
		});
		//Listen to the event from jComboBox_date for reorder
		jComboBox_time.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				if (e.getStateChange() == ItemEvent.SELECTED){
					refreshJspec_Table((String)jComboBox_date.getSelectedItem(), (String)jComboBox_time.getSelectedItem(), (String)jComboBox_type.getSelectedItem());
					refreshChanging_Table((String)jComboBox_date.getSelectedItem(), (String)jComboBox_time.getSelectedItem(), (String)jComboBox_type.getSelectedItem());
				}
			}
		});
		//Listen to the event from jComboBox_date for reorder
		jComboBox_type.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				if (e.getStateChange() == ItemEvent.SELECTED){
					refreshJspec_Table((String)jComboBox_date.getSelectedItem(), (String)jComboBox_time.getSelectedItem(), (String)jComboBox_type.getSelectedItem());
					refreshChanging_Table((String)jComboBox_date.getSelectedItem(), (String)jComboBox_time.getSelectedItem(), (String)jComboBox_type.getSelectedItem());
				}
			}
		});

		//to make a new appointment
		jButton_new.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				c.first(jPanel_table);
				jbt_submit.setVisible(true);
				jbt_delete.setVisible(false);
				jComboBox_date.setSelectedIndex(0);
				jComboBox_time.setSelectedIndex(0);
				jComboBox_type.setSelectedIndex(0);
				//refresh the spectTable
				refreshJspec_Table((String)jComboBox_date.getSelectedItem(), (String)jComboBox_time.getSelectedItem(), (String)jComboBox_type.getSelectedItem());
			}
		});
		
		//maintain the appointments
		jButton_change.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				c.last(jPanel_table);
				jbt_submit.setVisible(false);
				jbt_delete.setVisible(true);
				jComboBox_date.setSelectedIndex(0);
				jComboBox_time.setSelectedIndex(0);
				jComboBox_type.setSelectedIndex(0);
				//refresh the appointment changing Table
				refreshChanging_Table((String)jComboBox_date.getSelectedItem(), (String)jComboBox_time.getSelectedItem(), (String)jComboBox_type.getSelectedItem());
			}
		});
		
		//to make a new appointment 
		jbt_submit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (Member_Panel.getCurrentMemberID().length()==0){
					JOptionPane.showMessageDialog(null, "No member logs in so you cannot book!", "Warning", JOptionPane.INFORMATION_MESSAGE);
				} else if (jTable_spectbook.getSelectedRow()!=-1){
					HandleAppointment dealApp = new HandleAppointment();		
					String tempString = (String) jTable_spectbook.getValueAt(jTable_spectbook.getSelectedRow(), 2);
					Appointment newApp = new Appointment();
					newApp.setData((String) jTable_spectbook.getValueAt(jTable_spectbook.getSelectedRow(), 0));
					newApp.setTimePeriod((String) jTable_spectbook.getValueAt(jTable_spectbook.getSelectedRow(), 1));
					newApp.setMemberID(Member_Panel.getCurrentMemberID());
					newApp.setSpectID((String) jTable_spectbook.getValueAt(jTable_spectbook.getSelectedRow(), 5));
					newApp.setFlag("1");
					newApp.setRoomNo("NONE");
					
					if (tempString.charAt(0)=='P'){
						jButton_new.setEnabled(true);
						jButton_change.setEnabled(true);
						c.next(jPanel_table);
						jComboBox_date.setVisible(false);
						jComboBox_time.setVisible(false);
						jComboBox_type.setVisible(false);
						jLabel_type.setVisible(false);
						jLabel_time.setVisible(false);
						jLabel_date.setVisible(false);
						jbt_submit.setVisible(false);
						note.setVisible(true);
						jbt_back.setVisible(true);
						jbt_final.setVisible(true);
						jButton_new.setEnabled(false);
						jButton_change.setEnabled(false);
						
						newAppOfP = newApp;
						refreshRoom_Table(newAppOfP.getData(),newAppOfP.getTimePeriod());
					}else {
					dealApp.insertAppointment(newApp);
					refreshJspec_Table((String)jComboBox_date.getSelectedItem(), (String)jComboBox_time.getSelectedItem(), (String)jComboBox_type.getSelectedItem());					
					JOptionPane.showMessageDialog(null, "Congratulations! Booking succeeds!", "Warning", JOptionPane.INFORMATION_MESSAGE);					
					}				
					}				
				}
		});
		
		//to further book the room if it is Mr.P
		jbt_final.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (jTable_room.getSelectedRowCount()!=0){
					
					HandleAppointment dealApp = new HandleAppointment();
					newAppOfP.setRoomNo((String) jTable_room.getValueAt(jTable_room.getSelectedRow(), 0));
					dealApp.insertAppointment(newAppOfP);
					
				c.first(jPanel_table);
				jComboBox_date.setVisible(true);
				jComboBox_time.setVisible(true);
				jComboBox_type.setVisible(true);
				jLabel_type.setVisible(true);
				jLabel_time.setVisible(true);
				jLabel_date.setVisible(true);
				jbt_submit.setVisible(true);
				note.setVisible(false);
				jbt_back.setVisible(false);
				jbt_final.setVisible(false);
				jButton_new.setEnabled(true);
				jButton_change.setEnabled(true);
				
				JOptionPane.showMessageDialog(null, "Congratulations! Booking succeeds!", "Warning", JOptionPane.INFORMATION_MESSAGE);
				refreshJspec_Table((String)jComboBox_date.getSelectedItem(), (String)jComboBox_time.getSelectedItem(), (String)jComboBox_type.getSelectedItem());
				}
			}		
		});
				
		//to change the appointments
		jbt_delete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if ((jTable_changebook.getSelectedRowCount()!=0) && (jTable_changebook.getSelectedRow()<changeBookRow.size())){
					Appointment deathApp = new Appointment();
					HandleAppointment dealApp = new HandleAppointment();
					
					deathApp.setData((String) jTable_changebook.getValueAt(jTable_changebook.getSelectedRow(), 0));
					deathApp.setTimePeriod((String) jTable_changebook.getValueAt(jTable_changebook.getSelectedRow(), 1));
					deathApp.setMemberID((String) jTable_changebook.getValueAt(jTable_changebook.getSelectedRow(), 2));

					//Since only one appointment is available for each member at each time period, we ignore the spectID and roomNo
					//Above information is enough to recognize the member
					dealApp.deleteAppointment(deathApp);
					refreshChanging_Table((String)jComboBox_date.getSelectedItem(), (String)jComboBox_time.getSelectedItem(), (String)jComboBox_type.getSelectedItem());
					JOptionPane.showMessageDialog(null, "Congratulations! Deletion succeeds!", "Warning", JOptionPane.INFORMATION_MESSAGE);
					
				}
			}
		});
		
		//give up the appointment for specialist P
		jbt_back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				c.first(jPanel_table);
				jComboBox_date.setVisible(true);
				jComboBox_time.setVisible(true);
				jComboBox_type.setVisible(true);
				jLabel_type.setVisible(true);
				jLabel_time.setVisible(true);
				jLabel_date.setVisible(true);
				jbt_submit.setVisible(true);
				note.setVisible(false);
				jbt_back.setVisible(false);
				jbt_final.setVisible(false);
				jButton_new.setEnabled(true);
				jButton_change.setEnabled(true);
			}
		});
	}

	/**
	 * This method initializes this
	 *
	 *  
	 */
	public void initialize() {
		this.setLayout(new BorderLayout());
		this.setBounds(new Rectangle(0, 0, 780, 530));
		this.add(getJPanel_comboBox(),BorderLayout.SOUTH );
		this.add(getJPanel_button(), BorderLayout.EAST);
		this.add(getJPanel_table(), BorderLayout.CENTER);
	}

	/**
	 * This method initializes jPanel_comboBox
	 *
	 * @return javax.swing.JPanel
	 */
	public JPanel getJPanel_comboBox() {
			JPanel jPanel_comboBox = new JPanel();
			FlowLayout flowLayout = new FlowLayout(FlowLayout.LEADING);
			flowLayout.setAlignment(java.awt.FlowLayout.LEFT);
			flowLayout.setVgap(20);
			flowLayout.setHgap(24);
			jPanel_comboBox.setLayout(flowLayout);
			jPanel_comboBox.setPreferredSize(new Dimension(650, 60));
			jPanel_comboBox.add(jLabel_date, null);
			jPanel_comboBox.add(getJComboBox_date(), null);
			jPanel_comboBox.add(jLabel_time, null);
			jPanel_comboBox.add(getJComboBox_time(), null);
			jPanel_comboBox.add(jLabel_type, null);
			jPanel_comboBox.add(getJComboBox_type(), null);
			jbt_submit.setPreferredSize(new Dimension(180, 30));
			jbt_delete.setPreferredSize(new Dimension(180, 30));
			jbt_final.setPreferredSize(new Dimension(100, 30));
			jbt_back.setPreferredSize(new Dimension(100, 30));
			jbt_back.setVisible(false);
			jbt_final.setVisible(false);
			jbt_delete.setVisible(false);
			note.setVisible(false);
			jPanel_comboBox.add(note);
			jPanel_comboBox.add(jbt_back);
			jPanel_comboBox.add(jbt_final);
			jPanel_comboBox.add(jbt_submit);
			jPanel_comboBox.add(jbt_delete);
		return jPanel_comboBox;
	}

	/**
	 * This method initializes jPanel_table
	 *
	 * @return javax.swing.JPanel
	 */
	public JScrollPane getJScrollPane_spectbooking() {
		if (jScrollPane_spectbooking == null) {
			jScrollPane_spectbooking = new JScrollPane(getJTable_spectbook());
			jScrollPane_spectbooking.setName("spectbook");
			//jScrollPane_spectbooking.setViewportView();
			jScrollPane_spectbooking.setFont(new Font("Dialog", Font.PLAIN, 14));
		}
		return jScrollPane_spectbooking;
	}

	/**
	 * This method initializes jPanel_table
	 *
	 * @return javax.swing.JPanel
	 */
	public JScrollPane getJScrollPane_changebooking() {
		if (jScrollPane_changebooking == null) {
			jScrollPane_changebooking = new JScrollPane();
			jScrollPane_changebooking.setName("changebook");
			jScrollPane_changebooking.setViewportView(getJTable_changebook());
			jScrollPane_changebooking.setFont(new Font("Dialog", Font.PLAIN, 14));
		}
		return jScrollPane_changebooking;
	}

	
	/**
	 * This method initializes jComboBox_date
	 *
	 * @return javax.swing.JComboBox
	 */
	public JComboBox getJComboBox_date() {
		DateTest dt = new DateTest();
		String[] dateTerms = new String[11];
		dateTerms[0] = "ALL";
		for(int i=1;i<11;i++){
			//dateTerms[i]=dt.getDateTermList()[i];
			dateTerms[i]=dt.changeDateForm()[i-1]; // in the form of 2011-XX-XX
			
		}
		if (jComboBox_date == null) {
			jComboBox_date = new JComboBox(dateTerms);
		}
		return jComboBox_date;
	}

	/**
	 * This method initializes jComboBox_time
	 *
	 * @return javax.swing.JComboBox
	 */
	public JComboBox getJComboBox_time() {
		if (jComboBox_time == null) {
			jComboBox_time = new JComboBox(new Object[]{"ALL","AM","PM"});
		}
		return jComboBox_time;
	}

	/**
	 * This method initializes jComboBox_type
	 *
	 * @return javax.swing.JComboBox
	 */
	public JComboBox getJComboBox_type() {
		if (jComboBox_type == null) {
			jComboBox_type = new JComboBox(new Object[]{"ALL","Nutritionist","Trainer","Physiotherapist"});
		}
		return jComboBox_type;
	}

	/**
	 * This method initializes jPanel_button
	 *
	 * @return javax.swing.JPanel
	 */
	public JPanel getJPanel_button() {
			JPanel jPanel_button = null;
			FlowLayout flowLayout1 = new FlowLayout();
			flowLayout1.setHgap(15);
			flowLayout1.setVgap(40);
			jPanel_button = new JPanel();
			jPanel_button.setLayout(flowLayout1);
			jPanel_button.setPreferredSize(new Dimension(200, 530));
			jPanel_button.add(getJButton_new(), null);
			jPanel_button.add(getJButton_change(), null);
		return jPanel_button;
	}

	/**
	 * This method initializes jButton_new
	 *
	 * @return javax.swing.JButton
	 */
	public JButton getJButton_new() {
		if (jButton_new == null) {
			jButton_new = new JButton();
			jButton_new.setText("Available appointment");
			jButton_new.setPreferredSize(new Dimension(173, 30));
		}
		return jButton_new;
	}

	/**
	 * This method initializes jButton_change
	 *
	 * @return javax.swing.JButton
	 */
	public JButton getJButton_change() {
		if (jButton_change == null) {
			jButton_change = new JButton();
			jButton_change.setText("Change appointment");
			jButton_change.setPreferredSize(new Dimension(173, 30));
		}
		return jButton_change;
	}


	/**
	 * This method initializes jTable_spectbook
	 *
	 * @return javax.swing.JTable
	 */
	public JTable getJTable_spectbook() {
		bookSpectColumn.add("Date");
		bookSpectColumn.add("Time-Period");
		bookSpectColumn.add("Specialist Type");
		bookSpectColumn.add("Specialist Name");
		bookSpectColumn.add("Specialist Gender");
		bookSpectColumn.add("Specialist ID");
		
		for (int i=0; i<50; i++){
			Vector<String> row = new Vector<String>();
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			bookSpectRow.add(row);
		}

		if (jTable_spectbook == null) {
			jTable_spectbook = new JTable(new AllTableModel(bookSpectRow, bookSpectColumn));
			jTable_spectbook.setShowGrid(true);
			jTable_spectbook.setRowHeight(30);
		}
		return jTable_spectbook;
	}
	
	/**
	 * This method initializes jTable_changebook
	 *
	 * @return javax.swing.JTable
	 */
	public JTable getJTable_changebook() {
		changeBookColumn.add("Date");
		changeBookColumn.add("Time-Period");
		changeBookColumn.add("Member ID");
		changeBookColumn.add("Specialist Type");
		changeBookColumn.add("Specialist Name");
		changeBookColumn.add("Room");
		
		for (int i=0; i<50; i++){
			Vector<String> row = new Vector<String>();
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			changeBookRow.add(row);
		}

		if (jTable_changebook == null) {
			jTable_changebook = new JTable(new AllTableModel(changeBookRow, changeBookColumn));
			jTable_changebook.setShowGrid(true);
			jTable_changebook.setRowHeight(30);
		}
		return jTable_changebook;
	}

	/**
	 * This method initializes jPanel_table
	 *
	 * @return javax.swing.JPanel
	 */
	public JPanel getJPanel_table() {
		if (jPanel_table == null) {
			jPanel_table = new JPanel(c);
			jPanel_table.add(getJScrollPane_spectbooking(), getJScrollPane_spectbooking().getName());
			jPanel_table.add(getJScrollPane_roombooking(), getJScrollPane_roombooking().getName());
			jPanel_table.add(getJScrollPane_changebooking(), getJScrollPane_changebooking().getName());

		}
		return jPanel_table;
	}

	/**
	 * This method initializes jScrollPane_roombooking
	 *
	 * @return javax.swing.JScrollPane
	 */
	public JScrollPane getJScrollPane_roombooking() {
		if (jScrollPane_roombooking == null) {
			jScrollPane_roombooking = new JScrollPane();
			jScrollPane_roombooking.setName("roombook");
			jScrollPane_roombooking.setViewportView(getJTable_room());
		}
		return jScrollPane_roombooking;
	}

	/**
	 * This method initializes jTable_room
	 *
	 * @return javax.swing.JTable
	 */
	public JTable getJTable_room() {
		bookRoomColumn.add("Room");
		bookRoomColumn.add("Date");
		bookRoomColumn.add("Time-Period");
		
		for (int i=0; i<50; i++){
			Vector<String> row = new Vector<String>();
			row.add("");
			row.add("");
			row.add("");
			bookRoomRow.add(row);
		}

		if (jTable_room == null) {
			jTable_room = new JTable(new AllTableModel(bookRoomRow, bookRoomColumn));
			jTable_room.setShowGrid(true);
			jTable_room.setRowHeight(30);
		}
		return jTable_room;
	}

	/**
	 * @return the c
	 */
	public CardLayout getC() {
		return c;
	}
	
	
	/**
	 * refresh the Jspec_Table
	 */
	public void refreshJspec_Table(String date, String timePeriod, String spectType){
	
		bookSpectRow.clear();
	
		Vector<String> specialits = new StaffAccount().getAllSpecialist();
	
		HandleAppointment dealApp = new HandleAppointment();
		for (int i=1; i<11; i++){	
			for (int j=1; j<3; j++){
				for (int k=0; k<specialits.size();k++){
					boolean appFlag = false;
					if ((dealApp.isSpecialistAvailable((String)(jComboBox_date.getItemAt(i)), (String)(jComboBox_time.getItemAt(j)), specialits.get(k))) 
						&& (dealApp.isTimeAvailable((String)(jComboBox_date.getItemAt(i)), (String)(jComboBox_time.getItemAt(j)), Member_Panel.getCurrentMemberID()))){// To ensure that only available appointments can be made
						appFlag = true;
						
					}
						if (appFlag && (!date.equals("ALL"))) {
							if (!((String)(jComboBox_date.getItemAt(i))).equals(date)) appFlag = false;
						}
						
						if (appFlag && (!timePeriod.equals("ALL"))) {
							if (!((String)(jComboBox_time.getItemAt(j))).equals(timePeriod)) appFlag = false;
						}
						
						if (appFlag && (!spectType.equals("ALL"))) {
							if (!(new Staff(specialits.get(k)).getSpectType()).equals(spectType)) appFlag = false;
						}
						if (appFlag) {
						Vector<String> tempRow = new Vector<String>();	
						tempRow.add((String)(jComboBox_date.getItemAt(i)));
						tempRow.add((String)(jComboBox_time.getItemAt(j)));
						tempRow.add(new Staff(specialits.get(k)).getSpectType());
						tempRow.add(new Staff(specialits.get(k)).getStaffName());
						tempRow.add(new Staff(specialits.get(k)).getStaffGender());
						tempRow.add(specialits.get(k));
						bookSpectRow.add(tempRow);
					} 
				}
			}
		}
		SwingUtilities.updateComponentTreeUI(jTable_spectbook);
		}

	/**
	 * refresh the changing table
	 */
	public void refreshChanging_Table(String date, String timePeriod, String spectType){
		changeBookRow.clear();

		Vector<Appointment> tempApp = UserInterface.getCurrentApp();
		for (int i=0; i<tempApp.size(); i++){
			boolean appFlag = false;
			if (tempApp.get(i).getMemberID().equals(Member_Panel.getCurrentMemberID()) &&
				((String)(jComboBox_date.getItemAt(1))).compareTo(tempApp.get(i).getData())<=0 ) {
				appFlag = true; }
			
			if (appFlag && (!date.equals("ALL"))) {
				if (!(tempApp.get(i).getData()).equals(date)) appFlag = false;
			}
			
			if (appFlag && (!timePeriod.equals("ALL"))) {
				if (!(tempApp.get(i).getTimePeriod()).equals(timePeriod)) appFlag = false;
			}
			
			if (appFlag && (!spectType.equals("ALL"))) {
				if (!(new Staff(tempApp.get(i).getSpectID()).getSpectType()).equals(spectType)) appFlag = false;
			}
			if (appFlag) {
				Vector<String> tempRow = new Vector<String>();
				tempRow.add(tempApp.get(i).getData());
				tempRow.add(tempApp.get(i).getTimePeriod());
				tempRow.add(tempApp.get(i).getMemberID());
				tempRow.add(new Staff(tempApp.get(i).getSpectID()).getSpectType());
				tempRow.add(new Staff(tempApp.get(i).getSpectID()).getStaffName());
				tempRow.add(tempApp.get(i).getRoomNo());
		
				changeBookRow.add(tempRow);
			}
		}
		SwingUtilities.updateComponentTreeUI(jTable_changebook);
	}

	/**
	 * refresh the Room_Table
	 */
	public void refreshRoom_Table(String date, String timePeriod){
		bookRoomRow.clear();
		HandleAppointment dealApp = new HandleAppointment();
		Vector<String> rooms = new Room().getRoom();
	
			for (int k=0; k<rooms.size();k++){
				if (dealApp.isRoomAvailable(date, timePeriod, rooms.get(k))){
					Vector<String> tempRow = new Vector<String>();
					tempRow.add(rooms.get(k));
					tempRow.add(date);
					tempRow.add(timePeriod);
					bookRoomRow.add(tempRow);
				}
			}
			SwingUtilities.updateComponentTreeUI(jTable_room);
	}
} 