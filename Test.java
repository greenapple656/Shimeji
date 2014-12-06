import java.awt.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.ImageIO;

public class Test {

 public static void main(String[] args) throws IOException {
  Image i = ImageIO.read(new File("stickfigure.png"));
  BufferedImage image = (BufferedImage) i;
  
  ShapedWindow sw = new ShapedWindow(image);
  sw.setVisible(true);
  
  Actions actions = new Actions(sw);
  actions.nextAction();
}
}
