/**
	Title:	The "Fond" class
	Date Written: March 2014
	Written By:	Sam Dindyal
	Description:	"Fond", translated to "Background" in english. An object which acts as a background panel with animated, multicolored bubbles.
*/

import javax.swing.JComponent;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Fond extends JComponent implements ActionListener
{
	private ArrayList<Bulle> bulles = new ArrayList<Bulle>();
	private Timer timer;

/**
	Creates a "Fond" object.

	@param	width	The width of the panel.
	@param	height	The height of the panel.
*/
	public Fond(int width, int height)
	{
		setSize(width, height);
		setOpaque(true);
		setBackground(Color.DARK_GRAY);

		//Randomly generate a number of bubbles between 0 and 50
		Random random = new Random();
		int amount = random.nextInt(31) +20;
		//Add the random number of bubbles to the panel
		for (int i = 0; i<amount; i++)
			bulles.add(new Bulle(getBounds(), 20, 50, getBackground()));
		//Start timer for animations
		timer = new Timer(50, this);
		timer.start();
	}

	public void paintComponent(Graphics g)
	{
		//Paint all the bubbles based on their location on the panel
		Graphics2D g2 = (Graphics2D)g;
		for (Bulle bulle : bulles)
		{
			bulle.draw(g2);
			repaint();
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == timer)
			for (Bulle bulle : bulles)
				bulle.move();
	}
}