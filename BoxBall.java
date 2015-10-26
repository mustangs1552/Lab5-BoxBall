import java.awt.Color;
import java.util.Random;

/**
 * The ball object which handles the ball's position and finds random values for it's variables (at startup)
 * 
 * @author Matthew George 
 * @version 1
 * @date 10/25/15
 */
public class BoxBall
{
	private int xPos;
	private int yPos;
	private int minSize = 5;
	private int maxSize = 20;
	private int diameter;
	private Color color;
	private final int BORDER_OFFSET;
	private Canvas canvas;
	private boolean goingRight = false;
	private boolean goingUp = true;
	private int xSpd;
	private int ySpd;

	// Constructor
    public BoxBall(int borderOffset, Canvas canvas)
    {
		BORDER_OFFSET = borderOffset;
		this.canvas = canvas;
		
		RandomPosition();
		RandomSpeed();
		RandomDiameter();
		RandomColor();
		
		Draw();
    }
    
	// Find a random diameter within a range
    private void RandomDiameter()
    {
		Random rand = new Random();
		diameter = rand.nextInt((maxSize - minSize) + 1) + minSize;
	}
	// Find a random speed for x and y within a range
	private void RandomSpeed()
	{
		Random rand = new Random();
		
		xSpd = rand.nextInt((7 - 1)) + 1;
		ySpd = rand.nextInt((7 - 1)) + 1;
	}
	// Find a random starting position
	private void RandomPosition()
	{
		Random rand = new Random();
		
		// Set the max position (right and bottom edges)
		int maxPosX = canvas.getSize().width - BORDER_OFFSET - diameter;
		int maxPosY = canvas.getSize().height - BORDER_OFFSET - diameter;
		
		// Get the random number between BORDER_OFFSET and max position
		xPos = rand.nextInt(maxPosX - BORDER_OFFSET);
		yPos = rand.nextInt(maxPosY - BORDER_OFFSET);
		
		// Check to see if within border
		boolean posGood = false;
		while(!posGood)
		{
			// Random number should never exceed right and bottom edges, just check left and top edges
			if(xPos <= BORDER_OFFSET) xPos += BORDER_OFFSET;
			else if(yPos <= BORDER_OFFSET) yPos += BORDER_OFFSET;
			else posGood = true;
		}
	}
	// Find a random color using RGB
	private void RandomColor()
	{
		Random rand = new Random();
		
		// Radnom number between 0 and 200
		int r = rand.nextInt(200);
		int g = rand.nextInt(200);
		int b = rand.nextInt(200);
		
		color = new Color(r, g, b);
	}
	
	// Draw the ball
	private void Draw()
	{
		canvas.setForegroundColor(color);
		canvas.fillCircle(xPos, yPos, diameter);
	}
	// Erase the ball
	private void Erase()
	{
		canvas.eraseCircle(xPos, yPos, diameter);
	}
	
	// Move the ball
	public void Move()
	{
		Erase();
		
		// Find new position
		xPos += xSpd;
		yPos += ySpd;
		
		 // Check if hit a wall
		// Left side
		if(xPos <= BORDER_OFFSET) xSpd *= -1;
		// Right side
		else if(xPos >= canvas.getSize().width - BORDER_OFFSET - diameter) xSpd *= -1;
		// Bottom
		if(yPos >= canvas.getSize().height - BORDER_OFFSET - diameter) ySpd *= -1;
		// Top
		else if(yPos <= BORDER_OFFSET) ySpd *= -1;
		
		Draw();
	}
}
