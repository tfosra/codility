package vanadium;

public class Main {

	public int solution(String S) {
		int N = S.length();
		int res = 0;
		int k;
		int tmp;
		
		int[][] occ = new int[10][5];
		
		for (int i = 0; i < N; i++) {
			k = (int) S.charAt(i) - 48;
			res += i;
			for (int j = 0; j < 10; j++) {
				if (j == k) {
					occ[j][4]++;
					tmp = occ[j][2]; // Swap odd and even numbers
					occ[j][2] = occ[j][3] + i - occ[j][1];
					occ[j][3] = tmp;
					occ[j][1] = i;
				}
				int oddScenarios = occ[j][1] / 2 + ((i + 1) % 2);
				int evenScenarios = occ[j][1] + 1 - oddScenarios;
				res -= Math.min(evenScenarios, occ[j][2]);
				res += Math.max(oddScenarios, occ[j][3]);
			}
		}
		
		
//		int cache = 0;
//		for (int i = 0; i < N; i++) {
//			res++;
//			k = (int) S.charAt(i) - 48;
//			cache ^= (1 << k);
//
//		}

		return res;
	}

	public boolean match(int n) {
		int found = 0;
		while (n > 0) {
			if ((n & 1) == 1)
				found++;
			if (found > 1)
				break;
			n = n >> 1;
		}
		return found < 2;
	}
	
	public static void main(String args[]) {
		String str = "20020";
		System.out.println(new Main().solution(str));
	}
	
}
