import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;

/**
 * This is the super interface for all the UI class of specialists
 * @author royal (luoyi.zxx@gmail.com)
 */
public class UserInterface extends JFrame{

	private static final long serialVersionUID = 1L;
	JTabbedPane jTabbedPane = null;
	StatusBar statusBar = null;
	private static Vector<Appointment> currentApp = new Vector<Appointment>();// save all the appointments
	private static String userID = new String("");
	
	/**
	 * To set current user ID
	 * @param string The user's ID number
	 */
	public void setUserID(String string){
		userID = new String(string);
	}
	/**
	 * To get current user ID
	 * @return current user ID
	 */
	public static String getUserID(){
		return userID;
	}
	/**
	 * To set current list of all the appointments
	 * @param temp The list of current appointments that will be saved in the system
	 */
	public void setCurrentApp(Vector<Appointment> temp){
		currentApp = temp;
	}
	/**
	 * To get the list of all the appointments
	 * @return the list of all the appointments
	 */
	public static Vector<Appointment> getCurrentApp(){	
		return currentApp;
	}

	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	public JPanel getJContentPane() {
		JPanel jContentPane = null;
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			jContentPane.setFont(new Font("Dialog", Font.PLAIN, 12));
			jContentPane.setPreferredSize(new Dimension(800, 600));
			jContentPane.add(getJTabbedPane(), BorderLayout.CENTER);
			jContentPane.add(getStatusBar(), BorderLayout.SOUTH);
		return jContentPane;
	}

	/**
	 * This method initializes jTabbedPane
	 *
	 * @return javax.swing.JTabbedPane
	 */
	public JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setPreferredSize(new Dimension(780, 530));
		}	
		return jTabbedPane;
	}

	/**
	 * This method initializes StatusBar
	 * @return the statusBar
	 */
	public StatusBar getStatusBar() {
		statusBar = new StatusBar();
		return statusBar;
	}

	/**
	 * This is the default constructor
	 */
	public UserInterface() {
		super();
		initialize();
		
		statusBar.getJbt_Logout().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			dispose();			
	}
		});
	}
	/**
	 * This method initializes this
	 *
	 * 
	 */
	public void initialize() {
		this.setLocationRelativeTo(null);//in the center place
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(900, 600);
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("Gym Booking System");
		this.setVisible(false);
	}
}