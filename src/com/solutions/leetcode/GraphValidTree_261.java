/*
 * MEDIUM: https://leetcode.com/problems/graph-valid-tree/
 * 
 * Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes), 
 * write a function to check whether these edges make up a valid tree.
 * 
 * Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
 * Output: true
 * 
 * Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
 * Output: false
 * 
 */

package com.solutions.leetcode;

public class GraphValidTree_261 {

	int connectedComponent;
    public boolean validTree(int n, int[][] edges) {
        
        connectedComponent = n;
        
        int [] tree = new int [n];
        for(int i = 0; i < n; i++) {
            tree[i] = i;
        }
        
        for(int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            
            if(isConnected(tree, u, v)) {
                return false;
            }
            
            connect(tree, u, v);
        }
        
        return connectedComponent == 1;
    }
    
    private boolean isConnected(int[] tree, int u, int v) {
        return root(tree, u) == root(tree, v);
    }
    
    private void connect(int[] tree, int u, int v) {
        int uR = root(tree, u);
        int vR = root(tree, v);
        
        if(uR != vR) {
            tree[uR] = vR;
            connectedComponent--;
        }
        
    }
    
//     private int root (int[] tree, int node) {
// 		while(tree[node] != node) {
// 			// path compression
// 			tree[node] = tree[tree[node]];
			
// 			node = tree[node];
// 		}
		
// 		return node;
// 	}
    
    private int root(int[] tree, int u) {
        if(u == tree[u]) {
            return u;
        }
        
        int rootU = root(tree, tree[u]);
        
        tree[u] = rootU;
        
        return rootU;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
