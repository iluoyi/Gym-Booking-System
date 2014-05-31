import java.awt.*;
import javax.swing.*;

/**
 * This class generates the interface for Receptionist
 * @author royal (luoyi.zxx@gmail.com)
 */
public class Receptionist_UI extends UserInterface {

	private static final long serialVersionUID = 1L;
	private JPanel member_Panel = null;
	private JPanel booking_Panel = null;
	private JPanel records_Panel = null;
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
		}
		return jTabbedPane;
	}
	/**
	 * This method initializes Member_Panel
	 *
	 * @return javax.swing.JPanel
	 */
	public JPanel getMember_Panel() {
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
	public JPanel getBooking_Panel() {
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
	public JPanel getRecords_Panel() {
		if (records_Panel == null) {
			records_Panel = new Records_Panel();
		}
		return records_Panel;
	}

	/**
	 * This is the default constructor
	 */
	public Receptionist_UI() {
		super();
		initialize();
	}
}