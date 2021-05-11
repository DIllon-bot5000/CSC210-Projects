/* 
 * Name: Dillon Barr
 * File: QueueInterface.java
 * Course: CSC 210
 * Purpose: This interface contains methods to implement to a
 * class to give it the behavior of a queue. It contains the 
 * standard enqueue, dequeue and peek methods as well as methods to
 * clear it, find the size and check if it is empty.
 */
interface QueueInterface<E> {

    /*
     * Add an element to the back of the queue.
     */
    void enqueue(E value);

    /*
     * Remove and return the front element in the queue.
     * 
     * If the user attempts to dequeue from an empty queue, ignore the
     * request (i.e. make no changes to your queue) and return -1.
     */
    E dequeue();

    /*
     * Return (but do NOT remove) the front element of the queue.
     * 
     * If the user tries to peek on an empty queue, ignore the
     * request (i.e. make no changes to your queue) and return -1.
     */
    E peek();

    /*
     * Returns true if the queue has no elements.
     */
    boolean isEmpty();

    /*
     * Returns the number of elements in the queue.
     */
    int size();

    /*
     * Removes all elements from the queue.
     */
    void clear();
}