/* Medium
 * https://leetcode.com/problems/string-to-integer-atoi/
 */

package com.solutions.leetcode;

public class StringToInteger_8 {

	public static void main(String[] args) {
		StringToInteger_8 r = new StringToInteger_8();
		System.out.println(r.myAtoi("91283472332"));
	}

	public int myAtoi(String str) {

		int start = 0;
		for (; start < str.length() && str.charAt(start) == ' '; start++) {
		}
		if (start == str.length()) {
			return 0;
		}
		int sign = 1;
		char first = str.charAt(start);
		if (first == '+' || first == '-') {
			start += 1;
			if (first == '-') {
				sign = -1;
			}
		}

		int res = 0, digit;
		int OVERFLOW_BOUNDARY = Integer.MAX_VALUE / 10;
		for (int present = start; present < str.length() && str.charAt(present) >= 48
				&& str.charAt(present) <= 57; present++) {
			digit = str.charAt(present) - 48;
			
			// where these logic came from?
			// Integer.MAX_VALUE =  2147483647
			// Integer.MIN_VALUE = -2147483648
			
			if (sign > 0 && (res > OVERFLOW_BOUNDARY || (res == OVERFLOW_BOUNDARY && digit > 7))) {
				System.out.println("Input = " + str + " Current number = " + res + " Trying to add digit = " + digit);
				return Integer.MAX_VALUE;
			}
			if (sign < 0 && (res < -OVERFLOW_BOUNDARY || (res == -OVERFLOW_BOUNDARY && digit > 8))) {
				System.out.println("Input = " + str + " Current number = " + res + " Trying to add digit = " + digit);
				return Integer.MIN_VALUE;
			}
			res = 10 * res + sign * digit;
		}

		return res;
	}

}
