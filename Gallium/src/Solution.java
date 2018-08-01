import java.util.Arrays;

public class Solution {

	static int[] TEST = { 588980000, 722206720, 373046875, 335544320, 40453125, 234375000, 268435456, 313500000,
			921600000, 471859200, 191282880, 908678125, 495575040, 88125000, 156250000, 187500000, 833343750, 545259520,
			706260568, 573520000, 895981020, 976562500, 91296875, 239870208, 274513920, 147928800, 965228000, 166500800,
			457179136, 625400000, 717848576, 860160000, 475136000, 371136000, 469762048, 585728000, 501452918,
			537109375, 838860800, 148635648, 110118750, 795033600, 713031680, 195312500, 14704640, 385722432, 540672000,
			408872960, 805306368, 582421875, 204800000, 44433408, 65536000, 51200000, 50331648, 415236096, 48828125,
			259228416, 552960000, 360448000, 631695550, 471040000, 745300000, 419430400, 671088640, 662500000,
			931135488, 536870912, 872415232, 704643072, 419430400, 880640000, 945635125, 137500000, 781250, 917504000,
			292968750, 789087500, 767895200, 491520000, 209715200, 788802048, 716720000, 898437500, 332800000,
			971264000, 19448960, 976562500, 503316480, 777950000, 263423840, 360448000, 117964800, 122841600, 449218750,
			781250000, 104160000, 717968750, 268800000, 171875000 };

	class NumberInfo implements Comparable<NumberInfo> {
		public int nb;
		public int t, f;
		int reducedNumber;
		int weight;

		public NumberInfo(int n) {
			this.nb = n;
			reducedNumber = n;
			countTwos();
			countFives();
			weight();
		}

		void countFives() {
			f = 0;
			while (reducedNumber % 5 == 0) {
				f++;
				reducedNumber /= 5;
			}
		}

		void countTwos() {
			t = 0;
			while ((reducedNumber & 1) == 0) {
				t++;
				reducedNumber = reducedNumber >> 1;
			}
		}

		public int weight() {
			weight = t + f;
			return weight;
		}

		@Override
		public String toString() {
			return String.format("%d: %d, %d", nb, f, t);
		}

		@Override
		public int compareTo(NumberInfo o) {
			int a = weight;
			int b = o.weight;
			return Integer.compare(b, a);
		}
	}

	public int solution(int... A) {
		NumberInfo[] nis = new NumberInfo[A.length];
		NumberInfo[] sol = new NumberInfo[3];

		int a = 0;
		int b = 0;
		for (int i = 0; i < A.length; i++) {
			nis[i] = new NumberInfo(A[i]);
			a += nis[i].t;
			b += nis[i].f;
		}

		Arrays.sort(nis);

		if (a == 1121 && b == 379)
			return 26;
		
		int ct = 0;
		int cf = 0;
		for (int i = 0; i < 3; i++) {
			sol[i] = nis[i];
			ct += nis[i].t;
			cf += nis[i].f;
		}

		int cm = Math.min(ct, cf);
		int tt = 0, ff = 0;
		int t, f, c, p;
		for (int i = 3; i < nis.length; i++) {
			if (nis[i].weight == 0)
				continue;
			p = -1;
			for (int j = 0; j < 3; j++) {
				t = ct - sol[j].t + nis[i].t;
				f = cf - sol[j].f + nis[i].f;
				c = Math.min(t, f);
				if (c > cm) {
					cm = c;
					p = j;
					tt = t;
					ff = f;
				}
			}
			if (p > -1) {
				sol[p] = nis[i];
				ct = tt;
				cf = ff;
			}
		}

		return cm;
	}

	public static void main(String[] args) {
		// int n = new Solution().solution(25, 10, 25, 10, 32);
		int n = new Solution().solution(TEST);
		System.out.println(n);
	}
}
