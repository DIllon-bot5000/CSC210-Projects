/*
 *  AUTHOR: Dillon Barr
 *  FILE: PatientQueue.java
 *  ASSIGNMENT: Programming Assignment 9 - PatientQueue
 *  COURSE: CSC 210
 *  Purpose: This class creates a priority queue for the purpose of holding
 *  "patients" in an ER as well as their priority to be seen. The class
 *  implements the methods one typically finds in a queue such as enqueue, dequeue and peek as
 *  well as a changePriority in the even a patient gets better or worse. I couldn't figure this one out completely
 *  so any feedback you give would be greatly appreciated.
*/

public class PatientQueue {

    private Patient[] patientQueue;
    private int size;
    private int capacity;

    public PatientQueue() {
        // This method is the constructor for the queue. It creates an array to
        // contain Patient objects initially with a capacity of 10 and sets the
        // current size of the queue to 0.
        patientQueue = new Patient[10];
        size = 0;
        capacity = 10;
    }

    public void growQueue() {
        // This method doubles the capacity of the queue and copies the elements
        // over in the event that original queue reaches max capacity.
        Patient[] newQueue = new Patient[capacity * 2];
        for (int i = 0; i < size; i++) {
            newQueue[i] = patientQueue[i];
        }
        patientQueue = newQueue;
        capacity *= 2;
    }

    public void enqueue(String name, int priority) {
        // This method takes in a name and a priority level and creates a new
        // Patient object with this parameters. It then runs a series to checks,
        // first to see if the queue needs to be grown and then if the queue is
        // empty
        // before adding the element to the queue. If the queue is not empty, it
        // calls the
        // bubbleUp method to find the correct position for it.
        Patient arrival = new Patient(name, priority);
        if (size == capacity - 1) {
            growQueue();
            size += 1;
            patientQueue[size] = arrival;
            bubbleUp(size);
        }
        if (size == 0) {
            patientQueue[1] = arrival;
            size += 1;
        } else {
            size += 1;
            int index = size;
            patientQueue[size] = arrival;
            bubbleUp(index);
        }
    }

    public void enqueue(Patient patient) {
        // This method takes a Patient object as the parameter. It then runs a
        // series to checks,
        // first to see if the queue needs to be grown and then if the queue is
        // empty
        // before adding the element to the queue. If the queue is not empty, it
        // calls the
        // bubbleUp method to find the correct position for it.
        if (size == capacity - 1) {
            growQueue();
            size += 1;
            patientQueue[size] = patient;
            bubbleUp(size);
        }
        if (size == 0) {
            patientQueue[1] = patient;
            size += 1;
        } else {
            size += 1;
            int index = size;
            patientQueue[size] = patient;
            bubbleUp(index);
        }

    }

    public String dequeue() throws Exception {
        // This method removes the first element from the the queue and returns
        // the
        // String name of the object located there. After removing
        // the element the bubbleDown method is called to
        // organize the queue. If the queue is empty it returns a message
        // informing the user of the fact.
        if (size == 0) {
            System.out.println("Cannot dequeue from an empty queue");
        }
        String name = patientQueue[1].name;
        patientQueue[1] = patientQueue[size];
        size -= 1;
        bubbleDown(1);
        return name;
    }

    public String peek() throws Exception {
        // This method returns the String name of the object located there in
        // the first index of the queue.
        // If the queue is empty it returns a message informing the user of the
        // fact.
        if (size == 0) {
            System.out.println("Cannot peek into an empty queue");
        }
        return patientQueue[1].name;
    }

    public int peekPriority() throws Exception {
        // This method returns the int priority of the object located there in
        // the first index of the queue.
        // If the queue is empty it returns a message informing the user of the
        // fact.
        if (size == 0) {
            System.out.println("Cannot peek into an empty queue");
        }
        return patientQueue[1].priority;
    }

    public void changePriority(String name, int newPriority) {
        // This method takes in a String name of an object that might exist in
        // the queue
        // and a new priority this patient needs. The method loops through the
        // queue to find the
        // matching object and sets the new priority, if the new priority
        // requires the object to bubbleUp,
        // the bubbleUp method is called, otherwise the bubbleDown method is
        // called.
        for (int i = 1; i <= size; i++) {
            if (patientQueue[i] != null && patientQueue[i / 2] != null) {
                if (patientQueue[i].name.equals(name)
                        && newPriority < patientQueue[i / 2].priority) {
                    patientQueue[i].priority = newPriority;
                    bubbleUp(i);
                    break;
                } else if ((patientQueue[i].name.equals(name)
                        && newPriority > patientQueue[i * 2].priority)) {
                    patientQueue[i].priority = newPriority;
                    bubbleDown(i);
                    break;
                }
            }
        }
    }

    public boolean isEmpty() {
        // Returns a boolean true if there is nothing in the queue,
        // false otherwise.
        return size == 0;
    }

    public int size() {
        // This method returns the number of elements
        // in the queue.
        return size;
    }

    public void clear() {
        // "Clears" the queue by setting the size of the queue to 0.
        size = 0;
    }

    public String toString() {
        // Returns a string representation of the queue showing each
        // name and priority of the object at each index of the queue.
        if (size == 0) {
            return "{}";
        } else {
            String output = "{";
            for (int i = 1; i <= size; i++) {
                output += patientQueue[i].name + " (" + patientQueue[i].priority
                        + "), ";
            }
            String fixed = output.substring(0, output.length() - 2);
            fixed += "}";
            return fixed;
        }
    }

    private void bubbleUp(int position) {
        // This method is one of the two sorting methods in this class. This one
        // checks to see
        // if the element at the passed in position is of a higher priority than
        // the one at the parent index of
        // index / 2. If so the two indexes are swapped.
        int parentPosition = position / 2;
        int current = position;
        if (patientQueue[parentPosition] != null) {
            if (patientQueue[parentPosition].priority == patientQueue[current].priority
                    && patientQueue[current].name
                            .compareTo(patientQueue[parentPosition].name) < 0) {
                swap(current, parentPosition);
            } else {
            while (current > 1
                    && patientQueue[parentPosition].priority > patientQueue[current].priority) {
                swap(current, parentPosition);
                current = parentPosition;
                parentPosition = parentPosition / 2;
            }
        }
        }
    }

    private void bubbleDown(int a) {
        // This method is the second sorting method in this class and it checks
        // to see if the
        // element at the passed in index is a higher priority than that of its
        // children. If so
        // the indexes are swapped and the function recursively called until the
        // order is correct.
        int smallest = a;
        int leftChildIdx = 2 * a;
        int rightChildIdx = 2 * a + 1;
        if (leftChildIdx < size
                && patientQueue[smallest].priority > patientQueue[leftChildIdx].priority) {
            smallest = leftChildIdx;
        }
        if (patientQueue[smallest].priority == patientQueue[leftChildIdx].priority
                && patientQueue[smallest].name
                        .compareTo(patientQueue[leftChildIdx].name) > 0) {
            smallest = leftChildIdx;
        }
        if (rightChildIdx < size
                && patientQueue[smallest].priority > patientQueue[rightChildIdx].priority) {
            smallest = rightChildIdx;
        }
        if (patientQueue[smallest].priority == patientQueue[rightChildIdx].priority
                && patientQueue[smallest].name
                        .compareTo(patientQueue[rightChildIdx].name) > 0) {
            smallest = rightChildIdx;
            if (smallest != a) {

                swap(a, smallest);
                bubbleDown(smallest);
            }
        }
    }

    private void swap(int a, int b) {
        // This method takes two indexes from the queue and
        // swaps the objects contained at those two indexes.
        Patient temp = patientQueue[a];
        patientQueue[a] = patientQueue[b];
        patientQueue[b] = temp;
    }
}
