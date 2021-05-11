/* 
 * Name: Dillon Barr
 * File: ArrayQueue.java
 * Class: CSC 210
 * Purpose: This class creates a queue data structure using an array as the underlying structure. It implements 
 * the methods required by the QueueInterface interface and a couple other methods such as
 * toString, equals and a copy constructor. 
*/

public class ArrayQueue implements QueueInterface {

    private final static int MAX_CAPACITY = 1000;
    private int[] queue;
    private int size;
    private int capacity;

    public ArrayQueue() {
        // This is the default constructor for the class,
        // it instantiates an array of default capacity and
        // sets the size to 0.
        queue = new int[MAX_CAPACITY];
        size = 0;
        capacity = MAX_CAPACITY;
    }

    public ArrayQueue(ArrayQueue queue) {
        // This constructor takes in an instance of a queue and creates
        // a new queue object with the exact fields as the one passed in.
        this.queue = queue.queue;
        this.size = queue.size;
        this.capacity = queue.capacity;
    }

    private void growQueue() {
        // This method is called in the enqueue method if the
        // queue has outgrown the alloted size and creates a new
        // array with double the capacity.
        int[] newQueue = new int[capacity * 2];
        for (int i = 0; i < size; i++) {
            newQueue[i] = queue[i];
        }
        queue = newQueue;
        capacity *= 2;
    }

    @Override
    public void enqueue(int value) {
        // This method takes in an integer value and adds the value
        // to the end of the array, or the back of the queue, and then
        // increments the overall size of the queue by 1.
        if (size > capacity) {
            this.growQueue();
        }
        queue[size] = value;
        size += 1;
    }

    @Override
    public int dequeue() {
        // This method returns the integer value of the
        // element at the front of the queue and then removes it
        // by shifting the rest of the elements over one index.
        int front = queue[0];
        for (int i = 0; i < size - 1; i++) {
            queue[i] = queue[i + 1];
        }
        size -= 1;
        return front;
    }

    @Override
    public int peek() {
        // Returns the integer at the front of the queue
        // but does not remove it.
        int front = queue[0];
        return front;
    }

    @Override
    public boolean isEmpty() {
        // This method checks to see if the queue is empty
        // or not and returns a true or false.
        return size == 0;
    }

    @Override
    public int size() {
        // This method returns the current size of the queue
        return size;
    }

    @Override
    public void clear() {
        // This method "empties" the queue by
        // setting the size of the queue to 0.
        size = 0;

    }

    @Override
    public boolean equals(Object o) {
        // This method checks to see if the passed in object is an
        // instance of ArrayQueue and if some compares if the size is equal and
        // if all the elements are the same. Returns true if so false otherwise.
        if (!(o instanceof ArrayQueue)) {
            return false;
        }
        ArrayQueue other = (ArrayQueue) o;
        if (size == other.size) {
            for (int i = 0; i < size; i++) {
                if (queue[i] != other.queue[i]) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        // This method returns a string representation of each element
        // in the queue separated by a comma. Returns empty brackets if the
        // stack is empty.
        String stack = "{ ";
        for (int i = 0; i < size; i++) {
            stack += queue[i] + ", ";
        }
        stack += "}";
        return stack;
    }

}