package grand_challenge;

import java.util.HashSet;
import java.util.Set;

public class Main {
	
	public static int solution(String S) {
		int N = S.length();
		int maxSeq = 0, seq;
		int[] count = new int[26];
		
		int cur = -1, startIndex = 0, lastStreak = 0;
		Set<Integer> set = new HashSet<>();
		int c;
		S = S + "#";
		for (int i = 0; i < N + 1; i++) {
			c = S.charAt(i) - 97;
			if (c != cur) {
				set.add(c);
				if (set.size() == 3) {
					set.remove(c);
					
					seq = partial_solution(S.substring(startIndex, i), count, set.toArray(new Integer[2]));
					maxSeq = Math.max(maxSeq, seq);
					startIndex = i - lastStreak;
					if (S.charAt(i) == '#') {
						break;
					}
					count[c] = 0;
					count[cur] = lastStreak;
					set.clear();
					set.add(cur);
					set.add(c);
				}
				lastStreak = 0;
				cur = c;
			}
			if (S.charAt(i) == '#')
				break;
			count[c]++;
			lastStreak++;
		}
		return maxSeq;
	}
	
	public static int partial_solution(String S, int[] count, Integer[] firstSecond) {
		int first = firstSecond[0];
		int second = firstSecond[1];
		int firstCount = count[first];
		int secondCount = count[second];
		int N = S.length();
		
		if (firstCount == secondCount) {
			return N;
		}
		if (firstCount > secondCount) {
			int tmp = first;
			first = second;
			second = tmp;
		}
		
		int res = 0;
		int l = 0, c;
		int max = 0, skipped = 0;
		boolean skip = false;
		for (int i = 0; i < N; i++) {
			c = S.charAt(i) - 97;
			if (c == first) {
				skip = false;
				res++;
				l = 0;
			} else {
				if (skip) {
					continue;
				}
				l++;
			}
			if (l > count[first] - skipped) {
				skip = true;
				max = Math.max(max, res);
				skipped += res;
				res = 0;
			}
		}
		return 2 * Math.max(max, res);
	}

	
	public static void main(String args[]) {
		String str = "bbbbbbabbbbbbbbbabbbbbbbbbbbbbbbbbbbbbbaabbabbbbbbbbbabbbbbbbbbabbbbbaabbbbbbbbbbbbbbbbbbbabbbbbbbbb";
		System.out.println(Main.solution(str));
	}
}
