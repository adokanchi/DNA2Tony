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
    public static int STRCount(String sequence, String STR) {
        // Initialize variables
        int seqLen = sequence.length();
        int STRLen = STR.length();
        long STRVal = 0;
        long window = 0;
        int[] vals = new int['t'+1];
        vals['a'] = 0;
        vals['c'] = 1;
        vals['g'] = 2;
        vals['t'] = 3;
        vals['A'] = 0;
        vals['C'] = 1;
        vals['G'] = 2;
        vals['T'] = 3;
        for (int i = 0; i < STRLen; i++) {
            STRVal = STRVal << 2;
            STRVal += vals[STR.charAt(i)];
        }
        for (int i = 0; i < STRLen; i++) {
            window = window << 2;
            window += vals[sequence.charAt(i)];
        }

        // numRepeats[i] is the number of times STR is repeated ending at index i (last STR starts at i)
        int[] numRepeats = new int[seqLen];
        int max = 0;

        /*
        for (int i = 0; i <= seqLen - STRLen; i++) {
            if (window == STRVal) {
                if (i-STRLen >= 0) {
                    numRepeats[i] = 1 + numRepeats[i-STRLen];
                }
                else {
                    numRepeats[i] = 1;
                }
                if (numRepeats[i] > max) max = numRepeats[i];
            }
            // Shift
            window = window << 2;
            window += vals[sequence.charAt(i + 1)];
            window = window % (((long) 1) << (2 * STRLen));
        }
         */

        for (int i = 0; i <= seqLen - STRLen; i++) {
            // If the hashes match
            if (window == STRVal) {
                // Find numRepeats[i], possibly update max
                if (i - STRLen >= 0) {
                    numRepeats[i] = 1 + numRepeats[i - STRLen];
                }
                else {
                    numRepeats[i] = 1;
                }
                if (numRepeats[i] > max) max = numRepeats[i];
                // Shift STRLen times (do STRLen - 1 shifts, one more happens at the end)
                for (int j = 0; j < STRLen - 1; j++) {
                    if (i + j  + 1 < seqLen) {
                        window = window << 2;
                        window += vals[sequence.charAt(i + j + 1)];
                        window = window % (((long) 1) << (2 * STRLen));
                    }
                }
                i += STRLen - 1;
            }
            // Shift
            window = window << 2;
            if (i + 1 < seqLen) window += vals[sequence.charAt(i + 1)];
            window = window % (((long) 1) << (2 * STRLen));
        }

        return max;
    }
}