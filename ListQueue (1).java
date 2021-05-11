/* Name: Dillon Barr
 * File: ListQueue.java
 * Course: CSC 210
 * Purpose: This class creates a generic queue data structure using a linked list as the underlying structure.
 * It implements the QueueInterface interface and a couple other methods such as a 
 * toString, an equals and a copy constructor. 
*/
public class ListQueue<E> implements QueueInterface<E> {

    private Node<E> front;
    private int size;

    public ListQueue() {
        // This method creates an instance of a ListQueue while initializing
        // the front node to null and setting the size of the queue to 0.
        front = null;
        size = 0;
    }

    public ListQueue(ListQueue<E> other) {
        // This constructor takes in an instance of a queue and creates
        // a new queue object with the exact fields as the one passed in.
        this.front = other.front;
        this.size = other.size;
    }

    @Override
    public void enqueue(E value) {
        // This method takes in a value of type E and adds the value
        // to the end of the linked list, or the back of the queue, and then
        // increments the overall size of the queue by 1.
        Node<E> newNode = new Node<E>(value, null);
        if (front == null) {
            front = newNode;
        } else {
            Node<E> curr = front;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
        }
        size += 1;
    }

    @Override
    public E dequeue() {
        // This method returns the E value of the
        // node at the front of the queue and then removes it
        // by setting the second element to be the head.
        if (front == null) {
            return null;
        } else {
            E top = front.data;
            front = front.next;
            size -= 1;
            return top;
        }
    }

    @Override
    public E peek() {
        // Returns the E value at the front of the queue
        // but does not remove it.
        if (front == null) {
            return null;
        } else {
            return front.data;
        }
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
        // This method empties the queue by
        // setting the front of the linked list to null and the size to 0.
        front = null;
        size = 0;

    }

    public boolean equals(Object o) {
        // This method checks to see if the passed in object is an
        // instance of ListQueue and if some compares if the size is equal and
        // if all the elements are the same. Returns true if so false otherwise.
        if (!(o instanceof ListQueue<?>)) {
            return false;
        }
        ListQueue<?> other = (ListQueue<?>) o;
        if (size == other.size) {
            Node<E> curr = front;
            Node<?> otherCurr = other.front;
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
        Node<E> curr = front;
        while (curr != null) {
            stack += curr.data + ", ";
            curr = curr.next;
        }

        stack += "}";
        return stack;
    }

    private static class Node<E> {
        // This class creates a node object that make
        // up the elements of the LinkedList used for
        // the ListStack class.
        E data;
        Node<E> next;

        public Node(E data, Node<E> next) {
            // Creates a node with the passed in integer
            // set as the data field and next is set to the
            // passed in Node object.
            this.data = data;
            this.next = next;
        }
    }

}