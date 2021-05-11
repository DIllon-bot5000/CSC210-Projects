import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
*
*
* AUTHOR: Dillon Barr
* FILE: WordSearch.java
* ASSIGNMENT: Programming Assignment 1 - WordSearch
* COURSE: CSC 210
* PURPOSE: This program reads in two files, one text file containing all 
* the valid words that can be found and then a text file containing the height 
* and width specifications of a word search and the letter combinations that will be 
* searched. After reading in the program searches 4 ways and outputs all the
* found words in the order they were found.
*
*
* USAGE: 
* java PA1Main infile 
*
*
* where infile is the name of an input file in the following format
* ----------- EXAMPLE INPUT -------------
* Input file:
* 6
* 6
* y c o d e j
* h s e y p k
* l p h b w a
* l o b w x z
* w o b a a i
* p l y y c g
* 
*/

public class WordSearch {
    private static final int MIN_WORD_LENGTH = 3;

    static public void main(String[] args) {
        int[] dimensions = getDimensions(args);
        int row = dimensions[0];
        int column = dimensions[1];
        List<String> dictionary = storeDict(args);
        String[][] grid = storeGrid(args);
        searchHorizontal(grid, row, column, dictionary, false);
        searchHorizontal(grid, row, column, dictionary, true);
        searchVertical(grid, row, column, dictionary, false);
        searchVertical(grid, row, column, dictionary, true);
    }

    /*
     * Purpose: This method reads the first two lines of the file with
     * the word search specifications to get the dimensions.
     *
     *
     * @param args, command line arguments
     *
     *
     * @return dimensions, an array containing the dimensions of the word search
     */
    static public int[] getDimensions(String[] args) {
        int[] dimensions = new int[2];
        Scanner numInput = null;

        try {
            numInput = new Scanner(new File(args[1]));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        dimensions[0] = Integer.valueOf(numInput.nextLine());
        dimensions[1] = Integer.valueOf(numInput.nextLine());
        numInput.close();
        return dimensions;

    }

    /*
     * Purpose: A method that reads in a dictionary file and stores
     * all the words in a List of strings.
     *
     *
     * @param args, command line arguments
     *
     *
     * @return list, a list of Strings containing all the words in the
     * dictionary file.
     */
    static public List<String> storeDict(String[] args) {
        List<String> dictionary = new ArrayList<String>();
        Scanner dictFile = null;

        try {
            dictFile = new Scanner(new File(args[0]));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (dictFile.hasNext()) {
            dictionary.add(dictFile.nextLine().toLowerCase());
        }
        dictFile.close();
        return dictionary;
    }
    /*
     * Purpose: This method reads in the file containing the
     * contents of the word search and stores them in a 2D array
     * built as specified by the file two lines of the file.
     *
     *
     * @param args, command line arguments.
     *
     *
     * @return grid, a 2D array containing the word search.
     */

    static public String[][] storeGrid(String args[]) {
        Scanner gridFile = null;
        try {
            gridFile = new Scanner(new File(args[1]));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int row = Integer.valueOf(gridFile.nextLine());
        int column = Integer.valueOf(gridFile.nextLine());
        
        String[][] grid = new String[row][column];
        
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < column; y++) {
                grid[x][y] = gridFile.next().toLowerCase();
            }
        }
        gridFile.close();
        return grid;
    }
    /*
     * Purpose: A method that searches across the lines of the word search left
     * to right if reverse is false, searches right to left otherwise.
     * It calls containsCheck to see if any words found in the search are valid.
     *
     *
     * @param grid is the 2D array containing the word search, row and column
     * represent
     * the dimensions of the word search and dictionary is the list of Strings taken
     * from the
     * dictionary. reverse is a boolean.
     *
     *
     * @return None.
     */

    static public void searchHorizontal(String[][] grid, int row, int column,
            List<String> dictionary, boolean reverse) {
    	if (reverse == false) {
    		for (int x = 0; x < row; x++) {
    			String word = "";
    			for (int y = 0; y < column; y++) {
    				word += grid[x][y];
    			}
    			containsCheck(dictionary, word);
    		}
    	}
    	else {
    		for (int x = 0; x < row; x++) {
    			String word = "";
    			int index = column - 1;
    			for (int y = 0; y < column; y++) {
    				word += grid[x][index];
    				index--;
            		}	
    			containsCheck(dictionary, word);
        		}
    		}
    }
  

    /*
     * Purpose: A method that searches across the lines of the word search top
     * to bottom if reverse is false, searches bottom to top otherwise.
     * It calls containsCheck to see if any words found in the search are valid.
     *
     *
     * @param grid is the 2D array containing the word search, row and column
     * represent
     * the dimensions of the word search and dictionary is the list of Strings taken
     * from the
     * dictionary.
     *
     *
     * @return None.
     */
    static public void searchVertical(String[][] grid, int row, int column,
            List<String> dictionary, boolean reverse) {
    	if (reverse == false) {
    		int index = 0;
    		while (index < column) {
    			String word = "";
    			for (int x = 0; x < row; x++) {
    				word += grid[x][index];
    			}
    			containsCheck(dictionary, word);
    			index += 1;
        		}
    		}
    	else {
    		int counter = 0;
            int reverseCounter = 0;
            while (counter < column) {
                String word = "";
                int index = row - 1;
                for (int x = 0; x < row; x++) {
                    word += grid[index][reverseCounter];
                    index -= 1;
                }
                containsCheck(dictionary, word);
                reverseCounter += 1;
                counter += 1;
            }
    	}
    }


    /*
     * Purpose: This method takes a string from the word search and loops to
     * find
     * any words contained within the string by checking if they exist in the
     * dictionary list created earlier.
     *
     *
     * @param dictionary is the String list created from the dictionary file and word
     * is a String taken from the various searches of the program.
     *
     *
     * @return None.
     */

    static public void containsCheck(List<String> dictionary, String word) {
        int counter = MIN_WORD_LENGTH;
        int i = 0;
        while (i < word.length()) {
            if (i + counter > word.length()) {
                counter = MIN_WORD_LENGTH;
                i++;
            } else {
                String newWord = word.substring(i, i + counter);
                if (dictionary.contains(newWord) == true) {
                    System.out.println(newWord);
                }
                counter += 1;
            }
        }
    }
}