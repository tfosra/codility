package decoding_master;

import java.util.HashSet;
import java.util.Set;

public class Main {

	public int domino(int A[], int B[]) {
		int N = A.length;
		int pairs[] = new int[7];
		int singles[] = new int[7];
		for (int i = 0; i < N; i++) {
			if (A[i] == B[i])
				pairs[A[i]] += 2;
			else {
				singles[A[i]]++;
				singles[B[i]]++;
			}
		}
		int res = 0;
		for (int i = 0; i < 7; i++) {
			res = Math.max(res, pairs[i] + Math.min(singles[i], 2));
		}
		return res;
	}
	
	public static int candy(int A[], int B[]) {
		int N = A.length;
		int N2 = N / 2;
		Set<Integer> setA = new HashSet<>();
		Set<Integer> setB = new HashSet<>();
		int onlyA = N;
		int onlyB = N;
		int both = 0;
		for (int i = 0; i < N; i++) {
			if (setA.add(A[i])) {
				if (setB.contains(A[i])) {
					both++;
					onlyA--;
					onlyB--;
				}
			} else {
				onlyA--;
			}
			if (setB.add(B[i])) {
				if (setA.contains(B[i])) {
					both++;
					onlyA--;
					onlyB--;
				}
			} else {
				onlyB--;
			}
		}
		onlyA = Math.min(onlyA, N2);
		onlyB = Math.min(onlyB, N2);
		return Math.min(onlyA + onlyB + both, N);
	}

	public static void main(String args[]) {
		int[] A = {2, 2, 2, 2};
		int[] B = {3, 2, 4, 7};
		System.out.println(candy(A, B));
	}
	
}
