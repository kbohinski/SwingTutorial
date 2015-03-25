/**
 * @author Kevin Bohinski <bohinsk1@tcnj.edu>
 * @version 1.0
 * @since 2015-3-25
 * 
 * TCNJ-ACM Tutorial
 * SwingTutorialFollowalong
 * Driver.java
 * Copyright (c) 2015 Kevin Bohinski. All rights reserved.
 */

/* Setting Package */

/* Setting Imports */
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import tcnjacmtutorial.DynamicColor;

public class Driver extends JFrame implements ActionListener {

	/* Global Vars */
	int frameX = 600;
	int frameY = 600;
	private JButton button;
	private JTextField textbox;
	private DynamicColor myColor = new DynamicColor();
	
	/**
	 * Need a constructor for JFrame.
	 */
	public Driver() {
		/* Setting up a window */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new DrawingPanel());
		setSize(frameX,frameY);
		setLayout(null);
		setVisible(true);
		
		/* Making a new button */
		button = new JButton("A Button");
		int bwidth = 200;
		int bheight = 50;
		int bx = (frameX - (frameX/2) - (bwidth/2));
		int by = (frameY - (frameY/2) - (bheight/2));
		button.setBounds(bx, by, bwidth, bheight);
		
		/* Making a new textbox */
		textbox = new JTextField();
		textbox.setBounds(bx, (by + bheight + 20), bwidth, bheight);
		
		/* Calls actionPerformed if clicked. */
		button.addActionListener(this);
		
		/* Adds objects to window */
		add(button);
		add(textbox);
	}
	
	public static void main(String[] args) {
		Driver window = new Driver();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {
			textbox.setEnabled(!textbox.isEnabled());
			repaint();
		}
	}
	
	public class DrawingPanel extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(myColor);
			g.fillRect(200, 200, 200, 200);
		}
	}

}
