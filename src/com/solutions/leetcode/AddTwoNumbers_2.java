/* Medium
 * https://leetcode.com/problems/add-two-numbers/
 */

package com.solutions.leetcode;

import com.leetcode.solution.utilityclasses.ListNode;

public class AddTwoNumbers_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode dummyHead = new ListNode(0);
		ListNode p = l1, q = l2, curr = dummyHead;
		int carry = 0;
		while (p != null || q != null) {
			int x = (p != null) ? p.val : 0;
			int y = (q != null) ? q.val : 0;
			int sum = carry + x + y;
			carry = sum / 10;
			curr.next = new ListNode(sum % 10);
			curr = curr.next;
			if (p != null)
				p = p.next;
			if (q != null)
				q = q.next;
		}
		if (carry > 0) {
			curr.next = new ListNode(carry);
		}
		return dummyHead.next;
	}

	public ListNode addTwoNumbers_My(ListNode l1, ListNode l2) {
		if (l1 == null && l2 == null) {
			return new ListNode(0);
		}

		if (l1 == null)
			return l2;

		if (l2 == null)
			return l1;

		ListNode head = null;
		ListNode cur = null;

		int carry = 0;

		while (l1 != null && l2 != null) {
			int res = l1.val + l2.val + carry;
			carry = res / 10;
			res = res % 10;

			ListNode resN = new ListNode(res);

			if (head == null) {
				head = resN;
				cur = resN;
			} else {
				cur.next = resN;
				cur = cur.next;
			}

			l1 = l1.next;
			l2 = l2.next;
		}

		ListNode remaining = null;

		if (l1 == null && l2 == null) {
			if (carry > 0) {
				cur.next = new ListNode(carry);
			}
		} else if (l1 == null) {
			remaining = l2;
		} else {
			remaining = l1;
		}

		while (remaining != null) {
			int res = remaining.val + carry;
			carry = res / 10;
			res = res % 10;

			cur.next = new ListNode(res);

			remaining = remaining.next;
			cur = cur.next;
		}

		if (carry > 0) {
			cur.next = new ListNode(carry);
		}

		return head;
	}

}
