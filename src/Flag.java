
import java.awt.*;
import javax.swing.*;

public class Flag {

	/**
	 * Creates a new FlagFrame and displays it
	 * @param args unused parameter
	 */
	public static void main(String[] args) {

		FlagFrame f = new FlagFrame();
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		f.setTitle("U.S. Flag");
		f.setVisible(true);

	}

}

class FlagFrame extends JFrame {

	private static final Color OLD_GLORY_RED = new Color(0xB22234);
	private static final Color OLD_GLORY_BLUE = new Color(0x3C3B6E);

	//Ratios of various components of the flag with respect to the flag height
	private static final double FLAG_HEIGHT = 1.0; 
	private static final double FLAG_WIDTH = 1.9; 
	private static final double UNION_WIDTH = .76;
	private static final double UNION_HEIGHT = 7.0 / 13.0;
	private static final double STAR_Y_OFFSET = .054;
	private static final double STAR_X_OFFSET = .063;
	private static final double STAR_DIAMETER = .0616;
	private static final double STRIPE_HEIGHT = 1.0 / 13.0;

	public FlagFrame() {

		setSize(1140, 600);

	}

	/**
	 * Obtains the window width and height and determines the initial width offset, initial height
	 * offset, the actual width, and actual height based off of this by accounting for the amount of
	 * space taken up by the window borders. The method then determines whether the actual width of
	 * the window is less than the theoretical width it should be (calculated by multiplying the flag
	 * width ratio and the window's actual height) or not, and resizes the actual width and height
	 * accordingly to compensate. Finally, the paint method calls the various draw methods to draw the
	 * components of the flag, passing the necessary variables, after they have been appropriately
	 * modified, to the other methods.
	 */
	public void paint(Graphics g) {

		int windowWidth = getWidth();
		int windowHeight = getHeight();

		/* The initial width and height offsets determine how far towards the left from the right edge
		 * and how far towards the bottom from the top to begin drawing the flag components and are
		 * calculated by respectively subtracting the width/height of the window without borders from
		 * the width/height of the window including borders.
		 */
		int initialWidthOffset = windowWidth - getContentPane().getWidth();
		int initialHeightOffset = windowHeight - getContentPane().getHeight();

		/* The actual width and height indicate the actual amount of space available to draw the flag.
		 * These are calculated by subtracting respectively subtracting the initial width/height
		 * offsets from the width/height of the window without borders.
		 */
		int actualWidth = getContentPane().getWidth() - initialWidthOffset;
		int actualHeight = getContentPane().getHeight() - initialHeightOffset;

		/* Scales down the actual height if the actual width is too small in relation to the height.
		 * Scales down the actual width if the width is too big in relation to the height. Otherwise,
		 * the actual width and actual height are kept the same if they are in the correct ratios.
		 */
		if(actualWidth < actualHeight * FLAG_WIDTH) {

			actualHeight = (int)(actualWidth / FLAG_WIDTH);

		} else {

			actualWidth = (int)(actualHeight * FLAG_WIDTH);

		}

		drawBackground(g, windowWidth, windowHeight);
		drawStripes(g, initialWidthOffset, initialHeightOffset, actualWidth, actualHeight);
		drawUnion(g, initialWidthOffset, initialHeightOffset, actualWidth, actualHeight);
		drawStars(g, initialWidthOffset, initialHeightOffset, actualWidth, actualHeight);

	}

	/**
	 * Draws the background behind the flag
	 * @param g The graphics component on which to draw the background
	 * @param windowWidth The window width of the window including the frame border
	 * @param windowHeight The window height of the window including the frame border
	 */
	public void drawBackground(Graphics g, int windowWidth, int windowHeight) {

		//Background color used is the default background color
		Color backgroundColor = getContentPane().getBackground();

		g.setColor(backgroundColor);

		g.fillRect(0, 0, windowWidth, windowHeight);

	}

	/**
	 * Draws the stripes of the flag using a for loop to draw each stripe
	 * @param g The graphics component on which to draw the background
	 * @param initialWidthOffset The initial width offset towards the right from the left side of the frame
	 * @param initialHeightOffset The initial height offset towards the bottom from the top of the frame
	 * @param actualWidth The actual width of the window, not including the frame border
	 * @param actualHeight The actual height of the window, not including the frame border
	 */
	public void drawStripes(Graphics g, int initialWidthOffset, int initialHeightOffset, int actualWidth, int actualHeight) {

		for(int i = 0; i < 13; i++) {

			/* Starts with the first stripe as red and utilizes an ternary operator to create a branch
			 * to alternate between white and red for every stripe after.
			 */
			g.setColor(((i % 2) == 0) ? OLD_GLORY_RED : Color.WHITE);

			/* Draws a rectangle positioned horizontally according to the initial width offset, with
			 * width equal to the entire actual width of the flag. The rectangle is positioned
			 * vertically according to the initial height offset with height equal to the stripe
			 * height in proportion to the actual height. The starting vertical position is
			 * incremented by 1/13ths of the actual height for each iteration of the for loop, which is
			 * also equal to the individual height of each stripe.
			 */
			g.fillRect(initialWidthOffset, initialHeightOffset + (int)(actualHeight / 13 * i), actualWidth, (int)(actualHeight * STRIPE_HEIGHT));

		}

	}

	/**
	 * Draws the blue union rectangle corner using a for loop to align the union with the stripes
	 * @param g The graphics component on which to draw the union
	 * @param initialWidthOffset The initial width offset towards the right from the left side of the frame
	 * @param initialHeightOffset The initial height offset towards the bottom from the top of the frame
	 * @param actualWidth The actual width of the window, not including the frame border
	 * @param actualHeight The actual height of the window, not including the frame border
	 */
	public void drawUnion(Graphics g, int initialWidthOffset, int initialHeightOffset, int actualWidth, int actualHeight) {

		g.setColor(OLD_GLORY_BLUE);

		/* Drawing the union is divided into 7 individual rectangles that are the same height as each
		 * stripe so that the stripes that make up the union are aligned with the regular stripes of
		 * the flag.
		 */
		for(int i = 0; i < 7; i++) {

			/* The width of each rectangle is equal to the union width in proportion to the a
			 * actual height. See prior documentation in the drawStripes method for more info about
			 * the other arguments.
			 */
			g.fillRect(initialWidthOffset, initialHeightOffset + (int)(actualHeight / 13 * i), (int)(actualHeight * UNION_WIDTH), (int)(actualHeight * STRIPE_HEIGHT));

		}

	}

	/**
	 * Draws the stars on the flag. Two integer arrays are used to store the x-coordinates and
	 * y-coordinates of a star (a total of 10 points for each of the 10 vertices). The outer radius is
	 * then calculated by multiplying the actual height by the star diameter ratio then dividing by 2.
	 * The inner radius is then calculated using trigonometric relationships between the first point
	 * (outer right most point) and the point directly to the left of it (the inner right most point),
	 * based on the assumption that the y-coordinates of these points are the same. The method then
	 * uses a series of for loops to draw the stars, drawing them row by row and 1 by 1 across each
	 * row. The outer most for loop increments each row while also determining the number of stars in
	 * each row and the initial x offset for the first star, both of which vary depending on the row
	 * number. The intermediate for loop increments each column while also determining the X and Y 
	 * coordinate centers for each star. The inner most for loop generates the x and y coordinates for
	 * each of the 10 vertices for each star and stores them in their respective arrays to be used by
	 * the fillPolygon method to draw each star.
	 * @param g The graphics component on which to draw the stars
	 * @param initialWidthOffset The initial width offset towards the right from the left side of the frame
	 * @param initialHeightOffset The initial height offset towards the bottom from the top of the frame
	 * @param actualWidth The actual width of the window, not including the frame border
	 * @param actualHeight The actual height of the window, not including the frame border
	 */
	public void drawStars(Graphics g, int initialWidthOffset, int initialHeightOffset, int actualWidth, int actualHeight) {

		g.setColor(Color.WHITE);

		int[] xPoints = new int[10];
		int[] yPoints = new int[10];

		//outer radius
		double r1 = actualHeight * (STAR_DIAMETER / 2);

		//inner radius
		double r2 = r1 * Math.sin(Math.toRadians(18)) / Math.sin(Math.toRadians(54));

		for(int row = 0; row < 9; row++) {

			/* The number of stars per row initially begin at 6 for the first row and alternate between
			 * 5 and 6 for each successive row.
			 */
			int starsPerRow = (row % 2 == 0) ? 6 : 5;

			/* The initial star x offset is initially 1 for the first row, but alternates between 2
			 * and 1 for each successive row.
			 */
			int initialStarXOffset = (row % 2 == 0) ? 1 : 2;


			for(int col = 0; col < starsPerRow; col++) {

				/* The x-coordinate center is calculated based on the initial width offset and the star x 
				 * offset proportional to the actual height, in addition to the initial star x offset and the
				 * column number. The x-coordinate center is incremented by the 2 times the star x offset in
				 * proportion to the actual height for each iteration of the intermediate for loop. The
				 * y-coordinate center is calculated based on the initial height offset and the star y offset
				 * proportional to the actual height, in addition to the row number. The y-coordinate center is
				 * incremented by the star y offset in proportion to the actual height for each iteration of the
				 * outer most for loop.
				 */
				int starCenterX = (initialWidthOffset) + (int)((actualHeight * STAR_X_OFFSET) * (initialStarXOffset + 2 * col));
				int starCenterY = (initialHeightOffset) + (int)((actualHeight * STAR_Y_OFFSET) * (1 + row));

				for(int i = 0; i < 10; i++) {

					/* Assigns different x and y coordinates for each of the 10 points on a star depending on
					 * whether the point is an even/outer point (i % 2) == 0, or an odd/inner point. Points are
					 * calculated based on the center x and y coordinates and with the trigonometric sin and cos
					 * functions. The angle measure is incremented by 36 degrees for each iteration of the for loop, for
					 * use in the cos and sin functions.
					 */
					xPoints[i] = (i % 2) == 0 ? starCenterX + (int)(r1 * Math.cos(Math.toRadians(18 + 36 * i))) : starCenterX + (int)(r2 * Math.cos(Math.toRadians(54 + 36 * (i - 1))));
					yPoints[i] = (i % 2) == 0 ? starCenterY - (int)(r1 * Math.sin(Math.toRadians(18 + 36 * i))) : starCenterY - (int)(r2 * Math.sin(Math.toRadians(54 + 36 * (i - 1))));

				}

				g.fillPolygon(xPoints, yPoints, 10);

			}

		}

	}

}
