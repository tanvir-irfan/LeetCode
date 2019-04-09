/*
 * MEDIUM: https://leetcode.com/problems/3sum-closest/
 * 
 * Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. 
 * Return the sum of the three integers. You may assume that each input would have exactly one solution.
 * 
 * Given array nums = [-1, 2, 1, -4], and target = 1. 
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */

package com.solutions.leetcode;

import java.util.Arrays;

public class ThreeSumClosest_16 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int threeSumClosest(int[] nums, int target) {
        if(nums == null || nums.length < 3) throw new IllegalArgumentException("Not enough input values!");
        
        int minDiff = Integer.MAX_VALUE;
        
        Arrays.sort(nums);
        
        for(int i = 0; i < nums.length; i++) {
            int left = i + 1, right = nums.length - 1;
            
            while(left < right) {
                
                int twoSum = nums[left] + nums[right];
                
                // update minDiff
                int tempDiff = (nums[i] + twoSum) - target;
                
                // need to keep track of Min Difference along with its sign.
                // to calculate the return value.
                if( Math.abs(minDiff) > Math.abs(tempDiff) ) {
                    minDiff = tempDiff;
                }
                
                if(tempDiff == 0) {
                    return target;
                } else if(tempDiff > 0) {
                    right--;
                } else {
                    left++;
                }
            }
            
            
        }
        
        return target + minDiff;
    }
}
