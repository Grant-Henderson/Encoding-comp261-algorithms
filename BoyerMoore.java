import java.util.*;

public class BoyerMoore{

	public static int search(String pattern, String text) {
		// TODO fill this in.
		int M = pattern.length();
		int N = text.length();
		int shift = 0;
		if (M == 0 || N == 0 || M > N) {
			return -1;
		}
		
		Map<Character, Integer> badCharTable = new HashMap<>();
		for (int i = 0; i < M; i++) {
			badCharTable.put(pattern.charAt(i),i);
		}
		
		while (shift <= N - M) {
			
			int j = M - 1;
	
			while (j >= 0 && pattern.charAt(j) == text.charAt(shift + j)) {
				j--;
			}
			
			if (j < 0) return shift;
			
			char badChar = text.charAt(shift + j);
			int lastIndex = badCharTable.getOrDefault(badChar, -1);
			int skip = Math.max(1,j - lastIndex);
			
			shift += skip;
		}
		
		return -1;
	}

}
