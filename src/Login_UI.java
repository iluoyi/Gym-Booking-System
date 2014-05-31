import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This is the interface of login process
 * @author royal (luoyi.zxx@gmail.com)
 */
public class Login_UI extends JFrame{

	private static final long serialVersionUID = 1L;
	String identity = new String("");
	public static JTextField userID = new JTextField("");
	JPasswordField password = new JPasswordField(10);
	JButton loginButton = new JButton("Login");
	private JButton exitButton = new JButton("Exit");
	JRadioButton[] identities = new JRadioButton[5];
	
	/**
	 * The default constructor
	 */
	public Login_UI(){
		setTitle("Welcome");
		setSize(900,560);
		setLocationRelativeTo(null);
		setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		Container container=getContentPane();
		container.setBackground(Color.WHITE);
		container.setLayout(null);

		//Put in the welcome title
		JLabel title = new JLabel("Gym Booking System");
		Font titleFont = new Font("Arial", Font.PLAIN, 40 );
		title.setBounds(250, 50, 400, 100);
		title.setFont(titleFont);
		container.add(title);

		//Put in the version information
		JLabel version = new JLabel("Version 2.1");
		Font versionFont = new Font("Arial", Font.PLAIN, 15 );
		version.setBounds(600, 80, 200, 100);
		version.setFont(versionFont);
		container.add(version);

		//put in the RadioButtons of identities
		identities[0] = new JRadioButton("Manager");
		identities[1] = new JRadioButton("Receptionist");
		identities[2] = new JRadioButton("Trainer");
		identities[3] = new JRadioButton("Nutritionist");
		identities[4] = new JRadioButton("Physiotherapist");
		ButtonGroup group = new ButtonGroup();
		Font radioButtonFont = new Font("Arial",Font.PLAIN,13);

		for (int i = 0;i<5;i++){
			identities[i].setBounds(150+i*120,200,120,30);
			identities[i].setBackground(Color.WHITE);
			identities[i].setFont(radioButtonFont);
			container.add(identities[i]);
			group.add(identities[i]);
			identities[i].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					identity = e.getActionCommand();
				}
			});
		}

		//Put in the input text field of userID and password
		Font inputLabel = new Font("Arial", Font.BOLD, 20 );
		JLabel userLabel = new JLabel("User ID");
		JLabel passwordLabel = new JLabel("Password");
		userLabel.setFont(inputLabel);
		passwordLabel.setFont(inputLabel);
		userLabel.setBounds(300, 300, 150, 30);
		passwordLabel.setBounds(300, 350, 150, 30);
		userID.setBounds(450, 300,150,30);
		password.setBounds(450, 350,150,30);
		container.add(userLabel);
		container.add(passwordLabel);
		container.add(userID);
		container.add(password);

		//put in the "login" button and "exit" button
		loginButton.setBounds(200, 420,150,30);
		exitButton.setBounds(550, 420,150,30);
		container.add(loginButton);
		container.add(exitButton);

		exitButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				System.exit(0);
			}
		});
		
		}

}

