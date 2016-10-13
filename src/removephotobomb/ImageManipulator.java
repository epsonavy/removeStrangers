package removephotobomb;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
  
  public void run(String directory, String outputfile) {
    Picture[] pics = loadPics(directory);
    removePhotobomb(pics, outputfile);
  }
  
  public void removePhotobomb(Picture[] pics, String outputfile) {
    // if multiply pictures are not the same size exit right away
    if (!picsCheck(pics)) {
      System.out.println("Please make sure all pics are the same size!");
      return;
    }
    int w = pics[0].getWidth();
    int h = pics[0].getHeight();
    int red, green, blue;
    Picture newPic = new Picture(w, h);
    
    ArrayList<Integer> redList = new ArrayList<>();
    ArrayList<Integer> blueList = new ArrayList<>();
    ArrayList<Integer> greenList = new ArrayList<>();
    
    for (int i = 0; i < w; i++) {
      for (int j = 0; j < h; i++) {
        for (int n = 0; n < pics.length; n++) {
          redList.add(pics[n].getPixel(i, j).getRed());
          greenList.add(pics[n].getPixel(i, j).getGreen());
          blueList.add(pics[n].getPixel(i, j).getBlue());
          red = median(redList);
          green = median(greenList);
          blue = median(blueList);
          newPic.setPixel(i, j, new Pixel(red, green, blue));
        }
      }  
    } 
    newPic.store(outputfile);  
  }

  // simple Median
  public int median(ArrayList<Integer> input) {
      int median;
      int size = input.size();
      Collections.sort(input);
      if (input.size() % 2 == 0) {
        median = input.get(size / 2 - 1);
      } else {
        median = input.get(size / 2);
      }
      return median;  
  }
  
  
  // 1D Median algorithm
  public ArrayList<Integer> getMedian(ArrayList<Integer> input) {
    ArrayList<Integer> result = new ArrayList<>();
    int size = input.size();
    int windowSize = input.size() / 2 + 1; // make sure window size is odd
    int[] window = new int[windowSize];
    int median;
    int index;
    ArrayList<Integer> temp = new ArrayList<>();
    
    for (int n = 0 ; n < size; n++) {
      // adding dummy element to fix the boundary issue
      int dummySize = windowSize / 2;
      for (int i = 0;  i < dummySize; i++) {
        temp.add(input.get(0));
      }
      for (Integer e: input) {
        temp.add(e);
      }
      for (int i = 0;  i < dummySize; i++) {
        temp.add(input.get(size - 1));
      }
      int start = 0 + n;
      int end = windowSize + n; 
      // Adding element to window
      index = 0;
      for (int i = start; i < end; i++) {
        window[index] = temp.get(i);
        index++;
      }
      Arrays.sort(window);
      median = window[windowSize / 2];

      result.add(median);
    }
    return result;
  }
  
  // check all pics size if they are the same size
  public boolean picsCheck(Picture[] pics) {
    for (int i = 0; i < pics.length - 1; i++) {
       if (pics[i].getHeight() != pics[i+1].getHeight())
         return false;
    }
    
    for (int i = 0; i < pics.length - 1; i++) {
       if (pics[i].getWidth() != pics[i+1].getWidth())
         return false;
    }
    return true;
  }
  
  
  public void zoomMiddle(String inputfile, String outputfile) {
  
  }
  
}
