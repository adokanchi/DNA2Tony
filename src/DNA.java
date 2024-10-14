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
        int[] vals = new int[21];
        vals['a'-'a'] = 0;
        vals['c'-'a'] = 1;
        vals['g'-'a'] = 2;
        vals['t'-'a'] = 3;

        int maxCount = 0;

        int seqLen = sequence.length();
        int STRLen = STR.length();



        for (int i = 0; i < seqLen; i++) {

        }



        return maxCount;
    }
}
