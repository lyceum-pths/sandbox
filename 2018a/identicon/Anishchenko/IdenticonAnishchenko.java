import java.io.*;
import java.util.*;

public class IdenticonAnishchenko {
	static int colour[] = new int[3];
	static final int SIZE = 15;
	static PrintWriter out;

	public static byte[][] generateIdenticons(String text, int width, int height) {
		byte[][] identicon = new byte[width][height];

		Random rand = new Random(text.hashCode());

		colour[0] = rand.nextInt(256);
		colour[1] = rand.nextInt(256);
		colour[2] = rand.nextInt(256);

		for (int x = 0; x <= width / 2; x++) {
			for (int y = 0; y < height; y++) {
				identicon[x][y] = (byte) rand.nextInt(2);
				identicon[width - x - 1][y] = identicon[x][y];
			}
		}

		return identicon;
	}

	public static void createHTML(byte[][] image) throws IOException {
		out.print("<svg width=\"" + image.length * SIZE + " height=\"" + image.length * SIZE + "\" >\n");

		for (int x = 0; x < image.length; x++) {
			for (int y = 0; y < image[0].length; y++) {
				if (image[x][y] == 1) {
					out.print("<rect height = \"" + SIZE + "\" width = \"" + SIZE + "\" x=\"" + SIZE * x + "\" y=\""
							+ SIZE * y + "\" fill=\"rgb(" + colour[0] + ", " + colour[1] + ", " + colour[2]
							+ ")\" />\n");
				} else {
					out.print("<rect height = \"" + SIZE + "\" width = \"" + SIZE + "\" x=\"" + SIZE * x + "\" y=\""
							+ SIZE * y + "\" fill=\"rgb(255, 255, 255)\" />\n");
				}
			}
		}

		out.print("</svg>\n");
	}

	public static void main(String[] args) throws IOException {
		out = new PrintWriter(new File("output.html"));

		out.print("<!DOCTYPE html>\n" + "<html>\n" + "<body>\n");

		for (int i = 0; i < 100; i++) {
			String s = Integer.toString(i);

			byte[][] image = generateIdenticons(s, 8, 8);

			createHTML(image);
		}

		out.print("</body>\n" + "</html>");

		out.close();
	}
}