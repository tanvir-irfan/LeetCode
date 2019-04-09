package com.solutions.leetcode;

public class ReadN_157 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param buf
	 *            Destination buffer
	 * @param n
	 *            Number of characters to read
	 * @return The number of actual characters read
	 */

	private static final int READ_SIZE = 4;

	public int read(char[] buf, int n) {

		if (n <= READ_SIZE) {
			// TODO uncomment the following line
			// int count = read4(buf);
			int count = 0;
			return Math.min(n, count);
		}

		int bufIndex = 0;

		while (bufIndex < n) {
			char[] readBuff = new char[READ_SIZE];
			// TODO uncomment the following line
			// int count = read4(buf);
			int count = 0;

			for (int i = 0; i < Math.min(count, READ_SIZE) && bufIndex < n; i++) {
				buf[bufIndex++] = readBuff[i];
			}

			if (count < READ_SIZE || bufIndex >= n) {
				break;
			}
		}

		return bufIndex;
	}

}
