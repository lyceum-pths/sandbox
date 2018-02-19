import java.util.Random;

public class Identicon {

	public static String createRandomHex(Random random) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("#");
		for (int i = 0; i < 6; i++) {
			int t = random.nextInt(16);
			if (t < 10) {
				buffer.append(t);
			} else {
				buffer.append((char) ('A' + (t - 10)));
			}
		}

		return buffer.toString();
	}
	
	

	public static String genIdenticon(int size, String seed) {
		Random random = new Random(seed.hashCode());
		
		StringBuilder out = new StringBuilder();

		out.append("<svg height=\"" + size + "\" width=\"" + size + "\" style=\"background: " + createRandomHex(random)
				+ "\"> ");

		int numberOfLines = random.nextInt(10) + 10;
		double dispersion = random.nextDouble() * 3;
		double peak = random.nextDouble() * 2 * Math.PI;

		for (int i = 0; i < numberOfLines; i++) {
			double angle = i * 2 * Math.PI / numberOfLines;
			double length = size / 2
					/ Math.cos(Math.min(angle % (Math.PI / 2), (Math.PI / 2) - (angle % (Math.PI / 2))))
					* Math.exp(-(angle - peak) * (angle - peak) / (2 * dispersion * dispersion));
			double x2 = size / 2 + length * Math.cos(angle);
			double y2 = size / 2 + length * Math.sin(angle);
			out.append("<line x1=\"" + size / 2 + "\" y1=\"" + size / 2 + "\" x2=\"" + x2 + "\" y2=\"" + y2
					+ "\" style=\"stroke:" + createRandomHex(random) + ";stroke-width:"
					+ (random.nextInt(size / 20) + size / 100) + "\" /> ");
		}

		int pointNumber = random.nextInt(4);

		if (pointNumber == 0) {
			out.append("<circle  cx=\"" + (size / 2) + "\" cy=\"" + (size / 2) + "\" r=\"" + size / 7
					+ "\" style=\"fill:" + createRandomHex(random) + ";stroke-width:" + size / 50 + ";stroke:"
					+ createRandomHex(random) + "\" /> ");
		} else {
			pointNumber += 2;
			out.append("<polygon points=\"");
			for (int i = 0; i < pointNumber; i++) {
				double x2 = size / 2 + size / 7 * Math.cos((double) i * 2 * Math.PI / pointNumber);
				double y2 = size / 2 + size / 7 * Math.sin((double) i * 2 * Math.PI / pointNumber);
				out.append(x2 + "," + y2 + " ");
			}
			out.append("\" style=\"fill:" + createRandomHex(random) + "; stroke: " + createRandomHex(random)
					+ ";stroke-width:" + size / 50 + ";\" /> ");
		}

		out.append("</svg> ");
		return out.toString();
	}
	
public static void main(String[] args) {
	System.out.println(genIdenticon(100, "1"));

}


}
