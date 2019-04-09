/*
 * MEDIUM: https://leetcode.com/problems/zigzag-conversion/
 */
package com.solutions.leetcode;

import java.util.ArrayList;
import java.util.List;

public class ZigZagConversion_6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "PAYPALISHIRING";
		int numRows = 3;
		
		ZigZagConversion_6 z = new ZigZagConversion_6();
		
		System.out.println(z.convert(s, numRows));
	}

	public String convert(String s, int numRows) {

		if (numRows == 1)
			return s;

		List<StringBuilder> rows = new ArrayList<>();
		for (int i = 0; i < Math.min(numRows, s.length()); i++)
			rows.add(new StringBuilder());

		int curRow = 0;
		boolean goingDown = false;

		for (char c : s.toCharArray()) {
			rows.get(curRow).append(c);
			if (curRow == 0 || curRow == numRows - 1)
				goingDown = !goingDown;
			curRow += goingDown ? 1 : -1;
		}

		StringBuilder ret = new StringBuilder();
		for (StringBuilder row : rows)
			ret.append(row);
		return ret.toString();
	}

}
