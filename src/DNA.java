/**
 * DNA
 * <p>
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *</p>
 * <p>
 * Completed by: Tony Dokanchi
 *</p>
 */
public class DNA {
    private static int window;
    private static int[] vals;
    private static String seq;
    public static int STRCount(String sequence, String STR) {
        // Initialize variables
        seq = sequence;
        int seqLen = sequence.length();
        int STRLen = STR.length();
        vals = new int['t'+1];
        vals['c'] = 1;
        vals['g'] = 2;
        vals['t'] = 3;
        vals['C'] = 1;
        vals['G'] = 2;
        vals['T'] = 3;
        int STRVal = 0;
        window = 0;
        for (int i = 0; i < STRLen; i++) {
            STRVal = (STRVal << 2) | vals[STR.charAt(i)];
            shift(sequence.charAt(i));
        }

        // Iterate through sequence, finding repeats of STRs (skips after finding an STR and doesn't check last place)
        // max is the number of STRs in the longest string of repeats
        int max = 0;
        int counter = 0;
        int windowMask = (1 << 2 * STRLen) - 1;
        int lastIndex = seqLen - STRLen;
        for (int i = 0; i < lastIndex; i++) {
            // If the hashes match
            if (window == STRVal) {
                if (++counter > max) max = counter;
                // Shift STRLen times
                // All of window is overwritten, so set window to 0 once instead of repeatedly overwriting bits
                window = 0;
                for (int j = 0; j < STRLen; j++) {
                    shift(i + j + 1);
                }
                // - 1 because i is incremented in the loop
                i += STRLen - 1;
            }
            else {
                counter = 0;
                // Shift
                shift(i + 1);
                window = window & windowMask;
            }
        }

        // Check final window position
        if (window == STRVal && ++counter > max) {
            max = counter;
        }
        return max;
    }

    // Shifts window to add the character at index i
    public static void shift(int i) {
        window = (window << 2) | vals[seq.charAt(i)];
    }
}