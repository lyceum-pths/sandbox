import java.util.*;

public class AnnealingAnishchenko {
	static int SIZE;
	static int START_TEMPERATURE;
	static final double FINISH_TEMPERATURE = 0.000001;
	static int COUNT_ITERATIONS;
	static final double ALPHA = 0.98;

	static Cell currSolution;

	static Random random;

	public static class Cell {
		int[] board;
		int energy;

		public Cell() {
			board = new int[SIZE];

			for (int i = 0; i < SIZE; i++) {
				board[i] = i;
			}

			energy = SIZE * (SIZE - 1) / 2;
		}

		public Cell(Cell that) {
			this.board = combination(that.board.clone());
			this.energy = getEnergy(this.board);
		}
	}

	public static void main(String[] args) {
		init();

		solve();

		prolemResult();
		
		//result();
	}

	private static void prolemResult() {
		for (int i = 0; i < SIZE; i++) {
			System.out.print(SIZE - currSolution.board[i] + " ");
		}
	}

	public static int getEnergy(int[] board) {
		int energy = 0;

		int[] leftDiagonal = new int[2 * SIZE - 1];
		int[] rightDiagonal = new int[2 * SIZE - 1];

		for (int i = 0; i < SIZE; i++) {
			leftDiagonal[i + (SIZE - board[i] - 1)]++;
			rightDiagonal[i + board[i]]++;
		}

		for (int i = 0; i < 2 * SIZE - 1; i++) {
			energy += leftDiagonal[i] * (leftDiagonal[i] - 1) / 2;
			energy += rightDiagonal[i] * (rightDiagonal[i] - 1) / 2;
		}

		return energy;
	}

	public static int[] combination(int[] board) {
		int firstPosition = random.nextInt(SIZE);
		int secondPosition = 0;

		do {
			secondPosition = random.nextInt(SIZE);
		} while (firstPosition == secondPosition);

		int temp = board[secondPosition];
		board[secondPosition] = board[firstPosition];
		board[firstPosition] = temp;

		return board;
	}

	private static void result() {
		int[] ans = new int[SIZE];

		for (int i = 0; i < SIZE; i++) {
			ans[currSolution.board[i]] = i;
		}

		System.out.println(currSolution.energy);

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < ans[i]; j++) {
				System.out.print("#");
			}

			System.out.print("Q");

			for (int j = ans[i] + 1; j < SIZE; j++) {
				System.out.print("#");
			}

			System.out.println();
		}

		System.out.close();
	}

	private static void init() {
		Scanner in = new Scanner(System.in);

		SIZE = in.nextInt();
		START_TEMPERATURE = SIZE * SIZE * SIZE;
		COUNT_ITERATIONS = 10000;

		random = new Random((int) (SIZE * SIZE * SIZE * ALPHA));

		currSolution = new Cell();

		in.close();
	}

	public static void solve() {
		double temperature = START_TEMPERATURE;

		int i = 0;

		while (true) {
			i++;

			for (int step = 1; step < COUNT_ITERATIONS; step++) {
				Cell newSolution = new Cell(currSolution);
				if (newSolution.energy <= currSolution.energy) {
					currSolution = newSolution;
				} else {
					double p = Math.exp(-(newSolution.energy - currSolution.energy) / temperature);

					if (p > random.nextDouble()) {
						currSolution = newSolution;
					}
				}
			}

			temperature = getT(temperature, i);

			if (temperature <= FINISH_TEMPERATURE) {
				break;
			}

		}
	}

	public static double getT(double temperature, int step) {
		return ALPHA * temperature / step;
	}
}