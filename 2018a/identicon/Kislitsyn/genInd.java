package indentic;

import java.util.Random;

public class genInd {

	public static void main(String[] args) {
		int seed = 566;
		Random random = new Random();
		int[][][] rgbInd = new int[10][10][3];
		for(int i = 0; i < 10 ; i++){
			for(int j = 0; j < 10; j++){
				for(int k = 0; k < 3; k++){
					rgbInd[i][j][k] = 200;
				}
			}
		}
		int[] color = {random.nextInt(256), random.nextInt(256), random.nextInt(256)};
		int centrX = random.nextInt(3) + 1;
		int centrY = random.nextInt(3) + 1;
		for(int k = 0; k < 3; k++){
			rgbInd[centrX][centrY][k] = (color[k] + 566)%256;
		}
		for(int i = 0; i < 10 ; i++){
			for(int j = 0; j < 10; j++){
				if(random.nextBoolean() && !(centrX == i && centrY == j)){
					for(int k = 0; k < 3; k++){
						rgbInd[i][j][k] = color[k];
					}
				}
			}
		}
		for(int i = 5; i < 10 ; i++){
			for(int j = 0; j < 10; j++){
				for(int k = 0; k < 3; k++){
					rgbInd[i][j][k] = rgbInd[9 - i][j][k];
				}
			}
		}
		for(int i = 0; i < 10 ; i++){
			for(int j = 0; j < 10; j++){
				String strFill =  "fill=\"rgb(" + rgbInd[i][j][0] + " , " + rgbInd[i][j][1] + " , " + rgbInd[i][j][2] + ")\"";
				System.out.println("<rect x=\"" + i * 30 + "\"" + " y=\"" + j * 30 + "\"" + " width=\"30\" height=\"30\" " + strFill+" />");
			}
		}
	}

}
