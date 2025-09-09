/**
 * A new KMP instance is created for every substring search performed. Both the
 * pattern and the text are passed to the constructor and the search method. You
 * could, for example, use the constructor to create the match table and the
 * search method to perform the search itself.
 */
public class KMP {

	/**
	 * Perform KMP substring search on the given text with the given pattern.
	 * 
	 * This should return the starting index of the first substring match if it
	 * exists, or -1 if it doesn't.
	 */
	public static int search(String pattern, String text) {
		// TODO fill this in.
		int M = pattern.length();
        int N = text.length();

        if (M == 0) return 0;  //some edge case for an empty pattern

        //make an array of size M to hold the pattern 
        //then build the longest prefix suffix table 
        int[] lps = new int[M];
        buildLPSArray(pattern, lps);

        int i = 0; // index for text
        int j = 0; // index for pattern

        while (i < N) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }

            if (j == M) {
                return i - j; // match found
            } else if (i < N && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1]; // apply prefix table
                } else {
                    i++;
                }
            }
        }

        return -1; //match not found
    }

    //this is a method to build the LPS array
    private static void buildLPSArray(String pattern, int[] lps) {
        int length = 0; // length of the previous longest prefix suffix
        lps[0] = 0; // lps[0] is always 0
        
        int i = 1;
        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(length)) {
                length++;
                lps[i] = length;
                i++;
            } else {
                if (length != 0) {
                    length = lps[length - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }
}
