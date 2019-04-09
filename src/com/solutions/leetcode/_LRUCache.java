package com.solutions.leetcode;
import java.util.HashMap;

import com.leetcode.solution.utilityclasses.ListNode;

public class _LRUCache {

	HashMap<Integer, ListNode> cache;
	ListNode head, tail;
	int capacity;
	int size = 0;

	public _LRUCache(int capacity) {
		this.capacity = capacity;
		head = tail = null;
		cache = new HashMap<>();
	}

	public int get(int key) {
		if (cache.containsKey(key)) {
			ListNode res = cache.get(key);
			moveToFront(res);
			return res.val;
		} else {
			return -1;
		}
	}

	public void put(int key, int value) {
		if(cache.containsKey(key)) {
			ListNode node = cache.get(key);
			node.val = value;
			
			moveToFront(node);
		} else {
			
			ListNode node = new ListNode(key, value);
			
			if(size == 0) {
				head = tail = node;
			} else if(size == capacity) {
				ListNode oldHead = head;
				cache.remove(oldHead.key);
				
				if(capacity == 1) {
					head = tail = node;
				} else {
					head = head.next;
					head.prev = null;
					size--;
					addToTail(node);
				}
				
			} else {
				addToTail(node);
			}
			
			cache.put(key, node);
			size++;
		}
	}

	private void addToTail(ListNode node) {
		tail.next = node;
		node.prev = tail;
		node.next = null;
		
		tail = node;
	}
	
	private void moveToFront(ListNode node) {
		if (size == 1 || node == this.tail) {
			return;
		} else {
			if (node == this.head) {
				head = node.next;
				head.prev = null;
				node.next = null;
				
				addToTail(node);
			} else {
				ListNode nodePrev = node.prev;
				ListNode nodeNext = node.next;
				
				nodePrev.next = nodeNext;
				nodeNext.prev = nodePrev;
				
				addToTail(node);
				
				return;
			}
		}
	}

	public static void main(String[] args) {
		_LRUCache lru = new _LRUCache(1);

		lru.put(1, 1);
		lru.put(2, 2);
		System.out.println(lru.get(1));
		lru.put(3, 3);
		System.out.println(lru.get(2));
		lru.put(4, 4);
		System.out.println(lru.get(1));
		System.out.println(lru.get(3));
		System.out.println(lru.get(4));

	}

}
