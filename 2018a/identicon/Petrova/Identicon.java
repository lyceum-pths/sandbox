import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;

public class Identicon {
	static int t = 40;
	static int d = 5;
	static int size = 100;
	static int number = 100;
	static int m1 = 5;
	static int m2 = 20;
	static String shapes[] = {"circle", "rect","poligon"};
	static String colors[] = {"aquamarine", "bisque", "blanchedalmond","blueviolet", "brown", "burlywood", "cadetblue" ,"chartreuse", "chocolate", "coral", "cornflowerblue", "cornsilk", "crimson", "darkblue", "darkcyan", "darkgoldenrod", "darkgray", "darkgreen", "darkgrey", "darkkhaki", "darkmagenta", "darkolivegreen", "darkorange", "darkorchid", "darkred", "darksalmon", "darkseagreen", "darkslateblue","darkslategray", "darkslategrey", "darkturquoise", "darkviolet", "deeppink", "deepskyblue", "dimgray", "dimgrey", "dodgerblue", "firebrick", "floralwhite", "forestgreen", "gainsboro", "ghostwhite", "gold", "goldenrod", "gray(16)", "green(16)", "greenyellow", "grey(16)", "honeydew", "hotpink", "indianred", "indigo", "ivory", "khaki", "lavender", "lavenderblush", "lawngreen", "lemonchiffon", "lightblue", "lightcoral", "lightcyan", "lightgoldenrodyellow","lightgray", "lightgreen", "lightgrey", "lightpink", "lightsalmon", "lightseagreen", "lightskyblue", "lightsteelblue", "lime(Safe 16 Hex3)", "limegreen", "maroon(16)", "mediumaquamarine", "mediumblue", "mediumorchid" ,"mediumpurple", "mediumseagreen", "mediumslateblue", "mediumspringgreen", "mediumturquoise", "mediumvioletred", "midnightblue", "mistyrose", "navajowhite", "navy(16)", "oldlace", "olive(16)", "olivedrab", "orange", "orangered", "orchid", "palegoldenrod", "palegreen", "paleturquoise", "palevioletred", "peachpuff", "peru", "pink", "plum", "powderblue", "purple(16)", "red", "rosybrown", "royalblue", "saddlebrown", "salmon", "sandybrown", "seagreen", "sienna", "silver(16)", "skyblue", "slateblue", "slategray", "slategrey", "springgreen", "steelblue", "tan", "teal)", "thistle", "tomato", "turquoise", "violet", "yellow", "yellowgreen"};
	public static void main(String[] args) throws FileNotFoundException {
		PrintWriter out = new PrintWriter(new File("output.html"));
		int richness = 0;
		String Color = "";
		String Color1 = "";
		double[][] rotationmatrix1 = new double[2][2];
		rotationmatrix1[0][0] = Math.cos(2 * Math.PI/4);
		rotationmatrix1[1][1] = Math.cos(2 * Math.PI/4);
		rotationmatrix1[1][0] = - Math.sin(2 * Math.PI/4);
		rotationmatrix1[0][1] = Math.sin(2 * Math.PI/4);
		for(int i = 0; i < number; i++) {
			Random rand = new Random();
			String shape1  = shapes[rand.nextInt(shapes.length)];
			int [] dots1 = new int[16];
			if(shape1.equals("rect")) {
				dots1[0] = size/4 + rand.nextInt(size/4);
				dots1[1] = size - 2 * dots1[0];
			} else if(shape1.equals("circle")) {
				dots1[0] = size/2;
				dots1[1] = m1 + rand.nextInt(m2);
			} else {
				dots1[0] = rand.nextInt(size/4);
				dots1[1] = rand.nextInt(size/4);
				dots1[2] = size/4;
				dots1[3] = rand.nextInt(size/4);
				for(int j = 4; j < 16; j = j + 2) {
					dots1[j] = (int) (dots1[j-4] * rotationmatrix1[0][0] + dots1[j - 3]*rotationmatrix1[0][1]);
					dots1[j + 1] = (int) (dots1[j-4] * rotationmatrix1[1][0] + dots1[j - 3]*rotationmatrix1[1][1]);
					
				}
				for(int j = 0; j < 16; j++) {
					dots1[j] = dots1[j] + size/2;
				}
			}
			int k = rand.nextInt(6) + 2;
			double[][] rotationmatrix = new double[2][2];
			rotationmatrix[0][0] = Math.cos(2 * Math.PI/k);
			rotationmatrix[1][1] = Math.cos(2 * Math.PI/k);
			rotationmatrix[1][0] = - Math.sin(2 * Math.PI/k);
			rotationmatrix[0][1] = Math.sin(2 * Math.PI/k);
		
			int poligon [] =  new int[16];
			poligon[0] = rand.nextInt(size/4);
			poligon[1] = rand.nextInt(size/4);
			poligon[2] = size/2;
			poligon[3] = rand.nextInt(size/4);
			for(int j = 4; j < 16; j = j + 2) {
				poligon[j] = (int) (poligon[j-4] * rotationmatrix1[0][0] + poligon[j - 3]*rotationmatrix1[0][1]);
				poligon[j + 1] = (int) (poligon[j-4] * rotationmatrix1[1][0] + poligon[j - 3]*rotationmatrix1[1][1]);
				
			}
			for(int j = 0; j < 16; j++) {
				poligon[j] = poligon[j] + size/2;
			}
			
			int triangle[][] = new int[k][6];
			for (int j = 0; j < 6; j++) {
				triangle[0][j] =rand.nextInt(t);
			}
			for(int g = 1; g < k; g++) {
				for(int j = 0; j < 6; j++) {
					if(j%2 == 0 ) {
						triangle[g][j] = (int) (triangle[g-1][j] * rotationmatrix[0][0] + triangle[g-1][j + 1]*rotationmatrix[0][1]);
					} else {
						triangle[g][j] = (int) (triangle[g-1][j-1] * rotationmatrix[1][0] + triangle[g-1][j]*rotationmatrix[1][1]);
					}
				}
			}
			for(int g = 0; g < k; g++) {
				for(int j = 0; j < 6; j++) {
					triangle[g][j] = triangle[g][j] + size/2;
				}
			}
			Color = colors[rand.nextInt(colors.length)];
			Color1 = colors[rand.nextInt(colors.length)];
			
			out.println("<html>");
			out.println("<body>");
			out.println("<svg width=\"" + size + "\" height=\"" + "\">\"");
			richness = rand.nextInt(3);
			if(shape1.equals("rect")) {
				out.println("<" + shape1 + " x=\"" + dots1[0] + "\" y=\"" + dots1[0] + "\" width=\"" + dots1[1] +  "\" height=\"" + dots1[1] +  "\" stroke=\"" + Color + "\" fill=\"" + Color1 + "\" stroke-width=\"" + richness + "\"/>");
			} else if(shape1.equals("circle")) {
				out.print("<circle cx=\"" + dots1[0] + "\" cy=\"" + dots1[0] + "\" r=\"" + dots1[1] + "\" stroke=\"" + Color + "\" fill=\"" + Color1 + "\" stroke-width=\"" + richness + "\"/>");
			} else {
				out.print("<polygon points=\"" + dots1[0]  +" "+ dots1[1]  +" "+ dots1[2]  +" "+ dots1[3]  +" "+ dots1[4]  +" "+ dots1[5]  +" "+ dots1[6]  +" "+ dots1[7]  +" "+ dots1[8]  +" "+ dots1[9]  +" "+dots1[10]  +" "+ dots1[11] +" "+ dots1[12]  +" "+ dots1[13]  +" "+ dots1[14]  +" "+ dots1[15] +"\" stroke=\"" + Color + "\" fill=\"" + Color1 + "\" stroke-width=\"" + richness + "\"/>");
			}
			Color = colors[rand.nextInt(colors.length)];
			Color1 = "transparent";
			richness = 5 + rand.nextInt(3);
			out.println("<polygon points=\"" + poligon[0]  +" "+ poligon[1]  +" "+ poligon[2]  +" "+ poligon[3]  +" "+ poligon[4]  +" "+ poligon[5]  +" "+ poligon[6]  +" "+ poligon[7]  +" "+ poligon[8]  +" "+ poligon[9]  +" "+poligon[10]  +" "+ poligon[11] +" "+ poligon[12]  +" "+ poligon[13]  +" "+ poligon[14]  +" "+ poligon[15] +"\" stroke=\"" + Color + "\" fill=\"" + Color1 + "\" stroke-width=\"" + richness + "\"/>");
			Color = colors[rand.nextInt(colors.length)];
			Color1 = colors[rand.nextInt(colors.length)];
			richness = rand.nextInt(4);
			for(int g = 0; g < k; g++) {
				out.println("<polygon points=\"" + triangle[g][0]  +" "+ triangle[g][1]  +" "+ triangle[g][2]  +" "+ triangle[g][3]  +" "+ triangle[g][4]  +" "+ triangle[g][5] + "\" stroke=\"" + Color + "\" fill=\"" + Color1 + "\" stroke-width=\"" + richness + "\"/>");
			}
			out.println("</svg> ");
			out.println("</body>");
			out.println("</html>");
			
		
			
			
		}
	out.close();
	}
	
	

}