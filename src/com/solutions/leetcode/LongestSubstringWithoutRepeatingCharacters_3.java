/*
 * Medium
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */

package com.solutions.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters_3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }
	
	//////////////////////////////////////////////////////////////
	/*
	 * T: O(2n) ~ O(n)
	 * 
	 * S: O(min(m, n))O(min(m,n)). Same as the previous approach. We need O(k)
	 * space for the sliding window, where k is the size of the Set. The size of
	 * the Set is upper bounded by the size of the string nn and the size of the
	 * charset/alphabet mm.
	 */
	public int lengthOfLongestSubstring_SlidingWindow(String s) {
		int n = s.length();
		Set<Character> set = new HashSet<>();
		int ans = 0, i = 0, j = 0;
		while (i < n && j < n) {
			// try to extend the range [i, j]
			if (!set.contains(s.charAt(j))) {
				set.add(s.charAt(j++));
				ans = Math.max(ans, j - i);
			} else {
				set.remove(s.charAt(i++));
			}
		}
		return ans;
	}
	//////////////////////////////////////////////////////////////

	//////////////////////////////////////////////////////////////
	/*
	 * T: O(n^3) S: O(1)
	 */
	public int lengthOfLongestSubstring_Naive(String s) {
		int n = s.length();
		int ans = 0;
		for (int i = 0; i < n; i++)
			for (int j = i + 1; j <= n; j++)
				if (allUnique(s, i, j))
					ans = Math.max(ans, j - i);
		return ans;
	}

	public boolean allUnique(String s, int start, int end) {
		Set<Character> set = new HashSet<>();
		for (int i = start; i < end; i++) {
			Character ch = s.charAt(i);
			if (set.contains(ch))
				return false;
			set.add(ch);
		}
		return true;
	}
	//////////////////////////////////////////////////////////////
}
