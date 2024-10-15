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
    private static long window;
    private static String sequence;
    private static String STR;
    private static int[] vals;
    private static long STRVal;
    private static int seqLen;
    private static int STRLen;
    private static boolean[] hasSTR;
    private static int[] numRepeats;

    public static int STRCount(String sequence, String STR) {
        initInstanceVars(sequence, STR);

        // hasSTR[i] == true when there is a STR starting from index i
        // loop through sequence checking where there are STRs
        for (int i = 0; i <= seqLen - STRLen; i++) {
            if (window == STRVal) {
                hasSTR[i] = true;
            }
            // Shift
            window = window << 2;
            window += vals[sequence.charAt(i + 1) - 'A'];
        }

        // numRepeats[i] is the number of times STR is repeated starting from index i
        numRepeats = new int[seqLen];
        int max = 0;
        // Looping bakcward makes the logic easier
        if (hasSTR[seqLen  - STRLen]) {
            numRepeats[seqLen - STRLen] = 1;
        }
        for (int i = seqLen - STRLen - 1; i >= 0; i--) {
            if (hasSTR[i]) {
                numRepeats[i] = 1 + numRepeats[i+STRLen];
                if (numRepeats[i] > max) {
                    max = numRepeats[i];
                }
            }
        }
        return max;
    }

    public static void initInstanceVars(String seq, String str) {
        sequence = seq;
        STR = str;
        seqLen = sequence.length();
        STRLen = STR.length();
        hasSTR = new boolean[seqLen];
        window = 0;
        STRVal = 0;

        vals = new int[21];
        vals['A'-'A'] = 0;
        vals['C'-'A'] = 1;
        vals['G'-'A'] = 2;
        vals['T'-'A'] = 3;

        for (int i = 0; i < STRLen; i++) {
            STRVal = STRVal << 2;
            STRVal += vals[STR.charAt(i) - 'A'];
        }

        for (int i = 0; i < STRLen; i++) {
            window = window << 2;
            window += vals[seq.charAt(i)-'A'];
        }
    }
}