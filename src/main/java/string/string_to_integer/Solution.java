package string.string_to_integer;

class Solution {

//    The algorithm for myAtoi(string s) is as follows:
//
//Read in and ignore any leading whitespace.
//Check if the next character (if not already at the end of the string) is '-' or '+'.
// Read this character in if it is either. This determines if the final result is negative or positive respectively.
// Assume the result is positive if neither is present.
//Read in next the characters until the next non-digit character or the end of the input is reached.
// The rest of the string is ignored.
//Convert these digits into an integer (i.e. "123" -> 123, "0032" -> 32).
// If no digits were read, then the integer is 0. Change the sign as necessary (from step 2).
//If the integer is out of the 32-bit signed integer range [-231, 231 - 1],
// then clamp the integer so that it remains in the range. Specifically,
// integers less than -231 should be clamped to -231, and integers greater than 231 - 1 should be clamped to 231 - 1.
//Return the integer as the final result.
    
    // comments

    public int myAtoi(String s) {
        StringBuilder expression = new StringBuilder();
        StringBuilder digits = new StringBuilder();
        char[] chars = getCharArray(s, expression);
        for (char ch : chars) {
            if (ch >= 48 && ch <= 57) {
                if (digits.length() == 0 && ch == 48) {
                    continue;
                }
                digits.append(ch);
                if (digits.length() > 10) {
                    break;
                }
            } else break;
        }
        if (digits.length() == 0) {
            return 0;
        }
        long result = Long.parseLong(expression.append(digits).toString());
        if (result < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        if (result > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        return (int) result;
    }

    public char[] getCharArray(String s, StringBuilder sb) {
        int index = 0;
        String str = s.stripLeading();
        if (str.startsWith("-")) {
            sb.append("-");
            index = 1;
        }
        if (str.startsWith("+")) {
            index = 1;
        }
        return str.substring(index).toCharArray();
    }
}
