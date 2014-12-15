package shimeji;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.Random;

public class Mascot extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage image = new BufferedImage(1, 1,
			BufferedImage.TYPE_INT_ARGB);
	private boolean flipped = false;

	public void init()
	{
		image = ImageSets.stand[0];
		// window size = the image's size
		this.setSize(image.getWidth(), image.getHeight());
		// currently, sets x to half of screen
		// and y to right where it looks like it's standing on the bottom of the
		// screen
		this.setLocation(GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getMaximumWindowBounds().width / 2, GraphicsEnvironment
				.getLocalGraphicsEnvironment().getMaximumWindowBounds().height
				- image.getHeight());
		//make image sets
		ImageSets.init();
		// draws image on window
		this.setContentPane(new JLabel(new ImageIcon(image)));

		MouseInputListener listener = new MouseInputListener();
		this.addMouseListener(listener);
		// gets rid of the borders, title bar, etc.
		setUndecorated(true);
		// gives it a transparent background, but doesn't transparentize the
		// image
		setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setAlwaysOnTop(true);
		this.repaint();
	}

	public void setImage(BufferedImage bi) {		
		if(!bi.equals(null))
		{
			ImageIcon pic = new ImageIcon(bi);
			JLabel back = new JLabel(pic);
			this.setContentPane(back);
			this.setVisible(true);
		}		
	}
	
	public void nextAction() {
		Random random = new Random();
		int num = random.nextInt(3);
		flipped = random.nextBoolean();
		switch (num) {
		case 0:
			stand();
			break;
		case 1:
			try {
				walk();
			} catch (InterruptedException e) {
			}
			break;
		case 2:
			try {
				wave();
			} catch (InterruptedException e) {
			}
			break;
		default:
			stand();
			break;
		}
	}

	public void stand() {

		Random random = new Random();
		// pauses for random time between 0.5 second and 3.5 seconds
		int time = 100 * (random.nextInt(31) + 5);
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		nextAction();
	}

	public void walk() throws InterruptedException {
		Random random = new Random();
		while (flipped ? (this.getX() < (GraphicsEnvironment
				.getLocalGraphicsEnvironment().getMaximumWindowBounds().width - this
				.getWidth()))
				: (this.getX() > 0)) {
			for (BufferedImage b : flipped ? ImageSets.walkR : ImageSets.walk) {
				this.setImage(b);
				this.setLocation(
						(flipped ? this.getX() + 10 : this.getX() - 10),
						this.getY());
				Thread.sleep(40);
			}

			int keep = random.nextInt(30);
			if (keep == 0) {
				nextAction();
			}
		}
		nextAction();
	}

	public void wave() throws InterruptedException {
		Random random = new Random();
		int keep = random.nextInt(8);
		while (keep != 0) {
			for (BufferedImage b : flipped ? ImageSets.waveR : ImageSets.wave) {
				this.setImage(b);
				Thread.sleep(300);
			}

			keep = random.nextInt(8);
		}
		nextAction();

	}

	public void fall() {
		while (this.getY() > (GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getMaximumWindowBounds().height - this.getHeight())) {
			this.setImage(flipped ? ImageSets.fallR[0] : ImageSets.fall[0]);
			this.setLocation(this.getLocation().x, this.getLocation().y - 10);
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
			}
		}
		if (ImageSets.fall.length > 1) {
			fallComplete();
		}
		nextAction();
	}

	private void fallComplete() {
		for (BufferedImage b : flipped ? ImageSets.fallR : ImageSets.fall) {
			this.setImage(b);
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
			}
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
	}

	class MouseInputListener implements MouseListener, MouseMotionListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("Clicked");
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseMoved(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			Mascot.this.setImage(ImageSets.stand[0]);
			Mascot.this.setLocation(e.getLocationOnScreen().x - e.getX(),
					e.getLocationOnScreen().y - e.getY());
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			fall();
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			Mascot.this.setLocation(e.getLocationOnScreen().x - e.getX(),
					e.getLocationOnScreen().y - e.getY());
		}
	}

}
