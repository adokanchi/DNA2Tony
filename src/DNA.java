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

    public static int STRCount(String sequence, String STR) {
        initInstanceVars(sequence, STR);

        int maxCount = 0;

        // Set up window
        for (int i = 0; i < STRLen; i++) {
            window = window << 2;
            window += vals[STR.charAt(i)-'A'];
        }

        for (int i = 0; i < seqLen; i++) {
           //
            if (window == STRVal) {
                hasSTR[i] = true;
            }
        }



        return maxCount;
    }

    public static void initInstanceVars(String seq, String str) {
        sequence = seq;
        STR = str;
        vals = new int[21];
        vals['A'-'A'] = 0;
        vals['C'-'A'] = 1;
        vals['G'-'A'] = 2;
        vals['T'-'A'] = 3;
        seqLen = sequence.length();
        STRLen = STR.length();
        hasSTR = new boolean[seqLen];
        window = 0;
        STRVal = 0;
        for (int i = 0; i < STRLen; i++) {
            STRVal = STRVal << 2;
            STRVal += vals[STR.charAt(i) - 'A'];
        }
    }

    public static int shift(int i) {

    }
}
