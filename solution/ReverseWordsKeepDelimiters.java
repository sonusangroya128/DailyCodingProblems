/**
 * Problem: Given a string and a set of delimiters, reverse the words
 *  in the string while maintaining the relative order of the delimiters.
 *  For example, given "hello/world:here", return "here/world:hello"
 *   Follow-up: Does your solution work for the following cases:
 *     "hello/world:here/", "hello//world:here"
 * Approach: Use stack to store words and queue to store delimiters.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * @author Sonu Sangroya
 * @since 2020-12-27
 */
import java.lang.StringBuilder;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ReverseWordsKeepDelimiters {

    public static void main(String[] args) {
        String s = "hello/world:here/";
        System.out.println(reverseWords(s));
    }

    public static String reverseWords(String s) {
        Stack<String> stack = new Stack<>();
        Queue<String> queue = new LinkedList<>();

        int i = 0, j = 0;

        // this part of the algorithm assumes string starts with word ie no leading
        // delimiters
        while(j < s.length()) {
            if(isNonChar(s.charAt(j))) {
                stack.push(s.substring(i,j));
                i = j;
                j++;

                // tracking continuous delimiter to add to queue
                while(j < s.length() && isNonChar(s.charAt(j))) {
                    j++;
                }
                queue.add(s.substring(i,j));
                i = j;
            }
            else {
                j++;
            }
        }

        // if string ends with words
        if(i != j) {
            System.out.println(s.length());
            System.out.println(j);
            stack.push(s.substring(i,j));
        }

        StringBuilder builder = new StringBuilder();

        while(!stack.isEmpty() || !queue.isEmpty()) {
            if(!stack.isEmpty()) {
                builder.append(stack.pop());
            }
            if(!queue.isEmpty()) {
                builder.append(queue.remove());
            }
        }

        return builder.toString();
    }

    public static boolean isNonChar(char j) {
        return j < 'A' || j > 'Z' && j < 'a' || j > 'z';
    }
}

/*
Output:
——————————————————————
here/world:hello/

*/
