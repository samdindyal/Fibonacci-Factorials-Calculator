/**
	Title:	The "interfactUtilisateur" class 
	Date Written	March 2014
	Written By:	Sam Dindyal
	Description:	An "interfaceUtilisateur" object, translated to "User Interface". This user interface calculates numbers 
                    of the Fibonacci Sequence and factorials in a clean and elegant user interface.
*/

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Color;


public class interfaceUtilisateur extends JFrame implements ActionListener
{
	private Fond fond;
	private JPanel p[] = new JPanel[2], controls;
	private JRadioButton options[] = new JRadioButton[2]; 
	private ButtonGroup b;

/**
	Creates an "interfaceUtilisatuer" object.
*/
	public interfaceUtilisateur()
	{
		setLayout(null);
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		p[1] = new FibonacciP(getWidth(), getHeight());
		p[0] = new FactoriellesP(getWidth(), getHeight());
		
		//Create background panel to fill entire frame and set it as the content pane
		fond = new Fond(getWidth(), getHeight());
		setContentPane(fond);

		//Add panels for factorial and fibonacci sequence calculation to the frame
		add(p[0]);
		add(p[1]);
		p[1].setVisible(false);
		add(controls());

		
		controls.setLocation(0, 
					getHeight()-(int)controls.getHeight()-25);
		setTitle("Factorielles");
		options[0].setBounds(0, 0, 100, 30);

		setLocationRelativeTo(null);

		setVisible(true);
	}

/**
	Creates a JPanel which toggles the Factorials and Fibonacci calculation panels on the frame.

	@return		A JPanel holding the JRadioButtons used to toggle between panels. 
*/
	public JPanel controls()
	{
		controls = new JPanel();
		
		controls.setSize(getWidth(), 30);

		options[0] = new JRadioButton("Factorielles");
		options[1] = new JRadioButton("Fibonacci");

		b = new ButtonGroup();
		for (int i = 0; i<2; i++)
		{
			b.add(options[i]);
			controls.add(options[i]);
			options[i].setForeground(Color.WHITE);
			options[i].setFont(new Font("Arial", Font.BOLD, 14));
			options[i].addActionListener(this);
		}
		controls.setBackground(new Color(0, 0, 0, 85));
		options[0].setSelected(true);
		return controls;

	}

/**
	Deals with input from the user.
*/
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == options[0])
		{
			if (!p[0].isVisible())
				p[0].setVisible(true);
			if (p[1].isVisible())
				p[1].setVisible(false);
			setTitle("Factorielles");
		}
		if (e.getSource() == options[1])
		{
			if (!p[1].isVisible())
				p[1].setVisible(true);
			if (p[0].isVisible())
				p[0].setVisible(false);
			setTitle("Fibonacci");
		}
	}

	public static void main (String[] args)
	{
		//Creates and runs the "interfaceUtilisateur"
		new interfaceUtilisateur();
	}
}