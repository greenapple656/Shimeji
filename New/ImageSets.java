package shimeji;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//kind of global variable dump for the animations. 
//please come up with better idea for this.

public class ImageSets {
 public BufferedImage[] walk = new BufferedImage[2];
 public BufferedImage[] stand = new BufferedImage[1];
 public BufferedImage[] wave = new BufferedImage[2];
 public BufferedImage[] fall = new BufferedImage[1];
 
 public BufferedImage[] walkR = new BufferedImage[2];
 public BufferedImage[] standR = new BufferedImage[1];
 public BufferedImage[] waveR = new BufferedImage[2];
 public BufferedImage[] fallR = new BufferedImage[1];
 
 public void init()
 {
	 //because we can't put ImageIO.read outside of a method, where we can exception handle
  try {
   walk[0]=ImageIO.read(new File("stickfigure.png"));
   walk[1]=ImageIO.read(new File("stickfigure1.png"));
   
   stand[0]=ImageIO.read(new File("stickfigure.png"));
   
   wave[0]=ImageIO.read(new File("stickfigure.png"));
   wave[1]=ImageIO.read(new File("stickfigure2.png"));
   
   fall[0]=ImageIO.read(new File("stickfigure1.png"));
   
   for(int i = 0; i < walk.length; i++){walkR[i] = flip(walk[i]);}
   for(int i = 0; i < stand.length; i++){standR[i] = flip(stand[i]);}
   for(int i = 0; i < wave.length; i++){waveR[i] = flip(wave[i]);}
   for(int i = 0; i < fall.length; i++){fallR[i] = flip(fall[i]);}
  } 
  catch (IOException e){}
  
 }
 
 private static BufferedImage flip(final BufferedImage b) {

	  final BufferedImage copy = new BufferedImage(b.getWidth(), b.getHeight(),
	    BufferedImage.TYPE_INT_ARGB);

	  for (int y = 0; y < b.getHeight(); ++y) {
	   for (int x = 0; x < b.getWidth(); ++x) {
	    copy.setRGB(copy.getWidth() - x - 1, y, b.getRGB(x, y));
	   }
	  }
	  return copy;
	 }

}
