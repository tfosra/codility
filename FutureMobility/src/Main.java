public class Main {

	private static final int MOD = 1000000007;

	public int solution(int[] A, int[] B) {
		int N = A.length;
		int res = 0;
		int[] move = new int[N];
		int sum = 0;
		for (int i = 0; i < N; i++) {
			move[i] = A[i] - B[i];
			sum += move[i];
		}
		if (sum != 0)
			return -1;
		int i1, i2, tomove, coef;
		for (int i = 0; i < N; i++) {
			if (move[i] == 0)
				continue;
			i1 = i + 1;
			i2 = i + 2;
			coef = ((move[i] ^ 1) < 0) ? -1 : 1;

			if (i1 < N && ((move[i] ^ move[i1]) < 0)) {
				tomove = Math.min(Math.abs(move[i]), Math.abs(move[i1]));
				move[i] += -coef * tomove;
				move[i1] += coef * tomove;
				res = (res + tomove % MOD) % MOD;
			}

			if (i2 < N) {
				if (move[i2] == 0) {
					move[i2] = move[i];
				} else {
					move[i2] -= coef * move[i];
				}
				res = (res + Math.abs(move[i]) % MOD) % MOD;
				move[i] = 0;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		int[] A = { 1, 1, 2, 4, 3};
		int[] B = { 2, 2, 2, 3, 2};
		System.out.println(new Main().solution(A, B));
	}

}
