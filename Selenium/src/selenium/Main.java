package selenium;

public class Main {

	private static final int MOD = 1000000007;

	public static int solution(int[] X, int[] Y) {
		int N = X.length;
		int[] tmpX = new int[N];
		int[] tmpY = new int[N];
		for (int i = 0; i < N; i++) {
			tmpX[X[i] - 1]++;
			tmpY[Y[i] - 1]++;
		}

		return partial_solution(tmpX) + partial_solution(tmpY);
	}

	public static int partial_solution(int[] A) {
		int N = A.length;
		int res = 0;
		int emptyIndex = 0;
		int emptySpots = 0;
		int toShift;
		for (int i = 0; i < N; i++) {
			if (A[i] != 0) {
				emptySpots = i - emptyIndex;
				if (emptySpots > 0) {
					toShift = Math.min(emptySpots, A[i]);
					A[i] -= toShift;

					res += toShift * (2 * emptySpots - toShift + 1) / 2;
					res %= MOD;
					emptyIndex += toShift;
				}
				if (A[i] > 0)
					emptyIndex++;
				if (A[i] > 1) {
					toShift = A[i] - 1;
					A[i + 1] += toShift;
					res += toShift;
					res %= MOD;
				}
			}
		}
		return res;
	}

	public static void main(String[] args) {
		int[] X = { 1, 1, 2 };
		int[] Y = { 1, 2, 1 };
		System.out.println(solution(X, Y));
	}
}
