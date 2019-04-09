package com.solutions.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class MostCommonWord_819 {

	public String mostCommonWord(String paragraph, String[] banned) {
		if (paragraph == null || paragraph.isEmpty())
			return "";

		HashSet<String> bannedMemory = new HashSet<>();

		for (String bannedW : banned) {
			bannedMemory.add(bannedW);
		}

		char[] paraArr = paragraph.toCharArray();

		boolean isWordStarted = false;

		StringBuilder sb = new StringBuilder();

		HashMap<String, Integer> goodWordMemory = new HashMap<>();

		for (int i = 0; i < paraArr.length; i++) {
			char curCh = Character.toLowerCase(paraArr[i]);
			boolean valid = isValid(curCh);

			if (!isWordStarted && !valid) {
				continue;
			} else if (!isWordStarted && valid) {
				isWordStarted = true;
				sb.append(curCh);
			} else if (isWordStarted && valid) {
				sb.append(curCh);
			} else if (isWordStarted && !valid) {
				// process the current word and continue;
				String curWord = sb.toString();
				
                processWord(curWord, bannedMemory, goodWordMemory);

				isWordStarted = false;
				sb = new StringBuilder();
			}
		}

        if(sb.length() > 0) {
            processWord(sb.toString(), bannedMemory, goodWordMemory);
        }
        
		return getMostFrequentWord(goodWordMemory);
	}

	boolean isValid(char ch) {
		return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
	}
    
    void processWord(String curWord, HashSet<String> bannedMemory, HashMap<String, Integer> goodWordMemory) {
        if (!bannedMemory.contains(curWord)) {
            // addToGoodWordMemory(curWord, goodWordMemory);

            if (goodWordMemory.containsKey(curWord)) {
                goodWordMemory.put(curWord, goodWordMemory.get(curWord) + 1);
            } else {
                goodWordMemory.put(curWord, 1);
            }
        }
    }
    
	String getMostFrequentWord(HashMap<String, Integer> goodWordMemory) {
		String result = "";
		int resultFreq = Integer.MIN_VALUE;

		for (Map.Entry<String, Integer> entry : goodWordMemory.entrySet()) {
			int tempFreq = entry.getValue();

			if (tempFreq > resultFreq) {
				resultFreq = tempFreq;
				result = entry.getKey();
			}
		}

		return result;
	}

	public static void main(String[] args) {
		MostCommonWord_819 m = new MostCommonWord_819();

		String para = "Bob";//"Bob hit a ball, the hit BALL flew far after it was hit.";
		String[] banned = { "bob" };

		System.out.println(m.mostCommonWord(para, banned));
	}

}
