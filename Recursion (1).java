/*
 * There is no requirement for a file header comment for this 
 * assignment. Spend your time writing good testcases instead!
 */
import java.util.Queue;
import java.util.Stack;

public class Recursion {

	/**
	 * Write a recursive function that finds the index of s2 in s1. Do not use any
	 * string functions except for .length(), .equals(), and .substring(). Do not use
	 * any loops, or any data structures.
	 * @param s1
	 * @param s2
	 * @return Returns the index of the first time that
	 * 			s2 appears in s1 or -1 if s2 is not contained
	 * 			in s1.
	 */
	public static int indexOf(String s1, String s2) {
		if (s2.length() > s1.length()){
            return -1;
        }
		if (s1.substring(0,s2.length()).equals(s2)){
            return 0;
        }

        if (s1.length() == 0 || s2.length() <= 0){
            return -1;
        }
        
        if(s1.length() == s2.length() &&
        		!s1.substring(0,1).equals(s2.substring(0,1))) {
        	return -1;
        }

        if (s1.length() <= 1){
            return -1;
        }

        return (1 + indexOf(s1.substring(1,s1.length()), s2));
    }

	/**
	 * Write a recursive function that removes the first k even numbers
	 * from the stack. If there are less than k even elements in the stack,
	 * just remove all even elements. Do not use any loops or data structures
	 * other than the stack passed in as a parameter.
	 * @param stack
	 * @param k
	 * @return Returns the number of elements removed from the stack.
	 */
	public static int removeEvenNumbers(Stack<Integer> stack, int k) {
		if (stack.isEmpty()) {
			return 0;
		}
		if (k  == 0 && !stack.isEmpty()) {
			return k;
		}
		if (stack.peek() % 2 == 0) {
			stack.pop();
			k -= 1;
			return removeEvenNumbers(stack, k) + 1;
		}
		else {
			stack.pop();
			return removeEvenNumbers(stack, k);
			
		}	
	}

	/**
	 * Write a recursive function that accepts an integer and
	 * returns a new number containing only the even digits, in the same
	 * order. If there are no even digits, return 0. Your function should
	 * work for positive or negative numbers. You are NOT allowed
	 * to use any data structures. You are not allowed to use Strings to
	 * solve this problem either.
	 * @param n
	 * @return The input with only the even digits remaining in the same
	 * order.
	 */
	public static int evenDigits(int n) {
		if(n == 0) {
	        return 0;
		}
	        
	    int last = n % 10;
	    
	    if(last % 2 == 0) {
	        return 10 * evenDigits(n / 10) + last;
	    }
	        
	    return evenDigits(n / 10);
	}

	/**
	 * Write a recursive function that evaluates a Queue<Character> as a mathematical
	 * expression. This queue can have any of the following characters:
	 * { '(', ')', '+', '*'} or any single digit number. Evaluate this expression and
	 * return the result. For example, for the expression:
	 * "(((1+2)*(3+1))+(1*(2+2)))", each of these characters would be in the
	 * q. As you recursively evaluate characters from the expression, you will
	 * remove the characters from the q. After evaluating the above expression,
	 * you should return 16. You are guaranteed that there are NO two digit numbers,
	 * and that the expression is well formed (parenthesis match, etc...). Do not use any
	 * loops. Do not use any data structures besides the q passed in as a parameter.
	 * @param q
	 * @return The result of the mathematical expression.
	 */
	public static int evaluate(Queue<Character> q) {
		return 0;
		
	}
	

	/**
	 * Write a recursive function that accepts a stack of integers and
	 * replaces each int with two copies of that integer. For example,
	 * calling repeatStack and passing in a stack of { 1, 2, 3} would change
	 * the stack to hold { 1, 1, 2, 2, 3, 3}. Do not use any loops. Do not use
	 * any data structures other than the stack passed in as a parameter.
	 * @param stack
	 */
	public static void repeatStack(Stack<Integer> stack) {
		if (stack.size() == 1) {
		stack.push(stack.peek());
		}
		else if (stack.size() > 0) {
			int elem = stack.pop();
			repeatStack(stack);
			stack.push(elem);
			stack.push(elem);
		}
	}
	/**
	 * Write a recursive function that accepts a Queue<Integer>. It
	 * should change every int in this queue to be double its original
	 * value. You may NOT use loops or any other data structures besides
	 * the queue passed in as a parameter. You may use a helper function.
	 * @param q
	 */
	public static void doubleElements(Queue<Integer> q) {
		int size = q.size();
		doubleHelper(q, size);
	}
	
	// This helper does the work the function about describes. The only
	// change is there is an int passed in to keep count of the amount of
	// elements left to be doubled in the queue.
	public static void doubleHelper(Queue<Integer> q, int size) {
		if (size != 0) {
			int element = q.remove();
			q.add(element * 2);
			doubleHelper(q, size-1);
		}
	}

}