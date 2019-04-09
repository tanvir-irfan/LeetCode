/*
 * MEDIUM: https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
 * MEDIUM: https://leetcode.com/problems/friend-circles/
 */
package com.solutions.leetcode;

public class ConnectedComponentUndirectedGraph_323 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
	public int countComponents(int n, int[][] edges) {
		if (n == 0 || edges == null)
			return 0;
		if (n == 1)
			return 1;

		if (edges.length == 0)
			return n;

		int[][] graph = new int[n][n];

		for (int i = 0; i < edges.length; i++) {
			graph[edges[i][0]][edges[i][1]] = 1;
			graph[edges[i][1]][edges[i][0]] = 1;
		}

		boolean[] visited = new boolean[n];

		int connectedComponent = 0;

		for (int i = 0; i < n; i++) {
			if (visited[i] == false) {
				dfs(i, graph, visited);
				connectedComponent++;
			}
		}

		return connectedComponent;
	}

	// https://leetcode.com/problems/friend-circles/
	public int findCircleNum(int[][] graph) {

		if (graph == null || graph.length == 0)
			return 0;
		int n = graph.length;
		boolean[] visited = new boolean[n];

		int connectedComponent = 0;

		for (int i = 0; i < n; i++) {
			if (visited[i] == false) {
				dfs(i, graph, visited);
				connectedComponent++;
			}
		}

		return connectedComponent;
	}

	private void dfs(int v, int[][] graph, boolean[] visited) {
		visited[v] = true;

		for (int n = 0; n < graph.length; n++) {
			if (graph[v][n] == 1 && !visited[n]) {
				dfs(n, graph, visited);
			}
		}
	}
}
