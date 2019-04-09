/*
 * EASY https://leetcode.com/problems/backspace-string-compare/
 */

package com.solutions.leetcode;

import java.util.Stack;

public class BackspaceStringCompare_844 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String S = "a#c";
		String T = "c";

		boolean res = new BackspaceStringCompare_844().backspaceCompare(S, T);

		System.out.println(res);
	}

	public boolean backspaceCompare(String S, String T) {
		int i = S.length() - 1, j = T.length() - 1;
		int skipS = 0, skipT = 0;

		while (i >= 0 || j >= 0) { // While there may be chars in build(S) or
									// build (T)
			while (i >= 0) { // Find position of next possible char in build(S)
				if (S.charAt(i) == '#') {
					skipS++;
					i--;
				} else if (skipS > 0) {
					skipS--;
					i--;
				} else
					break;
			}
			while (j >= 0) { // Find position of next possible char in build(T)
				if (T.charAt(j) == '#') {
					skipT++;
					j--;
				} else if (skipT > 0) {
					skipT--;
					j--;
				} else
					break;
			}
			// If two actual characters are different
			if (i >= 0 && j >= 0 && S.charAt(i) != T.charAt(j))
				return false;
			// If expecting to compare char vs nothing
			if ((i >= 0) != (j >= 0))
				return false;
			i--;
			j--;
		}
		return true;
	}

	///////////////////////////////////////////////////////////////////////
	public boolean backspaceCompare_Time_M_N_Space_M_N(String S, String T) {
		String modifiedS = getModifiedString(S);
		String modifiedT = getModifiedString(T);

		System.out.println(modifiedS);
		System.out.println(modifiedT);

		return modifiedS.equals(modifiedT);

	}

	private String getModifiedString(String str) {
		if (str == null || str.isEmpty())
			return "";
		Stack<Character> stack = new Stack<>();

		for (char ch : str.toCharArray()) {
			if (ch == '#') {
				if (!stack.isEmpty()) {
					stack.pop();
				}
			} else {
				stack.push(ch);
			}
		}

		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}

		return sb.toString();
	}
}
