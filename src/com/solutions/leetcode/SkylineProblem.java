package com.solutions.leetcode;

import java.util.ArrayList;
import java.util.List;

public class SkylineProblem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] buildings = new int[][] { { 2, 9, 10 }, { 3, 7, 15 }, { 5, 12, 12 }, { 15, 20, 10 }, { 19, 24, 8 } };

		SkylineProblem s = new SkylineProblem();

		List<int[]> res = s.getSkyline(buildings);

		System.out.println(res.size());
	}

	public List<int[]> getSkyline(int[][] buildings) {

		int n = buildings.length;

		// The base cases
		if (n == 0)
			return new ArrayList<int[]>();

		List<int[]> output = getSkyline(buildings, 0, buildings.length - 1);

		return output;
	}

	/**
	 * Divide-and-conquer algorithm to solve skyline problem, which is similar
	 * with the merge sort algorithm.
	 */
	public List<int[]> getSkyline(int[][] buildings, int left, int right) {

		// The base cases
		if (left > right)
			return new ArrayList<int[]>();
		if (left == right) {
			List<int[]> output = new ArrayList<int[]>();
			int xStart = buildings[left][0];
			int xEnd = buildings[left][1];
			int y = buildings[left][2];

			output.add(new int[] { xStart, y });
			output.add(new int[] { xEnd, 0 });
			return output;
		}

		// If there is more than one building,
		// recursively divide the input into two subproblems.
		int mid = left + (right - left) / 2;
		List<int[]> leftSkyline, rightSkyline;
		leftSkyline = getSkyline(buildings, left, mid);
		rightSkyline = getSkyline(buildings, mid + 1, right);

		// Merge the results of subproblem together.
		return mergeSkylines(leftSkyline, rightSkyline);
	}

	/**
	 * Merge two skylines together.
	 */
	public List<int[]> mergeSkylines(List<int[]> left, List<int[]> right) {
		int nL = left.size(), nR = right.size();
		int pL = 0, pR = 0;
		int currY = 0, leftY = 0, rightY = 0;
		int x, maxY;
		List<int[]> output = new ArrayList();

		// while we're in the region where both skylines are present
		while ((pL < nL) && (pR < nR)) {
			int[] pointL = left.get(pL);
			int[] pointR = right.get(pR);
			// pick up the smallest x
			if (pointL[0] < pointR[0]) {
				x = pointL[0];
				leftY = pointL[1];
				pL++;
			} else {
				x = pointR[0];
				rightY = pointR[1];
				pR++;
			}
			// max height (i.e. y) between both skylines
			maxY = Math.max(leftY, rightY);
			// update output if there is a skyline change
			if (currY != maxY) {
				updateOutput(output, x, maxY);
				currY = maxY;
			}
		}

		// there is only left skyline
		currY = appendSkyline(output, left, pL, nL, currY);

		// there is only right skyline
		currY = appendSkyline(output, right, pR, nR, currY);

		return output;
	}

	/**
	 * Update the final output with the new element.
	 */
	public void updateOutput(List<int[]> output, int x, int y) {
		// if skyline change is not vertical -
		// add the new point
		if (output.isEmpty() || output.get(output.size() - 1)[0] != x)
			output.add(new int[] { x, y });
		// if skyline change is vertical -
		// update the last point
		else {
			output.get(output.size() - 1)[1] = y;
		}
	}

	/**
	 * Append the rest of the skyline elements with indice (p, n) to the final
	 * output.
	 */
	public int appendSkyline(List<int[]> output, List<int[]> skyline, int p, int n, int currY) {
		while (p < n) {
			int[] point = skyline.get(p);
			int x = point[0];
			int y = point[1];
			p++;

			// update output
			// if there is a skyline change
			if (currY != y) {
				updateOutput(output, x, y);
				currY = y;
			}
		}
		return currY;
	}

}
