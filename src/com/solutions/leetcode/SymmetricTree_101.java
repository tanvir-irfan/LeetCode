/*
 * EASY https://leetcode.com/problems/symmetric-tree/
 */

package com.solutions.leetcode;

public class SymmetricTree_101 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public boolean isSymmetric(TreeNode root) {
        // if root is the only node in the tree, then tree is Symmetric
        if(root == null || (root.left == null && root.right == null)) 
            return true;
        
        // otherwise the tree is Symmetric only if the following helper
        // function returns that it is Symmetric
        return isSymmetric(root.left, root.right);
    }
    
    public boolean isSymmetric(TreeNode left, TreeNode right) {
        // some base case:- for any root, if both of the left and right 
        // nodes are null that means, either root is the only node in the tree
        // or we are dealing with a leaf node, both cases should return true.
        if(left == null && right == null) {
            return true;
        }
        
        // at any point of our recursion, if we find for a root, one of
        // either left or right is null and other is not, then we can
        // return false.
        //          4           or      4
        //         / \                    \
        //        5  null                     5
        if((left == null && right != null) || (left != null && right == null))
            return false;
        
        // finally, we check whether left and right node has the same value or not
        // and also, the other two conditions. :-)
        return left.val == right.val && isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }
}
