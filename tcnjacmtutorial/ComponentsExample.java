
import javax.swing.*; // JFrame, JComponents
import java.awt.event.*; // ActionListener

/**
 * Class that demonstrates use of JComponents
 */
public class ComponentsExample extends JFrame implements ActionListener {

	/**Some JComponents we can add. See the full list in the Java API*/
	private JButton button;
	private JTextField field;
	
	public ComponentsExample() {
		
		//---Set up the JFrame---//
		
		setSize(1000,700); // Set the size of the JFrame(width, height) in pixels
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set when the JFrame closes
		setLayout(null); // Set the layout
		
		//---Set up the JComponents---//
		
		// Initialize the components
		field = new JTextField();
		button = new JButton("On/Off");
		
		// Set the bounds(necessary for 'null' layout, each layout has different needs)
		field.setBounds(100,100,200,25);
		button.setBounds(100,125,100,25);
		
		button.addActionListener(this); // Add the action listener to the button
		
		//---Add the JComponents to the JFrame---//
		
		add(button);
		add(field);
		
		// Ready to go!
	}
	
	public static void main(String[] args) {
		ComponentsExample frame = new ComponentsExample(); // Initialize a new frame
		frame.setVisible(true); // Set frame visible **IMPORTANT**
	}

	/**
	 * Automatically is called any time an action is performed on any JComponents
	 * that have been added to the JFrame.
	 * @param ActionEvent e - ActionEvent instance
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button)
			field.setEnabled(!field.isEnabled());
	}
	
}
