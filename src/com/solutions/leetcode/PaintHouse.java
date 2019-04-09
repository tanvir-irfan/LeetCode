package com.solutions.leetcode;

public class PaintHouse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [][] costs = {{17,2,17},{16,16,5},{14,3,19}};
		
		int res = new PaintHouse().minCost(costs);
		System.out.println(res);
	}

	public int minCost(int[][] costs) {
	    if(costs==null||costs.length==0)
	        return 0;
	 
	    for(int i=1; i<costs.length; i++){
	        costs[i][0] += Math.min(costs[i-1][1], costs[i-1][2]);
	        costs[i][1] += Math.min(costs[i-1][0], costs[i-1][2]);
	        costs[i][2] += Math.min(costs[i-1][0], costs[i-1][1]);
	    }
	 
	    int m = costs.length-1;
	    return Math.min(Math.min(costs[m][0], costs[m][1]), costs[m][2]);
	}
	
	public int minCostExtraSpace(int[][] costs) {
	    if(costs==null||costs.length==0){
	        return 0;
	    }
	 
	    int[][] matrix = new int[3][costs.length];
	 
	    for(int j=0; j<costs.length; j++){
	        if(j==0){
	            matrix[0][j]=costs[j][0];
	            matrix[1][j]=costs[j][1];
	            matrix[2][j]=costs[j][2];
	        }else{
	            matrix[0][j]=Math.min(matrix[1][j-1], matrix[2][j-1])+costs[j][0];
	            matrix[1][j]=Math.min(matrix[0][j-1], matrix[2][j-1])+costs[j][1];
	            matrix[2][j]=Math.min(matrix[0][j-1], matrix[1][j-1])+costs[j][2];
	        }        
	    }
	 
	    int result = Math.min(matrix[0][costs.length-1], matrix[1][costs.length-1]);
	    result = Math.min(result, matrix[2][costs.length-1]);
	 
	    return result;
	}
}
