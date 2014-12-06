import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

//kind of global variable dump for the animations. 
//please come up with better idea for this.

public class ImageSets {
 public static BufferedImage[] walk = new BufferedImage[2];
 public static BufferedImage[] stand = new BufferedImage[1];
 public static BufferedImage[] wave = new BufferedImage[2];
 
 public static BufferedImage[] walkR = new BufferedImage[2];
 public static BufferedImage[] standR = new BufferedImage[1];
 public static BufferedImage[] waveR = new BufferedImage[2];
 
 public static void init()
 {
 	//turned file path to URLs since github can't upload pictures. not guaranteed to work.
  try {
   walk[0]=ImageIO.read(new URL("https://cloud.githubusercontent.com/assets/8552282/5325501/5cc5a396-7caa-11e4-9f05-1662361a6899.png"));
   walk[1]=ImageIO.read(new URL("https://cloud.githubusercontent.com/assets/8552282/5325503/61dc8b38-7caa-11e4-85b5-d14e5d69335b.png"));
   
   stand[0]=ImageIO.read(new URL("https://cloud.githubusercontent.com/assets/8552282/5325501/5cc5a396-7caa-11e4-9f05-1662361a6899.png"));
   
   wave[0]=ImageIO.read(new URL("https://cloud.githubusercontent.com/assets/8552282/5325501/5cc5a396-7caa-11e4-9f05-1662361a6899.png"));
   wave[1]=ImageIO.read(new URL("https://cloud.githubusercontent.com/assets/8552282/5325504/658bf084-7caa-11e4-9b90-e50d130aff00.png"));
   
   //create flipped arrays for when it's going in the opposite direction
   for(int i = 0; i < walk.length; i++){walkR[i] = flip(walk[i]);}
   for(int i = 0; i < stand.length; i++){standR[i] = flip(stand[i]);}
   for(int i = 0; i < wave.length; i++){waveR[i] = flip(wave[i]);}
  } 
  catch (IOException e){}
  
 }
 
 private static BufferedImage flip(BufferedImage b) {

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
