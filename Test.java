import java.awt.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.ImageIO;

public class Test {

 public static void main(String[] args) throws IOException {
  Image i = ImageIO.read(new URL("https://cloud.githubusercontent.com/assets/8552282/5325501/5cc5a396-7caa-11e4-9f05-1662361a6899.png"));
  BufferedImage image = (BufferedImage) i;
  
  ShapedWindow sw = new ShapedWindow(image);
  sw.setVisible(true);
  
  Actions actions = new Actions(sw);
  actions.nextAction();
}
}
