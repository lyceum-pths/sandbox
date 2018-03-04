package olympiad;

import java.util.BitSet;
import java.util.Random;
import java.util.Scanner;

public class Identicon {

	public static final int SIZE = 32;
	public static final int COLOR = 3;
	public static final int LOCATION = 32;

	public static int[] chooseColor(int color) {
		int[] a = new int[3];
		if (color == 0) {// Black
			a[0] = 0;
			a[1] = 0;
			a[2] = 0;
		}
		if (color == 1) {// Red
			a[0] = 255;
			a[1] = 0;
			a[2] = 0;
		}
		if (color == 2) {// Orange
			a[0] = 255;
			a[1] = 140;
			a[2] = 0;
		}
		if (color == 7) {// Yellow
			a[0] = 255;
			a[1] = 255;
			a[2] = 0;
		}
		if (color == 4) {// Green
			a[0] = 0;
			a[1] = 255;
			a[2] = 0;
		}
		if (color == 5) {// Cyan
			a[0] = 0;
			a[1] = 255;
			a[2] = 255;
		}
		if (color == 6) {// Blue
			a[0] = 0;
			a[1] = 0;
			a[2] = 255;
		}
		if (color == 3) {// Purple
			a[0] = 255;
			a[1] = 0;
			a[2] = 255;
		}
		return a;
	}

	public static void drawIdenticon(BitSet field, int[] rgbColor) {
		int side = 7;
		int halfSide = (int)Math.ceil((double)side/2.0);
		int color = COLOR;
		String colour = rgbColor[0] + ","+rgbColor[1]+","+rgbColor[2];
		for (int i = 0; i < side; i++) {
			for (int j = 0; j < halfSide; j++) {
				if(field.get(color + halfSide*i + j)) {
					System.out.println("<rect x = \"" + 20*j + "\" y = \"" + 20*i + "\" width=\"20\" height=\"20\" style=\"fill:rgb("+colour+")\" />");
				}
			}
		}
		for (int i = 0; i < side; i++) {
			for (int j = 0; j < halfSide-1; j++) {
				if(field.get(color + halfSide*i + j)) {
					System.out.println("<rect x = \"" + (120 - 20*j) + "\" y = \"" + 20*i + "\" width=\"20\" height=\"20\" style=\"fill:rgb("+colour+")\" />");
				}	
			}
		}
	}


	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Random rnd = new Random(in.nextLine().hashCode());
		long seed = rnd.nextLong();
		BitSet field = BitSet.valueOf(new long[]{seed});
		System.out.println(field.toString());
		int color = 0;
		if (field.get(0)) {
			color += Math.pow(2, COLOR - 1);
		}
		if (field.get(1)) {
			color += Math.pow(2, COLOR - 2);
		}
		if (field.get(2)) {
			color++;
		}
		int[] rgbColor = chooseColor(color);
		System.out.println("<html><body><svg width=\"140\" height=\"140\">");
		drawIdenticon(field, rgbColor);
		System.out.println("</svg></body></html>");
	}

}

