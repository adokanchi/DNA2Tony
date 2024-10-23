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
    private static int STRVal;
    private static int seqLen;
    private static int STRLen;
    private static int[] vals;
    private static int max;
    private static int[] numRepeats;
    private static int windowMask;
    private static int numChecks;
    private static String seq;
    public static int STRCount(String sequence, String STR) {
        // Initialize variables
        seqLen = sequence.length();
        STRLen = STR.length();
        seq = sequence;
        vals = new int['t'+1];
        vals['c'] = 1;
        vals['g'] = 2;
        vals['t'] = 3;
        vals['C'] = 1;
        vals['G'] = 2;
        vals['T'] = 3;
        STRVal = 0;
        for (int i = 0; i < STRLen; i++) {
            STRVal = STRVal << 2;
            STRVal += vals[STR.charAt(i)];
        }
        window = 0;
        for (int i = 0; i < STRLen; i++) {
            window = window << 2;
            window += vals[sequence.charAt(i)];
        }

        // Iterate through sequence, finding numRepeats[i] for all important values of i (skips after finding an STR and doesn't check last place)
        // numRepeats[i] is the number of times STR is repeated ending at index i (i.e. the last STR starts at i)
        numRepeats = new int[seqLen];
        // max is the number of STRs in the longest string of repeats
        max = 0;
        numChecks = seqLen - STRLen;
        windowMask = (1 << 2 * STRLen) - 1;

        int counter = 0;
        for (int i = 0; i < numChecks; i++) {
            // If the hashes match
            if (window == STRVal) {
                // Find numRepeats[i], possibly update max
                counter++;
                if (counter > max) max = counter;
                // Shift STRLen times (do STRLen - 1 shifts, one more happens at the end)
                // All of window is overwritten, so set window to 0 once instead of repeatedly overwriting bits
                window = 0;
                for (int j = 0; j < STRLen - 1; j++) {
                    shift(i + j + 1);
                }
                i += STRLen - 1;
            }
            else {
                counter = 0;
            }
            // Shift
            shift(i + 1);
            window = window & windowMask;
        }

        // Check final window position
        if (window == STRVal) {
            counter++;
            if (counter > max) {
                max = counter;
            }
        }
        return max;
    }

    // Shifts window to add the character at index i
    public static void shift(int i) {
        window = window << 2;
        window += vals[seq.charAt(i)];
    }
}