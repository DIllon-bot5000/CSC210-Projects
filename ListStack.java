/* 
 * Name: Dillon Barr
 * File: ListStack.java
 * Class: CSC 210
 * Purpose: This class creates a stack data structure using a linked list as the underlying structure.
   It implements the methods required by the StackInterface interface and a couple other methods such as a 
   toString, an equals and a copy constructor. 
*/
public class ListStack implements StackInterface {

    private Node front;
    private int size;

    public ListStack() {
        // This method creates an instance of a ListStack while initializing
        // the front node to null and setting the size of the stack to 0.
        front = null;
        size = 0;
    }

    public ListStack(ListStack list) {
        // This constructor takes in an instance of a stack and creates
        // a new stack object with the exact fields as the one passed in.
        this.front = list.front;
        this.size = list.size;
    }

    @Override
    public void push(int value) {
        // This method takes in an integer value and adds the value
        // to the end of the linked list, or the top of the stack, and then
        // increments the overall size of the stack by 1.
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
    public int pop() {
        // This method returns the value at the top of the stack
        // and decrements the size of the stack by 1. It uses
        // the little brother method to have a Node variable that keeps track
        // of the previous node to remove the last node from the stack.
        Node curr = front;
        Node tracker = front;
        while (curr.next != null) {
            tracker = curr;
            curr = curr.next;
        }
        int top = curr.data;
        tracker.next = null;
        size -= 1;
        return top;
    }

    @Override
    public int peek() {
        // This method shows the user the value at the top of the stack but does
        // not remove the item from the stack.
        Node curr = front;
        while (curr.next != null) {
            curr = curr.next;
        }
        int top = curr.data;
        return top;
    }

    @Override
    public boolean isEmpty() {
        // This method returns whether or not the stack is empty.
        return size == 0;
    }

    @Override
    public int size() {
        // This method returns the size of the stack.
        return size;
    }

    @Override
    public void clear() {
        // This method "empties" the stack by
        // setting the front node to null.
        front = new Node(0, null);
    }

    public boolean equals(Object o) {
        // This method checks to see if the passed in object is an
        // instance of ListStack and if some compares if the size is equal and
        // if all the elements are the same. Returns true if so false otherwise.
        if (!(o instanceof ListStack)) {
            return false;
        }
        ListStack other = (ListStack) o;
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
        // in the stack separated by a comma. Returns empty brackets if the
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