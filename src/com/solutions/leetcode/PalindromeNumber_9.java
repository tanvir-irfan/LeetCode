/* EASY
 * https://leetcode.com/problems/palindrome-number/
 */

package com.solutions.leetcode;

public class PalindromeNumber_9 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PalindromeNumber_9 p = new PalindromeNumber_9();
		
		System.out.println(p.isPalindrome(10));
	}
	
	
    public boolean isPalindrome(int x) {
        // no negative num is a palindrome.
        
        // number which is not 0 (e.g: 110,10,20) and 
        // has least significatn digit == 0  is NOT palindrome.
        if(x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        
        int revertedNumber = 0;
        while(x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }
        
        // handle the case when x has odd number of digits.
        return x == revertedNumber || x == revertedNumber/10;
    }
}
