/**
	Title"	The "FibonacciP" class.
	Date Written: March 2014
	Written By:	Sam Dindyal
	Description: A panel which displays the Fibonacci sequence values from indices 0-45 inclusive.
				 It uses a GridBagLayout and translucency along with a JProgressBar to give it a nice, clean look.
*/

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JProgressBar;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.awt.ComponentOrientation;
import java.awt.GridBagConstraints;


public class FibonacciP extends JPanel implements ActionListener
{

	private String[] optionsA;
	private JTextField sortie;
	private JComboBox options;
	private JLabel titre;
	private int counter =0;
	private JProgressBar loading;
	private JPanel l;

/**
	Creates a "FibonacciP" object.

	@param	width 	The width of the panel.
	@param 	height 	The height of the panel.
*/
	public FibonacciP(int width, int height)
	{
		setSize(width, height);
		setLayout(new GridBagLayout());
		setOpaque(false);

		GridBagConstraints c = new GridBagConstraints();

		//Adds elements to the panel using a GridBagLayout and GridBagConstraints
		titre = new JLabel ("Fibonacci", JLabel.CENTER);
		//Giving the "titre" JPanel a background and making it translucent
		titre.setOpaque(true);
		titre.setBackground(new Color(0, 0, 0, 85));
		titre.setFont(new Font("Arial", Font.BOLD, 48));
		titre.setForeground(Color.WHITE);
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 0.5;
		c.anchor = GridBagConstraints.PAGE_START;
		c.gridwidth = 3;
		c.ipadx = getWidth();
		add(titre, c);

		//Generates the options of the maximum allowed value, without an overflow, that is
		optionsA = new String[46];
		for (int i = 0; i<= 45; i++)
			optionsA[i] = "a" + generateurIndice(i);
		options = new JComboBox(optionsA);
		((JLabel)options.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		c.ipadx = 0;
		c.gridx = 2;
		c.gridy = 1;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		add(options, c);

		sortie = new JTextField("1");
		sortie.setHorizontalAlignment(JLabel.CENTER);
		sortie.setEditable(false);
		sortie.setFocusable(false);
		sortie.setBackground(new Color(0, 0, 0, 85));
		sortie.setFont(new Font("Arial", Font.BOLD, 48));
		sortie.setForeground(Color.WHITE);
		c.anchor = GridBagConstraints.PAGE_START;
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1;
		c.weighty = 0;
		c.gridwidth = 3;
		c.ipadx = getWidth();
		add(sortie, c);

		l = new JPanel();
		l.setOpaque(false);
		loading = new JProgressBar();
		loading.setIndeterminate(true);
		loading.setVisible(false);
		l.setSize(getWidth(), (int)loading.getPreferredSize().getHeight());
		l.add(loading);
		c.anchor = GridBagConstraints.PAGE_START;
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 3;
		c.ipadx = getWidth();
		add(l, c);

		options.addActionListener(this);

	}

/**
	Generates a subscript for an index value, i, and concatenates it with "a".

	@param	i 	Index to generate string with subscript for.
	@return		Concatenated a with subscript of i.
*/
	public String generateurIndice(int i)
	{
		if (i <10)
		{
			if (i == 0)return "\u2080";
			else if (i == 1)return "\u2081";
			else if (i == 2)return "\u2082";
			else if (i == 3)return "\u2083";
			else if (i == 4)return "\u2084";
			else if (i == 5)return "\u2085";
			else if (i == 6)return "\u2086";
			else if (i == 7)return "\u2087";
			else if (i == 8)return "\u2088";
			else if (i == 9)return "\u2089";
		}
		String out = "";
		char[] c = (i+"").toCharArray();
		for (char indice : c)
			out+= generateurIndice(Integer.parseInt(indice+""));
		return out;
	}


/**
	Recursively calculate a value of the Fibonacci sequnce with an index.

	@param	valeur 		The index of the Fibonacci sequence number to be calculated.
	@return 	 		The calculated value of the Fibonacci sequence number based on the given index.
*/
	public static int calculateurFibonacci(int valeur)
	{
	    if(valeur == 1 || valeur == 0)
	      return 1;
	   else
	      return calculateurFibonacci(valeur - 1) + calculateurFibonacci(valeur - 2);
	}

	public void actionPerformed(ActionEvent e) 
	{
		//Calculates a number in the Fibonacci sequence. The index is defined by the user input.
		if (e.getSource() == options)
		{
			sortie.setFont(new Font("Arial", Font.BOLD, 24));
			sortie.setText("Attendez Veuillez.");
			//Calculates the value recursively on a separate thread so that the animations can continue on without interruption
			 Thread thread = new Thread(new Runnable(){
   				public void run()
   				{
   					loading.setVisible(true);
					sortie.setText(calculateurFibonacci(options.getSelectedIndex())+ "");
					sortie.setFont(new Font("Arial", Font.BOLD, 48));
					loading.setVisible(false);
				}
			});
			thread.start();
			
		}
		
	}
}