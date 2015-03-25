import javax.swing.*; // JFrame, JComponents
import java.awt.*; // JPanels, Graphics
import java.awt.event.*; // ActionListener, MouseListener, MouseMotionListener

/**
 * Class that demonstrates use of Graphics
 */
public class GraphicsExample extends JFrame implements ActionListener, MouseListener, MouseMotionListener {

	private JButton red, green, blue;
	private Color color;
	private int mouseX, mouseY, x0, y0, x1, y1;
	private boolean drawing;
	
	public GraphicsExample() {
		
		// Sets up JFrame
		setSize(1000,700);
		setContentPane(new DrawingPanel()); // Adds DrawingPanel to the JFrame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		// JComponents: initialize, set bounds, add listeners, add to JFrame
		red = new JButton("RED");
		green = new JButton("GREEN");
		blue = new JButton("BLUE");
		red.setBounds(0,0,100,50);
		green.setBounds(100,0,100,50);
		blue.setBounds(200,0,100,50);
		red.addActionListener(this);
		green.addActionListener(this);
		blue.addActionListener(this);
		add(red);
		add(green);
		add(blue);

		// Add listeners to the JFrame
		addMouseListener(this);
		addMouseMotionListener(this);
		
		repaint(); // Calls the paintComponent method, clearing previous drawings
	}
	
	public static void main(String[] args) {
		GraphicsExample frame = new GraphicsExample(); // Same as before
		frame.setVisible(true);
	}
	
	/**
	 * Drawing panel in which Graphics is used to draw shapes
	 */
	public class DrawingPanel extends JPanel {
		
		/**
		 * Called every on repaint;
		 */
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			g.drawString(drawing ? "Select second point" : "Select first point", 850, 50);
			
			g.setColor(color); // sets painting color to given Color object
			g.drawLine(x0, y0, x1, y1); // Draws line from (x0,y0) to (x1,y1)
			
			g.setColor(Color.BLACK); // or: g.setColor(new Color(0,0,0));
			
			// Print coordinates and distance
			g.drawString("(" + mouseX + "," + mouseY + ")", mouseX, mouseY);
			g.drawString("    Distance: " + ((int)(Math.sqrt(x0*x0 + x1*x1)*100))/100.0, (x1-x0)/2+x0, (y1-y0)/2+y0);
		}
		
	}

	/**
	 * Called when a JComponent is interacted with
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (((JButton)e.getSource()).getText()) {
			case "RED":
				color = Color.RED;
				break;
			case "GREEN":
				color = Color.GREEN;
				break;
			case "BLUE":
				color = Color.BLUE;
				break;
		}
		repaint();
	}

	/**
	 * Called when the mouse is pressed
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if (!drawing) {
			x0 = x1 = e.getX();
			y0 = y1 = e.getY();
			drawing = true;
		}
	}

	/**
	 * Called when the mouse is released
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		drawing = false;
	}
	
	/**
	 * Called when the mouse is clicked(pressed and released)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	/**
	 * Called when the mouse enters the JFrame
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	/**
	 * Called when the mouse exits the JFrame
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
	/**
	 * Called when the mouse is moved
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		repaint();
	}
	
	/**
	 * Called when the mouse is dragged(moved while pressed)
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		if (drawing) {
			x1 = mouseX;
			y1 = mouseY;
		}
		repaint();
	}

	
	
}
