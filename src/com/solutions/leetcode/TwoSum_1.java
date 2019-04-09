/* EASY
 * https://leetcode.com/problems/two-sum/
 */

package com.solutions.leetcode;

import java.util.HashMap;
import java.util.Map;

public class TwoSum_1 {

	public static void main(String[] args) {

	}
	
	/*
	 * T: O(n)
	 * S: O(n)
	 */
	public int[] twoSum(int[] nums, int target) {
        int [] indices = new int [2];
        
        HashMap<Integer, Integer> memory = new HashMap<>();
        
        for(int i = 0; i < nums.length; i++) {
            int comp = target - nums[i];
            if(memory.containsKey(comp)) {
                indices[0] = (int)memory.get(comp);
                indices[1] = i;
                break;
            } else {
                memory.put(nums[i], i);
            }
        }
        
        return indices;
    }
	
	////////////////////////////////////////////////////////////////////////////////
	/*
	 * T: O(n^2)
	 * S: O(1)
	 */
	public int[] twoSumNaive(int[] nums, int target) {
	    for (int i = 0; i < nums.length; i++) {
	        for (int j = i + 1; j < nums.length; j++) {
	            if (nums[j] == target - nums[i]) {
	                return new int[] { i, j };
	            }
	        }
	    }
	    throw new IllegalArgumentException("No two sum solution");
	}
	
	public int[] twoSumTwoPass(int[] nums, int target) {
	    Map<Integer, Integer> map = new HashMap<>();
	    for (int i = 0; i < nums.length; i++) {
	        map.put(nums[i], i);
	    }
	    for (int i = 0; i < nums.length; i++) {
	        int complement = target - nums[i];
	        if (map.containsKey(complement) && map.get(complement) != i) {
	            return new int[] { i, map.get(complement) };
	        }
	    }
	    throw new IllegalArgumentException("No two sum solution");
	}

}
