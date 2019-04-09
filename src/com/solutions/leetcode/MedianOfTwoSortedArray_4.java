/* Medium
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 */

package com.solutions.leetcode;
import java.util.List;
import java.util.ArrayList;

public class MedianOfTwoSortedArray_4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] arr1 = {1,3,5,7,9,11,20,40,60,80,100};
		int [] arr2 = {};
		
		MedianOfTwoSortedArray_4 md = new MedianOfTwoSortedArray_4();
		
		List<Integer> a = new ArrayList<>();
		List<Integer> b = new ArrayList<>();
		
		for(Integer i : arr1) {
			a.add(i);
		}
		
		for(Integer i : arr2) {
			b.add(i);
		}
		
		double  res = md.findMedianSortedArrays(arr1, arr2);
		
		System.out.println(res);
	}

	// DO NOT MODIFY BOTH THE LISTS
	public double findMedianSortedArrays(int[] a, int[] b) {
        if( (a == null || a.length == 0) && (b == null || b.length == 0)) return 0.0;
        
        int len = a.length + b.length;
        
        if(len % 2 == 1) {
            int kth = findKthElement(a, 0, b, 0, (len + 1) / 2);
            //System.out.println((len + 1) / 2 + " = " + kth);
            return kth;
        } else {
            int kth = findKthElement(a, 0, b, 0, len / 2);
            //System.out.println( len / 2 + " = " + kth);
            
            int kPlusOneth = findKthElement(a, 0, b, 0, (len / 2) + 1);
            //System.out.println( (len / 2) + 1 + " = " + kPlusOneth);
            
            return ( kth +  kPlusOneth ) / 2.;
        }
        
    }
    
    private int findKthElement(int [] a, int startA, int [] b, int startB, int k) {
        
        if(startA >= a.length) return b[startB + k - 1];
        if(startB == b.length) return a[startA + k - 1];
        
        if(k == 1) {
            return Math.min(a[startA], b[startB]);
        }
        
        
        int indexA = startA + k / 2 - 1;
        int indexB = startB + k / 2 - 1;
        
        int keyA = indexA < a.length ? a[indexA] : Integer.MAX_VALUE;
        int keyB = indexB < b.length ? b[indexB] : Integer.MAX_VALUE;
        
        if(keyA > keyB) {
            return findKthElement(a, startA, b, startB + k/2, k - k/2);
        } else {
            return findKthElement(a, startA + k / 2, b, startB, k - k/2);
        }
    }
	
//	public double findMedianSortedArrays(int[] input1, int[] input2) {
//        int x = input1.length;
//        int y = input2.length;
//        
//        if(x > y) {
//        	// this ensures the first array is either smaller or at least equal in size.
//            return findMedianSortedArrays(input2, input1);
//        }
//        
//        int low = 0;
//        int high = x;
//        while (low <= high) {
//            int partitionX = (low + high)/2;
//            int partitionY = (x + y + 1)/2 - partitionX;
//
//            //if partitionX is 0 it means nothing is there on left side. Use -INF for maxLeftX
//            //if partitionX is length of input then there is nothing on right side. Use +INF for minRightX
//            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : input1[partitionX - 1];
//            int minRightX = (partitionX == x) ? Integer.MAX_VALUE : input1[partitionX];
//
//            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : input2[partitionY - 1];
//            int minRightY = (partitionY == y) ? Integer.MAX_VALUE : input2[partitionY];
//
//            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
//                //We have partitioned array at correct place
//                // Now get max of left elements and min of right elements to get the median in case of even length combined array size
//                // or get max of left for odd length combined array size.
//                if ((x + y) % 2 == 0) {
//                    return ((double)Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY))/2;
//                } else {
//                    return (double)Math.max(maxLeftX, maxLeftY);
//                }
//            } else if (maxLeftX > minRightY) { //we are too far on right side for partitionX. Go on left side.
//                high = partitionX - 1;
//            } else { //we are too far on left side for partitionX. Go on right side.
//                low = partitionX + 1;
//            }
//        }
//
//        //Only we we can come here is if input arrays were not sorted. Throw in that scenario.
//        throw new IllegalArgumentException();
//    }
}
