/* Name: Dillon Barr
 * File: StackInterface.java
 * Course: CSC 210
 * Purpose: This interface contains methods to implement
 * in a class to give it the behavior of a stack. It contains the
 * normal methods such as push, pop and peek as well as a method to
 * clear it, check the size and see if the stack is empty.
 */
interface StackInterface<E> {

    /*
     * Add an element to the top of the stack.
     */
    void push(E value);

    /*
     * Remove and return the top element in the stack.
     * 
     * If the user attempts to pop an empty stack, ignore the
     * request (i.e. make no changes to the stack) and return -1.
     */
    E pop();

    /*
     * Return (but do NOT remove) the top element of the stack.
     * 
     * If the user attempts to peek on an empty stack, ignore the
     * request (i.e. make no changes to the stack) and return -1.
     */
    E peek();

    /*
     * Returns true if the stack has no elements.
     */
    boolean isEmpty();

    /*
     * Returns the number of elements in the stack.
     */
    int size();

    /*
     * Removes all elements from the stack.
     */
    void clear();

}