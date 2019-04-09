/* EASY
 * https://leetcode.com/problems/reverse-integer/
 */

package com.solutions.leetcode;

public class ReverseInteger_7 {

	public static void main(String[] args) {
		ReverseInteger_7 r = new ReverseInteger_7();
		r.reverse(-12300);
	}

	public int reverse(int x) {
		boolean isNeg = x < 0 ? true : false;

		if (isNeg)
			x *= -1;

		String s = x + "";
		int i = s.length() - 1;
		for (; i >= 0; i--) {
			if (s.charAt(i) != '0') {
				break;
			}
		}

		StringBuilder sb = new StringBuilder(s.substring(0, i + 1));
		if (sb.length() < 1)
			return 0;

		int res = 0;
		try {
			res = (int) Integer.parseInt(sb.reverse().toString());
		} catch (NumberFormatException e) {
			return 0;
		}

		return isNeg ? -1 * res : res;
	}

	///////////////////////////////////////////////////////////////////
	/*
	 * Complexity Analysis
	 * 
	 * Time Complexity: O(log(x)). There are roughly log10(x) digits in x. Space
	 * Complexity: O(1)O(1).
	 * 
	 */
	public int reverseConstantSpace(int x) {
		int rev = 0;
		while (x != 0) {
			int pop = x % 10;
			x /= 10;
			if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7))
				return 0;
			if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8))
				return 0;
			rev = rev * 10 + pop;
		}
		return rev;
	}
}
