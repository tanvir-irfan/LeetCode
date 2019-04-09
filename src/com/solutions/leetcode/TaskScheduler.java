package com.solutions.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class TaskScheduler {

	public static void main(String[] args) {
		char[] tasks = "AAABBB".toCharArray();
		int n = 2;

		List<Character> res = new TaskScheduler().leastIntervalWithSchedule(tasks, n);
	}

	public List<Character> leastIntervalWithSchedule(char[] tasks, int n) {
		
		List<Character> result = new ArrayList<>();
		
		int[] map = new int[26];
		for (char c : tasks)
			map[c - 'A']++;
		PriorityQueue<PQItem> queue = new PriorityQueue<>(26, Collections.reverseOrder());
		for (int index = 0; index < 26; index++) {
			if (map[index] > 0)
				queue.add(new PQItem((char) ('A' + index), map[index]));
		}
		int time = 0;
		while (!queue.isEmpty()) {
			int i = 0;
			List<PQItem> temp = new ArrayList<>();
			while (i <= n) {
				if (!queue.isEmpty()) {
					if (queue.peek().count > 1) {
						PQItem pqItem = queue.poll();

						result.add(pqItem.task);
						
						pqItem.count--;
						temp.add(pqItem);
					} else {
						
						result.add(queue.poll().task);
					}
				}
				time++;
				if (queue.isEmpty() && temp.size() == 0) {
					break;
				}
				i++;
			}
			for (PQItem l : temp)
				queue.add(l);
		}
		return result;
	}

	public int leastInterval(char[] tasks, int n) {
		int[] map = new int[26];
		for (char c : tasks)
			map[c - 'A']++;
		PriorityQueue<Integer> queue = new PriorityQueue<>(26, Collections.reverseOrder());
		for (int f : map) {
			if (f > 0)
				queue.add(f);
		}
		int time = 0;
		while (!queue.isEmpty()) {
			int i = 0;
			List<Integer> temp = new ArrayList<>();
			while (i <= n) {
				if (!queue.isEmpty()) {
					if (queue.peek() > 1)
						temp.add(queue.poll() - 1);
					else
						queue.poll();
				}
				time++;
				if (queue.isEmpty() && temp.size() == 0)
					break;
				i++;
			}
			for (int l : temp)
				queue.add(l);
		}
		return time;
	}

	class PQItem implements Comparable<PQItem> {
		char task;
		int count;

		PQItem(char task, int count) {
			this.task = task;
			this.count = count;
		}

		public int compareTo(PQItem other) {
			return this.count - other.count;
		}
		
		public String toString() {
			return "[" + this.task + "," + this.count + "]";
		}
	}
}
