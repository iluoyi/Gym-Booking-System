import java.awt.*;

import javax.swing.*;

/**
 * This is the interface of manager
 * @author royal (luoyi.zxx@gmail.com)
 */
public class Manager_UI extends UserInterface {

	private static final long serialVersionUID = 1L;
	private JPanel member_Panel = null;
	private Booking_Panel booking_Panel = null;
	private JPanel records_Panel = null;
	private JPanel staff_Panel = null;
	private JPanel room_Panel = null;

	/**
	 * This method initializes jTabbedPane
	 *
	 * @return javax.swing.JTabbedPane
	 */
	public JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setPreferredSize(new Dimension(780, 530));
			jTabbedPane.addTab("Member", null, getMember_Panel(), null);
			jTabbedPane.addTab("Booking", null, getBooking_Panel(), null);
			jTabbedPane.addTab("Records", null, getRecords_Panel(), null);
			jTabbedPane.addTab("Staff", null, getStaff_Panel(), null);
			jTabbedPane.addTab("Room", null, getRoom_Panel(), null);
		}
		return jTabbedPane;
	}

	/**
	 * This method initializes Member_Panel
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getMember_Panel() {
		if (member_Panel == null) {
			member_Panel = new Member_Panel();
		}
		return member_Panel;
	}

	/**
	 * This method initializes Booking_Panel
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getBooking_Panel() {
		if (booking_Panel == null) {
			booking_Panel = new Booking_Panel();
		}
		return booking_Panel;
	}

	/**
	 * This method initializes Records_Panel
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getRecords_Panel() {
		if (records_Panel == null) {
			records_Panel = new Records_Panel();
		}
		return records_Panel;
	}

	/**
	 * This method initializes Staff_Panel
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getStaff_Panel() {
		if (staff_Panel == null) {
			staff_Panel = new Staff_Panel();
		}
		return staff_Panel;
	}

	/**
	 * This method initializes Room_Panel
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getRoom_Panel() {
		if (room_Panel == null) {
			room_Panel = new Room_Panel();
		}
		return room_Panel;
	}

	/**
	 * This is the default constructor
	 */
	public Manager_UI() {
		super();
		initialize();
	}
}
