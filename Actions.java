import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

public class Actions{
	private ShapedWindow mascot;
	private boolean flipped;
	
	//TODO: make mouse event for dragging
	//TODO: make falling, etc.
	
	public Actions(ShapedWindow sw)
	{
		mascot = sw;
		ImageSets.init();
		stand();
	}
	
	public void nextAction()
	{
		Random random = new Random();
		int num = random.nextInt(3);
		flipped = random.nextBoolean();
		switch(num)
		{
		case 0:
			stand();
			break;
		case 1:
			try {walk();} 
			catch (InterruptedException e){}
			break;
		case 2:
			try {wave();} catch (InterruptedException e){}
			break;
		default:
			stand();
			break;
		}
	}
	
	public void stand()
	{
		mascot.setImage(flipped?ImageSets.standR[0]:ImageSets.stand[0]);
		
		Random random = new Random();
		//pauses for random time between 0.5 second and 3.5 seconds
		int time = 100*(random.nextInt(31)+5);
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		nextAction();
	}
	public void walk() throws InterruptedException
	{
		Random random = new Random();
		while(flipped?(mascot.getX()<
				(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width
				-mascot.getWidth())): (mascot.getX()>0))
		{
			for(BufferedImage b : flipped?ImageSets.walkR:ImageSets.walk)
			{
				mascot.setImage(b);
				mascot.setLocation((flipped?mascot.getX()+10:mascot.getX()-10),mascot.getY());
				Thread.sleep(40);
			}
			
			int keep = random.nextInt(30);
			if(keep == 0)
			{
				nextAction();
			}
		}
		nextAction();
	}
	public void wave() throws InterruptedException
	{
		Random random = new Random();
		int keep = random.nextInt(8);
		while(keep != 0)
		{
			for(BufferedImage b : flipped?ImageSets.waveR : ImageSets.wave)
			{
				mascot.setImage(b);
				Thread.sleep(300);
			}
			
			keep = random.nextInt(8);
		}
		nextAction();
	}

}
