/*
Picture image = new Picture("image.jpg");
System.out.println("Image height: " + image.getHeight());
System.out.println("Image width: " + image.getWidth());
image.setPixel(2, 3, new Pixel(255, 255, 255));
image.store("newImage.jpg");

 */
package removephotobomb;

import java.io.File;

/**
 *
 * @author Pei Lian Liu
 */
public class ImageManipulator {
  public void removePhotobomb(String directory, String outputfile) {
    File[] files = (new File(directory)).listFiles();
  }
  
  Picture [] loadPics(String directory) {
    File[] files = (new File(directory)).listFiles();
    Picture[] pics = new Picture[files.length];
    for (int i = 0; i < files.length; i++) {
      // Split the filename by "." so that fileName[0] = "filename", fileName[1] = "jpg"
      String fileName[] = files[i].getName().split("\\.");
      // if the extension is jpg 
      if (fileName[1].equals("jpg")) {
        // Load the picture
        pics[i] = new Picture(files[i].toString());
        // Testing : output their height and width
        System.out.println("Image " +i +" height: " + pics[i].getHeight());
        System.out.println("Image " +i +" width: " + pics[i].getWidth());
      }
    }
  
    return pics;
  }
  
  public void zoomMiddle(String inputfile, String outputfile) {
  
  }
}
