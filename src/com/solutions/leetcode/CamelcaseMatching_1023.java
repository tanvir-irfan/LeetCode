/*
 * MEDIUM: https://leetcode.com/problems/camelcase-matching/
 * 
 * A query word matches a given pattern if we can insert lowercase letters 
 * to the pattern word so that it equals the query. 
 * (We may insert each character at any position, and may insert 0 characters.) 
 * 
 * Given a list of queries, and a pattern, return an answer list of booleans, 
 * where answer[i] is true if and only if queries[i] matches the pattern.
 * 
 * Input: queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FB"
 * Output: [true,false,true,true,false]
 * 
 * Input: queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBa"
 * Output: [true,false,true,false,false]
 * 
 * Input: queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBaT"Output: [false,true,false,false,false]
 */
package com.solutions.leetcode;

import java.util.ArrayList;
import java.util.List;

public class CamelcaseMatching_1023 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// String[] queries =
		// {"FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"};
		// String pattern = "FB";
		String[] queries = { "CompetitiveProgramming", "CounterPick", "ControlPanel" };
		String pattern = "CooP";
		List<Boolean> res = new CamelcaseMatching_1023().camelMatch(queries, pattern);
		System.out.println(res.size());
	}

	public List<Boolean> camelMatch(String[] queries, String pattern) {

		int qLen = queries.length;
		List<Boolean> res = new ArrayList<>();

		for (int i = 0; i < qLen; i++) {
			res.add(matchWord(queries[i], pattern));
		}

		return res;
	}

	private boolean matchWord(String word, String pattern) {

		int wI = 0, pI = 0;

		char[] wA = word.toCharArray();
		char[] pA = pattern.toCharArray();

		while (true) {

			if (isUpper(wA[wI]) && isUpper(pA[pI])) {
				// if both are upper case, the needs to match.
				if (wA[wI] != pA[pI]) {
					return false;
				} else {
					wI++;
					pI++;
				}
			} else if ((isLower(wA[wI]) && isLower(pA[pI]))) {
				// if both are upper case, and both match then advance both
				// pointers.
				// otherwise advance only word pointer.
				if (wA[wI] != pA[pI]) {
					wI++;
				} else {
					wI++;
					pI++;
				}
			} else if (isLower(wA[wI]) && isUpper(pA[pI])) {
				// if word character is lower case, and pattert character is
				// upper case
				// advance word pointer.
				wI++;
			} else if (isUpper(wA[wI]) && isLower(pA[pI])) {
				// if word character is upper case, and pattert character is
				// lower case
				// then it is wrong.
				return false;
			}

			// if we passed length in pattern, needs to check whether word also
			// passed but
			// first we need to skip all thelower characters in word
			if (pI >= pA.length) {
				while (wI < wA.length && isLower(wA[wI])) {
					wI++;
				}

				if (wI < wA.length)
					return false;
			}

			// if we passed length in word, then we also need to be in a
			// position where we
			// passed the length of pattern.
			if (wI >= wA.length) {
				return pI >= pA.length;
			}

			if (wI >= wA.length && pI >= pA.length) {
				return true;
			}
		}
	}

	private boolean isLower(char ch) {
		return ch >= 'a' && ch <= 'z';
	}

	private boolean isUpper(char ch) {
		return ch >= 'A' && ch <= 'Z';
	}
}
