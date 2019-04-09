package com.solutions.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class EvaluateDivision {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[][] equations = { { "a", "b" }, { "b", "c" } };
		double[] values = { 2.0, 3.0 };
		String[][] queries = { { "a", "c" }, { "b", "a" }, { "a", "e" }, { "a", "a" }, { "x", "x" } };

		EvaluateDivision ed = new EvaluateDivision();

		double[] res = ed.calcEquation(equations, values, queries);

		for (double d : res) {
			System.out.print(d + " ");
		}

	}

	///////////////////////////////////////////////////////////////////////////////////////////////

	public double[] calcEquation_Floyd_Warshal(String[][] e, double[] v, String[][] q) {

		if (e.length == 0) {
			return new double[0];
		}

		HashMap<String, Integer> cache = new HashMap<>();
		int count = 0;
		for (int i = 0; i < e.length; i++) {
			String a = e[i][0];
			String b = e[i][1];

			if (!cache.containsKey(a)) {
				cache.put(a, count++);
			}

			if (!cache.containsKey(b)) {
				cache.put(b, count++);
			}
		}

		double[][] graph = new double[count][count];
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph[i].length; j++) {
				graph[i][j] = -1;
			}
		}

		for (int i = 0; i < e.length; i++) {
			String a = e[i][0];
			String b = e[i][1];

			int indexA = cache.get(a);
			int indexB = cache.get(b);

			graph[indexA][indexA] = graph[indexA][indexA] = 1;
			graph[indexA][indexB] = v[i];
			graph[indexB][indexA] = 1 / v[i];
		}

		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph.length; j++) {
				if (graph[i][j] != -1) {
					continue;
				}
				for (int k = 0; k < graph.length; k++) {
					if (graph[i][k] == -1 || graph[k][j] == -1) {
						continue;
					}
					graph[i][j] = graph[i][k] * graph[k][j];
				}
			}
		}

		double[] result = new double[q.length];
		for (int i = 0; i < q.length; i++) {
			String first = q[i][0];
			String second = q[i][1];
			if (!cache.containsKey(first) || !cache.containsKey(second)) {
				result[i] = -1;
			} else {
				int i1 = cache.get(first);
				int i2 = cache.get(second);
				result[i] = graph[i1][i2];
			}
		}

		return result;
	}

	////////////////////////////////////////////// Dijkstra
	////////////////////////////////////////////// /////////////////////////////////////////////////
	class Graph {
		List<Vertex> vertices;
		HashMap<String, Vertex> cache;

		Graph(String[][] equations, double[] values) {

			vertices = new ArrayList<>();
			cache = new HashMap<>();

			for (int i = 0; i < equations.length; i++) {
				String a = equations[i][0];
				String b = equations[i][1];

				addVertex(a, b);

				addEdge(cache.get(a), cache.get(b), values[i]);
				addEdge(cache.get(b), cache.get(a), 1 / values[i]);
			}
		}

		private void addVertex(String nameA, String nameB) {
			Vertex v = null;
			if (!cache.containsKey(nameA)) {
				v = new Vertex(nameA);
				cache.put(nameA, v);
				vertices.add(v);
			}
			if (!cache.containsKey(nameB)) {
				v = new Vertex(nameB);
				cache.put(nameB, v);
				vertices.add(v);
			}
		}

		private void addEdge(Vertex a, Vertex b, double value) {

			List<Edge> edges = cache.get(a.name).neighbor;

			edges.add(new Edge(b, value));
		}

		public void resetDistance() {
			for (Vertex v : this.vertices) {
				v.distance = Integer.MAX_VALUE;
			}
		}
	}

	class Vertex implements Comparable<Vertex> {
		String name;
		Vertex parent;

		double distance;

		List<Edge> neighbor;

		Vertex(String name) {
			this.distance = Integer.MAX_VALUE;
			this.name = name;
			neighbor = new ArrayList<>();
		}

		public void resetDistance() {
			this.distance = Integer.MAX_VALUE;
		}

		public String toString() {
			return "V = " + this.name;
		}

		@Override
		public int compareTo(Vertex o) {
			if (this.distance == o.distance)
				return 0;
			else if (this.distance > o.distance)
				return 1;
			return -1;
		}

	}

	class Edge {
		Vertex vertex;
		double value;

		Edge(Vertex vertex, double value) {
			this.vertex = vertex;
			this.value = value;
		}

		public String toString() {
			return "E = " + this.vertex.name;
		}
	}

	public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
		Graph graph = new Graph(equations, values);

		double[] result = new double[queries.length];

		for (int i = 0; i < queries.length; i++) {
			String a = queries[i][0];
			String b = queries[i][1];

			if (!graph.cache.containsKey(a) || !graph.cache.containsKey(b)) {
				result[i] = -1.0;
			} else {

				double eval = dijkstraShortestPath(graph, graph.cache.get(a), graph.cache.get(b));

				result[i] = (eval == Integer.MAX_VALUE ? -1.0 : eval);
			}
		}

		return result;
	}

	private double dijkstraShortestPath(Graph graph, Vertex source, Vertex destination) {

		graph.resetDistance();

		if (source == destination)
			return 1.0;

		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		source.distance = 1;
		pq.add(source);

		HashSet<Vertex> visited = new HashSet<>();

		while (!pq.isEmpty()) {
			Vertex curVertex = pq.remove();

			for (Edge e : curVertex.neighbor) {
				Vertex neighbor = e.vertex;

				if (visited.contains(neighbor)) {
					continue;
				}

				if (curVertex.distance * e.value < neighbor.distance) {
					neighbor.distance = curVertex.distance * e.value;
					pq.add(neighbor);
				}
			}

			visited.add(curVertex);

		}
		return destination.distance;
	}
}
