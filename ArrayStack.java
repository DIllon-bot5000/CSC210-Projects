/* 
 * Name: Dillon Barr
 * File: ArrayStack.java
 * Class: CSC 210
 * Purpose: This class creates a stack data structure using an array as the underlying structure.
 * It implements the required methods of a StackInterface interface and a couple other methods such as
 * toString, equals and a copy constructor.  
*/

public class ArrayStack implements StackInterface {

    private int[] array;
    private static final int DEFAULT_CAPACITY = 1000;
    private int size;
    private int capacity;

    public ArrayStack() {
        // This is the default constructor for the class,
        // it instantiates an array of default capacity and
        // sets the size to 0.
        array = new int[DEFAULT_CAPACITY];
        size = 0;
        capacity = DEFAULT_CAPACITY;
    }

    public ArrayStack(ArrayStack stack) {
        // This constructor takes in an instance of a stack and creates
        // a new stack object with the exact fields as the one passed in.
        this.array = stack.array;
        this.size = stack.size;
        this.capacity = stack.capacity;
    }

    private void growArray() {
        // This method is called in the push method if the
        // stack has outgrown the alloted size and creates a new
        // array with double the capacity.
        int[] newArray = new int[capacity * 2];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
        capacity *= 2;
    }

    public void push(int value) {
        // This method takes in an integer value and adds the value
        // to the end of the array, or the top of the stack, and then
        // increments the overall size of the stack by 1.
        if (size > capacity) {
            this.growArray();
        }
        array[size] = value;
        size += 1;
    }

    public int pop() {
        // This method returns the value at the top of the stack
        // and decrements the size of the stack by 1.
        int top = array[size - 1];
        size -= 1;
        return top;
    }

    public int peek() {
        // This method shows the user the value at the top of the stack but does
        // not remove the item from the stack.
        if (size == 0) {
            return -1;
        }
        return array[size - 1];
    }

    public boolean isEmpty() {
        // This method returns whether or not the stack is empty.
        return size == 0;
    }

    public int size() {
        // This method returns the size of the stack.
        return size;
    }

    public void clear() {
        // This method "empties" the stack by
        // setting the size to 0.
        size = 0;
    }

    @Override
    public boolean equals(Object o) {
        // This method checks to see if the passed in object is an
        // instance of ArrayStack and if some compares if the size is equal and
        // if all the elements are the same. Returns true if so false otherwise.
        if (!(o instanceof ArrayStack)) {
            return false;
        }
        ArrayStack other = (ArrayStack) o;
        if (size == other.size) {
            for (int i = 0; i < size; i++) {
                if (array[i] != other.array[i]) {
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
        // in the stack separated by a comma. Returns empty brackets if the
        // stack is empty.
        String stack = "{ ";
        for (int i = 0; i < size; i++) {
            stack += array[i] + ", ";
        }
        stack += "}";
        return stack;
    }
}