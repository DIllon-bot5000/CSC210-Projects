
/*
*
*
* AUTHOR: Dillon Barr
* FILE: JobSkills.java
* ASSIGNMENT: Programming Assignment 2 - JobSkills
* COURSE: CSC 210; Summer 2020
* PURPOSE: This program reads in a csv file containing information about open job
* positions including the title, location and number of open positions for the job.
* Using command line prompts of CATCOUNT or LOCATION and job category the program 
* either prints out all the available jobs and the number of positions open or the 
* locations and open available jobs tied to a specific job. If an invalid command is read in
* the program prints out Invalid command.
*
*Input: the input file is a csv file in the format:
*Company,Title,Category,Location,Responsibilities,Minimum Qualifications,Preferred Qualifications
*
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PA2Main {
    public static void main(String[] args) {
        if (args.length == 2 && args[1].equals("CATCOUNT")) {
            Map<String, Integer> jobFile = openAndStoreCategory(args[0]);
            searchAndPrintCategory(jobFile);
        } else if (args[1].equals("LOCATIONS")) {
            Map<String, Map<String, Integer>> jobFile = openAndStoreLocations(
                    args[0]);
            searchAndPrintLocation(jobFile, args);
        } else {
            System.out.println("Invalid Command");
        }
    }

    /*
     * Purpose: This method opens the csv file to be stored in a map. Each line
     * being read in
     * from the csv is split on the commas and stored as an ArrayList. From
     * there it
     * checks if a key exists in the Map<String, Integer> where the String is
     * the job
     * position and the Integer is the number of open positions. If is does not
     * exist a new key value pair
     * is created and if the key does exist the number of open positions is
     * incremented.
     *
     *
     * @param a string representing the csv file.
     *
     *
     * @return a Map<String, Integer> containing jobs and open positions.
     */
    public static Map<String, Integer> openAndStoreCategory(String fileName) {
        Map<String, Integer> categories = new HashMap<String, Integer>();
        Scanner fileInput = null;
        try {
            fileInput = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        fileInput.nextLine();
        while (fileInput.hasNext()) {
            String[] list = (fileInput.nextLine().split(","));
            ArrayList<String> newList = new ArrayList<>(Arrays.asList(list));
            String category = newList.get(2);
            if (categories.containsKey(category)) {
                int count = categories.get(category);
                count++;
                categories.put(category, count);
            } else {
                categories.put(category, 1);
            }
        }
        fileInput.close();
        return categories;
    }

    /*
     * Purpose: This method opens the csv file to be stored in a map. Each line
     * being read in
     * from the csv is split on the commas and stored as an ArrayList. From
     * there it
     * checks if a key exists in the Map<String, Map<String, Integer> where the
     * String is the location
     * position and the second map contains the job title as the key and the
     * number of open positions
     * as the value. If is does not exist a new key for the location is created
     * and a new map
     * containing the job and number of positions open is created. If the key
     * does exist the number of open positions is incremented.
     *
     *
     * @param a string representing the csv file.
     *
     *
     * @return a Map<String, Map<String, Integer> containing locations and then
     * another map containing the job title and number of open positions.
     */
    public static Map<String, Map<String, Integer>> openAndStoreLocations(
            String fileName) {
        Map<String, Map<String, Integer>> jobFile = new HashMap<String, Map<String, Integer>>();
        Scanner fileInput = null;
        try {
            fileInput = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        fileInput.nextLine();
        while (fileInput.hasNext()) {
            String[] list = (fileInput.nextLine().split(","));
            ArrayList<String> newList = new ArrayList<>(Arrays.asList(list));
            String location = newList.get(3);
            String category = newList.get(2);
            if (jobFile.containsKey(location)) {
                Map<String, Integer> newMap = jobFile.get(location);
                if (newMap.containsKey(category)) {
                    int count = newMap.get(category);
                    count++;
                    newMap.put(category, count);
                } else {
                    newMap.put(category, 1);
                }
            } else {
                Map<String, Integer> newMap = new HashMap<String, Integer>();
                newMap.put(category, 1);
                jobFile.put(location, newMap);
            }
        }
        fileInput.close();
        return jobFile;
    }

    /*
     * Purpose: This method stores the desired category and proceeds
     * to put the keys in an ArrayList and sort them. It then loops through the
     * array list and uses
     * the Map<String, Map<String, Integer>> to see if the location
     * has openings in the desired category, and if so prints out the location
     * and number of jobs open there.
     *
     *
     * @param a Map< String, Map<String, Integer> containing locations job
     * titles and open
     * positions and the String[]command line arguments.
     *
     * @return None.
     */
    public static void searchAndPrintLocation(
            Map<String, Map<String, Integer>> jobFile, String[] args) {
        String category = args[2];
        System.out.println("LOCATIONS for category: " + category);
        System.out.println("-------------------------------------");
        ArrayList<String> sortedKeys = new ArrayList<String>(jobFile.keySet());
        Collections.sort(sortedKeys);
        for (String key : sortedKeys) {
            if (jobFile.containsKey(key)) {
                Map<String, Integer> potentialMatch = jobFile.get(key);
                if (potentialMatch.containsKey(category)) {
                    System.out
                            .println(key + ", " + potentialMatch.get(category));
                }
            }
        }
    }

    /*
     * Purpose: This method puts the keys from categories in an ArrayList and
     * sort
     * them. It then loops
     * through the array list and uses the Map<String, Integer> and
     * prints out the job title and the number of openings that exist.
     *
     *
     * @param a Map<String, Integer> containing job titles and the
     * number of open positions.
     *
     * @return None.
     */
    public static void searchAndPrintCategory(Map<String, Integer> categories) {
        ArrayList<String> sortedKeys = new ArrayList<String>(
                categories.keySet());
        Collections.sort(sortedKeys);
        System.out.println("Number of positions for each category");
        System.out.println("-------------------------------------");
        for (String key : sortedKeys) {
            System.out.println(key + ", " + categories.get(key));
        }
    }
}

