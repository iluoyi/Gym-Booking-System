import java.awt.*;
import javax.swing.*;

/**
 * This class generates the interface for nutritionist or trainer
 */
public class Spectialist_UI extends UserInterface {

	private static final long serialVersionUID = 1L;
	protected JPanel appointment_Panel = null;
	protected JPanel dairy_Panel = null;

	/**
	 * This method initializes jTabbedPane
	 *
	 * @return javax.swing.JTabbedPane
	 */
	public JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setPreferredSize(new Dimension(780, 530));
			jTabbedPane.addTab("Appointment", null, getAppointment_Panel(), null);
			jTabbedPane.addTab("Dairy", null, getDairy_Panel(), null);
		}
		return jTabbedPane;
	}

	/**
	 * This method initializes Member_Panel
	 *
	 * @return javax.swing.JPanel
	 */
	protected JPanel getAppointment_Panel() {
		if (appointment_Panel == null) {
			appointment_Panel = new Appointment_Panel();
		}
		return appointment_Panel;
	}

	/**
	 * This method initializes Records_Panel
	 *
	 * @return javax.swing.JPanel
	 */
	protected JPanel getDairy_Panel() {
		if (dairy_Panel == null) {
			dairy_Panel = new Dairy_Panel();
		}
		return dairy_Panel;
	}

	/**
	 * This is the default constructor
	 */
	public Spectialist_UI() {
		super();
		initialize();
	}

}