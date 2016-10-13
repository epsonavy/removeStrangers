/*

 */
package removephotobomb;

import java.util.ArrayList;

/**
 *
 * @author Pei Lian Liu
 */
public class ImageDriver {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    ImageManipulator ma = new ImageManipulator();
    String path = "./src/removephotobomb/images";
    String newName = "nophotobomb.jpg";
    ma.run(path, newName);
    
    
  }
  
}
