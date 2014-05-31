import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
/**
 * This is the panel for to view all the records
 * @author royal (luoyi.zxx@gmail.com)
 */
public class Records_Panel extends JPanel {

	private static final long serialVersionUID = 1L;

	Vector<String> spectColumn = new Vector<String>();
	Vector<Vector<String>> spectRow = new Vector<Vector<String>>();
	Vector<String> memberColumn = new Vector<String>();
	Vector<Vector<String>> memberRow = new Vector<Vector<String>>();
	Vector<String> roomColumn = new Vector<String>();
	Vector<Vector<String>> roomRow = new Vector<Vector<String>>();
	private JButton jButton_spect = null;
	private JButton jButton_member = null;
	private JButton jButton_room = null;
	private JTable jTable_spect = null;
	private JTable jTable_member = null;
	private JTable jTable_room = null;
	
	private JPanel jPanel_table = null;
	private JScrollPane jScrollPane_table_spect = null;
	private JScrollPane jScrollPane_table_member = null;
	private JScrollPane jScrollPane_table_room = null;
	private CardLayout c = new CardLayout();

	/**
	 * This is the default constructor
	 */
	public Records_Panel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setLayout(new BorderLayout());
		this.setBounds(new Rectangle(0, 0, 780, 530));
		this.add(getJPanel_table(), BorderLayout.CENTER);
		this.add(getJPanel_button(), BorderLayout.EAST);
	}

	/**
	 * This method initializes getJPanel_table_spect
	 *
	 * @return javax.swing.JPanel
	 */
	private JScrollPane getJPanel_table_spect() {
		if (jScrollPane_table_spect == null) {
			jScrollPane_table_spect = new JScrollPane();
			jScrollPane_table_spect.setName("jScrollPane_table_spect");
			jScrollPane_table_spect.setViewportView(getJTable_spect());
			jScrollPane_table_spect.setFont(new Font("Dialog", Font.PLAIN, 14));
		}
		return jScrollPane_table_spect;
	}
	/**
	 * This method initializes getJPanel_table_member
	 *
	 * @return javax.swing.JPanel
	 */
	private JScrollPane getJPanel_table_member() {
		if (jScrollPane_table_member == null) {
			jScrollPane_table_member = new JScrollPane();
			jScrollPane_table_member.setName("jScrollPane_table_member");
			jScrollPane_table_member.setViewportView(getJTable_member());
			jScrollPane_table_member.setFont(new Font("Dialog", Font.PLAIN, 14));
		}
		return jScrollPane_table_member;
	}
	/**
	 * This method initializes getJPanel_table_room
	 *
	 * @return javax.swing.JPanel
	 */
	private JScrollPane getJPanel_table_room() {
		if (jScrollPane_table_room == null) {
			jScrollPane_table_room = new JScrollPane();
			jScrollPane_table_room.setName("jScrollPane_table_room");
			jScrollPane_table_room.setViewportView(getJTable_room());
			jScrollPane_table_room.setFont(new Font("Dialog", Font.PLAIN, 14));

		}
		return jScrollPane_table_room;
	}

	/**
	 * This method initializes jPanel_button
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel_button() {
			JPanel jPanel_button = null;
			FlowLayout flowLayout1 = new FlowLayout();
			flowLayout1.setHgap(15);
			flowLayout1.setVgap(40);
			jPanel_button = new JPanel();
			jPanel_button.setLayout(flowLayout1);
			jPanel_button.setPreferredSize(new Dimension(200, 530));
			jPanel_button.add(getJButton_spect(), null);
			jPanel_button.add(getJButton_member(), null);
			jPanel_button.add(getJButton_room(), null);
		return jPanel_button;
	}

	/**
	 * This method initializes jButton_new
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButton_spect() {
		if (jButton_spect == null) {
			jButton_spect = new JButton();
			jButton_spect.setText("Spectialist Records");
			jButton_spect.setPreferredSize(new Dimension(173, 28));
			
			// Add listener to the Specialist Records
			jButton_spect.addMouseListener(new MouseAdapter(){
		
				public void mouseClicked(MouseEvent e){
					c.first(jPanel_table);
					//Display booking information
					
					Vector<Appointment> tempApp = UserInterface.getCurrentApp();
					
					spectRow.clear();
					
					Vector<Appointment> tempP = new Vector<Appointment>();
					Vector<Appointment> tempN = new Vector<Appointment>();
					Vector<Appointment> tempT = new Vector<Appointment>();
					
					for (int i=0; i < tempApp.size(); i++){		
						if(tempApp.get(i).getSpectID().charAt(0)=='P'){		
							//Store all the Physiotherapist book to the Vector			
							tempP.add(tempApp.get(i));
						}	
						else if(tempApp.get(i).getSpectID().charAt(0)=='N'){		
							tempN.add(tempApp.get(i));		
						}
						else 		
							tempT.add(tempApp.get(i));	
					}
					for(int j1 = 0; j1 < new StaffAccount().getPhyNum(); j1++){	
						for(int m1 = 0; m1 < tempP.size(); m1++){
							   
							    Vector<String> temp1 = new Vector<String>();	
								if(Integer.parseInt(tempP.get(m1).getSpectID().substring(1))== (j1+1)){
									
									Staff tempSpect = new Staff(tempP.get(m1).getSpectID());
			
									temp1.add(tempSpect.getStaffName());
									
									temp1.add(tempSpect.getSpectType());
									temp1.add(tempP.get(m1).getData());
									temp1.add(tempP.get(m1).getTimePeriod());
									if(tempP.get(m1).getMemberID().equals("000")){
										
									temp1.add("Leave");
										
									}	
									else {
										temp1.add(tempP.get(m1).getMemberID());	
									}
									temp1.add(tempP.get(m1).getRoomNo());
								
									spectRow.add(temp1);
									
									SwingUtilities.updateComponentTreeUI(jTable_spect);		
								}	
						}
						
					}	

					for(int j2 = 0; j2 < new StaffAccount().getNutrNum(); j2++){
						for(int m2 = 0; m2 < tempN.size(); m2++){
								Vector<String> temp1 = new Vector<String>();
								if(Integer.parseInt(tempN.get(m2).getSpectID().substring(1))==(j2+1)){
					
									Staff tempSpect = new Staff(tempN.get(m2).getSpectID());
									
									temp1.add(tempSpect.getStaffName());
									temp1.add(tempSpect.getSpectType());
									temp1.add(tempN.get(m2).getData());
									temp1.add(tempN.get(m2).getTimePeriod());
									if(tempN.get(m2).getMemberID().equals("000"))
										temp1.add("Leave");
									else 
										temp1.add(tempN.get(m2).getMemberID());
									
									temp1.add(tempN.get(m2).getRoomNo());
									
									spectRow.add(temp1);
								
									SwingUtilities.updateComponentTreeUI(jTable_spect);	
								}		
						}
						
					}	
			
					for(int j3 = 0; j3 < new StaffAccount().getTraNum(); j3++){	
						for(int m3 = 0; m3 < tempT.size(); m3++){
								Vector<String> temp1 = new Vector<String>();	
								if(Integer.parseInt(tempT.get(m3).getSpectID().substring(1))== (j3+1)){
									
									Staff tempSpect = new Staff(tempT.get(m3).getSpectID());
									
									temp1.add(tempSpect.getStaffName());
									temp1.add(tempSpect.getSpectType());
									temp1.add(tempT.get(m3).getData());
									temp1.add(tempT.get(m3).getTimePeriod());
									if(tempT.get(m3).getMemberID().equals("000"))
										temp1.add("Leave");
									else 
										temp1.add(tempT.get(m3).getMemberID());
									temp1.add(tempT.get(m3).getRoomNo());
	
									spectRow.add(temp1);
								
									SwingUtilities.updateComponentTreeUI(jTable_spect);
									
								}	
						}
						
					}	
				
				}
			});
		}
		return jButton_spect;
	}
	/**
	 * This method initializes jButton_change
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButton_member() {
		if (jButton_member == null) {
			jButton_member = new JButton();
			jButton_member.setText("Member Records");
			jButton_member.setPreferredSize(new Dimension(173, 28));
			
			jButton_member.addMouseListener(new MouseAdapter(){
				
				public void mouseClicked(MouseEvent e){
					c.first(jPanel_table);
					c.next(jPanel_table);
													
					Vector<Appointment> tempApp = UserInterface.getCurrentApp();
					
					memberRow.clear();
	
					for(int i = 0; i  < new GenerateMemID().getMemNo(); i++){
					
						for(int j = 0; j  < tempApp.size(); j++){
					
						    Vector<String> temp = new Vector<String>();	
						   	
						    if(Integer.parseInt(tempApp.get(j).getMemberID())== (i+1)){
						    	Staff tempSpect = new Staff(tempApp.get(j).getSpectID());
								
								String memName = new Member(tempApp.get(j).getMemberID()).getMemName();
								
								temp.add(tempApp.get(j).getMemberID());
								temp.add(memName);
							    temp.add(tempApp.get(j).getData());
							    temp.add(tempApp.get(j).getTimePeriod());
							    temp.add(tempSpect.getStaffName());
							    temp.add(tempSpect.getSpectType());
								
							    temp.add(tempApp.get(j).getRoomNo());
							    memberRow.add(temp);
							     SwingUtilities.updateComponentTreeUI(jTable_member);
							}
						   
					    }
					}
				}
			
		    });
			
		}	
		return jButton_member;
	}

	/**
	 * This method initializes jButton_roombook
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButton_room() {
		if (jButton_room == null) {
			jButton_room = new JButton();
			jButton_room.setText("Room Records");
			jButton_room.setPreferredSize(new Dimension(173, 28));
			jButton_room.addMouseListener(new MouseAdapter(){
				
				public void mouseClicked(MouseEvent e){
					
					c.last(jPanel_table);
													
					Vector<Appointment> tempApp = UserInterface.getCurrentApp();
					
					roomRow.clear();
					for(int i = 0; i  < new Room().getRoomNo(); i++){
			
						for(int j = 0; j  < tempApp.size(); j++){
			
						    Vector<String> temp = new Vector<String>();	
						   	if(!tempApp.get(j).getRoomNo().equals("NONE")){			   		
						   			Staff tempSpect = new Staff(tempApp.get(j).getSpectID());
								
						   			temp.add(tempApp.get(j).getRoomNo());
						   			temp.add(tempApp.get(j).getData());
						   			temp.add(tempApp.get(j).getTimePeriod());
						   			temp.add(tempSpect.getStaffName());
						   			temp.add(tempApp.get(j).getMemberID());
						
						   			roomRow.add(temp);
						   			SwingUtilities.updateComponentTreeUI(jTable_room);
						   	}
						 }
					}
				}
			
		    });
		}
		return jButton_room;
	}

	/**
	 * This method initializes jTable_spectbook
	 *
	 * @return javax.swing.JTable
	 */
	private JTable getJTable_spect() {

		spectColumn.add("Specialist Name");
		spectColumn.add("Specialist Type");
		spectColumn.add("Booking Date");
		spectColumn.add("Time-period");
		spectColumn.add("Member ID");
		spectColumn.add("Room");

		for (int i=0; i<50; i++){
			Vector<String> row = new Vector<String>();
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			spectRow.add(row);
		}

		if (jTable_spect == null) {
			jTable_spect = new JTable(new AllTableModel(spectRow, spectColumn));
			jTable_spect.setRowHeight(30);
		}
		
		return jTable_spect;
		
	}
	/**
	 * This method initializes getJTable_member
	 *
	 * @return javax.swing.JTable
	 */
	private JTable getJTable_member() {
		memberColumn.add("Member ID");
		memberColumn.add("Member Name");
		memberColumn.add("Booking Date");
		memberColumn.add("Time-period");
		memberColumn.add("Specialist Name");
		memberColumn.add("Specialist Type");
		memberColumn.add("Room");

		for (int i=0; i<50; i++){
			Vector<String> row = new Vector<String>();
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			memberRow.add(row);
		}

		if (jTable_member == null) {
			jTable_member = new JTable(new AllTableModel(memberRow, memberColumn));
			jTable_member.setRowHeight(30);
		}
		return jTable_member;
	}
	/**
	 * This method initializes getJTable_room
	 *
	 * @return javax.swing.JTable
	 */
	private JTable getJTable_room() {
		roomColumn.add("Room");
		roomColumn.add("Booking Date");
		roomColumn.add("Time-period");
		roomColumn.add("Specialist Name");
		roomColumn.add("Member ID");
		for (int i=0; i<50; i++){
			Vector<String> row = new Vector<String>();
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			row.add("");
			roomRow.add(row);
		}		
		if (jTable_room == null) {
			jTable_room = new JTable(new AllTableModel(roomRow, roomColumn));
			jTable_room.setRowHeight(30);
		}
		return jTable_room;
	}

	/**
	 * This method initializes jPanel_table
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel_table() {
		if (jPanel_table == null) {
			jPanel_table = new JPanel();
			jPanel_table.setLayout(c);
			jPanel_table.add(getJPanel_table_spect(), getJPanel_table_spect().getName());
			jPanel_table.add(getJPanel_table_member(), getJPanel_table_member().getName());
			jPanel_table.add(getJPanel_table_room(), getJPanel_table_room().getName());
		}
		return jPanel_table;
	}
}

