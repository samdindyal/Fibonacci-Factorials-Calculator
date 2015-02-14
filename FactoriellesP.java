/**
	Title:	 The "FactoriellesP" class.
	Date Written: March 2014
	Written By:	Sam Dindyal
	Description: A panel which displays factorials values from 0 to 16 inclusive. It uses a GridBagLayout and transparency
				 for a nice, clean look.
*/

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.awt.ComponentOrientation;
import java.awt.GridBagConstraints;


public class FactoriellesP extends JPanel implements ActionListener
{

	private String[] optionsA;
	private JTextField sortie;
	private JComboBox options;
	private JLabel titre;

/**
	Creates a "FactoriellesP" object.

	@param	width 	The width of the panel.
	@param 	height	The height of the panel.
*/
	public FactoriellesP(int width, int height)
	{
		setSize(width, height);
		setLayout(new GridBagLayout());
		setOpaque(false);

		GridBagConstraints c = new GridBagConstraints();

		//Prepare and add all elements to the panel using a GridBagLayout
		titre = new JLabel ("Factorielles", JLabel.CENTER);
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

		//Generates the text for all elements in the options JComboBox for user input
		optionsA = new String[17];
		for (int i = 0; i<= 16; i++)
			optionsA[i] = i+"!";
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
		c.weighty = 1.0;
		c.gridwidth = 3;
		c.ipadx = getWidth();

		add(sortie, c);

		options.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) 
	{
		//Calculate the factorial value based on user input
		if (e.getSource() == options)
			try{
			sortie.setText(Factorielles.calculateurFactorielle(options.getSelectedIndex())+ "");
		}catch(java.io.IOException ex){}
	}
}