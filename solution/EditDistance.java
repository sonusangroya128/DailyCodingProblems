/**
 * Problem: Find edit distance between two strings
 * Approach: Use Dynamic Programming.
 *    If last characters of two strings are same, nothing much to do.
 *       Ignore last characters and get count for remaining strings. So we recur for lengths i-1 and j-1.
 *    Else (If last characters are not same), we consider all operations on ‘str1’,
 *       consider all three operations on last character of first string, recursively compute minimum cost for all three operations,
 *       and take minimum of three values.
 *          Insert:  Recur for i and j-1
 *          Remove:  Recur for i-1 and j
 *          Replace: Recur for i-1 and j-1
 *
 * Time Complexity: O(mn)
 * Auxiliary Space: O(mn)
 *
 * @author Sonu Sangroya
 * @version 1.0
 * @since 2019-12-27
 */
public class EditDistance {

    static int min(int x, int y, int z) {
        if (x <= y && x <= z)
            return x;
        if (y <= x && y <= z)
            return y;
        else
            return z;
    }

    static int editDistDP(String str1, String str2, int m, int n) {
        int dp[][] = new int[m + 1][n + 1];
        // Fill d[][] in bottom up manner
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0)   // If first string is empty, only option is to insert all characters of second string
                    dp[i][j] = j; // Min. operations = j
                else if (j == 0)  // If second string is empty, only option is to remove all characters of second string
                    dp[i][j] = i; // Min. operations = i
                else if (str1.charAt(i - 1) == str2.charAt(j - 1))  // If last characters are same, ignore last char and recur for remaining string
                    dp[i][j] = dp[i - 1][j - 1];
                    // If the last character is different, consider all possibilities and find the minimum
                else
                    dp[i][j] = 1 + min(dp[i][j - 1], // Insert
                               dp[i - 1][j],         // Remove
                               dp[i - 1][j-1]);      // Replace
            }
        }
        return dp[m][n];
    }

    public static void main(String args[]) {
        String str1 = "sunday";
        String str2 = "saturday";
        System.out.println("Distance between " + str1 + " and " + str2+ " is " + editDistDP(str1, str2, str1.length(), str2.length()));
        str1= "kitten"; str2 = "sitting";
        System.out.println("Distance between " + str1 + " and " + str2+ " is " + editDistDP(str1, str2, str1.length(), str2.length()));
    }

}




/*

Output:
—————————————————————— 

Distance between sunday and saturday is 3
Distance between kitten and sitting is 3


*/
