import java.io.File;
import java.util.Arrays;
import java.util.ArrayList;

public class ImageManipulator {
  
  public void removePhotobomb(String directory, String outputfile) {
    Picture[] pics = loadPics(directory);
    System.out.println("Removing... photobomb...");
    removePhoto(pics, outputfile);
  }
  
  Picture [] loadPics(String directory) {
    File[] files = (new File(directory)).listFiles();
    System.out.println("Found " + files.length + " files.");
    int count = 0;

    for (int i = 0; i < files.length; i++) {
      // Split the filename by "." so that fileName[0] = "filename", fileName[1] = "jpg"
      String fileName[] = files[i].getName().split("\\.");
      // if the extension is jpg 
      if (fileName[1].equals("jpg")) {
        count++;
      }
    }

    // Found total jpg files and then create Picture Array
    System.out.println("Found " + count + " jpg files.");
    Picture[] pics = new Picture[count];
    count = 0;
    for (int i = 0; i < files.length; i++) {
      // Split the filename by "." so that fileName[0] = "filename", fileName[1] = "jpg"
      String fileName[] = files[i].getName().split("\\.");
      // if the extension is jpg 
      if (fileName[1].equals("jpg")) {
        // Load the picture
        pics[count] = new Picture(files[i].toString());
        // Testing : output their height and width
        System.out.println("Loading " + files[i].toString() +" height: " + pics[count].getHeight());
        System.out.println("Loading " + files[i].toString() +" width: " + pics[count].getWidth()); 
        count++;    
      }
    }
    return pics;
  }
  
  public void removePhoto(Picture[] pics, String outputfile) {
    // if multiply pictures are not the same size exit right away
    if (!picsCheck(pics)) {
      System.out.println("Please make sure all pics are the same size!");
      return;
    }
    int w = pics[0].getWidth();
    int h = pics[0].getHeight();
    int red, green, blue;
    Picture newPic = new Picture(w, h);
    
    int[] r = new int[pics.length];
    int[] g = new int[pics.length];
    int[] b = new int[pics.length];
    
    for (int i = 0; i < w; i++) {
      for (int j = 0; j < h; j++) {
        for (int n = 0; n < pics.length; n++) {
          r[n] = pics[n].getPixel(i, j).getRed();
          g[n] = pics[n].getPixel(i, j).getGreen();
          b[n] = pics[n].getPixel(i, j).getBlue();
          red = median(r);
          green = median(g);
          blue = median(b);
          newPic.setPixel(i, j, new Pixel(red, green, blue));
        }
      }  
    } 
    System.out.println("Saving ..." + outputfile);
    newPic.store(outputfile);  
  }

  // simple Median
  public int median(int[] input) {
      int median;
      int size = input.length;
      Arrays.sort(input);
      if (input.length % 2 == 0) {
        median = input[size / 2 - 1];
      } else {
        median = input[size / 2];
      }
      return median;  
  }
  
  // check all pics size if they are the same size
  public boolean picsCheck(Picture[] pics) {
    int size = pics[0].getHeight();
    for(int i = 1; i < pics.length; i++) {
      if (pics[i].getHeight() != size) 
        return false;
    }

    size = pics[0].getWidth();
    for(int i = 1; i < pics.length; i++) {
      if (pics[i].getWidth() != size) 
        return false;
    }

    return true;
  }
  
  public void zoomMiddle(String inputfile, String outputfile) {
    System.out.println("Making.. Zooming image...");
    Picture pic = new Picture(inputfile);
    
    int w = pic.getWidth();
    int h = pic.getHeight();
    Picture newPic = new Picture(w, h);
    Pixel current;
    int t1 = 0;
    int t2 = 0;
    for (int i = w / 4 ; i < 3 * w / 4 ; i++) {
      for (int j = h / 4; j < 3 * h / 4; j++) {
        current = pic.getPixel(i, j);
        for (int m = (i - w / 4) * 2  ; m < w ; m++) {
          for (int n = (j - h / 4) * 2; n < h; n++) { 
            newPic.setPixel(m, n, current);
            t1++;
            if (t1 == 2) {
              t1 = 0;
              break;
            }      
          }
          t2++;
          if (t2 == 2) {
            t2 = 0;
            break;
          }
        }
      }
    }
    System.out.println("Saving..." + outputfile);
    newPic.store(outputfile);
  }
}
