package com.solutions.leetcode;

public class IntegerToEnglishWords {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String convert = convertThreeDigitWord("304");
		
		System.out.println(convert);
	}
	
	private static String convertThreeDigitWord(String str) {
        int len = str.length();
        
        int [] digits = new int[3];
        
        for(int i = 0; i < len; i++) {
            int d = str.charAt(len - i - 1) - '0';
            digits[digits.length - i - 1] = d;
        }
        
        StringBuilder sb = new StringBuilder();
        
        if(digits[0] > 0) sb.append(digits[0] + " Hundred ");
        if(digits[1] > 0) sb.append(digits[0] + " Ten ");
        if(digits[2] > 0) sb.append(digits[0]);
        
        return sb.toString();
    }

}
