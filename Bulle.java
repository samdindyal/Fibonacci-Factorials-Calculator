/**
	Title: The "Bulle" class
	Date Written: March 2014
	Author: Sam Dindyal
	Description: An object called "Bulle", translated to english as "Bubble", which is a moving, circular shape with optional size, color and a bounding box.
*/

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Bulle
{
	private Random random;
	private Rectangle boundingBox;
	private int xDir, yDir, rhLim, lhLim, xPos, yPos, diameter;
	private Color outerColor, innerColor;

/**
	Creates a "Bulle" object.

	@param	boundingBox		The box bounding the shape's movement.
	@param	lhLim 			The left hand limit of the shape's location.
	@param	rhLim			The right hand limit of the shape's location.
	@param	background		The background color of which the shape is intended to be used on.
*/
	public Bulle(Rectangle boundingBox, int lhLim, int rhLim, Color background)
	{
		 random = new java.util.Random();
		 this.boundingBox = boundingBox;
		 this.rhLim = rhLim;
		 this.lhLim = lhLim;

		 diameter = random.nextInt(rhLim+1-lhLim)+lhLim;

		 //Set the coordinates of a shape to a random position inside the bounding box
		 xPos = random.nextInt((int)boundingBox.getWidth()+1-diameter);
		 yPos = random.nextInt((int)boundingBox.getHeight()+1-diameter);
		 	
		 setDirections();

		 //Set the color of the shape as well as a darker color for its inner color
		 innerColor = generateRandomColorwithExclusions(background);
		 outerColor = new Color(innerColor.getRed(), innerColor.getGreen(), innerColor.getBlue(), 150);

	}


/**
	Move the shape in the direction it is currently travelling in.
*/
	public void move()
	{
		if (   (yDir > 0 && yPos >= (int)boundingBox.getHeight()-diameter-1)
			|| (yDir < 0 && yPos <= 1)
			|| (xDir > 0 && xPos >= (int)boundingBox.getWidth()-diameter-1)
			|| (xDir < 0 && xPos <= 1)
			)
			setDirections();

		xPos+=xDir;
		yPos+=xDir;

	}

/**
	Randomize the direction the shape is traveling in in the X and Y domains.
*/
	public void setDirections()
	{
		xDir = 0;
		yDir = 0;
		while (xDir == 0 || yDir == 0)
		{
			xDir = random.nextInt(3);
			int factor = random.nextInt(2)+1;
			 if (factor%2==1)
			 {
			 	xDir*=-1;
			 	yDir*=1;
			 }
			yDir = random.nextInt(3);
			factor = random.nextInt(2)+1;
			if (factor%2==1)
			{
				yDir*=1;
				xDir*=-1;
			}
		}

	}

/**
	Draw the shape onto the supplied "g2" Graphics2D object.

	@param	g2 		Graphics2D to paint onto.
*/
	public void draw (Graphics2D g2)
	{
		g2.setColor(outerColor);
		g2.fill(new Ellipse2D.Double(xPos, yPos, diameter, diameter));
	}

/**
	Generates a random color with RGB values while keeping a distance away from another.

	@param	exclude		Color to keep a distance from.
	@return 			The generated color given the the color to keep a distance from.
*/
	public Color generateRandomColorwithExclusions(Color exclude)
	{
		int r=0, g=0, b=0; 
		//Randomize RGB values
		r = random.nextInt(256);
		g = random.nextInt(256);
		b = random.nextInt(256);
		//Fetch RGB values of color to be kept away from
		int eR = exclude.getRed(); 
		int eG = exclude.getGreen(); 
		int eB =exclude.getBlue();
		//Define the threshold or distance from the color as 50
		int d = 50;

		//Until all RGB values are a distance away from the exlcusion color, keep randomizing.
		while(Math.abs(r-eR)<d && Math.abs(g-eG)<d && Math.abs(b-eB)<d)
		{
			if (Math.abs(r-eR)<d)
				r = random.nextInt(256);
			if 	(Math.abs(g-eG)<d)
				g = random.nextInt(256);
			if (Math.abs(b-eB)<d)
				b = random.nextInt(256);
		}
		return new Color(r, g, b);
	}
}