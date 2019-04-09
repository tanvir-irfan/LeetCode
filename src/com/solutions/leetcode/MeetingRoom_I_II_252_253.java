/*
 * 253: EASY
 * 253: MEDIUM https://leetcode.com/problems/meeting-rooms-ii/
 * Input: [[0, 30],[5, 10],[15, 20]]
 * Output: 2
 */

package com.solutions.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRoom_I_II_252_253 {

	public static void main(String[] args) {

	}

	class Interval {
		int start;
		int end;

		Interval() {
			start = 0;
			end = 0;
		}

		Interval(int s, int e) {
			start = s;
			end = e;
		}
	}

	public int minMeetingRooms(Interval[] intervals) {
		if (intervals == null || intervals.length == 0) {
			return 0;
		}
		
		// sorting interval by start time, as that's how we need to process
		Arrays.sort(intervals, (a, b) -> a.start - b.start);
		
		// PriorityQueue will keep track of all the END time of the meeting rooms
		PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> a.end - b.end);
		pq.offer(intervals[0]);

		int max = 1;

		for (int i = 1; i < intervals.length; i++) {
			Interval peek = pq.peek();
			
			// while processing one interval, we need to take care of the rooms which
			// are now empty due to meeting end time passed.
			while (peek.end <= intervals[i].start) {
				pq.poll();

				if (pq.isEmpty()) {
					break;
				}

				peek = pq.peek();
			}

			pq.offer(intervals[i]);

			max = Math.max(max, pq.size());
		}

		return max;
	}
}
