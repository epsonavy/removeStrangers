package removephotobomb;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author Pei Lian Liu
 */
public class ImageManipulator {
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
  
  public void removePhotobomb(String directory, String outputfile) {
    Picture[] pics = loadPics(directory);
    
  }
  
  public void medianFilter(Picture pic, int windowSize) {
    int image_width = pic.getWidth();
    int image_height = pic.getHeight();
    
    int outputPixelRed[][] = new int[image_width][image_height];
    int outputPixelGreen[][] = new int[image_width][image_height];
    int outputPixelBlue[][] = new int[image_width][image_height];

    int red[] = new int[windowSize * windowSize];
    int green[] = new int[windowSize * windowSize];
    int blue[] = new int[windowSize * windowSize];
    
    int edgex = windowSize / 2;
    int edgey = windowSize / 2;
    int i;
    for (int x = edgex; x <= image_width - edgex; x++) {
      for (int y = edgey; y <= image_height - edgey; y++) {
          i = 0;
          for (int fx = 0; fx <= windowSize; fx++) {
            for (int fy = 0; fy <= windowSize; fy++) {
              red[i] = pic.getPixel(x + fx - edgex, y + fy - edgey).getRed();
              green[i] = pic.getPixel(x + fx - edgex, y + fy - edgey).getGreen();
              blue[i] = pic.getPixel(x + fx - edgex, y + fy - edgey).getBlue();
              i++;
            }
          }
          Arrays.sort(red);
          Arrays.sort(green);
          Arrays.sort(blue);
          outputPixelRed[x][y] = red[windowSize * windowSize / 2];
          outputPixelGreen[x][y] = red[windowSize * windowSize / 2];
          outputPixelBlue[x][y] = red[windowSize * windowSize / 2];
      }
    }
    
  }
  
  public void zoomMiddle(String inputfile, String outputfile) {
  
  }
  
}
