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
        boolean[] hasSTR = new boolean[seqLen];
        long window = 0;
        long STRVal = 0;
        int[] vals = new int[21];
        vals['A'-'A'] = 0;
        vals['C'-'A'] = 1;
        vals['G'-'A'] = 2;
        vals['T'-'A'] = 3;
        for (int i = 0; i < STRLen; i++) {
            STRVal = STRVal << 2;
            STRVal += vals[Character.toUpperCase(STR.charAt(i)) - 'A'];
        }
        for (int i = 0; i < STRLen; i++) {
            window = window << 2;
            window += vals[Character.toUpperCase(sequence.charAt(i))-'A'];
        }

        // hasSTR[i] == true when there is a STR starting from index i
        // loop through sequence finding where there are STRs
        for (int i = 0; i <= seqLen - STRLen; i++) {
            if (window == STRVal) hasSTR[i] = true;
            // Shift
            window = window << 2;
            // IDK why I need this if statement but the chromosome test needs it for some rsn
            if (sequence.charAt(i + 1) != 0) window += vals[Character.toUpperCase(sequence.charAt(i + 1)) - 'A'];
            window = window % (((long) 1) << (2 * STRLen));
        }

        // numRepeats[i] is the number of times STR is repeated starting from index i
        int[] numRepeats = new int[seqLen];
        int max = 0;
        if (hasSTR[seqLen  - STRLen]) numRepeats[seqLen - STRLen] = 1;
        // If there are k repeats from index i, there are (k-1) repeats from index i+STRLen
        for (int i = seqLen - STRLen - 1; i >= 0; i--) {
            if (hasSTR[i]) {
                numRepeats[i] = 1 + numRepeats[i+STRLen];
                if (numRepeats[i] > max) max = numRepeats[i];
            }
        }
        return max;
    }
}