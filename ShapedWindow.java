import javax.swing.*;

import java.awt.*;
import java.awt.image.*;

//the thing that will actually move around.

@SuppressWarnings("serial")
public class ShapedWindow extends JFrame{

	private BufferedImage image = new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB);
	
	public ShapedWindow(BufferedImage initi)
	{
		image = initi;
		//window size = the image's size
		this.setSize(image.getWidth(),image.getHeight());
		//currently, sets x to half of screen 
		//and y to right where it looks like it's standing on the bottom of the screen
		this.setLocation(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width/2,
				GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height-image.getHeight());
		//draws image on window
		this.setContentPane(new JLabel(new ImageIcon(image)));

		//gets rid of the borders, title bar, etc.
		setUndecorated(true);
		//gives it a transparent background, but doesn't transparentize the image
		setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setAlwaysOnTop(true);
		this.repaint();		
	}
	public void setImage(BufferedImage bi)
	{
		this.setContentPane(new JLabel(new ImageIcon(bi)));
		this.setVisible(true);
	}
}
