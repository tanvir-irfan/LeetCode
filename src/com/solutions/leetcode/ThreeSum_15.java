/*
 * MEDIUM: https://leetcode.com/problems/3sum/
 * 
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? 
 * Find all unique triplets in the array which gives the sum of zero.
 * 
 * Note: The solution set must not contain duplicate triplets.
 * 
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 * OUTPUT: [ [-1, 0, 1], [-1, -1, 2] ]
 */
package com.solutions.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum_15 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
        
        if(nums == null || nums.length < 3) {
            return res;
        }
        
        Arrays.sort(nums);
        
        for(int i = 0; i < nums.length - 2; i++) {
            int comp = -1 * nums[i];
            
            int j = i + 1;
            int k = nums.length - 1;
            
            // if nums[j] >= comp that means we will not find any
            // nums[k] to yield 'nums [j] + nums[k] == comp'
            // as nums[k] is always >= nums[j]
            if(nums[j] > comp) {
                break;
            }
            
            while(j < nums.length - 1 && k > j) {
                
                int twoSum = nums [j] + nums[k];
                
                if( twoSum == comp) {
                    Integer [] arr = {nums[i], nums[j], nums[k]};
                    res.add(Arrays.asList(arr));
                    
                    // skip through the duplicate
                    while(j + 1 < nums.length - 1 && nums[j] == nums [j + 1]) {
                        j++;
                    }
                    // skip through the duplicate
                    while(k - 1 >= j && nums[k] == nums [k - 1]) {
                        k--;
                    }
                    
                    j++;
                    k--;
                } else if(twoSum > comp) {
                    k--;
                } else  if(twoSum < comp) {
                    j++;
                }
            }
            
            while(i + 1 < nums.length - 2 && nums[i] == nums [i + 1]) {
            	i++;
            }
        }
        
        return res;
	}
}
