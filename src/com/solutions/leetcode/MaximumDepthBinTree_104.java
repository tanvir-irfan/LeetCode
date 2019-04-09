/*
 * EASY https://leetcode.com/problems/maximum-depth-of-binary-tree/
 */

package com.solutions.leetcode;

public class MaximumDepthBinTree_104 {

	public static void main(String[] args) {
	}

	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public int maxDepth(TreeNode root) {
		if (root == null)
			return 0;

		int leftD = maxDepth(root.left);
		int rightD = maxDepth(root.right);

		return Math.max(leftD, rightD) + 1;
	}
}
