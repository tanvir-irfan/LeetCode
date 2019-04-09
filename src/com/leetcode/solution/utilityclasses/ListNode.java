package com.leetcode.solution.utilityclasses;

public class ListNode/* implements Comparable<ListNode>*/{
	public int key;
	public int val;
	public ListNode next;
	public ListNode prev;
	public ListNode random;
	
	boolean isClone;
	
	public ListNode() {
		this(0);
	}

	public ListNode(int val) {
		this.val = val;
		this.next = null;
		this.prev = null;
	}
	
	public ListNode(int val, ListNode next, ListNode random, boolean isClone) {
		this.val = val;
		this.next = null;
		this.random = null;
		this.isClone = isClone;
	}
	
	public ListNode (int key, int val) {
		this.key = key;
		this.val = val;
		this.next = null;
		this.prev = null;
	}

	public String toString() {
		return this.val + (isClone ? " C" : "");
	}

}
