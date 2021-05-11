/* 
*	AUTHOR: Dillon Barr
*	FILE: MyHashMap.java
*	ASSIGNMENT: Programming Assignment 8 - Generic HashMap
*	COURSE: CSC 210
*	Purpose: This class creates a generic version of a HashMap and implements
*	some of the methods included in the Java documentation for a HashMap.
*/


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MyHashMap<K, V> {
	
	ArrayList <HashNode<K, V>> arr;
	private final static int SIZE = 8;
	private int numBuckets = 8;
	private int nodes;
	
	public MyHashMap() {
		// This is the basic constructor for the class. It initializes
		// an ArrayList of HashNodes to size 8 and sets all the values to null to start.
		// It also sets the numbers of collisions and nodes in the HashMap to 0.
		arr = new ArrayList<HashNode<K, V>>();
		for (int i = 0; i < SIZE; i++) {
			arr.add(i, null);;
		}
		nodes = 0;
	}
	
	public void clear() {
		// This method resets the HashMap to empty by essentially being another constructor.
		arr = new ArrayList<HashNode<K, V>>();
		for (int i = 0; i < SIZE; i++) {
			arr.add(i, null);;
		}
		nodes = 0;
	}
	
	public boolean containsKey(Object key) {
		// This method takes in an Object key, casts it to type K and then
		// loops through the ArrayList and connected nodes to see if the key
		// exists in the map. If so returns true, false otherwise.
		K newKey = (K) key;
		for (HashNode<K, V> node : arr) {
			while (node != null) {
				if (newKey.equals(node.getKey())) {
					return true;
				}
				node = node.getNext();
			}
		}
		return false;
	}
	
	public boolean containsValue(Object value) {
		// This method takes in an Object value, casts it to type V and then
		// loops through the ArrayList and connected nodes to see if the value
		// exists in the map. If so returns true, false otherwise.
		V newValue = (V) value;
		for (HashNode<K, V> node : arr) {
			while (node != null) {
				if (newValue.equals(node.getValue())) {
					return true;
				}
				node = node.getNext();
			}
		}
		return false;
	}
	
	public V get(Object key) {
		// This method takes in an Object key, casts it to type K and then
		// loops through teh ArrayList and connected nodes to see if the key exists in the
		// map. If so the value connected to the key is returned. Otherwise null is returned.
		K newKey = (K) key;
		for (HashNode<K, V> node : arr) {
			while (node != null) {
				if (newKey.equals(node.getKey())) {
					 return node.getValue();
				}
				node = node.getNext();
			}
		}
		return null;
	}
	
	public boolean isEmpty() {
		// This methods checks to see if any nodes have been mapped 
		// to an index in the array list. If there are nodes it returns false and 
		// true otherwise.
		if (nodes != 0) {
			return false;
		}
		
		return true;
	}
	
	public Set<K> keySet() {
		// This method creates a set of all the keys contained in the map
		// and returns the set.
		HashSet<K> keySet = new HashSet<K>();
		for (HashNode <K, V> node : arr) {
			if (node != null) {
				while (node != null) {
					keySet.add(node.getKey());
					node = node.getNext();
				}
			}
		}
		return keySet;
		
	}
	
	public V put(K key, V value) {
		// This method takes in a key, value pair and maps it to a index in the map
		// determined by the hash function. If there is no mapping there then the value
		// pair is placed this and returns null. If a matching key already exists there
		// the value is updated and the old value is returned. If a non-matching key exists there
		// the new node is placed at the front of the LinkedList.
		HashNode<K, V> node = new HashNode<>(key, value);
		int index = hash(key);
		if (arr.get(index) == null) {
			arr.add(index, node);
			nodes += 1;
			return null;
		}
		if (arr.get(index) != null && arr.get(index).getKey().equals(key)) {
			V temp = arr.get(index).getValue();
			arr.get(index).setValue(value);
			return temp;
		}
		else {
			node.setNext(arr.get(index));
			arr.set(index, node);
			nodes += 1;
			return null;
		}
	}
	
	public V remove(Object key) {
		// This method takes in an Object key, casts it to K and finds the index 
		// that the key should be at using the hash method. Then if there is a LinkedList at the
		// index it loops through to find the node and remove it by setting the next node to 
		// be the front of the list or link the previous to the next node depending on where
		// it is found in the list.
		K newKey = (K) key;
		int index = hash(newKey);
		HashNode <K, V >node = arr.get(index);
		if (node != null) {
			HashNode <K, V> temp = null;
			while (node != null) {
				if (node.getKey().equals(newKey)) {
					if (temp == null) {
						V value = arr.get(index).getValue();
						arr.set(index, node.getNext());
						nodes -= 1;
						return value;
					}
					else {
						temp.setNext(node.getNext());
						nodes -= 1;
						return node.getValue();
					}
				}
				temp = node;
				node = node.getNext();
				
			}
			return null;
		}
		return null;
		
		
	}
	
	public int size() {
		// This method returns the number of mappings that exist in the 
		// map.
		return nodes;
	}
	
	public void printTable() {
		// This method loops through all the indexes in the array as well as the linkedlist possibly 
		// contained at each index and prints out a string representation of the table showing
		// the index, number of conflicts and keys of each node in a index if they exist.
		
		int conflicts = 0;
		for (int i = 0; i < SIZE; i++) {
			String output = "";
			if (arr.get(i) == null) {
				output += "Index " + i + ": (0 conflicts), []";
				System.out.println(output);
			}
			else {
				int tempConflicts = 0;
				HashNode<K, V> node = arr.get(i);
				tempConflicts = this.countNodes(node) - 1;
				conflicts += tempConflicts;
				output += "Index " + i + ": (" + tempConflicts + " conficts), [";
				while (node != null) {
					output += node.getKey() + ", ";
					node = node.getNext();
				}
				output += "]";
				System.out.println(output);
			}
			
			}
		System.out.println("Total # of conflicts: " + conflicts);

		}

	
	private int hash(K key) {
		// This method creates an index for a passed in key by first creating
		// a hashcode, modding that hashcode by the number of indexes in the array and then 
		// returning the absolute value of that index.
		int hashCode = key.hashCode();
		int index = hashCode % numBuckets;
		return Math.abs(index);
		}
	
	public int countNodes(HashNode<K, V> first) {
		// Count all of the nodes in the given singly-linked list of
	    // generic HashNode<K,V>s that starts with the given node.
        int counter = 0;
        while (first != null) {
        	first = first.getNext();
        	counter += 1;
        	
        	}
        return counter;
    }
}
