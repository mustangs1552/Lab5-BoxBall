import java.awt.Dimension;
import java.util.ArrayList;

/**
 * The main object that draws the square borders, instantiates the balls, and tells the balls to move
 * 
 * @author Matthew George 
 * @version 1
 * @date 10/25/15
 */
public class BoxBounce
{
    private Canvas canvas;
    final int OFFSET;
    private ArrayList<BoxBall> balls = new ArrayList<BoxBall>();
    private int maxBalls;
    private int maxTicks;
    
    // Constructor
    public BoxBounce(int canvasX, int canvasY, int offset, int ballCount, int timeLength)
    {
        canvas = new Canvas("Bouncing Balls", canvasX, canvasY);
        maxBalls = ballCount;
        OFFSET = offset;
        maxTicks = timeLength;
        
        DrawBorders();
        DrawBalls();
    }
    
    // Draw the square borders
    private void DrawBorders()
    {
		// Erase the canvas before re-drawing
		//canvas.erase();
		
		// Get size of canvas
        Dimension cSize = canvas.getSize();
        
         // Draw the lines to make a square
        // Top
        canvas.drawLine(OFFSET, OFFSET, cSize.width - OFFSET, OFFSET);
        // Bottom
        canvas.drawLine(OFFSET, cSize.height - OFFSET, cSize.width - OFFSET, cSize.height - OFFSET);
        // Left
        canvas.drawLine(OFFSET, OFFSET, OFFSET, cSize.height - OFFSET);
        // Right
        canvas.drawLine(cSize.width - OFFSET, OFFSET, cSize.width - OFFSET, cSize.height - OFFSET);
	}
	// Create the balls with the count specified by the user in the constructor
	private void DrawBalls()
	{
		for(int i = 0; i < maxBalls; i++) balls.add(new BoxBall(OFFSET, canvas));
	}
	
	// Tell each ball to move and re-draw borders each iteration, stop after a certain amount of ticks specified by the user in the constructor
	public void Move()
	{
		int currTickCount = 0;
		while(currTickCount <= maxTicks)
		{
			canvas.wait(50);
			
			// Move the balls
			for(int i = 0; i < maxBalls; i++) balls.get(i).Move();
			
			// Increment ticks
			currTickCount++;
			
			// Re-draw borders
			DrawBorders();
		}
	}
}
