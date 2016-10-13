package removephotobomb;

public class ImageDriver {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    ImageManipulator ma = new ImageManipulator();
    String path = "./src/removephotobomb/images";
    String newFileName = "nophotobomb.jpg";
    String zoomFileName = "zoomed.jpg";
    //ma.run(path, newFileName);
    ma.zoomMiddle(newFileName, zoomFileName);
  }
  
}
