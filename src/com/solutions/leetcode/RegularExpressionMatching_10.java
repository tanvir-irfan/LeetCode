/* HARD
 * https://leetcode.com/problems/regular-expression-matching/
 */

package com.solutions.leetcode;

public class RegularExpressionMatching_10 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	////////////////////////////// MY SOLUTION ////////////////////////////
	public boolean isMatch(String text, String pettarn) {
		if (text == null || pettarn == null || (pettarn.length() > 0 && pettarn.charAt(0) == '*'))
			return false;
		return isMatch(text.toCharArray(), 0, pettarn.toCharArray(), 0);
	}

	public boolean isMatch(char[] textA, int sI, char[] patternA, int pI) {

		if (pI == patternA.length && sI < textA.length) {
			return false;
		}

		if (sI == textA.length && pI < patternA.length) {
			if (patternA.length - pI == 1) {
				return false;
			} else if (patternA[pI + 1] == '*') {
				return isMatch(textA, sI, patternA, pI + 2);
			} else {
				return false;
			}
		}

		if (	   (sI == textA.length && pI == patternA.length) 
				|| (sI == textA.length - 1 && pI == patternA.length - 1 && textA[sI] == patternA[pI])) {
			return true;
		}

		if (isNextAsteric(patternA, pI)) {
			char cur = patternA[pI];

			if (cur == '.') {
				// current character is a '.'.
				// e.g. p = ".*"
				return isMatch(textA, sI, patternA, pI + 2) || isMatch(textA, sI + 1, patternA, pI + 2) || isMatch(textA, sI + 1, patternA, pI);
			} else {
				// current character is [a-z]
				// e.g. p = "a*"

				return isMatch(textA, sI, patternA, pI + 2) || (patternA[pI] == textA[sI] && isMatch(textA, sI + 1, patternA, pI + 2))
						|| (patternA[pI] == textA[sI] && isMatch(textA, sI + 1, patternA, pI));
			}

		} else {
			if (textA[sI] == patternA[pI]) {
				return isMatch(textA, sI + 1, patternA, pI + 1);
			}

			if (patternA[pI] == '.') {
				return isMatch(textA, sI + 1, patternA, pI + 1);
			}
		}

		return false;
	}

	private boolean isNextAsteric(char[] p, int pI) {
		return pI < p.length - 1 && p[pI + 1] == '*';
	}
	///////////////////////////////////////////////////////////////////////////////

	public boolean isMatch_RECUR(String text, String pattern) {
		if (pattern.isEmpty())
			return text.isEmpty();
		boolean first_match = (!text.isEmpty() && (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

		if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
			return (isMatch(text, pattern.substring(2)) || (first_match && isMatch(text.substring(1), pattern)));
		} else {
			return first_match && isMatch(text.substring(1), pattern.substring(1));
		}
	}

	///////////////////////////////////////////////////////////////////////////////

	enum Result {
		TRUE, FALSE
	}

	Result[][] memo;

	public boolean isMatch_DP_TOP_DOWN(String text, String pattern) {
		memo = new Result[text.length() + 1][pattern.length() + 1];
		return dp(0, 0, text, pattern);
	}

	public boolean dp(int i, int j, String text, String pattern) {
		if (memo[i][j] != null) {
			return memo[i][j] == Result.TRUE;
		}
		boolean ans;
		if (j == pattern.length()) {
			ans = i == text.length();
		} else {
			boolean first_match = (i < text.length()
					&& (pattern.charAt(j) == text.charAt(i) || pattern.charAt(j) == '.'));

			if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
				ans = (dp(i, j + 2, text, pattern) || first_match && dp(i + 1, j, text, pattern));
			} else {
				ans = first_match && dp(i + 1, j + 1, text, pattern);
			}
		}
		memo[i][j] = ans ? Result.TRUE : Result.FALSE;
		return ans;
	}
	///////////////////////////////////////////////////////////////////////////////

	public boolean isMatch_DP_BOTTOM_UP(String text, String pattern) {
		boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
		dp[text.length()][pattern.length()] = true;

		for (int i = text.length(); i >= 0; i--) {
			for (int j = pattern.length() - 1; j >= 0; j--) {
				boolean first_match = (i < text.length()
						&& (pattern.charAt(j) == text.charAt(i) || pattern.charAt(j) == '.'));
				if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
					dp[i][j] = dp[i][j + 2] || first_match && dp[i + 1][j];
				} else {
					dp[i][j] = first_match && dp[i + 1][j + 1];
				}
			}
		}
		return dp[0][0];
	}
}
