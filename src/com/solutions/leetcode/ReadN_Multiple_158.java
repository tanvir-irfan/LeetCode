/*
 * HARD https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times/
 * 
 * Given a file and assume that you can only read the file using a given method read4, 
 * implement a method read to read n characters. Your method read may be called multiple times.
 */

package com.solutions.leetcode;

import java.util.ArrayList;

public class ReadN_Multiple_158 {

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
	private ArrayList<Character> storage;
	private int readPointer;
	private boolean isStreamEnded;

	ReadN_Multiple_158() {
		storage = new ArrayList<>();
		readPointer = -1;
		isStreamEnded = false;
	}

	public int read(char[] buf, int n) {
		int totalChRead = 0;

		if (isStreamEnded) {
			while (readPointer < storage.size() - 1 && totalChRead < n) {
				buf[totalChRead++] = storage.get(++readPointer);
			}

			return totalChRead;
		}

		while (true) {
			if ((readPointer >= storage.size() - 1) || !isStreamEnded) {
				char[] newChars = new char[READ_SIZE];

				// TODO uncomment the following line
				// int numNewChars = read4(newChars);
				int numNewChars = 0;

				for (int i = 0; i < numNewChars; i++) {
					storage.add(newChars[i]);
				}

				if (numNewChars < READ_SIZE) {
					isStreamEnded = true;
				}

			}

			if (readPointer >= storage.size() - 1 || totalChRead >= n) {
				return totalChRead;
			}

			buf[totalChRead++] = storage.get(++readPointer);
		}
	}
}
