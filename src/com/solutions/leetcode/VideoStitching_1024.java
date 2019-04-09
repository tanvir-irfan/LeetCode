/*
 * MEDIUM: https://leetcode.com/problems/video-stitching/
 * 
 * You are given a series of video clips from a sporting event that lasted T seconds.  
 * These video clips can be overlapping with each other and have varied lengths.
 * Each video clip clips[i] is an interval: it starts at time clips[i][0] and ends at time clips[i][1].  
 * We can cut these clips into segments freely: for example, a clip [0, 7] can be cut into segments [0, 1] + [1, 3] + [3, 7].
 * Return the minimum number of clips needed so that we can cut the clips into segments that cover the entire sporting event ([0, T]).  
 * If the task is impossible, return -1.
 * 
 * 
 * Input: clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], T = 10
 * Output: 3
 * Explanation: 
 * We take the clips [0,2], [8,10], [1,9]; a total of 3 clips.
 * Then, we can reconstruct the sporting event as follows: We cut [1,9] into segments [1,2] + [2,8] + [8,9].
 * Now we have segments [0,2] + [2,8] + [8,10] which cover the sporting event [0, 10].
 * 
 * 
 * Input: clips = [[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],[2,6],[3,4],[4,5],[5,7],[6,9]], T = 9 
 * Output: 3
 * Explanation: 
 * We can take clips [0,4], [4,7], and [6,9].
 */
package com.solutions.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class VideoStitching_1024 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int videoStitching(int[][] clips, int T) {
		if (T == 0)
			return 0;

		if (clips == null || clips.length == 0)
			return -1;

		List<Interval> res = new ArrayList<>();

		Interval[] intervals = new Interval[clips.length];

		for (int i = 0; i < clips.length; i++) {
			intervals[i] = new Interval(clips[i][0], clips[i][1]);
		}

		Arrays.sort(intervals, (a, b) -> a.start - b.start);

		PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> b.end - a.end);

		int lengthCovered = 0;

		for (int i = 0; i < intervals.length;) {
			boolean isIncNeeded = true;
			while (i < intervals.length && intervals[i].start <= lengthCovered) {
				pq.offer(intervals[i++]);
				isIncNeeded = false;
			}

			if (pq.isEmpty()) {
				return -1;
			}

			Interval toAddToRes = pq.poll();
			res.add(toAddToRes);
			lengthCovered = toAddToRes.end;

			if (lengthCovered >= T)
				break;

			while (!pq.isEmpty() && pq.peek().end <= lengthCovered) {
				pq.poll();
			}

			if (isIncNeeded)
				i++;
		}

		return lengthCovered >= T ? res.size() : -1;
	}

	class Interval {
		int start;
		int end;

		Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

}
