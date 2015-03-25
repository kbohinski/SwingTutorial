import javax.swing.*; // JFrame
import java.awt.*; // Graphics, Color
import java.awt.event.*; // MouseMotionListener
import java.awt.image.*; // BufferedImage
import java.util.ArrayList; // ArrayList

import util.DynamicColor; // DynamicColor
/**
 * Class that demonstrates use of Images
 */
public class ImageExample extends JFrame implements MouseMotionListener {

	/** Screen dimensions*/
	private final static int SCREEN_WIDTH = 1000, SCREEN_HEIGHT = 700;
	/** Length of the pixel trail*/
	private final static int TRAIL_LENGTH = 50;
	
	private BufferedImage image;
	private ArrayList<Color> colorList;
	private ArrayList<Integer> xList, yList;
	private DynamicColor dColor;
	private boolean write;
	
	public ImageExample() {
		setSize(1000,700);
		setContentPane(new DrawingPanel());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		// Initialize BufferedImage to be screen size
		image = new BufferedImage(SCREEN_WIDTH,SCREEN_HEIGHT,BufferedImage.TYPE_INT_ARGB);
		colorList = new ArrayList<Color>();
		xList = new ArrayList<Integer>();
		yList = new ArrayList<Integer>();
		dColor = new DynamicColor(); // Initialize dynamic color
		
		addMouseMotionListener(this); // Add motion listener
	}
	
	public static void main(String[] args) {
		ImageExample frame = new ImageExample(); // Same as before
		frame.setVisible(true);
	}
	
	/**
	 * Drawing panel in which Graphics is used to draw the image
	 */
	public class DrawingPanel extends JPanel {
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			//---Erase the pixels of the image---//
			
			for (int xIndex = 0; xIndex < image.getWidth(); xIndex++) {
				for (int yIndex = 0; yIndex < image.getHeight(); yIndex++)
					image.setRGB(xIndex, yIndex, Color.BLACK.getRGB());
			}
			
			//---Draw the pixel trail---//
			
			for (int index = 0; index < colorList.size(); index++) {
				int x = xList.get(index),
					y = yList.get(index);
				Color color = colorList.get(index);
				// Trail fades unless mouse is dragging (write == true)
				for (int index0 = 0; index0 < 9; index0++)
					image.setRGB(Math.abs(x+(index0/3)-1)%SCREEN_WIDTH,
								 Math.abs(y+(index0%3)-1)%SCREEN_HEIGHT,
								 new Color((int)(color.getRed()*(write?1:(double)index/(colorList.size()-1))),
										   (int)(color.getGreen()*(write?1:(double)index/(colorList.size()-1))),
										   (int)(color.getBlue()*(write?1:(double)index/(colorList.size()-1)))).getRGB());
			}
			g.drawImage(image,0,0,null); // Draws image starting at (0,0)
		}
		
	}

	/**
	 * Called when the mouse is moved
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		colorList.add(dColor); // Add the dynamic color to the trail
		xList.add(e.getX()); // Add current x position
		yList.add(e.getY()); // Add current y position
		while (colorList.size() > TRAIL_LENGTH) { // Trim trail to correct length
			colorList.remove(0);
			xList.remove(0);
			yList.remove(0);
		}
		write = false; // write is false when moving
		repaint();
	}
	
	/**
	 * Called when the mouse is dragged(moved while pressed)
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		colorList.add(dColor); // Add the dynamic color to the trail
		xList.add(e.getX()); // Add current x position
		yList.add(e.getY()); // Add curent y position
		write = true; // write it true when dragging
		repaint();
	}
	
}
