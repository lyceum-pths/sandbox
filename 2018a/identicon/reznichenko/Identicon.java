import java.util.BitSet;
import java.util.Random;
import java.util.Scanner;

public class Identicon {
	public static int side = 7;//only odds
	public static int size = 30;
	public static int[] determineColor(BitSet plan, int firstBit){
		int step = 70;
		int[] result = new int[3];
		int[] multi = new int[3];
		for(int i = 0; i < 6; i+=2){
			if(plan.get(firstBit + i)){
				multi[i/2]+=2;
			}
			if(plan.get(firstBit + i + 1)){
				multi[i/2]++;
			}
		}
		for(int i = 0; i < 3; i++){
			result[i] = step * multi[i];
		}
		return result;
	}
	public static String genIdenticon(String source){
		Random random = new Random(source.hashCode());
		long forBits = random.nextLong();
		BitSet plan = BitSet.valueOf(new long[]{forBits});
		StringBuilder result = new StringBuilder();
		result.append("<html><body><svg width=\"" + side*size +  "\" height=\"" + side*size + "\">");
		int[] color = determineColor(plan, (side/2)*side);
		for(int i = 0; i < side/2 + 1; i++){
			for(int j = 0; j < side; j++){
				if(plan.get(i*side  + j)){
					result.append("<rect x = \"" + size*i + "\" y = \"" + j*size + "\" width=\"" + size + "\""
							+ " height=\"" + size + "\" style=\"fill:rgb(" + color[0] + "," + color[1] + "," + color[2] + ")\" />");
					if(i != (side/2)){
						result.append("<rect x = \"" + (size*side - size*i - size) + "\" y = \"" + j*size + "\" width=\"" + size + "\""
								+ " height=\"" + size + "\" style=\"fill:rgb(" + color[0] + "," + color[1] + "," + color[2] + ")\" />");	
					}
				}
			}
		}
		result.append("</svg></body></html>");
		return result.toString();
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		for(int i = 0; i < 100; i++){
			System.out.println(genIdenticon(String.valueOf(i)));
			if(i > 0 && i % 5 == 4 )
			System.out.println("</br>");
		}
		
		
		in.close();
	}

}
