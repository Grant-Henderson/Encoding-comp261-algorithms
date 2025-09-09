import java.util.*;

public class LempelZiv {
    /**
     * Take uncompressed input as a text string, compress it, and return it as a
     * text string.
     */
    public static String compress(String input) {
        // TODO fill this in.
        if (input == null || input.isEmpty()) {
            return "";
        }

        // initialize dictionary with single characters
        Map<String, Integer> dictionary = new HashMap<>();
        int dictSize = 256;
        for (int i = 0; i < 256; i++) {
            dictionary.put("" + (char)i, i);
        }

        StringBuilder compressed = new StringBuilder();
        String current = "";
        
        for (char c : input.toCharArray()) {
            String combined = current + c;
            if (dictionary.containsKey(combined)) {
                current = combined;
            } else {
               
                compressed.append(dictionary.get(current)).append(",");
                dictionary.put(combined, dictSize++);
                current = "" + c;
            }
        }

        // output the last code
        if (!current.isEmpty()) {
            compressed.append(dictionary.get(current));
        } else if (compressed.length() > 0) {
            // remove the trailing comma
            compressed.deleteCharAt(compressed.length() - 1);
        }

        return compressed.toString();
    }

    /**
     * Take compressed input as a text string, decompress it, and return it as a
     * text string.
     */
    public static String decompress(String compressed) {
        // TODO fill this in.
        //if (compressed == null || compressed.isEmpty()) {
           // return "";
       // }

        String[] codes = compressed.split(",");
        
        Map<Integer, String> dictionary = new HashMap<>();
        int dictSize = 256;
        for (int i = 0; i < 256; i++) {
            dictionary.put(i, "" + (char)i);
        }
        
        String previous = "" + (char)Integer.parseInt(codes[0]);
        StringBuilder decompressed = new StringBuilder(previous);
        
        for (int i = 1; i < codes.length; i++) {
            int code = Integer.parseInt(codes[i]);
            String current;
            if (dictionary.containsKey(code)) {
                current = dictionary.get(code);
            } else if (code == dictSize) {
               
                current = previous + previous.charAt(0);
            } else {
                throw new IllegalArgumentException("Invalid compressed string");
            }
            
            decompressed.append(current);
            
            
            dictionary.put(dictSize++, previous + current.charAt(0));
            previous = current;
        }

        return decompressed.toString();
    }

    /**
     * The getInformation method is here for your convenience, you don't need to
     * fill it in if you don't want to. It is called on every run and its return
     * value is displayed on-screen. You can use this to print out any relevant
     * information from your compression.
     */
    public String getInformation() {
        return "";
    }
}