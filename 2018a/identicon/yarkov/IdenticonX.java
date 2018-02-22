import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class IdenticonX {

	static final int size = 300;
	static final String[] colours = { "Black", "Red", "Green", "Blue", "Yellow", "DarkOrange", "Orange", "Gold", "Aqua",
			"Magenta", "DarkSlateBlue", "OrangeRed", "Silver", "DarkMagenta", "Indigo", "LightPink", "SaddleBrown",
			"Sienna", "DarkGreen", "YellowGreen", "LawnGreen" };
	static final int numberOfColours = colours.length;
	static final String backgroundOpacity = "0.2";
	static final int numberOfFigures = 5;
	static final int numberOfDiceSides = 10;
	static final int numberOfFieldsInLine = 3;

	static PrintWriter out;

	static void draw(int x, int y, int r, int fig, int fillCol, int strokeCol, int strokeWidth) {
		int www;
		if (strokeCol == -1) {
			www = 0;
			strokeCol = 0;
		} else {
			www = strokeWidth;
		}
		int l = (int) Math.floor(2.0 * r / Math.sqrt(3));
		switch (fig) {
		case 0:
			out.println("<rect x=\"" + (x - r) + "\" y=\"" + (y - r) + "\" width=\"" + 2 * r + "\" height=\"" + 2 * r
					+ "\" style=\"fill:" + colours[fillCol] + ";stroke:" + colours[strokeCol] + ";stroke-width:" + www
					+ ";\" />");
			break;

		case 1:
			out.println("<circle cx=\"" + x + "\" cy=\"" + y + "\" r=\"" + r + "\"  style=\"fill:" + colours[fillCol]
					+ ";stroke:" + colours[strokeCol] + ";stroke-width:" + www + "\" />");
			break;

		case 2:
			out.println("<polyline points=\"" + (x - l) + "," + (y - r) + " " + (x + l) + "," + (y - r) + " " + x + ","
					+ (y + r) + " " + (x - l) + "," + (y - r) + " " + (x + l) + "," + (y - r) + "\"  style=\"fill:"
					+ colours[fillCol] + ";stroke:" + colours[strokeCol] + ";stroke-width:" + www + "\" />");
			break;

		case 3:
			out.println("<polyline points=\"" + (x - l) + "," + (y + r) + " " + (x + l) + "," + (y + r) + " " + x + ","
					+ (y - r) + " " + (x - l) + "," + (y + r) + " " + (x + l) + "," + (y + r) + "\"  style=\"fill:"
					+ colours[fillCol] + ";stroke:" + colours[strokeCol] + ";stroke-width:" + www + "\" />");
			break;

		case 4:
			out.println("<polyline points=\"" + (x - r) + "," + y + " " + x + "," + (y - r) + " " + (x + r) + "," + y
					+ " " + x + "," + (y + r) + " " + (x - r) + "," + y + " " + x + "," + (y - r) + "\"  style=\"fill:"
					+ colours[fillCol] + ";stroke:" + colours[strokeCol] + ";stroke-width:" + www + "\" />");
			break;

		default:
			break;
		}
	}

	static void genIdenticon(int size, String string) throws FileNotFoundException {
		Random random = new Random(string.hashCode());
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<body>");
		out.println();

		int x0 = size, y0 = size;
		out.println("<svg width=\"" + x0 + "\" height=\"" + y0 + "\">");
		out.println("<rect x=\"0\" y=\"0\" width=\"" + x0 + "\" height=\"" + y0 + "\" style=\"fill:"
				+ colours[random.nextInt(numberOfColours)] + ";fill-opacity:" + backgroundOpacity + "\" />");

		int sx = x0 / numberOfFieldsInLine, sy = y0 / numberOfFieldsInLine;
		int sm = Math.min(sx, sy);

		int x1 = sx * (numberOfFieldsInLine / 2), y1 = sy * (numberOfFieldsInLine / 2);
		int strokeWidth1 = sm / 50;
		out.println("<rect x=\"" + x1 + "\" y=\"" + y1 + "\" width=\"" + sx + "\" height=\"" + sy + "\" style=\"fill:"
				+ colours[random.nextInt(numberOfColours)] + ";fill-opacity:" + backgroundOpacity
				+ ";stroke:Black;stroke-width:" + strokeWidth1 + "\" />");
		int r1 = sm / 10;
		int fig1 = random.nextInt(numberOfFigures);
		int dice1 = random.nextInt(numberOfDiceSides);
		// int dice1 = 9;
		int fillCol1 = random.nextInt(numberOfColours);
		int strokeCol1 = 0;
		switch (dice1) {
		case 0:
			draw(x1 + sx / 2, y1 + sy / 2, r1, fig1, fillCol1, strokeCol1, strokeWidth1);
			break;

		case 1:
			draw(x1 + sx / 3, y1 + sy / 3, r1, fig1, fillCol1, strokeCol1, strokeWidth1);
			draw(x1 + sx / 3 * 2, y1 + sy / 3 * 2, r1, fig1, fillCol1, strokeCol1, strokeWidth1);
			break;

		case 2:
			draw(x1 + sx / 3, y1 + sy / 3 * 2, r1, fig1, fillCol1, strokeCol1, strokeWidth1);
			draw(x1 + sx / 3 * 2, y1 + sy / 3, r1, fig1, fillCol1, strokeCol1, strokeWidth1);
			break;

		case 3:
			draw(x1 + sx / 4, y1 + sy / 4, r1, fig1, fillCol1, strokeCol1, strokeWidth1);
			draw(x1 + sx / 2, y1 + sy / 2, r1, fig1, fillCol1, strokeCol1, strokeWidth1);
			draw(x1 + sx / 4 * 3, y1 + sy / 4 * 3, r1, fig1, fillCol1, strokeCol1, strokeWidth1);
			break;

		case 4:
			draw(x1 + sx / 4 * 3, y1 + sy / 4, r1, fig1, fillCol1, strokeCol1, strokeWidth1);
			draw(x1 + sx / 2, y1 + sy / 2, r1, fig1, fillCol1, strokeCol1, strokeWidth1);
			draw(x1 + sx / 4, y1 + sy / 4 * 3, r1, fig1, fillCol1, strokeCol1, strokeWidth1);
			break;

		case 5:
			draw(x1 + sx / 3, y1 + sy / 3, r1, fig1, fillCol1, strokeCol1, strokeWidth1);
			draw(x1 + sx / 3 * 2, y1 + sy / 3 * 2, r1, fig1, fillCol1, strokeCol1, strokeWidth1);
			draw(x1 + sx / 3, y1 + sy / 3 * 2, r1, fig1, fillCol1, strokeCol1, strokeWidth1);
			draw(x1 + sx / 3 * 2, y1 + sy / 3, r1, fig1, fillCol1, strokeCol1, strokeWidth1);
			break;

		case 6:
			draw(x1 + sx / 4, y1 + sy / 4, r1, fig1, fillCol1, strokeCol1, strokeWidth1);
			draw(x1 + sx / 2, y1 + sy / 2, r1, fig1, fillCol1, strokeCol1, strokeWidth1);
			draw(x1 + sx / 4 * 3, y1 + sy / 4 * 3, r1, fig1, fillCol1, strokeCol1, strokeWidth1);
			draw(x1 + sx / 4 * 3, y1 + sy / 4, r1, fig1, fillCol1, strokeCol1, strokeWidth1);
			draw(x1 + sx / 4, y1 + sy / 4 * 3, r1, fig1, fillCol1, strokeCol1, strokeWidth1);
			break;

		case 7:
			draw(x1 + sx / 3, y1 + sy / 4, r1, fig1, fillCol1, strokeCol1, strokeWidth1);
			draw(x1 + sx / 3, y1 + sy / 2, r1, fig1, fillCol1, strokeCol1, strokeWidth1);
			draw(x1 + sx / 3, y1 + sy / 4 * 3, r1, fig1, fillCol1, strokeCol1, strokeWidth1);
			draw(x1 + sx / 3 * 2, y1 + sy / 4, r1, fig1, fillCol1, strokeCol1, strokeWidth1);
			draw(x1 + sx / 3 * 2, y1 + sy / 2, r1, fig1, fillCol1, strokeCol1, strokeWidth1);
			draw(x1 + sx / 3 * 2, y1 + sy / 4 * 3, r1, fig1, fillCol1, strokeCol1, strokeWidth1);
			break;

		case 8:
			draw(x1 + sx / 4, y1 + sy / 3, r1, fig1, fillCol1, strokeCol1, strokeWidth1);
			draw(x1 + sx / 2, y1 + sy / 3, r1, fig1, fillCol1, strokeCol1, strokeWidth1);
			draw(x1 + sx / 4 * 3, y1 + sy / 3, r1, fig1, fillCol1, strokeCol1, strokeWidth1);
			draw(x1 + sx / 4, y1 + sy / 3 * 2, r1, fig1, fillCol1, strokeCol1, strokeWidth1);
			draw(x1 + sx / 2, y1 + sy / 3 * 2, r1, fig1, fillCol1, strokeCol1, strokeWidth1);
			draw(x1 + sx / 4 * 3, y1 + sy / 3 * 2, r1, fig1, fillCol1, strokeCol1, strokeWidth1);
			break;

		default:
			break;
		}

		int sqr = numberOfFieldsInLine * numberOfFieldsInLine;
		// int sqr = 1;
		for (int zzz = 0; zzz < sqr; zzz++) {
			int i = zzz % numberOfFieldsInLine;
			int j = zzz / numberOfFieldsInLine;
			if (i == numberOfFieldsInLine / 2 && j == numberOfFieldsInLine / 2) {
				continue;
			}
			int xZ = sx * i + sx / 2, yZ = sy * j + sy / 2;
			int rZ = sm / 20 * 7;
			int figZ = random.nextInt(numberOfFigures);
			int fillColZ = random.nextInt(numberOfColours);
			int strokeColZ = random.nextBoolean() ? random.nextInt(numberOfColours) : -1;
			int strokeWidthZ = sm / 15;
			draw(xZ, yZ, rZ, figZ, fillColZ, strokeColZ, strokeWidthZ);
		}

		out.println("</svg>");
		out.println();
		out.println("</body>");
		out.println("</html>");
		out.println();
		out.println();
	}

	public static void main(String[] args) throws FileNotFoundException {
		out = new PrintWriter(new File("output.html"));
		for (int i = 1; i <= 100; i++) {
			genIdenticon(size, Integer.toString(i));
		}
		out.close();
	}
}
