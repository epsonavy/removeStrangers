public class ImageDriver {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {

		String path;
		String newFileName;
		String zoomFileName;

		if (args.length == 1) {
			try {
				path = args[0];
				newFileName = args[0] + "nophotobomb.jpg";
				zoomFileName = args[0] + "zoomed.jpg";
				ImageManipulator ma = new ImageManipulator();
				ma.removePhotobomb(path, newFileName);
				ma.zoomMiddle(newFileName, zoomFileName);
			} catch (NumberFormatException e) {
				System.err.println("Argument" + args[0] + " must the path of the image.");
				System.exit(1);
			}
		} else {
			System.out.println("************************************************");
			System.out.println("Usage: java ImageDriver [Path of the images]");
			System.out.println("For example: java ImageDriver images/");
			System.out.println("Output removebomb filename: \"nophotobomb.jpg\"");
			System.out.println("Output zoomed filename: \"zoomed.jpg\"");
			System.out.println("************************************************");
		}

	}

}
