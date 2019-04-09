package com.solutions.leetcode;

public class HouseRobber_198 {

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3, 1 };
		HouseRobber_198 h = new HouseRobber_198();

		int res = h.rob(nums);

		System.out.println(res);
	}

	public int rob(int[] nums) {
		if (nums == null || nums.length == 0)
			return 0;
		if (nums.length == 1)
			return nums[0];
		if (nums.length == 2)
			return Math.max(nums[0], nums[1]);

		/*
		 * IDEA: First, what is the problem with circular house setting? The
		 * problem is, when we are robbing the house, and building our MEMORY,
		 * we do not know whether we robbed house "1" or "len -1"
		 * 
		 * So, first discard house "len-1" and find the max then discard the 1st
		 * house and find the max
		 * 
		 * Return the max from these two approach.
		 */
		int firstButNotLast = robHelper(nums, 0, nums.length - 2);
		int lastButNotFirst = robHelper(nums, 1, nums.length - 1);

		return Math.max(firstButNotLast, lastButNotFirst);
	}

	/*
	 * both start and end are valid INDEX;
	 */
	public int robHelper(int[] nums, int start, int end) {

		int[] memory = new int[end - start + 1];
		memory[0] = nums[start];

		int max = memory[0];

		for (int i = start + 1; i <= end; i++) {

			int iMinusTwo = (i - 2) >= start ? memory[i - 2 - start] : 0;

			memory[i - start] = Math.max(iMinusTwo + nums[i], memory[i - 1 - start]);

			max = Math.max(max, memory[i - start]);
		}

		return max;
	}

	///////////////////////////////////////////////////////////////

	public int robLinearHouses(int[] nums) {
		if (nums == null || nums.length == 0)
			return 0;

		int[] memory = new int[nums.length];
		memory[0] = nums[0];

		int max = memory[0];

		for (int i = 1; i < nums.length; i++) {

			int iMinusTwo = (i - 2) >= 0 ? memory[i - 2] : 0;

			memory[i] = Math.max(iMinusTwo + nums[i], memory[i - 1]);

			max = Math.max(max, memory[i]);
		}

		return max;
	}

}
