/* Medium
 * https://leetcode.com/problems/longest-palindromic-substring/
 */

package com.solutions.leetcode;
import java.util.Arrays;

public class LongestPalindromicSubstring_5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LongestPalindromicSubstring_5 lps = new LongestPalindromicSubstring_5();
		
		System.out.println(lps.longestPalindromeNew("abcbabcab"));
	}

	
	public String longestPalindromeNew(String s) {
        if(s == null || s.isEmpty()) {
            return "";
        }
        
        if(s.length() == 1) {
        	return s;
        }
        
        if(s.length() == 2) {
        	return s.charAt(0) == s.charAt(1) ? s : s.charAt(0) + "";
        }
        
        char[] sArr = s.toCharArray();
        
        String res = "";
        
        for(int i = 1; i < sArr.length; i++) {
        	String odd = expand(sArr, i, i);
            
        	String even = expand(sArr, i - 1, i);
        	
        	String temp = odd.length() > even.length() ? odd : even;
            
        	res = temp.length() > res.length() ? temp : res;
        }
        
        return res;
    }
	
	private String expand(char[] sArr, int i, int j) {
		
		if(i != j && sArr[i] != sArr[j]) {
			return new String (Arrays.copyOfRange(sArr, i + 1, i + 2));
		}
		
        while(i >= 0 && j < sArr.length && sArr[i] == sArr[j]) {
        	i--;
        	j++;
        }
        
        int start = i + 1;
        int end = j;
        
        return new String (Arrays.copyOfRange(sArr, start, end));
    }
	
	////////////////////////////////////////////////////////////////////////////////////
	
	public String longestPalindrome(String s) {
        if(s == null || s.isEmpty()) {
            return "";
        }
        
        if(s.length() == 1) {
        	return s;
        }
        
        if(s.length() == 2) {
        	return s.charAt(0) == s.charAt(1) ? s : s.charAt(0) + "";
        }
        
        char[] sArr = s.toCharArray();
        
        String res = "";
        
        for(int i = 1; i < sArr.length; i++) {
        	String odd = checkOddStartingFromI(sArr, i);
            
        	String even = checkEvenStartingFromI(sArr, i);
        	
        	String temp = odd.length() > even.length() ? odd : even;
            
        	res = temp.length() > res.length() ? temp : res;
        }
        
        return res;
    }
    
    private String checkOddStartingFromI(char[] sArr, int i) {
        int res = 1;
        
        int index = 1;
        while(i - index >= 0 && i + index < sArr.length && sArr[i - index] == sArr[i + index]) {
            res += 2;
            index++;
        }
        
        int left = i - res / 2;
        int right = i + res / 2;
        
        return new String (Arrays.copyOfRange(sArr, left, right + 1));
    }
    
    private String checkEvenStartingFromI(char[] sArr, int i) {
        if(sArr[i - 1] != sArr[i]) {
            return sArr[i] + "";
        }
        
        int res = 2;
        
        int index = 1;
        
        while(i - index - 1 >= 0 && i + index < sArr.length && sArr[i - index - 1] == sArr[i + index]) {
            res += 2;
            index++;
        }
        
        int left = i - res / 2;
        int right = i + res / 2;
        
        return new String (Arrays.copyOfRange(sArr, left, right));
    }
}
