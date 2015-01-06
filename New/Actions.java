package shimeji;

import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.*;
import java.util.Random;

import javax.imageio.ImageIO;

//kind of global variable dump for the animations. 
//please come up with better idea for this. 
public class Actions {
	
	//this is basically a static class - we wont' make any instances of it, 
	//so currentMascot is the mascot that will be doing the actions. (think: Actions = the only enzyme, currentMascot = current substrate)
	//will be used when we make the launcher and there are multiple mascots.
	private static Mascot currentMascot;
	
	//declaring the image sets
	private static BufferedImage[] walki = new BufferedImage[2];
	private static BufferedImage[] standi = new BufferedImage[1];
	private static BufferedImage[] wavei = new BufferedImage[2];
	private static BufferedImage[] falli = new BufferedImage[1];
	
	//declaring the actions
	private static Action walk;
	private static Action stand;
	private static Action wave;
	
	//the only public instance variable - should we keep it public or make a get and set for it?
	//conglomerates all of the actions into one thing
	public static Action[] actionList = new Action[3];

	public static void init() {
		// puts images in empty image sets. Because Eclipse whines when we don't put it here.
		try {
			walki[0] = ImageIO.read(new File("stickfigure.png"));
			walki[1] = ImageIO.read(new File("stickfigure1.png"));

			standi[0] = ImageIO.read(new File("stickfigure.png"));

			wavei[0] = ImageIO.read(new File("stickfigure.png"));
			wavei[1] = ImageIO.read(new File("stickfigure2.png"));

			falli[0] = ImageIO.read(new File("stickfigure1.png"));
		} catch (IOException e) {e.printStackTrace();}
		
		//specifies the actions, puts them in actionList
		try{
			walk = new Action(Actions.class.getMethod("walkm", new Class[]{BufferedImage[].class}), walki);
			stand = new Action(Actions.class.getMethod("standm", new Class[]{BufferedImage[].class}), standi);
			wave = new Action(Actions.class.getMethod("wavem", new Class[]{BufferedImage[].class}), wavei);
			actionList[0] = stand;
			actionList[1] = walk;
			actionList[2] = wave;
		} catch(NoSuchMethodException e){e.printStackTrace();}
		
	}
	
	public static void setCurrentMascot(Mascot m){currentMascot = m;}
	
	//the methods that the actions refer to. Probably a better way to do this.
	public void standm(BufferedImage[] bi) {
		Random random = new Random();
		// pauses for random time between 0.5 second and 2.5 seconds
		int time = 100 * (random.nextInt(15) + 5);
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		stand.setContinue(0);
	}

	public void walkm(BufferedImage[] bi) throws InterruptedException {
		if(!currentMascot.getStopped())
		{
			while (walk.getFlipped() ? (currentMascot.getX() < (GraphicsEnvironment
					.getLocalGraphicsEnvironment().getMaximumWindowBounds().width - currentMascot
					.getWidth()))
					: (currentMascot.getX() > 0)) {
				for (BufferedImage b : bi) {
					currentMascot.setImage(b);
					currentMascot.setLocation(
							(walk.getFlipped() ? currentMascot.getX() + 10 : currentMascot.getX() - 10),
							currentMascot.getY());
					Thread.sleep(40);
				}			
			}
		}
		currentMascot.setImage(bi[0]);
		//walk.setContinue(random.nextInt(30));
		walk.setContinue(0);
	}//no I muted it. the problem is probably different java versions
	//unmute so i can explain //i did 

	public void wavem(BufferedImage[] bi) throws InterruptedException {
		Random random = new Random();
		if(!currentMascot.getStopped())
		{
			for (BufferedImage b : bi) {
				currentMascot.setImage(b);
				Thread.sleep(300);
			}
		}		
		currentMascot.setImage(bi[0]);
		wave.setContinue(random.nextInt(8));
	}

	public static void fall() {
		while (currentMascot.getY() < (GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getMaximumWindowBounds().height - (currentMascot.getHeight())-2)) {
			currentMascot.setImage(walki[1]);
			currentMascot.setLocation(currentMascot.getLocation().x, currentMascot.getLocation().y + 10);
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
			}
		}
	}
/*
	private void fallComplete(BufferedImage[] bi) {
		for (BufferedImage b : bi) {
			currentMascot.setImage(b);
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
			}
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		fall.setContinue(0);
	}
	*/
}

//a method coupled with an imageSet.
class Action
{
	private Method method;
	private BufferedImage[] imageSet;
	private BufferedImage[] imageSetR; //flipped imageSet
	private boolean flipped;
	private int cont=1; //if the action will continue
	
	public Action(Method mi, BufferedImage[] bi) 
	{	
		method = mi;
		imageSet = bi;
		imageSetR = new BufferedImage[imageSet.length];
		for(int i = 0; i < imageSet.length; i++) {imageSetR[i] = flip(imageSet[i]);}
	}
	
	//gets and sets
	public boolean getFlipped(){return flipped;}	
	public BufferedImage[] getImageSet(){return imageSet;}
	public BufferedImage[] getImageSetR(){return imageSetR;}
	public int getContinue(){return cont;}
	
	public void setContinue(int num){cont = num;}
	
	//makes the mascot do the action.
	//more specifically, calls the specified method in Actions class. it is the static method there that actually makes the mascot do stuff.
	public void go()
	{
		Random random = new Random();
		Actions actions = new Actions();
	    flipped = random.nextBoolean();
		BufferedImage[] anims = flipped?imageSetR:imageSet;
		try{method.invoke(actions, (Object)anims);}
		catch (SecurityException|IllegalAccessException|IllegalArgumentException|InvocationTargetException e){
            e.printStackTrace();}
	}
	
	
	//in order to make flipped image set
	private static BufferedImage flip(final BufferedImage b) {

		final BufferedImage copy = new BufferedImage(b.getWidth(),
				b.getHeight(), BufferedImage.TYPE_INT_ARGB);

		for (int y = 0; y < b.getHeight(); ++y) {
			for (int x = 0; x < b.getWidth(); ++x) {
				copy.setRGB(copy.getWidth() - x - 1, y, b.getRGB(x, y));
			}
		}
		return copy;
	}
}
