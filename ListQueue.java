/* 
 * Name: Dillon Barr
 * File: ListQueue.java
 * Class: CSC 210
 * Purpose: This class creates a queue data structure using a linked list as the underlying structure. 
 * It implements the methods required by the QueueInterface interface and a couple other methods such as 
 * toString, equals and a copy constructor. 
*/

public class ListQueue implements QueueInterface {

    private Node front;
    private int size;

    public ListQueue() {
        // This method creates an instance of a ListQueue while initializing
        // the front node to null and setting the size of the queue to 0.
        front = null;
        size = 0;
    }

    public ListQueue(ListQueue other) {
        // This constructor takes in an instance of a queue and creates
        // a new queue object with the exact fields as the one passed in.
        this.front = other.front;
        this.size = other.size;
    }

    @Override
    public void enqueue(int value) {
        // This method takes in an integer value and adds the value
        // to the end of the linked list, or the back of the queue, and then
        // increments the overall size of the queue by 1.
        Node newNode = new Node(value, null);
        if (front == null) {
            front = newNode;
        } else {
            Node curr = front;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
        }
        size += 1;
    }

    @Override
    public int dequeue() {
        // This method returns the integer value of the
        // node at the front of the queue and then removes it
        // by setting the second element to be the head.
        int top = front.data;
        front = front.next;
        size -= 1;
        return top;
    }

    @Override
    public int peek() {
        // Returns the integer at the front of the queue
        // but does not remove it.
        return front.data;
    }

    @Override
    public boolean isEmpty() {
        // This method checks to see if the queue is empty
        // or not and returns a true or false.
        return size == 0;
    }

    @Override
    public int size() {
        // This method returns the current size of the queue.
        return size;
    }

    @Override
    public void clear() {
        // This method "empties" the queue by
        // setting the front of the linked list to null.
        front = new Node(0, null);

    }

    public boolean equals(Object o) {
        // This method checks to see if the passed in object is an
        // instance of ListQueue and if some compares if the size is equal and
        // if all the elements are the same. Returns true if so false otherwise.
        if (!(o instanceof ListQueue)) {
            return false;
        }
        ListQueue other = (ListQueue) o;
        if (size == other.size) {
            Node curr = front;
            Node otherCurr = other.front;
            while (curr.next != null) {
                if (curr.data != otherCurr.data) {
                    return false;
                }
                curr = curr.next;
                otherCurr = otherCurr.next;
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
        Node curr = front;
        while (curr != null) {
            stack += curr.data + ", ";
            curr = curr.next;
        }

        stack += "}";
        return stack;
    }

    private static class Node {
        // This class creates a node object that make
        // up the elements of the LinkedList used for
        // the ListStack class.
        int data;
        Node next;

        public Node(int data, Node next) {
            // Creates a node with the passed in integer
            // set as the data field and next is set to the
            // passed in Node object.
            this.data = data;
            this.next = next;
        }
    }

}